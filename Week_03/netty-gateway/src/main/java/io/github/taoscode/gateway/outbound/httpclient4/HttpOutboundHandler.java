package io.github.taoscode.gateway.outbound.httpclient4;

import io.github.taoscode.gateway.filter.HeaderHttpResponseFilter;
import io.github.taoscode.gateway.filter.HttpRequestFilter;
import io.github.taoscode.gateway.filter.HttpResponseFilter;
import io.github.taoscode.gateway.router.HttpEndpointRouter;
import io.github.taoscode.gateway.router.RandomHttpEndpointRouter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @author: vencent
 * @date: 2021/1/30 16:15
 * @description:
 */
public class HttpOutboundHandler {
    private CloseableHttpAsyncClient httpClient;
    private ExecutorService proxyService;
    private List<String> backendUrls;
    private HttpResponseFilter httpResponseFilter = new HeaderHttpResponseFilter();
    private HttpEndpointRouter router = new RandomHttpEndpointRouter();
    private static final Logger logger = LoggerFactory.getLogger(HttpOutboundHandler.class);

    public HttpOutboundHandler(List<String> backendUrls) {
        this.backendUrls = backendUrls.stream().map(this::formatUrl).collect(Collectors.toList());
        int cores = Runtime.getRuntime().availableProcessors();
        long keepAliveTime = 1000;
        int queueSize = 2048;
        proxyService = new ThreadPoolExecutor(cores, cores, keepAliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(queueSize),
                new NamedThreadFactory("proxyService"),new ThreadPoolExecutor.CallerRunsPolicy());
        IOReactorConfig ioReactorConfig = IOReactorConfig.custom().setIoThreadCount(cores)
                .setConnectTimeout(1000)
                .setSoTimeout(1000)
                .setRcvBufSize(32*1024)
                .build();
        httpClient = HttpAsyncClients.custom()
                .setMaxConnTotal(40)
                .setMaxConnPerRoute(8)
                .setDefaultIOReactorConfig(ioReactorConfig)
                .setKeepAliveStrategy((response,context)->6000)
                .build();
        httpClient.start();
    }

    public void handle(final FullHttpRequest request, final ChannelHandlerContext ctx,final HttpRequestFilter requestFilter){
        //todo 可以自定义路由器
        String backendUrl = router.route(backendUrls);
        String url = backendUrl+request.uri();
        //todo 可以自定义过滤器
        requestFilter.filter(request,ctx);
        //调用后端服务
        proxyService.submit(()->doService(request,ctx,url));
    }

    private void doService(final FullHttpRequest request,final ChannelHandlerContext ctx,final String url){
        HttpMethod httpMethod = request.method();
        switch (httpMethod.name().toUpperCase()){
            case "POST":
                fetchPost(request, ctx, url);
                break;
            case "GET":
                fetchGet(request, ctx, url);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + httpMethod.name());
        }
    }
    private void fetchGet(final FullHttpRequest request, final ChannelHandlerContext ctx,final String url){
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader(HTTP.CONN_DIRECTIVE,HTTP.CONN_KEEP_ALIVE);
        httpGet.setHeader("hi",request.headers().get("hi"));
        httpClient.execute(httpGet, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(HttpResponse response) {
                try {
                    handleResponse(request,ctx,response);
                } catch (Exception e) {
                    logger.error("fetchGet error:",e);
                }
            }

            @Override
            public void failed(Exception e) {
                httpGet.abort();
                logger.error("http get error:",e);
            }

            @Override
            public void cancelled() {
                httpGet.abort();
            }
        });
    }

    private void fetchPost(final FullHttpRequest request, final ChannelHandlerContext ctx,final String url){
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader(HTTP.CONN_DIRECTIVE,HTTP.CONN_KEEP_ALIVE);
        httpPost.setHeader("hi",request.headers().get("hi"));
        httpClient.execute(httpPost, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(HttpResponse response) {
                try {
                    handleResponse(request,ctx,response);
                } catch (Exception e) {
                   logger.error("fetchPost error:",e);
                }
            }

            @Override
            public void failed(Exception e) {
                httpPost.abort();
                logger.error("http post error:",e);
            }

            @Override
            public void cancelled() {
                httpPost.abort();
            }
        });
    }

    private void handleResponse(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx,
                                final HttpResponse endpointResponse) throws Exception {
        FullHttpResponse fullResponse = null;
        try{
            byte [] body = EntityUtils.toByteArray(endpointResponse.getEntity());
            fullResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.OK, Unpooled.wrappedBuffer(body));
            fullResponse.headers().set("Content-Type", "application/json");
            fullResponse.headers().setInt("Content-Length", Integer.parseInt(endpointResponse.getFirstHeader("Content-Length").getValue()));
            //TODO 经过Outbound过滤器处理
            httpResponseFilter.filter(fullResponse,ctx);
        }catch (Exception e){
            logger.error("handleResponse error",e);
            fullResponse = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
            ctx.close();
        }finally {
            if(fullRequest != null){
                if(HttpUtil.isKeepAlive(fullRequest)){
                    ctx.write(fullResponse).addListener(ChannelFutureListener.CLOSE);
                }else{
                    ctx.write(fullResponse);
                }
            }
            ctx.flush();
        }
    }
    private String formatUrl(String backend) {
        return backend.endsWith("/")?backend.substring(0,backend.length()-1):backend;
    }
}

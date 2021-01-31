package io.github.taoscode.gateway.inbound;

import io.github.taoscode.gateway.filter.HeaderHttpRequestFilter;
import io.github.taoscode.gateway.filter.HttpRequestFilter;
import io.github.taoscode.gateway.outbound.httpclient4.HttpOutboundHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;

import java.util.List;

/**
 * @author: vencent
 * @date: 2021/1/30 15:45
 * @description:
 */
public class HttpInboundHandler extends ChannelInboundHandlerAdapter {
    private List<String> proxyServers;
    private HttpOutboundHandler httpOutboundHandler;
    private HttpRequestFilter requestFilter = new HeaderHttpRequestFilter();
    public HttpInboundHandler(List<String> proxyServers) {
        this.proxyServers = proxyServers;
        this.httpOutboundHandler = new HttpOutboundHandler(this.proxyServers);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx)  {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)  {
        try{
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            httpOutboundHandler.handle(fullRequest,ctx,requestFilter);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ReferenceCountUtil.release(msg);
        }

    }
}

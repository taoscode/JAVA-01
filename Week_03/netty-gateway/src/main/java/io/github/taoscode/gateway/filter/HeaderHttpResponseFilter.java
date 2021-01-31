package io.github.taoscode.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpResponse;

/**
 * @author: vencent
 * @date: 2021/1/31 9:24
 * @description:
 */
public class HeaderHttpResponseFilter implements HttpResponseFilter{
    @Override
    public void filter(FullHttpResponse fullResponse, ChannelHandlerContext ctx) {
        fullResponse.headers().set("out","out filter test");
    }
}

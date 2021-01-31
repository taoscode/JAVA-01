package io.github.taoscode.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author: vencent
 * @date: 2021/1/31 9:22
 * @description:
 */
public class HeaderHttpRequestFilter implements HttpRequestFilter{
    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        fullRequest.headers().set("hi","测试你好不好");
    }
}

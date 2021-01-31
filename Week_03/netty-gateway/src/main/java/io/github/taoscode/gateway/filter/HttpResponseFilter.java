package io.github.taoscode.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpResponse;

/**
 * @author: vencent
 * @date: 2021/1/31 9:19
 * @description: 出站过滤器
 */
public interface HttpResponseFilter {
    void filter(FullHttpResponse fullResponse, ChannelHandlerContext ctx);
}

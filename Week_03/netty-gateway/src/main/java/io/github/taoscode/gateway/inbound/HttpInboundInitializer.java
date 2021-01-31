package io.github.taoscode.gateway.inbound;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

import java.util.List;

/**
 * @author: vencent
 * @date: 2021/1/30 15:46
 * @description:
 */
public class HttpInboundInitializer extends ChannelInitializer<SocketChannel> {
    private List<String> proxyServers;

    public HttpInboundInitializer(List<String> proxyServers) {
        this.proxyServers = proxyServers;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(1024*1024));
        pipeline.addLast(new HttpInboundHandler(this.proxyServers));
    }
}

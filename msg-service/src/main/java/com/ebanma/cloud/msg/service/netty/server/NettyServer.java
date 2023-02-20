package com.ebanma.cloud.msg.service.netty.server;

import com.ebanma.cloud.msg.service.netty.config.NettyServerConfig;
import com.ebanma.cloud.msg.service.netty.handler.NettyHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.mqtt.MqttDecoder;
import io.netty.handler.codec.mqtt.MqttEncoder;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @author 黄贵龙
 * @version $ Id: NettyServer, v 0.1 2023/02/15 13:46 banma- Exp $
 */
public class NettyServer {

    private NioEventLoopGroup bossGroup;

    private NioEventLoopGroup workGroup;

    private NettyServerConfig config;

    public NettyServer(NettyServerConfig config) {
        this.config = config;
    }

    /**
     * 启动服务
     */
    public void startup() {

        try {
            bossGroup = new NioEventLoopGroup(3);
            workGroup = new NioEventLoopGroup();

            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup);
            bootstrap.channel(NioServerSocketChannel.class);

            bootstrap.option(ChannelOption.SO_REUSEADDR, true)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                    .option(ChannelOption.SO_RCVBUF, 10485760);

            bootstrap.childOption(ChannelOption.TCP_NODELAY, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) {
                    ChannelPipeline channelPipeline = ch.pipeline();
                    channelPipeline.addLast(new IdleStateHandler(600, 600, 1200));
                    channelPipeline.addLast("encoder", MqttEncoder.INSTANCE);
                    channelPipeline.addLast("decoder", new MqttDecoder());
                    channelPipeline.addLast(new NettyHandler());
                }
            });
            ChannelFuture f = bootstrap.bind(config.getPort()).sync();
            if (f.isSuccess()) {
                System.out.println("startup success port = " + config.getPort());
                f.channel().closeFuture().sync();
            } else {
                System.out.println("startup fail port = " + config.getPort());
            }

        } catch (Exception e) {
            System.out.println("start exception" + e.toString());
        }

    }

    /**
     * 关闭服务
     */
    public void shutdown() throws InterruptedException {
        if (workGroup != null && bossGroup != null) {
            bossGroup.shutdownGracefully().sync();
            workGroup.shutdownGracefully().sync();
            System.out.println("shutdown success");
        }
    }

}
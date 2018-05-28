package com.jerrychan.server;

import com.jerrychan.server.handler.SimpleHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;


public class NettyServer {

    public static void main(String[] args) {
        //Netty服务启动引导类
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        //bossGroup是用来处理TCP连接请求的 (bossGroup只需要一个线程处理连接请求)
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        //workGroup是来处理IO事件的 (workerGroup设置使用默认线程数来处理 ,默认使用CPU核心的2倍 )
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        //设置Netty服务的线程池
        serverBootstrap.group(bossGroup, workerGroup);
        //设置Netty服务通道为Nio服务Socket通道
        serverBootstrap.channel(NioServerSocketChannel.class);
        //设置：当连接数满了,允许128个连接进行等待
        serverBootstrap.option(ChannelOption.SO_BACKLOG,128);
        //设置：保持连接处于活跃
        serverBootstrap.option(ChannelOption.SO_KEEPALIVE,true);

        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(new SimpleHandler());
            }
        });

        try {
            //将Netty服务绑定在8080端口,并且同步返回
            ChannelFuture channelFuture = serverBootstrap.bind(8080).sync();
            //同步等待通道关闭
            channelFuture.channel().closeFuture();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //服务端线程优雅的退出
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

}

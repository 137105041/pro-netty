package com.jerrychan.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class SimpleHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取数据
     * @param ctx 通道适配器上下文
     * @param msg 传输数据
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("****** 开始读取数据 *****");
        if(msg instanceof ByteBuf){

        }
        System.out.println("****** 结束读取数据 *****");
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

    }
}

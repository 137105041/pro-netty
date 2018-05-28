package com.jerrychan.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

public class SimpleHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端Short Text is ：" + ctx.channel().id().asShortText());
    }

    /**
     * 读取数据
     * @param ctx 通道适配器上下文
     * @param msg 传输数据
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("客户端Short Text is ：" + ctx.channel().id().asShortText());
        System.out.println("****** 开始读取数据 *****");
        if(msg instanceof ByteBuf){
            ByteBuf req = (ByteBuf) msg;
            String content = req.toString(Charset.defaultCharset());
            System.out.println(content);
        }
        System.out.println("****** 结束读取数据 *****");
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

    }
}

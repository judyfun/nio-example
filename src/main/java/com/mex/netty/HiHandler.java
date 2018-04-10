package com.mex.netty;

import org.jboss.netty.channel.*;

/**
 * xuchuahao
 * on 2018/4/10.
 */
public class HiHandler extends SimpleChannelHandler {

    @Override
    public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelOpen");
        super.channelOpen(ctx, e);
    }

    /**
     * 接收消息
     *
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        System.out.println("messageReceived");
        super.messageReceived(ctx, e);
    }


    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelClosed");
        super.channelClosed(ctx, e);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        System.out.println("exceptionCaught");
        super.exceptionCaught(ctx, e);
    }

    @Override
    public void channelBound(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelBound");
        super.channelBound(ctx, e);
    }

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelDisconnected");
        super.channelDisconnected(ctx, e);
    }

    @Override
    public void writeComplete(ChannelHandlerContext ctx, WriteCompletionEvent e) throws Exception {
        System.out.println("writeComplete");
        super.writeComplete(ctx, e);
    }

    @Override
    public void writeRequested(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        System.out.println("writeRequested");
        super.writeRequested(ctx, e);
    }

    @Override
    public void connectRequested(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("connectRequested");
        super.connectRequested(ctx, e);
    }

    @Override
    public void closeRequested(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("closeRequested");
        super.closeRequested(ctx, e);
    }
}

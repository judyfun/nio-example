package com.mex.netty;

import org.jboss.netty.channel.*;

/**
 * xuchuahao
 * on 2018/4/10.
 */
public class HelloHandler extends SimpleChannelHandler {
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        System.out.println("messageReceived");
        super.messageReceived(ctx, e);
    }

    @Override
    public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelOpen");
        super.channelOpen(ctx, e);
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelConnected");
        super.channelConnected(ctx, e);
    }

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelDisconneted");
        super.channelDisconnected(ctx, e);
    }

    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelClosed");
        super.channelClosed(ctx, e);
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
    public void bindRequested(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("bindRequested");
        super.bindRequested(ctx, e);
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

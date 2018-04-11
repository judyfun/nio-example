package com.mex.netty;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * xuchuahao
 * on 2018/4/10.
 */
public class Client {

    public static void main(String[] args) {
        //创建客户端
        ClientBootstrap client = new ClientBootstrap();

        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService worker = Executors.newCachedThreadPool();

        // 设置niosocket工厂
        client.setFactory(new NioClientSocketChannelFactory(boss, worker));

        client.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                pipeline.addLast("decoder", new StringDecoder());
                pipeline.addLast("encoder", new StringEncoder());
                pipeline.addLast("hiHandler", new HiClinetHandler());
                return pipeline;
            }
        });

        //链接服务端
        ChannelFuture connect = client.connect(new InetSocketAddress(9898));

        // 获得通道
        Channel channel = connect.getChannel();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请输入...");
            channel.write(scanner.next());
        }

    }
}

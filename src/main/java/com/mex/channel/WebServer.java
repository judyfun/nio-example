package com.mex.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * xuchuahao
 * on 2018/3/26.
 */
public class WebServer {

    public static void main(String[] args) {
        ServerSocketChannel socketChannel = null;
        try {

            socketChannel = ServerSocketChannel.open();
            socketChannel.socket().bind(new InetSocketAddress("127.0.0.1", 8000));
            SocketChannel accept = socketChannel.accept();

            ByteBuffer readBuffer = ByteBuffer.allocate(128);
            accept.read(readBuffer);

            readBuffer.flip();

            while (readBuffer.hasRemaining()) {
                System.out.println((char) readBuffer.get());
            }

            accept.close();
            socketChannel.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

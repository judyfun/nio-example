package com.mex.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * xuchuahao
 * on 2018/3/26.
 */
public class WebClient {
    public static void main(String[] args) {
        SocketChannel socketChannel = null;
        try {
            socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 8000));

            ByteBuffer writeBuffer = ByteBuffer.allocate(128);
            writeBuffer.put("hello world".getBytes());

            writeBuffer.flip();
            socketChannel.write(writeBuffer);
            socketChannel.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

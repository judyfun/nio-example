package com.mex.buffer;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

/**
 * xuchuahao
 * on 2018/4/2.
 */
public class DatagramChannelExample {

    @Test
    public void client() throws IOException {

        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 9898);

        // 1.获取通道
        DatagramChannel dataChannel = DatagramChannel.open();

        dataChannel.configureBlocking(false);

        //
        ByteBuffer buffer = ByteBuffer.allocate(1204);

        buffer.put("hello".getBytes());
        buffer.flip();

        // 发送
        dataChannel.send(buffer, address);
        buffer.clear();

        dataChannel.close();
    }

    @Test
    public void server() throws IOException {
        DatagramChannel channel = DatagramChannel.open();
        channel.configureBlocking(false);

        channel.bind(new InetSocketAddress(9898));

        Selector selector = Selector.open();

        channel.register(selector, SelectionKey.OP_READ);

        while (selector.select() > 0) {
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();

            while (it.hasNext()) {
                SelectionKey sk = it.next();

                if (sk.isReadable()) {
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    channel.receive(buffer);
                    buffer.flip();
                    System.out.println(new String(buffer.array(), 0, buffer.limit()));
                    buffer.clear();
                }
            }
            it.remove();
        }


    }


}

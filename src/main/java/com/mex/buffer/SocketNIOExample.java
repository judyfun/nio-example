package com.mex.buffer;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;

/**
 * xuchuahao
 * on 2018/4/2.
 */
public class SocketNIOExample {


    private ServerSocketChannel ssChannel;

    // 客户端
    @Test
    public void client() throws IOException {

        // 1. 获取通道
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

        // 2.设置为非阻塞
        channel.configureBlocking(false);

        // 3. 创建缓冲区,并写入通道
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(new Date().toString().getBytes());
        buffer.flip();
        channel.write(buffer);

        // 4.关闭通 道
        channel.close();
    }

    // 服务端
    @Test
    public void server() throws IOException {
        // 1.获取通道
        ssChannel = ServerSocketChannel.open();

        // 2.配置为非阻塞
        ssChannel.configureBlocking(false);

        ssChannel.bind(new InetSocketAddress(9898));

        // 3.获取选择器
        Selector selector = Selector.open();

        // 4.将通道注册到选择器上，并指定监听事件
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 5.轮询式获取选择器上已经“准备就绪”的事件
        while (selector.select() > 0) {
            //6. 获取当前选择器中素有注册的“选择键（已经就绪的监听事件）”
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            while (iterator.hasNext()) {
                // 7. 获取准备“就绪”的事件
                SelectionKey selectionKey = iterator.next();

                // 8. 判断具体什么事件准备就绪
                if (selectionKey.isAcceptable()) {
                    // 9.若“接收就绪"，获取客户端连接
                    SocketChannel acceptChannel = ssChannel.accept();
                    // 10.切换为非阻塞模式
                    acceptChannel.configureBlocking(false);

                    // 11.将该通道注册到选择器上
                    acceptChannel.register(selector, SelectionKey.OP_READ);

                } else if (selectionKey.isReadable()) {
                    // 12.获取当前选择器上“读就绪”状态的通道
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                    // 13.读取数据
                    ByteBuffer buffer = ByteBuffer.allocate(1024);

                    int len = 0;
                    while ((len = socketChannel.read(buffer)) > 0) {
                        buffer.flip();
                        System.out.println(new String(buffer.array(), 0, len));
                        buffer.clear();
                    }
                }
            }

            // 14.取消选择键上的SelectionKey
            iterator.remove();
        }


    }
}

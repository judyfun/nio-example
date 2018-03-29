package com.mex.buffer;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * xuchuahao
 * on 2018/3/29.
 * <p>
 * 一、通道（channel）：用于源节点与目标节点的连接，在java NIO中负责缓冲区的数据传输，因此需要配合缓冲区进行传输
 * <p>
 * 二、通道的主要实现类
 * java.nio.channels.Channel 接口：
 * |--FileChannel
 * |--SocketChannel
 * |--ServerSocketChannel
 * |--DatagramChannel
 * <p>
 * 三、获取通道
 * 1.java针对支持通道提供了 getChannel（）方法
 * 本地IO：
 * FileInputStream/FileOutputStream
 * RandomAccessFile
 * <p>
 * 网络IO：
 * Socket
 * ServerSocket
 * DatagramSocket
 * <p>
 * 2. 在jdk 1.7中 NIO.2 针对各个通道提供了静态方法 open（）
 * 3. 在jdk 1.7中 NIO.2 的files工具类 newByteChannel（）
 * <p>
 * 4.通道之间的数据传输
 * transferTo
 * transferFrom
 */

public class ChannelExample {

    @Test
    public void copyDemo3() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("3.jpg"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        inChannel.transferTo(0, inChannel.size(), outChannel);

        inChannel.close();
        outChannel.close();

    }

    // 使用直接缓冲区复制文件（内存映射文件）
    @Test
    public void copyDemo2() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        inChannel.close();
        outChannel.close();
    }

    @Test
    // 1.利用通道完成文件复制（非直接缓冲区）
    public void copyDemo1() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            fos = new FileOutputStream(new File("2.jpg"));
            fis = new FileInputStream(new File("1.jpg"));
            // 获取通道
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();

            // 创建缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            // 将缓冲区放入通道
            while (inChannel.read(buffer) != -1) {
                // 将缓冲区变为可读模式
                buffer.flip();
                outChannel.write(buffer);
                buffer.clear();
            }

        } catch (java.io.IOException e) {
            e.printStackTrace();
        } finally {
            if (inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outChannel != null) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

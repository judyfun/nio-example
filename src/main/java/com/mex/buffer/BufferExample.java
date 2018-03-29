package com.mex.buffer;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * xuchuahao
 * on 2018/3/29.
 */
public class BufferExample {

    @Test
    public void bufferDemo3() {
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);

        System.out.println(allocate.isDirect());
        System.out.println(byteBuffer.isDirect());
    }

    @Test
    public void bufferDemo2() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        String str = "abcd";

        byteBuffer.put(str.getBytes());

        byteBuffer.flip();

        byte[] dst = new byte[byteBuffer.limit()];
        byteBuffer.get(dst, 0, 2);

        System.out.println(new String(dst, 0, 2));
        System.out.println(byteBuffer.position());

        byteBuffer.mark();

        byteBuffer.get(dst, 2, 2);
        System.out.println(new String(dst, 2, 2));
        System.out.println(byteBuffer.position());

        byteBuffer.reset();
        System.out.println(byteBuffer.position());
    }

    @Test
    public void bufferDemo() {

        String str = "abcdef";
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        System.out.println("--------init-------");
        System.out.println("position:" + byteBuffer.position());
        System.out.println("limit:" + byteBuffer.limit());
        System.out.println("capacity:" + byteBuffer.capacity());

        byteBuffer.put(str.getBytes());

        System.out.println("--------put-------");
        System.out.println("position:" + byteBuffer.position());
        System.out.println("limit:" + byteBuffer.limit());
        System.out.println("capacity:" + byteBuffer.capacity());

        byteBuffer.flip();


        System.out.println("--------flip-------");
        System.out.println("position:" + byteBuffer.position());
        System.out.println("limit:" + byteBuffer.limit());
        System.out.println("capacity:" + byteBuffer.capacity());

        byte b = byteBuffer.get(0);
        System.out.println((char) b);

        byte[] aa = new byte[byteBuffer.limit()];
        byteBuffer.get(aa, 0, byteBuffer.limit());

        System.out.println("--------get-------");
        System.out.println(new String(aa));
        System.out.println("position:" + byteBuffer.position());
        System.out.println("limit:" + byteBuffer.limit());
        System.out.println("capacity:" + byteBuffer.capacity());

        byteBuffer.rewind();
        System.out.println("--------rewind-------");
        System.out.println("position:" + byteBuffer.position());
        System.out.println("limit:" + byteBuffer.limit());
        System.out.println("capacity:" + byteBuffer.capacity());

        byteBuffer.clear();
        System.out.println("--------clear-------");
        System.out.println("position:" + byteBuffer.position());
        System.out.println("limit:" + byteBuffer.limit());
        System.out.println("capacity:" + byteBuffer.capacity());


    }
}

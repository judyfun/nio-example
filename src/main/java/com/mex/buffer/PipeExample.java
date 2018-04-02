package com.mex.buffer;

import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * xuchuahao
 * on 2018/4/2.
 */
public class PipeExample {

    @Test
    public void client() throws IOException {
        Pipe pipe = Pipe.open();
        Pipe.SinkChannel sinkChannel = pipe.sink();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("A Java NIO Pipe is a one-way data connection between two threads. A Pipe has a source channel and a sink channel. You write data to the sink channel. This data can then be read from the source channel".getBytes());
        buffer.flip();

        while (buffer.hasRemaining()) {
            sinkChannel.write(buffer);
        }

        Pipe.SourceChannel sourceChannel = pipe.source();
        buffer.flip();

        int bytesRead = sourceChannel.read(buffer);
        System.out.println(new String (buffer.array(),0,bytesRead));
        sourceChannel.close();

        sinkChannel.close();

    }

    @Test
    public void server() throws IOException {
        Pipe pipe = Pipe.open();

    }
}

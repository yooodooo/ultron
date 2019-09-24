package com.github.udoo.ultron.doc.test.nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ChannelTest {

    static final String READ_FILE_PATH = "document/pic/bean.jpg";

    @Test
    public void test_channel() {
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;

        FileChannel inChannel = null;
        FileChannel outChannel = null;

        try {
            inputStream = new FileInputStream(READ_FILE_PATH);
            outputStream = new FileOutputStream("2.jpg");

            inChannel = inputStream.getChannel();
            outChannel = outputStream.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (inChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                outChannel.write(byteBuffer);
                byteBuffer.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {

                    inputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {

                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (inChannel != null) {
                try {

                    inChannel.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (outChannel != null) {
                try {

                    outChannel.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

    }

    @Test
    public void test_mapped() throws Exception {
        FileChannel inChannel = FileChannel.open(Paths.get(READ_FILE_PATH), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        MappedByteBuffer inMappedByteBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMappedByteBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        byte[] bytes = new byte[inMappedByteBuffer.limit()];
        inMappedByteBuffer.get(bytes);
        outMappedByteBuffer.put(bytes);

        inChannel.close();
        outChannel.close();
    }

    @Test
    public void test_transfer() throws Exception {
        FileChannel inChannel = FileChannel.open(Paths.get(READ_FILE_PATH), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

//        inChannel.transferTo(0, inChannel.size(), outChannel);
        outChannel.transferFrom(inChannel, 0, inChannel.size());

        inChannel.close();
        outChannel.close();
    }
}

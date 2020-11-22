package com.ego.utils;

import java.io.*;

/**
 * @author liuweiwei
 */
public class IOFileUtils {
    /**
     * 1. 图片读取到字节数组
     * 1.1 图片到程序：FileInputStream
     * 1.2 程序到字节数组：ByteArrayOutputStream
     */
    public static byte[] fileToBytes(String path) throws IOException {
        InputStream is = new FileInputStream(new File(path));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024 * 10];
        int length = -1;
        while ((length = is.read(bytes)) != -1) {
            output.write(bytes, 0, length);
        }
        output.flush();
        byte[] data = output.toByteArray();
        output.close();
        is.close();
        return data;
    }

    /**
     * 2. 字节数组写出到图片
     * 2.1 字节数组到程序：ByteArrayInputStream
     * 2.2 程序到文件：FileOutputStream
     */
    public static void bytesToFile(byte[] src, String path) throws IOException {
        InputStream is = new ByteArrayInputStream(src);
        OutputStream output = new FileOutputStream(new File(path));
        byte[] bytes = new byte[5];
        int length = -1;
        while ((length = is.read(bytes)) != -1) {
            output.write(bytes, 0, length);
        }
        output.flush();
        output.close();
        is.close();
    }
}

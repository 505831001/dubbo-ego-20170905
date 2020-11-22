package com.ego.net;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.Socket;

/**
 * @author liuweiwei
 * @since 2020-09-27
 */
public class TCPFileClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhsot", 8848);
        OutputStream os = socket.getOutputStream();
        BufferedInputStream input = new BufferedInputStream(new FileInputStream("src/tcp.png"));
        BufferedOutputStream output = new BufferedOutputStream(os);
        byte[] bytes = IOUtils.toByteArray(input);
        int length = -1;
        while ((length = input.read()) != -1) {
            output.write(bytes, 0, length);
        }
        output.flush();
        socket.close();
    }
}

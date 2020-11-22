package com.ego.net;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author liuweiwei
 * @since 2020-09-27
 */
public class TCPFileServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8848);
        Socket socket = server.accept();
        BufferedInputStream   input = new BufferedInputStream(socket.getInputStream());
        BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream("src/tcp.png"));
        byte[] bytes = IOUtils.toByteArray(input);
        int length = -1;
        while ((length = input.read()) != -1) {
            output.write(bytes, 0, length);
        }
        output.flush();
        socket.close();
        server.close();
    }
}

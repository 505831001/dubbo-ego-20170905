package com.ego.net;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author liuweiwei
 * @since 2020-09-27
 */
public class TCPToServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8848);
        Socket socket = server.accept();
        DataInputStream input = new DataInputStream(socket.getInputStream());
        String data = input.readUTF();
        System.out.println(data);
        socket.close();
        server.close();
    }
}

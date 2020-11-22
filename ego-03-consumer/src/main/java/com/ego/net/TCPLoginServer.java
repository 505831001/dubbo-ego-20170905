package com.ego.net;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author liuweiwei
 * @since 2020-09-27
 */
public class TCPLoginServer {
    protected static ServerSocket server;
    protected static Socket socket;

    private static InputStream is;

    public static void main(String[] args) throws IOException {
        try {
            server = new ServerSocket(8848);
            socket = server.accept();
            is = socket.getInputStream();
            DataInputStream input = new DataInputStream(is);
            String data = input.readUTF();
            String[] array = data.split("&");
            for (int i = 0; i < array.length; i++) {
                String[] strings = array[i].split("=");
                if (strings[0].equals("username")) {
                    System.out.println(strings[1]);
                } else if (strings[0].equals("password")) {
                    System.out.println(strings[1]);
                }
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
            server.close();
        }
    }
}

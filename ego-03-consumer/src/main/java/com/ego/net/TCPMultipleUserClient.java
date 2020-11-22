package com.ego.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author liuweiwei
 * @since 2020-09-27
 */
public class TCPMultipleUserClient {
    protected static ServerSocket server;
    protected static Socket socket;

    public static void main(String[] args) throws IOException {
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(is);
        System.out.println("Please input username -> ");
        String username = reader.readLine();
        System.out.println("Please input password -> ");
        String password = reader.readLine();
        try {
            socket = new Socket("localhost", 8848);
            OutputStream os = socket.getOutputStream();
            DataOutputStream output = new DataOutputStream(os);
            output.writeUTF("username=" + username + "&" + "password=" + password);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }
}

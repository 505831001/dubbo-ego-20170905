package com.ego.net;

import lombok.SneakyThrows;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author liuweiwei
 * @since 2020-09-27
 */
public class TCPMultipleUserServer {
    protected static ServerSocket server;
    protected static Socket socket;

    public static void main(String[] args) throws IOException {
        boolean isRunning = true;
        try {
            server = new ServerSocket(8848);
            while (isRunning) {
                socket = server.accept();
                new Thread(new Runnable() {
                    @SneakyThrows
                    @Override
                    public void run() {
                        DataInputStream input = new DataInputStream(socket.getInputStream());
                        String data = input.readUTF();
                        String[] array = data.split("&");
                        for (int i = 0; i < array.length; i++) {
                            System.out.println(array[i].split("="));
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
            server.close();
        }
    }
}

package com.ego.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * @author liuweiwei
 * @since 2020-09-27
 */
public class UDPTaskSender implements Runnable {
    protected DatagramSocket socket = null;
    protected DatagramPacket packet = null;

    @Override
    public void run() {
        try {
            socket = new DatagramSocket(8848);
            while (true) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String message = reader.readLine();
                byte[] bytes = message.getBytes();
                InetSocketAddress socketAddress = new InetSocketAddress("localhost", 8080);
                packet = new DatagramPacket(bytes, 0, bytes.length, socketAddress);
                socket.send(packet);
                if (message.equals("exit")) {
                    break;
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }
}

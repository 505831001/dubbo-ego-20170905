package com.ego.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @author liuweiwei
 * @since 2020-09-27
 */
public class UDPTaskReceive implements Runnable {
    protected DatagramSocket socket = null;
    protected DatagramPacket packet = null;

    @Override
    public void run() {
        try {
            socket = new DatagramSocket(8848);
            while (true) {
                byte[] container = new byte[1024 * 64];
                packet = new DatagramPacket(container, 0, container.length);
                socket.receive(packet);
                byte[] bytes = packet.getData();
                int length = packet.getLength();
                String data = new String(bytes, 0, length);
                System.out.println("The server receives the information ->" + data);
                if (data.equals("exit")) {
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

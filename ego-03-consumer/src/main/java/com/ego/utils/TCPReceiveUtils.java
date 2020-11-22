package com.ego.utils;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA
 *
 * @Author liuweiwei 505831001@qq.com
 * @Description 网络编程之TCP传输协议 接收端工具类
 * @since 2020-05-20
 */
public class TCPReceiveUtils implements Runnable {
    @Override
    public void run() {
        boolean isRunning = true;
        while (isRunning) {
            // 1.接收消息
            String message = "";
            Socket client = new Socket();
            try {
                DataInputStream dis = new DataInputStream(client.getInputStream());
                message = dis.readUTF();
            } catch (IOException e) {
                System.out.println("====4====");
                isRunning = false;
            }
            if (!message.equals("")) {
                System.out.println(message);
            }
        }
    }
}

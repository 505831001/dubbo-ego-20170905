package com.ego.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA
 *
 * @Author liuweiwei 505831001@qq.com
 * @Description 网络编程之TCP传输协议 发送端工具类
 * @since 2020-05-20
 */
public class TCPSendUtils implements Runnable {
    @Override
    public void run() {
        boolean isRunning = true;
        while(isRunning) {
            // 1.从控制台获取消息
            String message = "";
            try {
                BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
                message = console.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 2.发送消息
            if(!message.equals("")) {
                try {
                    Socket client = new Socket();
                    DataOutputStream dos = new DataOutputStream(client.getOutputStream());
                    dos.writeUTF(message);
                    dos.flush();
                } catch (IOException e) {
                    System.out.println(e);
                    System.out.println("===3==");
                    isRunning = false;
                }
            }
        }
    }
}

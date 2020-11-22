package com.ego.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author liuweiwei
 * @since 2020-09-04
 */
public class TCPChatRoomServer {
    public static void main(String[] args) throws IOException {
        System.out.println("聊天室的服务器");
        ServerSocket server = new ServerSocket(8848);

        while (true) {
            boolean flag = true;
            Socket socket = server.accept();
            System.out.println("一个客户端建立了连接");
            DataInputStream  is = new DataInputStream(socket.getInputStream());
            DataOutputStream os = new DataOutputStream(socket.getOutputStream());
            while (flag) {
                String message = is.readUTF();
                os.writeUTF(message);
                os.flush();
            }
            os.close();
            is.close();
            socket.close();
        }
    }
}

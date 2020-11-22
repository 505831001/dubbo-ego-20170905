package com.ego.net;

import java.io.*;
import java.net.Socket;

/**
 * @author liuweiwei
 * @since 2020-09-04
 */
public class TCPChatRoomClient {
    public static void main(String[] args) throws IOException {
        System.out.println("聊天室的客户端");
        Socket socket = new Socket("localhost", 8848);

        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader   console = new BufferedReader(reader);
        DataOutputStream  output = new DataOutputStream(socket.getOutputStream());
        DataInputStream    input = new DataInputStream(socket.getInputStream());

        boolean flag = true;
        while (flag) {
            String message = console.readLine();
            output.writeUTF(message);
            output.flush();
            message = input.readUTF();
            System.out.println(message);
        }
        output.close();
         input.close();
        socket.close();
    }
}

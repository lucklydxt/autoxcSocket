package com.zidongxiche.clientdemo;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author dxt
 * @date 2020/5/2 13:33
 */
public class ClientSocketDemo {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8080;
    private static final int BUFFER_SIZE = 8;
    public static void main(String[] args) throws Exception {
        Socket client = new Socket(HOST, PORT);

        InputStream in=client.getInputStream();
        OutputStream out = client.getOutputStream();

        try {
            while (true) {
            Scanner inp = new Scanner(System.in);
            String str = inp.next();

                Thread.sleep(3000);
                System.out.println("发送心跳数据包");
                out.write(str.getBytes());

                byte[] recData = new byte[BUFFER_SIZE];
                int r = in.read(recData);
                if(r>-1) {
                String data = new String(recData);
                if(data.trim().equals("exit")) {
                    client.close();
                }
               System.out.println("读取到  -->客户端--->发送的来数据："+data);

//                byte[] buff  = new byte[BUFFER_SIZE];
//
//                int len=0;
//                StringBuffer data = new StringBuffer();
//                while((len = in.read(buff)) != -1) {
//                    System.out.println("dsdasa");
//                    data.append(new String(buff, 0, len));
//                    System.out.println("dsdasa"+data.toString());
//                }
//                System.out.println("接收到参数"+data.toString());

//                if(r>-1) {
//                    String data = new String(recData);
//                    System.out.println("123456"+data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.close();
            client.close();
        }
    }
}

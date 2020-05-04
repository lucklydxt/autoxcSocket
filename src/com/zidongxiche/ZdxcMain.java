package com.zidongxiche;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

import com.zidongxiche.utils.ServerHandlerExcutePool;
import com.zidongxiche.utils.ServerHanlder;

public class ZdxcMain {
	private static final int PORT = 8080;
    public static void main(String[] args) {
	  try {
          ServerSocket socketserver = new ServerSocket(PORT);
          while (true) {
              //��������
              Socket socket = socketserver.accept();
              SocketAddress sa=socket.getRemoteSocketAddress();
              System.out.println(sa.toString());
             
              //�½�һ���߳�ȥ��������
              //new Thread(new TestServerHanlder(socket)).start();
              //ʹ���̳߳صķ�ʽȥ������
              ServerHandlerExcutePool pool = new ServerHandlerExcutePool(4, 4);
              
              pool.execute(new ServerHanlder(socket));
          }
           
      } catch (Exception e) {
          //e.printStackTrace();
    	  System.out.println("socket �ͻ��˹ر�����");
      }
  }
}

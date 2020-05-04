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
              //监听连接
              Socket socket = socketserver.accept();
              SocketAddress sa=socket.getRemoteSocketAddress();
              System.out.println(sa.toString());
             
              //新建一条线程去处理数据
              //new Thread(new TestServerHanlder(socket)).start();
              //使用线程池的方式去做处理
              ServerHandlerExcutePool pool = new ServerHandlerExcutePool(4, 4);
              
              pool.execute(new ServerHanlder(socket));
          }
           
      } catch (Exception e) {
          //e.printStackTrace();
    	  System.out.println("socket 客户端关闭连接");
      }
  }
}

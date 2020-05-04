package com.zidongxiche.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;


import com.zidongxiche.dao.ZdxcDao;

public class ServerHanlder implements Runnable  {

	 private static final int BUFFER_SIZE = 8;
	 private Socket socket;
	 public ServerHanlder(Socket socket) {
	        this.socket = socket;
	 }
	 boolean flag=true;
//	public void runtest() {
//		System.out.println("连接建立成功");
//		BufferedReader in = null;
//        PrintWriter out = null;
//        try {
//            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
//            out = new PrintWriter(this.socket.getOutputStream(), true);
//            String body = null;
//            while (true) {
//                body = in.readLine();
//                if ("exit".equals(body)) {
//                    break;
//                }
//                System.out.println("接收到的数据=="+body);
////               int result= ZdxcDao.insert(body);
////               out.write(result);
////               out.flush();
//            }
//        } catch (Exception e) {
//        	e.printStackTrace();
//        }
//	}

	@Override
	public void run() {
		 try {
		    System.out.println("连接建立成功");
		    //socket.setKeepAlive(true); //设置心跳检测，但是要开启linux系统底层配置
		    socket.setSoTimeout(15000);
			InputStream in = this.socket.getInputStream();
			OutputStream out = this.socket.getOutputStream();
			
			while(true) { 

				byte[] recData = new byte[BUFFER_SIZE];
				int r = in.read(recData);
				if(r>-1) {
					String data = new String(recData);
					if(data.trim().equals("exit")) {
						break;
					}
					System.out.println("读取到  -->客户端--->发送的来数据："+data);
					System.out.println("数据长度"+data.length());
					//int result= ZdxcDao.insert(data);
					 
					out.write("11111111".getBytes());
					out.flush();
				}else {
				    
					System.out.println("数据读取完毕！");
					break ; 
					//flag=false; 
				}
                System.out.println("正常情况下----是否关闭 1 "+socket.isClosed()); // 是否关闭  
                System.out.println("正常情况下----是否关闭输入流1 "+socket.isInputShutdown()); // 是否关闭输入流  
                System.out.println("正常情况下----是否关闭输出流1"+socket.isOutputShutdown()); // 是否关闭输出流 
			} 
//			if(!flag) {
//				socket.shutdownOutput();
//				socket.shutdownInput();
//				socket.close();
//				System.out.println("关闭当前socket连接111"+socket.getRemoteSocketAddress().toString());
//			} 
			System.out.println("跳出循环后---->是否关闭  "+socket.isClosed()); // 是否关闭 
            System.out.println("跳出循环后---->是否关闭输入流 "+socket.isInputShutdown()); // 是否关闭输入流  
            System.out.println("跳出循环后---->是否关闭输出流"+socket.isOutputShutdown()); // 是否关闭输出流 
            
		 } catch (Exception e) {
			 System.out.println("异常情况下--->关闭当前socket连接000"+socket.getRemoteSocketAddress().toString());
			 try {
				socket.shutdownOutput();
				socket.shutdownInput();
				socket.close();
			 } catch (IOException e1) {
				e1.printStackTrace();
			 }
			
             System.out.println("异常情况下--->是否关闭2  "+socket.isClosed()); // 是否关闭  
             System.out.println("异常情况下--->是否关闭输入流2 "+socket.isInputShutdown()); // 是否关闭输入流  
             System.out.println("异常情况下--->是否关闭输出流2"+socket.isOutputShutdown()); // 是否关闭输出流 
			
			 e.printStackTrace(); 
	     }finally{
    	    try {
    	    	
    	    	 System.out.println("最终执行----->"+socket.toString());
	    	    
					socket.shutdownOutput();
					socket.shutdownInput();
					socket.close();
				 
    	    	 //System.out.println("是否邦定3 "+socket.isBound()); // 是否邦定  
                 System.out.println("最终执行----->是否关闭3  "+socket.isClosed()); // 是否关闭  
                 //System.out.println("是否连接3"+socket.isConnected()); // 是否连接  
                 System.out.println("最终执行----->是否关闭输入流3 "+socket.isInputShutdown()); // 是否关闭输入流  
                 System.out.println("最终执行----->是否关闭输出流3"+socket.isOutputShutdown()); // 是否关闭输出流 
			} catch (Exception e1) {
				System.out.println("socket 已经关闭");
				e1.printStackTrace();
			}
	     }
	} 
}

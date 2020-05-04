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
//		System.out.println("���ӽ����ɹ�");
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
//                System.out.println("���յ�������=="+body);
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
		    System.out.println("���ӽ����ɹ�");
		    //socket.setKeepAlive(true); //����������⣬����Ҫ����linuxϵͳ�ײ�����
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
					System.out.println("��ȡ��  -->�ͻ���--->���͵������ݣ�"+data);
					System.out.println("���ݳ���"+data.length());
					//int result= ZdxcDao.insert(data);
					 
					out.write("11111111".getBytes());
					out.flush();
				}else {
				    
					System.out.println("���ݶ�ȡ��ϣ�");
					break ; 
					//flag=false; 
				}
                System.out.println("���������----�Ƿ�ر� 1 "+socket.isClosed()); // �Ƿ�ر�  
                System.out.println("���������----�Ƿ�ر�������1 "+socket.isInputShutdown()); // �Ƿ�ر�������  
                System.out.println("���������----�Ƿ�ر������1"+socket.isOutputShutdown()); // �Ƿ�ر������ 
			} 
//			if(!flag) {
//				socket.shutdownOutput();
//				socket.shutdownInput();
//				socket.close();
//				System.out.println("�رյ�ǰsocket����111"+socket.getRemoteSocketAddress().toString());
//			} 
			System.out.println("����ѭ����---->�Ƿ�ر�  "+socket.isClosed()); // �Ƿ�ر� 
            System.out.println("����ѭ����---->�Ƿ�ر������� "+socket.isInputShutdown()); // �Ƿ�ر�������  
            System.out.println("����ѭ����---->�Ƿ�ر������"+socket.isOutputShutdown()); // �Ƿ�ر������ 
            
		 } catch (Exception e) {
			 System.out.println("�쳣�����--->�رյ�ǰsocket����000"+socket.getRemoteSocketAddress().toString());
			 try {
				socket.shutdownOutput();
				socket.shutdownInput();
				socket.close();
			 } catch (IOException e1) {
				e1.printStackTrace();
			 }
			
             System.out.println("�쳣�����--->�Ƿ�ر�2  "+socket.isClosed()); // �Ƿ�ر�  
             System.out.println("�쳣�����--->�Ƿ�ر�������2 "+socket.isInputShutdown()); // �Ƿ�ر�������  
             System.out.println("�쳣�����--->�Ƿ�ر������2"+socket.isOutputShutdown()); // �Ƿ�ر������ 
			
			 e.printStackTrace(); 
	     }finally{
    	    try {
    	    	
    	    	 System.out.println("����ִ��----->"+socket.toString());
	    	    
					socket.shutdownOutput();
					socket.shutdownInput();
					socket.close();
				 
    	    	 //System.out.println("�Ƿ�3 "+socket.isBound()); // �Ƿ�  
                 System.out.println("����ִ��----->�Ƿ�ر�3  "+socket.isClosed()); // �Ƿ�ر�  
                 //System.out.println("�Ƿ�����3"+socket.isConnected()); // �Ƿ�����  
                 System.out.println("����ִ��----->�Ƿ�ر�������3 "+socket.isInputShutdown()); // �Ƿ�ر�������  
                 System.out.println("����ִ��----->�Ƿ�ر������3"+socket.isOutputShutdown()); // �Ƿ�ر������ 
			} catch (Exception e1) {
				System.out.println("socket �Ѿ��ر�");
				e1.printStackTrace();
			}
	     }
	} 
}

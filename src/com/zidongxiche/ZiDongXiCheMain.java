package com.zidongxiche;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ZiDongXiCheMain {

	private static final int PORT = 8080;
	private static final int BUFFER_SIZE = 1024;
	
//	public static void main(String[] args) {
//		try {
//			ServerSocket ss = new ServerSocket(PORT);
//			Socket s = ss.accept();
//			System.out.println("���Ƿ���ˣ���������"+PORT+"�˿�");
//			byte[] recData = null;
//			InputStream in = s.getInputStream();
//			OutputStream out = s.getOutputStream();
//			while(true) {
//				recData = new byte[BUFFER_SIZE];
//				int r = in.read(recData);
//				//int r = in.read(recData);
//				if(r>-1) {
//					String data = new String(recData);
//					if(data.trim().equals("exit")) {
//						s.close();
//					}
//					System.out.println("��ȡ���ͻ��˷��͵������ݣ�"+data);
//					out.write("���Ƿ���˷����ͻ��˵����ݣ�".getBytes());
//					out.write(recData);
//				}else {
//					System.out.println("���ݶ�ȡ��ϣ�");
//					s.close();
//					System.exit(0);
//					//ss.close();
//				}
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//	}

}

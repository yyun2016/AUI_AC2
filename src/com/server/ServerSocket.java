package com.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import com.log.Log;

public class ServerSocket{
	public static String IpAddress = "192.168.1.86";
	private static int Port = 9999;
	public static Socket socket = null;
	private static BufferedWriter writer;
	private static BufferedReader reader;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			CreateSocket();
			
			SendMsg("Display");
			ReceiveMsg();
			
			//receiveFile(socket);
			CloseSocket();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void CreateSocket() throws IOException
	{
		socket = new Socket(IpAddress, Port);
		Log.info("建立连接....");  
	}

	public static void CloseSocket() throws IOException
	{
		if(reader!=null){
			reader.close();
		}
		if(writer!=null){
			writer.close();
		}
		//socket.close();
		Log.info("断开连接....");
	}

	public static void SendMsg(String Msg) throws IOException 
	{
		Log.info("发送命令:"+Msg);
		writer = new BufferedWriter(
				new OutputStreamWriter(socket.getOutputStream()));
		writer.write(Msg+"\n");
		writer.flush();

	}
	public static void receiveFile(Socket socket) {

		byte[] inputByte = null;
		int length = 0;
		DataInputStream dis = null;
		FileOutputStream fos = null;
		try {
			try {
				File file = new File("D:\\b.jpg");
				System.out.println(file.getAbsolutePath());
				dis = new DataInputStream(socket.getInputStream());
				fos = new FileOutputStream(file);
				inputByte = new byte[1024*4];
				System.out.println("开始接收数据...");
				while ((length = dis.read(inputByte, 0, inputByte.length)) > 0) {
					fos.write(inputByte, 0, length);
					fos.flush();
				}
				System.out.println("完成接收");
			} finally {
				if (fos != null){
					fos.close();
				}
				if (dis != null){
					dis.close();
				}
			}
		} catch (Exception e) {

		}

	}
	public static String ReceiveMsg() throws IOException
	{
		reader = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));     
		String txt=reader.readLine();
		Log.info("命令结果:"+txt);
		return txt;
	}
}

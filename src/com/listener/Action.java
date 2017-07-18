package com.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import com.log.Log;
import com.server.ServerSocket;

public class Action implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("TelephonyBtn")){
			Log.info("Start ATT Device Stability Test:Telephony");
			
			Thread t1 = new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						ServerSocket.CreateSocket();
						
						ServerSocket.SendMsg("系统设置");
						ServerSocket.ReceiveMsg();
						
						ServerSocket.SendMsg("WLAN");
						ServerSocket.ReceiveMsg();
						
						ServerSocket.CloseSocket();
						
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			});
			t1.start();
			
		}
	}

}

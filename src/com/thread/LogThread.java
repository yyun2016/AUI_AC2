package com.thread;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import com.helper.ServiceHelper;


public class LogThread implements Runnable{
	List<String> cmds;
	String random;
	int row;
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub

	}
	public LogThread(String random, ArrayList<String> cmds){
		this.cmds = cmds;
		this.random = random;
	}
	public void run() {
		Hashtable<String,Object> ret = ServiceHelper.RunCommand(cmds);
	}
}

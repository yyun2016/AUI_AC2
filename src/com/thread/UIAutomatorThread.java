package com.thread;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import com.frame.MainFrame;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import com.helper.ServiceHelper;
import com.log.Log;


public class UIAutomatorThread implements Runnable{
	List<String> cmds;
	String random;
	int row;
	String status;
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub

	}
	public UIAutomatorThread(int row ,String random, ArrayList<String> cmds,String status){
		this.cmds = cmds;
		this.random = random;
		this.row = row;
		this.status = status;
	}
	public void run() {
		if (MainFrame.termiante) {
			
		}else {
			MainFrame.scriptTable.setRowSelectionInterval(row, row);
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
			String startDate = df.format(new Date());
			
			Hashtable<String,Object> ret = ServiceHelper.RunCommand(cmds);
			if (MainFrame.termiante) {
				
			}else{
				Object object = ret.get("code");
				String retstr = ret.get("msg").toString();
				if((Integer)object==0){
					System.out.println("脚本执行成功！");
					Log.warn("...脚本执行成功！...");
					//JOptionPane.showMessageDialog(new JFrame(), "脚本执行成功!","提示", JOptionPane.INFORMATION_MESSAGE);
				}else{
					System.out.println("脚本执行失败！");
					Log.err("...脚本执行失败！");
					Log.err(ret.get("msg").toString());
					//JOptionPane.showMessageDialog(new JFrame(), "脚本执行失败!","警告", JOptionPane.WARNING_MESSAGE);
				}
				//String configXml = JarHelper.getProjectPath()+"config\\config.xml";

				//Statistics scts = MainFrame.read_script_account(configXml);

				String result = "NOTRUN";
				if (retstr.contains("OK (1 test)")||ServiceHelper.isPass(retstr)) {
					MainFrame.scriptTable.setValueAt("PASS", row, 6);
					result = "PASS";
					//doStatus(scts, status, "pass");
					//XmlUpdate.testCaseUpdateXml(Constant.writexml, caseBean, "resulttag", "PASS");
				}
				if (retstr.contains("FAILURES!!!")||ServiceHelper.isFail(retstr)||retstr.contains("发生异常，正在捕获异常")) {
					MainFrame.scriptTable.setValueAt("FAIL", row, 6);
					result = "FAIL";
					//doStatus(scts, status, "fail");
					//XmlUpdate.testCaseUpdateXml(Constant.writexml, caseBean, "resulttag", "FAIL");
				}
				if (retstr.contains("Terminated")) {
					result = "Terminated";
					MainFrame.scriptTable.setValueAt("Terminated", row, 6);
					//doStatus(scts, status, "terminate");
					//XmlUpdate.testCaseUpdateXml(Constant.writexml, caseBean, "resulttag", "Terminated");
				}
				MainFrame.scriptTable.repaint();
				String caseName =String.format("%s_%s",MainFrame.scriptTable.getValueAt(row, 3).toString(),random.split("_")[1]+"_"+random.split("_")[2]); 
				String logmsg = ret.get("msg").toString().replaceAll("test=.*\n", "test="+caseName+"\n");
				logmsg = logmsg.replaceAll("\n", "|BR|")+""; 
				Log.info(logmsg);

				String endDate = df.format(new Date());
				String classname = MainFrame.scriptTable.getValueAt(row, 2).toString().split("#")[0];
			
				try {
					Database qdb = new DatabaseBuilder(new File("Test.mdb")).open();
					Table  table = qdb.getTable("log");
					for (Row row : table) {
						String id_testcase = row.get("id_testcase").toString();
						if (random.equals(id_testcase)) {
							row.put("result", result);
							row.put("TimeStart", startDate);
							row.put("TimeEnd", endDate);
							row.put("LogArgument", logmsg);
							row.put("classname", classname);
							table.updateRow(row);
						}
					}
					qdb.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				MainFrame.write_select_script_count();
				MainFrame.write_script_account();
				
			}
		}
	}
}

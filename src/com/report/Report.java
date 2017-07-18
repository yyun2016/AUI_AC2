package com.report;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import com.exec.Exec;
import com.frame.MainFrame;
import com.helper.JarHelper;
import com.helper.ServiceHelper;
import com.xml.LogBean;
import com.xml.LogXmlReader;

public class Report {
	public static void export(String path,JTable table){
		StringBuffer reportSB = new StringBuffer();
		reportSB.append(IConst.TESTRESULT_MONKEYHTML_HEAD);

		reportSB.append("<table>");
		reportSB.append("<tr>");
		reportSB.append("<td border='0' cellspacing='0' cellpadding='0' style='border:0;' width='100px'>");
		reportSB.append("<IMG style='cursor:default;width:99px;height:64px' src='./dependency/css/images/logo.jpg' />");
		reportSB.append("</td>");
		reportSB.append("<td border='0' cellspacing='0' cellpadding='0' style='border:0;'>");
		reportSB.append("<p align='center'><font size='12px'>Android UIAutomator Test Report</font></p>");
		reportSB.append("</td>");
		reportSB.append("</tr>");
		reportSB.append("</table>");

		reportSB.append("<table>");
		reportSB.append("<tr>");
		reportSB.append("<td  style='border:0;align:left;width:400px;'>");
		reportSB.append("<p class='time' align='left'><font size='3px'>SW Version:"+getVersion(table.getValueAt(0, 3).toString())+"</font></p>");
		reportSB.append("</td>");
		reportSB.append("</tr>");
		reportSB.append("<tr>");
		reportSB.append("<td border='0' cellspacing='0' cellpadding='0' style='border:0;' align='right'>");
		reportSB.append("<p align='left'>" +
				"<font>Total TestCase="+getTestTotal(table)+"</font>&nbsp;&nbsp;" +
				"<font color='green'>OK="+getTestPass(table)+"</font>&nbsp;&nbsp;" +
				"<font color='red'>Failed="+getTestFail(table)+"</font>&nbsp;&nbsp;" +
				"<font color='red'>Notrun="+getNotRun(table)+"</font>&nbsp;&nbsp;" +
						"</p>");
		reportSB.append("</td>");
		reportSB.append("</tr>");
		reportSB.append("</table>");
		
		for(int i=0;i<table.getRowCount();i++){
			String divID = table.getValueAt(i, 7).toString()+String.valueOf(System.currentTimeMillis());
			String sumID = "TITLE"+String.valueOf(System.currentTimeMillis())+table.getValueAt(i, 7).toString();
			reportSB.append("<TABLE>");
			reportSB.append("<TR>\n");
			reportSB.append("<TH>method</TH>\n");
			reportSB.append("<TH>Argument</TH>\n");
			reportSB.append("<TH>Result</TH>");
			reportSB.append("<TH>device</TH>");
			reportSB.append("<TH>Title</TH>");
			reportSB.append("</TR>");
			

			reportSB.append(""+"<TR>");

			reportSB.append("<TD title = \""+
					table.getValueAt(i, 2).toString()
					+"\">"
					+"<a href=\"javascript:void(null)\"  onclick=\"javascript:change("
					+"'"+divID+"','AWY_213')\">"+
					ServiceHelper.trimPackage(table.getValueAt(i, 2).toString())+
			"</TD>");
			String argument = "<TD>"+table.getValueAt(i, 5).toString()+"-e RANDOM "+table.getValueAt(i, 7).toString()+"</TD>\n";
			reportSB.append(argument.replaceAll(" -e", "<BR>-e"));
			
			if (table.getValueAt(i, 6).toString().equals("PASS")) {
				reportSB.append("<TD title= \"PASS\"><IMG  src=./dependency/css/images/passed.gif></TD>\n");
			}else if (table.getValueAt(i, 6).toString().equals("FAIL")) {
				reportSB.append("<TD title= \"FAIL\"><IMG  src=./dependency/css/images/failed.gif></TD>\n");
			}else if (table.getValueAt(i, 6).toString().equals("Terminated")) {
				reportSB.append("<TD title= \"FAIL\"><IMG  src=./dependency/css/images/failed.gif></TD>\n");
			}
			else {
				reportSB.append("<TD title= \"NOTRUN\"><IMG  src=./dependency/css/images/failed.gif></TD>\n");
			}
			reportSB.append("<TD>"+table.getValueAt(i, 4).toString()+"</TD>");
			reportSB.append("<TD title = \""+
					"click to see case summay"
					+"\">"
					+"<a href=\"javascript:void(null)\"  onclick=\"javascript:change("
					+"'"+sumID+"','AWY_213')\">"+
					table.getValueAt(i, 3).toString()+
			"</TD>");
			
			reportSB.append("</TR>" +"</TABLE>");
			reportSB.append("\n");
			
			
			String random = MainFrame.scriptTable.getValueAt(i, 7).toString();
			String xml = JarHelper.getProjectPath()+"log\\log.xml";
			Vector<LogBean> A = null;
			LogXmlReader my = new LogXmlReader();
			A = my.toRead(xml);
			LogBean logBean = new LogBean();
			for (LogBean bean : A) {
				if (bean.getRandomid().equals(random)) {
					logBean = bean;
					break;
				}
			}
			
			reportSB.append("<div id="+divID+" class=\"alcatel\">");
			reportSB.append("<TABLE>\n");
			reportSB.append("<TR><TH>");
			reportSB.append(logBean.getLogAugument().replace("|BR|", "<BR>"));
			reportSB.append("</TH></TR>");
			reportSB.append("</TABLE>\n");
			reportSB.append("</div>\n");
			
			reportSB.append("<div id="+sumID+" class=\"alcatel\">");
			reportSB.append("<TABLE>\n");
			reportSB.append("<TR><TH>");
			reportSB.append(table.getValueAt(i, 3).toString().replaceAll("\\,", "<BR>").replaceAll("\\��", "<BR>"));
			reportSB.append("</TH></TR>");
			reportSB.append("</TABLE>\n");
			reportSB.append("</div>\n");
		}
		reportSB.append("<div><p>" +
				"<IMG style='cursor:default;width:900px;height:50px' " +
		"src='./dependency/css/images/Dots.jpg' /></p></div>");

		reportSB.append("</BODY></HTML>");
		ServiceHelper.writeStringToFile(path, reportSB.toString(), "UTF-8");
		ServiceHelper.openResult(path);
		JOptionPane.showMessageDialog(new JFrame(), "SUCCESS!","提示", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static int getTestTotal(JTable table){
		return table.getRowCount();
	}
	
	public static int getTestPass(JTable table){
		int pass=0;
		for(int i=0;i<table.getRowCount();i++){
			if(table.getValueAt(i, 6).equals("PASS")){
				pass++;
			}
		}
		return pass;
	}
	
	public static int getTestFail(JTable table){
		int fail=0;
		for(int i=0;i<table.getRowCount();i++){
			if(table.getValueAt(i, 6).equals("FAIL")||table.getValueAt(i, 6).equals("Terminated")){
				fail++;
			}
		}
		return fail;
	}
	
	public static int getNotRun(JTable table){
		int notrun=0;
		for(int i=0;i<table.getRowCount();i++){
			if(table.getValueAt(i, 6).equals("NOTRUN")){
				notrun++;;
			}
		}
		return notrun;
	}
	
	public static String getVersion(String device){
		List<String> com = new ArrayList<String>();
		com.add("adb");
		com.add("-s");
		com.add(device);
		com.add("shell");
		com.add("getprop");
		com.add("ro.mediatek.version.release");
		Hashtable<String,Object> result= Exec.exec(com, false);
		return result.get("msg").toString();
		
	}
}

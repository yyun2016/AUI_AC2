package com.junit;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import com.helper.ServiceHelper;
import com.xml.LogBean;

public class JunitReader {

	public void writeFailInfo(String retstr,Failure failure){
		retstr = retstr.replace("|BR|", "\n");
		if (retstr.contains("FAILURES!!!")||ServiceHelper.isFail(retstr)||retstr.contains("发生异常，正在捕获异异常")) {
			//failure.setType("com.android.uiautomator.core.UiObjectNotFoundException");
			//failure.setMessage("UiSelector[CLASS=android.widget.EditText, INDEX=1]");
			failure.setType("");
			failure.setMessage("");
			
			String[] str = retstr.split("INSTRUMENTATION_STATUS: id=UiAutomatorTestRunner");
			String ats = str[1];
			String[] at = ats.split("\n");
			StringBuffer stringBuffer = new StringBuffer();
			for (String line : at) {
				if (line.contains("at")) {
					stringBuffer.append(line+"\n");
				}
			}
			//failure.setFailInfo("\n"+stringBuffer.toString()+"\n"+"FAILURES!!!");
			//failure.setFailInfo(retstr.replaceAll("&lt;", "<").replaceAll("&gt;", ">"));
			failure.setFailInfo(retstr);
		}
	}
	 /**
	  * get the local hostname
	  * 
	  * @return the name of the local host, or "localhost" if we cannot work it out
	  */
	 private String getHostname() {
		 try {
			 return InetAddress.getLocalHost().getHostName();
		 } catch (UnknownHostException e) {
			 return "localhost";
		 }
	 }
	public Vector<LogBean> toRead(){
		Vector<LogBean> beans = new Vector<>();
		try {
			Database db = new DatabaseBuilder(new File("Test.mdb")).open();
			Table logtable = db.getTable("log");
			for (Row row : logtable) {
				LogBean logBean = new LogBean();
				String isDisplay = row.get("isDisplay").toString();
				if ("true".equals(isDisplay)){
					String result= row.get("result").toString();
					if ("PASS".equals(result)||"FAIL".equals(result)){
						String logAugument = row.get("LogArgument").toString();
						String randomid= row.get("id_testcase").toString();
						String caseName= row.get("caseName").toString();
						String summary= row.get("summary").toString();
						String suitName= row.get("suitName").toString();
						String TimeEnd= row.get("TimeEnd").toString();
						String TimeStamp= row.get("TimeStart").toString();
						String classname= row.get("classname").toString();
						logBean.setCaseName(caseName);
						logBean.setClassname(classname);
						logBean.setLogAugument(logAugument);
						logBean.setRandomid(randomid);
						logBean.setSuitName(suitName);
						logBean.setSummary(summary);
						logBean.setTimeEnd(TimeEnd);
						logBean.setTimeStamp(TimeStamp);
						beans.add(logBean);
					}
				}
			}
			db.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return beans;
	}
	public ArrayList<Testsuite> toReadTestsuites() throws ParseException{
		ArrayList<Testsuite> testsuites = new ArrayList<Testsuite>();
		Vector<LogBean> beans = toRead();
		Set<String> testsuitSet = new HashSet<String>();
		for (LogBean logBean : beans) {
			String suitName = logBean.getSummary()+"_"+logBean.getSuitName();
			if (!"null".equals(logBean.getTimeEnd())) {
				testsuitSet.add(suitName);
			}
		}
		int id=0;
		for (String testsuitName : testsuitSet) {
			Testsuite testsuite = new Testsuite();
			testsuite.setId(id+"");
			id = id + 1;
			
			int errors= 0;
			int fail=0;
			String hostname=getHostname();
			String name="";
			String t_package=System.currentTimeMillis()+"";
			int tests=0;
			double time=0;
			String timestamp="";
			
			ArrayList<Testcase> testcases = new ArrayList<Testcase>();
			for (LogBean logBean : beans) {
				if (!"null".equals(logBean.getTimeEnd())) {
					String sm = logBean.getSummary();
					String sn = logBean.getSuitName();
					String ts = String.format("%s_%s", sm,sn);
					if (ts.equals(testsuitName)) {
						tests = tests+1;
						name = testsuitName;
						//name = testsuitName.split("_")[0];
						
						testsuite.setName(name);
						testsuite.setTests(tests+"");
						
						testsuite.setErrors(errors+"");
						testsuite.setHostname(hostname);
						testsuite.setT_package(t_package);
						
						Testcase testcase = new Testcase();
						testcase.setClassname(logBean.getClassname());
						testcase.setName(logBean.getCaseName());
						
						SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd HH:mm:ss");
				        String str1=logBean.getTimeStamp();
				        String str2=logBean.getTimeEnd();
				        Date dt1=sdf.parse(str1);
				        Date dt2=sdf.parse(str2);
				        Calendar d1Now = Calendar.getInstance();
				        d1Now.setTime(dt1);
				        long d1s = d1Now.getTimeInMillis();
				        Calendar d2Now = Calendar.getInstance();
				        d2Now.setTime(dt2);
				        long d2s = d2Now.getTimeInMillis();
				        
				        double durTime = (d2s-d1s)/1000;
						testcase.setTime(durTime+"");
						
						time = time+durTime;
						testsuite.setTime(time+"");
						
						
						timestamp = logBean.getTimeStamp();
						testsuite.setTimestamp(timestamp);
						
						Failure failure = new Failure();
						String retstr = logBean.getLogAugument();
						if (retstr.contains("FAILURES!!!")||ServiceHelper.isFail(retstr)||retstr.contains("发生异常，正在捕获异常")) {
							fail = fail+1;
							testsuite.setFailures(fail+"");
						}
						writeFailInfo(retstr, failure);
						
						testcase.setFailureInfo(failure);
						testsuite.setFailures(fail+"");
						testcases.add(testcase);
						testcase.toString();
					}
				}
			}
			testsuite.setTestcases(testcases);
			testsuites.add(testsuite);
		}
		return testsuites;
	}
	public static void main(String[] args) throws ParseException {
		
	}
} 
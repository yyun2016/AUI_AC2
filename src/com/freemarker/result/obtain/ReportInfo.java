package com.freemarker.result.obtain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.freemarker.result.bean.CaseBean;
import com.freemarker.result.bean.ListBean;

public class ReportInfo {
	
	public static void main(String[] args) throws Exception{
		String path1 = "E:\\ATT\\mobile_log\\13995371050510_HIKE 828";
		String path2= "E:\\ATT\\mobile_log\\13995471050511_HIKE 838";
		String path3= "E:\\ATT\\mobile_log\\13995471050511_HIKE 848";
//		String path4= "E:\\ATT\\mobile_log\\13995471050511_HIKE 858";
//		String path5= "E:\\ATT\\mobile_log\\13995471050511_HIKE 868";
		List<String> lists = new ArrayList<String>();
		lists.add(path1);
		lists.add(path2);
		lists.add(path3);
//		lists.add(path4);
//		lists.add(path5);
		
		System.out.println(listSummary(lists));
		
		
	}
	public static Set<String> deviceInfo(List<String> lists) throws Exception{
		Set<String> infoSet = new HashSet<String>();

		for(int dev = 0; dev < lists.size(); dev++){
			File[] files = getFileList(lists.get(dev));
			for(int i = 0; i < files.length; i++){
				
				BufferedReader br = null;
				try {
					br = new BufferedReader(new InputStreamReader(new FileInputStream(files[i]), Charset.forName("UTF-8")));
					String logpath = files[i].toString();
					String line;
					if(logName(logpath).equals("devinfo")&&files[i].exists()){
						while ((line = br.readLine()) != null) {
							if (line.trim().equals("")) {
								
							} else {
								if(line.substring(0, 8).equals("hardware")){
									infoSet.add(line);	
								}
								if(line.substring(0, 8).equals("SoftWare")){
									infoSet.add(line);
								}
							}
						}
					br.close();
					}
				} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}

			}
		}
		return infoSet;
	}
	
	public static Map<String, List<List<String>>> report(List<String> lists) throws Exception{
		TreeMap<String, List<List<String>>> resultMap = new TreeMap<String, List<List<String>>>();
		//[001短信, 002通话, 003设置, 004图库, 004短信, 005短信, 005设置]
		for(String key:listSummary(lists)){
			System.out.println("循环遍历key:"+key);
			List<List<String>> lineList = new ArrayList<List<String>>();
			
			for(int dev = 0; dev < lists.size();dev++){
				//存放[status, log]
				List<String> caseInfoList = new ArrayList<String>();
				File[] files = getFileList(lists.get(dev));
				boolean status = false;
				String caseStatus = "Not Run";
				String pathV = "";
				
				for(int i = 0; i < files.length; i++){
					if (files[i].exists()) {
						BufferedReader br = null;
						try {
							br = new BufferedReader(new InputStreamReader(new FileInputStream(files[i]), Charset.forName("UTF-8")));
							String line;
							//log路径为path
							String path = files[i].toString();
							
							while ((line = br.readLine()) != null) {
								if (line.trim().equals("")) {
									
								} else {
									if (line.length()>9&&line.substring(0, 8).toString().equals("SUMMARY:")) {
										if((logName(path)+line.substring(8, line.length())).equals(key)){
											System.out.println("判断条件"+(logName(path)+line.substring(8, line.length())));
											status = true;
											String line1;
											while((line1 = br.readLine()) != null){
												if(line1.trim().equals("OK (1 test)")){
													caseStatus = "Pass";
													pathV = path;
												}
												if(line1.trim().equals("FAILURES!!!")){	
													caseStatus = "Fail";
													pathV = path;
												}
												if(line1.length()>6&&line.substring(0,5).equals("Time:")){
//													cb.setTime(Float.parseFloat(line.substring(5, line.length()).trim()));
												}
											}
										}
									}
								}
							}
							br.close();
							
							
							
//							System.out.println(caseInfoList);

						}catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}
				if (status) {
					caseInfoList.add(caseStatus);
					caseInfoList.add(pathV);
					lineList.add(caseInfoList);
//					break;
				}else{
					caseInfoList.add(caseStatus);
					caseInfoList.add(pathV);
					lineList.add(caseInfoList);
//					break;
				}
				
			}
			
			System.out.println("@#"+lineList);
			resultMap.put(key, lineList);
		}
		System.out.println(resultMap);
		return resultMap;
	}
	
	public static String logName(String logpath){
    	String name = "";
    	name = logpath.substring(logpath.lastIndexOf(System.getProperty("file.separator"))+1, logpath.length()-4);
    	return name;
    }
	
	//遍历所有summary
	public static Set<String> listSummary(List<String> lists) throws Exception{
		Set<String> setSum = new TreeSet<String>();
		for(int dev = 0; dev < lists.size();dev++){
			File[] files = getFileList(lists.get(dev));
			for(int i = 0; i < files.length; i++){
				if (files[i].exists()) {
					BufferedReader br = null;
					try {
						br = new BufferedReader(new InputStreamReader(new FileInputStream(files[i]), Charset.forName("UTF-8")));
						String line;
						//log路径为path
						String path = files[i].toString();
						while ((line = br.readLine()) != null) {
							if (line.trim().equals("")) {
								
							} else {
								if (line.length()>9&&line.substring(0, 8).toString().equals("SUMMARY:")) {
									setSum.add(logName(path) + line.substring(8, line.length()));
								}
							}
						}
					}catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		}
		return setSum;
	}
	
	public static ListBean resultInfo(String path) throws Exception{
		int totalpass = 0;
		int totalfail = 0;
		long totaltime = 0;
		File[] files = getFileList(path);
		
		ArrayList<CaseBean> cases = new ArrayList<CaseBean>();
		ListBean lb = new ListBean();
		
		for(int i = 0; i < files.length; i++){
			CaseBean cb =setCaseList(files[i]);
			cases.add(cb);
			if (cb.getStatus().equals("Pass")) {
				totalpass++;
			}
			if (cb.getStatus().equals("Fail")) {
				totalfail++;
			}
			if (!(cb.getTime()==0)) {
				totaltime += cb.getTime();
			}
		}
		lb.setTotalfail(totalfail);
		lb.setTotalpass(totalpass);
		lb.setTotaltime(totaltime);
		lb.setTotal(totalfail+totalpass);
		lb.setCaseList(cases);
		lb.setDevice(path.substring(path.lastIndexOf(System.getProperty("file.separator"))+1,path.length()));
		return lb;
	}
	/**
	 * 
	 * @param dir 文件目录
	 * @return 返回dir下所有文件夹
	 * @throws Exception
	 */
	public static File[] getFileList(String path) throws Exception{
		File directory = new File(path);
		File[] fs = directory.listFiles();  
		for(int i=0; i<fs.length; i++){
			fs[i].getAbsolutePath();
		}
		return fs;
	}

	public static CaseBean setCaseList(File file) {
//		int countPass = 0;
//		int countFail = 0;
//		int countTotal = 0;
		CaseBean cb = new CaseBean();
		if (file.exists()) {
			BufferedReader br = null;
			try {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charset.forName("UTF-8")));
				String path = file.toString();
				cb.setLog(path);
				String line;
				while ((line = br.readLine()) != null) {
					if (line.trim().equals("")) {
						
					} else {
						if (line.length()>9&&line.substring(0, 8).toString().equals("SUMMARY:")) {
							cb.setSummary(line.substring(8, line.length()));
						}
						if(line.trim().equals("OK (1 test)")){
//							countPass += 1;
							cb.setStatus("Pass");
						}
						if(line.trim().equals("FAILURES!!!")){	
//							countFail += 1;
							cb.setStatus("Fail");
						}
						if(line.length()>6&&line.substring(0,5).equals("Time:")){
							cb.setTime(Float.parseFloat(line.substring(5, line.length()).trim()));
						}
					}
				}
				br.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cb;
	}
}

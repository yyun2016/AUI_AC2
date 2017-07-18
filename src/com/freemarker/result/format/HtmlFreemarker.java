package com.freemarker.result.format;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.freemarker.result.format.HtmlFreemarker;

import com.freemarker.result.bean.CaseBean;
import com.freemarker.result.bean.ListBean;
import com.freemarker.result.obtain.ReportInfo;
import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class HtmlFreemarker {
	private Configuration cfg ;  
    private Template template;  
    private StringWriter stringWriter;   
    private PrintWriter printWriter;
    
    private String templatedir = null;
    private String templatefile = null;
    public SimpleHash root = new SimpleHash(); // 将会使用默认的包装器
    
    public HtmlFreemarker(String dir, String file) {
    	this.templatedir = dir;
    	this.templatefile = file;
    	try {
			init();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
   
    /** 
     * 初始化方法 
     * @throws IOException 
     */  
    public void init() throws IOException{  
         // 获取freemarker的Configuration实例  
        cfg = new Configuration();  
        cfg.setDefaultEncoding("UTF-8");
         //设置模板文件目录  
        cfg.setDirectoryForTemplateLoading(new File(templatedir));  
         // 取得模板文件  
        template = cfg.getTemplate(templatefile);  
    }  
    /** 
     * 开始处理 
     * @throws TemplateException 
     * @throws IOException 
     */  
    public void make(String outputfile) throws TemplateException, IOException{ 
    	File dir = new File("report");
    	if( (!dir.exists()) || (!dir.isDirectory()) ) {
    		dir.mkdir();
    	}
        //实例化一个文件输出流  
//    	printWriter = new PrintWriter("report/" + outputfile, "UTF-8");   
    	printWriter = new PrintWriter(outputfile, "UTF-8");   
        //合并模板和数据模型，并输出到stringWriter中  
        template.process(root, printWriter); 
    } 
    
    public String getHtml() throws TemplateException, IOException {
    	//实例化一个文件输出流  
    	stringWriter = new StringWriter();  
        //合并模板和数据模型，并输出到stringWriter中  
        template.process(root, stringWriter);        
        //打印结果  
        System.out.println(stringWriter.toString());
        return stringWriter.toString();
    }
    
    public static void stableReport(List<String> lists, String fileName) throws Exception {
		HtmlFreemarker hf = new HtmlFreemarker("template", "stablerep.ftl");
		List<String> devInfo = new ArrayList<String>();
		String envNo = "";
		String envYes = "";
		
		Set<String> hashSet = ReportInfo.deviceInfo(lists);
		System.out.println("###########"+hashSet);
		if(hashSet.size()>2){
			for(String key:hashSet){
				devInfo.add(key);
			}
			envNo = "测试环境不一致(软件版本/硬件版本)";
		}else{
			for(String key:hashSet){
				devInfo.add(key);
			}
			envYes = "测试环境相同";
		}
		
		hf.root.put("hardware", devInfo.get(0));
		hf.root.put("software", devInfo.get(1));
		hf.root.put("envNo", envNo);
		hf.root.put("envYes", envYes);
        //放置参数  
		hf.root.put("total", 10);
		hf.root.put("pass", 7);
		hf.root.put("fail", 3);
		hf.root.put("totaltime", 3);
		
		List<ListBean> listBeans = new ArrayList<ListBean>();

		for(int i = 0; i < lists.size(); i++){
			listBeans.add(ReportInfo.resultInfo(lists.get(i)));
		}
		List<Integer> resultP = new ArrayList<Integer>();
		List<Integer> resultF = new ArrayList<Integer>();
		List<Integer> resultTotal = new ArrayList<Integer>();
		List<Float> resultTime = new ArrayList<Float>();
		List<List<CaseBean>> caseBeans = new ArrayList<List<CaseBean>>();
		for(int j = 0; j < listBeans.size(); j++){
			caseBeans.add(listBeans.get(j).getCaseList());
			resultP.add(listBeans.get(j).getTotalpass());
			resultF.add(listBeans.get(j).getTotalfail());
			resultTime.add(listBeans.get(j).getTotaltime());
			resultTotal.add(listBeans.get(j).getTotal());
		}
		
		hf.root.put("resultP", resultP);
		hf.root.put("resultF", resultF);
		hf.root.put("resultTotal", resultTotal);
		hf.root.put("resultTime", resultTime);
//		
		List<String> devices = new ArrayList<String>();
		for(int i = 0; i < listBeans.size(); i++){
			devices.add(listBeans.get(i).getDevice());
		}
		System.out.println("@lists"+lists);
		System.out.println("devices" + devices);
		hf.root.put("devices", devices);
//		
//
//		TreeMap<String, List<List<String>>> result = new TreeMap<String, List<List<String>>>();		
//		List<String> summaryList = new ArrayList<String>();
//		List<List<List<String>>> temp = new ArrayList<List<List<String>>>();
//		List<List<List<String>>> temp2 = new ArrayList<List<List<String>>>();
//		
//		for(int k = 0; k < listBeans.size(); k++){
//			List<List<String>> c1 = new ArrayList<List<String>>();
//			for(int n = 0; n <caseBeans.get(k).size(); n++){
//				List<String> statusList = new ArrayList<String>();
//				List<CaseBean> cbg = caseBeans.get(k);
//				if(k==0){
//					summaryList.add( logName(cbg.get(n).getLog()) + cbg.get(n).getSummary());
//				}
//				statusList.add(cbg.get(n).getStatus());
////				System.out.println(cbg.get(n).getStatus());
//				statusList.add(cbg.get(n).getLog());
//				c1.add(statusList);
//			}
//			temp.add(c1);
//		}
//
//		for(int caseNum = 0; caseNum < temp.get(0).size(); caseNum++){
//			List<List<String>> c2 = new ArrayList<List<String>>();
//			for(int devNo = 0; devNo < temp.size(); devNo++){
//				c2.add(temp.get(devNo).get(caseNum));
//			}
//			temp2.add(c2);
//		}
//		for(int n =0; n< temp2.size();n++){
//			result.put(summaryList.get(n), temp2.get(n));
//		}
//		
//		System.out.println(result);
//		for(String key:result.keySet()){
//			System.out.println("~@~: key =" + key +", value = " + result.get(key));
//		}
		Map<String, List<List<String>>> result = ReportInfo.report(lists);
		hf.root.put("result", result);
		
		
		TreeMap<String, List<List<Integer>>> countMap = new TreeMap<String, List<List<Integer>>>();		
	
		Set<String>  keyList=new HashSet<String>();
		int devList = 0;
		for(String key:result.keySet()){
			keyList.add(key.substring(3, key.length()));
			System.out.println("#keySet" + keyList);
			
//			countMap.put(keyName, value);
		}
		for(String key:result.keySet()){
			devList = result.get(key).size();
			break;
		}
		Iterator<String> iterator=keyList.iterator();
		List<String> mapList = new ArrayList<String>();
		while (iterator.hasNext()) {
			mapList.add(iterator.next());
		}
		System.out.println("MapList"+mapList);
		
		for(int a = 0; a < mapList.size(); a++){
			List<List<Integer>> pfList = new ArrayList<List<Integer>>();
			
			String caseName = mapList.get(a);
			for(int dev = 0; dev < devList; dev++){
				int passcount = 0;
				int failcount = 0;
				int notruncount = 0;
				for(String key:result.keySet()){				
					//指定key遍历
					if(key.substring(3, key.length()).equals(caseName)){
						String caseStatus = result.get(key).get(dev).get(0);
						if ("PASS".equals(caseStatus.toUpperCase())) {
							passcount++;
						}else if ("FAIL".equals(caseStatus.toUpperCase())) {
							failcount++;
						}else {
							notruncount++;
						}
					}
				}
				List<Integer> itemList = new ArrayList<Integer>();
				
				itemList.add(passcount);
				itemList.add(failcount);
				itemList.add(notruncount);
				
				pfList.add(itemList);
				System.out.println("pflist:"+pfList);
			}
			countMap.put(caseName, pfList);
			
		}
		System.out.println("countMap:"+countMap);
		hf.root.put("countMap", countMap);
		try {  
            hf.make(fileName);
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (TemplateException e) {  
            e.printStackTrace();  
        }
    }
    public static String logName(String logpath){
    	String name = "";
    	name = logpath.substring(logpath.lastIndexOf(System.getProperty("file.separator"))+1, logpath.length()-4);
    	return name;
    }
    
    public static ListBean report() throws Exception{
    	//一台device的所有log
    	String path = "E:\\ATT\\mobile_log\\13995371050510_HIKE 828";
//    	List<String> lists = new ArrayList<String>();
//    	lists.add(path);
		ListBean lb = ReportInfo.resultInfo(path);
		System.out.println(lb);
		return lb;
    }
    
    
	public static void main(String[] args) throws Exception {
		String path1 = "E:\\ATT\\mobile_log\\20140605_14_42_48_HIKe 828";
//		String path2= "E:\\ATT\\mobile_log\\13995471050511_HIKE 838";
//		String path3= "E:\\ATT\\mobile_log\\13995471050511_HIKE 848";
//		String path4= "E:\\ATT\\mobile_log\\13995471050511_HIKE 858";
//		String path5= "E:\\ATT\\mobile_log\\13995471050511_HIKE 868";
		List<String> lists = new ArrayList<String>();
		lists.add(path1);
//		lists.add(path2);
//		lists.add(path3);
//		lists.add(path4);
//		lists.add(path5);
		stableReport(lists,"stable_report.html");
//		report();
	}
}

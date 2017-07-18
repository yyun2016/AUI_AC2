package com.freemarker.result.obtain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FilesOp {
	public static String filepath = "E:\\ATT\\mobile_log";
	public static void getSummary(){
		
	}
	public static void main(String[] args) throws Exception {
		File root = new File(filepath);  
		File[] fs = showAllDirectory(root);
		for(int i= 0; i < fs.length; i++){
			System.out.println(getSummary(fs[i])); 
		}
	}
	
	/**
	 * 
	 * @param dir 路径
	 * @return 获取dir下所有文件夹
	 * @throws Exception
	 */
	public static File[] showAllDirectory(File dir) throws Exception{
		File[] fs = dir.listFiles();  
		for(int i=0; i<fs.length; i++){
			System.out.println(fs[i].getAbsolutePath());
		}
		return fs;
	}
	
	/**
	 * 
	 * @param dir 目录路径
	 * @return 
	 * @throws Exception
	 */
	public static List<String> getSummary(File dir) throws Exception{
		List<String> fileList = new ArrayList<String>();
		if(dir.isDirectory()){
			 File[] fis = dir.listFiles();
			 for(int j = 0; j<fis.length; j++){
				 if(fis[j].isFile()&&fis[j].exists()){
					 BufferedReader in = new BufferedReader(new FileReader(fis[j]));
					 fileList.add(in.readLine());
					 in.close();
				 }
			 }
		 }
		return fileList;
	}
	
}

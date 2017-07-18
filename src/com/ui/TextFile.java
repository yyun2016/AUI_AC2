package com.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.log.Log;

public class TextFile {
	/*
	 * 文件是否存在
	 */
	public static boolean fileExists(String filepath) {
		File f = new File(filepath);
		return f.exists();
	}
	/*
	 * 文件所在的目录是否存在
	 */
	public static boolean dirExists(String filepath) {
		File f = new File(filepath);
		return f.getParentFile().exists();
	}
	public static List<String> readWithNoN(String filepath) {
		BufferedReader in;
		List<String> lines = new ArrayList<String>();
		try {
			String s;
			InputStreamReader read = new InputStreamReader (new FileInputStream(filepath),"GB2312");
			in=new BufferedReader(read);
			//in = new BufferedReader( new FileReader(filepath));
			while((s = in.readLine())!=null) {
				if (!s.trim().equals("")) {
					lines.add(s);
				}
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
	public static String getDuration(String filepath){
		String durString = "0";
		List<String> lineList = read(filepath);
		for (String line : lineList) {
			if (line.startsWith("Duration=")) {
				durString = line.replaceAll("Duration=", "");
				break;
			}
		}
		return durString;
	}
	/*
	 * 读取文件内容，返回字符串
	 */
	public static List<String> read(String filepath) {
		BufferedReader in;
		List<String> lines = new ArrayList<String>();
		try {
			String s;
			in = new BufferedReader(new InputStreamReader(new FileInputStream(filepath),"GBK"));
			while((s = in.readLine())!=null) {
				if (s.replaceAll("\n", "").length()>=1){
					//Log.info("Import:"+s);
					lines.add(s);
				}
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
	/*
	 * 写内容到文件
	 */
	public static void write(String filepath, List<String> lines) {
		File file = new File(filepath);
		File dir = file.getParentFile();
		try {
			if(!dir.exists()) {
				dir.mkdirs();
			}
			if(!file.exists()) {
				file.createNewFile();
			} else {
				file.delete();
				file.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		PrintWriter out;
		try {
			out = new PrintWriter(filepath);
			for(String line: lines) {
				out.println(line);
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	/*
	 * 写内容到文件，如果没有此文件则创建后再写入
	 */
	public static void writeWhenever(String filepath, List<String> lines) {
		File file = new File(filepath);
		File dir = file.getParentFile();
		try {
			if(!dir.exists()) {
				dir.mkdirs();
			}
			if(!file.exists()) {
				file.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		write(filepath, lines);
	}

	public static void writeWhenever(String filepath, String content) {
		String[] lines = content.split("\n");
		List<String> list = Arrays.asList(lines);
		writeWhenever(filepath, list);
	}

	public static void main(String[] args) {
		List<String> cs = read("F:\\ATT\\testsuit\\Browser.txt");
		for (String s : cs) {
			System.out.println(s);
		}
	}
}

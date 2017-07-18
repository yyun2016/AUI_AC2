package com.cover;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;

public class tete
{
	public static void main(String[] args) throws IOException, InterruptedException
	{
		String testStr = "12315<Text>show me1</Text>\n " +
				"12315<Text>show me2</Text>  sfsadfasdfas 12315<Text>show me10086</Text>\n" +
				"12315<Text>show me3</Text>\n" +
				"12315<Text>show me4</Text>\n" +
				"12315<Text>show me5</Text>\n" +
				"12315<Text>show me6</Text>\n" +
				"12315<Text>show me7</Text>\n" +
				"12315<Text>show me8</Text>\n";
		Pattern p = Pattern.compile("<Text>(.*)</Text>");
		Matcher m = p.matcher(testStr);
		while(m.find()){
			System.out.println(m.group(1));
		}
		
		String t = "root      2552  2550  460072 22668 ffffffff 40127af4 S uiautomator";
		Pattern pt = Pattern.compile("root(.*) ");
		Matcher mt = pt.matcher(t);
		while(mt.find()){
			System.out.println(mt.group(1));
		}
		
		System.out.println(System.getenv("ANT_HOME"));

		/*Process process = Runtime.getRuntime().exec("cmd.exe /c start "+"F:\\ATT\\report\\1414746667992_stable_report.html");
		process.waitFor();*/

		// Runtime.getRuntime().exec("cmd.exe   /c   start "+"F:\\ATT\\report\\1414746667992_stable_report.html");
	
	
	}
}
package com.helper;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class EnvHelper {

	public static String getEnv(String key){
		String value = "";
		Map<String, String> map = System.getenv();
		Iterator<Entry<String, String>> it = map.entrySet().iterator();
		while(it.hasNext())
		{
			Entry<?, ?> entry = (Entry<?, ?>)it.next();
			if (key.equals(entry.getKey().toString())) {
				value = entry.getValue().toString();
			}
		}
		return value;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getEnv("ANT_HOME"));
		//System.setProperty("ANT_HOME", "E:\\Program Files\\apache-ant-1.8.12");  
	}

}

package com.panel;

import java.io.File;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;

public class SettingDb {

	public static String readAntHome(){
		String Ant_Home_Path = "";
		try {
			Database db = new DatabaseBuilder(new File("Test.mdb")).open();
			Table table = db.getTable("setting");
			for (Row row: table) {
				Ant_Home_Path = row.get("ANT_HOME").toString();
			}
			db.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return Ant_Home_Path;
	}
	public static String readJarPath(){
		String jar_Path = "";
		try {
			Database db = new DatabaseBuilder(new File("Test.mdb")).open();
			Table table = db.getTable("setting");
			for (Row row: table) {
				jar_Path = row.get("uiautomatorjar").toString();
			}
			db.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return jar_Path;

	}
	public static void setAntPath(String value){
		try {
			Database db = new DatabaseBuilder(new File("Test.mdb")).open();
			Table table = db.getTable("setting");
			for (Row row: table) {
				row.put("ANT_HOME", value);
				table.updateRow(row);
			}
			db.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void setJarPath(String value){
		try {
			Database db = new DatabaseBuilder(new File("Test.mdb")).open();
			Table table = db.getTable("setting");
			for (Row row: table) {
				row.put("uiautomatorjar", value);
				table.updateRow(row);
			}
			db.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

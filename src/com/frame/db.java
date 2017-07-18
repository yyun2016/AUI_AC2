package com.frame;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;

public class db {
	
	public static void down(String randomid){
		Database db;
		Table table;
		try {
			db = new DatabaseBuilder(new File("Test.mdb")).open();
			table = db.getTable("config");

			ArrayList<Object[]> rows = new ArrayList<>();
			//14159458513784
			for (Row row : table) {
				Object[] rowl = new Object[table.getColumnCount()];
				rowl[0] = row.get("isSelect").toString();
				rowl[1] =row.get("script").toString();
				rowl[2] =row.get("summary").toString();
				rowl[3] =row.get("device").toString();
				rowl[4] =row.get("randomid").toString();
				rowl[5] =row.get("es").toString();
				rowl[6] =row.get("suitName").toString();
				rows.add(rowl);
			}

			db.close();
			int current = 0 ;
			for (int i = 0; i < rows.size(); i++) {
				if (randomid.equals(rows.get(i)[4])) {
					current = i;
				}
			}
			if (current==rows.size()-1) {
				Object[] up = rows.get(current);
				Object[] down = rows.get(0);
				rows.set(current, down);
				rows.set(0, up);
			}else {
				Object[] up = rows.get(current);
				Object[] down = rows.get(current+1);

				rows.set(current, down);
				rows.set(current+1, up);
			}
			db = new DatabaseBuilder(new File("Test.mdb")).open();
			table = db.getTable("config");
			for (Row row : table) {
				table.deleteRow(row);
			}
			db.close();

			db = new DatabaseBuilder(new File("Test.mdb")).open();
			table = db.getTable("config");
			for (Object[] object : rows) {
				table.addRow(object);
			}
			db.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void up(String randomid){
		Database db;
		Table table;
		try {
			db = new DatabaseBuilder(new File("Test.mdb")).open();
			table = db.getTable("config");

			ArrayList<Object[]> rows = new ArrayList<>();
			//14159458513784
			for (Row row : table) {
				Object[] rowl = new Object[table.getColumnCount()];
				rowl[0] = row.get("isSelect").toString();
				rowl[1] =row.get("script").toString();
				rowl[2] =row.get("summary").toString();
				rowl[3] =row.get("device").toString();
				rowl[4] =row.get("randomid").toString();
				rowl[5] =row.get("es").toString();
				rowl[6] =row.get("suitName").toString();
				rows.add(rowl);
			}
			db.close();
			int current = 0 ;
			int cnt = rows.size();
			for (int i = 0; i < cnt; i++) {
				if (randomid.equals(rows.get(i)[4])) {
					current = i;
				}
			}
			if (current==0) {
				Object[] up = rows.get(0);
				Object[] down = rows.get(cnt-1);
				rows.set(cnt-1, up);
				rows.set(0, down);
			}else {
				Object[] down = rows.get(current);
				Object[] up = rows.get(current-1);

				rows.set(current-1, down);
				rows.set(current, up);
			}
			db = new DatabaseBuilder(new File("Test.mdb")).open();
			table = db.getTable("config");
			for (Row row : table) {
				table.deleteRow(row);
			}
			db.close();

			db = new DatabaseBuilder(new File("Test.mdb")).open();
			table = db.getTable("config");
			for (Object[] object : rows) {
				table.addRow(object);
			}
			db.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String args[]){
		/*try {
			Database db = new DatabaseBuilder(new File("Test.mdb")).open();

			Object[] objects = new Object[]{
					"id_testsuit",
					"isSelect",
					"id_testcase",
					"isDisplay",
					"suitName",
					"caseName",
					"summary",
					"TimeStart",
					"TimeEnd",
					"classname",
					"LogArgument",
					"es",
					"device",
					"result",
					"script",};
			TableBuilder tb = new TableBuilder("log");
			for(Object key : objects) {
				ColumnBuilder cBuilder = new ColumnBuilder(key.toString());
				cBuilder.setSQLType(Types.CHAR);
				tb.addColumn(cBuilder.toColumn());
			}
			tb.toTable(db);

			db.close();

		} catch (Exception e) {
			// TODO: handle exception
		}*/

		/*try {
			Database db = new DatabaseBuilder(new File("Test.mdb")).open();
			Table table = db.getTable("log");
			Object[] t = new Object[15];
			for (int i = 0; i < t.length; i++) {
				t[i] = i;
			}
			table.addRow(t);
		} catch (Exception e) {
			// TODO: handle exception
		}*/

		/*try {
			Database db = new DatabaseBuilder(new File("Test.mdb")).open();
			Table logtable = db.getTable("log");
			Row row;
			while ((row = logtable.getNextRow())!=null) {
				logtable.deleteRow(row);
			}
			db.close();
		} catch (Exception e) {
			// TODO: handle exception
		}*/


		Map<String, String> tMap = new Hashtable<>();
		tMap.put("14162067393321", "sadfasd");
		tMap.put("14162067393323", "tewtwe");
		tMap.put("14162067393322", "ggffgf");

		System.out.println(tMap.keySet());
	}
}

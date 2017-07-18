package com.db;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.healthmarketscience.jackcess.ColumnBuilder;
import com.healthmarketscience.jackcess.Cursor;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import com.healthmarketscience.jackcess.TableBuilder;
import com.model.RowBean;
/**
 * 导入 jackcess-1.2.8.jar //不过需要 commons-lang2.0 的支持
 * @ClassName: AccessJdbcDemo 
 */
public class AccessJdbc {
	public static String dbpath="C:\\Test.mdb";
	public static Database db;
	@SuppressWarnings("static-access")
	public static void createDataBase(String dataName) throws IOException{
		dbpath = dataName;
		DatabaseBuilder dBuilder = new DatabaseBuilder(new File(dbpath));
		dBuilder.create();
	}
	public void createTable(String tableName,Object[] objects) throws IOException, SQLException{
		Database db = new DatabaseBuilder(new File(dbpath)).open();
		TableBuilder tb = new TableBuilder(tableName);
		for(Object key : objects) {
			ColumnBuilder cBuilder = new ColumnBuilder(key.toString());
			cBuilder.setSQLType(Types.CHAR);
			tb.addColumn(cBuilder.toColumn());
		}
		tb.toTable(db);

	}
	public void insertTable(String tableName,Object[] data) throws IOException{
		Database db = new DatabaseBuilder(new File(dbpath)).open();
		Table table = db.getTable(tableName);
		table.addRow(data);
		db.close();
	}
	public void queryTable(String tableName) throws IOException{
		Database qdb = new DatabaseBuilder(new File(dbpath)).open();
		Table table = qdb.getTable(tableName);

		for(Row row : table) {  
			System.out.println(row);  
		} 
		qdb.close();
		
		/*//Map<String, String> m = Collections.singletonMap("name", "1s");
		Map<String, String> m = new HashMap<String, String>();
		//m.put("sex", "bs");
		m.put("name", "1s");
		Map<String, Object> row = Cursor.findRow(table, m);  
		if(row != null) {  
			System.err.println(row.toString());
			System.out.println("Found row where"+m+": " + row);  
		} else {  
			System.out.println("not Found row where"+m+": " + row); 
		} */
	}
	//复制JDBC ResultSet（例如，从外部数据库）的内容到一个新的表：
	//Database.open(new File("my.mdb")).copyTable("Imported", resultSet); 
	//复制CSV文件的内容到一个新的表：
	public static void importCVS() throws IOException{
		//Database.open(new File("my.mdb")).importFile("Imported2", new File("my.csv"), ","); 
	}
	public void updateTable(String tableName,String randomid,Map<String, String> map) throws IOException{
		Database qdb = new DatabaseBuilder(new File(dbpath)).open();
		Table table = qdb.getTable(tableName);
		
	}
	public static void main(String[] args) throws IOException, SQLException {
		AccessJdbc access = new AccessJdbc();
		//AccessJdbc.createDataBase("C:\\Test.mdb");

		Object[] objects = new Object[3];
		objects[0]="name";
		objects[1]="sex";
		objects[2]="height";
		
		//access.createTable("config", new Object[]{"isSelect","script","summary","device","randomid","es","suitName"});
		
		access.insertTable("config",new Object[]{"insert","true","true","true","14152405494970","true","true"});
		
		Map<String, Object> mp =new HashMap<String, Object>();
		mp.put("isSelect", "insert");
		mp.put("script", "true");
		mp.put("summary", "true");
		mp.put("device", "true");
		mp.put("randomid", "14152405494970");
		mp.put("es", "true");
		mp.put("suitName", "true");
		
		Database qdb = new DatabaseBuilder(new File(dbpath)).open();
		Table table = qdb.getTable("config");
		Map<String, String> map = new HashMap<String, String>();
		map.put("randomid", "mp");
		//table.deleteRow((Row)mp);
		Row row ; 
		while ((row = table.getNextRow())!=null) {
			
			if ("14152405494970".equals(row.get("randomid"))) {
				row.put("randomid", "99999999");
				table.updateRow(row);
				//table.deleteRow(row);
			}
		}
		
		access.queryTable("config");
	}
}
package com.model;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

import com.frame.MainFrame;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;

public class tableModel extends AbstractTableModel {

	private static final long serialVersionUID = -7495940408592595397L;

	@SuppressWarnings("rawtypes")
	public Vector content = null;

	//private String[] title_name = { "id", "target","class","case","selected"};
	private String[] title_name = {
			"id",
			"选择",
			"脚本",
			"描述",
			"命令",
			//"参数",
			//"参数",
			//"参数",
			"随机数",
			"设备",
			"集合"
	};

	@SuppressWarnings("rawtypes")
	public tableModel() {
		content = new Vector();
	}

	public tableModel(int count) {
		content = new Vector(count);
	}
	public void deleteAllInDb(){
		try {
			Database qdb = new DatabaseBuilder(new File("Test.mdb")).open();
			Table table = qdb.getTable("config");

			for (Row row : table) {
				if ("true".equals(row.get("isSelect"))) {
					table.deleteRow(row);
				}
			}
			qdb.close();
		} catch (Exception e2) {
			// TODO: handle exception
		}
	}
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
	public void parseToDb(){
		int size = content.size();
		deleteAllInDb();
		try {
			Database db = new DatabaseBuilder(new File("Test.mdb")).open();
			Table table = db.getTable("config");
			for (int i = 0; i < size; i++) {
				Vector v = (Vector) content.get(i);
				try {
					Object[] row = new Object[table.getColumnCount()];
					row[0] =v.get(1);
					row[1] =v.get(2);
					row[2] =v.get(3);
					row[3] =v.get(6);
					row[4] =v.get(5);
					row[5] =v.get(4);
					row[6] =v.get(7);
					table.addRow(row);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			db.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void construct(){
		try {
			MainFrame.setTitle("start construct config ui");
			content.removeAllElements();
			MainFrame.spTable.updateUI();
			
			Database qdb = new DatabaseBuilder(new File("Test.mdb")).open();
			Table table = qdb.getTable("config");

			//Vector vectors = new Vector();
			int i = 0;
			for(Row row : table) {
				Vector v = new Vector(8);
				v.add(0, i);
				v.add(1, new Boolean(row.get("isSelect").toString()));
				v.add(2, row.get("script").toString());
				v.add(3, row.get("summary").toString());
				v.add(4, row.get("es").toString());
				v.add(5, row.get("randomid").toString());
				v.add(6, row.get("device").toString());
				v.add(7, row.get("suitName").toString());
				content.add(v);
				i = i +1;
			} 
			//content = vectors;
			qdb.close();
			MainFrame.spTable.updateUI();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void serialize(){
		int count = getRowCount();
		for (int i = 0; i < count; i++) {
			setValueAt(i, i, 0);
		}
	}

	public void updateRow(
			int row,
			boolean selected,
			String script,
			String summary,
			String es,
			//String e1,
			//String e2,
			//String e3,
			String randomid
			){
		setValueAt(selected, row, 1);
		setValueAt(script, row, 2);
		setValueAt(summary, row, 3);
		setValueAt(es, row, 4);
		//setValueAt(e1, row, 4);
		//setValueAt(e2, row, 5);
		//setValueAt(e3, row, 6);
	}
	public void addRow(
			boolean selected,
			String script,
			String summary,
			String es,
			//String e1,
			//String e2,
			//String e3,
			String randomid,
			String device,
			String suitName
			)
	{
		Vector v = new Vector(8);
		v.add(0, new Integer(content.size()));
		v.add(1, new Boolean(selected));
		v.add(2, script);
		v.add(3, summary);
		v.add(4, es);
		//v.add(4, e1);
		//v.add(5, e2);
		//v.add(6, e3);
		v.add(5, randomid);
		v.add(6, device);
		v.add(7, suitName);
		content.add(v);
		this.fireTableDataChanged();
	}
	public void Upmodify(int row) {
		if(row==0){
			Vector CurrentVenctor=(Vector)content.get(row);
			Vector UPVenctor=(Vector)content.get(content.size()-1);
			content.setElementAt((Object)UPVenctor, row);
			content.setElementAt((Object)CurrentVenctor, content.size()-1);
		}
		else{
			Vector CurrentVenctor=(Vector)content.get(row);
			Vector UPVenctor=(Vector)content.get(row-1);
			content.setElementAt((Object)UPVenctor, row);
			content.setElementAt((Object)CurrentVenctor, row-1);
		}

	}
	public void Downmodify(int row) {
		if(content.size()==row+1){
			Vector UPVenctor=(Vector)content.get(row);
			Vector DownVenctor=(Vector)content.get(0);
			content.setElementAt((Object)DownVenctor,row);
			content.setElementAt((Object)UPVenctor, 0);
		}
		else{
			Vector UPVenctor=(Vector)content.get(row);
			Vector DownVenctor=(Vector)content.get(row+1);
			content.setElementAt((Object)DownVenctor, row);
			content.setElementAt((Object)UPVenctor, row+1);
		}


	}
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex==1) {
			if (MainFrame.editSelect.isSelected()) {
				return true;
			}else {
				return false;
			}
		}
		else if (columnIndex==3) {
			if (MainFrame.editSummary.isSelected()) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	public void setValueAt(Object value, int row, int col) {
		((Vector) content.get(row)).remove(col);
		((Vector) content.get(row)).add(col, value);
		this.fireTableCellUpdated(row, col);
	}

	public String getColumnName(int col) {
		return title_name[col];
	}

	public int getColumnCount() {
		return title_name.length;
	}

	public int getRowCount() {
		return content.size();
	}

	public Object getValueAt(int row, int col) {
		return ((Vector) content.get(row)).get(col);
	}

	public Class getColumnClass(int col) {
		return getValueAt(0, col).getClass();
	}
	public ArrayList<Integer> getSelectRows(){
		ArrayList<Integer> sList = new ArrayList<Integer>();
		int count = getRowCount();
		for (int i = 0; i < count; i++) {
			boolean select = Boolean.parseBoolean(String.valueOf(getValueAt(i, 1)));
			if (select) {
				sList.add(i);
			}
		}
		return sList;
	}
	public Hashtable<String, Object> getSelectHash(){
		Hashtable<String, Object> isselecthashtable = new Hashtable<String,Object>();
		for (int i = 0; i < getRowCount(); i++) {
			Boolean selected = Boolean.parseBoolean(getValueAt(i, 1).toString());
			if (selected) {
				String random = getValueAt(i, 5).toString();
				Object  obj= content.get(i);
				isselecthashtable.put(random, obj);
			}
		}
		return isselecthashtable;
	}
}






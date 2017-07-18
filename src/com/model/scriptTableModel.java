package com.model;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.Map.Entry;

import javax.swing.table.AbstractTableModel;

import org.omg.CORBA.portable.ValueBase;

import com.frame.MainFrame;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import com.helper.JarHelper;
import com.helper.Statistics;
import com.log.Log;
import com.thread.Lock;
import com.xml.LogXmlUpdate;

public class scriptTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -7495940408592595397L;

	@SuppressWarnings("rawtypes")
	public static Vector content = null;

	//private String[] title_name = { "id", "target","class","case","selected"};
	private String[] title_name = {
			"id",
			"选择",
			"脚本",
			"描述",
			"设备",
			"参数",
			"结果",
			"随机数",
			"集合"
	};

	@SuppressWarnings("rawtypes")
	public scriptTableModel() {
		content = new Vector();
	}

	public scriptTableModel(int count) {
		content = new Vector(count);
	}
	public void update_select_all(String regex ,boolean bol){
		try {
			Database qdb = new DatabaseBuilder(new File("Test.mdb")).open();
			Table log_table = qdb.getTable("log");
			for(Row row : log_table) {
				String isSelect = row.get("isSelect").toString();
				if ("true".equals(isSelect)) {
					if(row.toString().contains(regex)){
						row.put("isDisplay",String.valueOf(bol));
						log_table.updateRow(row);
					}
				}
			} 
			qdb.close();
			MainFrame.scriptTable.updateUI();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void ui_select_all(boolean bol){
		int cnt = getRowCount();
		for (int i = 0; i < cnt; i++) {
			setValueAt(bol, i, 1);
		}
	}
	public void construct_select_all(String regex ,boolean bol){
		try {
			content.removeAllElements();
			MainFrame.scriptTable.updateUI();
			Database qdb = new DatabaseBuilder(new File("Test.mdb")).open();
			Table log_table = qdb.getTable("log");
			Vector vectors = new Vector();
			int i = 0;
			for(Row row : log_table) {
				String isSelect = row.get("isSelect").toString();
				if ("true".equals(isSelect)) {
					if(row.toString().contains(regex)){
						Vector v = new Vector(9);
						v.add(0, i);
						v.add(1, String.valueOf(bol));
						v.add(2, row.get("script").toString());
						v.add(3, row.get("summary").toString());
						v.add(4, row.get("device").toString());
						v.add(5, row.get("es").toString());
						v.add(6, row.get("result").toString());
						v.add(7, row.get("id_testcase").toString());
						v.add(8, row.get("suitName").toString());
						vectors.add(v);
						i = i +1;
						content.add(v);
					}
				}
			} 
			qdb.close();
			MainFrame.scriptTable.updateUI();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void filter(String filterText){
		try {
			content.removeAllElements();
			MainFrame.scriptTable.updateUI();
			Database qdb = new DatabaseBuilder(new File("Test.mdb")).open();
			Table log_table = qdb.getTable("log");
			Vector vectors = new Vector();
			int i = 0;
			for(Row row : log_table) {
				String isSelect = row.get("isSelect").toString();
				if ("true".equals(isSelect)) {
					if (row.toString().contains(filterText)) {
						Vector v = new Vector(9);
						v.add(0, new Integer(i));
						v.add(1, new Boolean(row.get("isDisplay").toString()));
						v.add(2, row.get("script").toString());
						v.add(3, row.get("summary").toString());
						v.add(4, row.get("device").toString());
						v.add(5, row.get("es").toString());
						v.add(6, row.get("result").toString());
						v.add(7, row.get("id_testcase").toString());
						v.add(8, row.get("suitName").toString());
						vectors.add(v);
						i = i +1;
						content.add(v);
					}
				}
			} 
			qdb.close();
			MainFrame.scriptTable.updateUI();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void construct(){
		MainFrame.setTitle("start construct script table");
		try {
			content.removeAllElements();
			MainFrame.scriptTable.updateUI();
			Database qdb = new DatabaseBuilder(new File("Test.mdb")).open();
			Table log_table = qdb.getTable("log");
			Vector vectors = new Vector();
			int i = 0;
			for(Row row : log_table) {
				String isSelect = row.get("isSelect").toString();
				if ("true".equals(isSelect)) {
					Vector v = new Vector(9);
					v.add(0, new Integer(i));
					v.add(1, new Boolean(row.get("isDisplay").toString()));
					v.add(2, row.get("script").toString());
					v.add(3, row.get("summary").toString());
					v.add(4, row.get("device").toString());
					v.add(5, row.get("es").toString());
					v.add(6, row.get("result").toString());
					v.add(7, row.get("id_testcase").toString());
					v.add(8, row.get("suitName").toString());
					vectors.add(v);
					i = i +1;
					content.add(v);
				}
			} 
			qdb.close();
			MainFrame.scriptTable.updateUI();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void up(String id_testsuit){
		Database db;
		Table table;
		Table logtable;
		try {
			db = new DatabaseBuilder(new File("Test.mdb")).open();
			table = db.getTable("config");
			logtable = db.getTable("log");

			//transform the config random to a list
			ArrayList<String> ids = new ArrayList<>();
			for (Row row : table) {
				ids.add(row.get("randomid").toString());
			}

			Map<String, ArrayList<Object[]>> rowMap = new Hashtable<>();
			Map<String, ArrayList<Object[]>> rowMapNew = new Hashtable<>();
			//covert the log table into a map
			for (String randomid : ids) {
				ArrayList<Object[]> rows = new ArrayList<>();
				for (Row row : logtable) {
					Object[] lrow = new Object[15];
					lrow[0]=row.get("id_testsuit").toString();
					if (id_testsuit.equals(lrow[0])) {
						lrow[1]=row.get("isSelect").toString();
						lrow[2]=row.get("id_testcase").toString();
						lrow[3]=row.get("isDisplay").toString();
						lrow[4]=row.get("suitName").toString();
						lrow[5]=row.get("caseName").toString();
						lrow[6]=row.get("summary").toString();
						lrow[7]=row.get("TimeStart").toString();
						lrow[8]=row.get("TimeEnd").toString();
						lrow[9]=row.get("classname").toString();
						lrow[10]=row.get("LogArgument").toString();
						lrow[11]=row.get("es").toString();
						lrow[12]=row.get("device").toString();
						lrow[13]=row.get("result").toString();
						lrow[14]=row.get("script").toString();
						rows.add(lrow);
					}
				}
				rowMap.put(randomid, rows);
			}
			db.close();

			//get the index 
			Object[] keySet =rowMap.keySet().toArray();
			int current = 0 ;
			for (int i = 0; i < keySet.length; i++) {
				String id_key = keySet[i].toString();
				if (id_testsuit.equals(id_key)) {
					current = i;
				}
			}
			//exchange the data in log table
			if (current==0){
				String id_key_up = keySet[0].toString();
				ArrayList<Object[]> up_value = rowMap.get(id_key_up);

				String id_key_down = keySet[keySet.length-1].toString();
				ArrayList<Object[]> down_value = rowMap.get(id_key_down);

				for (int i = 0; i < keySet.length; i++) {
					String id_key = keySet[i].toString();
					ArrayList<Object[]> id_value = rowMap.get(id_key);
					if (i==0) {
						rowMapNew.put(id_key_down, down_value);
					}else if (i==keySet.length-1) {
						rowMapNew.put(id_key_up, up_value);
					}else {
						rowMapNew.put(id_key, id_value);
					}
				}
			}else {
				for (int i = 0; i < keySet.length; i++) {
					if (i==current-1) {
						String id_key_up = keySet[i-1].toString();
						ArrayList<Object[]> up_value = rowMap.get(id_key_up);

						String id_key_down = keySet[i].toString();
						ArrayList<Object[]> down_value = rowMap.get(id_key_down);

						rowMapNew.put(id_key_down, down_value);
						rowMapNew.put(id_key_up, up_value);
						i = 1+1;
					}else {
						String id_key = keySet[i].toString();
						ArrayList<Object[]> id_value = rowMap.get(id_key);
						rowMapNew.put(id_key, id_value);
					}
				}
			}
			//delete all row in log table
			db = new DatabaseBuilder(new File("Test.mdb")).open();
			logtable = db.getTable("log");
			for (Row row : table) {
				table.deleteRow(row);
			}
			db.close();

			//add all new data in log table
			db = new DatabaseBuilder(new File("Test.mdb")).open();
			logtable = db.getTable("log");

			for (String key_new : rowMapNew.keySet()) {
				ArrayList<Object[]> new_values = rowMapNew.get(key_new);
				for (Object[] row : new_values) {
					logtable.addRow(row);
				}
			}
			db.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void updown(){
		Database db;
		Table table;
		Table logtable;
		try {
			db = new DatabaseBuilder(new File("Test.mdb")).open();
			table = db.getTable("config");
			logtable = db.getTable("log");

			//transform the config random to a list
			ArrayList<String> ids = new ArrayList<>();
			for (Row row : table) {
				ids.add(row.get("randomid").toString());
			}
			System.out.println(ids);

			ArrayList<Object[]> rowMap = new ArrayList<>();
			//covert the log table into a map
			for (String randomid : ids) {
				for (Row row : logtable) {
					Object[] lrow = new Object[15];
					lrow[0]=row.get("id_testsuit").toString();
					if (randomid.equals(lrow[0])) {
						lrow[1]=row.get("isSelect").toString();
						lrow[2]=row.get("id_testcase").toString();
						lrow[3]=row.get("isDisplay").toString();
						lrow[4]=row.get("suitName").toString();
						lrow[5]=row.get("caseName").toString();
						lrow[6]=row.get("summary").toString();
						lrow[7]=row.get("TimeStart").toString();
						lrow[8]=row.get("TimeEnd").toString();
						lrow[9]=row.get("classname").toString();
						lrow[10]=row.get("LogArgument").toString();
						lrow[11]=row.get("es").toString();
						lrow[12]=row.get("device").toString();
						lrow[13]=row.get("result").toString();
						lrow[14]=row.get("script").toString();
						rowMap.add(lrow);
					}
				}
			}
			db.close();

			for (Object[] objects : rowMap) {
				System.out.println(objects[0]);
			}

			//get the index 

			System.err.println("after change the index...");

			//delete all row in log table
			db = new DatabaseBuilder(new File("Test.mdb")).open();
			logtable = db.getTable("log");
			for (Row row : logtable) {
				logtable.deleteRow(row);
			}
			db.close();

			//add all new data in log table
			db = new DatabaseBuilder(new File("Test.mdb")).open();
			logtable = db.getTable("log");
			for (Object[] objects : rowMap) {
				logtable.addRow(objects);
			}
			db.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void LoadEs(RowBean rowBean,ArrayList<Object[]> objs){
		String es = rowBean.getArgument();
		String randomid_testsuit = rowBean.getId_testsuit();
		System.err.println(randomid_testsuit);
		String isSelect = rowBean.getIsSelect();
		String suitName = rowBean.getSuitname();
		String summary =rowBean.getSummary();
		String device = rowBean.getDevice();
		String script = rowBean.getScript();

		String LogArgument = "";
		String isDisplay = "false";
		String TimeStart = "";
		String TimeEnd ="";
		String result = "NOTRUN";

		ArrayList<ArgBean> argList = new ArrayList<ArgBean>();
		String[] cargs = es.split("\\|");
		for (String cs : cargs) {
			String[] csArg = cs.split("_");
			if (csArg.length==3) {
				ArgBean argBean = new ArgBean();
				argBean.setArgName(csArg[0]);
				argBean.setArgType(csArg[1]);
				argBean.setArgValue(csArg[2]);
				MainFrame.initValue(argBean);
				argList.add(argBean);
			}else {
				//if not exist: -e value
				ArgBean argBean = new ArgBean();
				argBean.setArgName(cs);
				argBean.setArgType("NA");
				argBean.setArgValue(cs);
				MainFrame.initValue(argBean);
				argList.add(argBean);
			}
		}
		int argCount = argList.size();
		int max= 0;
		for (int i = 0; i < argCount; i++) {
			ArgBean bean = argList.get(i);
			int count = bean.getValue().size();
			if (count>=max) {
				max=count;
			}
		}
		System.out.println("max data is : "+max);

		for (int i = 0; i < max; i++) {
			int indexNum = i+1;
			String id_testcase = randomid_testsuit+"_"+max+"_"+indexNum;
			String id_casename = summary+"_"+max+"_"+indexNum;

			Object[] lrow = new Object[15];
			lrow[0]=rowBean.getId_testsuit();
			lrow[1]=isSelect;
			lrow[2]=id_testcase;
			lrow[3]=isDisplay;
			lrow[4]=suitName;
			lrow[5]=id_casename;
			lrow[6]=summary;
			lrow[7]=TimeStart;
			lrow[8]=TimeEnd;
			lrow[9]="calssname";
			lrow[10]=LogArgument;

			StringBuffer argBuffer = new StringBuffer();
			for (int j = 0; j < argCount; j++) {
				ArgBean bean = argList.get(j);
				String argname = bean.getArgName();
				String argvalue;
				List<String> reads = bean.getValue();
				if (indexNum<=reads.size()) {
					argvalue = reads.get(i);
				}else {
					argvalue = reads.get(0);
				}
				String eArg = String.format("-e %s %s ", argname,argvalue);
				argBuffer.append(eArg);
			}

			lrow[11]=argBuffer.toString();
			lrow[12]=device;
			lrow[13]=result;
			lrow[14]=script;
			for (int j = 0; j < lrow.length; j++) {
				System.out.println(i+":"+j+":"+lrow[j]);
			}
			objs.add(lrow);
		}
	}
	public static void devChangeAll(String device){
		Database db = null;
		Table logtable = null;
		
		try {
			db = new DatabaseBuilder(new File("Test.mdb")).open();
			logtable = db.getTable("config");
			for (Row row : logtable) {
					row.put("device", device);
					logtable.updateRow(row);
			}
			db.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			db = new DatabaseBuilder(new File("Test.mdb")).open();
			logtable = db.getTable("log");
			for (Row row : logtable) {
				row.put("device", device);
				logtable.updateRow(row);
			}
			db.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void devChanged(String testsuit_id ,String device){
		Database db = null;
		Table logtable = null;
		
		try {
			db = new DatabaseBuilder(new File("Test.mdb")).open();
			logtable = db.getTable("config");
			for (Row row : logtable) {
				if(testsuit_id.equals(row.get("randomid"))){
					row.put("device", device);
					logtable.updateRow(row);
				}
			}
			db.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		try {
			db = new DatabaseBuilder(new File("Test.mdb")).open();
			logtable = db.getTable("log");
			for (Row row : logtable) {
				if(testsuit_id.equals(row.get("id_testsuit"))){
					row.put("device", device);
					logtable.updateRow(row);
				}
			}
			db.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void construct(String testsuit_id ,RowBean rowBean){
		Database db = null;
		Table logtable = null;
		int row_count=0;
		try {
			db = new DatabaseBuilder(new File("Test.mdb")).open();
			logtable = db.getTable("log");
			for (Row row : logtable) {
				if(testsuit_id.equals(row.get("id_testsuit"))){
					row_count = row_count + 1;
				}
			}
			db.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("row_count:"+row_count);
		String testcase_id = String.format("%s_%d_%d", testsuit_id,row_count,row_count);
		System.err.println(testcase_id);
		ArrayList<Object[]> objs = new ArrayList<>();

		try {
			db = new DatabaseBuilder(new File("Test.mdb")).open();
			logtable = db.getTable("log");
		} catch (Exception e) {
			// TODO: handle exception
		}
		for (Row row : logtable) {
			String id_testcase = row.get("id_testcase").toString();
			String id_testsuit = row.get("id_testsuit").toString();
			if (testsuit_id.equals(id_testsuit)) {
				if (testcase_id.equals(id_testcase)) {
					LoadEs(rowBean, objs);
				}
			}else {
				Object[] lrow = new Object[logtable.getColumnCount()];
				lrow[0]=row.get("id_testsuit").toString();
				lrow[1]=row.get("isSelect").toString();
				lrow[2]=row.get("id_testcase").toString();
				lrow[3]=row.get("isDisplay").toString();
				lrow[4]=row.get("suitName").toString();
				lrow[5]=row.get("caseName").toString();
				lrow[6]=row.get("summary").toString();
				lrow[7]=row.get("TimeStart").toString();
				lrow[8]=row.get("TimeEnd").toString();
				lrow[9]=row.get("classname").toString();
				lrow[10]=row.get("LogArgument").toString();
				lrow[11]=row.get("es").toString();
				lrow[12]=row.get("device").toString();;
				lrow[13]=row.get("result").toString();;
				lrow[14]=row.get("script").toString();;
				objs.add(lrow);
			}
		}
		try {
			db = new DatabaseBuilder(new File("Test.mdb")).open();
			logtable = db.getTable("log");
			for (Row row : logtable) {
				logtable.deleteRow(row);
			}
			for (Object[] object : objs) {
				logtable.addRow(object);
			}
			db.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void addRow(
			Object[] row 
			)
	{
		String id_testsuit = row[4].toString();
		String id_testcase = String.format("%s_1_1", id_testsuit);
		String summary = row[2].toString();
		String script =row[1].toString();
		String device = row[3].toString();
		String suitName = row[6].toString();
		
		Vector v = new Vector(9);
		v.add(0, new Integer(content.size()));
		v.add(1, false);
		v.add(2, script);
		v.add(3, summary);
		v.add(4, device);
		v.add(5, "NA");
		v.add(6, "");
		v.add(7, id_testcase);
		v.add(8, suitName);
		content.add(v);
		
		try {
			Database db = new DatabaseBuilder(new File("Test.mdb")).open();
			Table logtable = db.getTable("log");
			
			Object[] lrow = new Object[logtable.getColumnCount()];
			lrow[0]=id_testsuit;
			lrow[1]="false";
			lrow[2]=id_testcase;
			lrow[3]="false";
			lrow[4]=suitName;
			lrow[5]=id_testcase;
			lrow[6]=summary;
			lrow[7]="";
			lrow[8]="";
			lrow[9]="calssname";
			lrow[10]="";
			lrow[11]="-e NA NA ";
			lrow[12]=device;
			lrow[13]="NOTRUN";
			lrow[14]=script;
			
			logtable.addRow(lrow);
			db.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		this.fireTableDataChanged();
	}
	public void addRow(
			boolean selected,
			String script,
			String summary,
			String device,
			String es,
			//String e2,
			//String e3,
			String result,
			String randomid,
			String suitName
			)
	{
		Vector v = new Vector(9);
		v.add(0, new Integer(content.size()));
		v.add(1, new Boolean(selected));
		v.add(2, script);
		v.add(3, summary);
		v.add(4, device);
		v.add(5, es);
		//v.add(6, e2);
		//v.add(7, e3);
		v.add(6, result);
		v.add(7, randomid);
		v.add(8, suitName);
		content.add(v);
		this.fireTableDataChanged();
	}
	public void addRow(
			boolean selected,
			String script,
			String summary,
			String device,
			String es,
			//String e2,
			//String e3,
			String result,
			String randomid,
			String suitName,
			boolean display
			)
	{
		if (display) {
			Vector v = new Vector(9);
			v.add(0, new Integer(content.size()));
			v.add(1, new Boolean(selected));
			v.add(2, script);
			v.add(3, summary);
			v.add(4, device);
			v.add(5, es);
			//v.add(6, e2);
			//v.add(7, e3);
			v.add(6, result);
			v.add(7, randomid);
			v.add(8, suitName);
			content.add(v);
			this.fireTableDataChanged();
		}
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		//if (columnIndex==1||columnIndex==4) {
		if (columnIndex==1) {
			if (Lock.replay) {
				return false;
			}else {
				return true;
			}
		}else {
			return false;
		}
	}
	public void removeRow(int row) {
		content.remove(row);
	}

	public void Upmodify(int row) {
		if (row==-1) {

		}else if(row==0){
			Vector CurrentVenctor=(Vector)content.get(row);
			Vector UPVenctor=(Vector)content.get(content.size()-1);

			Object u0 = CurrentVenctor.get(0);
			Object d0 = UPVenctor.get(0);

			CurrentVenctor.set(0, d0);
			UPVenctor.set(0, u0);

			content.setElementAt((Object)UPVenctor, row);
			content.setElementAt((Object)CurrentVenctor, content.size()-1);
			MainFrame.scriptTable.setRowSelectionInterval(content.size()-1, content.size()-1);

		}
		else{
			Vector CurrentVenctor=(Vector)content.get(row);
			Vector UPVenctor=(Vector)content.get(row-1);

			Object u0 = CurrentVenctor.get(0);
			Object d0 = UPVenctor.get(0);

			CurrentVenctor.set(0, d0);
			UPVenctor.set(0, u0);

			content.setElementAt((Object)UPVenctor, row);
			content.setElementAt((Object)CurrentVenctor, row-1);

			MainFrame.scriptTable.setRowSelectionInterval(row-1, row-1);
		}

	}
	public void sequenceTable(Hashtable<Integer,Integer> sequenceSet){
		Vector sequencecontent =new Vector<>(content.size());
		for(Entry<Integer, Integer> entry:sequenceSet.entrySet())
		{
			sequencecontent.add(content.get(entry.getValue()));
		}
		
		for (int i = 0; i < sequencecontent.size(); i++) {
			Vector CurrentVenctor=(Vector)sequencecontent.get(i);
			
			Object u0 = CurrentVenctor.get(0);
			CurrentVenctor.set(0, i);
			content.set(i, CurrentVenctor);
		}
		MainFrame.scriptTable.updateUI();
	}
	public void Downmodify(int row) {
		if (row==-1) {

		}
		else{
			if(content.size()==row+1){
				Vector UPVenctor=(Vector)content.get(row);
				Vector DownVenctor=(Vector)content.get(0);

				Object u0 = UPVenctor.get(0);
				Object d0 = DownVenctor.get(0);

				UPVenctor.set(0, d0);
				DownVenctor.set(0, u0);

				content.setElementAt((Object)DownVenctor,row);
				content.setElementAt((Object)UPVenctor, 0);

				MainFrame.scriptTable.setRowSelectionInterval(0, 0);
			}
			else{
				Vector UPVenctor=(Vector)content.get(row);
				Vector DownVenctor=(Vector)content.get(row+1);

				Object u0 = UPVenctor.get(0);
				Object d0 = DownVenctor.get(0);

				UPVenctor.set(0, d0);
				DownVenctor.set(0, u0);

				content.setElementAt((Object)DownVenctor, row);
				content.setElementAt((Object)UPVenctor, row+1);

				MainFrame.scriptTable.setRowSelectionInterval(row+1, row+1);
			}
		}
	}
	public void setValueAt(Object value, int row, int col) {
		((Vector) content.get(row)).remove(col);
		((Vector) content.get(row)).add(col, value);
		/*
		 * java.lang.IndexOutOfBoundsException: Invalid range
		 * so not use this code
		 * */
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

	public Object getValueAt(int row, int col) throws ArrayIndexOutOfBoundsException{
		return ((Vector) content.get(row)).get(col);
	}

	public Class getColumnClass(int col) {
		return getValueAt(0, col).getClass();
	}
	public ArrayList<Integer> getSelectRows(){
		ArrayList<Integer> sList = new ArrayList<Integer>();
		int count = getRowCount();
		System.out.println(count);
		for (int i = 0; i < count; i++) {
			boolean select = Boolean.parseBoolean(String.valueOf(getValueAt(i, 1)));
			if (select) {
				sList.add(i);
			}
		}
		return sList;
	}
	public Hashtable<String, String> get_Select_Row_Random_ID(){
		Hashtable<String, String> row_hashtable = new Hashtable<String, String>();
		int count = getRowCount();
		for (int i = 0; i < count; i++) {
			boolean select = Boolean.parseBoolean(String.valueOf(getValueAt(i, 1)));
			if (select) {
				String random = getValueAt(i, 7).toString();
				row_hashtable.put(i+"", random);
			}
		}
		return row_hashtable;
	}
	public void modifyRow(int row,String status){
		String logxml = JarHelper.getProjectPath()+"log\\log.xml";
		String random =  getValueAt(row, 7).toString();	
		String caseName =String.format("%s_%s",MainFrame.scriptTable.getValueAt(row, 3).toString(),random.split("_")[1]+"_"+random.split("_")[2]);
		Log.err(caseName);
		String suitName = getValueAt(row, 8).toString();	
		String summary =  getValueAt(row, 3).toString();	
		String method_class = getValueAt(row, 2).toString();	
		String _method = method_class.split("#")[1];
		String _class = method_class.split("#")[0];
		StringBuffer buff =new StringBuffer();
		buff.append("INSTRUMENTATION_STATUS: current=1"+"\n");
		buff.append("INSTRUMENTATION_STATUS: id=UiAutomatorTestRunner"+"\n");
		buff.append("INSTRUMENTATION_STATUS: class="+_class+"\n");
		buff.append("INSTRUMENTATION_STATUS: stream="+"\n");
		buff.append(_class+":"+"\n");
		buff.append("INSTRUMENTATION_STATUS: numtests=1"+"\n");
		buff.append("INSTRUMENTATION_STATUS: test="+caseName+"\n");
		buff.append("INSTRUMENTATION_STATUS_CODE: 1"+"\n");
		buff.append("INSTRUMENTATION_STATUS: current=1"+"\n");
		buff.append("INSTRUMENTATION_STATUS: id=UiAutomatorTestRunner"+"\n");
		buff.append("INSTRUMENTATION_STATUS: class="+_class+"\n");
		buff.append("INSTRUMENTATION_STATUS: stream=."+"\n");
		buff.append("INSTRUMENTATION_STATUS: numtests=1"+"\n");
		buff.append("INSTRUMENTATION_STATUS: test="+caseName+"\n");
		buff.append("INSTRUMENTATION_STATUS_CODE: 0"+"\n");
		buff.append("INSTRUMENTATION_STATUS: stream="+"\n");
		buff.append("Test results for WatcherResultPrinter=."+"\n");

		if ("PASS".equals(status)) {
			LogXmlUpdate.testCaseUpdateXml(logxml, random, "LogArgument",buff.toString()+"OK (1 test)");
			LogXmlUpdate.testCaseUpdateXml(logxml, random, "caseName", caseName);
			LogXmlUpdate.testCaseUpdateXml(logxml, random, "suitName", suitName);
			LogXmlUpdate.testCaseUpdateXml(logxml, random, "summary", summary);
			setValueAt("PASS", row, 6);
		}
		if ("FAIL".equals(status)) {
			LogXmlUpdate.testCaseUpdateXml(logxml, random, "LogArgument",buff.toString()+"FAILURES!!!");
			LogXmlUpdate.testCaseUpdateXml(logxml, random, "caseName", caseName);
			LogXmlUpdate.testCaseUpdateXml(logxml, random, "suitName", suitName);
			LogXmlUpdate.testCaseUpdateXml(logxml, random, "summary", summary);
			setValueAt("FAIL", row, 6);
		}
		if ("NOTRUN".equals(status)) {
			LogXmlUpdate.testCaseUpdateXml(logxml, random, "LogArgument","NOTRUN");
			LogXmlUpdate.testCaseUpdateXml(logxml, random, "caseName", caseName);
			LogXmlUpdate.testCaseUpdateXml(logxml, random, "suitName", suitName);
			LogXmlUpdate.testCaseUpdateXml(logxml, random, "summary", summary);
			setValueAt("NOTRUN", row, 6);
		}
		if ("Terminated".equals(status)) {
			LogXmlUpdate.testCaseUpdateXml(logxml, random, "LogArgument","Terminated");
			LogXmlUpdate.testCaseUpdateXml(logxml, random, "caseName", caseName);
			LogXmlUpdate.testCaseUpdateXml(logxml, random, "suitName", suitName);
			LogXmlUpdate.testCaseUpdateXml(logxml, random, "summary", summary);
			setValueAt("Terminated", row, 6);
		}
	}
	public Statistics getStatistics(){
		Statistics stcs = new Statistics();
		int pass = 0;
		int fail = 0;
		int terminate = 0;
		int notrun = 0;

		ArrayList<Integer> selectRows = getSelectRows();
		for (Integer row : selectRows) {
			String res = getValueAt(row, 6).toString(); 
			if ("PASS".equals(res)) {
				pass = pass+1;
			}else if ("FAIL".equals(res)) {
				fail = fail +1;
			}else if ("NOTRUN".equals(res)) {
				notrun = notrun+1;
			}else if ("TERMINATE".equals(res)) {
				terminate = terminate+1;
			}else{

			}
		}
		stcs.setTests(pass+fail+notrun+terminate);
		stcs.setPass(pass);
		stcs.setFail(fail);
		stcs.setNotrun(notrun);
		stcs.setTerminate(terminate);
		return stcs;
	}
}






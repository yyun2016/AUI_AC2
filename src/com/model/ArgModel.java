package com.model;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

public class ArgModel extends AbstractTableModel {

	private static final long serialVersionUID = -7495940408592595397L;

	@SuppressWarnings("rawtypes")
	public Vector content = null;

	//private String[] title_name = { "id", "target","class","case","selected"};
	private String[] title_name = {
			"id",
			"参数名字",
			"参数类型",
			"参数值",
	};

	@SuppressWarnings("rawtypes")
	public ArgModel() {
		content = new Vector();
	}

	public ArgModel(int count) {
		content = new Vector(count);
	}

	public void serialize(){
		int count = getRowCount();
		for (int i = 0; i < count; i++) {
			setValueAt(i, i, 0);
		}
	}
	
	public void updateRow(
			int row,
			String argument,
			String type,
			String value
	){
		setValueAt(argument, row, 1);
		setValueAt(type, row, 2);
		setValueAt(value, row, 3);
	}
	public void addRow(
			String argument,
			String type,
			String value
	)
	{
		Vector v = new Vector(4);
		v.add(0, new Integer(content.size()));
		v.add(1, argument);
		v.add(2, type);
		v.add(3, value);
		content.add(v);
		this.fireTableDataChanged();
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex==1) {
			return true;
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
}






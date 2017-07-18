package com.model;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import com.frame.MainFrame;
import com.helper.JarHelper;

public class MobileModel extends AbstractTableModel {

	private static final long serialVersionUID = -7495940408592595397L;

	@SuppressWarnings("rawtypes")
	public static Vector content = null;

	//private String[] title_name = { "id", "target","class","case","selected"};
	private String[] title_name = {
			"id",
			"选择",
			"文件夹",
	};

	@SuppressWarnings("rawtypes")
	public MobileModel() {
		content = new Vector();
	}

	public MobileModel(int count) {
		content = new Vector(count);
	}

	public void addRow(
			boolean selected,
			String script
	)
	{
		Vector v = new Vector(3);
		v.add(0, new Integer(content.size()));
		v.add(1, new Boolean(selected));
		v.add(2, script);
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
	public void serialize(){
		int count = getRowCount();
		for (int i = 0; i < count; i++) {
			setValueAt(i, i, 0);
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

	public Object getValueAt(int row, int col) {
		return ((Vector) content.get(row)).get(col);
	}

	public Class getColumnClass(int col) {
		return getValueAt(0, col).getClass();
	}
	public ArrayList<String> getSelectRowsPath(){
		String folder = JarHelper.getProjectPath()+"mobile_log";
		ArrayList<String> sList = new ArrayList<String>();
		
		int count = getRowCount();
		for (int i = 0; i < count; i++) {
			boolean select = Boolean.parseBoolean(String.valueOf(getValueAt(i, 1)));
			if (select) {
				String folderFile = String.format("%s\\%s", folder,getValueAt(i,2).toString());
				sList.add(folderFile);
			}
		}
		return sList;
	}
	public ArrayList<Integer> getSelectRows(){
		String folder = JarHelper.getProjectPath()+"mobile_log";
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

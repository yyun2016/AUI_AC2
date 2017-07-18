package com.frame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TableFrame extends JFrame {
	public TableFrame(){
		setLayout(new BorderLayout());
		DefaultTableModel dm = new DefaultTableModel(new String[]{"index","content"}, 0){

			@Override
			public Object getValueAt(int row, int column) {
				if(column==0){
					return row;
				}
				return super.getValueAt(row, column);
			}
			
		};
		final JTable table=new JTable(dm);
		table.setUI(new DragDropRowTableUI());
		for(int i=0;i<10;i++){
			dm.addRow(new Object[]{i,i+"string"});
		}
		add(new JScrollPane(table),BorderLayout.CENTER);
		JButton btn=new JButton("hello");
		btn.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				System.out.println(table.getSelectedRow());;
				System.out.println(table.getValueAt(4, 1));
			}
		});
		add(btn,BorderLayout.SOUTH);
		this.setSize(600,400);
		setVisible(true);
	}
	public static void main(String[] args) {
		new TableFrame();
	}
}

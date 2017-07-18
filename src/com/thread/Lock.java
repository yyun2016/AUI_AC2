package com.thread;

import com.frame.MainFrame;
import com.panel.scriptPanel;

public class Lock {
	public static boolean replay = false;

	public static void setUp(){
		replay= true;
		MainFrame.selectAll.setEnabled(false);
		MainFrame.selectAllSP.setEnabled(false);
		MainFrame.editSelect.setEnabled(false);
		MainFrame.editSummary.setEnabled(false);
		MainFrame.scrpitsImport.setEnabled(false);
		MainFrame.scrpitsDel.setEnabled(false);
		MainFrame.scrpitsAdd.setEnabled(false);
		MainFrame.constantscript.setEnabled(false);
		MainFrame.spUpButton.setEnabled(false);
		MainFrame.spDownButton.setEnabled(false);
		scriptPanel.tf_file_path.setEnabled(false);
		MainFrame.tf_filter_path.setEnabled(false);
		MainFrame.bt_down.setEnabled(false);
		MainFrame.bt_up.setEnabled(false);
		System.out.println("..start...");
	}
	public static void tearDown(){
		replay= false;
		MainFrame.selectAll.setEnabled(true);
		MainFrame.selectAllSP.setEnabled(true);
		MainFrame.editSelect.setEnabled(true);
		MainFrame.editSummary.setEnabled(true);
		MainFrame.scrpitsImport.setEnabled(true);
		MainFrame.scrpitsDel.setEnabled(true);
		MainFrame.scrpitsAdd.setEnabled(true);
		MainFrame.constantscript.setEnabled(true);
		MainFrame.spUpButton.setEnabled(true);
		MainFrame.spDownButton.setEnabled(true);
		scriptPanel.tf_file_path.setEnabled(true);
		MainFrame.tf_filter_path.setEnabled(true);
		MainFrame.bt_down.setEnabled(true);
		MainFrame.bt_up.setEnabled(true);
		System.out.println("..stop...");
	}
}
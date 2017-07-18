package com.listener;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TabChangeListener implements ChangeListener{
	JTabbedPane jTabbedPane;
	public TabChangeListener(JTabbedPane jTabbedPane){
		this.jTabbedPane = jTabbedPane;
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		int selectedIndex = jTabbedPane.getSelectedIndex();
		System.out.println(selectedIndex);
	}

}

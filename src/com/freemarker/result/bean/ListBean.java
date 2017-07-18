package com.freemarker.result.bean;

import java.util.List;

import com.freemarker.result.bean.ListBean;

public class ListBean {
	private int totalpass = 0;
	private int totalfail = 0;
	private float totaltime = 0;
	private int total = 0;
	

	private String device = "Null";
	
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}

	private List<CaseBean> cbl;
	
	public void setCaseList(List<CaseBean> cbl){
		this.cbl = cbl;
	}
	public List<CaseBean> getCaseList() {
		return cbl;
	}


	
	public int getTotalpass() {
		return totalpass;
	}
	public void setTotalpass(int totalpass) {
		this.totalpass = totalpass;
	}
	
	public int getTotalfail() {
		return totalfail;
	}
	public void setTotalfail(int totalfail) {
		this.totalfail = totalfail;
	}
	
	public float getTotaltime() {
		return totaltime;
	}
	public void setTotaltime(float totaltime) {
		this.totaltime = totaltime;
	}
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("Device: %s, CaseBeanList: %s, Totalpass: %s, Totalfail: %s, Totaltime: %.2f, Total: %s" ,
				this.device, this.cbl, this.totalpass, this.totalfail, this.totaltime, this.total);
	}
}

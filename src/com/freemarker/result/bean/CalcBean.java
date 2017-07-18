package com.freemarker.result.bean;

public class CalcBean {
	private int totalpass = 0;
	private int totalfail = 0;
	private double totaltime = 0;
	
	public int getTotalpass() {
		return totalpass;
	}
	public void setTotalpass(int totalpass) {
		this.totalpass = totalpass;
	}
	
	public int getTotlalfail() {
		return totalfail;
	}
	public void setTotalfail(int totalfail) {
		this.totalfail = totalfail;
	}
	
	public double getTotaltime() {
		return totaltime;
	}
	public void setTotaltime(double totaltime) {
		this.totaltime = totaltime;
	}



	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("Totalpass: %s, Totalfail: %s, Totaltime: %s," ,
				this.totalpass, this.totalfail, this.totaltime);
	}
}

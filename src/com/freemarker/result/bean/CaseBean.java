package com.freemarker.result.bean;

public class  CaseBean {
	
	private String summary="Null";
	private String status="Not Run";
	private String log="Null";
	private float time=0;
	
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	public float getTime() {
		return time;
	}
	public void setTime(float time) {
		this.time = time;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("Summary: %s, Status: %s, Log: %s, Time: %.2f" ,
				this.summary, this.status, this.log, this.time);
	}
	
}

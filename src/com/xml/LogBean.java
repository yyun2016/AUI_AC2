package com.xml;

public class LogBean {
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getTimeEnd() {
		return TimeEnd;
	}
	public void setTimeEnd(String timeEnd) {
		TimeEnd = timeEnd;
	}
	public String getTimeStamp() {
		return TimeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		TimeStamp = timeStamp;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getCaseName() {
		return caseName;
	}
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
	public String getSuitName() {
		return suitName;
	}
	public void setSuitName(String suitName) {
		this.suitName = suitName;
	}
	public String getLogAugument() {
		return logAugument;
	}
	public void setLogAugument(String logAugument) {
		this.logAugument = logAugument;
	}
	public String getRandomid() {
		return randomid;
	}
	public void setRandomid(String randomid) {
		this.randomid = randomid;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		System.out.println(this.randomid);
		System.out.println(this.logAugument);
		
		return super.toString();
	}
	private	String logAugument;
	private	String randomid;
	private String caseName;
	private String summary;
	private String suitName;
	private String TimeEnd;
	private String TimeStamp;
	private String classname;
}

package com.junit;

public class Testcase {
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		System.out.println("===============================");
		System.err.println("Testcase.toString");
		System.out.println(classname+"");
		System.out.println(name+"");
		System.out.println(time+"");
		System.out.println(failureInfo.getFailInfo()+"");
		System.out.println(failureInfo.getType()+"");
		System.out.println(failureInfo.getMessage()+"");
		System.out.println("===============================");
		
		return super.toString();
	}
	public Failure getFailureInfo() {
		return failureInfo;
	}
	public void setFailureInfo(Failure failureInfo) {
		this.failureInfo = failureInfo;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	private  String classname;
	private  String name;
	private  String time;
	private  Failure failureInfo;
	
}

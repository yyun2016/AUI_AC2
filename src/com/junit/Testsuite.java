package com.junit;

import java.util.ArrayList;

public class Testsuite{
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		System.err.println("Testsuite.toString");
		System.out.println(errors);
		System.out.println(failures);
		System.out.println(hostname);
		System.out.println(id);
		System.out.println(name);
		System.out.println(t_package);
		System.out.println(tests);
		System.out.println(time);
		System.out.println(timestamp);
		
		/*for (Testcase testcase : testcases) {
			testcase.toString();
		}*/
		
		return super.toString();
	}
	public ArrayList<Testcase> getTestcases() {
		return testcases;
	}
	public void setTestcases(ArrayList<Testcase> testcases) {
		this.testcases = testcases;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getErrors() {
		return errors;
	}
	public void setErrors(String errors) {
		this.errors = errors;
	}
	public String getFailures() {
		return failures;
	}
	public void setFailures(String failures) {
		this.failures = failures;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getT_package() {
		return t_package;
	}
	public void setT_package(String t_package) {
		this.t_package = t_package;
	}
	public String getTests() {
		return tests;
	}
	public void setTests(String tests) {
		this.tests = tests;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	private  String errors="";
	private  String failures="";
	private  String hostname="";
	private  String id="";
	private  String name="";
	private  String t_package="";
	private  String tests="";
	private  String time="";
	private  String timestamp="";
	private  ArrayList<Testcase> testcases;
	
}

package com.helper;

public class Statistics {

	public int getTerminate() {
		return terminate;
	}
	public void setTerminate(int terminate) {
		this.terminate = terminate;
	}
	public int getPass() {
		return pass;
	}
	public void setPass(int pass) {
		this.pass = pass;
	}
	public int getTests() {
		return tests;
	}
	public void setTests(int tests) {
		this.tests = tests;
	}
	public int getNotrun() {
		return notrun;
	}
	public void setNotrun(int notrun) {
		this.notrun = notrun;
	}
	public int getFail() {
		return fail;
	}
	public void setFail(int fail) {
		this.fail = fail;
	}
	private int tests=0;
	private int pass=0;
	private int notrun=0;
	private int fail=0;
	private int terminate=0;

}

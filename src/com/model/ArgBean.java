package com.model;

import java.util.List;


public class ArgBean {
	public List<String> getValue() {
		return value;
	}
	public void setValue(List<String> value) {
		this.value = value;
	}
	public String getArgName() {
		return argName;
	}
	public void setArgName(String argName) {
		this.argName = argName;
	}
	public String getArgType() {
		return argType;
	}
	public void setArgType(String argType) {
		this.argType = argType;
	}
	public String getArgValue() {
		return argValue;
	}
	public void setArgValue(String argValue) {
		this.argValue = argValue;
	}
	String argName;
	String argType;
	String argValue;
	List<String> value;
	
}

package com.xml;

public class UiBean {
	public String getSuitName() {
		return suitName;
	}
	public void setSuitName(String suitName) {
		this.suitName = suitName;
	}
	public String getEs() {
		return es;
	}
	public void setEs(String es) {
		this.es = es;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		System.out.println(this.device);
		System.out.println(this.randomid);
		//System.out.println(this.e1);
		//System.out.println(this.e2);
		//System.out.println(this.e3);
		System.out.println(this.selected);
		System.out.println(this.summary);
		System.out.println(this.script);
		
		return super.toString();
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getSelected() {
		return selected;
	}
	public void setSelected(String selected) {
		this.selected = selected;
	}
	public String getScript() {
		return script;
	}
	public void setScript(String script) {
		this.script = script;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getRandomid() {
		return randomid;
	}
	public void setRandomid(String randomid) {
		this.randomid = randomid;
	}
	private String selected;
	private String script;
	private	String summary;
	private	String device;
	//private	String e1;
	//private	String e2;
	//private	String e3;
	private String randomid;
	private String es;
	private String suitName;
}

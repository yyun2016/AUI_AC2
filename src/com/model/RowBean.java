package com.model;

import com.log.Log;

public class RowBean {

	public String getIsSelect() {
		return isSelect;
	}
	public void setIsSelect(String isSelect) {
		this.isSelect = isSelect;
	}
	public String getId_testsuit() {
		return id_testsuit;
	}
	public void setId_testsuit(String id_testsuit) {
		this.id_testsuit = id_testsuit;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj == null){  
			return false;  
		}else {           
			if(this.getClass() == obj.getClass()){  
				RowBean u = (RowBean)obj;  
				if(this.getArgument().equals(u.getArgument())&&
				   this.getDevice().equals(u.getDevice())&&
				   this.getScript().equals(u.getScript())&&
				   this.getSuitname().equals(u.getSuitname())&&
				   this.getSummary().equals(u.getSummary())){  
					return true;  
				}else{  
					return false;  
				}  
			}else{  
				return false;  
			}  
		}
	}
	public boolean equalsDev(Object obj) {
		// TODO Auto-generated method stub
		if(obj == null){  
			return false;  
		}else {           
			if(this.getClass() == obj.getClass()){  
				RowBean u = (RowBean)obj;  
				if(this.getArgument().equals(u.getArgument())&&
				   this.getScript().equals(u.getScript())&&
				   this.getSuitname().equals(u.getSuitname())&&
				   this.getSummary().equals(u.getSummary())&&
				   (this.getDevice().equals(u.getDevice())==false)
						){  
					return true;  
				}else{  
					return false;  
				}  
			}else{  
				return false;  
			}  
		}
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		/*Log.err(script);
		Log.err(summary);
		Log.err(argument);
		Log.err(device);
		Log.err(suitname);
		Log.info("======================");*/
		return super.toString();
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
	public String getArgument() {
		return argument;
	}
	public void setArgument(String argument) {
		this.argument = argument;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getSuitname() {
		return suitname;
	}
	public void setSuitname(String suitname) {
		this.suitname = suitname;
	}
	private String script ;
	private String summary ;
	private String argument ;
	private String device ;
	private String suitname ;
	private String id_testsuit ;
	private String isSelect ;

}

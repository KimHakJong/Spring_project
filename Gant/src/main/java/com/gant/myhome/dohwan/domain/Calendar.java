package com.gant.myhome.dohwan.domain;

public class Calendar {
	
	String id;
	String admin;
	String allday;
	String name;
	String startday;
	String endday;
	String title;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStartday() {
		return startday;
	}
	public void setStartday(String startday) {
		this.startday = startday;
	}
	public String getEndday(){
		return endday;
	}
	public void setEndday(String endday) {
		this.endday = endday;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public String getAllday() {
		return "true";
	}
	public void setAllday(String allday) {
		this.allday = allday;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
}

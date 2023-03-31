package com.gant.myhome.domain;

public class ReservationCheck {
	private String resource_name;
	private String day;
	private int reserved_time;
	public String getResource_name() {
		return resource_name;
	}
	public void setResource_name(String resource_name) {
		this.resource_name = resource_name;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public int getReserved_time() {
		return reserved_time;
	}
	public void setReserved_time(int reserved_time) {
		this.reserved_time = reserved_time;
	}
}

package com.ourteams.backend;

import java.io.Serializable;

public class Date implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String day;
	private String month;
	private String year;
	private String hour;
	private String minute;
	
	//default constructor
	public Date() {
		
	}

	//argument constructor
	public Date(String day, String month, String year, String hour, String minute) {
		this.day = day;
		this.month = month;
		this.year = year;
		this.hour = hour;
		this.minute = minute;
	}
	
	//getters and setters for data members
	public String getFormattedDate(){
		return this.day + "/" + this.month + "/" + this.year + ". " + this.hour + ":" + this.minute;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	@Override
	public String toString() {
		return "Date [day=" + day + ", month=" + month + ", year=" + year + ", hour=" + hour + ", minute=" + minute
				+ "]";
	}

		
}

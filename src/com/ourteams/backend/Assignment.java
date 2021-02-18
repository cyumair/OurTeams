package com.ourteams.backend;
import java.io.Serializable;
import java.util.ArrayList;

public class Assignment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private Date dueDate;
	private Team team;
	private String detail;
	private double totalPoints; //we are making it string as User can assign A grade instead of number
	private ArrayList<AssignmentData> assignmentData = new ArrayList<AssignmentData>();
	
	//default constructor
	public Assignment() {
		
	}
	
	//argument constructor
	public Assignment(String title, Date dueDate, String detail, Team team, double points) {
		this.title = title;
		this.dueDate = dueDate;
		this.detail = detail;
		this.totalPoints = points;
		this.team = team;
//		this.referenceFile = refFile;
	}
	
	
	//setters and getters for data members
	public Team getTeam() {
		return this.team;
	}


	public void setTeamn(Team team) {
		this.team = team;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}


	public ArrayList<AssignmentData> getAssignmentData() {
		return assignmentData;
	}

	public void setAssignmentData(ArrayList<AssignmentData> assignmentData) {
		this.assignmentData = assignmentData;
	}

	public double getTotalPoints() {
		return totalPoints;
	}


	public void setTotalPoints(double totalPoints) {
		this.totalPoints = totalPoints;
	}

	@Override
	public String toString() {
		return "Assignment [title=" + title + ", dueDate=" + dueDate.getFormattedDate() + ", team=" + team + ", detail=" + detail
				+ ", totalPoints=" + totalPoints + ", assignmentData="
				+ assignmentData + "]";
	}

	
}

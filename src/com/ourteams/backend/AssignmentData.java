package com.ourteams.backend;
import java.io.File;
import java.io.Serializable;
public class AssignmentData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Student student;
	private File file;
	private double obtainedPoints;
	private String markedby;
	
	
	//argument constructor
	public AssignmentData(Student student, File file) {
		this.student = student;
		this.file = file;
		this.obtainedPoints = -1;
	}
	
	//overriding equals method
	public boolean equals(AssignmentData data) {
		if(this.student.equals(student)) {
			return true;
		}
		return false;
	}
	
	//getters and setters for data members
	public double getObtainedPoints() {
		return this.obtainedPoints;
	}

	public Student getStudent() {
		return student;
	}
	
	public String getMarkedby() {
		return markedby;
	}

	public void setMarkedby(String checkedby) {
		this.markedby = checkedby;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void setObtainedPoints(double points) {
		this.obtainedPoints = points;	
	}
	
	
	
	
	
}

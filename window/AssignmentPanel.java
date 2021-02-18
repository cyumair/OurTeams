package com.ourteams.window;

import java.awt.Color;

import javax.swing.JPanel;

import com.ourteams.backend.Assignment;
import com.ourteams.backend.Student;
import com.ourteams.backend.User;


public class AssignmentPanel extends JPanel{
	/**
	 * This panel handlers all the sub panels for viewing, uploading, creating, checking assignments. 
	 * Different functionality / Panel is provided to Student and a Faculty member so different panels are created based on user
	 */
	private static final long serialVersionUID = 1L;
	private JPanel currentPanel;
	private User user;
	
	public AssignmentPanel(User user) {
		this.user = user;
		initialize();
	}

	private void initialize() {
		this.setLayout(null);
		this.setBounds(75, 42, 1149, 687);
		this.currentPanel = new ViewAssignmentsPanel(this);
		this.setBackground(new Color(33, 32, 32));
		
		
		this.add(currentPanel);
		

	}
	
	/*
	 * Opens up a new Panel with details of the assignment.
	 * if the user is faculty member than opens Faculty Assignment Panel (for checking, deleting and updating assignments) 
	 * else opens Student Assignment Panel (for uploading, viewing assignments)
	 */
	
	public void openAssignment(Assignment assignment) {
		this.remove(currentPanel);
		if (user instanceof Student){
            this.currentPanel = new StudentAssignmentPanel(user, assignment);
         
        }
        else{
            this.currentPanel = new FacultyAssignmentPanel(this, assignment);
        }

		this.add(currentPanel);
        this.repaint();
        this.revalidate();	
	}
	
	//refreshes the View Assignment Panel
	public void refreshViewAssignment() {
		this.remove(currentPanel);
		currentPanel = new ViewAssignmentsPanel(this);
		this.add(currentPanel);
		repaint();
		revalidate();
	}
	
	//getter for the current user
	public User getUser() {
		return user;
	}
	
	
	
	

	
	
	
	
	
	
	
	
	
}

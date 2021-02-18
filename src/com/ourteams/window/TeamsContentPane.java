package com.ourteams.window;

import java.awt.Color;

import javax.swing.JPanel;

import com.ourteams.backend.Team;
import com.ourteams.backend.User;

public class TeamsContentPane extends JPanel{
	/**
	 * This Panel displays the contents of the Team, team menu panel or current team panel
	 */
	private static final long serialVersionUID = 1L;
	private JPanel currentPanel;
	private User user;
	public TeamsContentPane(User user) {
		this.user = user;
		initialize();
	}
	
	private void initialize() {
		this.setLayout(null);
		this.setBounds(75, 42, 1149, 687);
		this.setBackground(new Color(33, 32, 32));
		this.currentPanel = new TeamsMenuPanel(this);
		this.add(currentPanel);
		
		
		
	}

	public void openCurrentTeamsPanel(Team team) {
		this.remove(currentPanel);
		currentPanel = new currentTeamsPane(team, this);
		this.add(currentPanel);
		this.repaint();
		this.revalidate();
	}
	
	public void updateMenuPanel() {
		this.remove(currentPanel);
		currentPanel = new TeamsMenuPanel(this);
		this.add(currentPanel);
		this.repaint();
		this.revalidate();
	}

	public User getUser() {
		return user;
	}
	
	
		
	
}

package com.ourteams.window;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import com.ourteams.backend.Student;
import com.ourteams.backend.Team;
import com.ourteams.backend.User;

public class ParticipantsPanel extends JPanel{

	/**
	 * The Panel Displays all the participants of the team
	 */
	private static final long serialVersionUID = 1L;
	private Team team;
	private JTextPane textPane;
	
	public ParticipantsPanel(Team team) {
		this.team = team;
		initialize();
		
	}
	
	private void initialize() {
		
		this.setBounds(75, 70, 1009, 592);
		this.setBackground(Color.BLACK);
		this.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 989, 570);
		
		textPane = new JTextPane();
		textPane.setFont(new Font("Lucida Fax", Font.PLAIN, 16));
		textPane.setEditable(false);
		textPane.setForeground(new Color(124, 252, 0));
		textPane.setBackground(Color.DARK_GRAY);
		scrollPane.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		scrollPane.setViewportView(textPane);
		
		for(User user: team.getParticipants()) {
			String text ="  " + user.getName() +", "+ user.getEmail() + ", ";
			if(user instanceof Student) {
				text += "Student, " + ((Student)user).getSemester() + " Semester";
			}
			else {
				text += "Faculty";
			}
			text += "\n  Department of "+user.getDepartment() + "\n---------------------------------------------------------"
					+ "----------------------------------------------------------------------\n";
			textPane.setText(textPane.getText() +  text);

		this.add(scrollPane);
		}
	}
	
}

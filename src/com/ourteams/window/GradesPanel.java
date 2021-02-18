package com.ourteams.window;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import com.ourteams.backend.Assignment;
import com.ourteams.backend.AssignmentData;
import com.ourteams.backend.Team;
import com.ourteams.backend.User;

public class GradesPanel extends JPanel{
	
	/**
	 * This Panel is sub-panel of Current Team Panel, 
	 * displays the grades of the current user in all assignments of that Team
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private Team team;
	private User user;
	public GradesPanel(Team team, User user) {
		this.team = team;
		this.user = user;
		initialize();
	}

	private void initialize() {
		
		this.setBounds(75, 70, 1009, 592);
		this.setBackground(Color.BLACK);
		this.setLayout(null);
		
		JLabel headingLabel = new JLabel("           Assignment              "
				+ "                                Points             "
				+ "                      Marked By         "
				+ "                    Due Date    ");
		headingLabel.setOpaque(true);
		headingLabel.setBackground(Color.DARK_GRAY);
		headingLabel.setFont(new Font("Lucida Fax", Font.PLAIN, 16));
		headingLabel.setForeground(Color.LIGHT_GRAY);
		headingLabel.setBounds(10, 11, 989, 34);
		this.add(headingLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		scrollPane.setBounds(10, 47, 989, 534);
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Lucida Fax", Font.PLAIN, 16));
		textPane.setEditable(false);
		textPane.setForeground(new Color(124, 252, 0));
		textPane.setBackground(Color.DARK_GRAY);
		scrollPane.setViewportView(textPane);
		
		for(Assignment assign: team.getAssignments()) {
			for(AssignmentData data: assign.getAssignmentData()) {
				if(user.equals(data.getStudent())) {
					if(data.getObtainedPoints() != -1) {
						String text = "  " +assign.getTitle()+ "\t\t\t\t" + data.getObtainedPoints()+ "/" +assign.getTotalPoints()+
							"\t\t"+data.getMarkedby()+"\t\t"+assign.getDueDate().getFormattedDate()+"\n";
						textPane.setText(textPane.getText() + text);
					}
				}
			
			}

		}
		this.add(scrollPane);
	}

}

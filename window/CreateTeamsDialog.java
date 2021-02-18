package com.ourteams.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.ourteams.backend.Team;
import com.ourteams.backend.User;

public class CreateTeamsDialog extends JDialog {

	/**
	 * A dialog that asks for team name and code and creates a new Team
	 */
	private static final long serialVersionUID = 1L;
	private JTextField teamcodefield;
	private JTextField teamnamefield;
	private JButton createButton;
	private User user;
	private JLabel errorlabel;
	private TeamsContentPane teamscontentpanel;
	
	public CreateTeamsDialog(TeamsContentPane teamscontentpanel){
		this.user = teamscontentpanel.getUser();
		this.teamscontentpanel = teamscontentpanel;
		initialize();
	}

	private void initialize() {
		setBounds(470, 300, 580, 399);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel contentPanel = new JPanel();
		contentPanel.setBounds(0, 0, 573, 360);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		contentPanel.setBackground(new Color(25,20,20));
		
		JLabel toplabel = new JLabel("Create a New Team");
		toplabel.setFont(new Font("MV Boli", Font.BOLD, 24));
		toplabel.setForeground(Color.RED);
		toplabel.setBounds(169, 41, 271, 33);
		contentPanel.add(toplabel);
		
		ActionListener al = new actionlistener();
		createButton = new JButton("Create");
		createButton.setContentAreaFilled(false);
		createButton.setForeground(Color.RED);
		createButton.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 18));
		createButton.setBounds(225, 244, 118, 33);
		contentPanel.add(createButton);
		createButton.addActionListener(al);
		
		teamnamefield = new JTextField();
		teamnamefield.setForeground(Color.WHITE);
		teamnamefield.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 18));
		teamnamefield.setCaretColor(Color.WHITE);
		teamnamefield.setBackground(Color.DARK_GRAY);
		teamnamefield.setBounds(215, 110, 281, 40);
		contentPanel.add(teamnamefield);
		teamnamefield.setColumns(10);
		
		teamcodefield = new JTextField();
		teamcodefield.setForeground(Color.WHITE);
		teamcodefield.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 18));
		teamcodefield.setCaretColor(Color.WHITE);
		teamcodefield.setBackground(Color.DARK_GRAY);
		teamcodefield.setColumns(10);
		teamcodefield.setBounds(215, 168, 281, 40);
		contentPanel.add(teamcodefield);
		
		JLabel teamlabel = new JLabel("Team Name");
		teamlabel.setForeground(Color.WHITE);
		teamlabel.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 18));
		teamlabel.setBounds(73, 111, 132, 33);
		contentPanel.add(teamlabel);
		
		JLabel joinlabel = new JLabel("Join Code");
		joinlabel.setForeground(Color.WHITE);
		joinlabel.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 18));
		joinlabel.setBounds(73, 169, 118, 33);
		contentPanel.add(joinlabel);
		
		errorlabel = new JLabel("");
		errorlabel.setForeground(Color.RED);
		errorlabel.setFont(new Font("MV Boli", Font.BOLD, 16));
		errorlabel.setBounds(169, 287, 327, 33);
		contentPanel.add(errorlabel);
		
	
		this.setVisible(true);
	}
	
	class actionlistener implements ActionListener{

		@Override
		/*
		 * When the create button is pressed then check if any other Team with same code is already created
		 * if not then call the create team method providing its parameters and creating a new team
		 * The frame is closed after the team is created
		 */
		
		public void actionPerformed(ActionEvent a) {
			String name = teamnamefield.getText();
			if(name.equals("")) {
				errorlabel.setText("Name cannot be Set Empty");
			}
			else {
				String joincode = teamcodefield.getText();
				if(joincode.equals("")) {
					errorlabel.setText("Join Code cannot be Empty");
				}
				else {
					if(!Team.isTeamAlreadyCreated(joincode)) {
						Team newteam = new Team(joincode, name);
						user.createTeam(newteam);
						teamcodefield.setText("");
						teamnamefield.setText("");
						errorlabel.setText(" Team created Successfully");
						teamscontentpanel.updateMenuPanel();
						dispose();
					}
					else {
						teamcodefield.setText("");
						errorlabel.setText("This Code is not avaialable");
					}
				}
				
			}
			
			
		}
		
	}
}

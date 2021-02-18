package com.ourteams.window;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.ourteams.backend.Faculty;
import com.ourteams.backend.Team;
import com.ourteams.backend.User;

public class currentTeamsPane extends JPanel{
	/**
	 * Panel for displaying a Teams data, participants, posts, grades..
	 */
	private static final long serialVersionUID = 1L;
	private JPanel currentPanel;
	private JButton postsButton;
	private JButton participantsButton;
	private JButton gradesButton;
	private JButton leaveButton;
	private LineBorder grayborder = new LineBorder(Color.GRAY, 2);
	private LineBorder redborder = new LineBorder(Color.RED);
	private Team team;
	private User user;
	private TeamsContentPane teamscontentpane;

	
	public currentTeamsPane(Team team, TeamsContentPane teamscontentpane) {
		this.team = team;
		this.teamscontentpane = teamscontentpane;
		this.user = teamscontentpane.getUser();
		initialize();
	}

	private void initialize() {
		this.setBackground(new Color(27, 25, 25));
		this.setBounds(0, 0, 1149, 687);
		this.setLayout(null);
		
		currentPanel = new PostsPane(team, user);
		
		JLabel teamlabel = new JLabel();
		teamlabel.setText(this.team.getName());
		teamlabel.setBounds(52, 27, 192, 35);
		teamlabel.setFont(new Font("Monotype Corsiva", Font.BOLD, 23));
		teamlabel.setForeground(new Color(255, 255, 255));
		
		ActionListener al = new ActionHandler();
		MouseListener ml = new MouseHandler();
		
		participantsButton = new JButton("Participants");
		participantsButton.setBounds(840, 27, 121, 27);
		participantsButton.setForeground(Color.WHITE);
		participantsButton.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 13));
		participantsButton.setBorder(grayborder);
		participantsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		participantsButton.addActionListener(al);
		participantsButton.addMouseListener(ml);
		participantsButton.setContentAreaFilled(false);
		
		gradesButton = new JButton("Grades");
		gradesButton.setBounds(578, 27, 121, 27);
		gradesButton.setForeground(Color.WHITE);
		gradesButton.setBorder(grayborder);
		gradesButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		gradesButton.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 13));
		gradesButton.setContentAreaFilled(false);
		if(this.user instanceof Faculty) {
			gradesButton.setEnabled(false);
		}
		else {
			gradesButton.addActionListener(al);
			gradesButton.addMouseListener(ml);
		}
		
		postsButton = new JButton("Posts");
		postsButton.setBounds(709, 27, 121, 27);
		postsButton.setForeground(Color.WHITE);
		postsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		postsButton.setBorder(grayborder);
		postsButton.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 13));
		postsButton.addActionListener(al);
		postsButton.addMouseListener(ml);
		postsButton.setContentAreaFilled(false);
		
		leaveButton = new JButton("Leave");
		leaveButton.setBounds(971, 27, 121, 27);
		leaveButton.setForeground(Color.WHITE);
		leaveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		leaveButton.setBorder(grayborder);
		leaveButton.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 13));
		leaveButton.addActionListener(al);
		leaveButton.addMouseListener(ml);
		leaveButton.setContentAreaFilled(false);
		
		this.add(leaveButton);
		this.add(postsButton);
		this.add(participantsButton);
		this.add(gradesButton);
		this.add(teamlabel);
		this.add(currentPanel);
			
		
	}
	
	class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent a) {
			//When user presses posts Button then current panel is replaced by posts panel
			if(a.getSource().equals(postsButton)) {
				changePanel(new PostsPane(team, user));
			}
			//When user presses participants Button then current panel is replaced by participants panel
			else if(a.getSource().equals(participantsButton)) {
				changePanel(new ParticipantsPanel(team));
			}
			//When user presses grades Button then current panel is replaced by grades panel
			else if(a.getSource().equals(gradesButton)) {
				changePanel(new GradesPanel(team, user));
			}
			/*
			 * When user presses leave Button then a confirm dialog box appears,
			 * on confirming the leave team method is called for that user 
			 * and user is returned to Teams menu 
			 */
			else if(a.getSource().equals(leaveButton)) {
				int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to leave", "Leave Teams", JOptionPane.YES_NO_OPTION);
				if(confirm == 0) {
					user.leaveTeam(team);
					teamscontentpane.updateMenuPanel();
				}
			}
			
		}

		//This method takes a JPanel as argument and replaces the previous panel with that JPanel
		private void changePanel(JPanel panel) {
			remove(currentPanel);
			currentPanel = panel;
			add(currentPanel);
			repaint();
			revalidate();
		}
		
	}
	
	//Mouse Listener for hover over effects
	class MouseHandler implements MouseListener{

		@Override
		public void mouseEntered(MouseEvent m) {
			if(m.getSource().equals(postsButton)) {
				postsButton.setBorder(redborder);
			}
			else if(m.getSource().equals(gradesButton)){
				gradesButton.setBorder(redborder);
			}
			else if(m.getSource().equals(participantsButton)) {
				participantsButton.setBorder(redborder);
			}
			else if(m.getSource().equals(leaveButton)) {
				leaveButton.setBorder(redborder);
			}
			
		}

		@Override
		public void mouseExited(MouseEvent m) {
			if(m.getSource().equals(postsButton)) {
				postsButton.setBorder(grayborder);
			}
			else if(m.getSource().equals(gradesButton)){
				gradesButton.setBorder(grayborder);
			}
			else if(m.getSource().equals(participantsButton)) {
				participantsButton.setBorder(grayborder);
			}			
			else if(m.getSource().equals(leaveButton)) {
				leaveButton.setBorder(grayborder);
			}			
		}
		
		@Override
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
		public void mouseClicked(MouseEvent arg0) {}

		
	}
	
	
}

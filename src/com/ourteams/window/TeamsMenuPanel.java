package com.ourteams.window;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.ourteams.backend.User;
import com.ourteams.backend.Team;

public class TeamsMenuPanel extends JPanel{
	/**
	 * This Panel displays the list of all the teams user has joined in buttons
	 * A user can select a team and open its contents
	 */
	private static final long serialVersionUID = 1L;
	private JButton createbutton;
	private JButton joinbutton;
	private ArrayList<JButton> buttons = new ArrayList<JButton>();
	private JLabel teamlabel;
	private User user;
	private ArrayList<Team> userteams;
	private TeamsContentPane teamContentPanel;
	private LineBorder redborder= new LineBorder(Color.RED);
	private LineBorder blueborder= new LineBorder(Color.BLUE, 2);
	private LineBorder greenborder= new LineBorder(Color.GREEN, 2);
	private LineBorder border1 = new LineBorder(new Color(52, 50, 50), 3);
	private int pageNumber;
	private JButton nextButton;
	
	public TeamsMenuPanel(TeamsContentPane teamscontentpanel) {
		this.user = teamscontentpanel.getUser();
		this.userteams = user.getTeams();
		this.teamContentPanel = teamscontentpanel;
		this.setBackground(new Color(33, 32, 32));
		this.pageNumber = 0;
		this.initialize();
		
	}
	
	
	private void initialize() {
		this.setLayout(null);
		this.setBounds(0, 0, 1149, 687);
		this.addButtons();
		
		teamlabel = new JLabel("Your Teams");
		teamlabel.setBounds(103, 40, 192, 35);
		teamlabel.setFont(new Font("Monotype Corsiva", Font.BOLD, 23));
		teamlabel.setForeground(new Color(255, 255, 255));
		
		JLabel noteamlabel = new JLabel("You current donot have any team.");
		noteamlabel.setForeground(Color.RED);
		noteamlabel.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
		noteamlabel.setBounds(159, 110, 717, 49);
		noteamlabel.setVisible(false);
		if(this.user.getTeams().size() == 0) {
			noteamlabel.setVisible(true);
		}
		
		this.add(noteamlabel);
		this.add(teamlabel);
		

	}
	
	class actionlistener implements ActionListener{

		@Override
		//On clicking any team button the Panel for the team is opened on the Team content Panel
		public void actionPerformed(ActionEvent a) {

			if(a.getSource().equals(buttons.get(0))) {
				teamContentPanel.openCurrentTeamsPanel(userteams.get(0 + pageNumber * 9));
			}
			else if(a.getSource().equals(buttons.get(1))) {
				teamContentPanel.openCurrentTeamsPanel(userteams.get(1 + pageNumber * 9));
			}
			else if(a.getSource().equals(buttons.get(2))) {
				teamContentPanel.openCurrentTeamsPanel(userteams.get(2 + pageNumber * 9));
			}
			else if(a.getSource().equals(buttons.get(3))) {
				teamContentPanel.openCurrentTeamsPanel(userteams.get(3 + pageNumber * 9));
			}
			else if(a.getSource().equals(buttons.get(4))) {
				teamContentPanel.openCurrentTeamsPanel(userteams.get(4 + pageNumber * 9));
			}
			else if(a.getSource().equals(buttons.get(5))) {
				teamContentPanel.openCurrentTeamsPanel(userteams.get(5 + pageNumber * 9));
			}
			else if(a.getSource().equals(buttons.get(6))) {
				teamContentPanel.openCurrentTeamsPanel(userteams.get(6 + pageNumber * 9));
			}
			else if(a.getSource().equals(buttons.get(7))) {
				teamContentPanel.openCurrentTeamsPanel(userteams.get(7 + pageNumber * 9));
			}
			else if(a.getSource().equals(buttons.get(8))) {
				teamContentPanel.openCurrentTeamsPanel(userteams.get(8 + pageNumber * 9));
			}
			else if(a.getSource().equals(createbutton)){
				new CreateTeamsDialog(teamContentPanel);
			}
			else if(a.getSource().equals(joinbutton)) {
				new JoinTeamsDialog(userteams, teamContentPanel);
			}
			else if(a.getSource().equals(nextButton)) {
					pageNumber++;
					goToNextPage();
//					repaint();

			}
			
		}
		
	}
	
	//Mouse Listener for Hover-Over Effects
	class mouselistener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {}

		@Override
		public void mouseEntered(MouseEvent m) {
			
			if(m.getSource().equals(buttons.get(0))){
				buttons.get(0).setBorder(redborder);
			}
			else if(m.getSource().equals(buttons.get(1))){
				buttons.get(1).setBorder(blueborder);
			}
			else if(m.getSource().equals(buttons.get(2))){
				buttons.get(2).setBorder(greenborder);
			}
			else if(m.getSource().equals(buttons.get(3))){
				buttons.get(3).setBorder(blueborder);
			}
			else if(m.getSource().equals(buttons.get(4))){
				buttons.get(4).setBorder(greenborder);
			}
			else if(m.getSource().equals(buttons.get(5))){
				buttons.get(5).setBorder(redborder);
			}
			else if(m.getSource().equals(buttons.get(6))){
				buttons.get(6).setBorder(greenborder);
			}
			else if(m.getSource().equals(buttons.get(7))){
				buttons.get(7).setBorder(redborder);
			}
			else if(m.getSource().equals(buttons.get(8))){
				buttons.get(8).setBorder(blueborder);
			}
			else if(m.getSource().equals(joinbutton)){
				joinbutton.setBorder(redborder);
			}
			else if(m.getSource().equals(createbutton)){
				createbutton.setBorder(redborder);
			}
		
		}

		@Override
		public void mouseExited(MouseEvent m) {
			if(m.getSource().equals(buttons.get(0))){
				buttons.get(0).setBorder(border1);
			}
			else if(m.getSource().equals(buttons.get(1))){
				buttons.get(1).setBorder(border1);
			}
			else if(m.getSource().equals(buttons.get(2))){
				buttons.get(2).setBorder(border1);
			}
			else if(m.getSource().equals(buttons.get(3))){
				buttons.get(3).setBorder(border1);
			}
			else if(m.getSource().equals(buttons.get(4))){
				buttons.get(4).setBorder(border1);
			}
			else if(m.getSource().equals(buttons.get(5))){
				buttons.get(5).setBorder(border1);
			}
			else if(m.getSource().equals(buttons.get(6))){
				buttons.get(6).setBorder(border1);
			}
			else if(m.getSource().equals(buttons.get(7))){
				buttons.get(7).setBorder(border1);
			}
			else if(m.getSource().equals(buttons.get(8))){
				buttons.get(8).setBorder(border1);
			}
			else if(m.getSource().equals(joinbutton)){
				joinbutton.setBorder(border1);
			}
			else if(m.getSource().equals(createbutton)){
				createbutton.setBorder(border1);
			}			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {}

		@Override
		public void mouseReleased(MouseEvent arg0) {}
		
	}
	

	//This method initializes all the Buttons in the Panel
	private void addButtons() {
		MouseListener ml = new mouselistener();
		ActionListener al = new actionlistener();
		
		createbutton = new JButton("Create");
		createbutton.setBounds(790, 45, 114, 27);
		createbutton.setForeground(Color.WHITE);
		createbutton.setContentAreaFilled(false);
		createbutton.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 13));
		createbutton.setBorder(border1);
		createbutton.addMouseListener(ml);
		createbutton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		createbutton.addActionListener(al);
		
	    joinbutton = new JButton("Join");
		joinbutton.setBounds(914, 45, 114, 27);
		joinbutton.setForeground(Color.WHITE);
		joinbutton.setContentAreaFilled(false);
		joinbutton.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 13));
		joinbutton.setBorder(border1);
		joinbutton.addMouseListener(ml);
		joinbutton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		joinbutton.addActionListener(al);
		
		
		nextButton = new JButton("next");
		nextButton.setForeground(Color.WHITE);
		nextButton.setContentAreaFilled(false);
		nextButton.setBounds(995, 570, 54, 68);
		nextButton.addActionListener(al);
		nextButton.setVisible(false);
		this.add(nextButton);
		if(userteams.size() >= ((9* (pageNumber+1))+1)) {
			nextButton.setVisible(true);
		}
		JButton teambutton1 = new JButton("New button");
		teambutton1.setBounds(159, 110, 262, 164);

		
		JButton teambutton3 = new JButton("New button");
		teambutton3.setBounds(723, 110, 262, 164);

		
		JButton teambutton2 = new JButton("New button");
		teambutton2.setBounds(441, 110, 262, 164);

		
		JButton teambutton4 = new JButton("New button");
		teambutton4.setBounds(159, 292, 262, 164);

		
		JButton teambutton5 = new JButton("New button");
		teambutton5.setBounds(441, 292, 262, 164);

		
		JButton teambutton6 = new JButton("New button");
		teambutton6.setBounds(723, 292, 262, 164);

		
		JButton teambutton7 = new JButton("New button");
		teambutton7.setBounds(159, 474, 262, 164);

		
		JButton teambutton8 = new JButton("New button");
		teambutton8.setBounds(441, 474, 262, 164);


		
		JButton teambutton9 = new JButton("New button");
		teambutton9.setBounds(723, 474, 262, 164);

		
		this.buttons.add(teambutton1);
		this.buttons.add(teambutton2);
		this.buttons.add(teambutton3);
		this.buttons.add(teambutton4);
		this.buttons.add(teambutton5);
		this.buttons.add(teambutton6);
		this.buttons.add(teambutton7);
		this.buttons.add(teambutton8);
		this.buttons.add(teambutton9);
		
		this.add(joinbutton);
		this.add(createbutton);
		
		for(int i = 0; i < this.buttons.size(); i++) {
			this.add(this.buttons.get(i));
			this.buttons.get(i).setForeground(Color.WHITE);
			this.buttons.get(i).setFont(new Font("Lucida Calligraphy", Font.BOLD, 20));
			this.buttons.get(i).setContentAreaFilled(false);
//			this.buttons.get(i).setBackground(new Color(26,148,184));
			
//			this.buttons.get(i).setVisible(false);
			this.buttons.get(i).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			this.buttons.get(i).setBorder(border1);
			this.buttons.get(i).addActionListener(al);
			this.buttons.get(i).addMouseListener(ml);
			this.buttons.get(i).setVisible(false);
			
		}
		int size = userteams.size();
		if(userteams.size() > 8) {
			size = this.buttons.size();
		}
		for(int i = 0; i < size; i++) {
			this.buttons.get(i).setText(userteams.get(i).getName());
			this.buttons.get(i).setVisible(true);
		}
	
				
	}
	
	//This method is called on clicking next button, The buttons data is replaced with next available Teams in the Teams Array
	public void goToNextPage() {
		for(int i = 0; i < this.buttons.size(); i++) {
			this.buttons.get(i).setVisible(false);
		}
		
		int buttonindex = 0;
		for(int i = this.pageNumber*9; i < this.userteams.size() && buttonindex < 9; i++) {
			this.buttons.get(buttonindex).setText(this.userteams.get(i).getName());
			this.buttons.get(buttonindex).setVisible(true);
			buttonindex++;
		}
		
		if(userteams.size() < ((9* (pageNumber+1))+1)) {
			this.nextButton.setVisible(false);
		} 
		
	}

	
	
}

package com.ourteams.window;

	//ALL OF THE ICONS WERE MADE FROM https://www.ucraft.com/free-logo-maker .. Many thanks to the ucrafty.com

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.ourteams.backend.Student;
import com.ourteams.backend.Team;
import com.ourteams.backend.User;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JDesktopPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
/*
 * This is Main Frame of our Application
 * This is displayed when user signs in into the app.
 * The main frame consists of a buttons navigating to different panels and 
 * a current panel in which user is currently in 
 */

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel MainPanel;
	private JPanel signinPanel;
	private JPanel signupPanel;
	private JButton chatButton;
	private JButton assignmentButton;
	private JButton teamsButton;
	private JButton signOutButton;
	private JButton notificationButton;
	private JButton accountButton;
	private JPanel current;
	private JDesktopPane desktopPane;
	private User[] user = new User[1];
	private JTextField searchField;
	private JButton searchButton;

	public static void main(String[] args) {
		new MainWindow();
	}
	
	public MainWindow() {
		initialize();
	}

	private void initialize() {
		this.user[0] = new Student();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("OUR TEAMS");
		setBounds(100, 100, 1240, 768);
		setBackground(new Color(25, 111, 226));
		signinPanel = new SignInPanel(this);
		signupPanel = new SignUpPanel(this);
		setContentPane(signinPanel);
		this.setResizable(false);
		
		MainPanel = new JPanel(); //MainPanel = Opening panel
		MainPanel.setBackground(Color.BLACK);
		MainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		ActionListener al = new actionListener();
		MouseListener ml = new mouseListener();

		current = new AccountDetailPanel(this);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 42, 77, 687);
		desktopPane.setBackground(new Color(59, 58, 58));

		chatButton = new JButton();
		chatButton.setBackground(Color.LIGHT_GRAY);
		chatButton.setBounds(0, 11, 77, 77);
		chatButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chatButton.setIcon(new ImageIcon(MainWindow.class.getResource("/resources/chat1.png")));
		chatButton.setContentAreaFilled(false);
		chatButton.addActionListener(al);
		chatButton.addMouseListener(ml);
		chatButton.setBorder(new EmptyBorder(0,0,0,0));
		desktopPane.add(chatButton);
		
		notificationButton = new JButton();
		notificationButton.setBackground(Color.LIGHT_GRAY);
		notificationButton.setBounds(0, 105, 77, 77);
		notificationButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		notificationButton.setIcon(new ImageIcon(MainWindow.class.getResource("/resources/notif1.png")));
		notificationButton.setContentAreaFilled(false);
		notificationButton.setBorder(new EmptyBorder(0,0,0,0));
		notificationButton.addActionListener(al);
		notificationButton.addMouseListener(ml);
		desktopPane.add(notificationButton);
		
		teamsButton = new JButton();
		teamsButton.setBackground(Color.LIGHT_GRAY);
		teamsButton.setBounds(0, 197, 77, 77);
		teamsButton.addActionListener(al);
		teamsButton.setContentAreaFilled(false);
		teamsButton.setBorder(new EmptyBorder(0,0,0,0));
		teamsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		teamsButton.setIcon(new ImageIcon(MainWindow.class.getResource("/resources/teams1.png")));
		teamsButton.addMouseListener(ml);
		desktopPane.add(teamsButton);
		
		assignmentButton = new JButton();
		assignmentButton.setContentAreaFilled(false);
		assignmentButton.setBackground(Color.LIGHT_GRAY);
		assignmentButton.setBounds(0, 289, 77, 77);
		assignmentButton.addActionListener(al);
		assignmentButton.addMouseListener(ml);
		assignmentButton.setIcon(new ImageIcon(MainWindow.class.getResource("/resources/assign1.png")));
		assignmentButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		assignmentButton.setBorder(new EmptyBorder(0,0,0,0));
		desktopPane.add(assignmentButton);
		
		accountButton = new JButton();
		accountButton.setBackground(Color.LIGHT_GRAY);
		accountButton.setBounds(0, 383, 77, 77);
		accountButton.setContentAreaFilled(false);
		accountButton.setBorder(new EmptyBorder(0,0,0,0));
		accountButton.addActionListener(al);
		accountButton.addMouseListener(ml);
		accountButton.setIcon(new ImageIcon(MainWindow.class.getResource("/resources/account1.png")));
		accountButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		desktopPane.add(accountButton);
		
		signOutButton = new JButton();
		signOutButton.setBackground(Color.LIGHT_GRAY);
		signOutButton.setBounds(0, 586, 77, 66);
		signOutButton.addActionListener(al);
		signOutButton.addMouseListener(ml);
		signOutButton.setContentAreaFilled(false);
		signOutButton.setBorder(new EmptyBorder(0,0,0,0));
		signOutButton.setIcon(new ImageIcon(MainWindow.class.getResource("/resources/signout1.png")));
		signOutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		desktopPane.add(signOutButton);
		
		searchField = new JTextField();
		searchField.setBounds(248, 10, 639, 24);
		MainPanel.add(searchField);
		searchField.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		searchField.setBackground(new Color(33, 32, 32));
		searchField.setColumns(10);
		searchField.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 15));
		searchField.setForeground(Color.WHITE);
		searchField.setCaretColor(Color.WHITE);
		
		searchButton = new JButton("Search");
		searchButton.setBounds(897, 10, 89, 23);
		searchButton.addActionListener(al);
		searchButton.setBackground(new Color(33,32,32));
		searchButton.setForeground(Color.WHITE);
		searchButton.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 15));
		searchButton.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		searchButton.addMouseListener(ml);
		MainPanel.add(searchButton);
		
		MainPanel.setLayout(null);
		MainPanel.add(desktopPane);
		MainPanel.add(current);
		MainPanel.setOpaque(false);
		
		
		//the following action listener saves data before closing
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	User.updateUserData(user[0]);
		    }
		});
		
		this.setVisible(true);
		
	}
	

	class actionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent a) {
			//When sign out button is clicked user is directed to Sign in panel
			if(a.getSource().equals(signOutButton)) {
				gotoSigninPage();
			}
			//When assignment Button is clicked current Panel is replaced with Assignment Panel
			else if(a.getSource().equals(assignmentButton)) {
				changePanel(new AssignmentPanel(user[0]));
			}
			//When notification Button is clicked current Panel is replaced with Notification Panel
			else if(a.getSource().equals(notificationButton)) {
				changePanel(new NotificationsPanel(user[0]));
			}
			//When Account Button is clicked current Panel is replaced with account details Panel
			else if(a.getSource().equals(accountButton)) {
				changePanel(createnewAccountDetailpanel());
			}
			//When Chat Button is clicked current Panel is replaced with Chat Panel
			else if(a.getSource().equals(chatButton)) {
				changePanel(new ChatPanel(user[0], -1));
			}
			/*
			 * When search button is pressed then that user is searched from the file 
			 * if found a new dialog is opened with that user's data
			 * else a dialog is displayed with information that user was not found
			 */
			else if (a.getSource().equals(searchButton)){
				if (searchField.getText().length()!=0) {
				if (User.findUser(searchField.getText()) == null) {
						searchField.setText("");
						JOptionPane.showMessageDialog(null, "no such user found\n");
						}
					else {
						openSearchDialogBox(searchField.getText());
						searchField.setText("");
					}
				}
			}
			//When Teams Button is clicked current Panel is replaced with Teams Content Panel
			else if (a.getSource().equals(teamsButton)) {
				changePanel(new TeamsContentPane(user[0]));
			}
		
		}
	}
	
	//Mouse Listener for adding hover effects to buttons
	class mouseListener implements MouseListener{
		@Override
		public void mouseEntered(MouseEvent m) {
			if(m.getSource().equals(assignmentButton)){
				assignmentButton.setIcon(new ImageIcon(MainWindow.class.getResource("/resources/assign2.png")));
			}
			else if(m.getSource().equals(chatButton)) {
				chatButton.setIcon(new ImageIcon(MainWindow.class.getResource("/resources/chat2.png")));
			}
			else if(m.getSource().equals(teamsButton)) {
				teamsButton.setIcon(new ImageIcon(MainWindow.class.getResource("/resources/teams2.png")));
			}
			else if(m.getSource().equals(notificationButton)) {
				notificationButton.setIcon(new ImageIcon(MainWindow.class.getResource("/resources/notif2.png")));
			}
			else if(m.getSource().equals(signOutButton)) {
				signOutButton.setIcon(new ImageIcon(MainWindow.class.getResource("/resources/signout2.png")));
			}
			else if(m.getSource().equals(accountButton)) {
				accountButton.setIcon(new ImageIcon(MainWindow.class.getResource("/resources/account2.png")));
			}
			else if(m.getSource().equals(searchButton)) {
				searchButton.setBorder(new LineBorder(Color.RED));
			}
			
		}

		@Override
		public void mouseExited(MouseEvent m) {
			if(m.getSource().equals(assignmentButton)){
				assignmentButton.setIcon(new ImageIcon(MainWindow.class.getResource("/resources/assign1.png")));
			}		
			else if(m.getSource().equals(chatButton)) {
				chatButton.setIcon(new ImageIcon(MainWindow.class.getResource("/resources/chat1.png")));
			}
			else if(m.getSource().equals(teamsButton)) {
				teamsButton.setIcon(new ImageIcon(MainWindow.class.getResource("/resources/teams1.png")));
			}
			else if(m.getSource().equals(notificationButton)) {
				notificationButton.setIcon(new ImageIcon(MainWindow.class.getResource("/resources/notif1.png")));
			}
			else if(m.getSource().equals(signOutButton)) {
				signOutButton.setIcon(new ImageIcon(MainWindow.class.getResource("/resources/signout1.png")));
			}
			else if(m.getSource().equals(accountButton)) {
				accountButton.setIcon(new ImageIcon(MainWindow.class.getResource("/resources/account1.png")));
			}
			else if(m.getSource().equals(searchButton)) {
				searchButton.setBorder(new LineBorder(Color.DARK_GRAY, 2));
			}
	
		}

		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0){}
		public void mouseClicked(MouseEvent arg0) {}

		
	}

	/*
	 * This method receives a JPanel as an arguments
	 * Removes the previous Panel and replaces it with new JPanel that is passed as argument
	 */
	private void changePanel(JPanel panel) {
		MainPanel.remove(current);
		current = panel;
		MainPanel.add(panel);
		MainPanel.repaint();
		MainPanel.revalidate();
	}

	//Getters for data members
	private JPanel createnewAccountDetailpanel() {
		return new AccountDetailPanel(this);
	}

	public JPanel getMainPanel() {
		return MainPanel;
	}

	public JPanel getSigninPanel() {
		return signinPanel;
	}

	public JPanel getSignupPanel() {
		return signupPanel;
	}

	public User getUser() {
		return user[0];
	}
	
	//This method updates the Account Details Panel
	public void updateAccountDetailsPanel() {
		this.changePanel(new AccountDetailPanel(this));
	}
	
	//This method opens a searched user's Dialog box
	public void openSearchDialogBox(String text) {
		new searchDialog(this, text);
	}
	
	//This method directly opens a chat of a user at specific index in contacts
	public void openChatsPanel(int defaultcontactindex) {
		changePanel(new ChatPanel(user[0], defaultcontactindex));
	}
	
	//This method redirects user to sign in panel
	public void gotoSigninPage() {
		User.updateUserData(user[0]);
		setContentPane(signinPanel);
		invalidate();
		validate();
	}

	//setter for the user ( data members ) this is set when User logs in
	public void setUser(User user) {
		this.user[0] = user;
	}
	
	//grab the updated data from the file...
	//This method loops through teams in the file and user's teams. and updates all the user's teams
	public void updateUserTeams() {
		ArrayList<Team> teams = Team.readAllDataFromFile();
		for(int i = 0; i < teams.size(); i++) {
			for(int j = 0; j < this.user[0].getTeams().size(); j++) {
				if(teams.get(i).getJoincode().equals(this.user[0].getTeams().get(j).getJoincode())) {
					this.user[0].getTeams().set(j, teams.get(i));
				}
			}
		}
	}

	

}

package com.ourteams.window;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.ourteams.backend.User;


public class SignInPanel extends JPanel{
	/**
	 * This Panel is displayed when application is launched
	 * asks for user's email and password to sign into his/her account
	 * has a button for signing up a new account
	 */
	private static final long serialVersionUID = 1L;
	private JButton signinButton;
	private JButton signupButton;
	private JTextField emailField;
	private JPasswordField passField;
	private MainWindow frame;
	private JLabel errorLabel;
	private LineBorder redborder = new LineBorder(Color.RED);
	private LineBorder grayborder = new LineBorder(Color.GRAY);
	
	public SignInPanel(MainWindow frame) {
		this.frame = frame;
		initialize();
	}

	private void initialize() {
		this.setBounds(0, 0, 1224, 729);
		this.setLayout(null);
		
		JLabel passlabel = new JLabel("Password:");
		passlabel.setForeground(Color.WHITE);
		passlabel.setFont(new Font("MV Boli", Font.PLAIN, 18));
		passlabel.setBounds(445, 398, 95, 33);
		this.add(passlabel);
		
		JLabel labelemail = new JLabel("Email:");
		labelemail.setFont(new Font("MV Boli", Font.PLAIN, 18));
		labelemail.setForeground(Color.WHITE);
		labelemail.setBounds(445, 338, 95, 33);
		this.add(labelemail);
		
		errorLabel = new JLabel("email or password incorret !");
		errorLabel.setVisible(false);
		errorLabel.setForeground(Color.RED);
		errorLabel.setFont(new Font("MV Boli", Font.PLAIN, 16));
		errorLabel.setBounds(501, 527, 223, 33);
		this.add(errorLabel);
		
		MouseListener ml = new mouseListener();
		ActionListener al = new actionlistener();
		
		signinButton = new JButton("Sign In");	
		signinButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		signinButton.setForeground(Color.WHITE);
		signinButton.setFont(new Font("MV Boli", Font.PLAIN, 18));
		signinButton.setContentAreaFilled(false);
		signinButton.setBorder(new LineBorder(Color.GRAY));
		signinButton.setBackground(Color.WHITE);
		signinButton.setBounds(494, 466, 104, 33);
		signinButton.addMouseListener(ml);
		signinButton.addActionListener(al);
		this.add(signinButton);
		
		signupButton = new JButton("Sign Up");
		signupButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		signupButton.setBorder(new LineBorder(Color.GRAY));
		signupButton.setForeground(Color.WHITE);
		signupButton.setFont(new Font("MV Boli", Font.PLAIN, 18));
		signupButton.setContentAreaFilled(false);
		signupButton.setBackground(Color.WHITE);
		signupButton.setBounds(620, 466, 104, 33);
		signupButton.addMouseListener(ml);
		signupButton.addActionListener(al);
		this.add(signupButton);
		
		passField = new JPasswordField();
		passField.setFont(new Font("MV Boli", Font.PLAIN, 14));
		passField.setCaretColor(Color.RED);
		passField.setToolTipText("Password");
		passField.setForeground(Color.WHITE);
		passField.setBackground(Color.DARK_GRAY);
		passField.setBounds(550, 400, 230, 33);
		passField.addMouseListener(ml);
		this.add(passField);
		
		emailField = new JTextField();
		emailField.setCaretColor(Color.RED);
		emailField.setFont(new Font("MV Boli", Font.PLAIN, 16));
		emailField.setForeground(Color.WHITE);
		emailField.setBackground(Color.DARK_GRAY);
		emailField.setBounds(550, 337, 230, 33);
		emailField.addMouseListener(ml);
		this.add(emailField);
		emailField.setColumns(10);
		
		JLabel logolabel = new JLabel("");
		logolabel.setIcon(new ImageIcon(SignUpPanel.class.getResource("/resources/logo.png")));
		logolabel.setBounds(356, 87, 575, 228);
		this.add(logolabel);
		
		JLabel backgroundlabel = new JLabel("");
		backgroundlabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		backgroundlabel.setIcon(new ImageIcon(SignUpPanel.class.getResource("/resources/galaxy.jpg")));
		backgroundlabel.setBounds(0, 0, 1224, 729);
		this.add(backgroundlabel);
	}
	
	
	public JLabel getErrorLabel() {
		return errorLabel;
	}

	public void setErrorLabel(JLabel errorLabel) {
		this.errorLabel = errorLabel;
	}

	class actionlistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent m) {
			/*
			 * if user clicks sign in button then performs sign in method
			 */
			if (m.getSource().equals(signinButton)) {
				signinButton.setBorder(grayborder);	
				SignIn();
				
			}
			else if (m.getSource().equals(signupButton)) {
				errorLabel.setVisible(false);
				signupButton.setBorder(grayborder);	
				frame.setContentPane(frame.getSignupPanel());
				frame.invalidate();
				frame.validate();
			}
			
		}

		/*
		 * This method searches the record if that email and password is found
		 * if found then signs in. 
		 * else displays an error message
		 */
		private void SignIn() {
			User user = User.findUser(emailField.getText(), String.valueOf(passField.getPassword()));
			if(user == null) {
				errorLabel.setVisible(true);
				emailField.setText("");
				passField.setText("");
			}
			else {
				emailField.setText("");
				passField.setText("");
				errorLabel.setVisible(false);
				frame.setUser(user);
				frame.updateUserTeams();
				frame.updateAccountDetailsPanel();
				frame.setContentPane(frame.getMainPanel());
				frame.invalidate();
				frame.validate();
			}
			
		}
		
	}
	
	//Mouse Listener for hover over button effects
	class mouseListener implements MouseListener{
		
		@Override
		public void mouseEntered(MouseEvent m) {
			if (m.getSource().equals(signupButton)) {		
				signupButton.setBorder(redborder);
			}
			else if (m.getSource().equals(signinButton)) {
				signinButton.setBorder(redborder);
			}
			else if(m.getSource().equals(emailField)) {
				emailField.setBorder(redborder);
			}
			else if(m.getSource().equals(passField)) {
				passField.setBorder(redborder);
			}
			
		}

		@Override
		public void mouseExited(MouseEvent m) {
			if (m.getSource().equals(signupButton)) {		
				signupButton.setBorder(grayborder);
			}
			else if (m.getSource().equals(signinButton)) {
				signinButton.setBorder(grayborder);
			}
			else if(m.getSource().equals(emailField)) {
				emailField.setBorder(grayborder);
			}
			else if(m.getSource().equals(passField)) {
				passField.setBorder(grayborder);
			}
		}
		
		@Override
		public void mouseClicked(MouseEvent m) {}
		@Override
		public void mousePressed(MouseEvent arg0) {}
		@Override
		public void mouseReleased(MouseEvent arg0) {}
		
	}
		
}

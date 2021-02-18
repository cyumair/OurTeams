package com.ourteams.window;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.ourteams.backend.Faculty;
import com.ourteams.backend.Student;
import com.ourteams.backend.User;


public class SignUpPanel extends JPanel{
	/**
	 * This Panel has a form for sign up details for the user
	 * Creates a new user object if details are entered correctly
	 */
	private static final long serialVersionUID = 1L;
	private JButton signupButton;
	private JTextField emailField;
	private JPasswordField passField;
	private JRadioButton radiobtn1;
	private JRadioButton radiobtn2;
	private JTextField namefield;
	private JLabel errorLabel;
	private MainWindow frame;
	private ButtonGroup radioGroup;
	private JComboBox<String> departmentBox;
	private JComboBox<String> semesterBox;
	private JButton signInButton; 
	private LineBorder redborder= new LineBorder(Color.RED);
	private LineBorder grayborder = new LineBorder(Color.GRAY);
	
	public SignUpPanel(MainWindow frame) {
		this.frame = frame;
		initialize();
	}

	private void initialize() {
		this.setBounds(0, 0, 1224, 729);
		this.setLayout(null);
		
		
		MouseListener ml = new mouseListener();
		
		JLabel detaillabel = new JLabel(" Register New Our Teams Account");
		detaillabel.setForeground(Color.RED);
		detaillabel.setFont(new Font("MV Boli", Font.PLAIN, 16));
		detaillabel.setBounds(465, 267, 294, 33);
		this.add(detaillabel);
		
		JLabel namelabel = new JLabel("Name:");
		namelabel.setForeground(Color.WHITE);
		namelabel.setFont(new Font("MV Boli", Font.PLAIN, 18));
		namelabel.setBounds(412, 325, 95, 33);
		this.add(namelabel);
		
		
		JLabel passlabel = new JLabel("Password:");
		passlabel.setForeground(Color.WHITE);
		passlabel.setFont(new Font("MV Boli", Font.PLAIN, 18));
		passlabel.setBounds(412, 412, 95, 33);
		this.add(passlabel);
		
		JLabel labelemail = new JLabel("Email:");
		labelemail.setFont(new Font("MV Boli", Font.PLAIN, 18));
		labelemail.setForeground(Color.WHITE);
		labelemail.setBounds(412, 368, 95, 33);
		this.add(labelemail);
		
		JLabel departmentlabel = new JLabel("Department:");
		departmentlabel.setForeground(Color.WHITE);
		departmentlabel.setFont(new Font("MV Boli", Font.PLAIN, 18));
		departmentlabel.setBounds(412, 456, 123, 33);
		this.add(departmentlabel);
		
		JLabel semesterlabel = new JLabel("Semester:");
		semesterlabel.setForeground(Color.WHITE);
		semesterlabel.setFont(new Font("MV Boli", Font.PLAIN, 18));
		semesterlabel.setBounds(412, 500, 95, 33);
		this.add(semesterlabel);
		
	

		namefield = new JTextField();
		namefield.setForeground(Color.WHITE);
		namefield.setFont(new Font("MV Boli", Font.PLAIN, 16));
		namefield.setColumns(10);
		namefield.setCaretColor(Color.RED);
		namefield.setBackground(Color.DARK_GRAY);
		namefield.setBounds(533, 325, 264, 33);
		namefield.addMouseListener(ml);
		this.add(namefield);
		
		emailField = new JTextField();
		emailField.setCaretColor(Color.RED);
		emailField.setFont(new Font("MV Boli", Font.PLAIN, 16));
		emailField.setForeground(Color.WHITE);
		emailField.setBackground(Color.DARK_GRAY);
		emailField.setBounds(533, 369, 264, 33);
		emailField.addMouseListener(ml);
		this.add(emailField);
		emailField.setColumns(10);
		
		passField = new JPasswordField();
		passField.setFont(new Font("MV Boli", Font.PLAIN, 14));
		passField.setCaretColor(Color.RED);
		passField.setToolTipText("Password");
		passField.setForeground(Color.WHITE);
		passField.setBackground(Color.DARK_GRAY);
		passField.setBounds(533, 413, 264, 33);
		passField.addMouseListener(ml);
		this.add(passField);
		
		
		String[] semesters = {"1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th", "9th", "10th", "11th", "12th"};
		String[] departments = {"Computer Science", "Electrical Engineering", "Physics", "Bio Sciences", "Business Administration", "Natural Sciences", "Humanitites",
				"Mechanical Eningeering", "Psychology", "Mathematics", "Architecture", "Other"};
		
		departmentBox = new JComboBox<String>(departments);
		departmentBox.setForeground(Color.WHITE);
		departmentBox.setFont(new Font("MV Boli", Font.PLAIN, 14));
		departmentBox.setBackground(Color.DARK_GRAY);
		departmentBox.setBounds(533, 463, 264, 22);
		this.add(departmentBox);
		
		semesterBox = new JComboBox<String>(semesters);
		semesterBox.setForeground(Color.WHITE);
		semesterBox.setFont(new Font("MV Boli", Font.PLAIN, 14));
		semesterBox.setBackground(Color.DARK_GRAY);
		semesterBox.setBounds(533, 500, 264, 22);
		semesterBox.setEnabled(false);;
		this.add(semesterBox);
	
		ItemListener il = new itemlistener();
		radiobtn1 = new JRadioButton("Student");
		radiobtn1.setFont(new Font("MV Boli", Font.PLAIN, 16));
		radiobtn1.setForeground(Color.WHITE);
		radiobtn1.setContentAreaFilled(false);
		radiobtn1.setBounds(506, 546, 109, 23);
		radiobtn1.addMouseListener(ml);
		radiobtn1.addItemListener(il);
		this.add(radiobtn1);
		
		radiobtn2 = new JRadioButton("Faculty");
		radiobtn2.setForeground(Color.WHITE);
		radiobtn2.setFont(new Font("MV Boli", Font.PLAIN, 16));
		radiobtn2.setContentAreaFilled(false);
		radiobtn2.setBounds(607, 546, 109, 23);
		radiobtn2.addItemListener(il);
		radiobtn2.addMouseListener(ml);
		this.add(radiobtn2);
		
		radioGroup = new ButtonGroup();
		radioGroup.add(radiobtn1);
		radioGroup.add(radiobtn2);
	
		signInButton = new JButton("Sign In");
		signInButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		signInButton.setBorder(new LineBorder(Color.GRAY));
		signInButton.setForeground(Color.WHITE);
		signInButton.setFont(new Font("MV Boli", Font.PLAIN, 18));
		signInButton.setContentAreaFilled(false);
		signInButton.setBackground(Color.WHITE);
		signInButton.setBounds(492, 595, 104, 33);
		signInButton.addActionListener(new actionlistener());
		this.add(signInButton);
		
		
		signupButton = new JButton("Register");
		signupButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		signupButton.setBorder(new LineBorder(Color.GRAY));
		signupButton.setForeground(Color.WHITE);
		signupButton.setFont(new Font("MV Boli", Font.PLAIN, 18));
		signupButton.setContentAreaFilled(false);
		signupButton.setBackground(Color.WHITE);
		signupButton.setBounds(612, 595, 104, 33);
		signupButton.addMouseListener(ml);
		signupButton.addActionListener(new actionlistener());
		this.add(signupButton);
		
		errorLabel = new JLabel("");
		errorLabel.setForeground(Color.RED);
		errorLabel.setFont(new Font("MV Boli", Font.PLAIN, 16));
		errorLabel.setBounds(412, 651, 399, 33);
		this.add(errorLabel);
		
		JLabel logolabel = new JLabel("");
		logolabel.setIcon(new ImageIcon(SignUpPanel.class.getResource("/resources/logo.png")));
		logolabel.setBounds(355, 57, 575, 228);
		this.add(logolabel);
		
		JLabel backgroundlabel = new JLabel("");
		backgroundlabel.setIcon(new ImageIcon(SignUpPanel.class.getResource("/resources/galaxy.jpg")));
		backgroundlabel.setBounds(0, 0, 1224, 729);
		this.add(backgroundlabel);
	}
	
	class actionlistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent m) {
			//When sign up button is clicked register for account method is called
			if (m.getSource().equals(signupButton)) {
				signupButton.setBorder(new LineBorder(Color.GRAY));	
				registerForAccount();
			}
			
			//When sign in button is pressed the main panel is changed to sign in panel
			else if(m.getSource().equals(signInButton)){
				frame.setContentPane(frame.getSigninPanel());
				frame.invalidate();
				frame.validate();
			}
		}

		/*
		 * This method stores the details the user has entered
		 * Checks if all the data entered is valid
		 * Creates a new user if its valid
		 * else displays error message
		 */
		private void registerForAccount() {
			String name = namefield.getText();
			if(!name.isEmpty() && Character.isLetter(name.charAt(0))){
				String email = emailField.getText();
				if(User.isValidEmail(email)) {
					if(User.hasEmailAlreadyBeenUser(email)) {
						emailField.setText("");
						errorLabel.setText("      This email has already been Registered");
					}
					else {
						errorLabel.setText("");
						String password = String.valueOf(passField.getPassword());
						if(password.length() > 7) {
							if(radiobtn1.isSelected() || radiobtn2.isSelected()) {
								errorLabel.setText("");	
								String department = (String) departmentBox.getSelectedItem();
								if(radiobtn1.isSelected()) {
									String semester = (String) semesterBox.getSelectedItem();
									Student student = new Student(name, email, password, department, semester);
									User.AddUser(student);
								}
								else{
									Faculty faculty = new Faculty(name, email, password, department);	
									User.AddUser(faculty);
								}
								errorLabel.setText("             Registered Successfully");
								namefield.setText("");
								emailField.setText("");
								passField.setText("");
								radiobtn1.setSelected(false);
								radiobtn1.setSelected(false);
							}
							else{
								errorLabel.setText("          Please select Account-Type");
							}
						}
						else {
							passField.setText("");
							errorLabel.setText("    Password length must be greater than 7");
						}
					}
				}
				else {
					errorLabel.setText("        Please enter valid email Address");
					emailField.setText("");
				}
			}
			else {
				errorLabel.setText("       Name must start with an Alphabet");
				namefield.setText("");
			}
		
		}
		
	}
	
	/*
	 * Item listener for radio buttons
	 * If faculty is selected then disable the semester box as faculty member dont have semeseter
	 */
	class itemlistener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent a) {
			if(radiobtn1.isSelected()) {
				semesterBox.setEnabled(true);
			}
			else {
				semesterBox.setEnabled(false);
			}
			
		}
		
	}
	
	//Mouse Listeners for hover over button effects
	class mouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent m) {}

		@Override
		public void mouseEntered(MouseEvent m) {
			if (m.getSource().equals(signupButton)) {
				signupButton.setBorder(redborder);	
			}
			else if (m.getSource().equals(namefield)) {
				namefield.setBorder(redborder);	
			}
			else if (m.getSource().equals(emailField)) {
				emailField.setBorder(redborder);	
			}
			else if (m.getSource().equals(passField)) {
				passField.setBorder(redborder);	
			}
		}

		@Override
		public void mouseExited(MouseEvent m) {
			if (m.getSource().equals(signupButton)) {
				signupButton.setBorder(grayborder);	
			}
			else if (m.getSource().equals(namefield)) {
				namefield.setBorder(grayborder);	
			}
			else if (m.getSource().equals(emailField)) {
				emailField.setBorder(grayborder);	
			}
			else if (m.getSource().equals(passField)) {
				passField.setBorder(grayborder);	
			}

		}

		@Override
		public void mousePressed(MouseEvent arg0) {}
		@Override
		public void mouseReleased(MouseEvent arg0) {}
		
	}
		
}

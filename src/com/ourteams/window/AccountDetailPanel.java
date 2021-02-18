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

import com.ourteams.backend.Student;
import com.ourteams.backend.User;

public class AccountDetailPanel extends JPanel{

	/**
	 * This panel displays Account details of the current user
	 * A user can edit details or delete the account with buttons on this Panel
	 */
	private static final long serialVersionUID = 1L;
	private JButton updateButton;
	private JLabel username;
	private JLabel emaillabel;
	private JLabel typelabel;
	private JLabel useremail;
	private JLabel usertype;
	private JLabel userdepartment;
	private JLabel usersemeter;
	private JButton deleteButton;
	private MainWindow frame;
	private User user;
	private LineBorder grayborder = new LineBorder(Color.GRAY, 2);
	private LineBorder redborder = new LineBorder(Color.RED, 1);
	
	public AccountDetailPanel(MainWindow frame) {
		this.frame = frame;
		this.user = frame.getUser();
		this.setBackground(new Color(33, 32, 32));
		initialize();
	}

	private void initialize() {
		this.setLayout(null);
		this.setBounds(75, 42, 1149, 687);
		JLabel accountlabel = new JLabel("Account Details");
		accountlabel.setBackground(Color.BLACK);
		accountlabel.setBounds(63, 44, 338, 48);
		accountlabel.setFont(new Font("Lucida Calligraphy", Font.BOLD, 20));
		accountlabel.setForeground(Color.LIGHT_GRAY);
		this.add(accountlabel);
		
		ActionListener al = new ActionHandler();
		MouseListener ml = new MouseHandler();
		
		updateButton = new JButton("Update");
		updateButton.addActionListener(al);
		updateButton.addMouseListener(ml);
		updateButton.setForeground(Color.LIGHT_GRAY);
		updateButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		updateButton.setContentAreaFilled(false);
		updateButton.setBorder(grayborder);
		updateButton.setBackground(Color.LIGHT_GRAY);
		updateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		updateButton.setBounds(887, 56, 98, 28);
		this.add(updateButton);
		
				
		deleteButton = new JButton("Delete");
		deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		deleteButton.setForeground(Color.LIGHT_GRAY);
		deleteButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		deleteButton.setContentAreaFilled(false);
		deleteButton.setBorder(grayborder );
		deleteButton.setBackground(Color.LIGHT_GRAY);
		deleteButton.setBounds(995, 56, 98, 28);
		deleteButton.addActionListener(al);
		deleteButton.addMouseListener(ml);
		this.add(deleteButton);
		
		JLabel namelabel = new JLabel("Name:");
		namelabel.setForeground(Color.LIGHT_GRAY);
		namelabel.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18));
		namelabel.setBackground(Color.BLACK);
		namelabel.setBounds(340, 156, 137, 37);
		this.add(namelabel);
		
		username = new JLabel();
		username.setText(this.user.getName());
		username.setForeground(Color.LIGHT_GRAY);
		username.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18));
		username.setBackground(Color.BLACK);
		username.setBounds(524, 156, 357, 37);
		this.add(username);
		
		emaillabel = new JLabel("Email:");
		emaillabel.setForeground(Color.LIGHT_GRAY);
		emaillabel.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18));
		emaillabel.setBackground(Color.BLACK);
		emaillabel.setBounds(340, 219, 169, 37);
		this.add(emaillabel);
		
		useremail = new JLabel();
		useremail.setText(this.user.getEmail());
		useremail.setForeground(Color.LIGHT_GRAY);
		useremail.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18));
		useremail.setBackground(Color.BLACK);
		useremail.setBounds(524, 219, 357, 37);
		this.add(useremail);
		
		typelabel = new JLabel("Type:");
		typelabel.setForeground(Color.LIGHT_GRAY);
		typelabel.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18));
		typelabel.setBackground(Color.BLACK);
		typelabel.setBounds(340, 281, 188, 37);
		this.add(typelabel);

		usertype = new JLabel();		
		usertype.setForeground(Color.LIGHT_GRAY);
		usertype.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18));
		usertype.setBackground(Color.BLACK);
		usertype.setBounds(524, 281, 357, 37);
		this.add(usertype);
		
		JLabel departmentlabel = new JLabel("Department:");
		departmentlabel.setForeground(Color.LIGHT_GRAY);
		departmentlabel.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18));
		departmentlabel.setBackground(Color.BLACK);
		departmentlabel.setBounds(340, 341, 137, 37);
		this.add(departmentlabel);
		
		JLabel labelsemester = new JLabel("Semester:");
		labelsemester.setForeground(Color.LIGHT_GRAY);
		labelsemester.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18));
		labelsemester.setBackground(Color.BLACK);
		labelsemester.setBounds(340, 403, 137, 37);
		this.add(labelsemester);
		
		userdepartment = new JLabel();
		userdepartment.setText(user.getDepartment());
		userdepartment.setForeground(Color.LIGHT_GRAY);
		userdepartment.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18));
		userdepartment.setBackground(Color.BLACK);
		userdepartment.setBounds(524, 341, 357, 37);
		this.add(userdepartment);
		
		usersemeter = new JLabel("");
		usersemeter.setForeground(Color.LIGHT_GRAY);
		usersemeter.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18));
		usersemeter.setBackground(Color.BLACK);
		usersemeter.setBounds(524, 403, 357, 37);
		this.add(usersemeter);
		
		
		if(this.user instanceof Student) {
			usertype.setText("Student");
			Student student = (Student)user;
			usersemeter.setText(student.getSemester());
		}
		else {
			labelsemester.setVisible(false);
			usertype.setText("Faculty");
		}
	}
	
	class ActionHandler implements ActionListener{

		@Override
		
		public void actionPerformed(ActionEvent a) {
			//if user clicks on update button then open a new frame(dialog) that updates user details
			if(a.getSource().equals(updateButton)) {
				new UpdateUser(frame);
			}
			
			/*
			 * if user clicks on delete button, open an input dialog
			 * confirm password from the user and delete the account
			 * after deleting account user will be returned to login panel
			 */
			else if(a.getSource().equals(deleteButton)) {
				String pas=JOptionPane.showInputDialog("enter password to delete account");
				if(pas != null) {
		                if(pas.equals(user.getPassword())){
		                	user.deleteAccount();    
		                    JOptionPane.showMessageDialog(null, "deleted successfully");
		                    frame.gotoSigninPage();
		                }
		                else {
		                    JOptionPane.showMessageDialog(null, "Wrong Password");
		                }
				}
			}
			
		}
		
	}
	
	//Mouse listeners for hover over effects on buttons.
	class MouseHandler implements MouseListener{

		@Override
		public void mouseEntered(MouseEvent m) {
			if(m.getSource().equals(updateButton)) {
				updateButton.setBorder(redborder);
			}
			else if(m.getSource().equals(deleteButton)){
				deleteButton.setBorder(redborder);
			}
			
		}

		@Override
		public void mouseExited(MouseEvent m) {
			if(m.getSource().equals(updateButton)) {
				updateButton.setBorder(grayborder);
			}
			else if(m.getSource().equals(deleteButton)){
				deleteButton.setBorder(grayborder);
			}
			
		}

		public void mouseClicked(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
		
	}
	
	
}


package com.ourteams.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import com.ourteams.backend.Team;
import com.ourteams.backend.User;

public class PostsPane extends JPanel{
	/**
	 * This panel displays posts in the Team
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private JTextPane textPane;
	private JTextField textField;
	private JButton postButton;
	private User user;
	private Team team;
	
	public PostsPane(Team team, User user) {
		this.team = team;
		this.user = user;
		initialize();
//		System.out.println("heelo");
	}

	private void initialize() {
		
		this.setBounds(75, 70, 1009, 592);
		this.setBackground(Color.BLACK);
		this.setLayout(null);
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		scrollPane.setBounds(10, 11, 989, 535);
		
		textField = new JTextField();
		textField.setBounds(10, 552, 865, 27);
		textField.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 15));
		textField.setBackground(Color.DARK_GRAY);
		textField.setCaretColor(Color.WHITE);
		textField.setForeground(Color.WHITE);
		textField.setColumns(10);
		
		postButton = new JButton("Post");
		postButton.setBounds(885, 553, 114, 27);
		postButton.setForeground(Color.WHITE);
		postButton.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 13));
		postButton.setContentAreaFilled(false);
		postButton.addActionListener(new actionlistener());
		
		textPane = new JTextPane();
		textPane.setFont(new Font("Lucida Fax", Font.PLAIN, 16));
		textPane.setEditable(false);
		textPane.setCaretColor(Color.WHITE);
		textPane.setText("");
		
		for(int i = 0 ; i < this.team.getPosts().size(); i++) {
			textPane.setText(textPane.getText() + "\n" + this.team.getPosts().get(i));
		}
		
		textPane.setForeground(new Color(124, 252, 0));
		textPane.setBackground(Color.DARK_GRAY);
		scrollPane.setViewportView(textPane);
		
		this.add(textField);
		this.add(postButton);
		this.add(scrollPane);
		
		
	}
	
	class actionlistener implements ActionListener {
	@Override
		public void actionPerformed(ActionEvent a) {
		//When post button is pressed the text form text field is inserted into textPane on the screen and is added to Team's posts by calling post message method
			if(a.getSource().equals(postButton)) {
				if(!textField.getText().equals("")) {
					String text = user.getName() + ": " + textField.getText();
					user.postMessage(team, text);
					textField.setText("");
					textPane.setText(textPane.getText() + "\n" + text);
				}
			}
		
		}
	}
}
	
	

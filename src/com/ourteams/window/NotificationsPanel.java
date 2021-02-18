package com.ourteams.window;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import com.ourteams.backend.User;

public class NotificationsPanel extends JPanel{

	/**
	 * This panel displays notifications on the Screen by simply looping through User's Notifications Array.
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private JTextPane textPane;
	private JLabel notificationlabel;
	public NotificationsPanel(User user) {
		this.user = user;
		initialize();
	}

	private void initialize() {
		this.setLayout(null);
		this.setBounds(75, 42, 1149, 687);
		this.setBackground(new Color(33, 32, 32));
		
		notificationlabel = new JLabel("Notifications");
		notificationlabel.setBounds(30, 31, 192, 35);
		notificationlabel.setFont(new Font("Monotype Corsiva", Font.BOLD, 23));
		notificationlabel.setForeground(new Color(255, 255, 255));
		this.add(notificationlabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(70, 86, 1013, 556);
		scrollPane.setBorder(new LineBorder(new Color(33, 32, 32)));
		this.add(scrollPane);
		
		textPane = new JTextPane();
		textPane.setBackground(new Color(33, 32, 32));
		textPane.setForeground(Color.RED);
		textPane.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 15));
		for(int i = user.getNotifications().size() - 1; i >= 0; i--){
			String notification = user.getNotifications().get(i);
			textPane.setText(textPane.getText() + notification + "\n------------"
					+ "---------------------------------------------------------------------------------------------\n");
		}
		
		scrollPane.setViewportView(textPane);
		
		
	}
}

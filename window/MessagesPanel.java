package com.ourteams.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import com.ourteams.backend.User;

public class MessagesPanel extends JPanel{

	/**
	 * This Panel displays the messages of panel and lets you chat with the contact
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JButton sendButton;
	private JTextPane textPane;
	private User sender;
	private int contactindex;
	public MessagesPanel(User sender, int contactindex) {
		this.sender = sender;
		this.contactindex = contactindex;
		initialize();
	}

	private void initialize() {
		
		this.setBackground(Color.DARK_GRAY);
		this.setBounds(328, 51, 811, 625);
		this.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 791, 556);
		scrollPane.setBorder(new LineBorder(new Color(33,32,32),2));
		this.add(scrollPane);
		
		textPane = new JTextPane();
		textPane.setCaretColor(Color.WHITE);
		textPane.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 14));
		textPane.setForeground(Color.CYAN);
		textPane.setBackground(new Color(33,32,32));
		scrollPane.setViewportView(textPane);
		
		textField = new JTextField();
		textField.setCaretColor(Color.WHITE);
		textField.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
		textField.setBackground(Color.DARK_GRAY);
		textField.setForeground(Color.CYAN);
		textField.setBorder(new LineBorder(Color.GRAY, 2));
		textField.setBounds(10, 574, 689, 39);
		this.add(textField);
		textField.setColumns(10);
		
		for(String message: sender.getChats().get(contactindex).getMessages()) {
			textPane.setText(textPane.getText() + message + "\n---------------------------- \n");
		}
		
		sendButton = new JButton("Send");
		sendButton.setForeground(Color.WHITE);
		sendButton.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 13));
		sendButton.setBackground(Color.DARK_GRAY);
		sendButton.setBounds(709, 574, 92, 39);
		sendButton.addActionListener(new actionlistener());
		sendButton.setBorder(new LineBorder(Color.GRAY, 2));
		sendButton.addMouseListener(new mouselistener());
		this.add(sendButton);
		
		
	}
	
	class actionlistener implements ActionListener{
		
		@Override
		//When send button is clicked the test entered in the text field is sent to the contact by calling send message method 
		public void actionPerformed(ActionEvent a) {
			textPane.setText(textPane.getText() +sender.getName() + ": " + textField.getText() + "\n--------------------------\n");
			sender.sendMessage(contactindex, sender.getName() + ": " + textField.getText());
			textField.setText("");
		}
	}
	
	//Mouse Listener for hover effects for buttons
	class mouselistener implements MouseListener{

		@Override
		public void mouseEntered(MouseEvent m) {
			sendButton.setBorder(new LineBorder(Color.RED));
		}

		@Override
		public void mouseExited(MouseEvent m) {
			sendButton.setBorder(new LineBorder(Color.GRAY, 2));
			
		}

		public void mouseClicked(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
		
	}
}

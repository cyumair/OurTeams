package com.ourteams.window;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.ourteams.backend.User;

public class ChatPanel extends JPanel{

	
	/**
	 * The chat Panel has contact buttons, and a messaging panel
	 */
	private static final long serialVersionUID = 1L;
	private JLabel errorLabel;
	private JButton loadmoreButton;
	private JPanel messagePanel;
	private CompoundBorder grayPaddingborder;
	private CompoundBorder redPaddingborder;
	private CompoundBorder greenPaddingborder;
	private CompoundBorder bluePaddingborder;
	private User user;
	private int defaultcontactindex;
	private ArrayList<JButton> contactButtons = new ArrayList<JButton>();
	private ArrayList<User> contacts = new ArrayList<User>();
	private ArrayList<ArrayList<String>> messages = new ArrayList<ArrayList<String>>();
	private int pageNumber;
	
	public ChatPanel(User user, int defaultcontactindex) {
		this.user = user;
		this.defaultcontactindex = defaultcontactindex;
		initialize();
	}

	private void initialize() {
		
		this.setBackground(new Color(45,44,44));
		this.setBounds(75, 42, 1149, 687);
		this.setLayout(null);
		
		JLabel chatlabel = new JLabel("Chats");
		chatlabel.setBounds(10, 5, 192, 35);
		chatlabel.setFont(new Font("Monotype Corsiva", Font.BOLD, 23));
		chatlabel.setForeground(Color.WHITE);
		this.add(chatlabel);
		
		errorLabel = new JLabel("You current dont have any chats");
		errorLabel.setForeground(Color.RED);;
		errorLabel.setBounds(20, 51, 609, 35);
		errorLabel.setFont(new Font("Monotype Corsiva", Font.BOLD, 23));
		this.errorLabel.setVisible(false);
		this.add(errorLabel);
		
		EmptyBorder padding = new EmptyBorder(0, 10, 0, 0);
		LineBorder redborder= new LineBorder(Color.RED);
		LineBorder blueborder= new LineBorder(Color.BLUE, 2);
		LineBorder greenborder= new LineBorder(Color.GREEN, 2);
		LineBorder grayborder = new LineBorder(new Color(52, 50, 50), 3);
		this.grayPaddingborder = BorderFactory.createCompoundBorder(grayborder, padding);
		this.redPaddingborder = BorderFactory.createCompoundBorder(redborder, padding);
		this.bluePaddingborder = BorderFactory.createCompoundBorder(blueborder, padding);
		this.greenPaddingborder = BorderFactory.createCompoundBorder(greenborder, padding);
		this.messagePanel = new JPanel();
		messagePanel.setBackground(new Color(33, 32, 32));
		addButtons();	
		addMessegeFrame();		
		
	}
	


	private void addMessegeFrame() {
		if(defaultcontactindex != -1) {
			this.messagePanel = new MessagesPanel(user, defaultcontactindex);
			this.add(messagePanel);
			errorLabel.setVisible(false);
		}
		
	}
	class actionlistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent a) {
			if(a.getSource().equals(loadmoreButton)) {
					pageNumber++;
					goToNextPage();
			}	
			else if(a.getSource().equals(contactButtons.get(0))) {
				openConversation(0 + pageNumber * 8);
			}
			else if(a.getSource().equals(contactButtons.get(1))) {
				openConversation(1 + pageNumber * 8);
			}
			else if(a.getSource().equals(contactButtons.get(2))) {
				openConversation(2 + pageNumber * 8);
			}
			else if(a.getSource().equals(contactButtons.get(3))) {
				openConversation(3 + pageNumber * 8);
			}
			else if(a.getSource().equals(contactButtons.get(4))) {
				openConversation(4 + pageNumber * 8);
			}
			else if(a.getSource().equals(contactButtons.get(5))) {
				openConversation(5 + pageNumber * 8);
			}
			else if(a.getSource().equals(contactButtons.get(6))) {
				openConversation(6 + pageNumber * 8);
			}
			else if(a.getSource().equals(contactButtons.get(7))) {
				openConversation(7 + pageNumber * 8);
			}
			
		}
		
	} 
	
	//The message Panel will be replacing by new Message panel of the specific contact whose button is clicked
	public void openConversation(int contactindex) {
		this.remove(messagePanel);
		this.messagePanel = new MessagesPanel(user, contactindex);
		this.add(messagePanel);
		repaint();
		revalidate();
	}
	
	
	//Mouse Listener for hover over effects on buttons
	class mouselistener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {}

		@Override
		public void mouseEntered(MouseEvent m) {
			
			if(m.getSource().equals(contactButtons.get(0))){
				contactButtons.get(0).setBorder(redPaddingborder);
			}
			else if(m.getSource().equals(contactButtons.get(1))){
				contactButtons.get(1).setBorder(bluePaddingborder);
			}
			else if(m.getSource().equals(contactButtons.get(2))){
				contactButtons.get(2).setBorder(greenPaddingborder);
			}
			else if(m.getSource().equals(contactButtons.get(3))){
				contactButtons.get(3).setBorder(bluePaddingborder);
			}
			else if(m.getSource().equals(contactButtons.get(4))){
				contactButtons.get(4).setBorder(greenPaddingborder);
			}
			else if(m.getSource().equals(contactButtons.get(5))){
				contactButtons.get(5).setBorder(redPaddingborder);
			}
			else if(m.getSource().equals(contactButtons.get(6))){
				contactButtons.get(6).setBorder(greenPaddingborder);
			}
			else if(m.getSource().equals(contactButtons.get(7))){
				contactButtons.get(7).setBorder(redPaddingborder);
			}
			else if(m.getSource().equals(loadmoreButton)){
				loadmoreButton.setBorder(redPaddingborder);
			}
			
		
		}

		@Override
		public void mouseExited(MouseEvent m) {
			if(m.getSource().equals(contactButtons.get(0))){
				contactButtons.get(0).setBorder(grayPaddingborder);
			}
			else if(m.getSource().equals(contactButtons.get(1))){
				contactButtons.get(1).setBorder(grayPaddingborder);
			}
			else if(m.getSource().equals(contactButtons.get(2))){
				contactButtons.get(2).setBorder(grayPaddingborder);
			}
			else if(m.getSource().equals(contactButtons.get(3))){
				contactButtons.get(3).setBorder(grayPaddingborder);
			}
			else if(m.getSource().equals(contactButtons.get(4))){
				contactButtons.get(4).setBorder(grayPaddingborder);
			}
			else if(m.getSource().equals(contactButtons.get(5))){
				contactButtons.get(5).setBorder(grayPaddingborder);
			}
			else if(m.getSource().equals(contactButtons.get(6))){
				contactButtons.get(6).setBorder(grayPaddingborder);
			}
			else if(m.getSource().equals(contactButtons.get(7))){
				contactButtons.get(7).setBorder(grayPaddingborder);
			}
			else if(m.getSource().equals(loadmoreButton)){
				loadmoreButton.setBorder(grayPaddingborder);
			}
					
		}

		@Override
		public void mousePressed(MouseEvent arg0) {}

		@Override
		public void mouseReleased(MouseEvent arg0) {}
		
	}
	
	/*
	 * On clicking the next button the previous buttons are replaced by next available contacts in the contacts array
	 */
	public void goToNextPage() {
		for(int i = 0; i < this.contactButtons.size(); i++) {
			this.contactButtons.get(i).setVisible(false);
		}
		
		int buttonindex = 0;
		for(int i = this.pageNumber*8; i < this.contacts.size() && buttonindex < 8; i++) {
			//way of writing this text copied from stack overflow
			String text = "<html><b>" + this.contacts.get(i).getName() + "</b><br /> " + this.messages.get(i).get(this.messages.get(i).size() -1) + "</html>";
			
			this.contactButtons.get(buttonindex).setText(text);
			this.contactButtons.get(buttonindex).setVisible(true);
			buttonindex++;
		}
		
		if(this.contacts.size() < ((8* (pageNumber+1))+1)) {
			this.loadmoreButton.setVisible(false);
		} 
		
	}
	
	//This method initializes all the buttons on the Panel
	private void addButtons() {
		loadmoreButton = new JButton("load more");
		loadmoreButton.setForeground(Color.WHITE);
		loadmoreButton.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 13));
		loadmoreButton.setBackground(Color.DARK_GRAY);
		loadmoreButton.setBounds(10, 627, 308, 27);
		if(this.contacts.size() < 9) {
			loadmoreButton.setVisible(false);
		}
		this.add(loadmoreButton);
		
		JButton contactButton1 = new JButton();
		contactButton1.setBounds(10, 51, 308, 58);

		JButton contactButton2 = new JButton();
		contactButton2.setBounds(10, 120, 308, 58);
	
		JButton contactButton3 = new JButton();
		contactButton3.setBounds(10, 189, 308, 58);
		
		JButton contactButton4 = new JButton();
		contactButton4.setBounds(10, 258, 308, 58);
		
		JButton contactButton5 = new JButton();
		contactButton5.setBounds(10, 327, 308, 58);
		
		JButton contactButton6 = new JButton();
		contactButton6.setBounds(10, 396, 308, 58);
		
		JButton contactButton7 = new JButton();
		contactButton7.setBounds(10, 465, 308, 58);
		
		JButton contactButton8 = new JButton();
		contactButton8.setBounds(10, 534, 308, 58);
		
		this.contactButtons.add(contactButton1);
		this.contactButtons.add(contactButton2);
		this.contactButtons.add(contactButton3);
		this.contactButtons.add(contactButton4);
		this.contactButtons.add(contactButton5);
		this.contactButtons.add(contactButton6);
		this.contactButtons.add(contactButton7);
		this.contactButtons.add(contactButton8);
		
		ActionListener al = new actionlistener();
		MouseListener ml = new mouselistener();
		for(int i = 0; i < this.contactButtons.size(); i++) {
			this.contactButtons.get(i).setForeground(Color.WHITE);
			this.contactButtons.get(i).setFont(new Font("Lucida Calligraphy", Font.PLAIN, 13));
			this.contactButtons.get(i).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//			this.contactButtons.get(i).setContentAreaFilled(false);
//			this.contactButtons.get(i).setBackground(new Color(26,148,184));
			this.contactButtons.get(i).setBackground(Color.DARK_GRAY);
			this.contactButtons.get(i).setVisible(false);
			this.contactButtons.get(i).setBorder(grayPaddingborder);
			this.contactButtons.get(i).setHorizontalAlignment(SwingConstants.LEFT);
			this.contactButtons.get(i).addActionListener(al);
			this.contactButtons.get(i).addMouseListener(ml);
			this.add(this.contactButtons.get(i));
		}
		
		
		for(int i = 0; i < user.getChats().size(); i++) {
			this.contacts.add(user.getChats().get(i).getContact());
			this.messages.add(user.getChats().get(i).getMessages());
		}
		if(this.user.getChats().size() == 0) {
			this.errorLabel.setVisible(true);
		}
		
		int size = this.contactButtons.size();
		if(size > this.contacts.size()) {
			size = this.contacts.size();
		}
		String text;
		for(int i = 0; i < size; i++) {
			text = "<html><b>" + this.contacts.get(i).getName() + "</b><br /> " + this.messages.get(i).get(this.messages.get(i).size() -1) + "</html>";
			this.contactButtons.get(i).setText(text);
			this.contactButtons.get(i).setVisible(true);
		}
		
	}
	
}

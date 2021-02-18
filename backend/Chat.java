package com.ourteams.backend;

import java.io.Serializable;
import java.util.ArrayList;

public class Chat implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<String> messages = new ArrayList<String>();
	private User contact;
	
	
	//default constructor
	public Chat() {
		
	}
	
	//argument constructor
	public Chat(User contact, ArrayList<String> messages) {
		super();
		this.messages = messages;
		this.contact = contact;
	}

	
	//adds a message to the message array
	public void addMessage(String message) {
		this.messages.add(message);
	}

	//getters and setters for data members
	public ArrayList<String> getMessages() {
		return messages;
	}


	public void setMessages(ArrayList<String> messages) {
		this.messages = messages;
	}


	public User getContact() {
		return contact;
	}


	public void setContact(User contact) {
		this.contact = contact;
	}
	
	
	
	
}

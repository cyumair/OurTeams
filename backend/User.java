package com.ourteams.backend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class User implements Serializable{
    /**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
/*	A user has an Array of the teams the he is part of, User has a name, email, password and a department along with array of all the notifications it has
 * and a all the chats he has. 
	 User is abstract class as we are not going make a direct object of it, A user can either be teacher be a student or
     a teacher each with a difficult functionality so we will inherit more classes from as the type of user we are 
	 making an account for. */
	private ArrayList<Team> teams;
    private String name;
    private String email;
    private String password;
    private String department;
    
    
    private ArrayList<String> notifications;
    private ArrayList<Chat> chats = new ArrayList<Chat>();

    //default constructor
    public User() {
        this.teams = new ArrayList<Team>();
        this.notifications = new ArrayList<String>();

    }
    
    
    //argument constructor to assign value to properties at the time when new Student or Teacher(User) is created 
    public User(String name, String email, String password, String department)  {
        this.name = name;
        this.email = email;
        this.password = password;
        this.department = department;
        this.teams = new ArrayList<Team>();
        this.notifications = new ArrayList<String>();
    }
    

    //getters and setters for the properties.
    public ArrayList<Team> getTeams() {
		return teams;
	}

	public void setTeams(ArrayList<Team> teams) {
		this.teams = teams;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	public ArrayList<Chat> getChats() {
		return chats;
	}

	public void setChats(ArrayList<Chat> chats) {
		this.chats = chats;
	}
	
		
	public ArrayList<String> getNotifications() {
		return notifications;
	}

	//Overriding equals method which only compares email of the user, as email for every user is unique
	public boolean equals(Object o) {
		if(o instanceof User) {
			User user = (User)o;
			if(this.email.equals(user.getEmail())) {
				return true;
			}
		}
		return false;
	}


	/*
	 * This method takes a Team object as an argument
	 * When a user joins a Team, that team is added to user's Teams array
	 * and its reference is added to participants of that particular team
	 * The data of that team is updated in the file and notification of the joining is 
	 * displayed to the user
	 */
	public void join_team(Team team){
		team.addParticipant(this);
    	this.teams.add(team);
    	Team.updateTeamsData(team);
    	this.AddNotification("You have joined the team: " + team.getName() + "\n" + this.getcurrentTime());
    };
    
    
    /*
     * This method takes a Team object as an argument 
     * Adds user's reference to participants of that Team 
     * and adds the new Team to all teams record file.
     * Adds the Team to user's Team array
     * Adds a notification in the user's Notification array
     */
	public void createTeam(Team newteam) {
		newteam.addParticipant(this);
		this.teams.add(newteam);
		Team.AddTeamtoRecord(newteam);
		this.AddNotification("You created the team: " + newteam.getName() + "\n" + this.getcurrentTime());
		
	}
	
	/*
	 * Deletes the user from the users file
	 */
	public void deleteAccount() {	
		User.deleteUser(this);
	}

	/*
	 * Takes a Team object as an argument
	 * Removes that Team from user's Team array
	 * Removes the user from that Team's participants
	 * Updates the Teams record file
	 * Adds a notification of leaving the team in user's notification Array
	 */
	public void leaveTeam(Team team) {
		this.teams.remove(team);
		User.updateUserData(this);
		team.removeParticipant(this);
		Team.updateTeamsData(team);
		this.AddNotification("You left the team: " + team.getName()+ "\n" + this.getcurrentTime());
		
	}

	
	/*
	 * This method takes the Team in which message has been posted and the text of the message as an argument
	 * The text is added to Teams posts
	 * and record file of that Team is updated
	 */
	public void postMessage(Team team, String text) {
		team.addPost(text);
		Team.updateTeamsData(team);
	}
	
	/*
	 * This method takes mail and password (Strings) as argument
	 * Updates the data with new mail and password in the file
	 * and Updates the data in current Object of the user
	 */
	public void updatecredentials(String mail, String password) {
		User.updateUserDetails(this.email, mail, password);
		this.setEmail(mail);
		this.setPassword(password);
	}
	
	
	//This method takes a text as an argument and adds that to notification array of the User.
	public void AddNotification(String text) {
		this.notifications.add(text);
		
	}
	
	
	@Override
	public String toString() {
		return "User [teams=" + teams + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", department=" + department + ", notifications=" + notifications + ", chats=" + chats + "]";
	}

	//we will use this method to get our notification time
	public String getcurrentTime() {
    	   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    	   LocalDateTime now = LocalDateTime.now();  
    	   return dtf.format(now);
	}

	/*
	 * This method takes User object (receiver of the message) as an argument
	 * If that receiver is found in user chats then previous conversation is continued
	 * else new conversation is started with the receiver and the receiver is added 
	 * to user's chats and user is also added to receiver's chat
	 * Data of the receiver is also updated as a new chat is added to his/her Chat's array
	 */
	
	public int startaconversation(User user) {
		boolean found = false;
		for(int i = 0; i < this.chats.size(); i++) {
			if(user.getEmail().equals(this.chats.get(i).getContact().getEmail())){ 
				return i;		
			}
		}
		
		if(!found) {
			ArrayList<String> messagesofsender = new ArrayList<String>();
			
			messagesofsender.add(this.getName() + " has started a  new conversation.");
			Chat senderchat = new Chat(user, messagesofsender);
			this.chats.add(0, senderchat);
			
			ArrayList<String> messagesofreciever = new ArrayList<String>();
			messagesofreciever.add(this.getName() + " has started a  new conversation.");
			
			Chat receiverchat = new Chat(this, messagesofreciever);
			user.chats.add(0, receiverchat);
			
			User.updateUserData(user);
		
		}
		return 0;
		
	}

	
	/*
	 * The method takes the contact index (index of the receiver in the user's chat array so it takes less time to find that user
	 * and a string text which is the message to be sent
	 * The message is added to User's chats 
	 * and the current User is searched in receiver's chats, when its found then message is added to his/her chat too.
	 * and finally the reciever's data is updated in the file
	 */
	public void sendMessage(int contactindex, String message) {
		
		this.getChats().get(contactindex).addMessage(message);
		User contact = User.findUser(this.getChats().get(contactindex).getContact().getEmail());
		for(int i = 0; i < contact.getChats().size(); i++) {
			if(contact.getChats().get(i).getContact().equals(this)) {
				contact.getChats().get(i).addMessage(message);
				break;
			}
		}

		User.updateUserData(contact);
	}
    
     
	
	/*
	 * The method takes name, password, department and semester as an argument and updates that data in the User record File
	 */
     //=======================================================================================
     public void updatecredentials(String name, String password,String depart,String semes) {
    	 //Updating data in the file
        User.updateUserDetails(this.email,name,password,depart,semes);
        this.setName(name);
        this.setPassword(password);
        this.setDepartment(depart);
        if (this instanceof Student ) {
            ((Student) this).setSemester(semes);
        }
    }
     
     
     //STATIC METHODS THAT DEAL WITH UPDATING, SEARCHING, VIEWING, DELETING, ADDING the records in the User Record File
     //SOME OF THESE METHODS ARE NOT USED. THEY WERE MADE FOR TESTING/PRACTICE PURPOSES.
     //=========================================================================================
     //Replaces the old User array is the file with new and updated one.
     public static void updateUserDetails(String currentmail,String name, String password,String depart,String semes) {

        ArrayList<User> users = readAllDataFromFile();
        Scanner input = new Scanner(System.in);
        for (User currentUser : users) {
            if (currentUser.getEmail().equalsIgnoreCase(currentmail)) {
                currentUser.setName(name);
                currentUser.setDepartment(depart);
                currentUser.setPassword(password);
                if (currentUser instanceof Student)
                    ((Student) currentUser).setSemester(semes);

                break;
            }
        }
        input.close();
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("Users Record"));
            output.writeObject(users);
            output.close();
        } catch (IOException e) {
            System.out.println("error");
        }
    }
     
    //========================================================================================
  
     //Takes email as an argument and searches for that user's email in User record file
     //finds the user and returns it if found else returns NULL
     public static User findUser(String mail){
        ArrayList<User> users = User.readAllDataFromFile();
        for (User currentuser:users){
            if (currentuser.getEmail().equalsIgnoreCase(mail))
                return currentuser;
        }
        return null;
    }
     //====================================================================================

    //Replaces the old User array is the file with new and updated one after replacing the old mail and password with new one. 
    public static void updateUserDetails(String currentmail,String mail,String paassword) {

        ArrayList<User> users = readAllDataFromFile();
        Scanner input = new Scanner(System.in);
        for (User currentUser : users) {
            if (currentUser.getEmail().equalsIgnoreCase(currentmail)) {
                currentUser.setEmail(mail);
                currentUser.setPassword(paassword);
                System.out.println(currentUser.getEmail());
                break;
            }
        }
        input.close();
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("Users Record"));
            output.writeObject(users);
            output.close();
        } catch (IOException e) {
            System.out.println("error");
        }
    }

     //================================================================================
    
    //Reads the Array of Users from the record file and returns it. If there is no file then creates a new one and adds the new empty User list to file
    @SuppressWarnings("unchecked")
	public static ArrayList<User> readAllDataFromFile(){
        ArrayList<User> list=new ArrayList<User>();
        try {
        	File file = new File("Users Record");
			if(!file.isFile()) {
				ObjectOutputStream o2=new ObjectOutputStream(new FileOutputStream("Users Record"));
				o2.writeObject(list);
				o2.close();
			}
            ObjectInputStream o1=new ObjectInputStream(new FileInputStream("Users Record"));
            list = (ArrayList<User>)o1.readObject();
            o1.close();

        }
        catch (Exception e){
        	System.out.println("Error inputing stream");
            e.printStackTrace();
        }
        return list;

    }
     //================================================================================
    
    //if there is any change in user's data, in his teams or notifications or chats etc, than that user is updated in the file using this method
    //the old user is removed and new one is inserted into the file
    public static void updateUserData(User user){
    	ArrayList<User> users = readAllDataFromFile();
        for (User currentUser : users
        ) {
            if (currentUser.getEmail().equalsIgnoreCase(user.getEmail())) {
                users.remove(currentUser);
                users.add(user);
                break;
            }
        }
        try {
            ObjectOutputStream output=new ObjectOutputStream(new FileOutputStream("Users Record"));
            output.writeObject(users);
            output.close();
      
        }
        catch (IOException e){
            System.out.println("error");
            e.printStackTrace();
        }
        

    }
//    ==============================================================================
    //checks if the email entered by the user at the time of sign up is valid or not
    //returns true if valid else returns false
    //for email to be valid it must have @ symbol and must start with an alphabet.
    //there must be an alphabet after @ symbol. 
    public static boolean isValidEmail(String email){
        //alphabet se start ho ro @rate ayeee.
        //ali123@asASA.com;
        int i=email.indexOf("@");
        if (email.length()==0)
            return false;
        if (!Character.isLetter(email.charAt(0)))
            return false;
        else if (i==-1)
            return false;
        else if (!Character.isLetter(email.charAt(i+1)))
            return false;
        else
            return true;

        //
    }
//===================================================================================
    
    /*
     * Reads the record of all the USers and checks if the email is already used in creating another account
     * returns true if its already used else returns false;
     */
    public static boolean hasEmailAlreadyBeenUser(String email) {
        ArrayList<User> users = readAllDataFromFile();
        for (User currentUser : users
        ) {
            if (currentUser.getEmail().equalsIgnoreCase(email)) {
                return true;
            }

        }
        return false;
    }
//   =========================================================================================
    
    /*
     * Adds the new user to the User Array and Writes the new array to the file(replaces old array with the new one)
     */
    public static void AddUser(User u){
        ArrayList<User> users=readAllDataFromFile();
        if (!isValidEmail(u.getEmail())) {
            System.out.println("invalid email");
            return;
        }
        if (hasEmailAlreadyBeenUser(u.getEmail())) {
            System.out.println("email already registered");
            return;
        }
        users.add(u);
        try {
            ObjectOutputStream output=new ObjectOutputStream(new FileOutputStream("Users Record"));
            output.writeObject(users);
            output.close();
        }
        catch (IOException e){
            System.out.println("error");
            e.printStackTrace();
        }

    }
//    ==============================================================================================
    
    /*
     * Searches for the email and password in the user record file and returns that user if found else returns null
     */
    public static User findUser(String mail,String passwrd ){
        ArrayList<User> users = readAllDataFromFile();
        for (User currentuser:users){
            if (currentuser.getEmail().equalsIgnoreCase(mail) && currentuser.getPassword().equals(passwrd))
                return currentuser;
        }
        return null;
    }
//    ============================================================================================

    //searches for user, returns true if found else returns false
    public static boolean findEmail(User u){
        ArrayList<User> users=readAllDataFromFile();
        for (User currentUser:users ) {
            if (currentUser.getEmail().equalsIgnoreCase(u.getEmail()))
                return true;
        }
        return false;
    }
//    =====================================deleting user==============================================
    public static void deleteUser(User u){
        ArrayList<User> users=readAllDataFromFile();
        for (User currentUser:users ) {
            if (currentUser.getEmail().equalsIgnoreCase(u.getEmail())) {
                users.remove(currentUser);
                break;
            }

        }
//        updating file data ==============================================
        try {
            ObjectOutputStream output=new ObjectOutputStream(new FileOutputStream("Users Record"));
            output.writeObject(users);
            output.close();
        }
        catch (IOException e){
            System.out.println("error");
        }
    }
    
    
    //Replaces the old file with new one with updated user data array
    public static void updateUserDetails(User u){
        ArrayList<User> users=readAllDataFromFile();
        Scanner input=new Scanner(System.in);
        for (User currentUser:users) {
            if (currentUser.getEmail().equalsIgnoreCase(u.getEmail())){

                System.out.println("enter new password");
                currentUser.setPassword(input.nextLine());
                System.out.println("enter new email");
                currentUser.setEmail(input.nextLine());

                break;
            }
        }
        input.close();
        try {
            ObjectOutputStream output=new ObjectOutputStream(new FileOutputStream("Users Record"));
            output.writeObject(users);
            output.close();
        }
        catch (IOException e){
            System.out.println("error");
        }

    }




}

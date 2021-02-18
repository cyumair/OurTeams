package com.ourteams.backend;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.io.File;

public class Team implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<User>participants;
    ArrayList<Assignment>assignments;
    ArrayList<String> posts;
    String joincode;
    String name;

    //default constructor
    public Team(){
    	this.participants = new ArrayList<User>();
        this.posts = new ArrayList<String>();
        this.assignments = new ArrayList<Assignment>();  
    }
    
    //argument constructor
    public Team(String joincode, String teamname) {
        this.joincode = joincode;
        this.name = teamname;
        this.participants = new ArrayList<User>();
        this.posts = new ArrayList<String>();
        this.assignments = new ArrayList<Assignment>();
        
    }
    
    //this methods takes an assignment as an argument and adds that to the team
    public void addAssignment(Assignment assign) {
    	this.assignments.add(0, assign);
    }
    
    //getters and setters for the data members
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public ArrayList<User> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<User> participants) {
        this.participants = participants;
    }

    public ArrayList<Assignment> getAssignments() {
    	if(assignments == null) {
    		System.out.println("NULL");
    	}
        return assignments;
    }

    public void setAssignments(ArrayList<Assignment> assignments) {
        this.assignments = assignments;
    }


    public String getJoincode() {
        return joincode;
    }

    public void setJoincode(String joincode) {
        this.joincode = joincode;
    }
    
    public ArrayList<String> getPosts() {
		return posts;
	}

	public void setPosts(ArrayList<String> posts) {
		this.posts = posts;
	}
	
	//This method receives user as an argument and adds it to the team
	public void addParticipant(User u) {
		this.participants.add(u);
	}
	
	//This method receives user as an argument and removes it from the team
	public void removeParticipant(User u) {
		this.participants.remove(u);
	}
	
	//This method receives post as an argument and adds it to team's posts
	public void addPost(String post) {
		this.posts.add(post);
	}

	@Override
	public String toString() {
		return this.name + " " + this.joincode;
	}

	//--------------------------------------------------------------------------
	//STATIC METHODS FOR TEAMS RECORD FILE
	
	/*
	 * This method receives Team as an argument 
	 * Reads the array list from record file 
	 * Adds the new Team team to the list
	 * and then overwrites the previous file with new file having updated list 
	 */
	public static void AddTeamtoRecord(Team t) {
        ArrayList<Team> teams = readAllDataFromFile();
        teams.add(t);
        
        try {
            ObjectOutputStream output=new ObjectOutputStream(new FileOutputStream("Teams Record"));
            output.writeObject(teams);
            output.close();
        }
        
        catch (IOException e){
            System.out.println("error");
        }	
    }
	
	/*
	 * This method receives join-code as an argument
	 * Returns true if the join-code is found in teams record file 
	 * else returns false
	 */
	public static boolean isTeamAlreadyCreated(String joincode) {
        ArrayList<Team> teams = readAllDataFromFile();
        for(Team team: teams) {
        	if(team.getJoincode().equals(joincode)) {
        		return true;
        	}
        }
        return false;
	}

	/*
	 * This method reads all data from file (which as an Array list)
	 * Stores that into array list and returns it
	 * if no file is found then creates a new file which has empty array list (when program is run for the very first the new file needs to be created)
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<Team> readAllDataFromFile() {
		ArrayList<Team> list=new ArrayList<Team>();
		try{
			File file = new File("Teams Record");
			if(!file.isFile()) {
				ObjectOutputStream o2=new ObjectOutputStream(new FileOutputStream("Teams Record"));
				o2.writeObject(list);
				o2.close();
			}
	        ObjectInputStream o1= new ObjectInputStream(new FileInputStream("Teams Record"));
	        list = (ArrayList<Team>)o1.readObject();
	        o1.close();
		}
        catch (Exception e){
        	System.out.println("Error inputing stream");
            e.printStackTrace();
        }
        return list;
	}
	
	//Displays all the teams in file
	public static void displayAllTeamsRecord() {
		ArrayList<Team> teams = readAllDataFromFile();
		for(Team team : teams) {
			System.out.println(team.getJoincode());
		}
	}
	
	/*
	 * This method receives the team (updated one) as an argument
	 * Reads all the data from the file
	 * Removes the old team from the file and adds the updated one in the Array list
	 * Replaces the old file with new one having the updated Array list of teams
	 */
	public static void updateTeamsData(Team team) {
        ArrayList<Team> teams = readAllDataFromFile();
        for(int i = 0; i < teams.size(); i++) {
        	if(teams.get(i).getJoincode().equals(team.joincode)) {
        		teams.remove(teams.get(i));
        		teams.add(team);
        		break;
        	}
        }
	
        
        try {
            ObjectOutputStream output=new ObjectOutputStream(new FileOutputStream("Teams Record"));
            output.writeObject(teams);
            output.close();
        }
        
        catch (IOException e){
            System.out.println("error");
        }
	}
}

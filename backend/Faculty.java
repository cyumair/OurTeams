package com.ourteams.backend;

public class Faculty extends User{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	//default constructor
	public Faculty(){
		
	}
	
	//argument constructor
	public Faculty(String name, String email, String password, String department) {
        super(name, email, password, department);
    }

    
	/*
	 * This method receives title, detail, points, date and index(index of the team in user's team array whose assignment is being created
	 * It creates a new assignment and adds it to the team (whose index we received)
	 * Then updates the team in team record file and adds a notification for the user and all the other members in the team and 
	 * updates new data for all the participants of that team (as user's data has been modified) 
	 */
    public void createNewAssignmnet(String title, String detail, double points, Date date, int teamindex) {
    	Assignment assign = new Assignment(title, date, detail, this.getTeams().get(teamindex), points);
    	this.getTeams().get(teamindex).addAssignment(assign);
    	Team.updateTeamsData(this.getTeams().get(teamindex));
    	for(User u: this.getTeams().get(teamindex).getParticipants()) {
    		u.AddNotification("Assignment has been mentioned: " + title + ", " + this.getTeams().get(teamindex).getName() +
    				", Due date: " + date.getFormattedDate() + "."+ "\n" + this.getcurrentTime());
    		User.updateUserData(u);
    	}
    }

    /*
     * This method takes assignment data, assignment and the marks of the assignment to return it to student
     * sets the obtained marks in the data and updates the team (as data has been modified)
     * Adds a notification in the student's notification and updates its record in the file too (as its notifications have been modified)
     */
	public void returnAssignment(AssignmentData data, Assignment assignment, double marks) {
		data.setObtainedPoints(marks);
		data.setMarkedby(this.getEmail());
		Team.updateTeamsData(assignment.getTeam());
		data.getStudent().AddNotification("Assignment returned" + assignment.getTitle() + 
				", Obtained "+ marks + " points."+ "\n" + this.getcurrentTime());
		User.updateUserData(data.getStudent());
	}

	/*
	 * This method takes the assignment to be deleted as an argument.
	 * Removes that assignment from the team and updates the team in team record file
	 */
	public void deleteAssignment(Assignment assignment) {
		assignment.getTeam().getAssignments().remove(assignment);
		Team.updateTeamsData(assignment.getTeam());
		
	}

	/*
	 * This method takes changes that has been made as an argument or the old data if its not changed 
	 * sets the new data to the assignment 
	 * updates the team in teams record file
	 */
	public void updateAssignment(Assignment assignment,String newDay,String newMonth,String newYear,
								 String newTitle,String newDetail,String newMin,String newHours,double newpoints){
	

		assignment.setDetail(newDetail);
		assignment.setTitle(newTitle);
		assignment.setTotalPoints(newpoints);
		Date d1 = new Date(newDay, newMonth, newYear, newHours, newMin);
		assignment.setDueDate(d1);
		Team.updateTeamsData(assignment.getTeam());

	}

	@Override
	public String toString() {
		return super.toString();
	}
    

}

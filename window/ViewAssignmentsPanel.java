package com.ourteams.window;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.ourteams.backend.Assignment;
import com.ourteams.backend.Student;
import com.ourteams.backend.Team;
import com.ourteams.backend.User;

public class ViewAssignmentsPanel extends JPanel{
	/**
	 * This Panel displays the list of assignments 
	 * Different for faculty and student
	 * Faculty member also has access to create assignment button and to those assignments whose due date has passed
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private ArrayList<JButton> assignmentButtons = new ArrayList<JButton>();
	private JButton loadmoreButton;
	private JButton createButton;
	private CompoundBorder grayPaddingborder;
	private CompoundBorder redPaddingborder;
	private CompoundBorder greenPaddingborder;
	private CompoundBorder bluePaddingborder;
	private AssignmentPanel assignmentPanel;
	private ArrayList<Assignment> assignments =  new ArrayList<Assignment>();
	private int pageNumber;
	
	
	public ViewAssignmentsPanel(AssignmentPanel panel) {
		this.assignmentPanel = panel;
		this.user = panel.getUser();
		initalize();
	}

	private void initalize() {
		this.setLayout(null);
		this.setBounds(0, 0, 1149, 687);
		this.setBackground(new Color(33, 32, 32));
		
		JLabel assignemntsLabel = new JLabel("Assignments");
		assignemntsLabel.setBounds(34, 40, 192, 35);
		assignemntsLabel.setFont(new Font("Monotype Corsiva", Font.BOLD, 23));
		assignemntsLabel.setForeground(new Color(255, 255, 255));
		this.add(assignemntsLabel);
		
		//creating borders for our layout
		EmptyBorder padding = new EmptyBorder(0, 10, 0, 0);
		LineBorder redborder= new LineBorder(Color.RED);
		LineBorder blueborder= new LineBorder(Color.BLUE, 2);
		LineBorder greenborder= new LineBorder(Color.GREEN, 2);
		LineBorder grayborder = new LineBorder(new Color(52, 50, 50), 3);
		this.grayPaddingborder = BorderFactory.createCompoundBorder(grayborder, padding);
		this.redPaddingborder = BorderFactory.createCompoundBorder(redborder, padding);
		this.bluePaddingborder = BorderFactory.createCompoundBorder(blueborder, padding);
		this.greenPaddingborder = BorderFactory.createCompoundBorder(greenborder, padding);
		
		//adding all the assignments that need to be displayed
		for(Team team : this.user.getTeams()) {
			for(Assignment assign: team.getAssignments()) {
				if(user instanceof Student) {
					if(!pastDueDate(assign)) {
						this.assignments.add(assign);
					}
				}
				else {
					this.assignments.add(assign);
				}
			}
		}
		
		createButtons();

		
	}
	
	//this method compare current system time with due date of assignment
	public boolean pastDueDate(Assignment assign) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd/HH/mm/ss");  
		LocalDateTime now = LocalDateTime.now();  
		String timenow = dtf.format(now);
		//above three lines taking from https://www.javatpoint.com/java-get-current-date
		String[] currentdate = timenow.split("/");
		
		if(Integer.parseInt(currentdate[0]) > Integer.parseInt(assign.getDueDate().getYear())) {
			return true;
		}
		else if(Integer.parseInt(currentdate[0]) == Integer.parseInt(assign.getDueDate().getYear())){
			if(Integer.parseInt(currentdate[1]) > Integer.parseInt(assign.getDueDate().getMonth())) {
				return true;
			}
			else if(Integer.parseInt(currentdate[1]) == Integer.parseInt(assign.getDueDate().getMonth())) {
				if(Integer.parseInt(currentdate[2]) > Integer.parseInt(assign.getDueDate().getDay())) {
					return true;
				}
				else if(Integer.parseInt(currentdate[2]) == Integer.parseInt(assign.getDueDate().getDay())) {
					if(Integer.parseInt(currentdate[3]) > Integer.parseInt(assign.getDueDate().getHour())) {
						return true;
					}
					else if(Integer.parseInt(currentdate[3]) == Integer.parseInt(assign.getDueDate().getHour())) {
						if(Integer.parseInt(currentdate[4]) > Integer.parseInt(assign.getDueDate().getMinute())) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	class actionlistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent a) {
			//When load more button is pressed then goToNextPage method is called
			if(a.getSource().equals(loadmoreButton)) {
					pageNumber++;
					goToNextPage();
			}
			//When createButton is pressed a new Dialog Appears for creating a new assignment
			else if(a.getSource().equals(createButton)) {
				//open dialog box
				new CreateNewAssignmentDialog(assignmentPanel);
			}
	
			//if any assignment Button is pressed then data (Panel) for that assignment is opened
			else if(a.getSource().equals(assignmentButtons.get(0))) {
				assignmentPanel.openAssignment(assignments.get(0 + pageNumber * 8));
			}
			else if(a.getSource().equals(assignmentButtons.get(1))) {
				assignmentPanel.openAssignment(assignments.get(1 + pageNumber * 8));
			}
			else if(a.getSource().equals(assignmentButtons.get(2))) {
				assignmentPanel.openAssignment(assignments.get(2 + pageNumber * 8));
			}
			else if(a.getSource().equals(assignmentButtons.get(3))) {
				assignmentPanel.openAssignment(assignments.get(3 + pageNumber * 8));
			}
			else if(a.getSource().equals(assignmentButtons.get(4))) {
				assignmentPanel.openAssignment(assignments.get(4 + pageNumber * 8));
			}
			else if(a.getSource().equals(assignmentButtons.get(5))) {
				assignmentPanel.openAssignment(assignments.get(5 + pageNumber * 8));
			}
			else if(a.getSource().equals(assignmentButtons.get(6))) {
				assignmentPanel.openAssignment(assignments.get(6 + pageNumber * 8));
			}
			else if(a.getSource().equals(assignmentButtons.get(7))) {
				assignmentPanel.openAssignment(assignments.get(7 + pageNumber * 8));
			}
			
		}
		
	}
	
	
	//Mouse Listener is used to add hover over effects for buttons
	class mouselistener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {}

		@Override
		public void mouseEntered(MouseEvent m) {
			
			if(m.getSource().equals(assignmentButtons.get(0))){
				assignmentButtons.get(0).setBorder(redPaddingborder);
			}
			else if(m.getSource().equals(assignmentButtons.get(1))){
				assignmentButtons.get(1).setBorder(bluePaddingborder);
			}
			else if(m.getSource().equals(assignmentButtons.get(2))){
				assignmentButtons.get(2).setBorder(greenPaddingborder);
			}
			else if(m.getSource().equals(assignmentButtons.get(3))){
				assignmentButtons.get(3).setBorder(bluePaddingborder);
			}
			else if(m.getSource().equals(assignmentButtons.get(4))){
				assignmentButtons.get(4).setBorder(greenPaddingborder);
			}
			else if(m.getSource().equals(assignmentButtons.get(5))){
				assignmentButtons.get(5).setBorder(redPaddingborder);
			}
			else if(m.getSource().equals(assignmentButtons.get(6))){
				assignmentButtons.get(6).setBorder(greenPaddingborder);
			}
			else if(m.getSource().equals(assignmentButtons.get(7))){
				assignmentButtons.get(7).setBorder(redPaddingborder);
			}
			else if(m.getSource().equals(loadmoreButton)){
				loadmoreButton.setBorder(redPaddingborder);
			}
			else if(m.getSource().equals(createButton)) {
				createButton.setBorder(new LineBorder(Color.RED));
			}
			
		
		}

		@Override
		public void mouseExited(MouseEvent m) {
			if(m.getSource().equals(assignmentButtons.get(0))){
				assignmentButtons.get(0).setBorder(grayPaddingborder);
			}
			else if(m.getSource().equals(assignmentButtons.get(1))){
				assignmentButtons.get(1).setBorder(grayPaddingborder);
			}
			else if(m.getSource().equals(assignmentButtons.get(2))){
				assignmentButtons.get(2).setBorder(grayPaddingborder);
			}
			else if(m.getSource().equals(assignmentButtons.get(3))){
				assignmentButtons.get(3).setBorder(grayPaddingborder);
			}
			else if(m.getSource().equals(assignmentButtons.get(4))){
				assignmentButtons.get(4).setBorder(grayPaddingborder);
			}
			else if(m.getSource().equals(assignmentButtons.get(5))){
				assignmentButtons.get(5).setBorder(grayPaddingborder);
			}
			else if(m.getSource().equals(assignmentButtons.get(6))){
				assignmentButtons.get(6).setBorder(grayPaddingborder);
			}
			else if(m.getSource().equals(assignmentButtons.get(7))){
				assignmentButtons.get(7).setBorder(grayPaddingborder);
			}
			else if(m.getSource().equals(loadmoreButton)){
				loadmoreButton.setBorder(grayPaddingborder);
			}
			else if(m.getSource().equals(createButton)){
				createButton.setBorder(new LineBorder(new Color(52, 50, 50), 3));
			}
					
		}

		@Override
		public void mousePressed(MouseEvent arg0) {}

		@Override
		public void mouseReleased(MouseEvent arg0) {}
		
	}
	
	//this method adjust buttons according to the More data
	public void goToNextPage() {
		for(int i = 0; i < this.assignmentButtons.size(); i++) {
			this.assignmentButtons.get(i).setVisible(false);
		}
		
		int buttonindex = 0;
		for(int i = this.pageNumber*8; i < this.assignments.size() && buttonindex < 8; i++) {
			//way of writing this text copied from stack overflow
			String text = "<html><b>" + this.assignments.get(i).getTitle() + "</b><br />Team: "+this.assignments.get(i).getTeam().getName() +", DueDate: "
					+this.assignments.get(i).getDueDate().getFormattedDate() + ", Points: " + this.assignments.get(i).getTotalPoints() + "</html>";
			
			this.assignmentButtons.get(buttonindex).setText(text);
			this.assignmentButtons.get(buttonindex).setVisible(true);
			buttonindex++;
		}
		
		if(this.assignments.size() < ((8* (pageNumber+1))+1)) {
			this.loadmoreButton.setVisible(false);
		} 
		
	}

	private void createButtons() {
		
		MouseListener ml = new mouselistener();
		ActionListener al = new actionlistener();
		
		createButton = new JButton("Create");
		createButton.setForeground(Color.LIGHT_GRAY);
		createButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		createButton.setContentAreaFilled(false);
		createButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		createButton.setBackground(Color.LIGHT_GRAY);
		createButton.setBounds(1009, 21, 98, 28);
		createButton.setBorder(new LineBorder(new Color(52, 50, 50), 3));
		createButton.addActionListener(al);
		createButton.addMouseListener(ml);
		this.add(createButton);
		
		//if its a student or if its a faculty member with no teams then hide the create button
		if(this.assignmentPanel.getUser() instanceof Student || this.assignmentPanel.getUser().getTeams().size() == 0) {
			createButton.setVisible(false);
		}
		
		
		JButton abutton1 = new JButton();
		abutton1.setBounds(82, 93, 697, 57);
		this.assignmentButtons.add(abutton1);
		
		JButton abutton2 = new JButton();
		abutton2.setBounds(82, 161, 697, 57);
		this.assignmentButtons.add(abutton2);
		
		JButton abutton3 = new JButton();
		abutton3.setBounds(82, 229, 697, 57);
		this.assignmentButtons.add(abutton3);
		
		JButton abutton4 = new JButton();	
		abutton4.setBounds(82, 297, 697, 57);
		this.assignmentButtons.add(abutton4);
		
		JButton abutton5 = new JButton();
		abutton5.setBounds(82, 365, 697, 57);
		this.assignmentButtons.add(abutton5);
		
		JButton abutton6 = new JButton();
		abutton6.setBounds(82, 433, 697, 57);
		this.assignmentButtons.add(abutton6);
		
		JButton abutton7 = new JButton();
		abutton7.setBounds(82, 501, 697, 57);
		this.assignmentButtons.add(abutton7);
		
		JButton abutton8 = new JButton();
		abutton8.setBounds(82, 569, 697, 57);
		this.assignmentButtons.add(abutton8);
		
		loadmoreButton = new JButton("load more....");
		loadmoreButton.setHorizontalAlignment(SwingConstants.LEFT);
		loadmoreButton.setForeground(Color.LIGHT_GRAY);
		loadmoreButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		loadmoreButton.setContentAreaFilled(false);
		loadmoreButton.setBackground(Color.BLACK);
		loadmoreButton.setBounds(82, 637, 192, 19);
		loadmoreButton.addMouseListener(ml);
		loadmoreButton.addActionListener(al);
		//border code copied from stack-overflow to add left padding to our border
		loadmoreButton.setBorder(grayPaddingborder);
		this.add(loadmoreButton);
		if(this.assignments.size() < 9) {
			loadmoreButton.setVisible(false);
		}
		
		for(int i = 0; i < this.assignmentButtons.size(); i++) {
//			this.assignmentButtons.get(i).setBackground(new Color(33,32,32));
			this.assignmentButtons.get(i).setBackground(Color.DARK_GRAY);
//			this.assignmentButtons.get(i).setBackground(new Color(26,148,184));
//			this.assignmentButtons.get(i).setContentAreaFilled(false);
			this.assignmentButtons.get(i).setForeground(Color.LIGHT_GRAY);
			this.assignmentButtons.get(i).setText("<html>Teams Completed<br />Teams:  , DueDate: ,Points: " + "10" + "</html>");
			this.assignmentButtons.get(i).setHorizontalAlignment(SwingConstants.LEFT);
			this.assignmentButtons.get(i).setFont(new Font("Times New Roman", Font.PLAIN, 16));	
			this.assignmentButtons.get(i).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			//border code was copied from stack-overflow to add left padding to our border
			this.assignmentButtons.get(i).setBorder(grayPaddingborder);
			this.assignmentButtons.get(i).addMouseListener(ml);
			this.assignmentButtons.get(i).addActionListener(al);
			this.assignmentButtons.get(i).setVisible(false);
			this.add(this.assignmentButtons.get(i));
		
		}
		int size = this.assignmentButtons.size();
		if(size > this.assignments.size()) {
			size = this.assignments.size();
		}
		String text;
		for(int i = 0; i < size; i++) {
			text = "<html><b>" + this.assignments.get(i).getTitle() + "</b><br />Team: "+this.assignments.get(i).getTeam().getName() +", DueDate: "
					+this.assignments.get(i).getDueDate().getFormattedDate() + ", Points: " + this.assignments.get(i).getTotalPoints() + "</html>";
			this.assignmentButtons.get(i).setText(text);
			this.assignmentButtons.get(i).setVisible(true);
		}
		
	}
}












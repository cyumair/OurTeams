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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.ourteams.backend.Assignment;
import com.ourteams.backend.AssignmentData;
import com.ourteams.backend.Faculty;

public class FacultyAssignmentPanel extends JPanel{
	/**
	 * This Panel displays list of students that uploaded the assignment
	 * Faculty member can click on a student button and view its assignment
	 * Faculty can also Edit the assignment or delete that assignment
	 */
	private static final long serialVersionUID = 1L;
	private Faculty faculty;
	private ArrayList<JButton> studentButtons = new ArrayList<JButton>();
	private JButton loadmoreButton;
	private JButton editButton;
	private JButton deleteButton;
	private CompoundBorder grayPaddingborder;
	private CompoundBorder redPaddingborder;
	private CompoundBorder greenPaddingborder;
	private CompoundBorder bluePaddingborder;
	private AssignmentPanel assignPanel;
	private Assignment assignment;
	private ArrayList<AssignmentData> assignmentData =  new ArrayList<AssignmentData>();
	private int pageNumber;
	
	public FacultyAssignmentPanel(AssignmentPanel assignPanel, Assignment assignment) {
		this.faculty = (Faculty)assignPanel.getUser();//no problem in casting, check has been already made in calling object
		this.assignment = assignment;
		this.assignPanel = assignPanel;
		this.assignmentData = assignment.getAssignmentData();
		initalize();
	}

	private void initalize() {
		this.setLayout(null);
		this.setBounds(0, 0, 1149, 687);
		this.setBackground(new Color(33, 32, 32));
		
		JLabel assignTitlelabel = new JLabel();
		assignTitlelabel.setText(this.assignment.getTitle());
		assignTitlelabel.setBounds(34, 23, 275, 28);
		assignTitlelabel.setFont(new Font("Cambria Math", Font.BOLD, 18));
		assignTitlelabel.setForeground(new Color(255, 255, 255));
		this.add(assignTitlelabel);
		
		JLabel duedatelabel = new JLabel();
		duedatelabel.setText("Due Date: " + this.assignment.getDueDate().getFormattedDate());
		duedatelabel.setForeground(Color.WHITE);
		duedatelabel.setFont(new Font("Cambria Math", Font.PLAIN, 12));
		duedatelabel.setBounds(945, 55, 181, 35);
		this.add(duedatelabel);
		
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
		
		
		createButtons();
		
	}
	
	class actionlistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent a) {
			if(a.getSource().equals(loadmoreButton)) {
					pageNumber++;
					goToNextPage();
			}
			//when delete button is clicked, the assignment is deleted and user is returned to view assignments panel
			else if(a.getSource().equals(deleteButton)) {
				int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to leave", "Leave Teams", JOptionPane.YES_NO_OPTION);
				if(confirm == 0) {
					faculty.deleteAssignment(assignment);
					assignPanel.refreshViewAssignment();
				}
			}
			else if(a.getSource().equals(editButton)) {
				new EditAssignmentDialog(assignment, faculty, assignPanel);
			}
			else if(a.getSource().equals(studentButtons.get(0))) {
//				System.out.println("caaled");
				new CheckAssignmentFrame(assignmentData.get(0 + pageNumber * 8), faculty, assignment, studentButtons.get(0));
			}
			else if(a.getSource().equals(studentButtons.get(1))) {
				new CheckAssignmentFrame(assignmentData.get(1 + pageNumber * 8), faculty, assignment, studentButtons.get(1));
			}
			else if(a.getSource().equals(studentButtons.get(2))) {
				new CheckAssignmentFrame(assignmentData.get(2 + pageNumber * 8), faculty, assignment, studentButtons.get(2));
			}
			else if(a.getSource().equals(studentButtons.get(3))) {
				new CheckAssignmentFrame(assignmentData.get(3 + pageNumber * 8), faculty, assignment, studentButtons.get(3));
			}
			else if(a.getSource().equals(studentButtons.get(4))) {
				new CheckAssignmentFrame(assignmentData.get(4 + pageNumber * 8), faculty, assignment, studentButtons.get(4));
			}
			else if(a.getSource().equals(studentButtons.get(5))) {
				new CheckAssignmentFrame(assignmentData.get(5 + pageNumber * 8), faculty, assignment, studentButtons.get(5));
			}
			else if(a.getSource().equals(studentButtons.get(6))) {
				new CheckAssignmentFrame(assignmentData.get(6 + pageNumber * 8), faculty, assignment, studentButtons.get(6));
			}
			else if(a.getSource().equals(studentButtons.get(7))) {
				new CheckAssignmentFrame(assignmentData.get(7 + pageNumber * 8), faculty, assignment, studentButtons.get(7));
			}

			
		}
		
	}
	
	//Mouse Listener for hover over effects
	class mouselistener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {}

		@Override
		public void mouseEntered(MouseEvent m) {
			
			if(m.getSource().equals(studentButtons.get(0))){
				studentButtons.get(0).setBorder(redPaddingborder);
			}
			else if(m.getSource().equals(studentButtons.get(1))){
				studentButtons.get(1).setBorder(bluePaddingborder);
			}
			else if(m.getSource().equals(studentButtons.get(2))){
				studentButtons.get(2).setBorder(greenPaddingborder);
			}
			else if(m.getSource().equals(studentButtons.get(3))){
				studentButtons.get(3).setBorder(bluePaddingborder);
			}
			else if(m.getSource().equals(studentButtons.get(4))){
				studentButtons.get(4).setBorder(greenPaddingborder);
			}
			else if(m.getSource().equals(studentButtons.get(5))){
				studentButtons.get(5).setBorder(redPaddingborder);
			}
			else if(m.getSource().equals(studentButtons.get(6))){
				studentButtons.get(6).setBorder(greenPaddingborder);
			}
			else if(m.getSource().equals(studentButtons.get(7))){
				studentButtons.get(7).setBorder(redPaddingborder);
			}
			else if(m.getSource().equals(loadmoreButton)){
				loadmoreButton.setBorder(redPaddingborder);
			}
			else if(m.getSource().equals(editButton)){
				editButton.setBorder(new LineBorder(Color.RED));
			}
			else if(m.getSource().equals(deleteButton)){
				deleteButton.setBorder(new LineBorder(Color.RED));
			}
			
		
		}

		@Override
		public void mouseExited(MouseEvent m) {
			if(m.getSource().equals(studentButtons.get(0))){
				studentButtons.get(0).setBorder(grayPaddingborder);
			}
			else if(m.getSource().equals(studentButtons.get(1))){
				studentButtons.get(1).setBorder(grayPaddingborder);
			}
			else if(m.getSource().equals(studentButtons.get(2))){
				studentButtons.get(2).setBorder(grayPaddingborder);
			}
			else if(m.getSource().equals(studentButtons.get(3))){
				studentButtons.get(3).setBorder(grayPaddingborder);
			}
			else if(m.getSource().equals(studentButtons.get(4))){
				studentButtons.get(4).setBorder(grayPaddingborder);
			}
			else if(m.getSource().equals(studentButtons.get(5))){
				studentButtons.get(5).setBorder(grayPaddingborder);
			}
			else if(m.getSource().equals(studentButtons.get(6))){
				studentButtons.get(6).setBorder(grayPaddingborder);
			}
			else if(m.getSource().equals(studentButtons.get(7))){
				studentButtons.get(7).setBorder(grayPaddingborder);
			}
			else if(m.getSource().equals(loadmoreButton)){
				loadmoreButton.setBorder(grayPaddingborder);
			}
			else if(m.getSource().equals(editButton)){
				editButton.setBorder(new LineBorder(Color.GRAY, 2));
			}
			else if(m.getSource().equals(deleteButton)){
				deleteButton.setBorder(new LineBorder(Color.GRAY, 2));
			}
					
		}

		@Override
		public void mousePressed(MouseEvent arg0) {}

		@Override
		public void mouseReleased(MouseEvent arg0) {}
		
	}
	
	
	/*
	 * When next button is clicked the student buttons on the page are refreshed with new ones
	 */
	public void goToNextPage() {
		for(int i = 0; i < this.studentButtons.size(); i++) {
			this.studentButtons.get(i).setVisible(false);
		}
		
		int buttonindex = 0;
		for(int i = this.pageNumber*8; i < this.assignmentData.size() && buttonindex < 8; i++) {
			System.out.println(assignmentData.size());
			//way of writing this text copied from stack overflow
			String text = "<html><b>" + this.assignmentData.get(i).getStudent().getName() + "</b><br />"
						+ this.assignmentData.get(i).getStudent().getEmail()+ "</html>";
			if(this.assignmentData.get(i).getObtainedPoints() != -1) {//if student assignment has been checked
				text = "<html><b>" + this.assignmentData.get(i).getStudent().getName() + 
						"</b><br />"+ this.assignmentData.get(i).getStudent().getEmail()+ 
						" || CHECKED, " +this.assignmentData.get(i).getObtainedPoints() + "-points. </html>";
			}

			this.studentButtons.get(buttonindex).setText(text);
			this.studentButtons.get(buttonindex).setVisible(true);
			buttonindex++;
		}
		
		if(this.assignmentData.size() < ((8* (pageNumber+1))+1)) {
			this.loadmoreButton.setVisible(false);
		} 
		
	}

	//This method initializes all the buttons on the Panel
	private void createButtons() {
		
		MouseListener ml = new mouselistener();
		ActionListener al = new actionlistener();
		
		JButton studentbutton1 = new JButton();
		studentbutton1.setBounds(82, 93, 697, 57);
		this.studentButtons.add(studentbutton1);
		
		JButton studentbutton2 = new JButton();
		studentbutton2.setBounds(82, 161, 697, 57);
		this.studentButtons.add(studentbutton2);
		
		JButton studentbutton3 = new JButton();
		studentbutton3.setBounds(82, 229, 697, 57);
		this.studentButtons.add(studentbutton3);
		
		JButton studentbutton4 = new JButton();	
		studentbutton4.setBounds(82, 297, 697, 57);
		this.studentButtons.add(studentbutton4);
		
		JButton studentbutton5 = new JButton();
		studentbutton5.setBounds(82, 365, 697, 57);
		this.studentButtons.add(studentbutton5);
		
		JButton studentbutton6 = new JButton();
		studentbutton6.setBounds(82, 433, 697, 57);
		this.studentButtons.add(studentbutton6);
		
		JButton studentbutton7 = new JButton();
		studentbutton7.setBounds(82, 501, 697, 57);
		this.studentButtons.add(studentbutton7);
		
		JButton studentbutton8 = new JButton();
		studentbutton8.setBounds(82, 569, 697, 57);
		this.studentButtons.add(studentbutton8);
		
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
		if(this.assignmentData.size() < 9) {
			loadmoreButton.setVisible(false);
		}
		
		editButton = new JButton("Edit");
		editButton.setForeground(Color.LIGHT_GRAY);
		editButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		editButton.setContentAreaFilled(false);
		editButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		editButton.setBackground(Color.LIGHT_GRAY);
		editButton.setBounds(901, 21, 98, 28);
		editButton.setBorder(new LineBorder(new Color(52, 50, 50), 3));
		editButton.addActionListener(al);
		editButton.addMouseListener(ml);
		this.add(editButton);
		
		deleteButton = new JButton("Delete");
		deleteButton.setForeground(Color.LIGHT_GRAY);
		deleteButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		deleteButton.setContentAreaFilled(false);
		deleteButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		deleteButton.setBackground(Color.LIGHT_GRAY);
		deleteButton.setBounds(1009, 21, 98, 28);
		deleteButton.setBorder(new LineBorder(new Color(52, 50, 50), 3));
		deleteButton.addActionListener(al);
		deleteButton.addMouseListener(ml);
		this.add(deleteButton);
		
		for(int i = 0; i < this.studentButtons.size(); i++) {
//			this.studentButtons.get(i).setContentAreaFilled(false);
			this.studentButtons.get(i).setForeground(Color.LIGHT_GRAY);
			this.studentButtons.get(i).setBackground(Color.DARK_GRAY);
			this.studentButtons.get(i).setText("<html>Teams Completed<br />Teams:  , DueDate: ,Points: " + "10" + "</html>");
			this.studentButtons.get(i).setHorizontalAlignment(SwingConstants.LEFT);
			this.studentButtons.get(i).setFont(new Font("Times New Roman", Font.PLAIN, 16));	
			this.studentButtons.get(i).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			//border code was copied from stack overflow to add left padding to our border
			this.studentButtons.get(i).setBorder(grayPaddingborder);
			this.studentButtons.get(i).addMouseListener(ml);
			this.studentButtons.get(i).addActionListener(al);
			this.studentButtons.get(i).setVisible(false);
			this.add(this.studentButtons.get(i));
		
		}
		int size = this.studentButtons.size();
		if(size > this.assignmentData.size()) {
			size = this.assignmentData.size();
		}
		
		for(int i = 0; i < size; i++) {
			String text = "<html><b>" + this.assignmentData.get(i).getStudent().getName() + "</b><br />"
						+ this.assignmentData.get(i).getStudent().getEmail()+ "</html>";
			if(this.assignmentData.get(i).getObtainedPoints() != -1) {//if student assignment has been checked
				text = "<html><b>" + this.assignmentData.get(i).getStudent().getName() + 
						"</b><br />"+ this.assignmentData.get(i).getStudent().getEmail()+ 
						" || CHECKED " +this.assignmentData.get(i).getObtainedPoints() + "points. </html>";
			}
		
			this.studentButtons.get(i).setText(text);
			this.studentButtons.get(i).setVisible(true);
		}
		
	}
}












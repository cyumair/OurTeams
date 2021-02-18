package com.ourteams.window;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import com.ourteams.backend.Assignment;
import com.ourteams.backend.AssignmentData;
import com.ourteams.backend.Student;
import com.ourteams.backend.User;

public class StudentAssignmentPanel extends JPanel{

	/**
	 * This panel will take a file from student and submit that file in the assignment
	 */
	private static final long serialVersionUID = 1L;
	private JLabel titlefield;
	private JTextPane detailArea;
	private JLabel dueDatefield;
	private JLabel teamnameField;
	private JButton addworkButton;
	private JLabel pointsField;
	private Assignment assignment;
	private JButton submitButton;
	private JButton viewFileButton;
	private File file;
	private JLabel errorlabel;
	private JFileChooser filechooser;
	private Student student;
	private JLabel alreadysubmittedlabel;
	private JLabel submitagainlabel;
	public StudentAssignmentPanel(User user, Assignment assignment) {
		this.student = (Student)user; //check of class type is already made in calling object
		this.assignment = assignment;
		for(AssignmentData data: assignment.getAssignmentData()) {
			if(data.getStudent().equals(this.student)) {
				this.file = data.getFile();
			}
		}
		initialize();
	}


	private void initialize() {
		this.setBounds(0, 0, 1149, 687);
		Color graycolor = new Color(52, 50 ,50);
		this.setBackground(graycolor);
		this.setLayout(null);
		JLabel assignemntsLabel = new JLabel("Assignment");
		assignemntsLabel.setBounds(56, 21, 400, 50);
		assignemntsLabel.setFont(new Font("Lucida Calligraphy", Font.BOLD, 24));
		assignemntsLabel.setForeground(new Color(128, 128, 128));
		this.add(assignemntsLabel);
		
//		LineBorder gray border = new LineBorder(new Color(52, 50 ,50));
		
		titlefield = new JLabel("Assignment Title");
		titlefield.setText(this.assignment.getTitle());
		titlefield.setForeground(Color.WHITE);
		titlefield.setFont(new Font("Cambria Math", Font.BOLD, 24));
		titlefield.setBounds(69, 95, 439, 46);
		this.add(titlefield);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(79, 229, 945, 64);
		scrollPane.setBorder(new LineBorder(graycolor));
		scrollPane.getVerticalScrollBar().setBackground(Color.DARK_GRAY);
		scrollPane.getVerticalScrollBar().setForeground(Color.BLACK);
		detailArea = new JTextPane();
		detailArea.setText(this.assignment.getDetail());
		detailArea.setBackground(graycolor);
		detailArea.setEditable(false);
		detailArea.setForeground(Color.WHITE);
		detailArea.setFont(new Font("Cambria Math", Font.PLAIN, 16));
//		detailArea.setBounds(79, 229, 945, 64);
		scrollPane.setViewportView(detailArea);
		this.add(scrollPane);
		
		dueDatefield = new JLabel("Due Date");
		dueDatefield.setText("Due Date: " + assignment.getDueDate().getFormattedDate());
		dueDatefield.setForeground(Color.WHITE);
		dueDatefield.setFont(new Font("Cambria Math", Font.PLAIN, 15));
		dueDatefield.setBounds(69, 172, 230, 28);
		this.add(dueDatefield);
		
		errorlabel = new JLabel("Text(.txt) Files is supported");
		errorlabel.setForeground(Color.RED);
		errorlabel.setFont(new Font("Cambria Math", Font.PLAIN, 15));
		errorlabel.setBounds(69, 385, 300, 28);
		this.add(errorlabel);
		
		submitagainlabel = new JLabel("Add a new file to submit again");
		submitagainlabel.setForeground(Color.RED);
		submitagainlabel.setFont(new Font("Cambria Math", Font.PLAIN, 15));
		submitagainlabel.setBounds(69, 410, 300, 28);
		this.add(submitagainlabel);
		
		teamnameField = new JLabel("Team Name");
		teamnameField.setText(assignment.getTeam().getName());
		teamnameField.setForeground(Color.WHITE);
		teamnameField.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 19));
		teamnameField.setBackground(graycolor);
		teamnameField.setBounds(69, 140, 187, 28);
		this.add(teamnameField);
		
		ActionListener al = new ActionHandler();
		MouseListener ml = new MouseHandler();
		
		submitButton = new JButton("Submit");
		submitButton.setContentAreaFilled(false);
		submitButton.setVisible(false);
		submitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		submitButton.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
		submitButton.setForeground(Color.WHITE);
		submitButton.setContentAreaFilled(false);
		submitButton.setBounds(989, 16, 102, 35);
		submitButton.setBorder(new LineBorder(Color.GRAY, 2));
		submitButton.addActionListener(al);
		submitButton.addMouseListener(ml);
		this.add(submitButton);
		
		alreadysubmittedlabel = new JLabel("Submitted");
		alreadysubmittedlabel.setForeground(Color.RED);
		alreadysubmittedlabel.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		alreadysubmittedlabel.setBounds(975, 16, 135, 40);
		this.add(alreadysubmittedlabel);
		
		addworkButton = new JButton("Add work");
		addworkButton.setBorder(new LineBorder(Color.GRAY, 2));
		addworkButton.setForeground(Color.WHITE);
		addworkButton.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 14));
		addworkButton.setContentAreaFilled(false);
		addworkButton.setBounds(67, 318, 122, 21);
		addworkButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addworkButton.addActionListener(al);
		addworkButton.addMouseListener(ml);
		this.add(addworkButton);
		
		viewFileButton = new JButton("View File");
		viewFileButton.setBorder(new LineBorder(Color.GRAY, 2));
		viewFileButton.setForeground(Color.WHITE);
		viewFileButton.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 14));
		viewFileButton.setContentAreaFilled(false);
		viewFileButton.setBounds(67, 350, 122, 21);
		viewFileButton.addActionListener(al);
		viewFileButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		viewFileButton.addMouseListener(ml);
		
		if(this.file == null) {
			viewFileButton.setVisible(false);
			alreadysubmittedlabel.setVisible(false);
			submitagainlabel.setVisible(false);
		}
		this.add(viewFileButton);
		
		JLabel labeldetail = new JLabel("Detail:");
		labeldetail.setForeground(Color.WHITE);
		labeldetail.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
		labeldetail.setBounds(69, 205, 120, 28);
		this.add(labeldetail);
		
		pointsField = new JLabel();
		pointsField.setText("Possible Points: " + assignment.getTotalPoints());
		pointsField.setForeground(Color.WHITE);
		pointsField.setFont(new Font("Cambria Math", Font.PLAIN, 15));
		pointsField.setBounds(818, 95, 206, 28);
		this.add(pointsField);
		
		filechooser = new JFileChooser();
		filechooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	}
	class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent a) {
			//When user clicks on add work button then upload a file method is called
			if(a.getSource().equals(addworkButton)) {
				uploadAFile();
			}
			//When viewFileButton is clicked new frame is opened which displays the contents of the file
			else if(a.getSource().equals(viewFileButton)){
				new ViewFileFrame(file);
			}
			/*
			 * When submit button is pressed then it checks if assignment is not past due date,
			 * if not then submits it, else displays an error message
			 */
			else if(a.getSource().equals(submitButton)) {
				if(!pastDueDate(assignment)) {
					student.submitAssignment(file, assignment);
					errorlabel.setText("Submitted Successfully");
					alreadysubmittedlabel.setVisible(true);
					submitButton.setVisible(false);
					submitagainlabel.setVisible(true);
				}
				else {
					errorlabel.setText("Assignment Time Ended, unable to upload file");
				}
			}
		}
	
	}
	
	//Mouse Listener for hover over button effects
	class MouseHandler implements MouseListener {
		
	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent m) {
		if(m.getSource().equals(addworkButton)){
			addworkButton.setBorder(new LineBorder(Color.RED));
		}
		else if(m.getSource().equals(submitButton)){
			submitButton.setBorder(new LineBorder(Color.RED));
		}
		else if(m.getSource().equals(viewFileButton)){
			viewFileButton.setBorder(new LineBorder(Color.RED));
		}
		
	}

	@Override
	public void mouseExited(MouseEvent m) {
		if(m.getSource().equals(addworkButton)){
			addworkButton.setBorder(new LineBorder(Color.GRAY, 2));
		}
		else if(m.getSource().equals(submitButton)){
			submitButton.setBorder(new LineBorder(Color.GRAY, 2));
		}
		else if(m.getSource().equals(viewFileButton)){
			viewFileButton.setBorder(new LineBorder(Color.GRAY, 2));
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

	}
	
	//This method selects using JFileChooser and stores it in a variable
	public void uploadAFile() {
				int response = filechooser.showOpenDialog(null);
				if(response == JFileChooser.APPROVE_OPTION) {
					this.file = filechooser.getSelectedFile();
					if(this.file.isFile()) {
						alreadysubmittedlabel.setVisible(false);
						submitButton.setVisible(true);
						viewFileButton.setVisible(true);
						submitagainlabel.setVisible(false);
						errorlabel.setText("");
					}
					else {
						errorlabel.setText("There was a problem uploading the file.");
					}
				}	
				
	}
	
	//This method checks if assignment is past due date or not
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
	
}

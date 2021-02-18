package com.ourteams.window;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import com.ourteams.backend.Assignment;
import com.ourteams.backend.AssignmentData;
import com.ourteams.backend.Faculty;

public class CheckAssignmentFrame extends JFrame{
	/**
	 * This frame views the assignment File of the student and allows the Faculty member to mark that assignment
	 */
	private static final long serialVersionUID = 1L;
	private Scanner input;
	private JTextField pointsField;
	private JTextPane textPane;
	private JButton cancelButton;
	private JButton confirmButton;
	private JButton studentButton;
	private Faculty faculty;
	private Assignment assignment;
	private AssignmentData data;
	
	public CheckAssignmentFrame(AssignmentData data, Faculty faculty, Assignment assignment, JButton studentbutton) {
		this.assignment = assignment;
		this.data = data;
		this.faculty = faculty;
		this.studentButton = studentbutton;
		initialize();
	}

	private void initialize() {
		
		setBounds(100, 100, 980, 727);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 964, 700);
		panel.setBackground(Color.BLACK);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.DARK_GRAY);
		buttonPanel.setBounds(10, 647, 944, 31);
		panel.add(buttonPanel);
		buttonPanel.setLayout(null);
		
		JLabel studentdetail = new JLabel();
		studentdetail.setText(this.data.getStudent().getName() + " || " + this.data.getStudent().getEmail());
		studentdetail.setForeground(Color.LIGHT_GRAY);
		studentdetail.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 15));
		studentdetail.setBackground(Color.BLACK);
		studentdetail.setBounds(10, 0, 425, 29);
		buttonPanel.add(studentdetail);
		
		JLabel awardpointslabel = new JLabel("|| Award Points out of 10:");
		awardpointslabel.setText("|| Award Points out of " + this.assignment.getTotalPoints() + ": ");
		awardpointslabel.setBackground(Color.BLACK);
		awardpointslabel.setBounds(445, 2, 225, 29);
		awardpointslabel.setForeground(Color.LIGHT_GRAY);
		awardpointslabel.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 15));
		buttonPanel.add(awardpointslabel);
		
		pointsField = new JTextField();
		pointsField.setFont(new Font("Cambria Math", Font.PLAIN, 15));
		pointsField.setBackground(Color.LIGHT_GRAY);
		pointsField.setColumns(10);
		pointsField.setBounds(673, 2, 43, 27);
		buttonPanel.add(pointsField);
		
		MouseListener ml = new MouseHandler();
		ActionListener al = new ActionHandler();
		cancelButton = new JButton("Cancel");
		cancelButton.setForeground(Color.LIGHT_GRAY);
		cancelButton.setFont(new Font("MV Boli", Font.PLAIN, 16));
		cancelButton.setContentAreaFilled(false);
		cancelButton.setBackground(Color.DARK_GRAY);
		cancelButton.setBorder(new LineBorder(Color.WHITE, 2));
		cancelButton.setBounds(840, 1, 104, 30);
		cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancelButton.addMouseListener(ml);
		cancelButton.addActionListener(al);
		buttonPanel.add(cancelButton);
		
		confirmButton = new JButton("Confirm");
		confirmButton.setForeground(Color.LIGHT_GRAY);
		confirmButton.setFont(new Font("MV Boli", Font.PLAIN, 16));
		confirmButton.setBorder(new LineBorder(Color.WHITE, 2));
		confirmButton.setContentAreaFilled(false);
		confirmButton.setBackground(Color.DARK_GRAY);
		confirmButton.setBounds(726, 2, 104, 29);
		confirmButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		confirmButton.addMouseListener(ml);
		confirmButton.addActionListener(al);
		buttonPanel.add(confirmButton);
		
		JLabel bgLabel = new JLabel("");
		bgLabel.setBounds(0, 0, 944, 31);
		bgLabel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		buttonPanel.add(bgLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 944, 625);
		scrollPane.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel.add(scrollPane);
		
		textPane = new JTextPane();
		textPane.setForeground(Color.WHITE);
		textPane.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textPane.setEditable(false);
		textPane.setBackground(Color.DARK_GRAY);
		scrollPane.setViewportView(textPane);
		
		try {
			input = new Scanner(this.data.getFile());
			if(this.data.getFile().isFile()) {
				while(input.hasNextLine()) {
					String line = input.nextLine();
					textPane.setText(textPane.getText() + "\n" + line );	
				}
			}
			else {
				textPane.setText("File Problem");
			}
			input.close();
		}
		catch (FileNotFoundException e) {
			textPane.setText("Error File not found");
	//					e.printStackTrace();
		}
		
		this.setVisible(true);		
	}
	
	
	class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent a) {
			//If confirm is pressed the return assignment method is called which sets marks for that student and closes this frame
			if(a.getSource().equals(confirmButton)) {
				try {
					double marks = Double.parseDouble(pointsField.getText());
					if(marks > assignment.getTotalPoints()) {
						JOptionPane.showMessageDialog(null, "Obtained points must be less than or equal to total points");
					}
					else if(marks < 0) {
						JOptionPane.showMessageDialog(null, "Points cannot be negative");
					}
					else {
						faculty.returnAssignment(data, assignment, marks);
						String text = "<html><b>" + data.getStudent().getName() + 
						"</b><br />"+ data.getStudent().getEmail()+ 
						" || CHECKED, " + data.getObtainedPoints() + "-points. </html>";
						studentButton.setText(text);
						dispose();
					}
				}
				catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Points can only contain integers");
				}
	
			}
			//if cancel is called the frame is simply disposed (closed)
			else if(a.getSource().equals(cancelButton)) {
				dispose();
			}
			
		}
		
	}
	
	//Mouse Listener for hover over effects on buttons
	class MouseHandler implements MouseListener{

		@Override
		public void mouseEntered(MouseEvent m) {			
			if(m.getSource().equals(confirmButton)) {
				confirmButton.setBorder(new LineBorder(Color.RED));
			}
			else if(m.getSource().equals(cancelButton)) {
				cancelButton.setBorder(new LineBorder(Color.RED));
			}
			
		}

		@Override
		public void mouseExited(MouseEvent m) {
			if(m.getSource().equals(confirmButton)) {
				confirmButton.setBorder(new LineBorder(Color.GRAY, 2));
			}
			else if(m.getSource().equals(cancelButton)) {
				cancelButton.setBorder(new LineBorder(Color.GRAY, 2));
			}			
			
		}
		
		public void mouseClicked(MouseEvent m) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
		
	}
}

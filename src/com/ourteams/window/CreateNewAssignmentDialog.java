package com.ourteams.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.ourteams.backend.Faculty;
import com.ourteams.backend.Date;

public class CreateNewAssignmentDialog extends JDialog{

	/**
	 * A Dialog which asks for assignment details and creates it.
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel mainPanel;
	private JTextField titleField;
	private JTextPane detailsField;
	private JComboBox<String> yearBox;
	private JComboBox<String> hourBox;
	private JComboBox<String> minuteBox;
	private JTextField pointsField;
	private JComboBox<String> dayBox;
	private JComboBox<String> teamsBox;
	private JComboBox<String> monthBox;
	private JLabel errorlabel;
	private JButton createButton;
	private LineBorder grayborder = new LineBorder(new Color(52, 50, 50), 2);
	private Faculty facultyMemeber;
	private AssignmentPanel assignmentPanel;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	
	public CreateNewAssignmentDialog(AssignmentPanel assignPanel) {
		
		this.assignmentPanel = assignPanel;
		//only faculty member can open this create assignment panel so no need to check
		this.facultyMemeber = (Faculty)assignPanel.getUser(); 
		setBounds(100, 100, 701, 612);
		this.setLayout(null);
		mainPanel  = new JPanel();
		mainPanel.setBounds(0, 0, 698, 573);
		mainPanel.setBackground(new Color(25,20,20));
		this.setBackground(new Color(52,50,50));
		setContentPane(mainPanel);
		mainPanel.setLayout(null);
		
		
		JLabel toplabel = new JLabel("Create Assignment");
		toplabel.setFont(new Font("Lucida Calligraphy", Font.BOLD, 24));
		toplabel.setForeground(Color.RED);
		toplabel.setBounds(215, 30, 271, 33);
		mainPanel.add(toplabel);
		
		titleField = new JTextField();
		titleField.setText("Title");
		titleField.setForeground(Color.WHITE);
		titleField.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 17));
		titleField.setCaretColor(Color.WHITE);
		titleField.setBackground(Color.DARK_GRAY);
		titleField.setBounds(89, 109, 371, 40);
		titleField.setBorder(grayborder);
		mainPanel.add(titleField);
		titleField.setColumns(10);
		
		pointsField = new JTextField();
		pointsField.setText("Total Points");
		pointsField.setForeground(Color.WHITE);
		pointsField.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 14));
		pointsField.setColumns(10);
		pointsField.setCaretColor(Color.WHITE);
		pointsField.setBackground(Color.DARK_GRAY);
		pointsField.setBounds(470, 109, 124, 40);
		pointsField.setBorder(grayborder);
		pointsField.addMouseListener(new mouselistener());
		mainPanel.add(pointsField);
				
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(89, 160, 505, 113);
		scrollPane.setBorder(grayborder);
		detailsField = new JTextPane();
		detailsField.setText("Details");
		detailsField.setForeground(Color.WHITE);
		detailsField.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
		detailsField.setCaretColor(Color.WHITE);
		detailsField.setBackground(Color.DARK_GRAY);
		detailsField.setBorder(grayborder);
		scrollPane.setViewportView(detailsField);
		mainPanel.add(scrollPane);
		
		JLabel dueDateLabel = new JLabel("Due Date:");
		dueDateLabel.setForeground(Color.WHITE);
		dueDateLabel.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 14));
		dueDateLabel.setBounds(86, 297, 124, 27);
		mainPanel.add(dueDateLabel);
		
		errorlabel = new JLabel();
		errorlabel.setForeground(Color.RED);
		errorlabel.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 15));
		errorlabel.setBounds(164, 525, 430, 20);
		mainPanel.add(errorlabel);
		
		String []months = new String[12];
		String []hours = new String[24];
		String []minutes = new String[60];
		String []years = {"2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"};
		String []days = new String[31];
		for(int i = 0; i < 60; i++) {
			if(i < 31) {
				days[i] = Integer.toString(i+1);
			}
			if(i < 24) {
				hours[i] = Integer.toString(i);
			}
			if(i < 12) {
				months[i] = Integer.toString(i+1);
			}
			minutes[i] = Integer.toString(i);
		}
		
		//combo box does not accept array list so converting it to an array
		teamsBox = new JComboBox(this.facultyMemeber.getTeams().toArray());
		teamsBox.setForeground(Color.WHITE);
		teamsBox.setBackground(Color.DARK_GRAY);
		teamsBox.setBounds(86, 437, 219, 27);
		mainPanel.add(teamsBox);

		
		JLabel teamslabel = new JLabel("Teams:");
		teamslabel.setForeground(Color.WHITE);
		teamslabel.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 14));
		teamslabel.setBounds(86, 410, 124, 27);
		mainPanel.add(teamslabel);
		
		dayBox = new JComboBox<String>(days);
		dayBox.setForeground(Color.WHITE);
		dayBox.setBackground(Color.DARK_GRAY);
		dayBox.setBounds(86, 355, 44, 33);
		dayBox.setSelectedIndex(15);
		mainPanel.add(dayBox);
		
		
		monthBox = new JComboBox<String>(months);
		monthBox.setForeground(Color.WHITE);
		monthBox.setBackground(Color.DARK_GRAY);
		monthBox.setBounds(140, 355, 59, 33);
		monthBox.setSelectedIndex(6);
		mainPanel.add(monthBox);
		
		
		yearBox = new JComboBox<String>(years);
		yearBox.setForeground(Color.WHITE);
		yearBox.setBackground(Color.DARK_GRAY);
		yearBox.setBounds(209, 355, 54, 33);
		mainPanel.add(yearBox);
		
		hourBox = new JComboBox<String>(hours);
		hourBox.setForeground(Color.WHITE);
		hourBox.setBackground(Color.DARK_GRAY);
		hourBox.setBounds(479, 355, 44, 33);
		hourBox.setSelectedIndex(12);
		mainPanel.add(hourBox);
		
		minuteBox = new JComboBox<String>(minutes);
		minuteBox.setForeground(Color.WHITE);
		minuteBox.setBackground(Color.DARK_GRAY);
		minuteBox.setBounds(532, 355, 59, 33);
		minuteBox.setSelectedIndex(30);
		mainPanel.add(minuteBox);
		
		JLabel daylabel = new JLabel("Day");
		daylabel.setForeground(Color.LIGHT_GRAY);
		daylabel.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 14));
		daylabel.setBounds(86, 325, 59, 27);
		mainPanel.add(daylabel);
		
		JLabel monthlabel = new JLabel("Month");
		monthlabel.setForeground(Color.LIGHT_GRAY);
		monthlabel.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 14));
		monthlabel.setBounds(140, 325, 59, 27);
		mainPanel.add(monthlabel);
		
		JLabel yearlabel = new JLabel("Year");
		yearlabel.setForeground(Color.LIGHT_GRAY);
		yearlabel.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 14));
		yearlabel.setBounds(209, 325, 59, 27);
		mainPanel.add(yearlabel);
		
		JLabel hourlabel = new JLabel("Hour");
		hourlabel.setForeground(Color.LIGHT_GRAY);
		hourlabel.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 14));
		hourlabel.setBounds(479, 325, 49, 27);
		mainPanel.add(hourlabel);
		
		JLabel minutelabel = new JLabel("Minute");
		minutelabel.setForeground(Color.LIGHT_GRAY);
		minutelabel.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 14));
		minutelabel.setBounds(532, 325, 59, 27);
		mainPanel.add(minutelabel);

		ActionListener al = new ActionHandler();
		createButton = new JButton("Create");
		createButton.addActionListener(al);
		createButton.addMouseListener(new mouselistener());
		createButton.setContentAreaFilled(false);
		createButton.setForeground(Color.RED);
		createButton.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 18));
		createButton.setBounds(280, 481, 118, 33);
		createButton.setBorder(new LineBorder(Color.GRAY, 2));
		mainPanel.add(createButton);
		
		this.setVisible(true);
		
	}
	
	class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent a) {
			/*
			 * If create button is pressed then collect the data entered
			 * check if it is right, then call the create assignment method of faculty class with that data provided
			 */
			if(a.getSource().equals(createButton)) {
				String title = titleField.getText();
				if(title.equals("")) {
					errorlabel.setText("  Assignment Title cannot be left empty");
				}
				else {
					String details = detailsField.getText();
					//combo boxes only contain digits so there wont be error of parse int and the data type is string so no error in string casting
					String day = (String)dayBox.getSelectedItem();
					String month = (String)monthBox.getSelectedItem();
					String year = (String)yearBox.getSelectedItem();
					String hour = (String)hourBox.getSelectedItem();
					String minutes = (String)minuteBox.getSelectedItem();
					Date date = new Date(day, month, year, hour, minutes);
					int teamindex = teamsBox.getSelectedIndex();
					
					try {
						double totalpoints = Double.parseDouble(pointsField.getText());
						facultyMemeber.createNewAssignmnet(title, details, totalpoints, date, teamindex);
						assignmentPanel.refreshViewAssignment();
						dispose();
					}
					catch(Exception e) {
						pointsField.setText("");
						errorlabel.setText("  Total Points Should only contain digits");
					}
				}
				
			}
			
		}
		
	}
	
	//Mouse Listener for hover over mouse effects
	class mouselistener implements MouseListener {

		@Override
		public void mouseEntered(MouseEvent m) {
			if(m.getSource().equals(createButton)) {
				createButton.setBorder(new LineBorder(Color.RED));
				createButton.setForeground(Color.WHITE);
			}
		}

		@Override
		public void mouseExited(MouseEvent m) {
			if(m.getSource().equals(createButton)) {
				createButton.setBorder(new LineBorder(Color.GRAY, 2));
				createButton.setForeground(Color.WHITE);
			}
			
		}

		public void mouseClicked(MouseEvent m) {
			if(m.getSource().equals(pointsField)) {
				pointsField.setText("");
			}
		}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
		
	}
	

}

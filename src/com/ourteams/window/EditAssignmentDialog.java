package com.ourteams.window;

import com.ourteams.backend.Assignment;
import com.ourteams.backend.Faculty;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EditAssignmentDialog extends JDialog {
	/**
	 *  A Dialog that edits the assignment details
	 */
	private static final long serialVersionUID = 1L;
	private AssignmentPanel assignPanel;
    private JPanel mainPanel;
    private JLabel editAssignmentLabel;
    private JTextField textFieldTitle;
    private JTextField textFieldPoints;
    private JTextPane detailsTextPane;
    private JButton updateButton;
    private JLabel minutesLabel;
    private JComboBox<String> comboBox;
    private JComboBox<String> comboBoxHours;
    private JLabel hoursLabel;
    private JLabel yearLabel;
    private JLabel monthLabel;
    private JLabel dayLabel;
    private JComboBox<String> comboBoxYear;
    private JComboBox<String> comboBoxmonth;
    private JComboBox<String> comboBoxDay;
    private JLabel duedateLabel;
    private JLabel errorLabel;
    private Assignment assignment;
    private Faculty faculty;
    
    public EditAssignmentDialog(Assignment assignment, Faculty faculty, AssignmentPanel assignPanel){
    	this.assignPanel = assignPanel;
        this.assignment=assignment;
        this.faculty=faculty;
        initialize();
        this.setVisible(true);

    }
    public void initialize(){
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	mainPanel  = new JPanel();
		mainPanel.setBounds(0, 0, 698, 573);
		mainPanel.setBackground(new Color(25,20,20));
		this.setBackground(new Color(25,20,20));
		setContentPane(mainPanel);
		mainPanel.setLayout(null);
        this.setBounds(119, 11, 688, 483);
        this.setLayout(null);
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
//       =====================================================================================

        editAssignmentLabel = new JLabel("    Edit Assignment");
        editAssignmentLabel.setForeground(Color.RED);
        editAssignmentLabel.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 18));
        editAssignmentLabel.setBounds(235, 27, 195, 30);
        mainPanel.add(editAssignmentLabel);

        textFieldTitle = new JTextField();
        textFieldTitle.setBackground(Color.DARK_GRAY);
        textFieldTitle.setForeground(Color.WHITE);
        textFieldTitle.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 18));
        textFieldTitle.setText("title");
        textFieldTitle.setBorder(new LineBorder(Color.GRAY, 2));
        textFieldTitle.setCaretColor(Color.WHITE);
        textFieldTitle.setBounds(89, 88, 350, 41);
        mainPanel.add(textFieldTitle);
        textFieldTitle.setColumns(10);
        textFieldTitle.setText(assignment.getTitle());


        textFieldPoints = new JTextField();
        textFieldPoints.setText("Points");
        textFieldPoints.setForeground(Color.WHITE);
        textFieldPoints.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 18));
        textFieldPoints.setColumns(10);
        textFieldPoints.setBorder(new LineBorder(Color.GRAY, 2));
        textFieldPoints.setCaretColor(Color.WHITE);
        textFieldPoints.setBackground(Color.DARK_GRAY);
        textFieldPoints.setBounds(470, 88, 124, 40);
        mainPanel.add(textFieldPoints);
        String points="";
        points+=assignment.getTotalPoints();
        textFieldPoints.setText(points);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(new LineBorder(Color.GRAY, 2));
        scrollPane.setBounds(89, 160, 505, 113);
        detailsTextPane = new JTextPane();
        detailsTextPane.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 18));
        detailsTextPane.setForeground(Color.WHITE);
        detailsTextPane.setCaretColor(Color.WHITE);
        detailsTextPane.setBackground(Color.DARK_GRAY);
        detailsTextPane.setText(assignment.getDetail());
        scrollPane.setViewportView(detailsTextPane);
        mainPanel.add(scrollPane);
        
        duedateLabel = new JLabel("Due Date:");
        duedateLabel.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 14));
        duedateLabel.setBounds(96, 295, 105, 14);
        duedateLabel.setForeground(Color.WHITE);
        mainPanel.add(duedateLabel);

        dayLabel = new JLabel("Day");
        dayLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        dayLabel.setForeground(Color.WHITE);
        dayLabel.setBounds(106, 320, 29, 14);
        mainPanel.add(dayLabel);

        monthLabel = new JLabel("month");
        monthLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        monthLabel.setBounds(147, 320, 46, 14);
        monthLabel.setForeground(Color.WHITE);
        mainPanel.add(monthLabel);

        yearLabel = new JLabel("Year");
        yearLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        yearLabel.setBounds(211, 320, 46, 14);
        yearLabel.setForeground(Color.WHITE);
        mainPanel.add(yearLabel);

        hoursLabel = new JLabel("Hours");
        hoursLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        hoursLabel.setBounds(430, 320, 46, 14);
        hoursLabel.setForeground(Color.WHITE);
        mainPanel.add(hoursLabel);
        
        minutesLabel = new JLabel("Minutes");
        minutesLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        minutesLabel.setBounds(481, 320, 46, 14);
        minutesLabel.setForeground(Color.WHITE);
        mainPanel.add(minutesLabel);

        comboBoxHours = new JComboBox<String>(hours);
        comboBoxHours.setForeground(Color.WHITE);
        comboBoxHours.setBackground(Color.DARK_GRAY);
        comboBoxHours.setBounds(430, 341, 46, 26);
        mainPanel.add(comboBoxHours);
        
        comboBoxDay = new JComboBox<String>(days);
        comboBoxDay.setForeground(Color.WHITE);
        comboBoxDay.setBackground(Color.DARK_GRAY);
        comboBoxDay.setBounds(96, 337, 41, 30);
        mainPanel.add(comboBoxDay);
//        comboBoxDay.setSelectedIndex(Integer.parseInt(assignment.getDueDate().getDay()));

        comboBoxmonth = new JComboBox<String>(months);
        comboBoxmonth.setForeground(Color.WHITE);
        comboBoxmonth.setBackground(Color.DARK_GRAY);
        comboBoxmonth.setBounds(147, 337, 41, 30);
        mainPanel.add(comboBoxmonth);

        comboBoxYear = new JComboBox<String>(years);
        comboBoxYear.setForeground(Color.WHITE);
        comboBoxYear.setBackground(Color.DARK_GRAY);
        comboBoxYear.setBounds(196, 337, 64, 30);
        mainPanel.add(comboBoxYear);

//        comboBoxYear.setSelectedIndex(Integer.parseInt(assignment.getDueDate().getYear()));

//        comboBoxHours.setSelectedIndex(Integer.parseInt(assignment.getDueDate().getHour()));

        comboBox = new JComboBox<String>(minutes);
        comboBox.setForeground(Color.WHITE);
        comboBox.setBackground(Color.DARK_GRAY);
        comboBox.setBounds(481, 341, 46, 26);
        mainPanel.add(comboBox);
//        comboBox.setSelectedIndex(Integer.parseInt(assignment.getDueDate().getMinute()));
       
        errorLabel = new JLabel("invalid points digits only");
        errorLabel.setBackground(Color.DARK_GRAY);
        errorLabel.setForeground(Color.RED);
        errorLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        errorLabel.setBounds(453, 401, 209, 30);
        mainPanel.add(errorLabel);
        errorLabel.setVisible(false);

        updateButton = new JButton("Update");
        updateButton.setBackground(Color.DARK_GRAY);
        updateButton.setForeground(Color.RED);
        updateButton.setBounds(288, 399, 99, 34);
        updateButton.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 15));
        updateButton.addActionListener(new actionlistener());
        updateButton.addMouseListener(new mouselistener());
        mainPanel.add(updateButton);

    }
    
    class actionlistener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//on clicking the update button the new data is collected and update assignment method is called
			String newtitle=textFieldTitle.getText();
            String newDetail=detailsTextPane.getText();
            String newDay=(String) comboBoxDay.getSelectedItem();
            String newMonth=(String)comboBoxmonth.getSelectedItem();
            String newYear=(String)comboBoxYear.getSelectedItem();
            String newMinutes=(String)comboBox.getSelectedItem();
            String newHours=(String)comboBoxHours.getSelectedItem();
            
            try {
                double totalpoints = Double.parseDouble(textFieldPoints.getText());
                faculty.updateAssignment(assignment, newDay, newMonth, newYear,
                        newtitle, newDetail, newMinutes, newHours, totalpoints);
                JOptionPane.showMessageDialog(null, "updated successfully");
                assignPanel.openAssignment(assignment);
            dispose();
            } catch (Exception ea) {
                textFieldPoints.setText("");
                errorLabel.setVisible(true);
                textFieldPoints.setText("");
            }
		}
    	
    }
    
    class mouselistener implements MouseListener {

		@Override
		public void mouseEntered(MouseEvent m) {
			if(m.getSource().equals(updateButton)) {
				updateButton.setBorder(new LineBorder(Color.RED));
				updateButton.setForeground(Color.WHITE);
			}
		}

		@Override
		public void mouseExited(MouseEvent m) {
			if(m.getSource().equals(updateButton)) {
				updateButton.setBorder(new LineBorder(Color.GRAY, 2));
				updateButton.setForeground(Color.WHITE);
			}
			
		}

		public void mouseClicked(MouseEvent m) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
		
	}
}

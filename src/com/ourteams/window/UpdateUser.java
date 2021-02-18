package com.ourteams.window;

import com.ourteams.backend.Faculty;
import com.ourteams.backend.Student;
import com.ourteams.backend.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UpdateUser extends JDialog {
	/**
	 * This Panel updates the user data
	 */
	private static final long serialVersionUID = 1L;

    private JTextField nametextField;
    private JPasswordField passwordField;
    private JButton updateBtn;
    private JLabel NewPasswordLabel;
    private JLabel namelbl;
    private JPanel updatepanel;
    private JLabel errorLabel;
    private JComboBox<String> DepartmentcomboBox;
    private JLabel DepartmentLbl;
    private JLabel semesterlbl;
    private JComboBox<String> semestercomboBox;
    private MainWindow frame;
    private User user;


    public UpdateUser(MainWindow frame){
    	this.frame = frame;
        this.user = frame.getUser();
        initialize();
    }

    public void initialize(){
        setBounds(119, 11, 688, 483);


        updatepanel = new JPanel();
        updatepanel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        updatepanel.setBackground(new Color(30, 144, 255));
        updatepanel.setBounds(0, 11, 672, 433);
        this.add(updatepanel);
        updatepanel.setLayout(null);

        namelbl = new JLabel("Name");
        namelbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
        namelbl.setBackground(new Color(245, 222, 179));
        namelbl.setBounds(145, 58, 93, 25);
        updatepanel.add(namelbl);


        nametextField = new JTextField();
        nametextField.setBounds(226, 60, 203, 23);
        updatepanel.add(nametextField);
        nametextField.setColumns(10);
        nametextField.setText(user.getName());


        NewPasswordLabel = new JLabel("Password");
        NewPasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        NewPasswordLabel.setBounds(145, 155, 71, 25);
        updatepanel.add(NewPasswordLabel);
//		nametextField.setText("333434");


        passwordField = new JPasswordField();
        passwordField.setBounds(226, 157, 203, 23);
        passwordField.setText("wewe");
        updatepanel.add(passwordField);
        passwordField.setText(user.getPassword());

        updateBtn = new JButton("Update");
        updateBtn.setBackground(new Color(124, 252, 0));

        updateBtn.setBounds(279, 191, 93, 33);
        updatepanel.add(updateBtn);

        ActionHandler al=new ActionHandler();
        updateBtn.addActionListener(al);

        errorLabel = new JLabel("enter password of length greater than 7");
        errorLabel.setForeground(new Color(255, 0, 0));
        errorLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        errorLabel.setBounds(145, 224, 290, 33);
        updatepanel.add(errorLabel);
        String[] semesters = {"1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th", "9th", "10th", "11th", "12th"};
        String[] departments = {"Computer Science", "Electrical Engineering", "Physics", "Bio Sciences", "Business Administration", "Natural Sciences", "Humanitites",
                "Mechanical Eningeering", "Psychology", "Mathematics", "Architecture", "Other"};

        DepartmentcomboBox = new JComboBox<String>(departments);
        DepartmentcomboBox.setBounds(226, 94, 203, 22);
        updatepanel.add(DepartmentcomboBox);

        DepartmentLbl = new JLabel("Department");
        DepartmentLbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
        DepartmentLbl.setBounds(145, 96, 71, 18);
        updatepanel.add(DepartmentLbl);

        semesterlbl = new JLabel("Semester");
        semesterlbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
        semesterlbl.setBounds(145, 130, 71, 14);
        updatepanel.add(semesterlbl);

        semestercomboBox = new JComboBox<String>(semesters);
        semestercomboBox.setBounds(226, 127, 203, 22);
        updatepanel.add(semestercomboBox);
        if (user instanceof Faculty) {
            semestercomboBox.setEnabled(false);
        }
        errorLabel.setVisible(false);
        this.setVisible(true);


    }
    class ActionHandler implements ActionListener{
    	@Override
		public void actionPerformed(ActionEvent a) {
    		/*
    		 * When update button is pressed then the entered data is check if its Valid
    		 * if it is valid then updateUserCredentials method is called with that data
    		 * else an error message is displayed on invalid data
    		 */
    		if(a.getSource().equals(updateBtn)) {
                String name = nametextField.getText();
                String password =  String.valueOf(passwordField.getPassword());
                String department=(String)DepartmentcomboBox.getSelectedItem();
                String semester="";
                if (user instanceof Student) {
                    semester = (String) semestercomboBox.getSelectedItem();
                    semester = ((Student) user).getSemester();
                }
                    semester =(String)semestercomboBox.getSelectedItem();
                if (name.equals(user.getName()) &&password.equals(user.getPassword())
                        &&department.equals(user.getDepartment())&& semester.equals(((Student) user).getSemester())){
                }
                else if (name.equals("") || name==null || Character.isDigit(name.charAt(0))){
//                	|| !name.matches("^[a-zA-Z]*$")
                    errorLabel.setText("invalid name");
                    errorLabel.setVisible(true);
                    nametextField.setText(user.getName());
                }
                else if (password.length()<7){
                    errorLabel.setText("invalid password");
                    errorLabel.setVisible(true);
                    passwordField.setText(user.getPassword());
                }
                else{
                    user.updatecredentials(name,password,department,semester);
                    JOptionPane.showMessageDialog(null,"account updated");
                    frame.updateAccountDetailsPanel();
                    dispose();
                }


    		}

		}
    }


}

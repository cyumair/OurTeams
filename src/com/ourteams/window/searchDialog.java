package com.ourteams.window;

import com.ourteams.backend.Student;
import com.ourteams.backend.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class searchDialog extends JDialog {
    /**
	 * This Panel displays the data for the user that was searched
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel nameDisplay;
    private JLabel useremail;
    private JLabel username;
    private MainWindow frame;
    private JButton messageButton;
    private JButton okbutton;
    private JLabel usertypelabel;
    private JLabel departmentlabel;
    private JLabel departmentnamelabel;
    private JLabel semeterlabel;
    private JLabel semesterDisplay;
    private User u;
    searchDialog(MainWindow frame, String mail){
                u= User.findUser(mail);
                this.frame = frame;
                initialize();
                
    }
    public void initialize(){

        setBounds(119, 11, 688, 483);
        getContentPane().setLayout(null);
        contentPanel.setBackground(UIManager.getColor("InternalFrame.activeTitleBackground"));
        contentPanel.setBounds(0, 0, 672, 444);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(contentPanel);
        contentPanel.setLayout(null);

        nameLabel = new JLabel("Name :");
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        nameLabel.setBounds(150, 29, 72, 26);
        contentPanel.add(nameLabel);


        emailLabel = new JLabel("Email :");
        emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        emailLabel.setBounds(150, 66, 72, 26);
        contentPanel.add(emailLabel);

        nameDisplay = new JLabel("waleed");
        nameDisplay.setFont(new Font("Tahoma", Font.PLAIN, 15));
        nameDisplay.setBounds(306, 34, 231, 19);
        contentPanel.add(nameDisplay);

        useremail = new JLabel("waleedahmed10200@gmail.com");
        useremail.setForeground(UIManager.getColor("infoText"));
        useremail.setFont(new Font("Tahoma", Font.PLAIN, 15));
        useremail.setBounds(306, 71, 356, 19);
        contentPanel.add(useremail);

        username = new JLabel();
        username.setHorizontalAlignment(SwingConstants.LEFT);
        username.setFont(new Font("Tahoma", Font.PLAIN, 15));
        username.setBounds(306, 108, 231, 19);
        contentPanel.add(username);

        messageButton = new JButton("Send message");
        messageButton.setBackground(new Color(173, 216, 230));
        messageButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
        messageButton.setBounds(412, 257, 125, 23);
        if(u.equals(frame.getUser())) {
        	messageButton.setEnabled(false);
        }
        messageButton.addActionListener(new actionlistener());
        contentPanel.add(messageButton);

        okbutton = new JButton("OK");
        okbutton.setBackground(new Color(230, 230, 250));
        okbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        okbutton.setFont(new Font("Tahoma", Font.PLAIN, 13));
        okbutton.setBounds(266, 301, 83, 23);
        contentPanel.add(okbutton);

        usertypelabel = new JLabel("User Type :");
        usertypelabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        usertypelabel.setBounds(150, 103, 98, 26);
        contentPanel.add(usertypelabel);

        departmentlabel = new JLabel("Department :");
        departmentlabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        departmentlabel.setBounds(150, 140, 116, 26);
        contentPanel.add(departmentlabel);

        departmentnamelabel = new JLabel("departemtDisplay");
        departmentnamelabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        departmentnamelabel.setBounds(306, 145, 198, 19);
        contentPanel.add(departmentnamelabel);

        semeterlabel = new JLabel("Semester :");
        semeterlabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        semeterlabel.setBounds(150, 180, 116, 26);
        contentPanel.add(semeterlabel);
        semeterlabel.setVisible(false);

        semesterDisplay = new JLabel("sem Display");
        semesterDisplay.setFont(new Font("Tahoma", Font.PLAIN, 15));
        semesterDisplay.setBounds(306, 185, 116, 19);
        contentPanel.add(semesterDisplay);
        semesterDisplay.setVisible(false);
        
        nameDisplay.setText(u.getName());
        useremail.setText(u.getEmail());
        if (u instanceof Student) {
            username.setText("Student");
            semeterlabel.setVisible(true);
            semesterDisplay.setVisible(true);
            semesterDisplay.setText(((Student)u).getSemester());
        }
        else
            username.setText("Faculty");
        departmentnamelabel.setText(u.getDepartment());
        
        this.setVisible(true);
    }
    
    class actionlistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			/*
			 * when message button is pressed the conversation of that user is opened by
			 * first getting the index of the searched user in user's contacts and then 
		     * opening chats panel of that contact
		     * the frame is disposed (closed) after this action
			 */
			int defaultcontactindex = frame.getUser().startaconversation(u);
			frame.openChatsPanel(defaultcontactindex);
			dispose();
		}
    	
    }

}

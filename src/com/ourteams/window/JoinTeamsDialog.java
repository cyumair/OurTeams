package com.ourteams.window;

import com.ourteams.backend.Team;
import com.ourteams.backend.User;

import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class JoinTeamsDialog extends JDialog{

    /**
     * This Dialog asks for a team code and joins the Team if it exists
     */
    private static final long serialVersionUID = 1L;
    private JTextField teamcodefield;
    private JButton joinButton;
    private User user;
    private JLabel errorlabel;
    private TeamsContentPane teamscontentpanel;
  

    public JoinTeamsDialog(ArrayList<Team> teams, TeamsContentPane teamscontentpanel){
    	this.user = teamscontentpanel.getUser();
        this.teamscontentpanel = teamscontentpanel;
        initialize();
    }

    private void initialize() {
        setBounds(470, 300, 580, 399);
        getContentPane().setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel contentPanel = new JPanel();
        contentPanel.setBounds(0, 0, 573, 360);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel);
        contentPanel.setLayout(null);
        contentPanel.setBackground(new Color(25,20,20));

        JLabel toplabel = new JLabel("Join a New Team");
        toplabel.setFont(new Font("MV Boli", Font.BOLD, 24));
        toplabel.setForeground(Color.RED);
        toplabel.setBounds(169, 41, 271, 33);
        contentPanel.add(toplabel);

        ActionListener al = new actionlistener();
        joinButton = new JButton("Join");
        joinButton.setContentAreaFilled(false);
        joinButton.setForeground(Color.RED);
        joinButton.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 18));
        joinButton.setBounds(225, 244, 118, 33);
        contentPanel.add(joinButton);
        joinButton.addActionListener(al);

        teamcodefield = new JTextField();
        teamcodefield.setForeground(Color.WHITE);
        teamcodefield.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 18));
        teamcodefield.setCaretColor(Color.WHITE);
        teamcodefield.setBackground(Color.DARK_GRAY);
        teamcodefield.setColumns(10);
        teamcodefield.setBounds(215, 140, 281, 40);
        contentPanel.add(teamcodefield);


        JLabel joinlabel = new JLabel("Join Code");
        joinlabel.setForeground(Color.WHITE);
        joinlabel.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 18));
        joinlabel.setBounds(73, 140, 118, 33);
        contentPanel.add(joinlabel);

        errorlabel = new JLabel("");
        errorlabel.setForeground(Color.RED);
        errorlabel.setFont(new Font("MV Boli", Font.BOLD, 16));
        errorlabel.setBounds(169, 287, 327, 33);
        contentPanel.add(errorlabel);
        
        this.setVisible(true);
        
    }
//    ====================================================================================================
//=========================================================================================================

    class actionlistener implements ActionListener{

        @Override
        /*
         * On clicking join button, It is checked if user has already joined that team,
		 * if no then join team method of user is called else an error message is displayed
         */
        public void actionPerformed(ActionEvent a) {
       
                String joincode=teamcodefield.getText();
                boolean available=false;
                ArrayList<Team> allteams = Team.readAllDataFromFile();
                Team newteam = null;
                for (int i=0;i<allteams.size();i++){
                    if (allteams.get(i).getJoincode().equals(joincode)){
                        newteam = allteams.get(i);
                        available=true;
                        break;
                    }
                }
                if (available){
                    boolean joinedAlready=false;
                    for (int i=0;i<user.getTeams().size();i++){
                        if (user.getTeams().get(i).getJoincode().equals(joincode)){
                            joinedAlready=true;
                            break;
                        }
                    }
                    if (!joinedAlready){
                        user.join_team(newteam);
                        teamcodefield.setText("");
                        errorlabel.setText("  Team Joined Successfully");
                        teamscontentpanel.updateMenuPanel();
                        dispose();
                    }
                    else{
                        errorlabel.setText("    Team Already Joined ");
                    }



                }
                else {
                    teamcodefield.setText("");
                    errorlabel.setText("     Wrong Team Code");
                }
            
        }

    }
}

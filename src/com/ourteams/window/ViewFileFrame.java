package com.ourteams.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ViewFileFrame extends JDialog{
	/**
	 * This frame takes file as its constructor argument 
	 * and displays content of that file in JTextPane
	 */
	private static final long serialVersionUID = 1L;
	private JTextPane textPane;
	private File file;
	private JPanel panel;
	private JScrollPane scrollPane;
	private Scanner input;
	
	public ViewFileFrame(File file) {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.file = file;
		setBounds(100, 100, 980, 720);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setBackground(Color.BLACK);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 944, 659);
		scrollPane.setBorder(new LineBorder(Color.GRAY, 2));
		panel.add(scrollPane);
		
		textPane = new JTextPane();
		textPane.setForeground(Color.WHITE);
		textPane.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textPane.setEditable(false);
		textPane.setBackground(Color.DARK_GRAY);
		scrollPane.setViewportView(textPane);
		try {
			input = new Scanner(this.file);
			if(file.isFile()) {
				while(input.hasNextLine()) {
					String line = input.nextLine();
					textPane.setText(textPane.getText() + "\n" + line);
				
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
}

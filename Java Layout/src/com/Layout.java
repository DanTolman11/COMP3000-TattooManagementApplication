package com;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Layout implements ActionListener {

    private static JLabel userLabel;
    private static JTextField userText;
    private static JLabel pwlabel;
    private static JPasswordField pwText;
    private static JButton button;
    private static JLabel success;

    public static void main(String[] args) {

        //config the frame
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(350,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        //config panel
        panel.setLayout(null);

        userLabel = new JLabel("Username: ");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);
        userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        panel.add(userText);

        pwlabel = new JLabel("Password: ");
        pwlabel.setBounds(10,50,80,25);
        panel.add(pwlabel);
        pwText = new JPasswordField(20);
        pwText.setBounds(100,50,165,25);
        panel.add(pwText);

        button = new JButton("Login");
        button.setBounds(10,80,80,25);
        button.addActionListener(new Layout());
        panel.add(button);

        success = new JLabel("");
        success.setBounds(10,110,300,25);
        panel.add(success);



        frame.setVisible(true);

    }

    //this code gets run when the button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Button Clicked");
        String user = userText.getText();
        String password = pwText.getText();
        System.out.println(user + ", " + password);

        if(user.equals("Dan") && password.equals("toestoestoes")){
            success.setText("Login successful! Welcome back " + user);

        }
    }
}

package com.tolman.controller;

import com.tolman.model.User;
import com.tolman.model.UserService;
import com.tolman.model.database.UserDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class LoginPanel extends JPanel {

    private static LoginPanel INSTANCE;

    private UserService userService = UserService.getInstance();

    private JTextField userName;
    private JPasswordField password;
    private  JButton login;
    private JLabel errorMessage;

    private LoginPanel() {

        userName = new JTextField(20);
        password = new JPasswordField(20);
        errorMessage = new JLabel("");
        this.add(new JLabel("Username: "));
        this.add(userName);
        this.add(new JLabel("password"));
        this.add(password);
        login = new JButton("Login");
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    if(userService.validateLoginCredentials(userName.getText(), password.getText())){
                        Application.getInstance().setWindow(ApplicationPanel.getInstance());
                        return;
                    }
                String noUser = JOptionPane.showInputDialog("Incorrect Credentials");
                errorMessage.setText("User not found");

            }
        });
        this.add(login);
    }

    public static LoginPanel getInstance(){
        if (INSTANCE == null){
            INSTANCE = new LoginPanel();
        }
        return INSTANCE;
    }

}

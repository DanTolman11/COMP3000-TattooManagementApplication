package com.tolman.view.panels;

import com.tolman.Application;
import com.tolman.model.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {

    private static LoginPanel INSTANCE;

    private UserService userService = UserService.getInstance();

    private JTextField userName;
    private JPasswordField password;
    private  JButton btnLogin;
    private JLabel errorMessage;
    private JLabel titleMsg;
    private JLabel lblPassword;
    private JLabel lblUsername;

    private LoginPanel() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();


        titleMsg = new JLabel("Tattoo Ease Of Life");
        titleMsg.setFont(titleMsg.getFont().deriveFont(18.0f));
        lblUsername = new JLabel("Username: ");
        userName = new JTextField(10);
        lblPassword = new JLabel("Password: ");
        password = new JPasswordField(10);
        btnLogin = new JButton("Login");
        errorMessage = new JLabel("");

        gbc.gridy=0;
        this.add(titleMsg, gbc);

        gbc.gridy=1;
        gbc.gridx=0;
        this.add(lblUsername, gbc);

        gbc.gridy=1;
        gbc.gridx=1;
        this.add(userName, gbc);

        gbc.gridy=2;
        gbc.gridx=0;
        this.add(lblPassword, gbc);

        gbc.gridy=2;
        gbc.gridx=1;
        this.add(password, gbc);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(userService.validateLoginCredentials(userName.getText(), password.getText())){
                    Application.getInstance().setWindow(ApplicationPanel.getInstance());
                    return;
                }
                JOptionPane.showMessageDialog(btnLogin,"Incorrect Credentials", "Error", JOptionPane.ERROR_MESSAGE);
                errorMessage.setText("User or Password not correct, please try again.");
            }
        });
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridy++;
        gbc.gridx=0;
        gbc.gridwidth=2;
        this.add(btnLogin, gbc);

        gbc.gridy++;
        this.add(errorMessage, gbc);


    }

    public static LoginPanel getInstance(){
        if (INSTANCE == null){
            INSTANCE = new LoginPanel();
        }
        return INSTANCE;
    }

}

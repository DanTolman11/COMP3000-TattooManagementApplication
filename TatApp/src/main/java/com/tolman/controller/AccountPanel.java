package com.tolman.controller;

import com.tolman.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountPanel extends JPanel {

    private static AccountPanel INSTANCE;
    private GridBagLayout mylayout;
    private JButton btnBack;
    private JButton btnAddAccount;
    private JButton btnRemoveAccount;
    private JButton btnEditAccount;
    private JList<User> lstUser;

    private DefaultListModel<User> userListModel = new DefaultListModel<>();
    private static final Dimension List_DIMENSION = new Dimension(200,100);

    private AccountPanel(){
        mylayout = new GridBagLayout();
        this.setLayout(mylayout);
        GridBagConstraints gbc = new GridBagConstraints();

        lstUser = new JList<>(userListModel);
        lstUser.setMinimumSize(List_DIMENSION);

        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.gridy=5;
        gbc.gridwidth=2;
        btnBack = new JButton("Back");
        this.add(btnBack, gbc);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Application.getInstance().setWindow(ApplicationPanel.getInstance());
            }
        });

        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=3;
        this.add(new JLabel("Account"), gbc);

        gbc.gridx=0;
        gbc.gridy=1;
        this.add(lstUser, gbc);

        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.gridwidth=2;
        btnAddAccount = new JButton("Add Account");
        this.add(btnAddAccount, gbc);
        btnAddAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String addAccount = JOptionPane.showInputDialog("Please enter new Account details");
            }
        });

        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.gridy=3;
        gbc.gridwidth=2;
        btnEditAccount = new JButton("Edit Account");
        this.add(btnEditAccount, gbc);
        btnEditAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String editAccount = JOptionPane.showInputDialog("Please edit selected Accounts details");
            }
        });

        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.gridy=4;
        gbc.gridwidth=2;
        btnRemoveAccount = new JButton("Remove Account");
        this.add(btnRemoveAccount, gbc);
        btnRemoveAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String removeAccount = JOptionPane.showInputDialog("Are you sure you want to delete this?");
            }
        });


    }

    public static AccountPanel getInstance(){
        if (INSTANCE == null){
            INSTANCE = new AccountPanel();
        }
        return INSTANCE;
    }
}

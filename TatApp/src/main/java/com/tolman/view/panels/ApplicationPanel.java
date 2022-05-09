package com.tolman.view.panels;

import com.tolman.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicationPanel extends JPanel {

    private static ApplicationPanel INSTANCE;

    private GridBagLayout mylayout;
    private JButton btnBookings;
    private JButton btnStock;
    private JButton btnLogout;
    private JButton btnAccount;

    private ApplicationPanel() {
        mylayout = new GridBagLayout();
        this.setLayout(mylayout);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=3;
        gbc.weightx=1;
        gbc.weighty=0;
        this.add(new JLabel("Tattoo Management"), gbc);

        gbc.gridx=0;
        gbc.gridy=1;
        gbc.gridwidth=1;
        btnBookings = new JButton("Bookings");
        this.add(btnBookings, gbc);
        btnBookings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Application.getInstance().setWindow(new BookingPanel());
            }
        });

        gbc.gridx=1;
        gbc.gridy=1;
        gbc.gridwidth=1;
        btnStock = new JButton("Stock");
        this.add(btnStock, gbc);
        btnStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Application.getInstance().setWindow(new StockPanel());
            }
        });

        gbc.gridx=2;
        gbc.gridy=1;
        gbc.gridwidth=1;
        btnAccount = new JButton("Account");
        this.add(btnAccount, gbc);
        btnAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Application.getInstance().setWindow(new AccountPanel());
            }
        });

        gbc.gridx=0;
        gbc.gridy=2;
        gbc.gridwidth=3;
        gbc.weightx=1;
        gbc.weighty=0;
        btnLogout = new JButton("Logout");
        this.add(btnLogout, gbc);
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //need to create an 'if' for when "cancel or No" are pressed as it currently runs anyway.
                String logoutMsg = "Are you sure you want to log out?";
                String title = "Logging out";
                int reply = JOptionPane.showConfirmDialog(null, logoutMsg, title, JOptionPane.YES_NO_CANCEL_OPTION);
                Application.getInstance().setWindow(LoginPanel.getInstance());
            }
        });


    }

    public static ApplicationPanel getInstance(){
        if (INSTANCE == null){
            INSTANCE = new ApplicationPanel();
        }
        return INSTANCE;
    }
}

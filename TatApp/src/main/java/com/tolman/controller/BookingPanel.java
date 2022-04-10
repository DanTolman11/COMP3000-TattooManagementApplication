package com.tolman.controller;

import com.tolman.model.Booking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookingPanel extends JPanel {

    private static BookingPanel INSTANCE;
    private GridBagLayout mylayout;
    private JButton btnBack;
    private JButton btnAdd;
    private JButton btnRemove;
    private JButton btnEdit;
    private JList<Booking> lstBooking;

    private DefaultListModel<Booking> bookingListModel = new DefaultListModel<>();
    private static final Dimension List_DIMENSION = new Dimension(300,400);


    private BookingPanel() {
        mylayout = new GridBagLayout();
        this.setLayout(mylayout);
        GridBagConstraints gbc = new GridBagConstraints();

        lstBooking = new JList<>(bookingListModel);
        lstBooking.setMinimumSize(List_DIMENSION);

        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=3;
        this.add(new JLabel("Bookings"), gbc);

        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.gridwidth=2;
        btnAdd = new JButton("Add Booking");
        this.add(btnAdd, gbc);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String addBooking = JOptionPane.showInputDialog("Please input a booking");
            }
        });

        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.gridy=3;
        gbc.gridwidth=2;
        btnEdit = new JButton("Edit Booking");
        this.add(btnEdit, gbc);
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String editBooking = JOptionPane.showInputDialog("Please edit selected booking");
            }
        });

        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.gridy=4;
        gbc.gridwidth=2;
        btnRemove = new JButton("Remove Booking");
        this.add(btnRemove, gbc);
        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String removeMsg = "Are you sure you want to remove this booking ?";
                String title = "Remove Booking";
                int reply = JOptionPane.showConfirmDialog(null,removeMsg,title,JOptionPane.YES_NO_CANCEL_OPTION);
            }
        });

        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.gridwidth=2;
        btnBack = new JButton("Back");
        this.add(btnBack, gbc);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Application.getInstance().setWindow(ApplicationPanel.getInstance());
            }
        });

        gbc.gridx=1;
        gbc.gridy=2;
        this.add(lstBooking, gbc);
    }

    public static BookingPanel getInstance(){
        if (INSTANCE == null){
            INSTANCE = new BookingPanel();
        }
        return INSTANCE;
    }
}

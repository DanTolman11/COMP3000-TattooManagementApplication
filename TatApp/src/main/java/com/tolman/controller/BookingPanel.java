package com.tolman.controller;

import com.tolman.model.Booking;
import com.tolman.model.BookingService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
* Steps:
* Create the database table for bookings, review the booking structure you probably want a start date/time (maybe seperate fields)
* you also probably want a duration so you know when the booking is finished, or just a finish time field
*
* Create the basic of the service & dao layers
*   - this will include any business logic, validation, saving etc.
* Create the basic UI, yse below as a template and expand it...
* Using events from different components as required tie the business logic in the service layer to the UI
* */
public class BookingPanel extends JPanel {

    private static BookingPanel INSTANCE;
    private GridBagLayout mylayout;
    private JButton btnBack;
    private JButton btnAdd;
    private JButton btnRemove;
    private JButton btnEdit;
    private JList<Booking> lstBookings;

    private DefaultListModel<Booking> bookingListModel = new DefaultListModel<>();
    private static final Dimension List_DIMENSION = new Dimension(300,400);

    private Booking currentlyEditing;
    private JTextField txtBookingName;

    private BookingPanel() {
        lstBookings = new JList<>(bookingListModel);
        lstBookings.setPreferredSize(new Dimension(500,100));

        //TODO Load from DB
        bookingListModel.addAll(BookingService.getInstance().getAllBookings());
        this.add(lstBookings);
        lstBookings.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (lstBookings.getSelectedIndex() != -1){
                    currentlyEditing = bookingListModel.get(lstBookings.getSelectedIndex());
                    if(currentlyEditing.getName() == null){
                        txtBookingName.setText("");
                        return;
                    }
                    txtBookingName.setText(currentlyEditing.getName());
                }
            }
        });
        this.add(createBookingControlsPanel());
        this.add(createBookingDetailsPanel());
    }

    private JPanel createBookingDetailsPanel() {
        JPanel bookingDetailsPanel = new JPanel();
        txtBookingName = new JTextField(20);
        bookingDetailsPanel.add(txtBookingName);
        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO Validation before save... this should bve part of the BookingService
                currentlyEditing.setName(txtBookingName.getText());
                boolean valid = BookingService.getInstance().validateBooking(currentlyEditing);
                if(!valid){
                    //TODO handle invalid change probably reset entity?
                    return;
                }
                BookingService.getInstance().saveBooking();
                lstBookings.invalidate();
                lstBookings.revalidate();
                lstBookings.repaint();
            }
        });
        bookingDetailsPanel.add(btnSave);
        return bookingDetailsPanel;
    }

    private JPanel createBookingControlsPanel() {
        JPanel bookingControls = new JPanel();
        btnAdd = new JButton("Add");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Booking booking = new Booking();
                bookingListModel.addElement(booking);
                lstBookings.setSelectedIndex(bookingListModel.size() - 1);
            }
        });
        btnRemove = new JButton("Remove");
        bookingControls.add(btnAdd);
        bookingControls.add(btnRemove);
        return bookingControls;
    }
//        lstBooking = new JList<>(bookingListModel);
//        lstBooking.setMinimumSize(List_DIMENSION);
//
//        gbc.fill=GridBagConstraints.HORIZONTAL;
//        gbc.gridx=0;
//        gbc.gridy=0;
//        gbc.gridwidth=3;
//        this.add(new JLabel("Bookings"), gbc);
//
//        gbc.fill=GridBagConstraints.HORIZONTAL;
//        gbc.gridx=0;
//        gbc.gridy=2;
//        gbc.gridwidth=2;
//        btnAdd = new JButton("Add Booking");
//        this.add(btnAdd, gbc);
//        btnAdd.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String addBooking = JOptionPane.showInputDialog("Please input a booking");
//            }
//        });
//
//        gbc.fill=GridBagConstraints.HORIZONTAL;
//        gbc.gridx=0;
//        gbc.gridy=3;
//        gbc.gridwidth=2;
//        btnEdit = new JButton("Edit Booking");
//        this.add(btnEdit, gbc);
//        btnEdit.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String editBooking = JOptionPane.showInputDialog("Please edit selected booking");
//            }
//        });
//
//        gbc.fill=GridBagConstraints.HORIZONTAL;
//        gbc.gridx=0;
//        gbc.gridy=4;
//        gbc.gridwidth=2;
//        btnRemove = new JButton("Remove Booking");
//        this.add(btnRemove, gbc);
//        btnRemove.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String removeMsg = "Are you sure you want to remove this booking ?";
//                String title = "Remove Booking";
//                int reply = JOptionPane.showConfirmDialog(null,removeMsg,title,JOptionPane.YES_NO_CANCEL_OPTION);
//            }
//        });
//
//        gbc.fill=GridBagConstraints.HORIZONTAL;
//        gbc.gridx=0;
//        gbc.gridy=1;
//        gbc.gridwidth=2;
//        btnBack = new JButton("Back");
//        this.add(btnBack, gbc);
//        btnBack.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Application.getInstance().setWindow(ApplicationPanel.getInstance());
//            }
//        });
//
//        gbc.gridx=1;
//        gbc.gridy=2;
//        this.add(lstBooking, gbc);
//    }

    public static BookingPanel getInstance(){
        if (INSTANCE == null){
            INSTANCE = new BookingPanel();
        }
        return INSTANCE;
    }
}

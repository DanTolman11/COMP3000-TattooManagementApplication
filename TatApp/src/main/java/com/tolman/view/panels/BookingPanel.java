package com.tolman.view.panels;

import com.tolman.Application;
import com.tolman.view.tablesmodels.booking.BookingTableModel;
import com.tolman.model.Booking;
import com.tolman.model.BookingService;
import com.tolman.view.ui.CustomJTable;
import com.tolman.view.ui.SpinnerCellEditor;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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

    BookingService bookingService = BookingService.getInstance();

    public BookingPanel()  {
        BookingTableModel tableModel = new BookingTableModel(bookingService.getAllBookings());

        JPanel buttonPanel = new JPanel();
        JButton btnAdd = new JButton("Add");
        JButton btnDelete = new JButton("Delete");
        JButton btnSave = new JButton("Save");
        JButton btnBack = new JButton("Back");

        CustomJTable table = new CustomJTable();
        table.setModel(tableModel);

        TableColumn bookingDateColumn = table.getColumnModel().getColumn(0);
        JSpinner spinner = new JSpinner();
        spinner.setModel(new SpinnerDateModel());
        bookingDateColumn.setCellEditor(new SpinnerCellEditor(spinner));

        TableColumn phoneNumberColumn = table.getColumnModel().getColumn(4);

        try {
            MaskFormatter maskFormatter = new MaskFormatter("##### ######");
            maskFormatter.setPlaceholderCharacter('#');
            JFormattedTextField phoneNumberFormattedField = new JFormattedTextField(maskFormatter);
            phoneNumberColumn.setCellEditor(new DefaultCellEditor(phoneNumberFormattedField));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;

        this.add(new JScrollPane(table), gbc);
        gbc.gridy++;

        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnSave);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnBack);
        this.add(buttonPanel, gbc);

        btnAdd.addActionListener(e -> {
            tableModel.addBooking(new Booking());
            table.revalidate();
        });
        
        btnDelete.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();

            if(selectedRow == -1){
                return;
            }

            int response = JOptionPane.showConfirmDialog(null, "Are you sure you wish to delete the selected user?", "confirm", JOptionPane.YES_NO_OPTION);

            if(response == JOptionPane.NO_OPTION) {
                return;
            }

            tableModel.deleteBooking(tableModel.getBooking(selectedRow));
        });

        btnSave.addActionListener(e -> {

            table.clearColors();

            List<Booking> failedSaveActions = new ArrayList<>();
            for (Booking booking : tableModel.getPendingState().getInserts()) {
                boolean ok = bookingService.createBooking((booking));
                if(!ok){
                    failedSaveActions.add(booking);
                }
            }

            List<Booking> failedUpdateActions = new ArrayList<>();
            for (Booking booking : tableModel.getPendingState().getUpdates()) {
                boolean ok = bookingService.updateBooking(booking);
                if(!ok){
                    failedUpdateActions.add(booking);
                }
            }

            List<Booking> failedDeleteActions = new ArrayList<>();
            for (Booking booking : tableModel.getPendingState().getDeletes()) {
                boolean ok = bookingService.deleteBooking(booking);
                if(!ok){
                    failedDeleteActions.add(booking);
                }
            }

            tableModel.getPendingState().reset();

            tableModel.getPendingState().getUpdates().addAll(failedUpdateActions);
            tableModel.getPendingState().getInserts().addAll(failedSaveActions);
            tableModel.getPendingState().getDeletes().addAll(failedDeleteActions);

            tableModel.addAllFailedDeletions(failedDeleteActions);

            List<Booking> allFailedBookings = new ArrayList<>();
            allFailedBookings.addAll(failedUpdateActions);
            allFailedBookings.addAll(failedSaveActions);
            allFailedBookings.addAll(failedDeleteActions);
            for (Booking booking : allFailedBookings) {
                table.setRowColor(tableModel.getRowIndexOf(booking), Color.RED);
            }
            table.repaint();
        });

        btnBack.addActionListener(e -> {
            //TODO validate pending state and warn user before navigating
            Application.getInstance().setWindow(ApplicationPanel.getInstance());
        });

        this.setVisible(true);
    }

    public static void main(String[] args) { new StockPanel(); }

}

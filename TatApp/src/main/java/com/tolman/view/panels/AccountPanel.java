package com.tolman.view.panels;

import com.tolman.Application;
import com.tolman.view.tablesmodels.account.UserTableModel;
import com.tolman.model.User;
import com.tolman.model.UserService;
import com.tolman.view.ui.CustomJTable;
import com.tolman.utils.SecurityUtils;

import javax.swing.*;
import java.awt.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class AccountPanel extends JPanel {

    UserService userService = UserService.getInstance();

    AccountPanel(){


        JPanel buttonPanel = new JPanel();
        JButton btnAdd = new JButton("Add");
        JButton btnDelete = new JButton("Delete");
        JButton btnSave = new JButton("Save");
        JButton btnBack = new JButton("Back");

        CustomJTable table = new CustomJTable();
        UserTableModel tableModel = new UserTableModel(userService.getAllUsers());
        table.setModel(tableModel);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        this.add(new JScrollPane(table), gbc);
        gbc.gridy++;

        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnSave);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnBack);

        this.add(buttonPanel, gbc);

        table.getSelectionModel().addListSelectionListener(e -> {
            User user = tableModel.getUser(table.getSelectedRow());
            String response = JOptionPane.showInputDialog("Enter a new password for " + user.getUsername());
            if(response == null || response == ""){
                return;
            }
            try {
                user.setPassword(SecurityUtils.hexDigest(response));
            } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                noSuchAlgorithmException.printStackTrace();
                return;
            }
            userService.updateUser(user);
        });

        btnAdd.addActionListener(e -> {
            User user = new User();
            user.setUsername(JOptionPane.showInputDialog("Enter username"));
            try {
                user.setPassword(SecurityUtils.hexDigest(JOptionPane.showInputDialog("Enter password")));
            } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                noSuchAlgorithmException.printStackTrace();
                return;
            }
            tableModel.addUser(user);
            table.revalidate();
        });

        btnDelete.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if(selectedRow == -1){
                return;
            }
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you wish to delete this user?", "Confirm", JOptionPane.YES_NO_OPTION);
            if(response == JOptionPane.NO_OPTION){
                return;
            }

            tableModel.deleteUser(tableModel.getUser(selectedRow));
        });

        btnSave.addActionListener(e -> {
            table.clearColors();

            List<User> failedSaveActions = new ArrayList<>();
            for (User user : tableModel.getPendingState().getInserts()) {
                boolean ok = userService.createUser(user);
                if(!ok){
                    failedSaveActions.add(user);
                }
            }

            List<User> failedDeleteActions = new ArrayList<>();
            for (User user : tableModel.getPendingState().getDeletes()) {
                boolean ok = userService.deleteUser(user);
                if(!ok){
                    failedDeleteActions.add(user);
                }
            }

            tableModel.getPendingState().reset();
            tableModel.getPendingState().getInserts().addAll(failedSaveActions);
            tableModel.getPendingState().getDeletes().addAll(failedDeleteActions);
            tableModel.addAllFailedDeletions(failedDeleteActions);

            List<User> allFailedItems = new ArrayList<>();
            allFailedItems.addAll(failedSaveActions);
            allFailedItems.addAll(failedDeleteActions);
            for (User user : allFailedItems) {
                table.setRowColor(tableModel.getRowIndexOf(user), Color.RED);
            }
            table.repaint();
        });

        btnBack.addActionListener(e -> {
            Application.getInstance().setWindow(ApplicationPanel.getInstance());
        });

        this.setVisible(true);
    }

    public static void main(String[] args) { new AccountPanel();}
}



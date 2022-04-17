package com.tolman.controller;

import com.tolman.model.Stock;
import com.tolman.model.StockService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StockPanel extends JPanel {

    private static StockPanel INSTANCE;
    private GridBagLayout mylayout;
    private JButton btnBack;
    private JButton btnAddStock;
    private JButton btnRemoveStock;
    private JButton btnEditStock;
    private JButton btnUseStock;
    private JButton btnHighlightStock;
    private JList<Stock> lstStock;

    private DefaultListModel<Stock> stockListModel = new DefaultListModel<>();
    private static final Dimension List_DIMENSION = new Dimension(300, 100);

    private Stock currentlyEditing;
    private JTextField txtStockName;

    private StockPanel() {
        mylayout = new GridBagLayout();
        this.setLayout(mylayout);
        GridBagConstraints gbc = new GridBagConstraints();

        lstStock = new JList<>(stockListModel);
        lstStock.setPreferredSize(List_DIMENSION);

        //TODO Load from DB
        stockListModel.addAll(StockService.getInstance().getAllStock(stockListModel));
        this.add(lstStock);
        lstStock.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (lstStock.getSelectedIndex() != -1){
                    currentlyEditing = stockListModel.get(lstStock.getSelectedIndex());
                    if(currentlyEditing.getName() == null){
                        txtStockName.setText("");
                        return;
                    }
                     txtStockName.setText(currentlyEditing.getName());
                }
            }
        });




        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        this.add(new JLabel("Stock"));

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        btnAddStock = new JButton("Add Stock");
        this.add(btnAddStock, gbc);
        btnAddStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String addStock = JOptionPane.showInputDialog("Please enter new stock");
            }
        });

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        btnRemoveStock = new JButton("Remove Stock");
        this.add(btnRemoveStock, gbc);
        btnRemoveStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String removeMsg = "Are you sure you want to remove this stock ?";
                String title = "Remove Stock";
                int reply = JOptionPane.showConfirmDialog(null,removeMsg,title,JOptionPane.YES_NO_CANCEL_OPTION);            }
        });

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        btnEditStock = new JButton("Edit Stock");
        this.add(btnEditStock, gbc);
        btnEditStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String editStock = JOptionPane.showInputDialog("Please edit selected stock");
            }
        });

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        btnUseStock = new JButton("Use Stock");
        this.add(btnUseStock, gbc);
        btnUseStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "You used 1 : *Insert Stock Here*");
            }
        });

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        btnHighlightStock = new JButton("Highlight Stock");
        this.add(btnHighlightStock, gbc);
        btnHighlightStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "You have highlighted the following stock : *Insert Stock Here");
            }
        });

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        btnBack = new JButton("Back");
        this.add(btnBack, gbc);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Application.getInstance().setWindow(ApplicationPanel.getInstance());
            }
        });

       // gbc.gridx = 1;
       // gbc.gridy = 2;
       // this.add(lstStock, gbc);
    }

    public static StockPanel getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new StockPanel();
        }
        return INSTANCE;
    }
}

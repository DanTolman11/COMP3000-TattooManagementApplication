package com.tolman.view.panels;

import com.tolman.Application;
import com.tolman.view.tablesmodels.stock.StockTableModel;
import com.tolman.model.Stock;
import com.tolman.model.StockService;
import com.tolman.view.ui.CustomJTable;
import com.tolman.view.ui.SpinnerCellEditor;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StockPanel extends JPanel {

    StockService stockService = StockService.getInstance();

    public StockPanel() {
        // Create table model, this also sets up the listener to listen for changes
        StockTableModel tableModel = new StockTableModel(stockService.getAllStock());

        //create our components
        JPanel buttonPanel = new JPanel();
        JButton btnAdd = new JButton("Add");
        JButton btnDelete = new JButton("Delete");
        JButton btnSave = new JButton("Save");
        JButton btnBack = new JButton("Back");

        //Custom Table allows setting of row color by calling table.setRowColor(rowIndex, Color);
        //this needs to be follows by table.repaint()
        CustomJTable table = new CustomJTable();
        table.setModel(tableModel);

        //Customise column input from model at index there also exists a DefaultCellEditor which allows, JComboBox and JCheckBox
        TableColumn column = table.getColumnModel().getColumn(1);
        JSpinner spinner = new JSpinner();
        spinner.setModel(new SpinnerNumberModel());
        column.setCellEditor(new SpinnerCellEditor(spinner));

        // this initialise
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        //setup and add our table


        this.add(new JScrollPane(table), gbc);
        gbc.gridy++;


        //add our action buttons
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnSave);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnBack);
        this.add(buttonPanel, gbc);

        btnAdd.addActionListener(e -> {
            tableModel.addStockItem(new Stock());
            table.revalidate();
        });

        btnDelete.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            //if no row selected
            if(selectedRow == -1){
                return;
            }
            //get user to confirm
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you wish to delete the selected user?", "Confirm", JOptionPane.YES_NO_OPTION);

            // return if user says no
            if(response == JOptionPane.NO_OPTION){
                return;
            }
            //delete the stock item
            tableModel.deleteStockItem(tableModel.getStockItem(selectedRow));
        });

        btnSave.addActionListener(e ->{
            //Clear any custom row colors of the table
            table.clearColors();

            //perform each action and create seperate list of each failed action so we
            //can add it back into the pending changes after
            List<Stock> failedSaveActions = new ArrayList<>();
            for (Stock stockItem : tableModel.getPendingState().getInserts()) {
                boolean ok = stockService.createStock(stockItem);
                if(!ok){
                    failedSaveActions.add(stockItem);
                }
            }
            List<Stock> failedUpdateActions = new ArrayList<>();
            for (Stock stockItem : tableModel.getPendingState().getUpdates()) {
                boolean ok = stockService.updateStock(stockItem);
                if(!ok){
                    failedUpdateActions.add(stockItem);
                }
            }
            List<Stock> failedDeleteActions = new ArrayList<>();
            for (Stock stockItem : tableModel.getPendingState().getDeletes()) {
                boolean ok = stockService.deleteStock(stockItem);
                if(!ok){
                    failedDeleteActions.add(stockItem);
                }
            }
            //This clear's all pending states as we've made go no further changes
            tableModel.getPendingState().reset();
            // add failed actions back into pending state
            tableModel.getPendingState().getUpdates().addAll(failedUpdateActions);
            tableModel.getPendingState().getInserts().addAll(failedSaveActions);
            tableModel.getPendingState().getDeletes().addAll(failedDeleteActions);

            // Deleted stock must be added back into the table if it's failed
            // this function doesnt fire the event which is important as otherwise
            // it would become an insertion
            tableModel.addAllFailedDeletions(failedDeleteActions);

            //Mark all failed actions as red rows in the table
            List<Stock> allFailedItems = new ArrayList<>();
            allFailedItems.addAll(failedUpdateActions);
            allFailedItems.addAll(failedSaveActions);
            allFailedItems.addAll(failedDeleteActions);
            for (Stock stock : allFailedItems) {
                table.setRowColor(tableModel.getRowIndexOf(stock), Color.RED);
            }
            table.repaint();
        });

        btnBack.addActionListener(e -> Application.getInstance().setWindow(ApplicationPanel.getInstance()));

        this.setVisible(true);
    }

    public static void main(String[] args) {
       new StockPanel();
    }
}

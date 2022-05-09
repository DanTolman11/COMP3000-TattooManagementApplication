package com.tolman.view.tablesmodels.stock;

import com.tolman.model.Stock;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Stock table model is a table model for the stock items editing view
 * it sets up an initial change listener as a {@See StockTableChangeListener}
 * as well as handling insertion deletion and updateing of values
 *
 */
public class StockTableModel extends AbstractTableModel {
    //Column headers of the table defined in order
    private List<String> columnHeaders = Arrays.asList("Name", "Amount", "Category");

    // our row data
    private List<Stock> tableData;

    //instant of the listener
    private  StockTableModelChangeListener changeListener;

    //constructor
    public StockTableModel(List<Stock> stockList) {
        this.tableData = stockList;
        changeListener = new StockTableModelChangeListener(this);
        this.addTableModelListener(changeListener);
    }

    public StockTablePendingState getPendingState() {
        return changeListener.getStockTablePendingState();
    }

    public void addStockItem(Stock stock){
        this.tableData.add(stock);
        fireTableRowsInserted(tableData.size() -1, tableData.size() - 1);
    }

    public Stock getStockItem(int i){
        return tableData.get(i);
    }

    public void deleteStockItem(Stock stock){

        int index = this.tableData.indexOf(stock);
        fireTableRowsDeleted(index, index);
        this.tableData.remove(stock);
    }

    public List<Stock> getRowsBetween(int firstRow, int lastRow) {
        List<Stock> returnValue = new ArrayList<>();
        for (int i = firstRow; i <= lastRow; i++) {
            returnValue.add(tableData.get(i));
        }
        return returnValue;
    }


    @Override
    public int getRowCount() {
        return tableData.size();
    }

    @Override
    public int getColumnCount() {
        return columnHeaders.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnHeaders.get(columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Stock stock = tableData.get(rowIndex);
        switch (columnIndex){
            case 0:
                return stock.getName();
            case 1:
                return stock.getAmount();
            case 2:
                return stock.getCategory();
            default:
                throw new RuntimeException("Failed to find column");
        }

    }

    @Override
    public void setValueAt(Object newValue, int rowIndex, int columnIndex) {
        Stock stockItem = tableData.get(rowIndex);
        switch (columnIndex) {
            case 0:
                stockItem.setName(newValue.toString());
                break;
            case 1:
                stockItem.setAmount(Integer.parseInt(newValue.toString()));
                break;
            case 2:
                stockItem.setCategory(newValue.toString());
                break;
            default:
                throw new RuntimeException("Failed to find column");
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public int getRowIndexOf(Stock stock) {
        return tableData.indexOf(stock);
    }

    public void addAllFailedDeletions(List<Stock> failedDeleteActions) {
        tableData.addAll(failedDeleteActions);
    }
}


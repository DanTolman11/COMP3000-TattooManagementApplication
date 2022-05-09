package com.tolman.view.tablesmodels.account;

import com.tolman.model.User;
import com.tolman.view.ui.CustomJTable;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserTableModel  extends AbstractTableModel {

    private List<String> columnHeaders = Arrays.asList("Username");

    private List<User> tableData;

    private UserTableModelChangeListener changeListener;

    private CustomJTable table;

    public UserTableModel(List<User> userList) {
        this.tableData = userList;
        changeListener = new UserTableModelChangeListener(this);
        this.addTableModelListener(changeListener);
    }

    public UserTablePendingState getPendingState() { return  changeListener.getUserTablePendingState();}

    public void addUser(User user){
        this.tableData.add(user);
        fireTableRowsInserted(tableData.size() -1, tableData.size() -1);
    }

    public User getUser(int i) { return tableData.get(i); }

    public void deleteUser(User user){
        int index = this.tableData.indexOf(user);
        fireTableRowsDeleted(index, index);
        this.tableData.remove(user);
    }

    public List<User> getRowsBetween(int firstRow, int lastRow) {
        List<User> returnValue = new ArrayList<>();
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
    public String getColumnName(int columnIndex) {return columnHeaders.get(columnIndex); }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) { return false;}

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = tableData.get(rowIndex);
        switch (columnIndex){
            case 0:
                return user.getUsername();
            default:
                throw new RuntimeException("Failed to find column");
        }
    }

    public int getRowIndexOf(User user) { return tableData.indexOf(user); }

    public void addAllFailedDeletions(List<User> failedDeleteActions) { tableData.addAll(failedDeleteActions); }
}

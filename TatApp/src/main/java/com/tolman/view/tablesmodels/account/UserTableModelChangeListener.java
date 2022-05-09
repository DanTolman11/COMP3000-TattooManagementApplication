package com.tolman.view.tablesmodels.account;

import com.tolman.model.User;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.util.ArrayList;
import java.util.List;

public class UserTableModelChangeListener  implements TableModelListener {

    private UserTablePendingState userTablePendingState;
    private UserTableModel userTableModel;

    public UserTableModelChangeListener(UserTableModel userTableModel){
        this.userTableModel = userTableModel;
        userTablePendingState = new UserTablePendingState();
    }

    public UserTablePendingState getUserTablePendingState() {
        return userTablePendingState;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        List<User> userSelected = new ArrayList<>();
        switch (e.getType()) {
            case TableModelEvent.INSERT:
                userTablePendingState.getInserts().addAll(userTableModel.getRowsBetween(e.getFirstRow(), e.getLastRow()));
                break;
            case TableModelEvent.DELETE:
                userSelected = userTableModel.getRowsBetween(e.getFirstRow(), e.getLastRow());
                userTablePendingState.getInserts().removeAll(userSelected);
                userTablePendingState.getDeletes().addAll(userSelected);
                break;
        }
    }
}

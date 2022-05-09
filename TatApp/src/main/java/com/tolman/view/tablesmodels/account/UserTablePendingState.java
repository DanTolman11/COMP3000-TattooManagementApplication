package com.tolman.view.tablesmodels.account;

import com.tolman.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserTablePendingState {


    private List<User> inserts = new ArrayList<>();
    private List<User> deletes = new ArrayList<>();

    public List<User> getInserts() { return inserts; }

    public List<User> getDeletes() { return deletes; }

    public void reset(){
        inserts.clear();
        deletes.clear();
    }
}

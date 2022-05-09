package com.tolman.view.tablesmodels.booking;

import com.tolman.model.Booking;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

public class BookingTablePendingState {

    private List<Booking> updates = new ArrayList<>();
    private List<Booking> inserts = new ArrayList<>();
    private List<Booking> deletes = new ArrayList<>();

    public List<Booking> getUpdates() { return updates; }

    public List<Booking> getInserts() { return inserts; }

    public List<Booking> getDeletes() { return deletes; }

    public void reset(){
        updates.clear();
        inserts.clear();
        deletes.clear();
    }
}

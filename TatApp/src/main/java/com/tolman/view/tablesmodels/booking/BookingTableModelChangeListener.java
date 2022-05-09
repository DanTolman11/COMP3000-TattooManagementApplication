package com.tolman.view.tablesmodels.booking;

import com.tolman.model.Booking;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.util.ArrayList;
import java.util.List;

public class BookingTableModelChangeListener implements TableModelListener {

    private BookingTablePendingState bookingTablePendingState;
    private BookingTableModel bookingTableModel;

    public BookingTableModelChangeListener(BookingTableModel bookingTableModel) {
        this.bookingTableModel = bookingTableModel;
        bookingTablePendingState = new BookingTablePendingState();
    }

    public BookingTablePendingState getBookingTablePendingState() { return bookingTablePendingState; }

    @Override
    public void tableChanged(TableModelEvent e) {
        List<Booking> bookingAffected = new ArrayList<>();
        switch (e.getType()){
            case TableModelEvent.INSERT:
                bookingTablePendingState.getInserts().addAll(bookingTableModel.getRowsBetween(e.getFirstRow(), e.getLastRow()));
                break;
            case TableModelEvent.UPDATE:
                bookingAffected = bookingTableModel.getRowsBetween(e.getFirstRow(), e.getLastRow());


                List<Booking> actualUpdates = new ArrayList<>();
                for (Booking booking : bookingAffected) {
                    if (!bookingTablePendingState.getInserts().contains(booking)) {
                        actualUpdates.add(booking);
                    }
                }
                bookingTablePendingState.getUpdates().addAll(actualUpdates);
                break;
            case TableModelEvent.DELETE:
                bookingAffected = bookingTableModel.getRowsBetween(e.getFirstRow(), e.getLastRow());
                bookingTablePendingState.getInserts().removeAll(bookingAffected);
                bookingTablePendingState.getUpdates().removeAll(bookingAffected);
                bookingTablePendingState.getDeletes().removeAll(bookingAffected);
                break;

        }
    }
}

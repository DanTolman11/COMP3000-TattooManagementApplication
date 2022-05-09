package com.tolman.view.tablesmodels.booking;

import com.tolman.model.Booking;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class BookingTableModel extends AbstractTableModel {

    private List<String> columnHeaders = Arrays.asList("Date", "Name", "Age", "Email", "Phone", "Brief");

    private List<Booking> tableData;

    private BookingTableModelChangeListener changeListener;

    public BookingTableModel(List<Booking> bookingList) {
        this.tableData = bookingList;
        changeListener = new BookingTableModelChangeListener(this);
        this.addTableModelListener(changeListener);
    }

    public BookingTablePendingState getPendingState() { return changeListener.getBookingTablePendingState(); }

    public void addBooking(Booking booking) {
        this.tableData.add(booking);
        fireTableRowsInserted(tableData.size() -1, tableData.size() - 1);
    }

    public Booking getBooking(int i) { return tableData.get(i);}

    public void deleteBooking(Booking booking){
        int index = this.tableData.indexOf(booking);
        fireTableRowsDeleted(index, index);
        this.tableData.remove(booking);
    }

    public List<Booking> getRowsBetween(int firstRow, int lastRow) {
        List<Booking> returnValue = new ArrayList<>();
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
        Booking booking = tableData.get(rowIndex);
        switch (columnIndex){
            case 0:
                return booking.getBookingDate();
            case 1:
                return booking.getName();
            case 2:
                return booking.getAge();
            case 3:
                return booking.getEmail();
            case 4:
                return booking.getPhoneNumber();
            case 5:
                return booking.getDesignBrief();
            default:
                throw new RuntimeException("Failed to find column");
        }
    }

    @Override
    public void setValueAt(Object newValue, int rowIndex, int columnIndex) {
        Booking booking = tableData.get(rowIndex);
        switch (columnIndex) {
            case 0:
                if (!(newValue instanceof Date)){
                    System.err.println("Error setting booking value");
                }
                //sort out how the date is parsed
                booking.setBookingDate((Date)newValue);
                break;
            case 1:
                booking.setName(newValue.toString());
                break;
            case 2:
                booking.setAge(Integer.parseInt(newValue.toString()));
                break;
            case 3:
                booking.setEmail(newValue.toString());
                break;
            case 4:
                booking.setPhoneNumber(newValue.toString());
                break;
            case 5:
                booking.setDesignBrief(newValue.toString());
                break;
            default:
                throw new RuntimeException("Failed to find column");
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public int getRowIndexOf(Booking booking) { return tableData.indexOf(booking); }

    public void addAllFailedDeletions(List<Booking> failedDeleteActions) { tableData.addAll(failedDeleteActions); }

}

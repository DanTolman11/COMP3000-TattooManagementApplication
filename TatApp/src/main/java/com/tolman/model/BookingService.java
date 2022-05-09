package com.tolman.model;

import com.tolman.model.database.BookingDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingService {

    private static BookingService INSTANCE;

    private BookingDAO bookingDAO = BookingDAO.getInstance();

    private BookingService(){

    }

    public boolean validateBooking(Booking booking){
        List<Booking> foundConflicts = bookingDAO.getBookingsWithinRange(booking.getBookingDate(), booking.getBookingDate());
        return foundConflicts.isEmpty();
    }

    public static BookingService getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new BookingService();
        }
        return INSTANCE;
    }

    public boolean createBooking(Booking booking) {
        if(!validateBooking(booking)){
            return false;
        }
        if(booking.getId() != 0){
            return false;
        }
        try {
            return bookingDAO.createBooking(booking);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return false;
    }

    public boolean updateBooking(Booking booking) {
        if(!validateBooking(booking)){
            return false;
        }
        if(booking.getId() == 0){
            return false;
        }
        try {
            return bookingDAO.updateBooking(booking);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public List<Booking> getAllBookings() {
        try {
            return bookingDAO.getAllBookings();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<>();
    }

    public boolean deleteBooking(Booking booking) {
        try {
            bookingDAO.deleteBooking(booking);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }
}

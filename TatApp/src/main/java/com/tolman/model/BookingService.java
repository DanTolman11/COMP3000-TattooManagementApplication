package com.tolman.model;

import com.tolman.model.database.BookingDAO;
import com.tolman.model.database.UserDAO;

import javax.naming.OperationNotSupportedException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingService {

    private static BookingService INSTANCE;

    private BookingDAO bookingDAO = BookingDAO.getInstance();

    private BookingService(){

    }

    public boolean validateBooking(Booking booking){
        List<Booking> foundConflicts = bookingDAO.getBookingsWithinRange(booking.getBookingDate(), booking.getBookingDate());
        return !foundConflicts.isEmpty();
    }

    public static BookingService getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new BookingService();
        }
        return INSTANCE;
    }

    public void saveBooking() {
        throw new RuntimeException("Implement me...");
    }

    public List<Booking> getAllBookings() {
        try {
            return bookingDAO.getAllBookings();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<>();
    }
}

package com.tolman.model.database;

import com.tolman.model.Booking;
import com.tolman.model.User;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingDAO {
    private static BookingDAO INSTANCE;
    private DBConnection connection = DBConnection.getInstance();
    private BookingDAO(){

    }

    public List<Booking> getBookingsWithinRange(Date start, Date end){
        //TODO write query checking dates
        // SELECT * FROM booking where booking.startdate>=start AND booking.endDate<end
        return new ArrayList<>();
    }
    public List<Booking> getAllBookings() throws SQLException {
        List<Booking> results = new ArrayList<>();
        String sql = String.format("select * from bookings");
        Statement statement = connection.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()){
            Booking booking = new Booking();
            booking.setId(rs.getLong("id"));
            booking.setBookingDate(rs.getDate("date"));
            booking.setName(rs.getString("name"));
            booking.setAge(rs.getInt("age"));
            booking.setEmail(rs.getString("email"));
            booking.setPhoneNumber(rs.getLong("phone number"));
            booking.setDesignBrief(rs.getString("design brief"));
            results.add(booking);

        }
        return results;

    }
    public static BookingDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BookingDAO();
        }

        return INSTANCE;
    }
}

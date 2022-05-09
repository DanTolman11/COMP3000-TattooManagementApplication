package com.tolman.model.database;

import com.tolman.model.Booking;

import javax.swing.plaf.nimbus.State;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static BookingDAO INSTANCE;

    private DBConnection connection = DBConnection.getInstance();

    private BookingDAO(){

    }

    public List<Booking> getBookingsWithinRange(java.util.Date start, java.util.Date end){
        //TODO write query checking dates
        // SELECT * FROM booking where booking.startdate>=start AND booking.endDate<end
        return new ArrayList<>();
    }
    public List<Booking> getAllBookings() throws SQLException {
        List<Booking> results = new ArrayList<>();
        String sql = String.format("select * from booking");
        Statement statement = connection.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()){
            Booking booking = new Booking();
            booking.setId(rs.getInt("id"));
            booking.setBookingDate(new Date(rs.getDate("date").getTime()));
            booking.setName(rs.getString("name"));
            booking.setAge(rs.getInt("age"));
            booking.setEmail(rs.getString("email"));
            booking.setPhoneNumber(rs.getString("phoneNumber"));
            booking.setDesignBrief(rs.getString("brief"));
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

    public boolean createBooking(Booking booking) throws SQLException {
        String sql = String.format("INSERT INTO booking(date, name, age, email, phoneNumber, brief) values('%d','%s','%s','%s','%s','%s')",booking.getBookingDate().getTime(), booking.getName(), booking.getAge(), booking.getEmail(), booking.getPhoneNumber(), booking.getDesignBrief());
        Statement statement = connection.getConnection().createStatement();
        statement.execute(sql);
        ResultSet rs = statement.getGeneratedKeys();
        while (rs.next()){
            booking.setId(rs.getInt(1));
        }
        return true;
    }

    public boolean updateBooking(Booking booking) throws SQLException {
        String sql = String.format("UPDATE booking set date='%s', name='%s', age='%d', email='%s', phoneNumber='%s', brief='%s' WHERE id='%d'", booking.getBookingDate().getTime(), booking.getName(), booking.getAge(), booking.getEmail(), booking.getPhoneNumber(), booking.getDesignBrief(), booking.getId());
        Statement statement = connection.getConnection().createStatement();
        statement.execute(sql);
        return true;
    }

    public void deleteBooking(Booking booking) throws SQLException {
        String sql = String.format("DELETE FROM booking WHERE id='%d'", booking.getId());
        Statement statement = connection.getConnection().createStatement();
        statement.execute(sql);
    }
}

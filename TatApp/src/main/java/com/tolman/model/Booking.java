package com.tolman.model;

import java.util.Date;

public class Booking {
    private int id;
    private Date bookingDate;
    private String name;
    private int age;
    private String email;
    private String phoneNumber;
    private String designBrief;


    public int getId() { return id;}
    public void setId(int id) { this.id = id;}

    public Date getBookingDate() { return bookingDate;}
    public void setBookingDate(Date bookingDate) { this.bookingDate = bookingDate;}

    public String getName() { return name;}
    public void setName(String name) { this.name = name;}

    public int getAge() { return age;}
    public void setAge(int age) { this.age = age;}

    public String getEmail() { return email;}
    public void setEmail(String email) { this.email = email;}

    public String getPhoneNumber() { return phoneNumber;}
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber;}

    public String getDesignBrief() { return designBrief;}
    public void setDesignBrief(String designBrief) { this.designBrief = designBrief;}

    @Override
    public String toString() {
        if(name == null){
            return "Empty Booking";
        }
        return "Booking:" + name;
        //return "Booking{set by the toString function}";
    }
}



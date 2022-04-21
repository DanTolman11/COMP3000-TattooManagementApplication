package com.tolman.model;

import java.sql.Date;

public class Booking {
    private long id;
    private Date bookingDate;
    private String name;
    private int age;
    private String email;
    private long phoneNumber;
    private String designBrief;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDesignBrief() {
        return designBrief;
    }

    public void setDesignBrief(String designBrief) {
        this.designBrief = designBrief;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String setEmail() {
        return email;
    }

    public void getEmail(String email) {
        this.email = email;
    }

    public long setPhoneNumber() {
        return phoneNumber;
    }

    public void getPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String setDesignBrief() {
        return designBrief;
    }

    public void getDesignBrief(String designBrief) {
        this.designBrief = designBrief;
    }

    @Override
    public String toString() {
        if(name == null){
            return "Empty Booking";
        }
        return "Booking:" + name;
    }
}



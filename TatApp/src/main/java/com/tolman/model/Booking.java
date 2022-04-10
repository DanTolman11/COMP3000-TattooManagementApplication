package com.tolman.model;

public class Booking {
    private long id;
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
}



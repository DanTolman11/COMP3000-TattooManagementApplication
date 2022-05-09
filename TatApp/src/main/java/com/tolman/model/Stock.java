package com.tolman.model;

public class Stock {
    private int id;
    private String name;
    private int amount;
    private String category;

    public int getId() { return id;}
    public void setId(int id) { this.id = id;}

    public String getName() { return name;}
    public void setName(String name) { this.name = name;}

    public long getAmount() { return amount;}
    public void setAmount(int amount) { this.amount = amount;}

    public String getCategory() { return category;}
    public void setCategory(String category) { this.category = category;}

    @Override
    public String toString() {
        return "Stock{set by the toString function}";
    }
}

package com.example.project;

import java.util.ArrayList;

public class Advertisement extends Message {
    String productName;
    int productPrice;
    boolean discount;  //  true: has a discount, false: doesn't have a discount
    int discountPercentage;
    ArrayList<User> seen = new ArrayList<>();

    public Advertisement(User sender, ArrayList<String> text, String name,
                         int price, boolean isDiscount, int percent) {
        this.sender = sender;
        this.textMessage = text;
        this.messageNum = HelloApplication.messageNum;
        HelloApplication.messageNum = HelloApplication.messageNum + 1;
        this.productName = name;
        this.productPrice = price;
        this.discount = isDiscount;
        this.isDeleted = false;
        this.isEdited = false;
        this.discountPercentage = percent;
        Database.advertisements.add(this);
        Database.messages.add(this);
    }
    public Advertisement (User sender, ArrayList<String> text, int messageNum, String name, int price, boolean isDiscount,
                          boolean isDeleted, boolean isEdited, int percent) {
        this.sender = sender;
        this.textMessage = text;
        this.messageNum = messageNum;
        this.productName = name;
        this.productPrice = price;
        this.discount = isDiscount;
        this.isDeleted = isDeleted;
        this.isEdited = isEdited;
        this.discountPercentage = percent;
        Database.advertisements.add(this);
        Database.messages.add(this);
    }
}


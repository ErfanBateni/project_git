package com.example.project;

import java.util.ArrayList;

public class User {
    String userName;
    String password;
    boolean userType;  // 0 for ordinary account, 1 for business account
    int credit;
    String securityAnswer;
    ArrayList<User> followers = new ArrayList<>();
    ArrayList<User> followings = new ArrayList<>();
    ArrayList<User> blocked = new ArrayList<>();
    ArrayList<Direct> directs = new ArrayList<>();
    ArrayList<Group> groups = new ArrayList<>();
    ArrayList<Post> posts = new ArrayList<>();

    public User (String name, String password, boolean type, String security) {
        this.userName = name;
        this.password = password;
        this.userType = type;
        this.credit = 0;
        this.securityAnswer = security;
        Database.users.add(this);
    }
}

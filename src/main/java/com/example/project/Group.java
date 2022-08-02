package com.example.project;

import java.util.ArrayList;

public class Group {
    String groupName;
    ArrayList<GroupMessage> messages = new ArrayList<>();
    ArrayList<User> members = new ArrayList<>();
    User admin;

    public Group (String name, User creator) {
        this.groupName = name;
        this.admin = creator;
        this.members.add(creator);
        creator.groups.add(this);
        Database.groups.add(this);
    }
}

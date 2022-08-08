package com.example.project;

import java.util.ArrayList;
import java.util.HashMap;

public class Group {
    String groupName;
    ArrayList<GroupMessage> messages = new ArrayList<>();
    ArrayList<User> members = new ArrayList<>();
    HashMap<User,Boolean> seen = new HashMap<>();
    User admin;
    String picture;

    public Group (String name, User creator, String picture) {
        this.groupName = name;
        this.picture = picture;
        this.admin = creator;
        this.members.add(creator);
        creator.groups.add(this);
        Database.groups.add(this);
        this.seen.put(creator,false);
    }
}

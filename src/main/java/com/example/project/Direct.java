package com.example.project;

import java.util.ArrayList;

public class Direct {
    User first;
    User second;
    ArrayList<DirectMessage> messages = new ArrayList<>();

    public Direct (User second) {
        int v = 0;
        for (int i = 0; i < Database.users.size(); i++) {
            if (Controller.currentUser.equals(Database.users.get(i).userName)) {
                v = i;
            }
        }
        this.first = Database.users.get(v);
        this.first.directs.add(this);
        this.second = second;
        this.second.directs.add(this);
        Database.directs.add(this);
    }

    public Direct (User first, User second) {
        this.first = first;
        this.first.directs.add(this);
        this.second = second;
        this.second.directs.add(this);
        Database.directs.add(this);
    }
}


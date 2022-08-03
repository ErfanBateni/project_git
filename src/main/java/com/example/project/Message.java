package com.example.project;

import java.util.ArrayList;

public class Message {
    User sender;
    String textMessage;
    int messageNum;
    boolean isDeleted;
    boolean isEdited;
    ArrayList<User> liked = new ArrayList<>();
    ArrayList<User> disliked = new ArrayList<>();
}


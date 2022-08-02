package com.example.project;

import java.util.ArrayList;

public class Post extends Message {
    ArrayList<CommentPost> replies = new ArrayList<>();
    public Post (User sender, ArrayList<String> text) {
        this.sender = sender;
        this.textMessage = text;
        this.messageNum = HelloApplication.messageNum;
        HelloApplication.messageNum = HelloApplication.messageNum + 1;
        this.isDeleted = false;
        this.isEdited = false;
        Database.posts.add(this);
        Database.messages.add(this);
    }
    public Post (User sender, ArrayList<String> text, int messageNum, boolean isDeleted, boolean isEdited) {
        this.sender = sender;
        this.textMessage = text;
        this.messageNum = messageNum;
        this.isDeleted = isDeleted;
        this.isEdited = isEdited;
        Database.posts.add(this);
        Database.messages.add(this);
    }

    public Post() {

    }
}

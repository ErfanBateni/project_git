package com.example.project;

import java.util.ArrayList;

public class CommentPost extends Post {
    Post repliedMessage;
    public CommentPost (User sender, String text, Post replied) {
        super(sender, text);
        this.sender = sender;
        this.textMessage = text;
        this.messageNum = HelloApplication.messageNum;
        this.repliedMessage = replied;
        HelloApplication.messageNum = HelloApplication.messageNum + 1;
        this.isDeleted = false;
        this.isEdited = false;
        replied.replies.add(this);
        Database.commentPosts.add(this);
        Database.messages.add(this);
    }
    public CommentPost (User sender, String text, int messageNum, boolean isDeleted, boolean isEdited) {
        super();
        this.sender = sender;
        this.textMessage = text;
        this.messageNum = messageNum;
        this.isDeleted = isDeleted;
        this.isEdited = isEdited;
        Database.commentPosts.add(this);
        Database.messages.add(this);
    }
}


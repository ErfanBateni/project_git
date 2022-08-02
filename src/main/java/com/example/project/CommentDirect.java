package com.example.project;

import java.util.ArrayList;

public class CommentDirect extends DirectMessage {
    DirectMessage repliedMessage;
    public CommentDirect (User sender, ArrayList<String> text, Direct receiver, DirectMessage replied) {
        super(sender, text, receiver);
        this.sender = sender;
        this.textMessage = text;
        this.receiverDirect = receiver;
        this.messageNum = HelloApplication.messageNum;
        this.repliedMessage = replied;
        HelloApplication.messageNum = HelloApplication.messageNum + 1;
        this.isDeleted = false;
        this.isEdited = false;
        replied.replies.add(this);
        replied.receiverDirect.messages.add(this);
        Database.commentDirects.add(this);
        Database.messages.add(this);
    }
    public CommentDirect (User sender, ArrayList<String> text, int messageNum, boolean isDeleted, boolean isEdited) {
        this.sender = sender;
        this.textMessage = text;
        this.messageNum = messageNum;
        this.isDeleted = isDeleted;
        this.isEdited = isEdited;
        Database.commentDirects.add(this);
        Database.messages.add(this);
    }
}


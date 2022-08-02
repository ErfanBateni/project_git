package com.example.project;

import java.util.ArrayList;

public class CommentGroup extends GroupMessage {
    GroupMessage repliedMessage;
    public CommentGroup (User sender, ArrayList<String> text, Group receiver, GroupMessage replied) {
        super(sender, text, receiver);
        this.sender = sender;
        this.textMessage = text;
        this.receiverGroup = receiver;
        this.messageNum = HelloApplication.messageNum;
        this.repliedMessage = replied;
        HelloApplication.messageNum = HelloApplication.messageNum + 1;
        this.isDeleted = false;
        this.isEdited = false;
        replied.replies.add(this);
        replied.receiverGroup.messages.add(this);
        Database.commentGroups.add(this);
        Database.messages.add(this);
    }
    public CommentGroup (User sender, ArrayList<String> text, int messageNum, boolean isDeleted, boolean isEdited) {
        this.sender = sender;
        this.textMessage = text;
        this.messageNum = messageNum;
        this.isDeleted = isDeleted;
        this.isEdited = isEdited;
        Database.commentGroups.add(this);
        Database.messages.add(this);
    }
}


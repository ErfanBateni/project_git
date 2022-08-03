package com.example.project;

import java.util.ArrayList;

public class GroupMessage extends Message {
    Group receiverGroup;  //  The group that the message is sent to
    ArrayList<CommentGroup> replies = new ArrayList<>();
    public GroupMessage (User sender, String text, Group receiver) {
        this.sender = sender;
        this.textMessage = text;
        this.receiverGroup = receiver;
        this.messageNum = HelloApplication.messageNum;
        HelloApplication.messageNum = HelloApplication.messageNum + 1;
        receiver.messages.add(this);
        this.isDeleted = false;
        this.isEdited = false;
        Database.groupMessages.add(this);
        Database.messages.add(this);
    }

    public GroupMessage (User sender, String text, int messageNum, boolean isDeleted, boolean isEdited) {
        this.sender = sender;
        this.textMessage = text;
        this.messageNum = messageNum;
        this.isDeleted = isDeleted;
        this.isEdited = isEdited;
        Database.groupMessages.add(this);
        Database.messages.add(this);
    }

    public GroupMessage() {

    }
}

package com.example.project;

import java.util.ArrayList;

public class ForwardedGroup extends GroupMessage {
    Message forwardedMessage;
    public ForwardedGroup (User sender, ArrayList<String> text, Group receiver, Message forwarded) {
        super(sender, text, receiver);
        this.sender = sender;
        this.textMessage = text;
        this.receiverGroup = receiver;
        this.forwardedMessage = forwarded;
        this.messageNum = HelloApplication.messageNum;
        HelloApplication.messageNum = HelloApplication.messageNum + 1;
        this.isDeleted = false;
        this.isEdited = false;
        receiver.messages.add(this);
        Database.forwardedMessageGroups.add(this);
        Database.messages.add(this);
    }
    public ForwardedGroup (User sender, ArrayList<String> text, int messageNum, boolean isDeleted, boolean isEdited) {
        this.sender = sender;
        this.textMessage = text;
        this.messageNum = messageNum;
        this.isDeleted = isDeleted;
        this.isEdited = isEdited;
        Database.forwardedMessageGroups.add(this);
        Database.messages.add(this);
    }
}


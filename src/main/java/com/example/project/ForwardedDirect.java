package com.example.project;

import java.util.ArrayList;

public class ForwardedDirect extends DirectMessage {
    Message forwardedMessage;
    public ForwardedDirect (User sender, String text, Direct receiver, Message forwarded) {
        super(sender, text, receiver);
        this.sender = sender;
        this.textMessage = text;
        this.receiverDirect = receiver;
        this.forwardedMessage = forwarded;
        this.messageNum = HelloApplication.messageNum;
        HelloApplication.messageNum = HelloApplication.messageNum + 1;
        this.isDeleted = false;
        this.isEdited = false;
        receiver.messages.add(this);
        Database.forwardedMessageDirects.add(this);
        Database.messages.add(this);
    }
    public ForwardedDirect (User sender, String text, int messageNum, boolean isDeleted, boolean isEdited) {
        this.sender = sender;
        this.textMessage = text;
        this.messageNum = messageNum;
        this.isDeleted = isDeleted;
        this.isEdited = isEdited;
        Database.forwardedMessageDirects.add(this);
        Database.messages.add(this);
    }
}


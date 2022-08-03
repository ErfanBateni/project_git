package com.example.project;

import java.util.ArrayList;

public class DirectMessage extends Message {
    Direct receiverDirect;  //  Sender sends his message to the "receiver"
    ArrayList<CommentDirect> replies = new ArrayList<>();
    public DirectMessage(User sender, String text, Direct receiver) {
        this.sender = sender;
        this.textMessage = text;
        this.receiverDirect = receiver;
        this.messageNum = HelloApplication.messageNum;
        HelloApplication.messageNum = HelloApplication.messageNum + 1;
        receiver.messages.add(this);
        this.isDeleted = false;
        this.isEdited = false;
        Database.directMessages.add(this);
        Database.messages.add(this);
    }

    public DirectMessage (User sender, String text, int messageNum, boolean isDeleted, boolean isEdited) {
        this.sender = sender;
        this.textMessage = text;
        this.messageNum = messageNum;
        this.isDeleted = isDeleted;
        this.isEdited = isEdited;
        Database.directMessages.add(this);
        Database.messages.add(this);
    }

    public DirectMessage() {

    }
}


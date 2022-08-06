package com.example.project;

import java.util.ArrayList;

public class CommentPost extends Message {
    Post receiverPost;
    public CommentPost (User sender, String text, Post post) {
        this.sender = sender;
        this.textMessage = text;
        this.receiverPost = post;
        this.messageNum = HelloApplication.messageNum;
        HelloApplication.messageNum = HelloApplication.messageNum + 1;
        post.replies.add(this);
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


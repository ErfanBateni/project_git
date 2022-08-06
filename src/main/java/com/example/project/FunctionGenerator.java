package com.example.project;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class FunctionGenerator {
    public static void signUp (String name, String pass, boolean type, String security) {
        boolean userExists = false;
        for (int i = 0; i < Database.users.size(); i++) {
            if (Database.users.get(i).userName.equals(name)) {
                userExists = true;
                break;
            }
        }
        if (userExists) {
            System.out.println("This username already exists. Please try again.");
            Scanner scan = new Scanner(System.in);
            String nam = scan.nextLine();
            signUp(nam, pass, type, security);
        }
        else {
            if (pass.length() < 8) {
                System.out.println("This password is too short. Your password should have at least 8 characters. Please try again.");
                Scanner scan = new Scanner(System.in);
                String pas = scan.nextLine();
                signUp(name, pas, type, security);
            }
            else {
                User newUser = new User(name, pass, type, security);
                System.out.println("You have signed up successfully. \n");
            }
        }
    } //done

    public static void login (String name, String pass) {
        boolean userExists = false;
        int v = 0;
        try {
            for (int i = 0; i < Database.users.size(); i++) {
                if (Database.users.get(i).userName.equals(name)) {
                    userExists = true;
                    v = i;
                    break;
                }
            }
        }
        catch (Exception e) {
            userExists = true;
        }
        if (userExists) {
            if (Database.users.get(v).password.equals(pass)) {
                Controller.currentUser = Database.users.get(v).userName;
                System.out.println("You have logged in successfully.");
            }
            else{
                System.out.println("Your password is incorrect. Please try again.");
                Scanner scan = new Scanner(System.in);
                String pas = scan.nextLine();
                login(name, pas);
            }
        }
        else {
            System.out.println("No user exists with your intended username. Please try again.");
            Scanner scan = new Scanner(System.in);
            String nam = scan.nextLine();
            login (nam, pass);
        }
    } //done

    public static void logout () {
        Controller.currentUser = "";
    } //done

    public static void forgetPassword (String name, String security) {
        boolean userExists = false;
        int v = 0;
        for (int i = 0; i < Database.users.size(); i++) {
            if (Database.users.get(i).userName.equals(name)) {
                userExists = true;
                v = i;
                break;
            }
        }
        if (userExists) {
            if (Database.users.get(v).securityAnswer.equals(security)) {
                System.out.print("You answered correctly. Your password was ");
                System.out.println(Database.users.get(v).password + " .");
                System.out.println("Would you like to change your password?");
                Scanner scan = new Scanner(System.in);
                String answer = scan.nextLine();
                if (answer.charAt(0) == 'Y' || answer.charAt(0) == 'y') {
                    System.out.println("Please, enter your new password.");
                    String pass = scan.nextLine();
                    Database.users.get(v).password = pass;
                    System.out.println("Your new password has been successfully set.");
                    login (name, pass);
                }
                else {
                    System.out.println("OK, you are now logged in.");
                    login (name, Database.users.get(v).password);
                }
            }
            else {
                System.out.println("Your answer is incorrect, please try again.");
                Scanner scan = new Scanner(System.in);
                String secure = scan.nextLine();
                forgetPassword (name, secure);
            }
        }
        else {
            System.out.println("No user exists with your intended username.");
            Scanner scan = new Scanner(System.in);
            String nam = scan.nextLine();
            forgetPassword (nam, security);
        }
    } //done



    public static void addMember (User b, Group c) {
        int v = 0;
        for (int i = 0; i < Database.users.size(); i++) {
            if (Controller.currentUser.equals(Database.users.get(i).userName)) {
                v = i;
                break;
            }
        }
        if (c.admin.equals(Database.users.get(v)) && !c.members.contains(b)) {
            c.members.add(b);
            b.groups.add(c);
            System.out.println("This user was added successfully.");
        }
        else {
            if (!c.admin.equals(Database.users.get(v))) {
                System.out.println("You do not have the permission to add this member.");
            }
            if (c.members.contains(b)) {
                System.out.println("This user is already a member of this chat.");
            }
        }
    } //done

    public static void removeMember (User b, Group c) {
        int v = 0;
        for (int i = 0; i < Database.users.size(); i++) {
            if (Controller.currentUser.equals(Database.users.get(i).userName)) {
                v = i;
                break;
            }
        }
        if (c.admin.equals(Database.users.get(v)) && c.members.contains(b)) {
            c.members.remove(b);
            b.groups.remove(c);
            System.out.println("This user was removed successfully.");
        }
        else {
            if (!c.admin.equals(Database.users.get(v))) {
                System.out.println("You do not have the permission to remove this user.");
            }
            if (!c.members.contains(b)) {
                System.out.println("This user is not a member of this chat yet. So, you can not remove him.");
            }
        }
    } //done

    public static void joinChat (User a, Group b) {
        if (!b.members.contains(a)) {
            b.members.add(a);
            a.groups.add(b);
            System.out.println("You are now a member of this group.");
        }
        else {
            System.out.println("You are already a member of this chat.");
        }
    } //done

    public static void listOfMembers (Group a) {
        for (int i = 0; i < a.members.size(); i++) {
            System.out.println(a.members.get(i).userName);
        }
        System.out.print("\n");
    } //done

    public static void leaveChat (User a, Group b) {
        if (!b.admin.equals(a)) {
            if (a.groups.contains(b)) {
                a.groups.remove(b);
                b.members.remove(a);
                System.out.println("You have left the group " + b.groupName);
            }
            else {
                System.out.println("You can not leave this group, because you are not currently a member of this chat.");
            }
        }
        else {
            System.out.println("You can not leave this chat, because you are the admin.");
        }
    } //done

    public static void changeGroupName (User a, Group b, String c) {
        if (a.equals(b.admin) && !c.equals(b.groupName)) {
            b.groupName = c;
            System.out.println("You have successfully changed the group's name to " + c);
        }
        else {
            if (!a.equals(b.admin)) {
                System.out.println("You do not have the permission to change the name of the group.");
            }
            if (c.equals(b.groupName)) {
                System.out.println("The former and latter names are the same.");
            }
        }
    } //done



    public static void like (User a, Message b) {
        if (b.liked.contains(a)) {
            b.liked.remove(a);
            System.out.println("You took your reaction back.");
        }
        else {
            if (b.disliked.contains(a)) {
                b.disliked.remove(a);
                b.liked.add(a);
                System.out.println("You used to dislike this message, You just liked it.");
            }
            else {
                b.liked.add(a);
                System.out.println("You liked this message.");
            }
        }
    } //done

    public static void dislike (User a, Message b) {
        if (b.disliked.contains(a)) {
            b.disliked.remove(a);
            System.out.println("You took your reaction back.");
        }
        else {
            if (b.liked.contains(a)) {
                b.liked.remove(a);
                b.disliked.add(a);
                System.out.println("You used to like this message, You just disliked it.");
            }
            else {
                b.disliked.add(a);
                System.out.println("You disliked this message.");
            }
        }
    } //done



    public static void editMessage (ArrayList<String> text, Message edited) {/*
        if (!edited.isDeleted) {
            if (Controller.currentUser.equals(edited.sender.userName)) {
                if ((edited instanceof Post && ! (edited instanceof CommentPost)) || edited instanceof Advertisement) {
                    System.out.println("You can not edit posts and advertisements.");
                }

                if (edited instanceof CommentPost || edited instanceof CommentDirect || edited instanceof CommentGroup) {
                    if (!edited.isEdited) {
                        edited.isEdited = true;
                    }
                    String help0 = edited.textMessage.get(0);
                    String help1 = edited.textMessage.get(1);
                    String help2 = edited.textMessage.get(2);
                    String help3 = edited.textMessage.get(3);
                    String help4 = edited.textMessage.get(4);
                    String help5 = edited.textMessage.get(5);
                    edited.textMessage.clear();
                    edited.textMessage.add(help0);
                    edited.textMessage.add(help1);
                    edited.textMessage.add(help2);
                    edited.textMessage.add(help3);
                    edited.textMessage.add(help4);
                    edited.textMessage.add(help5);
                    edited.textMessage.addAll(text);
                    edited.textMessage.add("edited");
                }

                if ((edited instanceof DirectMessage && ! (edited instanceof CommentDirect) && ! (edited instanceof ForwardedDirect))
                        || (edited instanceof GroupMessage && ! (edited instanceof CommentGroup) && ! (edited instanceof ForwardedGroup))) {
                    if (!edited.isEdited) {
                        edited.isEdited = true;
                    }
                    edited.textMessage.clear();
                    edited.textMessage.addAll(text);
                    edited.textMessage.add("edited");
                }

                if (edited instanceof ForwardedDirect || edited instanceof ForwardedGroup) {
                    System.out.println("You can not edit forwarded messages.");
                }
            }
            else {
                System.out.println("You can not edit the message you did not send.");
            }
        }
    */} //done

    public static void deleteMessage (User a, Message deleted) {/*
        if (deleted instanceof DirectMessage) {
            if (deleted.sender.equals(a)) {
                deleted.isDeleted = true;
                System.out.println("The message was deleted.");
                for (int i = 0; i < ((DirectMessage) deleted).replies.size(); i++) {
                    ArrayList<String> text = new ArrayList<>();
                    text.add("The");  text.add("replied");  text.add("message");
                    text.add("is");  text.add("currently");  text.add("deleted");
                    text.addAll(((DirectMessage) deleted).replies.get(i).textMessage);
                    ((DirectMessage) deleted).replies.get(i).textMessage.clear();
                    for (String s : text) {
                        ((DirectMessage) deleted).replies.get(i).textMessage.add(s);
                    }
                }
            }
            else {
                System.out.println("You can not delete the message you did not send.");
            }
        }

        if (deleted instanceof Advertisement) {
            if (deleted.sender.equals(a)) {
                deleted.isDeleted = true;
                System.out.println("The message was deleted.");
            }
            else {
                System.out.println("You can not delete the message you did not send.");
            }
        }

        if (deleted instanceof GroupMessage) {
            if (deleted.sender.equals(a) || ((GroupMessage) deleted).receiverGroup.admin.equals(a)) {
                deleted.isDeleted = true;
                System.out.println("The message was deleted.");
                for (int i = 0; i < ((GroupMessage) deleted).replies.size(); i++) {
                    ArrayList<String> text = new ArrayList<>();
                    text.add("The");
                    text.add("replied");
                    text.add("message");
                    text.add("is");
                    text.add("currently");
                    text.add("deleted.");
                    text.addAll(((GroupMessage) deleted).replies.get(i).textMessage);
                    ((GroupMessage) deleted).replies.get(i).textMessage.clear();
                    for (String s : text) {
                        ((GroupMessage) deleted).replies.get(i).textMessage.add(s);
                    }
                }
            }
            else {
                System.out.println("You are not either an admin or the sender of this message.");
            }
        }
    */} //done



    public static void follow (User follower, User followed) {
        if (!followed.followers.contains(follower) && !followed.blocked.contains(follower)) {
            followed.followers.add(follower);
            follower.followings.add(followed);
            System.out.println("You are now following " + followed.userName + " .");
        }
        else {
            if (followed.followers.contains(follower)) {
                System.out.println("You already followed this user.");
            }
            if (followed.blocked.contains(follower)) {
                System.out.println("You have been blocked by this user.");
            }
        }
    } //done

    public static void unfollow (User follower, User followed) {
        if (followed.followers.contains(follower)) {
            followed.followers.remove(follower);
            follower.followings.remove(followed);
            System.out.println("You no longer follow " + followed.userName + " .");
        }
        else {
            System.out.println("You have not already followed this user.");
        }
    } //done

    public static void blockUser (User blocked, User blocker) {
        if (!blocker.blocked.contains(blocked)) {
            blocker.blocked.add(blocked);
            System.out.println( blocked.userName + " has been blocked.");
        }
        else {
            System.out.println("This user has already been blocked by you.");
        }
    } //done

    public static void unblockUser (User blocked, User blocker) {
        if (blocker.blocked.contains(blocked)) {
            blocker.blocked.remove(blocked);
            System.out.println( blocked.userName + " has been unblocked.");
        }
        else {
            System.out.println("You have not already blocked this user.");
        }
    } //done



    public static void purchase (Advertisement A, int quantity, User buyer) {
        int a;
        a = quantity * A.productPrice * (100 - A.discountPercentage) / 100;
        if (buyer.credit < a) {
            System.out.println("You don't have enough credit to complete this transaction. Would you like to increase your credit?");
            Scanner scan = new Scanner(System.in);
            if (scan.nextLine().equals("Yes") || scan.nextLine().equals("yes")) {
                System.out.println("How much credit do you need?");
                int x = scan.nextInt();
                FunctionGenerator.increaseCredit(x, buyer);
                FunctionGenerator.purchase(A, quantity, buyer);
            }
        }
        else {
            buyer.credit = buyer.credit - a;
            A.sender.credit = A.sender.credit + a;
            System.out.println("Transaction is completed. Your product will be delivered to your address.");
        }
    }

    public static void increaseCredit (int a, User A) {
        if (a > 0) {
            A.credit = A.credit + a;
            System.out.println("Your have successfully charged your wallet.");
        }
        else {
            System.out.println("You can not negatively charge your wallet.");
        }
    }



    public static void statistics (Message a) {
        if (! (a instanceof Advertisement)) {
            System.out.println("Until now, " + a.liked.size() + " user(s) liked and " + a.disliked.size() + " user(s) disliked this message.");
            System.out.println("Likes: ");
            for (int i = 0; i < a.liked.size(); i++) {
                if (i != a.liked.size() - 1) {
                    System.out.println(a.liked.get(i).userName + " , ");
                } else {
                    System.out.println(a.liked.get(i).userName);
                }
            }
            System.out.println("\n");
            System.out.println("Dislikes: ");
            for (int i = 0; i < a.disliked.size(); i++) {
                if (i != a.disliked.size() - 1) {
                    System.out.println(a.disliked.get(i).userName + " , ");
                } else {
                    System.out.println(a.disliked.get(i).userName);
                }
            }
        }
        else {
            System.out.println("Until now, " + a.liked.size() + " user(s) liked and " + a.disliked.size() + " user(s) disliked this message.");
            System.out.println("Likes: ");
            for (int i = 0; i < a.liked.size(); i++) {
                if (i != a.liked.size() - 1) {
                    System.out.println(a.liked.get(i).userName + " , ");
                }
                else {
                    System.out.println(a.liked.get(i).userName);
                }
            }
            System.out.println("\n");
            System.out.println("Dislikes: ");
            for (int i = 0; i < a.disliked.size(); i++) {
                if (i != a.disliked.size() - 1) {
                    System.out.println(a.disliked.get(i).userName + " , ");
                }
                else {
                    System.out.println(a.disliked.get(i).userName);
                }
            }
            System.out.println("Until now, " + ((Advertisement) a).seen.size() + " user(s) have seen your advertisement.");
            System.out.println("Seen: ");
            for (int i = 0; i < ((Advertisement) a).seen.size(); i++) {
                if (i != ((Advertisement) a).seen.size() - 1) {
                    System.out.print(((Advertisement) a).seen.get(i).userName + " , ");
                }
                else {
                    System.out.print(((Advertisement) a).seen.get(i).userName);
                }
            }
            System.out.print("\n");
        }
    }



    public static void searchMessage (ArrayList<String> searching) {
        int v = 0;
        boolean search = false;
        for (int i = 0; i < Database.users.size(); i++) {
            if (Controller.currentUser.equals(Database.users.get(i).userName)) {
                v = i;
            }
        }

        for (int i = 0; i < Database.users.get(v).directs.size(); i++) {
            for (int j = 0; j < Database.users.get(v).directs.get(i).messages.size(); j++) {
                boolean[] exists = new boolean[searching.size()];
                for (int k = 0; k < searching.size(); k++) {
                    exists[k] = false;
                }
                for (int k = 0; k < searching.size(); k++) {
                    if (Database.users.get(v).directs.get(i).messages.get(j).textMessage.contains(searching.get(k))) {
                        exists[k] = true;
                    }
                }
                boolean allTrue = true;
                for (int k = 0; k < searching.size(); k++) {
                    if (!exists[k]) {
                        allTrue = false;
                        break;
                    }
                }
                if (!Database.users.get(v).directs.get(i).messages.get(j).isDeleted && allTrue) {
                    System.out.println("In the direct: ");
                    FunctionGenerator.displayMessage(Database.users.get(v).directs.get(i).messages.get(j));
                    search = true;
                }
            }
        }
        for (int i = 0; i < Database.users.get(v).groups.size(); i++) {
            for (int j = 0; j < Database.users.get(v).groups.get(i).messages.size(); j++) {
                boolean[] exists = new boolean[searching.size()];
                for (int k = 0; k < searching.size(); k++) {
                    exists[k] = false;
                }
                for (int k = 0; k < searching.size(); k++) {
                    if (Database.users.get(v).groups.get(i).messages.get(j).textMessage.contains(searching.get(k))) {
                        exists[k] = true;
                    }
                }
                boolean allTrue = true;
                for (int k = 0; k < searching.size(); k++) {
                    if (!exists[k]) {
                        allTrue = false;
                        break;
                    }
                }
                if (!Database.users.get(v).groups.get(i).messages.get(j).isDeleted && allTrue) {
                    System.out.println("In the group " + Database.users.get(v).groups.get(i).groupName);
                    FunctionGenerator.displayMessage(Database.users.get(v).groups.get(i).messages.get(j));
                    search = true;
                }
            }
        }
        if (!search) {
            System.out.println("\n No messages could be found in your directs and groups.");
        }
    } //done



    public static void displayMessage (Message a) {/*
        if (!a.isDeleted) {
            System.out.println(a.sender.userName + "\n");
            for (int i = 0; i < a.textMessage.size(); i++) {
                System.out.print(a.textMessage.get(i) + " ");
            }
            System.out.println("\n \n");
        }
        else {
            System.out.println("This message was deleted.");
        }
    */} //done

    public static void sendPost (User sender, String text) {
        Post newPost = new Post(sender, text);
        System.out.println("Your post has been uploaded successfully.");
    }

    public static void displayPost (Post a) {/*
        if (!a.isDeleted) {
            System.out.println(a.sender.userName + "\n");
            for (int i = 0; i < a.textMessage.size(); i++) {
                System.out.print(a.textMessage.get(i) + " ");
                System.out.print("\n");
            }
        }
        else {
            System.out.println("This message was deleted.");
        }
    */} //done

    public static void displayRepliesPost (Post a) {/*
        if (!a.isDeleted) {
            for (int i = a.replies.size() - 1; i >= 0; i--) {
                if (!a.replies.get(i).isDeleted) {
                    if (!Controller.currentUser.equals(a.replies.get(i).sender.userName)) {
                        System.out.println(a.replies.get(i).sender.userName + " said: \n");
                    } else {
                        System.out.println("You said: \n");
                    }
                    for (int j = 0; j < a.replies.get(i).textMessage.size(); j++) {
                        System.out.print(a.replies.get(i).textMessage.get(j) + " ");
                    }
                    System.out.println("""


                            """);
                }
            }
        }
        else {
            System.out.println("This message was deleted.");
        }
    */} //done

    public static void replyPost (User sender, ArrayList<String> text, Post replied) {/*
        if (!replied.isDeleted) {
            ArrayList<String> repliedText = new ArrayList<>();
            repliedText.add("Replied");
            repliedText.add("to:");
            if (replied.textMessage.size() >= 4) {
                repliedText.add(replied.textMessage.get(0));
                repliedText.add(replied.textMessage.get(1));
                repliedText.add(replied.textMessage.get(2));
                repliedText.add(replied.textMessage.get(3));
            }
            else {
                for (int i = 0; i < replied.textMessage.size(); i++) {
                    repliedText.add(replied.textMessage.get(i));
                }
            }
            repliedText.addAll(text);
            CommentPost newCommentPost = new CommentPost(sender, repliedText, replied);
            System.out.println("Your reply has been uploaded successfully.");
        }
    */} //done



    public static void sendAdvertisement (User sender, String text, String name, int price,
                                          boolean isDiscount, int percent) {
        if (sender.userType) {
            Advertisement newAdvertisement = new Advertisement(sender, text, name, price, isDiscount, percent);
            System.out.println("Your advertisement has been uploaded successfully.");
        }
        else {
            System.out.println("You can not advertise with your account.");
        }
    }

    public static void displayAdvertisement (Advertisement a) {/*
        if (!a.isDeleted) {
            int v = 0;
            for (int i = 0; i < Database.users.size(); i++) {
                if (Database.users.get(i).userName.equals(Controller.currentUser)) {
                    v = i;
                    break;
                }
            }
            if (!a.seen.contains(Database.users.get(v))) {
                a.seen.add(Database.users.get(v));
            }

            System.out.println(a.sender.userName + "\n");
            if (a.discount) {
                int x;
                x = a.productPrice * (1 - a.discountPercentage / 100);
                System.out.println(a.productName + " :    " + x + " $  (" + a.discountPercentage + " % Off!)");
            } else {
                System.out.println(a.productName + " :    " + a.productPrice + " $");
            }
            for (int i = 0; i < a.textMessage.size(); i++) {
                System.out.print(a.textMessage.get(i) + " ");
            }
            System.out.print("\n");
        }
        else {
            System.out.println("This message was deleted.");
        }
    */}



    public static void startConversation (User second) {
        int v = 0;
        for (int i = 0; i < Database.users.size(); i++) {
            if (Controller.currentUser.equals(Database.users.get(i).userName)) {
                v = i;
            }
        }
        boolean directExits = false, isBlocked = false;
        for (int i = 0; i < Database.directs.size(); i++) {
            if (Database.users.get(v).equals(Database.directs.get(i).first) && second.equals(Database.directs.get(i).second) ||
                    Database.users.get(v).equals(Database.directs.get(i).second) && second.equals(Database.directs.get(i).first)) {
                directExits = true;
                break;
            }
        }
        if (second.blocked.contains(Database.users.get(v)) || Database.users.get(v).blocked.contains(second)) {
            isBlocked = true;
        }
        if (!directExits && !isBlocked) {
            Direct newDirect = new Direct(second);
            System.out.println("You have successfully started a conversation with this user.");
        }
        else {
            if (directExits) {
                System.out.println("You have already started a conversation with this user.");
            }
            if (isBlocked) {
                System.out.println("You can not send a direct message to this user, because you have blocked each other.");
            }
        }
    } //done

    public static void sendDirectMessage (String text, User second) {
        int v = 0, u = 0;
        for (int i = 0; i < Database.users.size(); i++) {
            if (Controller.currentUser.equals(Database.users.get(i).userName)) {
                v = i;
            }
        }
        boolean directExits = false;
        for (int i = 0; i < Database.directs.size(); i++) {
            if (Database.users.get(v).equals(Database.directs.get(i).first) && second.equals(Database.directs.get(i).second) ||
                    Database.users.get(v).equals(Database.directs.get(i).second) && second.equals(Database.directs.get(i).first)) {
                directExits = true;
                u = i;
            }
        }
        if (directExits) {
            DirectMessage newDirectMessage = new DirectMessage(Database.users.get(v),text, Database.directs.get(u));
            System.out.println("Your direct message has been uploaded successfully.");
        }
        else {
            System.out.println("You have not already started a conversation with this user.");
        }
    } //done


    public static void suggestUser (User a) {
        ArrayList<User> followedByFollowers = new ArrayList<>();
        for (int i = 0; i < a.followings.size(); i++) {
            for (int j = 0; j < a.followings.get(i).followings.size(); j++) {
                boolean talked = false;
                for (int i1 = 0; i1 < a.directs.size(); i1++) {
                    if (a.directs.get(i).first.equals(a.followings.get(i).followings.get(j)) ||
                            a.directs.get(i).second.equals(a.followings.get(i).followings.get(j))) {
                        talked = true;
                        break;
                    }
                }
                if (!a.blocked.contains(a.followings.get(i).followings.get(j)) && !talked &&
                        !a.equals(a.followings.get(i).followings.get(j))) {
                    followedByFollowers.add(a.followings.get(i).followings.get(j));
                }
            }
        }
        for (int i = 0; i < a.followings.size(); i++) {
            for (int j = 0; j < a.followings.get(i).followers.size(); j++) {
                boolean talked = false;
                for (int i1 = 0; i1 < a.directs.size(); i1++) {
                    if (a.directs.get(i).first.equals(a.followings.get(i).followers.get(j)) ||
                            a.directs.get(i).second.equals(a.followings.get(i).followers.get(j))) {
                        talked = true;
                        break;
                    }
                }
                if (!a.blocked.contains(a.followings.get(i).followers.get(j)) && !talked &&
                        !a.equals(a.followings.get(i).followers.get(j))) {
                    followedByFollowers.add(a.followings.get(i).followers.get(j));
                }
            }
        }
        if (followedByFollowers.size() != 0) {
            Random random = new Random();
            int n = random.nextInt(followedByFollowers.size() - 1);
            System.out.println("Your suggested user is:" + followedByFollowers.get(n).userName);
            System.out.println("Would you like to start a conversation with " + followedByFollowers.get(n).userName + " ?");
            Scanner scan = new Scanner(System.in);
            if (scan.nextLine().equals("Yes") || scan.nextLine().equals("yes")) {
                FunctionGenerator.startConversation(followedByFollowers.get(n));
                Controller.recommend();
            }
            else {
                Controller.recommend();
            }
        }
        else {
            System.out.println("No suggested user was found for you.");
        }
    }

    public static void suggestProduct (User a) {
        ArrayList<Advertisement> suggestions = new ArrayList<>();
        ArrayList<User> sellers = new ArrayList<>();
        for (int i = 0; i < Database.advertisements.size(); i++) {
            if (Database.advertisements.get(i).liked.contains(a)) {
                sellers.add(Database.advertisements.get(i).sender);
            }
        }
        for (int i = 0; i < Database.advertisements.size(); i++) {
            if (sellers.contains(Database.advertisements.get(i).sender) && !Database.advertisements.get(i).liked.contains(a) &&
                    !Database.advertisements.get(i).disliked.contains(a)) {
                suggestions.add(Database.advertisements.get(i));
            }
        }
        if (suggestions.size() != 0) {
            Random random = new Random();
            int n = random.nextInt(suggestions.size() - 1);
            FunctionGenerator.displayAdvertisement(suggestions.get(n));
        }
        else {
            Random random = new Random();
            int n = random.nextInt(Database.advertisements.size() - 1);
            FunctionGenerator.displayAdvertisement(suggestions.get(n));
            System.out.println("Would you like to purchase this item?");
            Scanner scan = new Scanner(System.in);
            if (scan.nextLine().equals("Yes") || scan.nextLine().equals("yes")) {
                System.out.println("Please enter your quantity.");
                int x = scan.nextInt();
                int v = 0;
                for (int i = 0; i < Database.users.size(); i++) {
                    if (Controller.currentUser.equals(Database.users.get(i).userName)) {
                        v = i;
                        break;
                    }
                }
                FunctionGenerator.purchase(suggestions.get(n), x, Database.users.get(v));
            }
        }
    }



    public static void displayDirectMessage (DirectMessage a) {/*
        if (!a.isDeleted) {
            if (a.sender.userName.equals(Controller.currentUser)) {
                System.out.println("You said: \n");
                for (int j = 0; j < a.textMessage.size(); j++) {
                    System.out.print(a.textMessage.get(j) + " ");
                }
                System.out.println("\n");
            } else {
                System.out.println(a.sender.userName + " said: \n");
                for (int j = 0; j < a.textMessage.size(); j++) {
                    System.out.print(a.textMessage.get(j) + " ");
                    System.out.print("\n");
                }
                System.out.println("\n");
            }
        }
        else {
            System.out.println("This message was deleted.");
        }
    */} //done

    public static void replyDirectMessage (User sender, ArrayList<String> text, DirectMessage replied) {/*
        if (!replied.isDeleted) {
            int v = 0;
            for (int i = 0; i < Database.directs.size(); i++) {
                if (Database.directs.get(i).messages.contains(replied)) {
                    v = i;
                    break;
                }
            }
            ArrayList<String> repliedText = new ArrayList<>();
            repliedText.add("Replied");
            repliedText.add("to:");
            if (!(replied instanceof CommentDirect) && !(replied instanceof ForwardedDirect)) {
                if (replied.textMessage.size() > 3) {
                    repliedText.add(replied.textMessage.get(0));
                    repliedText.add(replied.textMessage.get(1));
                    repliedText.add(replied.textMessage.get(2));
                    repliedText.add(replied.textMessage.get(3));
                }
                else {
                    repliedText.addAll(replied.textMessage);
                }
            }
            if (replied instanceof CommentDirect) {
                if (replied.textMessage.size() > 9) {
                    repliedText.add(replied.textMessage.get(6));
                    repliedText.add(replied.textMessage.get(7));
                    repliedText.add(replied.textMessage.get(8));
                    repliedText.add(replied.textMessage.get(9));
                }
                else {
                    for (int i = 6; i < replied.textMessage.size(); i++) {
                        repliedText.add(replied.textMessage.get(i));
                    }
                }
            }
            if (replied instanceof ForwardedDirect) {
                if (replied.textMessage.size() > 6) {
                    repliedText.add(replied.textMessage.get(3));
                    repliedText.add(replied.textMessage.get(4));
                    repliedText.add(replied.textMessage.get(5));
                    repliedText.add(replied.textMessage.get(6));
                }
                else {
                    for (int i = 3; i < replied.textMessage.size(); i++) {
                        repliedText.add(replied.textMessage.get(i));
                    }
                }
            }
            repliedText.addAll(text);
            CommentDirect newCommentDirect = new CommentDirect(sender, repliedText, Database.directs.get(v) ,replied);
            System.out.println("Your reply has been uploaded successfully.");
        }
    */} //done

    public static void forwardToDirect (User sender, Direct receiver, Message forwarded) {/*
        if (!forwarded.isDeleted) {
            ArrayList<String> repliedText = new ArrayList<>();
            repliedText.add("Forwarded");
            repliedText.add("from");
            repliedText.add(forwarded.sender.userName);

            if (forwarded instanceof ForwardedDirect || forwarded instanceof ForwardedGroup) {
                for (int i = 3; i < forwarded.textMessage.size(); i++) {
                    repliedText.add(forwarded.textMessage.get(i));
                }
            }

            if (forwarded instanceof CommentGroup || forwarded instanceof CommentDirect) {
                for (int i = 6; i < forwarded.textMessage.size(); i++) {
                    repliedText.add(forwarded.textMessage.get(i));
                }
            }
            ForwardedDirect newForwardedDirect = new ForwardedDirect(sender, repliedText, receiver, forwarded);
        }
    */} //done



    public static void createGroup (String name, User creator) {
        boolean groupExists = false;
        for (int i = 0; i < Database.groups.size(); i++) {
            if (Database.groups.get(i).groupName.equals(name)) {
                groupExists = true;
                break;
            }
        }
        if (!groupExists) {
            Group newGroup = new Group(name, creator);
            System.out.println("Group was created successfully.");
        }
        else {
            System.out.println("This group already exists, Please choose another name for your group.");
            Scanner scan = new Scanner(System.in);
            String nam = scan.nextLine();
            Group newGroup = new Group(nam, creator);
        }
    } //done

    public static void sendGroupMessage (String text, String receiver) {
        boolean groupExists = false;  int v = 0, u = 0;
        for (int i = 0; i < Database.users.size(); i++) {
            if (Controller.currentUser.equals(Database.users.get(i).userName)) {
                v = i;
                break;
            }
        }
        for (int j = 0; j < Database.groups.size(); j++) {
            if (Database.groups.get(j).groupName.equals(receiver)) {
                groupExists = true;
                u = j;
                break;
            }
        }
        if (groupExists) {
            GroupMessage newGroupMessage = new GroupMessage(Database.users.get(v), text, Database.groups.get(u));
            System.out.println("Your group message has been uploaded successfully.");
        }
        else {
            System.out.println("This group does not exist.");
        }
    } //done

    public static void displayGroupMessage (GroupMessage a) {/*
        if (!a.isDeleted) {
            if (a.sender.userName.equals(Controller.currentUser)) {
                System.out.println("You said: \n");
                for (int j = 0; j < a.textMessage.size(); j++) {
                    System.out.print(a.textMessage.get(j) + " ");
                }
                System.out.println("\n");
            } else {
                System.out.println(a.sender.userName + " said: \n");
                for (int j = 0; j < a.textMessage.size(); j++) {
                    System.out.print(a.textMessage.get(j) + " ");
                }
                System.out.println("\n");
            }
        }
        else {
            System.out.println("This message was deleted.");
        }
    */} //done

    public static void replyGroupMessage (User sender, ArrayList<String> text, GroupMessage replied) {/*
        if (!replied.isDeleted) {
            int v = 0;
            for (int i = 0; i < Database.groups.size(); i++) {
                if (Database.groups.get(i).messages.contains(replied)) {
                    v = i;
                    break;
                }
            }
            ArrayList<String> repliedText = new ArrayList<>();
            repliedText.add("Replied");
            repliedText.add("to:");
            if (!(replied instanceof CommentGroup) && !(replied instanceof ForwardedGroup)) {
                if (replied.textMessage.size() > 3) {
                    repliedText.add(replied.textMessage.get(0));
                    repliedText.add(replied.textMessage.get(1));
                    repliedText.add(replied.textMessage.get(2));
                    repliedText.add(replied.textMessage.get(3));
                }
                else {
                    repliedText.addAll(replied.textMessage);
                }
            }
            if (replied instanceof CommentGroup) {
                if (replied.textMessage.size() > 9) {
                    repliedText.add(replied.textMessage.get(6));
                    repliedText.add(replied.textMessage.get(7));
                    repliedText.add(replied.textMessage.get(8));
                    repliedText.add(replied.textMessage.get(9));
                }
                else {
                    for (int i = 6; i < replied.textMessage.size(); i++) {
                        repliedText.add(replied.textMessage.get(i));
                    }
                }
            }
            if (replied instanceof ForwardedGroup) {
                if (replied.textMessage.size() > 6) {
                    repliedText.add(replied.textMessage.get(3));
                    repliedText.add(replied.textMessage.get(4));
                    repliedText.add(replied.textMessage.get(5));
                    repliedText.add(replied.textMessage.get(6));
                }
                else {
                    for (int i = 3; i < replied.textMessage.size(); i++) {
                        repliedText.add(replied.textMessage.get(i));
                    }
                }
            }
            repliedText.addAll(text);
            CommentGroup newCommentGroup = new CommentGroup(sender, repliedText, Database.groups.get(v) ,replied);
            System.out.println("Your reply has been uploaded successfully.");
        }
    */} //done

    public static void forwardToGroup (User sender, Group receiver, Message forwarded) {/*
        if (!forwarded.isDeleted) {
            ArrayList<String> repliedText = new ArrayList<>();
            repliedText.add("Forwarded");
            repliedText.add("from");
            repliedText.add(sender.userName);

            if (forwarded instanceof ForwardedDirect || forwarded instanceof ForwardedGroup) {
                for (int i = 3; i < forwarded.textMessage.size(); i++) {
                    repliedText.add(forwarded.textMessage.get(i));
                }
            }

            if (forwarded instanceof CommentGroup || forwarded instanceof CommentDirect) {
                for (int i = 6; i < forwarded.textMessage.size(); i++) {
                    repliedText.add(forwarded.textMessage.get(i));
                }
            }


            ForwardedGroup newForwardedGroup = new ForwardedGroup (sender, repliedText, receiver, forwarded);
        }
    */} //done
}


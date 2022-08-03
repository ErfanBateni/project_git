package com.example.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Controller {
    static String currentUser;

    public static void registration() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Here, you can either sign up or login to your account. Please choose one of these keywords below to proceed: ");
        System.out.println("1) Sign up");
        System.out.println("2) Login");
        System.out.println("3) Forget Password \n");
        String a = scan.nextLine();
        if (a.equalsIgnoreCase("Sign up")) {
            System.out.println("Please enter your username.");
            String name = scan.nextLine();
            System.out.println("Please enter your password.");
            String password = scan.nextLine();
            System.out.println("Are you currently creating a business account?");
            String response = scan.nextLine();
            boolean business;
            if (response.equals("Yes") || response.equals("yes")) {
                business = true;
            }
            else {
                business = false;
            }
            System.out.println("This is a security question. You can recover your account in case you forget your password.");
            System.out.println("What was the name of your first pet?");
            String pet = scan.nextLine();
            FunctionGenerator.signUp(name, password, business, pet);
            Controller.registration();
        }
        if (a.equalsIgnoreCase("Login")) {
            System.out.println("Please enter your username.");
            String name = scan.nextLine();
            System.out.println("Please enter your password.");
            String password = scan.nextLine();
            FunctionGenerator.login(name, password);
            Controller.mainMenu();
        }
        if (a.equalsIgnoreCase("Forget Password") || a.equalsIgnoreCase("Forget")) {
            System.out.println("Please enter your username.");
            String name = scan.nextLine();
            System.out.println("What was the name of your first pet?");
            String password = scan.nextLine();
            FunctionGenerator.forgetPassword(name, password);
        }
    }

    public static void mainMenu () {
        Scanner scan = new Scanner(System.in);
        Database.insertData();
        System.out.print("\n");
        System.out.println("Welcome to the main menu!");
        System.out.println("Where would you like to go?");
        System.out.println("1) Timeline");
        System.out.println("2) Directs");
        System.out.println("3) Groups");
        System.out.println("4) Search Messages");
        System.out.println("5) User And Product Recommendations");
        System.out.println("6) Store");
        System.out.println("7) Logout");
        String a;
        a = scan.nextLine();
        if (a.equals("Timeline") || a.equals("TimeLine")) {
            Controller.timeline();
        }
        if (a.equals("Directs") || a.equals("directs")) {
            Controller.directs();
        }
        if (a.equals("Groups") || a.equals("groups")) {
            Controller.groups();
        }
        if (a.equals("Search") || a.equals("search") || a.equals("Search Messages") || a.equals("search messages")) {
            Controller.searchMessage();
        }

        if (a.equals("User") || a.equals("user") || a.equals("User And Product Recommendations")
                || a.equals("user and product recommendations")) {
            Controller.recommend();
        }
        if (a.equals("Store") || a.equals("store")) {
            Controller.store();
        }
        if (a.equals("Logout") || a.equals("logout")) {
            Controller.registration();
        }
    }

    public static void timeline () {
        int v = 0;
        for (int i = 0; i < Database.users.size(); i++) {
            if (Database.users.get(i).userName.equals(Controller.currentUser)) {
                v = i;
                break;
            }
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Where do you like to go?");
        System.out.println("1) My Timeline");
        System.out.println("2) New Post");
        System.out.println("3) Follow User");
        System.out.println("4) Unfollow User");
        System.out.println("5) Main Menu");
        String x = scanner.nextLine();
        if (x.equals("My Timeline") || x.equals("my timeline")) {
            ArrayList<Post> timeline = new ArrayList<>();
            for (int i = 0; i < Database.posts.size(); i++) {
                if (Database.users.get(v).followings.contains(Database.posts.get(i).sender)) {
                    timeline.add(Database.posts.get(i));
                }
            }
            if (timeline.size() != 0) {
                Controller.postInteraction(timeline, timeline.size() - 1);
            }
            else {
                System.out.println("You have no posts in your timeline.");
                Controller.timeline();
            }
        }

        if (x.equals("New Post") || x.equals("new post")) {
            System.out.println("Please write what you want to post.");
            String text = scanner.nextLine();
            //String[] f = s.split(" ");
            //ArrayList<String> text = new ArrayList<>();
            //text.addAll(Arrays.asList(f));
            FunctionGenerator.sendPost(Database.users.get(v), text);
            Controller.timeline();
        }

        if (x.equals("Follow User") || x.equals("follow user")) {
            System.out.println("Please write the user's name you want to follow.");
            String name = scanner.nextLine();
            int n = 0;
            for (int i = 0; i < Database.users.size(); i++) {
                if (Database.users.get(i).userName.equals(name)) {
                    n = i;
                    break;
                }
            }
            FunctionGenerator.follow(Database.users.get(v), Database.users.get(n));
            Controller.timeline();
        }

        if (x.equals("Unfollow User") || x.equals("unfollow user")) {
            System.out.println("Please write the user's name you want to unfollow.");
            String name = scanner.nextLine();
            int n = 0;
            for (int i = 0; i < Database.users.size(); i++) {
                if (Database.users.get(i).userName.equals(name)) {
                    n = i;
                    break;
                }
            }
            FunctionGenerator.unfollow(Database.users.get(v), Database.users.get(n));
            Controller.timeline();
        }

        if (x.equals("Main Menu") || x.equals("main menu")) {
            Controller.mainMenu();
        }
    }

    public static void postInteraction (ArrayList<Post> timeline, int index) {
        int v = 0;
        for (int i = 0; i < Database.users.size(); i++) {
            if (Controller.currentUser.equals(Database.users.get(i).userName)) {
                v = i;
                break;
            }
        }
        FunctionGenerator.displayPost(timeline.get(index));
        Scanner scanner = new Scanner(System.in);
        String x = scanner.nextLine();
        if (x.equals("Next") || x.equals("next")) {
            if (index != timeline.size() - 1) {
                postInteraction(timeline, index + 1);
            }
            else {
                postInteraction(timeline, 0);
            }
        }
        if (x.equals("Previous") || x.equals("previous")) {
            if (index != 0) {
                postInteraction(timeline, index - 1);
            }
            else {
                postInteraction(timeline, timeline.size() - 1);
            }
        }
        if (x.equals("Main Menu") || x.equals("main menu")) {
            Controller.mainMenu();
        }
        if (x.equals("Like") || x.equals("like")) {
            FunctionGenerator.like(Database.users.get(v), timeline.get(index));
            postInteraction(timeline, index);
        }
        if (x.equals("Dislike") || x.equals("dislike")) {
            FunctionGenerator.dislike(Database.users.get(v), timeline.get(index));
            postInteraction(timeline, index);
        }
        if (x.equals("Stats") || x.equals("stats")) {
            FunctionGenerator.statistics(timeline.get(index));
            postInteraction(timeline, index);
        }
        if (x.equals("Comment") || x.equals("comment")) {
            System.out.println("Please write what you want to comment.");
            String s = scanner.nextLine();
            String[] texxt = s.split(" ");
            ArrayList<String> text = new ArrayList<>();
            text.addAll(Arrays.asList(texxt));
            FunctionGenerator.replyPost(Database.users.get(v), text, timeline.get(index));
            Controller.postInteraction(timeline, index);
        }
        if (x.equals("Show Comments") || x.equals("show comments")) {
            if (timeline.get(index).replies.size() != 0) {
                for (int i = 0; i < timeline.get(index).replies.size(); i++) {
                    FunctionGenerator.displayRepliesPost(timeline.get(index));
                }
                postInteraction(timeline, index);
            }
            else {
                System.out.println("This post does not have any replies yet.");
                postInteraction(timeline, index);
            }
        }
        if (x.equals("Forward") || x.equals("forward")) {
            System.out.println("Please choose that you want to send this to either a direct or a group.");
            String response = scanner.nextLine();
            if (response.equals("Direct") || response.equals("direct") || response.equals("Directs") || response.equals("directs")) {
                if (Database.users.get(v).directs.size() != 0) {
                    System.out.println("Please choose the user you want to interact.");
                    ArrayList<User> contacts = new ArrayList<>();
                    for (int i = 0; i < Database.users.get(v).directs.size(); i++) {
                        if (Database.users.get(v).directs.get(i).first.equals(Database.users.get(v))) {
                            System.out.println(Database.users.get(v).directs.get(i).second.userName);
                            contacts.add(Database.users.get(v).directs.get(i).second);
                        }
                        else {
                            System.out.println(Database.users.get(v).directs.get(i).first.userName);
                            contacts.add(Database.users.get(v).directs.get(i).first);
                        }
                    }
                    String name = scanner.nextLine();
                    int n = 0;
                    for (int i = 0; i < Database.users.size(); i++) {
                        if (name.equals(Database.users.get(i).userName)) {
                            n = i;
                            break;
                        }
                    }
                    if (contacts.contains(Database.users.get(n))) {
                        int m = 0;
                        for (int i = 0; i < Database.directs.size(); i++) {
                            if ((Database.directs.get(i).first.equals(Database.users.get(v)) && Database.directs.get(i).second.equals(Database.users.get(n))) ||
                                    (Database.directs.get(i).second.equals(Database.users.get(v)) && Database.directs.get(i).first.equals(Database.users.get(n)))) {
                                m = i;
                                break;
                            }
                        }
                        FunctionGenerator.forwardToDirect(Database.users.get(v), Database.directs.get(m), timeline.get(index));
                        postInteraction(timeline, index);
                    }
                    else {
                        System.out.println("This user does not exist among your contacts.");
                        postInteraction(timeline, index);
                    }
                }
                else {
                    System.out.println("You have not started any conversation yet.");
                    postInteraction(timeline, index);
                }
            }
            if (response.equals("Group") || response.equals("group") || response.equals("Groups") || response.equals("groups")) {
                if (Database.users.get(v).groups.size() != 0) {
                    System.out.println("Please choose the group you want to interact.");
                    for (int i = 0; i < Database.users.get(v).groups.size(); i++) {
                        System.out.println(Database.users.get(v).groups.get(i).groupName);
                    }
                    String name = scanner.nextLine();
                    int n = 0;
                    for (int i = 0; i < Database.groups.size(); i++) {
                        if (name.equals(Database.groups.get(i))) {
                            n = i;
                            break;
                        }
                    }
                    if (Database.users.get(v).groups.contains(Database.groups.get(n))) {
                        FunctionGenerator.forwardToGroup(Database.users.get(v), Database.groups.get(n), timeline.get(index));
                        postInteraction(timeline, index);
                    }
                    else {
                        System.out.println("This group does not exist among your contacts.");
                        postInteraction(timeline, index);
                    }
                }
                else {
                    System.out.println("You have not become a member of a group yet.");
                    postInteraction(timeline, index);
                }
            }
        }
    }



    public static void directs () {
        int v = 0;
        for (int i = 0; i < Database.users.size(); i++) {
            if (Controller.currentUser.equals(Database.users.get(i).userName)) {
                v = i;
                break;
            }
        }
        System.out.println("Where do you like to go?");
        System.out.println("1) My Directs");
        System.out.println("2) Start Conversation");
        System.out.println("3) Block User");
        System.out.println("4) Unblock User");
        System.out.println("5) Main Menu");
        Scanner scanner = new Scanner(System.in);
        String x = scanner.nextLine();
        if (x.equals("My Directs") || x.equals("my directs")) {
            if (Database.users.get(v).directs.size() != 0) {
                for (int i = 0; i < Database.users.get(v).directs.size(); i++) {
                    if (Database.users.get(v).directs.get(i).first.userName.equals(Controller.currentUser)) {
                        System.out.println(Database.users.get(v).directs.get(i).second.userName);
                    } else {
                        System.out.println(Database.users.get(v).directs.get(i).first.userName);
                    }
                }
                System.out.println("Please choose one of these users.");
                String name = scanner.nextLine();
                int n = 0;
                for (int i = 0; i < Database.users.size(); i++) {
                    if (Database.users.get(i).userName.equals(name)) {
                        n = i;
                        break;
                    }
                }
                int s = 0;
                for (int i = 0; i < Database.directs.size(); i++) {
                    if ((Database.directs.get(i).first.equals(Database.users.get(n)) && Database.directs.get(i).second.equals(Database.users.get(v))) ||
                            (Database.directs.get(i).second.equals(Database.users.get(n)) && Database.directs.get(i).first.equals(Database.users.get(v)))) {
                        s = i;
                        break;
                    }
                }
                if (Database.directs.get(s).messages.size() != 0) {
                    Controller.directInteraction(Database.directs.get(s), Database.directs.get(s).messages.size() - 1);
                } else {
                    System.out.println("Please send your first message to this user.");
                    String text = scanner.nextLine();
                    //String[] texxt = f.split(" ");
                    //ArrayList<String> text = new ArrayList<>(Arrays.asList(texxt));
                    FunctionGenerator.sendDirectMessage(text, Database.users.get(n));
                    Controller.directs();
                }
            }
            else {
                System.out.println("You have no-one to talk to. Please first start a conversation.");
                Controller.directs();
            }
        }
        if (x.equals("Start Conversation") || x.equals("start conversation")) {
            System.out.println("Please enter the name of the user.");
            String name = scanner.nextLine();
            if (!name.equals(Controller.currentUser)) {
                int n = 0;
                for (int i = 0; i < Database.users.size(); i++) {
                    if (Database.users.get(i).userName.equals(name)) {
                        n = i;
                        break;
                    }
                }
                FunctionGenerator.startConversation(Database.users.get(n));
                Controller.directs();
            }
            else {
                System.out.println("You can not start a conversation with yourself.");
                Controller.directs();
            }
        }

        if (x.equals("Block User") || x.equals("block user")) {
            System.out.println("Please enter the name you intend to block.");
            String name = scanner.nextLine();
            int n = 0;
            boolean c = false;
            if (!name.equals(Controller.currentUser)) {
                for (int i = 0; i < Database.users.size(); i++) {
                    if (Database.users.get(i).userName.equals(name)) {
                        n = i;
                        c = true;
                        break;
                    }
                }
                if (c) {
                    FunctionGenerator.blockUser(Database.users.get(n), Database.users.get(v));
                    Controller.directs();
                }
                else {
                    System.out.println("No user was found with this username.");
                    Controller.directs();
                }
            }
            else {
                System.out.println("You can not block or unblock yourself.");
                Controller.directs();
            }
        }
        if (x.equals("Unblock User") || x.equals("unblock user")) {
            System.out.println("Please enter the name you intend to unblock.");
            String name = scanner.nextLine();
            int n = 0;
            boolean c = false;
            if (!name.equals(Controller.currentUser)) {
                for (int i = 0; i < Database.users.size(); i++) {
                    if (Database.users.get(i).userName.equals(name)) {
                        n = i;
                        c = true;
                        break;
                    }
                }
                if (c) {
                    FunctionGenerator.unblockUser(Database.users.get(n), Database.users.get(v));
                    Controller.directs();
                }
                else {
                    System.out.println("No user was found with this username.");
                    Controller.directs();
                }
            }
            else {
                System.out.println("You can not block or unblock yourself.");
                Controller.directs();
            }
        }
        if (x.equals("Main Menu") || x.equals("main menu")) {
            Controller.mainMenu();
        }
    }

    public static void directInteraction (Direct a, int index) {
        int v = 0;
        for (int i = 0; i < Database.users.size(); i++) {
            if (Controller.currentUser.equals(Database.users.get(i).userName)) {
                v = i;
                break;
            }
        }
        FunctionGenerator.displayDirectMessage(a.messages.get(index));
        Scanner scanner = new Scanner(System.in);
        String x = scanner.nextLine();
        if (x.equals("Next") || x.equals("next")) {
            if (index != a.messages.size() - 1) {
                directInteraction(a, index + 1);
            }
            else {
                directInteraction(a, 0);
            }
        }
        if (x.equals("Previous") || x.equals("previous")) {
            if (index != 0) {
                directInteraction(a, index - 1);
            }
            else {
                directInteraction(a, a.messages.size() - 1);
            }
        }
        if (x.equals("Send") || x.equals("send") || x.equals("Post") || x.equals("post")) {
            System.out.println("Please write your message.");
            String text = scanner.nextLine();
            //String[] texxt = f.split(" ");
            //ArrayList<String> text = new ArrayList<>(Arrays.asList(texxt));
            if (a.first.userName.equals(Controller.currentUser)) {
                FunctionGenerator.sendDirectMessage(text, a.second);
            }
            else {
                FunctionGenerator.sendDirectMessage(text, a.first);
            }
            Controller.directInteraction(a, index);
        }
        if (x.equals("Forward") || x.equals("forward")) {
            System.out.println("Please choose that you want to send this to either a direct or a group.");
            String response = scanner.nextLine();
            if (response.equals("Direct") || response.equals("direct") || response.equals("Directs") || response.equals("directs")) {
                if (Database.users.get(v).directs.size() != 0) {
                    System.out.println("Please choose the user you want to interact.");
                    ArrayList<User> contacts = new ArrayList<>();
                    for (int i = 0; i < Database.users.get(v).directs.size(); i++) {
                        if (Database.users.get(v).directs.get(i).first.equals(Database.users.get(v))) {
                            System.out.println(Database.users.get(v).directs.get(i).second.userName);
                            contacts.add(Database.users.get(v).directs.get(i).second);
                        }
                        else {
                            System.out.println(Database.users.get(v).directs.get(i).first.userName);
                            contacts.add(Database.users.get(v).directs.get(i).first);
                        }
                    }
                    String name = scanner.nextLine();
                    int n = 0;
                    for (int i = 0; i < Database.users.size(); i++) {
                        if (name.equals(Database.users.get(i).userName)) {
                            n = i;
                            break;
                        }
                    }
                    if (contacts.contains(Database.users.get(n))) {
                        int m = 0;
                        for (int i = 0; i < Database.directs.size(); i++) {
                            if ((Database.directs.get(i).first.equals(Database.users.get(v)) && Database.directs.get(i).second.equals(Database.users.get(n))) ||
                                    (Database.directs.get(i).second.equals(Database.users.get(v)) && Database.directs.get(i).first.equals(Database.users.get(n)))) {
                                m = i;
                                break;
                            }
                        }
                        FunctionGenerator.forwardToDirect(Database.users.get(v), Database.directs.get(m), a.messages.get(index));
                        directInteraction(a, index);
                    }
                    else {
                        System.out.println("This user does not exist among your contacts.");
                        directInteraction(a, index);
                    }
                }
                else {
                    System.out.println("You have not started any conversation yet.");
                    directInteraction(a, index);
                }
            }
            if (response.equals("Group") || response.equals("group") || response.equals("Groups") || response.equals("groups")) {
                if (Database.users.get(v).groups.size() != 0) {
                    System.out.println("Please choose the group you want to interact.");
                    for (int i = 0; i < Database.users.get(v).groups.size(); i++) {
                        System.out.println(Database.users.get(v).groups.get(i).groupName);
                    }
                    String name = scanner.nextLine();
                    int n = 0;
                    for (int i = 0; i < Database.groups.size(); i++) {
                        if (name.equals(Database.groups.get(i).groupName)) {
                            n = i;
                            break;
                        }
                    }
                    if (Database.users.get(v).groups.contains(Database.groups.get(n))) {
                        FunctionGenerator.forwardToGroup(Database.users.get(v), Database.groups.get(n), a.messages.get(index));
                        directInteraction(a, index);
                    }
                    else {
                        System.out.println("This group does not exist among your contacts.");
                        directInteraction(a, index);
                    }
                }
                else {
                    System.out.println("You have not become a member of a group yet.");
                    directInteraction(a, index);
                }
            }
        }
        if (x.equals("Reply") || x.equals("reply")) {
            System.out.println("Please write your reply.");
            String f = scanner.nextLine();
            String[] texxt = f.split(" ");
            ArrayList<String> text = new ArrayList<>(Arrays.asList(texxt));
            FunctionGenerator.replyDirectMessage(Database.users.get(v), text, a.messages.get(index));
            Controller.directInteraction(a, index);
        }
        if (x.equals("Edit") || x.equals("edit")) {
            System.out.println("Please edit your message.");
            String f = scanner.nextLine();
            String[] texxt = f.split(" ");
            ArrayList<String> text = new ArrayList<>(Arrays.asList(texxt));
            FunctionGenerator.editMessage(text, a.messages.get(index));
        }
        if (x.equals("Delete") || x.equals("delete")) {
            FunctionGenerator.deleteMessage(Database.users.get(v), a.messages.get(index));
            Controller.directInteraction(a, index);
        }
        if (x.equals("Main Menu") || x.equals("main menu")) {
            Controller.mainMenu();
        }
        if (x.equals("Like") || x.equals("like")) {
            FunctionGenerator.like(Database.users.get(v), a.messages.get(index));
            directInteraction(a, index);
        }
        if (x.equals("Dislike") || x.equals("dislike")) {
            FunctionGenerator.dislike(Database.users.get(v), a.messages.get(index));
            directInteraction(a, index);
        }
        if (x.equals("Stats") || x.equals("stats")) {
            FunctionGenerator.statistics(a.messages.get(index));
            directInteraction(a, index);
        }
    }



    public static void groups () {
        int v = 0;
        for (int i = 0; i < Database.users.size(); i++) {
            if (Controller.currentUser.equals(Database.users.get(i).userName)) {
                v = i;
                break;
            }
        }
        System.out.println("Where do you like to go?");
        System.out.println("1) My Groups");
        System.out.println("2) New Group");
        System.out.println("3) Join Group");
        System.out.println("4) Leave Group");
        System.out.println("5) Main Menu");
        Scanner scanner = new Scanner(System.in);
        String x = scanner.nextLine();
        if (x.equals("My Groups") || x.equals("my groups")) {
            if (Database.users.get(v).groups.size() != 0) {
                for (int i = 0; i < Database.users.get(v).groups.size(); i++) {
                    System.out.println(Database.users.get(v).groups.get(i).groupName);
                }
                System.out.println("Please select which group you want to interact with.");
                String name = scanner.nextLine();
                int n = 0;
                boolean exists = false;
                for (int i = 0; i < Database.users.get(v).groups.size(); i++) {
                    if (name.equals(Database.users.get(v).groups.get(i).groupName)) {
                        n = i;
                        exists = true;
                        break;
                    }
                }
                if (exists) {
                    if (Database.users.get(v).groups.get(n).messages.size() == 0) {
                        System.out.println("This group has no messages yet. Please send the first message.");
                        String text = scanner.nextLine();
                        //String[] texxt = s.split(" ");
                        //ArrayList<String> text = new ArrayList<>(Arrays.asList(texxt));
                        FunctionGenerator.sendGroupMessage(text, Database.users.get(v).groups.get(n).groupName);
                        Controller.groupInteraction(Database.users.get(v).groups.get(n), Database.users.get(v).groups.get(n).messages.size() - 1);
                    } else {
                        Controller.groupInteraction(Database.users.get(v).groups.get(n), Database.users.get(v).groups.get(n).messages.size() - 1);
                    }
                }
                else {
                    System.out.println("This group does not exist among your groups.");
                    Controller.groups();
                }
            }
            else {
                System.out.println("You have no-one to talk to, please first join a group.");
                Controller.groups();
            }
        }
        if (x.equals("New Group") || x.equals("new group")) {
            System.out.println("Please enter the name of your group.");
            String name = scanner.nextLine();
            FunctionGenerator.createGroup(name, Database.users.get(v));
            Controller.groups();
        }
        if (x.equals("Join Group") || x.equals("join group")) {
            System.out.println("Please enter the name of the group.");
            String name = scanner.nextLine();
            int n = 0;
            boolean exists = false;
            for (int i = 0; i < Database.groups.size(); i++) {
                if (Database.groups.get(i).groupName.equals(name)) {
                    n = i;
                    exists = true;
                    break;
                }
            }
            if (exists) {
                FunctionGenerator.joinChat(Database.users.get(v), Database.groups.get(n));
                Controller.groups();
            } else {
                System.out.println("No group exists with this name.");
                Controller.groups();
            }
        }
        if (x.equals("Leave Group") || x.equals("leave group")) {
            System.out.println("Please enter the name of the group.");
            String name = scanner.nextLine();
            int n = 0;
            boolean exists = false;
            for (int i = 0; i < Database.groups.size(); i++) {
                if (Database.groups.get(i).groupName.equals(name)) {
                    n = i;
                    exists = true;
                    break;
                }
            }
            if (exists) {
                FunctionGenerator.leaveChat(Database.users.get(v), Database.groups.get(n));
                Controller.groups();
            }
            else {
                System.out.println("No group exists with this name.");
                Controller.groups();
            }
        }
        if (x.equals("Main Menu") || x.equals("main menu")) {
            Controller.mainMenu();
        }
    }


    public static void groupInteraction (Group a, int index) {
        int v = 0;
        for (int i = 0; i < Database.users.size(); i++) {
            if (Controller.currentUser.equals(Database.users.get(i).userName)) {
                v = i;
                break;
            }
        }
        FunctionGenerator.displayGroupMessage(a.messages.get(index));
        Scanner scanner = new Scanner(System.in);
        String x = scanner.nextLine();
        if (x.equals("Next") || x.equals("next")) {
            if (index != a.messages.size() - 1) {
                groupInteraction(a, index + 1);
            } else {
                groupInteraction(a, 0);
            }
        }
        if (x.equals("Previous") || x.equals("previous")) {
            if (index != 0) {
                groupInteraction(a, index - 1);
            } else {
                groupInteraction(a, a.messages.size() - 1);
            }
        }
        if (x.equals("Send") || x.equals("send") || x.equals("Post") || x.equals("post")) {
            System.out.println("Please write your message.");
            String text = scanner.nextLine();
            //String[] texxt = f.split(" ");
            //ArrayList<String> text = new ArrayList<>(Arrays.asList(texxt));
            FunctionGenerator.sendGroupMessage(text, a.groupName);
            Controller.groupInteraction(a, index);
        }
        if (x.equals("Forward") || x.equals("forward")) {
            System.out.println("Please choose that you want to send this to either a direct or a group.");
            String response = scanner.nextLine();
            if (response.equals("Direct") || response.equals("direct") || response.equals("Directs") || response.equals("directs")) {
                if (Database.users.get(v).directs.size() != 0) {
                    System.out.println("Please choose the user you want to interact.");
                    ArrayList<User> contacts = new ArrayList<>();
                    for (int i = 0; i < Database.users.get(v).directs.size(); i++) {
                        if (Database.users.get(v).directs.get(i).first.equals(Database.users.get(v))) {
                            System.out.println(Database.users.get(v).directs.get(i).second.userName);
                            contacts.add(Database.users.get(v).directs.get(i).second);
                        } else {
                            System.out.println(Database.users.get(v).directs.get(i).first.userName);
                            contacts.add(Database.users.get(v).directs.get(i).first);
                        }
                    }
                    String name = scanner.nextLine();
                    int n = 0;
                    for (int i = 0; i < Database.users.size(); i++) {
                        if (name.equals(Database.users.get(i).userName)) {
                            n = i;
                            break;
                        }
                    }
                    if (contacts.contains(Database.users.get(n))) {
                        int m = 0;
                        for (int i = 0; i < Database.directs.size(); i++) {
                            if ((Database.directs.get(i).first.equals(Database.users.get(v)) && Database.directs.get(i).second.equals(Database.users.get(n))) ||
                                    (Database.directs.get(i).second.equals(Database.users.get(v)) && Database.directs.get(i).first.equals(Database.users.get(n)))) {
                                m = i;
                                break;
                            }
                        }
                        FunctionGenerator.forwardToDirect(Database.users.get(v), Database.directs.get(m), a.messages.get(index));
                        groupInteraction(a, index);
                    } else {
                        System.out.println("This user does not exist among your contacts.");
                        groupInteraction(a, index);
                    }
                } else {
                    System.out.println("You have not started any conversation yet.");
                    groupInteraction(a, index);
                }
            }
            if (response.equals("Group") || response.equals("group") || response.equals("Groups") || response.equals("groups")) {
                if (Database.users.get(v).groups.size() != 0) {
                    System.out.println("Please choose the group you want to interact.");
                    for (int i = 0; i < Database.users.get(v).groups.size(); i++) {
                        System.out.println(Database.users.get(v).groups.get(i).groupName);
                    }
                    String name = scanner.nextLine();
                    int n = 0;
                    for (int i = 0; i < Database.groups.size(); i++) {
                        if (name.equals(Database.groups.get(i).groupName)) {
                            n = i;
                            break;
                        }
                    }
                    if (Database.users.get(v).groups.contains(Database.groups.get(n))) {
                        FunctionGenerator.forwardToGroup(Database.users.get(v), Database.groups.get(n), a.messages.get(index));
                        groupInteraction(a, index);
                    } else {
                        System.out.println("This group does not exist among your contacts.");
                        groupInteraction(a, index);
                    }
                } else {
                    System.out.println("You have not become a member of a group yet.");
                    groupInteraction(a, index);
                }
            }
        }
        if (x.equals("Reply") || x.equals("reply")) {
            System.out.println("Please write your reply.");
            String f = scanner.nextLine();
            String[] texxt = f.split(" ");
            ArrayList<String> text = new ArrayList<>(Arrays.asList(texxt));
            FunctionGenerator.replyGroupMessage(Database.users.get(v), text, a.messages.get(index));
            Controller.groupInteraction(a, index);
        }
        if (x.equals("Edit") || x.equals("edit")) {
            System.out.println("Please edit your message.");
            String f = scanner.nextLine();
            String[] texxt = f.split(" ");
            ArrayList<String> text = new ArrayList<>(Arrays.asList(texxt));
            FunctionGenerator.editMessage(text, a.messages.get(index));
            Controller.groupInteraction(a, index);
        }
        if (x.equals("Delete") || x.equals("delete")) {
            FunctionGenerator.deleteMessage(Database.users.get(v), a.messages.get(index));
            Controller.groupInteraction(a, index);
        }
        if (x.equals("Like") || x.equals("like")) {
            FunctionGenerator.like(Database.users.get(v), a.messages.get(index));
            groupInteraction(a, index);
        }
        if (x.equals("Dislike") || x.equals("dislike")) {
            FunctionGenerator.dislike(Database.users.get(v), a.messages.get(index));
            groupInteraction(a, index);
        }
        if (x.equals("Stats") || x.equals("stats")) {
            FunctionGenerator.statistics(a.messages.get(index));
            groupInteraction(a, index);
        }
        if (x.equals("Main Menu") || x.equals("main menu")) {
            Controller.mainMenu();
        }
        if (x.equals("Add Member") || x.equals("add member") || x.equals("Add") || x.equals("add")) {
            System.out.println("Please enter the user's name. He will be added.");
            String name = scanner.nextLine();
            int i;
            int n;
            for ( i = 0; i < a.members.size(); i++) {
                if (name.equals(a.members.get(i).userName)) {
                    n = i;
                    break;
                }
            }
            FunctionGenerator.addMember(Database.users.get(i), a);
            Controller.groupInteraction(a, index);
        }
        if (x.equals("Remove Member") || x.equals("remove member") || x.equals("Remove") || x.equals("remove")) {
            System.out.println("Please enter the user's name. He will be removed.");
            String name = scanner.nextLine();
            int i;
            int n;
            for ( i = 0; i < a.members.size(); i++) {
                if (name.equals(a.members.get(i).userName)) {
                    n = i;
                    break;
                }
            }
            FunctionGenerator.removeMember(Database.users.get(i), a);
            Controller.groupInteraction(a, index);
        }
        if (x.equals("Show Members") || x.equals("show members")) {
            FunctionGenerator.listOfMembers(a);
            Controller.groupInteraction(a, index);
        }
        if (x.equals("Change Groupname") || x.equals("change groupname") || x.equals("Change GroupName") || x.equals("Change") || x.equals("change")) {
            System.out.println("Please enter the new name of the group.");
            String name = scanner.nextLine();
            FunctionGenerator.changeGroupName(Database.users.get(v), a, name);
            Controller.groupInteraction(a, index);
        }
    }

    public static void searchMessage () {
        int v = 0;
        for (int i = 0; i < Database.users.size(); i++) {
            if (Controller.currentUser.equals(Database.users.get(i).userName)) {
                v = i;
                break;
            }
        }
        System.out.println("Please enter what you want to be searched.");
        Scanner scan = new Scanner(System.in);
        String x = scan.nextLine();
        String[] searched = x.split(" ");
        ArrayList<String> search = new ArrayList<>(Arrays.asList(searched));
        FunctionGenerator.searchMessage(search);
        Controller.mainMenu();
    }

    public static void recommend () {
        int v = 0;
        for (int i = 0; i < Database.users.size(); i++) {
            if (Controller.currentUser.equals(Database.users.get(i).userName)) {
                v = i;
                break;
            }
        }
        Scanner scan = new Scanner(System.in);
        System.out.println("You can either see new users or new products. Which one do you prefer? (Type \"Main Menu\" to go to the main menu.)");
        String response = scan.nextLine();
        if (response.equals("User") || response.equals("user")) {
            FunctionGenerator.suggestUser(Database.users.get(v));
            Controller.recommend();
        }
        if (response.equals("Product") || response.equals("product")) {
            FunctionGenerator.suggestProduct(Database.users.get(v));
            Controller.recommend();
        }
        if (response.equals("Main Menu") || response.equals("main menu")) {
            Controller.mainMenu();
        }
    }

    public static void store () {
        int v = 0;
        for (int i = 0; i < Database.users.size(); i++) {
            if (Controller.currentUser.equals(Database.users.get(i).userName)) {
                v = i;
                break;
            }
        }
        System.out.println("Where do you like to go?");
        System.out.println("1) Store");
        System.out.println("2) New Advertisement");
        System.out.println("3) Main Menu");
        Scanner scanner = new Scanner(System.in);
        String x = scanner.nextLine();
        if (x.equals("Store") || x.equals("store")) {
            if (Database.advertisements.size() != 0) {
                Controller.advertisementInteraction(Database.advertisements.size() - 1);
            }
            else {
                System.out.println("There are no advertisements to be shown here.");
                Controller.store();
            }
        }
        if (x.equals("New Advertisement") || x.equals("new advertisement")) {
            boolean discount = false;
            System.out.println("What is the name of your product?");
            String name = scanner.nextLine();
            System.out.println("Please write a description for your product.");
            String text = scanner.nextLine();
            //String[] texxt = s.split(" ");
            //ArrayList<String> text = new ArrayList<>(Arrays.asList(texxt));
            System.out.println("Please enter the product's price.");
            int price = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Does your product has a discount?");
            String response = scanner.nextLine();
            if (response.equals("Yes") || response.equals("yes")) {
                discount = true;
            }
            System.out.println("How many percents of discount is given?");
            int percent = scanner.nextInt();
            FunctionGenerator.sendAdvertisement(Database.users.get(v), text, name, price, discount, percent);
            Controller.store();
        }
        if (x.equals("Main Menu") || x.equals("main menu")) {
            Controller.mainMenu();
        }
    }

    public static void advertisementInteraction (int index) {
        int v = 0;
        for (int i = 0; i < Database.users.size(); i++) {
            if (Controller.currentUser.equals(Database.users.get(i).userName)) {
                v = i;
                break;
            }
        }
        FunctionGenerator.displayAdvertisement(Database.advertisements.get(index));
        Scanner scanner = new Scanner(System.in);
        String x = scanner.nextLine();
        if (x.equals("Next") || x.equals("next")) {
            if (index != Database.advertisements.size() - 1) {
                advertisementInteraction(index + 1);
            }
            else {
                advertisementInteraction(0);
            }
        }
        if (x.equals("Previous") || x.equals("previous")) {
            if (index != 0) {
                advertisementInteraction(index - 1);
            }
            else {
                advertisementInteraction(Database.advertisements.size() - 1);
            }
        }
        if (x.equals("Purchase") || x.equals("purchase")) {
            System.out.println("Please choose your quantity.");
            int quantity;
            quantity = scanner.nextInt();
            FunctionGenerator.purchase(Database.advertisements.get(index), quantity, Database.users.get(v));
            advertisementInteraction(index);
        }
        if (x.equals("Main Menu") || x.equals("main menu")) {
            Controller.mainMenu();
        }
        if (x.equals("Like") || x.equals("like")) {
            FunctionGenerator.like(Database.users.get(v), Database.advertisements.get(index));
            advertisementInteraction(index);
        }
        if (x.equals("Dislike") || x.equals("dislike")) {
            FunctionGenerator.dislike(Database.users.get(v), Database.advertisements.get(index));
            advertisementInteraction(index);
        }
        if (x.equals("Stats") || x.equals("stats")) {
            FunctionGenerator.statistics(Database.advertisements.get(index));
            advertisementInteraction(index);
        }
    }
}


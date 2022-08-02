package com.example.project;

public class Main {
    public static void main (String[] args) {
        System.out.println("Welcome to EARL");
        System.out.println("You can easily enjoy your easy, safe and highly-encrypted interactions. \n");
        Database.getData();
        Main.messageNum = Database.messages.size();
        Controller.registration();
    }
    static int messageNum;
}

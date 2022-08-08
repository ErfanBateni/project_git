package com.example.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Direct {
    public static String readFile(File file) throws FileNotFoundException {
        StringBuilder text = new StringBuilder("");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            text.append(scanner.nextLine());
        }
        return text.toString();
    }
    User first;
    User second;
    ArrayList<DirectMessage> messages = new ArrayList<>();
    HashMap<User,Boolean> seen = new HashMap<>();

    public Direct (User second) throws FileNotFoundException {
        int v = 0;
        for (int i = 0; i < Database.users.size(); i++) {
            if (readFile(new File("D:\\usernameLogin")).equals(Database.users.get(i).userName)) {
                v = i;
            }
        }
        this.first = Database.users.get(v);
        this.first.directs.add(this);
        this.second = second;
        this.second.directs.add(this);
        Database.directs.add(this);
        this.seen.put(Database.users.get(v),false);
        this.seen.put(second,false);
    }

    public Direct (User first, User second) {
        this.first = first;
        this.first.directs.add(this);
        this.second = second;
        this.second.directs.add(this);
        Database.directs.add(this);
    }
}


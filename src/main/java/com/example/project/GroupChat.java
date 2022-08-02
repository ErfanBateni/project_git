package com.example.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import static com.example.project.HelloApplication.stage;

public class GroupChat {
    public GridPane gridPane;
    public static String readFile(File file) throws FileNotFoundException {
        StringBuilder text = new StringBuilder("");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            text.append(scanner.nextLine());
        }
        return text.toString();
    }

    @FXML
    TextField message;

    @FXML
    Button addMember;
    public void addMember() throws FileNotFoundException {
        String groupName = readFile(new File("D:\\groupName"));
        for (User user : Database.users){
            if (user.userName.equals(readFile(new File("D:\\usernameLogin")))){
                for (Group group : user.groups){
                    if (group.groupName.equals(groupName)){
                        if (user.equals(group.admin)){
                            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addMember.fxml"));
                            Scene scene = null;
                            try {
                                scene = new Scene(fxmlLoader.load(), 600, 400);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            stage.setScene(scene);
                            stage.show();
                        }
                        else {
                            Label warning = new Label();
                            warning.setText("You do not have the permission.");
                            warning.setTextFill(Color.RED);
                            gridPane.add(warning,0,3);
                        }
                    }
                }
            }
        }
    }

    @FXML
    Button removeMember;
    public void removeMember() throws FileNotFoundException {
        String groupName = readFile(new File("D:\\groupName"));
        for (User user : Database.users){
            if (user.userName.equals(readFile(new File("D:\\usernameLogin")))){
                for (Group group : user.groups){
                    if (group.groupName.equals(groupName)){
                        if (user.equals(group.admin)){
                            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("RemoveMember.fxml"));
                            Scene scene = null;
                            try {
                                scene = new Scene(fxmlLoader.load(), 600, 400);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            stage.setScene(scene);
                            stage.show();
                        }
                        else {
                            Label warning = new Label();
                            warning.setText("  You do not have the permission.");
                            warning.setTextFill(Color.RED);
                            gridPane.add(warning,0,3);
                        }
                    }
                }
            }
        }
    }

    @FXML
    Button listOfMembers;
    public void listOfMembers(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("listOfMembers.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    Button changeGroupName;
    public void changeGroupName() throws FileNotFoundException {
        String groupName = readFile(new File("D:\\groupName"));
        for (User user : Database.users){
            if (user.userName.equals(readFile(new File("D:\\usernameLogin")))){
                for (Group group : user.groups){
                    if (group.groupName.equals(groupName)){
                        if (user.equals(group.admin)){
                            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("changeGroupName.fxml"));
                            Scene scene = null;
                            try {
                                scene = new Scene(fxmlLoader.load(), 600, 400);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            stage.setScene(scene);
                            stage.show();
                        }
                        else {
                            Label warning = new Label();
                            warning.setText("You do not have the permission.");
                            warning.setTextFill(Color.RED);
                            gridPane.add(warning,2,3);
                        }
                    }
                }
            }
        }
    }

    @FXML
    Button leaveChat;
    public void leaveChat() throws FileNotFoundException {
        String groupName = readFile(new File("D:\\groupName"));
        for (User user : Database.users) {
            if (user.userName.equals(readFile(new File("D:\\usernameLogin")))) {
                for (Group group : user.groups){
                    if (group.groupName.equals(groupName)){
                        if (group.admin.equals(user)){
                            Label warning = new Label();
                            warning.setText("You are the admin.");
                            warning.setTextFill(Color.RED);
                            gridPane.add(warning,2,3);
                            break;
                        }
                        else {
                            group.members.remove(user);
                            user.groups.remove(group);
                            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("groups.fxml"));
                            Scene scene = null;
                            try {
                                scene = new Scene(fxmlLoader.load(), 600, 400);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            stage.setScene(scene);
                            stage.show();
                        }
                    }
                }
            }
        }

    }

    @FXML
    Button send;
    public void send(){

    }

    @FXML
    Button back;
    public void back(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("groups.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() throws FileNotFoundException {

        String groupName = readFile(new File("D:\\groupName"));
        Label name = new Label();
        name.setText(groupName);
        name.setTextFill(Color.DARKBLUE);
        name.setFont(Font.font(40));
        GridPane.setHalignment(name, HPos.CENTER);
        gridPane.add(name,1,1);
    }

}

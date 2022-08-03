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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import static com.example.project.HelloApplication.stage;

public class GroupChat {
    public GridPane gridPane;
    public VBox vbox;
    public static String readFile(File file) throws FileNotFoundException {
        StringBuilder text = new StringBuilder("");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            text.append(scanner.nextLine());
        }
        return text.toString();
    }
    public static void writeFile(File file, String text, boolean append) throws IOException {
        FileWriter fileWriter = new FileWriter(file,append);
        fileWriter.write(text);
        fileWriter.close();
    }
    int v,u,counter;

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
                            gridPane.add(warning,0,1);
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
                            gridPane.add(warning,0,0);
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
                            gridPane.add(warning,2,0);
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
    public void send() throws FileNotFoundException {
        v=0;u=0;
        for (int i=0;i<Database.users.size();i++){
            if (Database.users.get(i).userName.equals(readFile(new File("D:\\usernameLogin")))){
                v=i;
                break;
            }
        }
        boolean groupExists = false;
        for (int i = 0; i < Database.groups.size(); i++) {
           if (Database.groups.get(i).groupName.equals(readFile(new File("D:\\groupName")))){
                groupExists = true;
                u=i;
            }
        }
        if (groupExists) {
            GroupMessage newGroupMessage = new GroupMessage(Database.users.get(v), message.getText(), Database.groups.get(u));
        }
        message.setText("");
        initialize();
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
        v=0;u=0;
        for (int i=0;i<Database.users.size();i++){
            if (Database.users.get(i).userName.equals(readFile(new File("D:\\usernameLogin")))){
                v=i;
                break;
            }
        }
        boolean groupExists = false;
        for (int i = 0; i < Database.groups.size(); i++) {
            if (Database.groups.get(i).groupName.equals(readFile(new File("D:\\groupName")))){
                groupExists = true;
                u=i;
            }
        }
        gridPane.getChildren().clear();
        Rectangle rectangle = new Rectangle(0,0,1600,1400);
        rectangle.setFill(Color.LIGHTBLUE);
        gridPane.add(rectangle,0,0);
        counter=6;
        String groupName = readFile(new File("D:\\groupName"));
        Label namegroup = new Label();
        namegroup.setText(groupName);
        namegroup.setTextFill(Color.DARKBLUE);
        namegroup.setFont(Font.font(40));
        GridPane.setHalignment(namegroup,HPos.CENTER);
        gridPane.add(namegroup,1,0);
        if (Database.groups.get(u).messages.size()>7){
            for (int i=Database.groups.get(u).messages.size()-1; i>=Database.groups.get(u).messages.size()-7; i--){
                if (counter>=0 && Database.groups.get(u).messages.get(i).sender.userName.equals(readFile(new File("D:\\usernameLogin")))){
                    Button edit = new Button("edit");
                    int finalI = i;
                    edit.setOnMouseClicked(mouseEvent -> {
                        File file = new File("D:\\editMessage");
                        try {
                            writeFile(file,Database.groups.get(u).messages.get(finalI).textMessage,false);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("editGroupMessage.fxml"));
                        Scene scene = null;
                        try {
                            scene = new Scene(fxmlLoader.load(), 600, 400);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        stage.setScene(scene);
                        stage.show();
                    });
                    gridPane.add(edit,0,counter);
                    Label myMessage = new Label();
                    if (Database.groups.get(u).messages.get(i).isEdited){
                        myMessage.setText("edited - "+Database.groups.get(u).messages.get(i).textMessage+"  ");
                    }
                    else {
                        myMessage.setText(Database.groups.get(u).messages.get(i).textMessage+"  ");
                    }
                    GridPane.setHalignment(myMessage, HPos.RIGHT);
                    myMessage.setFont(Font.font(18));
                    gridPane.add(myMessage,2,counter);
                    counter--;
                }
                else if (counter>=0){
                    Label yourMessage = new Label();
                    if (Database.groups.get(u).messages.get(i).isEdited){
                        yourMessage.setText("  "+Database.groups.get(u).messages.get(i).sender.userName+": edited - "+Database.groups.get(u).messages.get(i).textMessage);
                    }
                    else {
                        yourMessage.setText("  "+Database.groups.get(u).messages.get(i).sender.userName+": "+Database.groups.get(u).messages.get(i).textMessage);
                    }
                    yourMessage.setFont(Font.font(18));
                    gridPane.add(yourMessage,0,counter);
                    counter--;
                }

            }
        }
        else {
            for (int i=Database.groups.get(u).messages.size()-1; i>=0; i--){
                if (counter>=0 && Database.groups.get(u).messages.get(i).sender.userName.equals(readFile(new File("D:\\usernameLogin")))){
                    Label myMessage = new Label();
                    if (Database.groups.get(u).messages.get(i).isEdited){
                        myMessage.setText("edited - "+Database.groups.get(u).messages.get(i).textMessage+"  ");
                    }
                    else {
                        myMessage.setText(Database.groups.get(u).messages.get(i).textMessage+"  ");
                    }
                    GridPane.setHalignment(myMessage, HPos.RIGHT);
                    myMessage.setFont(Font.font(18));
                    gridPane.add(myMessage,2,counter);
                    Button edit = new Button("edit");
                    int finalI = i;
                    edit.setOnMouseClicked(mouseEvent -> {
                        File file = new File("D:\\editMessage");
                        try {
                            writeFile(file,Database.groups.get(u).messages.get(finalI).textMessage,false);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("editGroupMessage.fxml"));
                        Scene scene = null;
                        try {
                            scene = new Scene(fxmlLoader.load(), 600, 400);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        stage.setScene(scene);
                        stage.show();
                    });
                    gridPane.add(edit,0,counter);
                    counter--;
                }
                else if (counter>=0){
                    Label yourMessage = new Label();
                    if (Database.groups.get(u).messages.get(i).isEdited){
                        yourMessage.setText("  "+Database.groups.get(u).messages.get(i).sender.userName+": edited - "+Database.groups.get(u).messages.get(i).textMessage);
                    }
                    else {
                        yourMessage.setText("  "+Database.groups.get(u).messages.get(i).sender.userName+": "+Database.groups.get(u).messages.get(i).textMessage);
                    }
                    yourMessage.setFont(Font.font(18));
                    gridPane.add(yourMessage,0,counter);
                    counter--;
                }
            }
        }
    }
}

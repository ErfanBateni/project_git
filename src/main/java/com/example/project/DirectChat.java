package com.example.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static com.example.project.HelloApplication.stage;

public class DirectChat {
    public GridPane gridPane;
    public static String readFile(File file) throws FileNotFoundException {
        StringBuilder text = new StringBuilder("");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            text.append(scanner.nextLine());
        }
        return text.toString();
    }
    int v,vv,u,counter;

    public void initialize() throws FileNotFoundException {
        v=0;vv=0;u=0;
        for (int i=0;i<Database.users.size();i++){
            if (Database.users.get(i).userName.equals(readFile(new File("D:\\usernameLogin")))){
                v=i;
                break;
            }
        }
        for (int i=0;i<Database.users.size();i++){
            if (Database.users.get(i).userName.equals(readFile(new File("D:\\secondUser")))){
                vv=i;
                break;
            }
        }
        boolean directExits = false;
        for (int i = 0; i < Database.directs.size(); i++) {
            if ( (Database.users.get(v).userName.equals(Database.directs.get(i).first.userName) && Database.users.get(vv).userName.equals(Database.directs.get(i).second.userName)) ||
                    (Database.users.get(v).userName.equals(Database.directs.get(i).second.userName) && Database.users.get(vv).userName.equals(Database.directs.get(i).first.userName)) ) {
                directExits = true;
                u=i;
            }
        }

        gridPane.getChildren().clear();
        Rectangle rectangle = new Rectangle(0,0,1600,1400);
        rectangle.setFill(Color.LIGHTBLUE);
        gridPane.add(rectangle,0,0);
        counter=9;
        if (Database.directs.get(u).messages.size()>10){
            for (int i=Database.directs.get(u).messages.size()-1; i>=Database.directs.get(u).messages.size()-10; i--){
                if (counter>=0 && Database.directs.get(u).messages.get(i).sender.userName.equals(readFile(new File("D:\\usernameLogin")))){
                    Label myMessage = new Label("  "+Database.directs.get(u).messages.get(i).textMessage+"  ");
                    GridPane.setHalignment(myMessage, HPos.RIGHT);
                    myMessage.setFont(Font.font(18));
                    gridPane.add(myMessage,1,counter);
                    counter--;
                }
                else if (counter>=0){
                    Label yourMessage = new Label("  "+Database.directs.get(u).messages.get(i).textMessage+"  ");
                    yourMessage.setFont(Font.font(18));
                    gridPane.add(yourMessage,0,counter);
                    counter--;
                }
            }
        }
        else {
            for (int i=Database.directs.get(u).messages.size()-1; i>=0; i--){
                if (counter>=0 && Database.directs.get(u).messages.get(i).sender.userName.equals(readFile(new File("D:\\usernameLogin")))){
                    Label myMessage = new Label("  "+Database.directs.get(u).messages.get(i).textMessage+"  ");
                    GridPane.setHalignment(myMessage, HPos.RIGHT);
                    myMessage.setFont(Font.font(18));
                    gridPane.add(myMessage,1,counter);
                    counter--;
                }
                else if (counter>=0){
                    Label yourMessage = new Label("  "+Database.directs.get(u).messages.get(i).textMessage+"  ");
                    yourMessage.setFont(Font.font(18));
                    gridPane.add(yourMessage,0,counter);
                    counter--;
                }
            }
        }
    }

    @FXML
    TextField message;

    @FXML
    Button send;
    public void send() throws FileNotFoundException {
        v=0;vv=0;u=0;
        for (int i=0;i<Database.users.size();i++){
            if (Database.users.get(i).userName.equals(readFile(new File("D:\\usernameLogin")))){
                v=i;
                break;
            }
        }
        for (int i=0;i<Database.users.size();i++){
            if (Database.users.get(i).userName.equals(readFile(new File("D:\\secondUser")))){
                vv=i;
                break;
            }
        }
        boolean directExits = false;
        for (int i = 0; i < Database.directs.size(); i++) {
            if ( (Database.users.get(v).userName.equals(Database.directs.get(i).first.userName) && Database.users.get(vv).userName.equals(Database.directs.get(i).second.userName)) ||
                    (Database.users.get(v).userName.equals(Database.directs.get(i).second.userName) && Database.users.get(vv).userName.equals(Database.directs.get(i).first.userName)) ) {
                directExits = true;
                u=i;
            }
        }
        if (directExits) {
            DirectMessage newDirectMessage = new DirectMessage(Database.users.get(v), message.getText(), Database.directs.get(u));
        }
        message.setText("");
        initialize();
    }

    @FXML
    Button back;
    public void back(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("directs.fxml"));
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

package com.example.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.Scanner;

import static com.example.project.HelloApplication.stage;

public class Signup {
    public AnchorPane anchorPane;
    boolean type;

    @FXML
    TextField username;
    @FXML
    TextField password;
    @FXML
    TextField security_question;
    @FXML
    TextField picture;

    @FXML
    Button ordinary;
    public void ordinary(){
        type=false;
    }
    @FXML
    Button business;
    public void business(){
        type=true;
    }

    @FXML
    Button done;
    public void done(){
        boolean userExists = false;
        Label warning1 = new Label();
        Label warning2 = new Label();
        for (int i = 0; i < Database.users.size(); i++) {
            if (Database.users.get(i).userName.equals(username.getText())) {
                userExists = true;
                break;
            }
        }
        if (userExists) {
            warning1.setText("This username already exists. Please try again.");
            warning1.setTextFill(Color.RED);
            warning1.relocate(120,145);
            anchorPane.getChildren().add(warning1);
        }
        else {
            if (password.getLength()<8){
                warning2.setText("Your password should have at least 8 characters. Please try again.");
                warning2.setTextFill(Color.RED);
                warning2.relocate(120,200);
                anchorPane.getChildren().add(warning2);
            }
            else {
                User newUser = new User(username.getText(),password.getText(), type,security_question.getText(),picture.getText());
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("first_menu.fxml"));
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

    @FXML
    Button back;
    public void back(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("first_menu.fxml"));
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

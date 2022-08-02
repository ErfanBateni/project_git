package com.example.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.example.project.HelloApplication.stage;

public class Forget_password {
    public static void writeFile(File file, String text, boolean append) throws IOException {
        FileWriter fileWriter = new FileWriter(file,append);
        fileWriter.write(text);
        fileWriter.close();
    }

    public AnchorPane anchorPane;

    @FXML
    TextField username;
    @FXML
    TextField security_question;

    @FXML
    Button done;
    public void done() throws IOException {
        boolean userExists = false;
        Label warning1 = new Label();
        Label warning2 = new Label();
        int v = 0;
        for (int i = 0; i < Database.users.size(); i++) {
            if (Database.users.get(i).userName.equals(username.getText())) {
                userExists = true;
                v = i;
                break;
            }
        }
        if (userExists){
            if (Database.users.get(v).securityAnswer.equals(security_question.getText())){
                File file = new File("D:\\forgetPassword");
                writeFile(file,username.getText(),false);
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("password.fxml"));
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
                warning1.setText("Your answer is incorrect, please try again.");
                warning1.setTextFill(Color.RED);
                warning1.relocate(320,230);
                anchorPane.getChildren().add(warning1);
            }
        }
        else {
            warning2.setText("No user exists with your intended username.");
            warning2.setTextFill(Color.RED);
            warning2.relocate(290,170);
            anchorPane.getChildren().add(warning2);
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

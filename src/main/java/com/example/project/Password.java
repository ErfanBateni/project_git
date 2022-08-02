package com.example.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static com.example.project.HelloApplication.stage;

public class Password {
    public static String readFile(File file) throws FileNotFoundException {
        StringBuilder text = new StringBuilder("");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            text.append(scanner.nextLine());
        }
        return text.toString();
    }

    public AnchorPane anchorPane;
    public void initialize() throws FileNotFoundException {
        Label password = new Label();
        String username = readFile(new File("D:\\forgetPassword"));
        int v = 0;
        for (int i = 0; i < Database.users.size(); i++) {
            if (Database.users.get(i).userName.equals(username)) {
                v = i;
                break;
            }
        }
        password.setText(Database.users.get(v).password);
        password.setTextFill(Color.SADDLEBROWN);
        password.setFont(Font.font(30));
        password.relocate(330,75);
        anchorPane.getChildren().add(password);
    }

    @FXML
    Button no;
    public void no(){
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

    @FXML
    Button yes;
    public void yes(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("new_password.fxml"));
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

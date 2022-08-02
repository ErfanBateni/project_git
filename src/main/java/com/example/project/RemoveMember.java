package com.example.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static com.example.project.HelloApplication.stage;

public class RemoveMember {
    public GridPane gridPane;
    public static String readFile(File file) throws FileNotFoundException {
        StringBuilder text = new StringBuilder("");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            text.append(scanner.nextLine());
        }
        return text.toString();
    }

    public void initialize() throws FileNotFoundException {
        int counter=1;
        String groupName = readFile(new File("D:\\groupName"));
        for (Group group : Database.groups){
            if (group.groupName.equals(groupName)){
                for (User user : group.members){
                    if (!user.userName.equals(readFile(new File("D:\\usernameLogin")))){
                        Label username = new Label();
                        username.setText("                " + user.userName);
                        username.setTextFill(Color.DARKBLUE);
                        username.setFont(Font.font(25));
                        gridPane.add(username,0,counter);
                        Button remove = new Button();
                        remove.setText("   remove ");
                        remove.setTextFill(Color.DARKBLUE);
                        remove.setOnMouseClicked(mouseEvent -> {
                            group.members.remove(user);
                            user.groups.remove(group);
                        });
                        gridPane.add(remove,0,counter);
                        counter++;
                    }
                }
                break;
            }
        }
    }

    @FXML
    Button back;
    public void back(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("groupChat.fxml"));
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

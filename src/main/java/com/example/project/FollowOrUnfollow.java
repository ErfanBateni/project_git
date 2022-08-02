package com.example.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import static com.example.project.HelloApplication.stage;

public class FollowOrUnfollow {
    public GridPane gridPane;
    public static String readFile(File file) throws FileNotFoundException {
        StringBuilder text = new StringBuilder("");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            text.append(scanner.nextLine());
        }
        return text.toString();
    }
    int counter=0;

    public void initialize() throws FileNotFoundException {
        for (User user : Database.users){
            if (user.userName.equals(readFile(new File("D:\\usernameLogin")))){
                for (User user1 : user.followings){
                    if (!user1.userName.equals(readFile(new File("D:\\usernameLogin")))){
                        Label name1 = new Label(user1.userName);
                        name1.setTextFill(Color.WHITE);
                        GridPane.setHalignment(name1, HPos.RIGHT);
                        gridPane.add(name1,0,counter);
                        Button unfollow = new Button("  unfollow");
                        unfollow.setOnMouseClicked(mouseEvent -> {
                            user.followings.remove(user1);
                            user1.followers.remove(user);
                        });
                        gridPane.add(unfollow,0,counter);
                        counter++;
                    }
                }
                break;
            }
        }
        for (User user : Database.users){
            if (user.userName.equals(readFile(new File("D:\\usernameLogin")))){
                for (User user1 : Database.users){
                    if (!user.followings.contains(user1) && !user1.userName.equals(readFile(new File("D:\\usernameLogin")))){
                        Label name2 = new Label(user1.userName);
                        name2.setTextFill(Color.WHITE);
                        GridPane.setHalignment(name2, HPos.RIGHT);
                        gridPane.add(name2,0,counter);
                        Button unfollow = new Button("  follow");
                        unfollow.setOnMouseClicked(mouseEvent -> {
                            user.followings.add(user1);
                            user1.followers.add(user);
                        });
                        gridPane.add(unfollow,0,counter);
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

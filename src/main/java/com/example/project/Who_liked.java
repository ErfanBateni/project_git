package com.example.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
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

public class Who_liked {
    public GridPane gridPane;
    public static String readFile(File file) throws FileNotFoundException {
        StringBuilder text = new StringBuilder("");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            text.append(scanner.nextLine());
        }
        return text.toString();
    }
    int counter;

    public void initialize() throws FileNotFoundException {
        for (Post post : Database.posts){
            if (post.sender.userName.equals(readFile(new File("D:\\usernameLogin"))) && post.textMessage.equals(readFile(new File("D:\\postTextMessage"))))
                counter=0;
                for (User user : post.liked){
                    if (!user.userName.equals(readFile(new File("D:\\usernameLogin")))){
                        Label usernameWhoLiked = new Label(user.userName);
                        usernameWhoLiked.setFont(Font.font(20));
                        usernameWhoLiked.setTextFill(Color.DARKBLUE);
                        GridPane.setHalignment(usernameWhoLiked, HPos.CENTER);
                        gridPane.add(usernameWhoLiked,1,counter);
                        counter++;
                    }
            }
        }

    }

    @FXML
    Button back;
    public void back(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("posts.fxml"));
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

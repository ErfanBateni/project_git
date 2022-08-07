package com.example.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import static com.example.project.HelloApplication.stage;

public class Posts {
    public Pane pane;
    public static String readFile(File file) throws FileNotFoundException {
        StringBuilder text = new StringBuilder("");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            text.append(scanner.nextLine());
        }
        return text.toString();
    }
    int counter;

    @FXML
    HBox hBox1;
    @FXML
    HBox hBox2;

    public void initialize() throws FileNotFoundException {
        int v=0,u=0;
        for (int i=0;i<Database.users.size();i++){
            if (Database.users.get(i).userName.equals(readFile(new File("D:\\usernameLogin")))){
                v=i;
                break;
            }
        }
        for (int i=0;i<Database.posts.size();i++){
            if (Database.posts.get(i).textMessage.equals(readFile(new File("D:\\postTextMessage")))){
                u=i;
                break;
            }
        }
        if (Database.posts.get(u).textMessage.equals(readFile(new File("D:\\postTextMessage"))) && Database.posts.get(u).liked.contains(Database.users.get(v))){
                like.setTextFill(Color.GREEN);
                dislike.setTextFill(Color.RED);
        }
        else if (Database.posts.get(u).textMessage.equals(readFile(new File("D:\\postTextMessage"))) && Database.posts.get(u).disliked.contains(Database.users.get(v))){
                like.setTextFill(Color.RED);
                dislike.setTextFill(Color.GREEN);
        }
        else {
                like.setTextFill(Color.RED);
                dislike.setTextFill(Color.RED);
        }

        Label username = new Label(Database.posts.get(u).sender.userName);
        username.setTextFill(Color.DARKBLUE);
        username.setFont(Font.font(30));
        username.relocate(50,10);
        pane.getChildren().add(username);

        if (!Database.posts.get(u).textPicture.equals(".png")){
            Image postImage = new Image(Database.posts.get(u).textPicture);
            ImageView postImage_view = new ImageView(postImage);
            postImage_view.setX(204);
            postImage_view.setY(80);
            postImage_view.setFitHeight(120);
            postImage_view.setFitWidth(192);
            pane.getChildren().add(postImage_view);
        }

        if (Database.posts.get(u).sender.userType){
            Button seenNumber = new Button("seen = "+Database.posts.get(u).seenNumber);
            seenNumber.setOnMouseClicked(mouseEvent -> {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("charts.fxml"));
                Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load(), 600, 400);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage.setScene(scene);
                stage.show();
            });
            seenNumber.relocate(433,333);
            seenNumber.setPrefHeight(31);
            seenNumber.setTextFill(Color.rgb(0,170,255));
            seenNumber.setFont(Font.font(14));
            pane.getChildren().add(seenNumber);
        }

        Label postText = new Label();
        if (readFile(new File("D:\\postTextMessage")).length()<35){
            postText.setText(readFile(new File("D:\\postTextMessage")));
            postText.setTextFill(Color.DARKBLUE);
            postText.setFont(Font.font(27));
            hBox1.getChildren().add(postText);
        }
        else if (readFile(new File("D:\\postTextMessage")).length()>=35 && readFile(new File("D:\\postTextMessage")).length()<60){
            if (readFile(new File("D:\\postTextMessage")).charAt(33)!=' ' && readFile(new File("D:\\postTextMessage")).charAt(34)!=' '){
                counter=0;
                for (int i = 32; i >=0; i--) {
                    if (readFile(new File("D:\\postTextMessage")).charAt(i)==' '){
                        Label one = new Label(readFile(new File("D:\\postTextMessage")).substring(0,i));
                        Label two = new Label(readFile(new File("D:\\postTextMessage")).substring(i+1));
                        one.setTextFill(Color.DARKBLUE);
                        one.setFont(Font.font(27));
                        two.setTextFill(Color.DARKBLUE);
                        two.setFont(Font.font(27));
                        hBox1.getChildren().add(one);
                        hBox2.getChildren().add(two);
                        break;
                    }
                    counter++;
                }
                if (counter==33) {
                    Label one = new Label(readFile(new File("D:\\postTextMessage")).substring(0,35));
                    Label two = new Label(readFile(new File("D:\\postTextMessage")).substring(35));
                    one.setTextFill(Color.DARKBLUE);
                    one.setFont(Font.font(27));
                    two.setTextFill(Color.DARKBLUE);
                    two.setFont(Font.font(27));
                    hBox1.getChildren().add(one);
                    hBox2.getChildren().add(two);
                }
            }
            else {
                Label one = new Label(readFile(new File("D:\\postTextMessage")).substring(0,35));
                Label two = new Label(readFile(new File("D:\\postTextMessage")).substring(35));
                one.setTextFill(Color.DARKBLUE);
                one.setFont(Font.font(27));
                two.setTextFill(Color.DARKBLUE);
                two.setFont(Font.font(27));
                hBox1.getChildren().add(one);
                hBox2.getChildren().add(two);
            }
        }
    }

    @FXML
    Button showLikes;
    public void showLikes(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("who_liked.fxml"));
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
    Button showComments;
    public void showComments(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("who_commented.fxml"));
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
    Button like;
    public void like() throws FileNotFoundException {
        dislike.setTextFill(Color.RED);
        like.setTextFill(Color.GREEN);
        int v=0,u=0;
        for (int i=0;i<Database.users.size();i++){
            if (Database.users.get(i).userName.equals(readFile(new File("D:\\usernameLogin")))){
                v=i;
                break;
            }
        }
        for (int i=0;i<Database.posts.size();i++){
            if (Database.posts.get(i).textMessage.equals(readFile(new File("D:\\postTextMessage")))){
                u=i;
                break;
            }
        }
        if (!Database.posts.get(u).liked.contains(Database.users.get(v))){
            Database.posts.get(u).liked.add(Database.users.get(v));
            Database.posts.get(u).likeTime.add(String.valueOf(LocalDate.now()).split("-")[2]);
        }
        if (Database.posts.get(u).disliked.contains(Database.users.get(v))){
            Database.posts.get(u).disliked.remove(Database.users.get(v));
        }
    }
    @FXML
    Button dislike;
    public void dislike() throws FileNotFoundException {
        like.setTextFill(Color.RED);
        dislike.setTextFill(Color.GREEN);
        int v=0;
        for (int i=0;i<Database.users.size();i++){
            if (Database.users.get(i).userName.equals(readFile(new File("D:\\usernameLogin")))){
                v=i;
                break;
            }
        }
        for (Post post : Database.posts){
            if (post.textMessage.equals(readFile(new File("D:\\postTextMessage")))){
                if (!post.disliked.contains(Database.users.get(v))){
                    post.disliked.add(Database.users.get(v));
                }
                if (post.liked.contains(Database.users.get(v))){
                    post.liked.remove(Database.users.get(v));
                    post.likeTime.remove(String.valueOf(LocalDate.now()).split("-")[2]);
                }
            }
        }
    }

    @FXML
    Button back;
    public void back(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("second_menu.fxml"));
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

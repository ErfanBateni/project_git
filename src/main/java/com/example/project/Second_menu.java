package com.example.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static com.example.project.HelloApplication.stage;

public class Second_menu {
    public Pane pane;
    public GridPane gridPane;
    int counter,v;
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

    @FXML
    Button red;
    public void red() throws FileNotFoundException {
        for (int i=0;i<Database.users.size();i++){
            if (Database.users.get(i).userName.equals(readFile(new File("D:\\usernameLogin")))){
                v=i;
                break;
            }
        }
        Database.users.get(v).theme = 1;
        if (Database.users.get(v).theme==1){
            theme.setTextFill(Color.ORANGERED);
        }
        else if (Database.users.get(v).theme==2){
            theme.setTextFill(Color.LIGHTGREEN);
        }
        else if (Database.users.get(v).theme==3){
            theme.setTextFill(Color.LIGHTBLUE);
        }
        initialize();
    }
    @FXML
    Button green;
    public void green() throws FileNotFoundException {
        for (int i=0;i<Database.users.size();i++){
            if (Database.users.get(i).userName.equals(readFile(new File("D:\\usernameLogin")))){
                v=i;
                break;
            }
        }
        Database.users.get(v).theme = 2;
        if (Database.users.get(v).theme==1){
            theme.setTextFill(Color.ORANGERED);
        }
        else if (Database.users.get(v).theme==2){
            theme.setTextFill(Color.LIGHTGREEN);
        }
        else if (Database.users.get(v).theme==3){
            theme.setTextFill(Color.LIGHTBLUE);
        }
        initialize();
    }
    @FXML
    Button blue;
    public void blue() throws FileNotFoundException {
        for (int i=0;i<Database.users.size();i++){
            if (Database.users.get(i).userName.equals(readFile(new File("D:\\usernameLogin")))){
                v=i;
                break;
            }
        }
        Database.users.get(v).theme = 3;
        if (Database.users.get(v).theme==1){
            theme.setTextFill(Color.ORANGERED);
        }
        else if (Database.users.get(v).theme==2){
            theme.setTextFill(Color.LIGHTGREEN);
        }
        else if (Database.users.get(v).theme==3){
            theme.setTextFill(Color.LIGHTBLUE);
        }
        initialize();
    }

    @FXML
    Label theme;

    public void initialize() throws FileNotFoundException {
        v=0;counter=1;
        for (int i=0;i<Database.users.size();i++){
            if (Database.users.get(i).userName.equals(readFile(new File("D:\\usernameLogin")))){
                v=i;
                break;
            }
        }
        if (Database.users.get(v).theme==1){
            theme.setTextFill(Color.ORANGERED);
        }
        else if (Database.users.get(v).theme==2){
            theme.setTextFill(Color.LIGHTGREEN);
        }
        else if (Database.users.get(v).theme==3){
            theme.setTextFill(Color.LIGHTBLUE);
        }

        Label username = new Label(Database.users.get(v).userName);
        if (Database.users.get(v).theme==1){
            username.setTextFill(Color.ORANGERED);
        }
        else if (Database.users.get(v).theme==2){
            username.setTextFill(Color.LIGHTGREEN);
        }
        else if (Database.users.get(v).theme==3){
            username.setTextFill(Color.LIGHTBLUE);
        }
        username.setFont(Font.font(30));
        username.relocate(55,3);

        Label followersNum = new Label(String.valueOf(Database.users.get(v).followers.size()));
        if (Database.users.get(v).theme==1){
            followersNum.setTextFill(Color.ORANGERED);
        }
        else if (Database.users.get(v).theme==2){
            followersNum.setTextFill(Color.LIGHTGREEN);
        }
        else if (Database.users.get(v).theme==3){
            followersNum.setTextFill(Color.LIGHTBLUE);
        }
        followersNum.setFont(Font.font(30));
        followersNum.relocate(230,40);

        Label followingsNum = new Label(String.valueOf(Database.users.get(v).followings.size()));
        if (Database.users.get(v).theme==1){
            followingsNum.setTextFill(Color.ORANGERED);
        }
        else if (Database.users.get(v).theme==2){
            followingsNum.setTextFill(Color.LIGHTGREEN);
        }
        else if (Database.users.get(v).theme==3){
            followingsNum.setTextFill(Color.LIGHTBLUE);
        }
        followingsNum.setFont(Font.font(30));
        followingsNum.relocate(230,80);
        pane.getChildren().add(username);
        pane.getChildren().add(followersNum);
        pane.getChildren().add(followingsNum);

        if (!Database.users.get(v).picture.equals(".png")){
            Image profileImage = new Image(Database.users.get(v).picture);
            ImageView profileImage_view = new ImageView(profileImage);
            profileImage_view.setX(295);
            profileImage_view.setY(25);
            profileImage_view.setFitHeight(100);
            profileImage_view.setFitWidth(160);
            pane.getChildren().add(profileImage_view);
        }

        for (int i = Database.users.get(v).posts.size()-1; i>=0; i--) {
            if (Database.posts.get(i).sender.userName.equals(Database.users.get(v).userName)){
                Button showPost = new Button("  Show post");
                int finalI = i;
                showPost.setOnMouseClicked(mouseEvent -> {
                    if (!Database.posts.get(finalI).seen.contains(Database.users.get(v))){
                        Database.posts.get(finalI).seen.add(Database.users.get(v));
                    }
                    File file = new File("D:\\postTextMessage");
                    try {
                        writeFile(file,Database.posts.get(finalI).textMessage,false);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("posts.fxml"));
                    Scene scene = null;
                    try {
                        scene = new Scene(fxmlLoader.load(), 600, 400);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    stage.setScene(scene);
                    stage.show();
                });
                showPost.setTextFill(Color.DARKBLUE);
                gridPane.add(showPost,0,counter);
                Label postText = new Label();
                if (Database.posts.get(i).textMessage.length()>17){
                    postText.setText(Database.posts.get(i).textMessage.substring(0,17)+" ...");
                    postText.setFont(Font.font(14));
                }
                else {
                    postText.setText(Database.posts.get(i).textMessage);
                    postText.setFont(Font.font(17));
                }
                postText.setTextFill(Color.LIGHTBLUE);
                GridPane.setHalignment(postText, HPos.RIGHT);
                gridPane.add(postText,0,counter);
                counter++;
            }
        }
    }

    @FXML
    Button numberOfFollowings;
    public void numberOfFollowings(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("followingsList.fxml"));
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
    Button numberOfFollowers;
    public void numberOfFollowers(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("followersList.fxml"));
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
    Button timeline;
    public void timeline(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("timeline.fxml"));
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
    Button directs;
    public void directs(){
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

    @FXML
    Button groups;
    public void groups(){
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

    @FXML
    Button search;
    public void search(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("search.fxml"));
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
    Button advertising;
    public void advertising(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("advertising.fxml"));
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
    Button store;
    public void store(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("store.fxml"));
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
    Button logout;
    public void logout(){
        //Controller.currentUser = "";
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

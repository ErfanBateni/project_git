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
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static com.example.project.HelloApplication.stage;

public class Timeline {
    public GridPane gridPane;
    int counter,v,numOfFollowingsPosts,numOfSeen;
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

    public void initialize() throws FileNotFoundException {
        v=0;counter=0;numOfFollowingsPosts=0;numOfSeen=0;
        for (int i=0;i<Database.users.size();i++){
            if (Database.users.get(i).userName.equals(readFile(new File("D:\\usernameLogin")))){
                v=i;
                break;
            }
        }
        for (int i = 0; i < Database.users.get(v).followings.size(); i++) {
            numOfFollowingsPosts += Database.users.get(v).followings.get(i).posts.size();
        }
        for (int i = 0; i < Database.users.get(v).followings.size(); i++) {
            for (int j = 0; j < Database.users.get(v).followings.get(i).posts.size(); j++) {
                if (Database.users.get(v).followings.get(i).posts.get(j).seen.contains(Database.users.get(v)))
                    numOfSeen++;
            }
        }
        for (int i=Database.posts.size()-1; i>=0; i--) {
            if (numOfSeen != numOfFollowingsPosts){
                if (Database.users.get(v).followings.contains(Database.posts.get(i).sender) && !Database.posts.get(i).seen.contains(Database.users.get(v)) ){
                    Button showPost = new Button("  show post");
                    showPost.setTextFill(Color.DARKBLUE);
                    int finalI = i;
                    showPost.setOnMouseClicked(mouseEvent -> {
                        if (!Database.posts.get(finalI).seen.contains(Database.users.get(v))){
                            Database.posts.get(finalI).seen.add(Database.users.get(v));
                        }
                        for (int j=0;j<Database.posts.size();j++){
                            try {
                                if (Database.posts.get(j).textMessage.equals(readFile(new File("D:\\postTextMessage")))){
                                    Database.posts.get(j).seenNumber++;
                                    break;
                                }
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
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
                    postText.setTextFill(Color.DARKBLUE);
                    GridPane.setHalignment(postText, HPos.RIGHT);
                    gridPane.add(postText,0,counter);

                    counter++;
                }
            }
            else {
                if (Database.users.get(v).followings.contains(Database.posts.get(i).sender)){
                    Button showPost = new Button("  show post");
                    showPost.setTextFill(Color.DARKBLUE);
                    int finalI = i;
                    showPost.setOnMouseClicked(mouseEvent -> {
                        for (int j=0;j<Database.posts.size();j++){
                            try {
                                if (Database.posts.get(j).textMessage.equals(readFile(new File("D:\\postTextMessage")))){
                                    Database.posts.get(j).seenNumber++;
                                    break;
                                }
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
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
                    postText.setTextFill(Color.DARKBLUE);
                    GridPane.setHalignment(postText, HPos.RIGHT);
                    gridPane.add(postText,0,counter);

                    counter++;
                }
            }
        }
    }

    @FXML
    Button create_post;
    public void create_post(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("newPost.fxml"));
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

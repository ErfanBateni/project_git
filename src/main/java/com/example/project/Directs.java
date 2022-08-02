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

public class Directs {
    public GridPane gridPane;
    public static String readFile(File file) throws FileNotFoundException {
        StringBuilder text = new StringBuilder("");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            text.append(scanner.nextLine());
        }
        return text.toString();
    }
    int v,counter;

    public void initialize() throws FileNotFoundException {
        v=0;counter=0;
        for (int i=0;i<Database.users.size();i++){
            if (Database.users.get(i).userName.equals(readFile(new File("D:\\usernameLogin")))){
                v=i;
                break;
            }
        }
        for (Direct direct : Database.users.get(v).directs){
            if (direct.first.equals(Database.users.get(v)) && !Database.users.get(v).blocked.contains(direct.second)){
                Label name1 = new Label(direct.second.userName);
                name1.setTextFill(Color.DARKBLUE);
                GridPane.setHalignment(name1, HPos.RIGHT);
                gridPane.add(name1,0,counter);
                Button insideDirect = new Button("  inside direct");
                insideDirect.setOnMouseClicked(mouseEvent -> {
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("chat.fxml"));
                    Scene scene = null;
                    try {
                        scene = new Scene(fxmlLoader.load(), 600, 400);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    stage.setScene(scene);
                    stage.show();
                });
                gridPane.add(insideDirect,0,counter);
                counter++;
            }
            else if (!Database.users.get(v).blocked.contains(direct.first)){
                Label name2 = new Label(direct.first.userName);
                name2.setTextFill(Color.DARKBLUE);
                GridPane.setHalignment(name2, HPos.RIGHT);
                gridPane.add(name2,0,counter);
                Button insideDirect = new Button("  inside direct");
                insideDirect.setOnMouseClicked(mouseEvent -> {
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("chat.fxml"));
                    Scene scene = null;
                    try {
                        scene = new Scene(fxmlLoader.load(), 600, 400);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    stage.setScene(scene);
                    stage.show();
                });
                gridPane.add(insideDirect,0,counter);
                counter++;
            }
        }
    }

    @FXML
    Button followOrUnfollow;
    public void followOrUnfollow(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("followOrUnfollow.fxml"));
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
    Button blockOrUnblock;
    public void blockOrUnblock(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("blockOrUnblock.fxml"));
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

    @FXML
    Button new_conversation;
    public void new_conversation(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("new_conversation.fxml"));
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

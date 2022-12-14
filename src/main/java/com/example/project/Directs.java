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
    public static void writeFile(File file, String text, boolean append) throws IOException {
        FileWriter fileWriter = new FileWriter(file,append);
        fileWriter.write(text);
        fileWriter.close();
    }

    int v,counter,u;

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
                for (int i = 0; i < Database.users.get(v).directs.size(); i++) {
                    if (Database.users.get(v).directs.get(i).second.userName.equals(direct.second.userName) && !Database.users.get(v).directs.get(i).seen.get(Database.users.get(v))){
                        name1.setUnderline(true);
                    }
                }
                name1.setTextFill(Color.DARKBLUE);
                name1.setFont(Font.font(20));
                GridPane.setHalignment(name1, HPos.RIGHT);
                gridPane.add(name1,0,counter);
                Button insideDirect = new Button("  inside direct");
                insideDirect.setOnMouseClicked(mouseEvent -> {
                    for (int i = 0; i < Database.users.get(v).directs.size(); i++) {
                        if (Database.users.get(v).directs.get(i).second.userName.equals(direct.second.userName)){
                            Database.users.get(v).directs.get(i).seen.replace(Database.users.get(v),true);
                        }
                    }
                    File file = new File("D:\\secondUser");
                    try {
                        if (readFile(new File("D:\\usernameLogin")).equals(direct.first.userName)) {
                            writeFile(file,direct.second.userName,false);
                        }
                        else writeFile(file,direct.first.userName,false);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("directChat.fxml"));
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
            else if (direct.second.equals(Database.users.get(v)) && !Database.users.get(v).blocked.contains(direct.first)){
                Label name2 = new Label(direct.first.userName);
                for (int i = 0; i < Database.users.get(v).directs.size(); i++) {
                    if (Database.users.get(v).directs.get(i).second.userName.equals(direct.second.userName) && !Database.users.get(v).directs.get(i).seen.get(Database.users.get(v))){
                        name2.setUnderline(true);
                    }
                }
                name2.setTextFill(Color.DARKBLUE);
                name2.setFont(Font.font(20));
                GridPane.setHalignment(name2, HPos.RIGHT);
                gridPane.add(name2,0,counter);
                Button insideDirect = new Button("  inside direct");
                insideDirect.setOnMouseClicked(mouseEvent -> {
                    for (int i = 0; i < Database.users.get(v).directs.size(); i++) {
                        if (Database.users.get(v).directs.get(i).second.userName.equals(direct.second.userName)){
                            Database.users.get(v).directs.get(i).seen.replace(Database.users.get(v),true);
                        }
                    }
                    File file = new File("D:\\secondUser");
                    try {
                        if (readFile(new File("D:\\usernameLogin")).equals(direct.first.userName)) {
                            writeFile(file,direct.second.userName,false);
                        }
                        else writeFile(file,direct.first.userName,false);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("directChat.fxml"));
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

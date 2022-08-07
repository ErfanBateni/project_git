package com.example.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static com.example.project.HelloApplication.stage;

public class New_conversation {
    public GridPane gridPane;
    public static String readFile(File file) throws FileNotFoundException {
        StringBuilder text = new StringBuilder("");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            text.append(scanner.nextLine());
        }
        return text.toString();
    }
    boolean directExits = false, isBlocked = false;
    int v = 0,counter=0;


    public void initialize() throws FileNotFoundException {
        for (int i = 0; i < Database.users.size(); i++) {
            if (Database.users.get(i).userName.equals(readFile(new File("D:\\usernameLogin")))) {
                v = i;
                break;
            }
        }
        for (User secondUser : Database.users){
            if (!secondUser.userName.equals(readFile(new File("D:\\usernameLogin")))){
                directExits = false;isBlocked=false;
                for (int i = 0; i < Database.directs.size(); i++) {
                    if ( (Database.users.get(v).userName.equals(Database.directs.get(i).first.userName) && secondUser.userName.equals(Database.directs.get(i).second.userName)) ||
                            (Database.users.get(v).userName.equals(Database.directs.get(i).second.userName) && secondUser.userName.equals(Database.directs.get(i).first.userName)) ) {
                        directExits = true;
                        break;
                    }
                }
                if (secondUser.blocked.contains(Database.users.get(v)) || Database.users.get(v).blocked.contains(secondUser)) {
                    isBlocked = true;
                }
                if (!directExits && !isBlocked && Database.users.get(v).followers.contains(secondUser)){
                    Label name = new Label(secondUser.userName);
                    name.setTextFill(Color.DARKBLUE);
                    GridPane.setHalignment(name, HPos.RIGHT);
                    gridPane.add(name,0,counter);
                    Button newConversation = new Button("new conversation");
                    newConversation.setOnMouseClicked(mouseEvent -> {
                        Direct newDirect = new Direct(secondUser);
                        Label done = new Label("done!    ");
                        done.setTextFill(Color.GREEN);
                        GridPane.setHalignment(done,HPos.RIGHT);
                        gridPane.add(done,1,counter-1);
                    });
                    newConversation.setTextFill(Color.DARKBLUE);
                    gridPane.add(newConversation,0,counter);
                    counter++;
                }
            }
        }
        TextField username = new TextField();
        username.setMaxWidth(245);
        GridPane.setHalignment(username,HPos.RIGHT);
        gridPane.add(username,0,counter);
        Button search = new Button(" search");
        search.setOnMouseClicked(mouseEvent -> {
            for (User secondUser : Database.users){
                if (secondUser.userName.equals(username.getText())){
                    try {
                        if (!secondUser.userName.equals(readFile(new File("D:\\usernameLogin")))){
                            directExits = false;isBlocked=false;
                            for (int i = 0; i < Database.directs.size(); i++) {
                                if ( (Database.users.get(v).userName.equals(Database.directs.get(i).first.userName) && secondUser.userName.equals(Database.directs.get(i).second.userName)) ||
                                        (Database.users.get(v).userName.equals(Database.directs.get(i).second.userName) && secondUser.userName.equals(Database.directs.get(i).first.userName)) ) {
                                    directExits = true;
                                    break;
                                }
                            }
                            if (secondUser.blocked.contains(Database.users.get(v)) || Database.users.get(v).blocked.contains(secondUser)) {
                                isBlocked = true;
                            }
                            if (!directExits && !isBlocked){
                                Direct newDirect = new Direct(secondUser);
                                Label done = new Label("done!    ");
                                done.setTextFill(Color.GREEN);
                                GridPane.setHalignment(done,HPos.RIGHT);
                                gridPane.add(done,1,counter-1);
                            }
                            else {
                                Label tryAgain = new Label("try again    ");
                                tryAgain.setTextFill(Color.RED);
                                GridPane.setHalignment(tryAgain,HPos.RIGHT);
                                gridPane.add(tryAgain,1,counter-1);
                            }
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        });
        gridPane.add(search,0,counter);
        counter++;
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

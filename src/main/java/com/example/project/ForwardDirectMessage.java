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

public class ForwardDirectMessage {
    public static String readFile(File file) throws FileNotFoundException {
        StringBuilder text = new StringBuilder("");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            text.append(scanner.nextLine());
        }
        return text.toString();
    }
    public GridPane gridPane;
    int counter,v;

    public void initialize() throws FileNotFoundException {
        for (int i=0;i<Database.users.size();i++){
            if (Database.users.get(i).userName.equals(readFile(new File("D:\\usernameLogin")))){
                v=i;
                break;
            }
        }
        Label groups = new Label("    groups");
        groups.setFont(Font.font(30));
        groups.setTextFill(Color.DARKBLUE);
        GridPane.setHalignment(groups,HPos.RIGHT);
        gridPane.add(groups,0,0);
        counter=1;
        for (Group group : Database.groups){
            if (group.members.contains(Database.users.get(v))){
                Label groupName = new Label(group.groupName);
                groupName.setFont(Font.font(21));
                groupName.setTextFill(Color.DARKBLUE);
                GridPane.setHalignment(groupName, HPos.RIGHT);
                gridPane.add(groupName,0,counter);
                Button forwardGroup = new Button(" forward");
                forwardGroup.setOnMouseClicked(mouseEvent -> {
                    try {
                        GroupMessage newGroupMessage = new GroupMessage(Database.users.get(v),"(Forwarded) "+readFile(new File("D:\\forwardDirectMessage")),group);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                });
                gridPane.add(forwardGroup,0,counter);
                counter++;
            }
        }
        Label directs = new Label("    directs");
        directs.setFont(Font.font(30));
        directs.setTextFill(Color.DARKBLUE);
        GridPane.setHalignment(directs,HPos.RIGHT);
        gridPane.add(directs,0,counter);
        counter++;
        for (Direct direct : Database.directs){
            Label secondUserName = new Label();
            if (direct.first.userName.equals(readFile(new File("D:\\usernameLogin")))){
                secondUserName.setText(direct.second.userName);
            }
            else if (direct.second.userName.equals(readFile(new File("D:\\usernameLogin")))){
                secondUserName.setText(direct.first.userName);
            }
            secondUserName.setFont(Font.font(20));
            secondUserName.setTextFill(Color.DARKBLUE);
            GridPane.setHalignment(secondUserName, HPos.RIGHT);
            gridPane.add(secondUserName,0,counter);
            Button forwardDirect = new Button(" forward");
            forwardDirect.setOnMouseClicked(mouseEvent -> {
                try {
                    DirectMessage newDirectMessage = new DirectMessage(Database.users.get(v),"(Forwarded) "+readFile(new File("D:\\forwardDirectMessage")),direct);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            });
            gridPane.add(forwardDirect,0,counter);
            counter++;
        }
    }

    @FXML
    Button back;
    public void back(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("directChat.fxml"));
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

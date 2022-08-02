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

public class AddMember {
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
        String groupName = readFile(new File("D:\\groupName"));
        for (Group group : Database.groups){
            if (group.groupName.equals(groupName)){
                for (User user : Database.users){
                    if ( !group.admin.equals(user) && !group.members.contains(user)){
                        Label name = new Label();
                        name.setText(user.userName);
                        name.setTextFill(Color.DARKBLUE);
                        GridPane.setHalignment(name, HPos.RIGHT);
                        gridPane.add(name, 0, counter);
                        Button add = new Button();
                        add.setText("  add");
                        add.setTextFill(Color.DARKBLUE);
                        GridPane.setHalignment(add, HPos.LEFT);
                        gridPane.add(add, 0, counter);
                        add.setOnMouseClicked(mouseEvent -> {
                            if (!group.members.contains(user)){
                                group.members.add(user);
                                user.groups.add(group);
                            }
                        });
                        counter++;
                    }
                }
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




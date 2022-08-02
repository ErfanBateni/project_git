package com.example.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import static com.example.project.HelloApplication.stage;

public class Search_groups {
    public AnchorPane anchorPane;
    public static String readFile(File file) throws FileNotFoundException {
        StringBuilder text = new StringBuilder("");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            text.append(scanner.nextLine());
        }
        return text.toString();
    }


    @FXML
    TextField groupName;

    @FXML
    Button search;
    public void search() throws FileNotFoundException {
        for (Group group : Database.groups){
            if (group.groupName.equals(groupName.getText())){
                Button join = new Button();
                join.setText(" join ");
                join.setTextFill(Color.CADETBLUE);
                join.relocate(144,210);
                anchorPane.getChildren().add(join);
                Label groupName_join = new Label();
                groupName_join.setText(groupName.getText());
                groupName_join.setTextFill(Color.DARKBLUE);
                groupName_join.setFont(Font.font(25));
                groupName_join.relocate(244,200);
                anchorPane.getChildren().add(groupName_join);
                join.setOnMouseClicked(mouseEvent -> {for (User user : Database.users){
                    try {
                        if (user.userName.equals(readFile(new File("D:\\usernameLogin")))){
                            if (!group.members.contains(user) ){
                                group.members.add(user);
                                user.groups.add(group);
                                Label joined = new Label();
                                joined.setText("You are now a member of this group.");
                                joined.setTextFill(Color.LIGHTGREEN);
                                joined.relocate(180,235);
                                anchorPane.getChildren().add(joined);
                            }
                            else {
                                Label warning = new Label();
                                warning.setText("You are already a member of this chat.");
                                warning.setTextFill(Color.RED);
                                warning.relocate(180,235);
                                anchorPane.getChildren().add(warning);
                            }
                        }
                    }
                    catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }});
            }
        }
    }

    @FXML
    Button back;
    public void back(){
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
}

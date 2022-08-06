package com.example.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

public class New_group {
    public GridPane gridPane;
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
    TextField picture;

    @FXML
    Button create;
    public void create() throws FileNotFoundException {
        picture.setText(picture.getText()+".png");
        boolean groupExists = false;
        for (int i = 0; i < Database.groups.size(); i++) {
            if (Database.groups.get(i).groupName.equals(groupName.getText())) {
                groupExists = true;
                break;
            }
        }
        if (!groupExists) {
            for (User user : Database.users){
                if (user.userName.equals(readFile(new File("D:\\usernameLogin")))){
                    Group newGroup = new Group(groupName.getText(),user,picture.getText());
                    break;
                }
            }
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
        else {
            Label warning = new Label();
            warning.setText("This group already exists, Please choose another name for your group.");
            warning.setTextFill(Color.RED);
            gridPane.add(warning,1,2);
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

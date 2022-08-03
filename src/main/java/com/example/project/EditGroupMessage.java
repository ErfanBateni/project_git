package com.example.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static com.example.project.HelloApplication.stage;

public class EditGroupMessage {
    public static String readFile(File file) throws FileNotFoundException {
        StringBuilder text = new StringBuilder("");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            text.append(scanner.nextLine());
        }
        return text.toString();
    }
    int v,u;

    @FXML
    TextField editMessage;

    public void initialize() throws FileNotFoundException {
        editMessage.setText(readFile(new File("D:\\editMessage")));
    }

    @FXML
    Button edit;
    public void edit() throws FileNotFoundException {
        v=0;u=0;
        for (int i = 0; i < Database.groups.size(); i++) {
            if (Database.groups.get(i).groupName.equals(readFile(new File("D:\\groupName")))){
                u=i;
            }
        }
        for (int i = 0; i < Database.groups.get(u).messages.size(); i++) {
            if (Database.groups.get(u).messages.get(i).textMessage.equals(readFile(new File("D:\\editMessage")))){
                Database.groups.get(u).messages.get(i).isEdited = true;
                Database.groups.get(u).messages.get(i).textMessage = editMessage.getText();
                break;
            }
        }
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

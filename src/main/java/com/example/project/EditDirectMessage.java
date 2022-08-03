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

public class EditDirectMessage {
    public static String readFile(File file) throws FileNotFoundException {
        StringBuilder text = new StringBuilder("");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            text.append(scanner.nextLine());
        }
        return text.toString();
    }
    int v,vv;

    @FXML
    TextField editMessage;

    public void initialize() throws FileNotFoundException {
        editMessage.setText(readFile(new File("D:\\editDirectMessage")));
    }

    @FXML
    Button edit;
    public void edit() throws FileNotFoundException {
        v=0;vv=0;
        for (int i = 0; i < Database.users.size(); i++) {
            if (Database.users.get(i).userName.equals(readFile(new File("D:\\usernameLogin")))){
                v=i;
            }
        }
        for (int i = 0; i < Database.users.get(v).directs.size(); i++) {
            if (Database.users.get(v).directs.get(i).second.userName.equals(readFile(new File("D:\\secondUser"))) ||
                    Database.users.get(v).directs.get(i).first.userName.equals(readFile(new File("D:\\secondUser")))){
                for (int j = 0; j < Database.users.get(v).directs.get(i).messages.size(); j++) {
                    if (Database.users.get(v).directs.get(i).messages.get(j).textMessage.equals(readFile(new File("D:\\editDirectMessage")))){
                        Database.users.get(v).directs.get(i).messages.get(j).isEdited = true;
                        Database.users.get(v).directs.get(i).messages.get(j).textMessage = editMessage.getText();
                        break;
                    }
                }
                break;
            }
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

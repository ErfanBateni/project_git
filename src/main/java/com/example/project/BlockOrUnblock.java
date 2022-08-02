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

public class BlockOrUnblock {
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
        for (User user : Database.users.get(v).blocked){
            Label name1 = new Label(user.userName);
            name1.setTextFill(Color.DARKBLUE);
            GridPane.setHalignment(name1, HPos.RIGHT);
            gridPane.add(name1,0,counter);
            Button unblocked = new Button("  unblock");
            unblocked.setOnMouseClicked(mouseEvent -> {
                Database.users.get(v).blocked.remove(user);
            });
            gridPane.add(unblocked,0,counter);
            counter++;
        }
        for (User user : Database.users){
            if (!user.equals(Database.users.get(v)) && !Database.users.get(v).blocked.contains(user)){
                Label name2 = new Label(user.userName);
                name2.setTextFill(Color.DARKBLUE);
                GridPane.setHalignment(name2, HPos.RIGHT);
                gridPane.add(name2,0,counter);
                Button blocked = new Button("  block");
                blocked.setOnMouseClicked(mouseEvent -> {
                    Database.users.get(v).blocked.add(user);
                });
                gridPane.add(blocked,0,counter);
                counter++;
            }
        }
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

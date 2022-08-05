package com.example.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static com.example.project.HelloApplication.stage;

public class Search {
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
    TextField search_message;

    public void initialize(){
        Rectangle rectangle = new Rectangle(0,0,1600,1400);
        rectangle.setFill(Color.LIGHTBLUE);
        gridPane.add(rectangle,0,0);
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
    Button search;
    public void search() throws FileNotFoundException {
        int v = 0,counter=0;
        boolean search = false;
        for (int i = 0; i < Database.users.size(); i++) {
            if (Controller.currentUser.equals(readFile(new File("D:\\usernameLogin")))) {
                v = i;
            }
        }
        gridPane.getChildren().clear();
        Rectangle rectangle = new Rectangle(0,0,1600,1400);
        rectangle.setFill(Color.LIGHTBLUE);
        gridPane.add(rectangle,0,0);
        for (int i = 0; i < Database.users.get(v).directs.size(); i++){
            for (int j = 0; j < Database.users.get(v).directs.get(i).messages.size(); j++) {
                if (Database.users.get(v).directs.get(i).messages.get(j).textMessage.contains(search_message.getText())){
                    Label search_answer = new Label();
                    search_answer.setText("  In the direct:  " + Database.users.get(v).directs.get(i).messages.get(j).textMessage);
                    search_answer.setTextFill(Color.DARKBLUE);
                    search_answer.setFont(Font.font(20));
                    gridPane.add(search_answer,0,counter);
                    counter++;
                    search = true;
                }
            }
        }
        for (int i = 0; i < Database.users.get(v).groups.size(); i++) {
            for (int j = 0; j < Database.users.get(v).groups.get(i).messages.size(); j++) {
                if(Database.users.get(v).groups.get(i).messages.get(j).textMessage.contains(search_message.getText())){
                    Label search_answer = new Label();
                    search_answer.setText("  In the group "+Database.users.get(v).groups.get(i).groupName+" : "+Database.users.get(v).groups.get(i).messages.get(j).textMessage);
                    search_answer.setTextFill(Color.DARKBLUE);
                    search_answer.setFont(Font.font(20));
                    gridPane.add(search_answer,0,counter);
                    counter++;
                    search = true;
                }
            }
        }
        if (!search){
            Label search_answer = new Label();
            search_answer.setText(" No messages could be found in your directs and groups.");
            search_answer.setTextFill(Color.DARKBLUE);
            search_answer.setFont(Font.font(20));
            gridPane.add(search_answer,0,counter);
        }
        else search_message.setText("");
    }

}

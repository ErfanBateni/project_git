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
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static com.example.project.HelloApplication.stage;

public class Store {
    public static String readFile(File file) throws FileNotFoundException {
        StringBuilder text = new StringBuilder("");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            text.append(scanner.nextLine());
        }
        return text.toString();
    }
    int v;
    public GridPane gridPane;
    Label myCredit = new Label();

    @FXML
    TextField chargeNum;

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
    public void initialize() throws FileNotFoundException {
        for (int i = 0; i < Database.users.size(); i++) {
            if (Controller.currentUser.equals(readFile(new File("D:\\usernameLogin")))) {
                v = i;
            }
        }
        myCredit.setText("credit : "+Database.users.get(v).credit);
        myCredit.setFont(Font.font(20));
        if (Database.users.get(v).theme==1){
            myCredit.setTextFill(Color.ORANGERED);
        }
        else if (Database.users.get(v).theme==2){
            myCredit.setTextFill(Color.LIGHTGREEN);
        }
        else if (Database.users.get(v).theme==3){
            myCredit.setTextFill(Color.LIGHTBLUE);
        }
        GridPane.setHalignment(myCredit, HPos.CENTER);
        gridPane.add(myCredit,1,1);
    }

    @FXML
    Button charge;
    public void charge() throws FileNotFoundException {
        gridPane.getChildren().remove(myCredit);
        for (int i = 0; i < Database.users.size(); i++) {
            if (Controller.currentUser.equals(readFile(new File("D:\\usernameLogin")))) {
                v = i;
            }
        }
        Database.users.get(v).credit += Integer.parseInt(chargeNum.getText());
        chargeNum.setText("");
        initialize();
    }
}

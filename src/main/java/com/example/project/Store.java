package com.example.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    Label warning = new Label();

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
        gridPane.add(myCredit,2,1);

        for (int i = 0; i < Database.advertisements.size(); i++) {
            Button buy = new Button("  buy");
            int finalI = i;
            buy.setOnMouseClicked(mouseEvent -> {
                if (Database.users.get(v).credit>=Database.advertisements.get(finalI).productPrice){
                    Database.users.get(v).credit -= Database.advertisements.get(finalI).productPrice*(100-Database.advertisements.get(finalI).discountPercentage)/100;
                    try {
                        initialize();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    warning.setText("don't have enough credit");
                    warning.setFont(Font.font(14));
                    warning.setTextFill(Color.RED);
                    gridPane.add(warning,1,2);
                }
            });
            gridPane.add(buy,0,i+3);

            Label name = new Label(Database.advertisements.get(i).productName);
            name.setFont(Font.font(16));
            name.setTextFill(Color.rgb(0,117,212));
            GridPane.setHalignment(name,HPos.CENTER);
            gridPane.add(name,0,i+3);

            Label priceAfterDiscount = new Label("price after discount : "+Database.advertisements.get(i).productPrice*(100-Database.advertisements.get(i).discountPercentage)/100);
            priceAfterDiscount.setFont(Font.font(18));
            priceAfterDiscount.setTextFill(Color.rgb(160,204,181));
            gridPane.add(priceAfterDiscount,1,i+3);

            if (!Database.advertisements.get(i).picture.equals(".png")){
                Image adImage = new Image(Database.advertisements.get(i).picture);
                ImageView adImage_view = new ImageView(adImage);
                adImage_view.setFitHeight(45);
                adImage_view.setFitWidth(90);
                GridPane.setHalignment(adImage_view,HPos.CENTER);
                gridPane.add(adImage_view,2,i+3);
            }
        }
    }

    @FXML
    Button charge;
    public void charge() throws FileNotFoundException {
        gridPane.getChildren().remove(warning);
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

    @FXML
    Button newAd;
    public void newAd(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("newAd.fxml"));
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

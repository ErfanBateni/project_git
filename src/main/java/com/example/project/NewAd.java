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

public class NewAd {
    public AnchorPane anchorPane;
    boolean haveDiscount = false;
    public static String readFile(File file) throws FileNotFoundException {
        StringBuilder text = new StringBuilder("");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            text.append(scanner.nextLine());
        }
        return text.toString();
    }
    int v;

    @FXML
    TextField name;
    @FXML
    TextField picture;
    @FXML
    TextField price;
    TextField discount = new TextField();
    Label discountLabel = new Label();

    @FXML
    Button create;
    public void create() throws FileNotFoundException {
        for (int i=0;i<Database.users.size();i++){
            if (Database.users.get(i).userName.equals(readFile(new File("D:\\usernameLogin")))){
                v=i;
                break;
            }
        }
        if (haveDiscount){
            Advertisement newAdvertisement = new Advertisement(Database.users.get(v), picture.getText(), name.getText(), Integer.parseInt(price.getText()), true, Integer.parseInt(discount.getText()));
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("store.fxml"));
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
            Advertisement newAdvertisement = new Advertisement(Database.users.get(v), picture.getText(), name.getText(), Integer.parseInt(price.getText()), false, 0);
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("store.fxml"));
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

    @FXML
    Button yes;
    public void yes(){
        haveDiscount = true;
        initialize();
    }
    @FXML
    Button no;
    public void no(){
        haveDiscount = false;
        initialize();
    }

    public void initialize(){
        if (!picture.getText().endsWith(".png")){
            picture.setText(picture.getText()+".png");
        }
        if (haveDiscount){
            discountLabel.setText("discount : ");
            discountLabel.relocate(130,210);
            discountLabel.setTextFill(Color.rgb(0,255,240));
            discountLabel.setFont(Font.font(22));
            anchorPane.getChildren().add(discountLabel);
            discount.relocate(280,210);
            discount.setPromptText("%");
            anchorPane.getChildren().add(discount);
        }
        if (!haveDiscount && discountLabel.getText().equals("discount : ")){
            anchorPane.getChildren().remove(discountLabel);
            anchorPane.getChildren().remove(discount);
        }
    }

    @FXML
    Button back;
    public void back(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("store.fxml"));
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

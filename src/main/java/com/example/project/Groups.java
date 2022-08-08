package com.example.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static com.example.project.HelloApplication.stage;

public class Groups {
    public GridPane gridPane;
    public static String readFile(File file) throws FileNotFoundException {
        StringBuilder text = new StringBuilder("");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            text.append(scanner.nextLine());
        }
        return text.toString();
    }
    public static void writeFile(File file, String text, boolean append) throws IOException {
        FileWriter fileWriter = new FileWriter(file,append);
        fileWriter.write(text);
        fileWriter.close();
    }


    public void initialize() throws FileNotFoundException {
        int counter=0;
        for (User user : Database.users){
            if (user.userName.equals(readFile(new File("D:\\usernameLogin")))){
                for (Group group : user.groups){
                    Button my_group = new Button();
                    my_group.setText("go inside");
                    my_group.setTextFill(Color.DARKBLUE);
                    my_group.setOnMouseClicked(mouseEvent -> {
                        group.seen.replace(user,true);
                        File file = new File("D:\\groupName");
                        try {
                            writeFile(file,group.groupName,false);
                        } catch (IOException e) {
                            e.printStackTrace();
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
                    });
                    Label groupName = new Label();
                    if (!group.seen.get(user)){
                        groupName.setUnderline(true);
                    }
                    groupName.setText(user.groups.get(counter).groupName);
                    groupName.setTextFill(Color.DARKBLUE);
                    groupName.setFont(Font.font(25));
                    gridPane.add(my_group,0,counter);
                    gridPane.add(groupName,1,counter);
                    gridPane.setAlignment(Pos.BOTTOM_LEFT);
                    counter++;
                }
            }
        }
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
    public void search(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("search_groups.fxml"));
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
    Button new_group;
    public void new_group(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("new_group.fxml"));
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

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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import static com.example.project.HelloApplication.stage;

public class Who_commented {
    public GridPane gridPane;
    public static String readFile(File file) throws FileNotFoundException {
        StringBuilder text = new StringBuilder("");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            text.append(scanner.nextLine());
        }
        return text.toString();
    }
    int v,u,counter;

    @FXML
    TextField message;

    @FXML
    Button send;
    public void send() throws FileNotFoundException {
        v=0;u=0;
        for (int i=0;i<Database.users.size();i++){
            if (Database.users.get(i).userName.equals(readFile(new File("D:\\usernameLogin")))){
                v=i;
                break;
            }
        }
        boolean postExists = false;
        for (int i = 0; i < Database.posts.size(); i++) {
            if (Database.posts.get(i).textMessage.equals(readFile(new File("D:\\postTextMessage")))){
                postExists = true;
                u=i;
            }
        }
        if (postExists) {
            CommentPost newComment = new CommentPost(Database.users.get(v), message.getText(), Database.posts.get(u));
        }
        message.setText("");
        initialize();
    }

    @FXML
    Button back;
    public void back(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("posts.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() throws FileNotFoundException {
        v=0;u=0;
        for (int i=0;i<Database.users.size();i++){
            if (Database.users.get(i).userName.equals(readFile(new File("D:\\usernameLogin")))){
                v=i;
                break;
            }
        }
        for (int i = 0; i < Database.posts.size(); i++) {
            if (Database.posts.get(i).textMessage.equals(readFile(new File("D:\\postTextMessage")))){
                u=i;
            }
        }
        gridPane.getChildren().clear();
        Rectangle rectangle = new Rectangle(0,0,1600,1400);
        rectangle.setFill(Color.LIGHTBLUE);
        gridPane.add(rectangle,0,0);
        counter=6;
        if (Database.posts.get(u).replies.size()>7){
            for (int i=Database.posts.get(u).replies.size()-1; i>=Database.posts.get(u).replies.size()-7; i--) {
                if (counter>=0 && Database.posts.get(u).replies.get(i).sender.userName.equals(readFile(new File("D:\\usernameLogin")))){
                    Button reply = new Button("reply");
                    int final1 = i;
                    reply.setOnMouseClicked(mouseEvent -> {
                        Database.posts.get(u).replies.get(Database.posts.get(u).replies.size() - 1).textMessage = Database.posts.get(u).replies.get(Database.posts.get(u).replies.size() - 1).textMessage + " (replied to " + Database.posts.get(u).replies.get(final1).textMessage + ")";
                        try {
                            initialize();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    });
                    gridPane.add(reply, 0, counter);

                    Label myMessage = new Label();
                    myMessage.setText(Database.posts.get(u).replies.get(i).textMessage+"  ");
                    GridPane.setHalignment(myMessage, HPos.RIGHT);
                    if (myMessage.getText().length()>15){
                        myMessage.setFont(Font.font(13));
                    }
                    else {
                        myMessage.setFont(Font.font(18));
                    }
                    gridPane.add(myMessage,1,counter);

                    counter--;
                }
                else if (counter>=0){
                    Button reply = new Button("reply ");
                    int final2 = i;
                    reply.setOnMouseClicked(mouseEvent -> {
                        Database.posts.get(u).replies.get(Database.posts.get(u).replies.size() - 1).textMessage = Database.posts.get(u).replies.get(Database.posts.get(u).replies.size() - 1).textMessage + " (replied to " + Database.posts.get(u).replies.get(final2).textMessage + ")";
                        try {
                            initialize();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    });
                    GridPane.setHalignment(reply,HPos.RIGHT);
                    gridPane.add(reply, 1, counter);

                    Label myMessage = new Label();
                    myMessage.setText("  "+Database.posts.get(u).replies.get(i).sender.userName+" : "+Database.posts.get(u).replies.get(i).textMessage);
                    myMessage.setFont(Font.font(18));
                    gridPane.add(myMessage,0,counter);

                    counter--;
                }
            }
        }
        else {
            for (int i=Database.posts.get(u).replies.size()-1; i>=0; i--){
                if (counter>=0 && Database.posts.get(u).replies.get(i).sender.userName.equals(readFile(new File("D:\\usernameLogin")))){
                    Button reply = new Button("reply");
                    int final3 = i;
                    reply.setOnMouseClicked(mouseEvent -> {
                        Database.posts.get(u).replies.get(Database.posts.get(u).replies.size() - 1).textMessage = Database.posts.get(u).replies.get(Database.posts.get(u).replies.size() - 1).textMessage + " (replied to " + Database.posts.get(u).replies.get(final3).textMessage + ")";
                        try {
                            initialize();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    });
                    gridPane.add(reply, 0, counter);

                    Label myMessage = new Label();
                    myMessage.setText(Database.posts.get(u).replies.get(i).textMessage+"  ");
                    GridPane.setHalignment(myMessage, HPos.RIGHT);
                    if (myMessage.getText().length()>15){
                        myMessage.setFont(Font.font(13));
                    }
                    else {
                        myMessage.setFont(Font.font(18));
                    }
                    gridPane.add(myMessage,1,counter);

                    counter--;
                }
                else if (counter>=0){
                    Button reply = new Button("reply ");
                    int final4 = i;
                    reply.setOnMouseClicked(mouseEvent -> {
                        Database.posts.get(u).replies.get(Database.posts.get(u).replies.size() - 1).textMessage = Database.posts.get(u).replies.get(Database.posts.get(u).replies.size() - 1).textMessage + " (replied to " + Database.posts.get(u).replies.get(final4).textMessage + ")";
                        try {
                            initialize();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    });
                    GridPane.setHalignment(reply,HPos.RIGHT);
                    gridPane.add(reply, 1, counter);

                    Label myMessage = new Label();
                    myMessage.setText("  "+Database.posts.get(u).replies.get(i).sender.userName+" : "+Database.posts.get(u).replies.get(i).textMessage);
                    myMessage.setFont(Font.font(18));
                    gridPane.add(myMessage,0,counter);

                    counter--;
                }
            }
        }
    }
}

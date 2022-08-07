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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static com.example.project.HelloApplication.stage;

public class DirectChat {
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
    int v,vv,u,counter;

    public void initialize() throws FileNotFoundException {
        v=0;vv=0;u=0;
        for (int i=0;i<Database.users.size();i++){
            if (Database.users.get(i).userName.equals(readFile(new File("D:\\usernameLogin")))){
                v=i;
                break;
            }
        }
        for (int i=0;i<Database.users.size();i++){
            if (Database.users.get(i).userName.equals(readFile(new File("D:\\secondUser")))){
                vv=i;
                break;
            }
        }
        for (int i = 0; i < Database.directs.size(); i++) {
            if ( (Database.users.get(v).userName.equals(Database.directs.get(i).first.userName) && Database.users.get(vv).userName.equals(Database.directs.get(i).second.userName)) ||
                    (Database.users.get(v).userName.equals(Database.directs.get(i).second.userName) && Database.users.get(vv).userName.equals(Database.directs.get(i).first.userName)) ) {
                u=i;
            }
        }

        gridPane.getChildren().clear();
        Rectangle rectangle = new Rectangle(0,0,1600,1400);
        rectangle.setFill(Color.LIGHTBLUE);
        gridPane.add(rectangle,0,0);
        counter=9;
        if (Database.directs.get(u).messages.size()>10){
            for (int i=Database.directs.get(u).messages.size()-1; i>=Database.directs.get(u).messages.size()-10; i--){
                if (counter>=0 && Database.directs.get(u).messages.get(i).sender.userName.equals(readFile(new File("D:\\usernameLogin")))){

                    Button edit = new Button("edit");
                    int finalI = i;
                    edit.setOnMouseClicked(mouseEvent -> {
                        File file = new File("D:\\editDirectMessage");
                        try {
                            writeFile(file,Database.directs.get(u).messages.get(finalI).textMessage,false);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("editDirectMessage.fxml"));
                        Scene scene = null;
                        try {
                            scene = new Scene(fxmlLoader.load(), 600, 400);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        stage.setScene(scene);
                        stage.show();
                    });
                    gridPane.add(edit,0,counter);

                    Button delete = new Button("delete");
                    int finalI1 = i;
                    delete.setOnMouseClicked(mouseEvent -> {
                        Database.directs.get(u).messages.get(finalI1).isDeleted = true;
                        Database.directs.get(u).messages.get(finalI1).isEdited = false;
                        for (Message message : Database.directs.get(u).messages){
                            if (!message.textMessage.equals(Database.directs.get(u).messages.get(finalI1).textMessage) && message.textMessage.contains(Database.directs.get(u).messages.get(finalI1).textMessage)){
                                message.textMessage = message.textMessage.replaceAll(Database.directs.get(u).messages.get(finalI1).textMessage,"Deleted message");
                            }
                        }
                        try {
                            initialize();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    });
                    GridPane.setHalignment(delete,HPos.CENTER);
                    gridPane.add(delete,0,counter);

                    if (!Database.directs.get(u).messages.get(i).isDeleted){
                        Button reply = new Button("reply");
                        int finalI2 = i;
                        reply.setOnMouseClicked(mouseEvent -> {
                            Database.directs.get(u).messages.get(Database.directs.get(u).messages.size()-1).textMessage = Database.directs.get(u).messages.get(Database.directs.get(u).messages.size()-1).textMessage+" (replied to "+Database.directs.get(u).messages.get(finalI2).textMessage+")";
                            try {
                                initialize();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        });
                        GridPane.setHalignment(reply,HPos.RIGHT);
                        gridPane.add(reply,0,counter);
                    }

                    if (!Database.directs.get(u).messages.get(i).isDeleted){
                        Button forward = new Button("forward");
                        forward.setOnMouseClicked(mouseEvent -> {
                            File file = new File("D:\\forwardDirectMessage");
                            try {
                                writeFile(file,Database.directs.get(u).messages.get(finalI).textMessage,false);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("forwardDirectMessage.fxml"));
                            Scene scene = null;
                            try {
                                scene = new Scene(fxmlLoader.load(), 600, 400);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            stage.setScene(scene);
                            stage.show();
                        });
                        GridPane.setHalignment(forward,HPos.LEFT);
                        gridPane.add(forward,1,counter);
                    }

                    if (Database.directs.get(u).messages.get(i).textMessage.endsWith(".png")){
                        Image messageImage = new Image(Database.directs.get(u).messages.get(i).textMessage);
                        ImageView messageImage_view = new ImageView(messageImage);
                        messageImage_view.setFitWidth(100);
                        messageImage_view.setFitHeight(65);
                        GridPane.setHalignment(messageImage_view,HPos.CENTER);
                        gridPane.add(messageImage_view,1,counter);
                    }
                    else {
                        Label myMessage = new Label();
                        if (Database.directs.get(u).messages.get(i).isEdited){
                            myMessage.setText("edited - "+Database.directs.get(u).messages.get(i).textMessage+"  ");
                        }
                        else if (Database.directs.get(u).messages.get(i).isDeleted){
                            myMessage.setText("Deleted message  ");
                        }
                        else {
                            myMessage.setText(Database.directs.get(u).messages.get(i).textMessage+"  ");
                        }
                        GridPane.setHalignment(myMessage, HPos.RIGHT);
                        myMessage.setFont(Font.font(18));
                        gridPane.add(myMessage,1,counter);
                    }

                    counter--;
                }
                else if (counter>=0){
                    if (!Database.directs.get(u).messages.get(i).isDeleted) {
                        Button reply = new Button("reply");
                        int finalI3 = i;
                        reply.setOnMouseClicked(mouseEvent -> {
                            Database.directs.get(u).messages.get(Database.directs.get(u).messages.size() - 1).textMessage = Database.directs.get(u).messages.get(Database.directs.get(u).messages.size() - 1).textMessage + " (replied to " + Database.directs.get(u).messages.get(finalI3).textMessage + ")";
                            try {
                                initialize();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        });
                        GridPane.setHalignment(reply, HPos.RIGHT);
                        gridPane.add(reply, 1, counter);
                    }

                    if (Database.directs.get(u).messages.get(i).textMessage.endsWith(".png")){
                        Image messageImage = new Image(Database.directs.get(u).messages.get(i).textMessage);
                        ImageView messageImage_view = new ImageView(messageImage);
                        messageImage_view.setFitWidth(100);
                        messageImage_view.setFitHeight(65);
                        gridPane.add(messageImage_view,0,counter);
                    }
                    else {
                        Label yourMessage = new Label();
                        if (Database.directs.get(u).messages.get(i).isEdited){
                            yourMessage.setText("  edited - "+Database.directs.get(u).messages.get(i).textMessage+"  ");
                        }
                        else if (Database.directs.get(u).messages.get(i).isDeleted){
                            yourMessage.setText("  Deleted message");
                        }
                        else {
                            yourMessage.setText("  "+Database.directs.get(u).messages.get(i).textMessage+"  ");
                        }
                        yourMessage.setFont(Font.font(18));
                        gridPane.add(yourMessage,0,counter);
                    }
                    counter--;
                }
            }
        }
        else {
            for (int i=Database.directs.get(u).messages.size()-1; i>=0; i--){
                if (counter>=0 && Database.directs.get(u).messages.get(i).sender.userName.equals(readFile(new File("D:\\usernameLogin")))){

                    if (Database.directs.get(u).messages.get(i).textMessage.endsWith(".png")){
                        Image messageImage = new Image(Database.directs.get(u).messages.get(i).textMessage);
                        ImageView messageImage_view = new ImageView(messageImage);
                        messageImage_view.setFitWidth(100);
                        messageImage_view.setFitHeight(65);
                        GridPane.setHalignment(messageImage_view,HPos.CENTER);
                        gridPane.add(messageImage_view,1,counter);
                    }
                    else {
                        Label myMessage = new Label();
                        if (Database.directs.get(u).messages.get(i).isEdited){
                            myMessage.setText("edited - "+Database.directs.get(u).messages.get(i).textMessage+"  ");
                        }
                        else if (Database.directs.get(u).messages.get(i).isDeleted){
                            myMessage.setText("Deleted message  ");
                        }
                        else {
                            myMessage.setText(Database.directs.get(u).messages.get(i).textMessage+"  ");
                        }
                        GridPane.setHalignment(myMessage, HPos.RIGHT);
                        myMessage.setFont(Font.font(18));
                        gridPane.add(myMessage,1,counter);
                    }

                    Button edit = new Button("edit");
                    int finalI = i;
                    edit.setOnMouseClicked(mouseEvent -> {
                        File file = new File("D:\\editDirectMessage");
                        try {
                            writeFile(file,Database.directs.get(u).messages.get(finalI).textMessage,false);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("editDirectMessage.fxml"));
                        Scene scene = null;
                        try {
                            scene = new Scene(fxmlLoader.load(), 600, 400);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        stage.setScene(scene);
                        stage.show();
                    });
                    gridPane.add(edit,0,counter);

                    Button delete = new Button("delete");
                    int finalI1 = i;
                    delete.setOnMouseClicked(mouseEvent -> {
                        Database.directs.get(u).messages.get(finalI1).isDeleted = true;
                        Database.directs.get(u).messages.get(finalI1).isEdited = false;
                        for (Message message : Database.directs.get(u).messages){
                            if (!message.textMessage.equals(Database.directs.get(u).messages.get(finalI1).textMessage) && message.textMessage.contains(Database.directs.get(u).messages.get(finalI1).textMessage)){
                                message.textMessage = message.textMessage.replaceAll(Database.directs.get(u).messages.get(finalI1).textMessage,"Deleted message");
                            }
                        }
                        try {
                            initialize();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    });
                    GridPane.setHalignment(delete,HPos.CENTER);
                    gridPane.add(delete,0,counter);

                    if (!Database.directs.get(u).messages.get(i).isDeleted) {
                        Button reply = new Button("reply");
                        int finalI2 = i;
                        reply.setOnMouseClicked(mouseEvent -> {
                            Database.directs.get(u).messages.get(Database.directs.get(u).messages.size() - 1).textMessage = Database.directs.get(u).messages.get(Database.directs.get(u).messages.size() - 1).textMessage + " (replied to " + Database.directs.get(u).messages.get(finalI2).textMessage + ")";
                            try {
                                initialize();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        });
                        GridPane.setHalignment(reply, HPos.RIGHT);
                        gridPane.add(reply, 0, counter);
                    }

                    if (!Database.directs.get(u).messages.get(i).isDeleted){
                        Button forward = new Button("forward");
                        forward.setOnMouseClicked(mouseEvent -> {
                            File file = new File("D:\\forwardDirectMessage");
                            try {
                                writeFile(file,Database.directs.get(u).messages.get(finalI).textMessage,false);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("forwardDirectMessage.fxml"));
                            Scene scene = null;
                            try {
                                scene = new Scene(fxmlLoader.load(), 600, 400);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            stage.setScene(scene);
                            stage.show();
                        });
                        GridPane.setHalignment(forward,HPos.LEFT);
                        gridPane.add(forward,1,counter);
                    }

                    counter--;
                }
                else if (counter>=0){
                    if (!Database.directs.get(u).messages.get(i).isDeleted) {
                        Button reply = new Button("reply");
                        int finalI3 = i;
                        reply.setOnMouseClicked(mouseEvent -> {
                            Database.directs.get(u).messages.get(Database.directs.get(u).messages.size() - 1).textMessage = Database.directs.get(u).messages.get(Database.directs.get(u).messages.size() - 1).textMessage + " (replied to " + Database.directs.get(u).messages.get(finalI3).textMessage + ")";
                            try {
                                initialize();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        });
                        GridPane.setHalignment(reply, HPos.RIGHT);
                        gridPane.add(reply, 1, counter);
                    }

                    if (Database.directs.get(u).messages.get(i).textMessage.endsWith(".png")){
                        Image messageImage = new Image(Database.directs.get(u).messages.get(i).textMessage);
                        ImageView messageImage_view = new ImageView(messageImage);
                        messageImage_view.setFitWidth(100);
                        messageImage_view.setFitHeight(65);
                        gridPane.add(messageImage_view,0,counter);
                    }
                    else {
                        Label yourMessage = new Label();
                        if (Database.directs.get(u).messages.get(i).isEdited){
                            yourMessage.setText("  edited - "+Database.directs.get(u).messages.get(i).textMessage+"  ");
                        }
                        else if (Database.directs.get(u).messages.get(i).isDeleted){
                            yourMessage.setText("  Deleted message");
                        }
                        else {
                            yourMessage.setText("  "+Database.directs.get(u).messages.get(i).textMessage+"  ");
                        }
                        yourMessage.setFont(Font.font(18));
                        gridPane.add(yourMessage,0,counter);
                    }

                    counter--;
                }
            }
        }
    }

    @FXML
    TextField message;

    @FXML
    Button send;
    public void send() throws FileNotFoundException {
        v=0;vv=0;u=0;
        for (int i=0;i<Database.users.size();i++){
            if (Database.users.get(i).userName.equals(readFile(new File("D:\\usernameLogin")))){
                v=i;
                break;
            }
        }
        for (int i=0;i<Database.users.size();i++){
            if (Database.users.get(i).userName.equals(readFile(new File("D:\\secondUser")))){
                vv=i;
                break;
            }
        }
        boolean directExits = false;
        for (int i = 0; i < Database.directs.size(); i++) {
            if ( (Database.users.get(v).userName.equals(Database.directs.get(i).first.userName) && Database.users.get(vv).userName.equals(Database.directs.get(i).second.userName)) ||
                    (Database.users.get(v).userName.equals(Database.directs.get(i).second.userName) && Database.users.get(vv).userName.equals(Database.directs.get(i).first.userName)) ) {
                directExits = true;
                u=i;
            }
        }
        if (directExits && !message.getText().equals("") && !message.getText().equals(" ") && !message.getText().equals("  ") && !message.getText().equals("   ") && !message.getText().equals("    ") && !message.getText().equals("     ") && !message.getText().equals("      ") && !message.getText().equals("       ") && !message.getText().equals("        ") && !message.getText().equals("         ") && !message.getText().equals("          ") && !message.getText().equals("           ") && !message.getText().equals("            ") && !message.getText().equals("             ") && !message.getText().equals("              ") && !message.getText().equals("               ") && !message.getText().equals("                ") && !message.getText().equals("                 ") && !message.getText().equals("                  ") && !message.getText().equals("                   ") && !message.getText().equals("                    ") && !message.getText().equals("                     ") && !message.getText().equals("                      ") && !message.getText().equals("                       ") && !message.getText().equals("                        ") && !message.getText().equals("                         ") && !message.getText().equals("                          ") && !message.getText().equals("                           ") && !message.getText().equals("                            ") && !message.getText().equals("                             ") && !message.getText().equals("                              ")) {
            DirectMessage newDirectMessage = new DirectMessage(Database.users.get(v), message.getText(), Database.directs.get(u));
        }
        message.setText("");
        initialize();
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

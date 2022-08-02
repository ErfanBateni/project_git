package com.example.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static com.example.project.HelloApplication.stage;

public class New_password {
    public static String readFile(File file) throws FileNotFoundException {
        StringBuilder text = new StringBuilder("");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            text.append(scanner.nextLine());
        }
        return text.toString();
    }
    public AnchorPane anchorPane;

    @FXML
    TextField new_password;

    @FXML
    Button done;
    public void done() throws FileNotFoundException {
        String username = readFile(new File("D:\\forgetPassword"));
        int v = 0;
        for (int i = 0; i < Database.users.size(); i++) {
            if (Database.users.get(i).userName.equals(username)) {
                v = i;
                break;
            }
        }
        if (new_password.getLength()<8){
            Label warning = new Label();
            warning.setText("Your password should have at least 8 characters. Please try again.");
            warning.setTextFill(Color.RED);
            warning.relocate(140,180);
            anchorPane.getChildren().add(warning);
        }
        else {
            Database.users.get(v).password = new_password.getText();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("first_menu.fxml"));
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

}

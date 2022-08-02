package com.example.project;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.io.IOException;
import static com.example.project.HelloApplication.stage;

public class HelloController {
    public Pane pane;
    Image twitter_logo = new Image("twitter.png");
    ImageView twitter_logo_view = new ImageView(twitter_logo);

    public void initialize(){
        twitter_logo_view.setX(220);
        twitter_logo_view.setY(120);
        twitter_logo_view.setFitHeight(150);
        twitter_logo_view.setFitWidth(180);
        pane.getChildren().add(twitter_logo_view);
        pane.setOnMouseClicked(mouseEvent -> {
            next_page();
        });
    }
    public void next_page(){
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
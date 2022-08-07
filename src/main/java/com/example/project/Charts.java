package com.example.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static com.example.project.HelloApplication.stage;

public class Charts {
    public AnchorPane anchorPane;
    public static String readFile(File file) throws FileNotFoundException {
        StringBuilder text = new StringBuilder("");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            text.append(scanner.nextLine());
        }
        return text.toString();
    }
    int u;

    public void initialize() throws FileNotFoundException {
        for (int i=0;i<Database.posts.size();i++){
            if (Database.posts.get(i).textMessage.equals(readFile(new File("D:\\postTextMessage")))){
                u=i;
                break;
            }
        }
        Rectangle rectangle = new Rectangle();
        rectangle.setFill(Color.LIGHTBLUE);
        rectangle.setWidth(600);
        rectangle.setHeight(400);
        anchorPane.getChildren().add(rectangle);

        int m=0,max=0;
        for (int i = 0; i < Database.posts.get(u).seenTime.size(); i++) {
            if (i==0) m++;
            if (i==Database.posts.get(u).seenTime.size()-1) m++;
            else if (Database.posts.get(u).seenTime.get(i).equals(Database.posts.get(u).seenTime.get(i+1))){
                m++;
            }
            if (m>=max) max=m;
            if (i!=Database.posts.get(u).seenTime.size()-1 && !Database.posts.get(u).seenTime.get(i).equals(Database.posts.get(u).seenTime.get(i+1))){
                m=0;
            }
        }
        if (max==0){
            Label viewIsZero = new Label("view = 0");
            viewIsZero.setFont(Font.font(16));
            viewIsZero.relocate(100,150);
            anchorPane.getChildren().add(viewIsZero);
        }
        else {
            NumberAxis xAxisSeen = new NumberAxis(0,Integer.parseInt(Database.posts.get(u).seenTime.get(Database.posts.get(u).seenTime.size()-1))-Integer.parseInt(Database.posts.get(u).seenTime.get(0)),1);
            xAxisSeen.setLabel("Days");
            NumberAxis yAxisSeen = new NumberAxis(0, max-1, 1);
            yAxisSeen.setLabel("Number of views");
            LineChart lineChartSeen = new LineChart(xAxisSeen, yAxisSeen);
            XYChart.Series seriesSeen = new XYChart.Series();
            seriesSeen.setName("View");
            for (int i = 0; i < Database.posts.get(u).seenTime.size(); i++) {
                if (i==0) m++;
                if (i==Database.posts.get(u).seenTime.size()-1) m++;
                else if (Database.posts.get(u).seenTime.get(i).equals(Database.posts.get(u).seenTime.get(i+1))){
                    m++;
                }
                if (m>=max) max=m;
                if (i!=Database.posts.get(u).seenTime.size()-1 && !Database.posts.get(u).seenTime.get(i).equals(Database.posts.get(u).seenTime.get(i+1))){
                    seriesSeen.getData().add(new XYChart.Data(Integer.parseInt(Database.posts.get(u).seenTime.get(i+1))-Integer.parseInt(Database.posts.get(u).seenTime.get(0)), m));
                    m=0;
                }
            }
            lineChartSeen.getData().add(seriesSeen);
            lineChartSeen.setPrefWidth(290);
            lineChartSeen.setPrefHeight(300);
            anchorPane.getChildren().add(lineChartSeen);
        }

        m=0;max=0;
        for (int i = 0; i < Database.posts.get(u).likeTime.size(); i++) {
            if (i==0) m++;
            if (i==Database.posts.get(u).likeTime.size()-1) m++;
            else if (Database.posts.get(u).likeTime.get(i).equals(Database.posts.get(u).likeTime.get(i+1))){
                m++;
            }
            if (m>=max) max=m;
            if (i!=Database.posts.get(u).likeTime.size()-1 && !Database.posts.get(u).likeTime.get(i).equals(Database.posts.get(u).likeTime.get(i+1))){
                m=0;
            }
        }
        if (max==0){
            Label likeIsZero = new Label("like = 0");
            likeIsZero.setFont(Font.font(16));
            likeIsZero.relocate(400,150);
            anchorPane.getChildren().add(likeIsZero);
        }
        else {
            NumberAxis xAxisLike = new NumberAxis(0,Integer.parseInt(Database.posts.get(u).likeTime.get(Database.posts.get(u).likeTime.size()-1))-Integer.parseInt(Database.posts.get(u).likeTime.get(0)),1);
            xAxisLike.setLabel("Days");
            NumberAxis yAxisLike = new NumberAxis(0,max-1,1);
            yAxisLike.setLabel("Number of likes");
            LineChart lineChartLike = new LineChart(xAxisLike, yAxisLike);
            XYChart.Series seriesLike = new XYChart.Series();
            seriesLike.setName("Like");
            for (int i = 0; i < Database.posts.get(u).likeTime.size(); i++) {
                if (i==0) m++;
                if (i==Database.posts.get(u).likeTime.size()-1) m++;
                else if (Database.posts.get(u).likeTime.get(i).equals(Database.posts.get(u).likeTime.get(i+1))){
                    m++;
                }
                if (m>=max) max=m;
                if (i!=Database.posts.get(u).likeTime.size()-1 && !Database.posts.get(u).likeTime.get(i).equals(Database.posts.get(u).likeTime.get(i+1))){
                    seriesLike.getData().add(new XYChart.Data(Integer.parseInt(Database.posts.get(u).likeTime.get(i+1))-Integer.parseInt(Database.posts.get(u).likeTime.get(0)), m));
                    m=0;
                }
            }
            lineChartLike.getData().add(seriesLike);
            lineChartLike.setPrefWidth(290);
            lineChartLike.setPrefHeight(300);
            lineChartLike.relocate(300,0);
            anchorPane.getChildren().add(lineChartLike);
        }

        Button back = new Button("back");
        back.setOnMouseClicked(mouseEvent -> {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("posts.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 600, 400);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setScene(scene);
            stage.show();
        });
        back.setFont(Font.font(18));
        back.relocate(510,355);
        back.setPrefHeight(35);
        back.setPrefWidth(80);
        anchorPane.getChildren().add(back);
    }

}

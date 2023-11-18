package com.example.pachero;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

//HELLO SOMAY
//THIS IS PANKHURI

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Start_page.fxml"));
        Duration duration = Duration.seconds(3);
        Timeline timeline=new Timeline(new KeyFrame(duration,event -> dot_animation()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        Scene scene = new Scene(fxmlLoader.load(), 1080, 567);
        stage.setTitle("Pac-Hero!");
        stage.setScene(scene);
        stage.show();
    }

    public void dot_animation(){
        start_page_controller.dot_animation();
    }
    public static void main(String[] args) {
        launch();
    }
}
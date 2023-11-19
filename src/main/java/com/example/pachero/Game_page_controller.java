package com.example.pachero;

import javafx.animation.FadeTransition;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.ResponseCache;
import java.net.URL;
import java.util.ResourceBundle;

public class Game_page_controller implements Initializable {
    public ImageView blackscreen;
    public AnchorPane gamePane;
    public Text score;
    public Text lives;
    public Text cherries;
    public ImageView pacman_lives;
    public ImageView pacman_cherries;
    public Rectangle cur_rectangle;
    public Rectangle next_rectangle;
    public Rectangle perfect;

    public Game_Logic gameLogic;
    public Platform cur_platform;
    public Platform next_platform;
    public ImageView pacman;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gamePane.setOpacity(0);
        fadeintransition();
        cur_platform=new Platform(cur_rectangle);
        next_platform=new Platform(next_rectangle);
    }

    private void fadeintransition() {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), gamePane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }


}

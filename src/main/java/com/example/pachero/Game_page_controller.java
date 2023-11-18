package com.example.pachero;

import javafx.animation.FadeTransition;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

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
    public Rectangle cur_platform;
    public Rectangle next_platform;
    public Rectangle perfect;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gamePane.setOpacity(0);
        fadeintransition();
    }

    private void fadeintransition() {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), gamePane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }


}

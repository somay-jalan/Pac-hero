package com.example.pachero;
import javafx.animation.*;
import javafx.fxml.Initializable;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
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
    public Rectangle cur_rectangle;
    public Rectangle next_rectangle;
    public Rectangle perfect_rectangle;

    public static Game_Logic gameLogic;
    public Platform cur_platform;
    public Platform next_platform;
    public Perfect perfect;
    public ImageView pacman_image;
    public Line pacman_stick;
    public Pacman pacman;

    public Pacman_Stick pacmanStick;

    public Scene scene;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gamePane.setOpacity(0);
        fadeintransition();
        cur_platform=new Platform(cur_rectangle);
        next_platform=new Platform(next_rectangle);
        perfect=new Perfect(perfect_rectangle);
        pacman=new Pacman( new Costume(pacman_image));
        pacmanStick=new Pacman_Stick(pacman_stick);
        gameLogic=new Game_Logic(pacman,cur_platform,next_platform,perfect,pacmanStick,gamePane,score);
        pacman.setGameLogic(gameLogic);
        pacmanStick.setGameLogic(gameLogic);
        perfect.setGameLogic(gameLogic);
        perfect.setAnchorPane(gamePane);
        StartGame();
    }

    private void fadeintransition() {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), gamePane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    private void StartGame(){
        gameLogic.GameStart();
    }







}





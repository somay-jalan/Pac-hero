package com.example.pachero;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Game_page_controller implements Initializable {
    public ImageView blackscreen;
    public AnchorPane gamePane;
    public Text score;
    public Text cherries;
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
    public ImageView pacman_lives_3;
    public ImageView pacman_lives_2;
    public ImageView pacman_lives_1;
    public AnchorPane backPage;
    public AnchorPane gameOverMenu;
    public Text highest;
    public Text resurrect;
    public Rectangle restart_button;
    public Rectangle exit_button;
    public Group resurrect_group;
    public ImageView score_cup;
    public Text resurrect_text;
    public Button resurrect_button;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gamePane.setOpacity(0);
        cur_platform=new Platform(cur_rectangle);
        next_platform=new Platform(next_rectangle);
        perfect=new Perfect(perfect_rectangle);
        pacman=new Pacman( new Costume(pacman_image));
        pacmanStick=new Pacman_Stick(pacman_stick);
        gameLogic=new Game_Logic(pacman,cur_platform,next_platform,perfect,pacmanStick,gamePane,score,cherries, gameOverMenu);
        pacman.setGameLogic(gameLogic);
        pacmanStick.setGameLogic(gameLogic);
        perfect.setGameLogic(gameLogic);
        perfect.setAnchorPane(gamePane);
        fadeInTransition();
    }

    private void fadeInTransition() {
        gameLogic.setStopKeyboard(1);
        Text rules=new Text();
        rules.getStyleClass().add("pixeboy");
        rules.setFill(Color.RED);
        rules.setLayoutY(220);
        rules.setFont(new Font(50));
        rules.setStrokeType(StrokeType.INSIDE);
        rules.setStrokeWidth(100);
        rules.setOpacity(0);
        rules.setTextAlignment(TextAlignment.CENTER);
        Timeline timeBackPane=new Timeline(
            new KeyFrame(Duration.ZERO,new KeyValue(backPage.opacityProperty(),0)),
            new KeyFrame(Duration.millis(500),new KeyValue(backPage.opacityProperty(),1))
        );
        timeBackPane.play();
        backPage.getChildren().add(rules);
        rules.toFront();
        rules.setLayoutX(150);

//        rules.setText("*PRESS SPACE TO INCREASE STICK LENGTH*\n*PRESS UP/DOWN KEYS TO NAVIGATE*\n *PRESS ESC KEY TO PAUSE*") ;
        Timeline timelineRules1=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(rules.opacityProperty(),0)),
                new KeyFrame(Duration.millis(100),new KeyValue(rules.opacityProperty(),1)),
                new KeyFrame(Duration.millis(2100),new KeyValue(rules.opacityProperty(),1)),
                new KeyFrame(Duration.millis(2200),new KeyValue(rules.opacityProperty(),0))
        );
        timelineRules1.play();
//        timelineRules1.setOnFinished(this::StartGame);

        rules.setText("*PRESS SPACE TO INCREASE STICK LENGTH*");
        timelineRules1.setOnFinished(event -> {
            rules.setText("*PRESS UP/DOWN KEYS TO NAVIGATE*");
            rules.setLayoutX(540-(rules.getBoundsInParent().getMaxX()-rules.getBoundsInParent().getMinX())/2);
        });
        Timeline timelineRules2=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(rules.opacityProperty(),0)),
                new KeyFrame(Duration.millis(100),new KeyValue(rules.opacityProperty(),1)),
                new KeyFrame(Duration.millis(2100),new KeyValue(rules.opacityProperty(),1)),
                new KeyFrame(Duration.millis(2200),new KeyValue(rules.opacityProperty(),0))
        );
        timelineRules2.setDelay(Duration.millis(2200));
        timelineRules2.setOnFinished(event -> {
            rules.setText("*PRESS ESC KEY TO PAUSE*");
            rules.setLayoutX(540-(rules.getBoundsInParent().getMaxX()-rules.getBoundsInParent().getMinX())/2);
        });
        timelineRules2.play();
        Timeline timelineRules3=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(rules.opacityProperty(),0)),
                new KeyFrame(Duration.millis(100),new KeyValue(rules.opacityProperty(),1)),
                new KeyFrame(Duration.millis(2100),new KeyValue(rules.opacityProperty(),1)),
                new KeyFrame(Duration.millis(2200),new KeyValue(rules.opacityProperty(),0))
        );
        timelineRules3.setDelay(Duration.millis(4400));
        timelineRules3.setOnFinished(this::StartGame);
        timelineRules3.play();
        FadeTransition fadeTransitionGamePane = new FadeTransition(Duration.millis(500), gamePane);
        fadeTransitionGamePane.setFromValue(0);
        fadeTransitionGamePane.setToValue(1);
        fadeTransitionGamePane.setDelay(Duration.millis(6600));
        fadeTransitionGamePane.play();
        fadeTransitionGamePane.setOnFinished(event -> {
            gameLogic.setStopKeyboard(0);
        });
    }

    private void StartGame(ActionEvent event){
        gameLogic.GameStart();
    }


    public void restartGame(ActionEvent event ) {
        gameOverMenu.setVisible(false);
        resurrect.setText(String.valueOf(5));
        resurrect_text.setText("resurrect");
        pacman_lives_3.setOpacity(1);
        pacman_lives_2.setOpacity(1);
        pacman_lives_1.setOpacity(1);
        gameLogic.restartGame();
    }

    public void resurrectGame(ActionEvent event){
        if(Integer.parseInt(resurrect.getText())==5) {
            if (Integer.parseInt(cherries.getText()) >= 5){
                gameOverMenu.setVisible(false);
                pacman_lives_1.setOpacity(0);
                resurrect.setText(String.valueOf(Integer.parseInt(resurrect.getText()) + 5));
                gameLogic.resurrectGame();
                cherries.setText(String.valueOf(Integer.parseInt(gameLogic.getCherries().getText()) - 5));
                gameLogic.setData();
                gameLogic.getData();
            }else{
                resurrect_text.setText("Cherries?");
                resurrect_button.setCancelButton(true);
            }
        } else if (Integer.parseInt(resurrect.getText())==10) {
            if (Integer.parseInt(cherries.getText()) >= 10) {
                gameOverMenu.setVisible(false);
                resurrect.setText(String.valueOf(Integer.parseInt(resurrect.getText()) + 5));
                pacman_lives_2.setOpacity(0);
                gameLogic.resurrectGame();
                cherries.setText(String.valueOf(Integer.parseInt(gameLogic.getCherries().getText()) - 10));
                gameLogic.setData();
                gameLogic.getData();
            }else{
                resurrect_text.setText("Cherries?");
                resurrect_button.setCancelButton(true);
            }
        }else if (Integer.parseInt(resurrect.getText())==15){
            if (Integer.parseInt(cherries.getText()) >= 15) {
                gameOverMenu.setVisible(false);
                resurrect.setText("-1");
                pacman_lives_3.setOpacity(0);
                gameLogic.resurrectGame();
                cherries.setText(String.valueOf(Integer.parseInt(gameLogic.getCherries().getText()) - 15));
                gameLogic.setData();
                gameLogic.getData();
            }else{
                resurrect_text.setText("Cherries?");
                resurrect_button.setCancelButton(true);
            }
        }
        if(Objects.equals(resurrect.getText(), "-1")){
            resurrect_text.setText("Lives?");
            resurrect_button.setCancelButton(true);
        }



    }
}





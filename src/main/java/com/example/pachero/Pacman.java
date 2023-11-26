package com.example.pachero;

import javafx.animation.*;
import javafx.util.Duration;

public class Pacman {

    private final double startLayoutX=291;
    private final double startLayoutY=325;

    private Costume pacman;

    private Game_Logic gameLogic;
    public Pacman(Costume pacman){

        this.pacman=pacman;
    }

    public void relocate(double v){
        Timeline timeline=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(pacman.getPacman_costume().layoutXProperty(),pacman.getPacman_costume().getLayoutX())),
                new KeyFrame(Duration.millis(1050),new KeyValue(pacman.getPacman_costume().layoutXProperty(),v))
        );
        timeline.setCycleCount(1);
        timeline.setDelay(Duration.millis(1050));
        timeline.play();

        Timeline timelineMouthAnimation=new Timeline(
                new KeyFrame(Duration.ZERO,event -> pacman.chnageToOpen()),
                new KeyFrame(Duration.millis(175),event -> pacman.changeToClose()),
                new KeyFrame(Duration.millis(350),event -> pacman.chnageToOpen())
        );
        timelineMouthAnimation.setCycleCount(3);
        timelineMouthAnimation.setDelay(Duration.millis(1050));
        timelineMouthAnimation.play();
        timelineMouthAnimation.setOnFinished(gameLogic::pacmanPositionCheck);
    }

    public void rotate(double angle){
        RotateTransition rotateTransition=new RotateTransition(Duration.millis(100),pacman.getPacman_costume());
        rotateTransition.setByAngle(angle);
        rotateTransition.play();
    }


    public void rotateBack(double angle){
        RotateTransition rotateTransition=new RotateTransition(Duration.millis(750),pacman.getPacman_costume());
        rotateTransition.setByAngle(angle);
        rotateTransition.play();
    }

    public void goDown(){
    }

    public void goUp(){
    }

    public void costumeChange(Costume costume){

    }

    public void animationFadeIn(){
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), pacman.getPacman_costume());
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setDelay(Duration.millis(500));
        fadeTransition.play();
    }

    public Costume getPacman() {
        return pacman;
    }

    public void setGameLogic(Game_Logic gameLogic) {
        this.gameLogic = gameLogic;
    }

    public void goToStart(){
//        pacman.getPacman_costume().setRotate(180);
        Timeline timeline=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(pacman.getPacman_costume().layoutXProperty(),pacman.getPacman_costume().getLayoutX())),
                new KeyFrame(Duration.millis(350),new KeyValue(pacman.getPacman_costume().layoutXProperty(),startLayoutX))
        );
        timeline.setCycleCount(1);
        timeline.setOnFinished(gameLogic::resetKeyboard);
        timeline.play();

        Timeline timelineMouthAnimation=new Timeline(
                new KeyFrame(Duration.ZERO,event -> pacman.chnageToOpen()),
                new KeyFrame(Duration.millis(175),event -> pacman.changeToClose()),
                new KeyFrame(Duration.millis(350),event -> pacman.chnageToOpen())
        );
        timelineMouthAnimation.setCycleCount(1);
        timelineMouthAnimation.setOnFinished(gameLogic::resetKeyboard);
        timelineMouthAnimation.play();
        timelineMouthAnimation.setOnFinished(event -> {pacman.getPacman_costume().setRotate(0);});
    }

    public void goIntoOblivion(){
        Timeline timelineOblivion=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(pacman.getPacman_costume().layoutYProperty(),pacman.getPacman_costume().getLayoutY())),
                new KeyFrame(Duration.millis(1000),new KeyValue(pacman.getPacman_costume().layoutYProperty(),567))
        );
        timelineOblivion.play();

        Timeline timelineRotate=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(pacman.getPacman_costume().rotateProperty(),0)),
                new KeyFrame(Duration.millis(333),new KeyValue(pacman.getPacman_costume().rotateProperty(),360))
        );
        timelineRotate.setCycleCount(3);
        timelineRotate.play();
    }
}

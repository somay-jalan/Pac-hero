package com.example.pachero;

import javafx.animation.*;
import javafx.util.Duration;

public class Pacman {

    private double x;
    private double y;

    private Costume pacman;
    public Pacman(Costume pacman){

        this.pacman=pacman;
        System.out.println(pacman.getPacman_costume().getImage().getUrl());
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
}

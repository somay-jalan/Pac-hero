package com.example.pachero;

import javafx.animation.FadeTransition;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Platform {


    private double x;
    private double y;
    private final Rectangle rectangle;
    public Platform(Rectangle rectangle){
        this.rectangle=rectangle;
    }

    public void animateRectangleFadein(){
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), rectangle);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setDelay(Duration.millis(500));
        fadeTransition.play();
    }

    public void relocate(double v, double v1) {
        rectangle.relocate(v, v1);
    }

    public void animateRectangleFadeout(){
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), rectangle);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
    }
}

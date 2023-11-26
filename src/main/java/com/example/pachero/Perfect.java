package com.example.pachero;

import javafx.animation.*;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Random;

public class Perfect {

    private double x;
    private double y;
    private final Rectangle rectangle;
    public Perfect(Rectangle rectangle){
        this.rectangle=rectangle;
    }

    public void relocate(double v, double v1) {
        rectangle.relocate(v, v1);
    }

    public void animationFadeIn(){
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), rectangle);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setDelay(Duration.millis(500));
        fadeTransition.play();
        FlameAnimation();
    }

    private void FlameAnimation() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(rectangle.opacityProperty(), 0.5)),
                new KeyFrame(Duration.seconds(1), new KeyValue(rectangle.opacityProperty(), 1))
        );

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.setDelay(Duration.millis(2300));
        timeline.play();
    }
    public void animationFadeOut(){
    }

}

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

    public void relocate(double v1) {
        Timeline timeline=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(rectangle.layoutXProperty(),rectangle.getLayoutY())),
                new KeyFrame(Duration.millis(10),new KeyValue(rectangle.layoutXProperty(),v1))
        );
        timeline.setDelay(Duration.millis(500));
        rectangle.toFront();
        timeline.play();
    }

    public void animationFadeIn(){
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), rectangle);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setDelay(Duration.millis(500));
        fadeTransition.play();
        fadeTransition.setOnFinished(event -> {
//            System.out.println("PERFECT");
//            System.out.println(rectangle.getLayoutX());
//            System.out.println(rectangle.getLayoutY());
        });
        FlameAnimation();
    }
    private Timeline timelineFlame;
    private void FlameAnimation() {
        timelineFlame= new Timeline(

                new KeyFrame(Duration.ZERO, new KeyValue(rectangle.opacityProperty(), 0.5)),
                new KeyFrame(Duration.millis(1000), new KeyValue(rectangle.opacityProperty(), 1))
        );

        timelineFlame.setCycleCount(Animation.INDEFINITE);
        timelineFlame.setAutoReverse(true);
        timelineFlame.setDelay(Duration.millis(2300));
        timelineFlame.play();
    }
    public void animationFadeOut(){
        timelineFlame.pause();
        Timeline timelineFadeOut=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(rectangle.opacityProperty(),rectangle.getOpacity())),
                new KeyFrame(Duration.millis(50),new KeyValue(rectangle.opacityProperty(),0))
        );
//        timelineFadeOut.setDelay(Duration.millis(50));
        timelineFadeOut.play();
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void perfectAnimation(){

    }

}

package com.example.pachero;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Random;

public class Platform {
    

    private Rectangle rectangle;
    public Platform(Rectangle rectangle){
        this.rectangle=rectangle;
    }

    public void animateRectangleFadeIn(){
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), rectangle);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setDelay(Duration.millis(500));
        fadeTransition.play();
        fadeTransition.setOnFinished(event -> {
//            System.out.println("PLATFORM");
//            System.out.println(rectangle.getLayoutX());
//            System.out.println(rectangle.getLayoutY());
//            System.out.println(rectangle.getWidth() );
        });
    }

    public void relocate(double v) {
        Timeline timeline=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(rectangle.layoutXProperty(),rectangle.getLayoutX())),
                new KeyFrame(Duration.millis(350),new KeyValue(rectangle.layoutXProperty(),v))
        );
        timeline.play();
//        timeline.setOnFinished(event -> {
//            System.out.println(rectangle.getLayoutX());
//            System.out.println(rectangle.getLayoutY());
//        });
    }

    public void animateRectangleFadeout(){
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(100), rectangle);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public Rectangle newRectangle(){
        Rectangle newRect=new Rectangle();
        newRect.setFill(this.rectangle.getFill());
        newRect.setLayoutY(this.rectangle.getLayoutY());
        newRect.setLayoutX(new Random().nextDouble()*300+340);
        newRect.setWidth(new Random().nextDouble()*150+40);
        newRect.setHeight(this.rectangle.getHeight());
        newRect.setArcHeight(this.rectangle.getArcHeight());
        newRect.setArcWidth(this.rectangle.getArcWidth());
        newRect.setOpacity(0);
        return newRect;
    }
}

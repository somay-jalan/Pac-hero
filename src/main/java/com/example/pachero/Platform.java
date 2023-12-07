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
    private Game_Logic gameLogic;
    public Platform(Rectangle rectangle){

        this.rectangle=rectangle;
    }

    public void animateRectangleFadeIn(){
        FadeTransition fadeInTransition = new FadeTransition(Duration.millis(1000), rectangle);
        fadeInTransition.setFromValue(0);
        fadeInTransition.setToValue(1);
        fadeInTransition.setDelay(Duration.millis(500));
        fadeInTransition.play();
        gameLogic.getAnimationList().add(fadeInTransition);
        fadeInTransition.setOnFinished(event -> {
            gameLogic.getAnimationList().remove(fadeInTransition);
//            System.out.println("PLATFORM");
//            System.out.println(rectangle.getLayoutX());
//            System.out.println(rectangle.getLayoutY());
//            System.out.println(rectangle.getWidth() );
        });
    }

    public void relocate(double v) {
        Timeline timelineRelocate=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(rectangle.layoutXProperty(),rectangle.getLayoutX())),
                new KeyFrame(Duration.millis(350),new KeyValue(rectangle.layoutXProperty(),v))
        );
        timelineRelocate.setOnFinished(event -> {
            gameLogic.getAnimationList().remove(timelineRelocate);
        });
        timelineRelocate.play();
        gameLogic.getAnimationList().add(timelineRelocate);
//        timeline.setOnFinished(event -> {
//            System.out.println(rectangle.getLayoutX());
//            System.out.println(rectangle.getLayoutY());
//        });
    }

    public void animateRectangleFadeout(){
        FadeTransition fadeOutTransition = new FadeTransition(Duration.millis(100), rectangle);
        fadeOutTransition.setFromValue(1);
        fadeOutTransition.setToValue(0);
        fadeOutTransition.setOnFinished(event -> {
            gameLogic.getAnimationList().remove(fadeOutTransition);
        });
        fadeOutTransition.play();
        gameLogic.getAnimationList().add(fadeOutTransition);
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

    public void setGameLogic(Game_Logic gameLogic) {
        this.gameLogic = gameLogic;
    }
}

package com.example.pachero;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Pacman_Stick {


    public Line line;

    public final double startLayoutX=310;
    public final double startLayoutY=320;

    public Pacman_Stick(Line line){
        this.line=line;
    }
    public void IncreaseLength(){
        Timeline timeline=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(line.endYProperty(),line.getEndY())),
                new KeyFrame(Duration.millis( 62.5),new KeyValue(line.endYProperty(),line.getEndY()-8))
        );
        timeline.setCycleCount(1);
        timeline.play();
    }

    public void StopIncreaseLength(){
//        System.out.println(line.getRotate());
//        System.out.println(line.rotationAxisProperty());
        Rotate rotation=new Rotate();
        rotation.pivotXProperty().bind(line.startXProperty());
        rotation.pivotXProperty().bind(line.startYProperty());
        line.getTransforms().add(rotation);
        Timeline timeline=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(line.layoutYProperty(),line.getLayoutY()),new KeyValue(line.layoutXProperty(),line.getLayoutX()),new KeyValue(rotation.angleProperty(),line.getRotate())),
                new KeyFrame(Duration.millis(750),new KeyValue(line.layoutYProperty(),line.getLayoutY()+25),new KeyValue(line.layoutXProperty(),line.getLayoutX()+25),new KeyValue(rotation.angleProperty(),90))
        );
        timeline.setCycleCount(1);
        timeline.play();
        timeline.setOnFinished(this::Drop);

    }

    public void goToStart(){
//        Timeline timeline=new Timeline(
//                new KeyFrame(Duration.ZERO,new KeyValue(line.layoutXProperty(),line.getLayoutX()),new KeyValue(line.layoutYProperty(),line.getLayoutY()),new KeyValue(line.endYProperty(),line.getEndY())),
//                new KeyFrame(Duration.millis(20),new KeyValue(line.layoutXProperty(),startLayoutX),new KeyValue(line.layoutYProperty(),startLayoutY),new KeyValue(line.endYProperty(),0))
//        );
//        timeline.play();
        Rotate rotation=new Rotate();
        line.setLayoutX(startLayoutX-25);
        line.setLayoutY(startLayoutY);
        rotation.pivotXProperty().bind(line.startXProperty());
        rotation.pivotXProperty().bind(line.startYProperty());
        line.getTransforms().add(rotation);
        Timeline timeline=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(rotation.angleProperty(),line.getRotate())),
                new KeyFrame(Duration.millis(100),new KeyValue(line.layoutXProperty(),line.getLayoutX()+25),new KeyValue(rotation.angleProperty(),-90))
        );
        timeline.setCycleCount(1);
        timeline.play();
        line.toFront();
        line.setEndY(0);
    }
    public void Drop(ActionEvent event) {
//        System.out.println(line.getRotate());
//        System.out.println(line.rotateProperty());
//        System.out.println(line.rotationAxisProperty());
        Timeline timeline=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(line.layoutYProperty(),line.getLayoutY())),
                new KeyFrame(Duration.millis(300),new KeyValue(line.layoutYProperty(),line.getLayoutY()+25))
        );
        timeline.setCycleCount(1);
        timeline.setDelay(Duration.millis(0));
        timeline.play();
    }

    public void printStats(ActionEvent event){
        System.out.println(line.getLayoutY());
        System.out.println(line.getLayoutX());
        System.out.println(line.getEndY());
    }

    public Line getLine() {
        return line;
    }
}

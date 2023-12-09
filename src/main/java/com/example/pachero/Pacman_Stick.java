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


    private Line line;

    private Game_Logic gameLogic;
    public final double startLayoutX=310;
    public final double startLayoutY=320;

    public Pacman_Stick(Line line){

        this.line=line;
    }
    public void IncreaseLength(){
        Timeline timelineIncreaseLength=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(line.endYProperty(),line.getEndY())),
                new KeyFrame(Duration.millis( 62.5),new KeyValue(line.endYProperty(),line.getEndY()-8))
        );
        timelineIncreaseLength.setOnFinished(event -> {
            gameLogic.getAnimationList().remove(timelineIncreaseLength);
        });
        timelineIncreaseLength.setCycleCount(1);
        timelineIncreaseLength.play();
        gameLogic.getAnimationList().add(timelineIncreaseLength);
    }

    public void StopIncreaseLength(){
//        System.out.println(line.getRotate());
//        System.out.println(line.rotationAxisProperty());
        Rotate rotation=new Rotate();
        rotation.pivotXProperty().bind(line.startXProperty());
        rotation.pivotXProperty().bind(line.startYProperty());
        line.getTransforms().add(rotation);
        Timeline timelineStopIncreaseLength=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(line.layoutYProperty(),line.getLayoutY()),new KeyValue(line.layoutXProperty(),line.getLayoutX()),new KeyValue(rotation.angleProperty(),line.getRotate())),
                new KeyFrame(Duration.millis(750),new KeyValue(line.layoutYProperty(),line.getLayoutY()+25),new KeyValue(line.layoutXProperty(),line.getLayoutX()+25),new KeyValue(rotation.angleProperty(),90))
        );
        timelineStopIncreaseLength.setCycleCount(1);
        timelineStopIncreaseLength.play();
        gameLogic.getAnimationList().add(timelineStopIncreaseLength);
        timelineStopIncreaseLength.setOnFinished(event->{
            this.Drop(event);
            StickDropMusic drop_music= new StickDropMusic();
            Thread DropMusicPlayer=new Thread(drop_music);
            DropMusicPlayer.start();
            gameLogic.getAnimationList().remove(timelineStopIncreaseLength);
        });

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
        Timeline timelineGoToStart=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(rotation.angleProperty(),line.getRotate())),
                new KeyFrame(Duration.millis(100),new KeyValue(rotation.angleProperty(),-90))
        );
        timelineGoToStart.setCycleCount(1);
        timelineGoToStart.setOnFinished(event -> {
            line.setLayoutX(line.getLayoutX()+25);
            gameLogic.getAnimationList().remove(timelineGoToStart);
        });
        timelineGoToStart.play();
        gameLogic.getAnimationList().add(timelineGoToStart);
        line.toFront();
        line.setEndY(0);
    }
    public void Drop(ActionEvent event) {

//        System.out.println(line.getRotate());
//        System.out.println(line.rotateProperty());
//        System.out.println(line.rotationAxisProperty());
        Timeline timelineDrop=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(line.layoutYProperty(),line.getLayoutY())),
                new KeyFrame(Duration.millis(300),new KeyValue(line.layoutYProperty(),line.getLayoutY()+24))
        );
        timelineDrop.setCycleCount(1);
        timelineDrop.setDelay(Duration.millis(0));
        timelineDrop.setOnFinished(event1 -> {
            gameLogic.getAnimationList().remove(timelineDrop);
//            System.out.println(line.getLayoutX());
//            System.out.println(line.getEndY());
//            System.out.println(gameLogic.getPerfect().getRectangle().getLayoutX());
            if(gameLogic.getPerfect().getRectangle().getLayoutX()<(getLine().getLayoutX()+Math.abs(getLine().getEndY())) && gameLogic.getPerfect().getRectangle().getLayoutX()+gameLogic.getPerfect().getRectangle().getWidth()>=(getLine().getLayoutX()+Math.abs(getLine().getEndY()))){
                gameLogic.getPerfect().perfectAnimation();
            }
        });
        timelineDrop.play();
        gameLogic.getAnimationList().add(timelineDrop);
    }

    public void printStats(ActionEvent event){
        System.out.println(line.getLayoutY());
        System.out.println(line.getLayoutX());
        System.out.println(line.getEndY());
    }

    public Line getLine() {
        return line;
    }

    public void setGameLogic(Game_Logic gameLogic) {
        this.gameLogic = gameLogic;
    }

    public double getStartLayoutX() {
        return startLayoutX;
    }

    public double getStartLayoutY() {
        return startLayoutY;
    }
}

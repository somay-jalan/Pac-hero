package com.example.pachero;

import javafx.animation.*;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.util.Duration;

public class Pacman {

    private final double startLayoutX=291;
    private final double startLayoutY=325;

    private Costume pacman;

    private Game_Logic gameLogic;
    public Pacman(Costume pacman){

        this.pacman=pacman;
    }

    private Timeline timelineRelocate;
    private Timeline timelineMouthAnimation;
    public void relocate(double v){
        double duration=Math.abs((double) 3000 /242*gameLogic.getPacmanStick().getLine().getEndY());
        timelineRelocate=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(pacman.getPacman_costume().layoutXProperty(),pacman.getPacman_costume().getLayoutX())),
                new KeyFrame(Duration.millis(duration),new KeyValue(pacman.getPacman_costume().layoutXProperty(),v))
        );
        timelineRelocate.setCycleCount(1);
        timelineRelocate.setDelay(Duration.millis(1050));
        timelineRelocate.setOnFinished(gameLogic::pacmanPositionCheck);
        timelineRelocate.play();

        timelineMouthAnimation=new Timeline(
                new KeyFrame(Duration.ZERO,event -> pacman.changeToOpen()),
                new KeyFrame(Duration.millis(175),event -> pacman.changeToClose()),
                new KeyFrame(Duration.millis(350),event -> pacman.changeToOpen())
        );
        timelineMouthAnimation.setCycleCount((int) (duration/350));
        timelineMouthAnimation.setDelay(Duration.millis(1050));
        timelineMouthAnimation.play();
        BooleanBinding checkPlatformPacmanCollision= Bindings.createBooleanBinding(
                ()->gameLogic.getNext_platform().getRectangle().getBoundsInParent().intersects(gameLogic.getPacman().getPacman().getPacman_costume().getBoundsInParent()),
                gameLogic.getNext_platform().getRectangle().boundsInParentProperty(),
                gameLogic.getPacman().getPacman().getPacman_costume().boundsInParentProperty()
        );
        ChangeListener<Boolean> collisionListener=new ChangeListener<>() {
            @Override
            public void changed(ObservableValue obs, Boolean wasCollision, Boolean isNowCollision) {
//                System.out.println(ghost.boundsInParentProperty());
//                System.out.println(gameLogic.getPacman().getPacman().getPacman_costume().boundsInParentProperty());
//                System.out.println(obs);
//                System.out.println(wasCollision);
//                System.out.println(isNowCollision);
                if(isNowCollision && !wasCollision){
                    gameLogic.getPacman().getTimelineRelocate().pause();
                    gameLogic.getPacman().getTimelineMouthAnimation().pause();
                    gameLogic.setStopKeyboard(1);
                    gameLogic.getPacman().getPacman().changeToOpen();
                    gameLogic.setData();
                    gameLogic.getPacman().goIntoOblivion();
                    checkPlatformPacmanCollision.removeListener(this);

                }
            }
        };
        checkPlatformPacmanCollision.addListener(collisionListener );
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
                new KeyFrame(Duration.ZERO,event -> pacman.changeToOpen()),
                new KeyFrame(Duration.millis(175),event -> pacman.changeToClose()),
                new KeyFrame(Duration.millis(350),event -> pacman.changeToOpen())
        );
        timelineMouthAnimation.setCycleCount(1);
        timelineMouthAnimation.setOnFinished(gameLogic::resetKeyboard);
        timelineMouthAnimation.play();
        timelineMouthAnimation.setOnFinished(event -> {pacman.getPacman_costume().setRotate(0);});
    }

    public void goIntoOblivion(){
        gameLogic.setStopKeyboard(1);
        Timeline timelineOblivion=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(pacman.getPacman_costume().layoutYProperty(),pacman.getPacman_costume().getLayoutY())),
                new KeyFrame(Duration.millis(1000),new KeyValue(pacman.getPacman_costume().layoutYProperty(),600))
        );
        timelineOblivion.play();
        timelineOblivion.setOnFinished(event -> {
            Timeline timeShake=new Timeline(
                    new KeyFrame(Duration.ZERO,new KeyValue(gameLogic.getGamePane().layoutXProperty(),gameLogic.getGamePane().getLayoutX()),new KeyValue(gameLogic.getGamePane().layoutYProperty(),gameLogic.getGamePane().getLayoutY())),
                    new KeyFrame(Duration.millis(50)/*,new KeyValue(gameLogic.getGamePane().layoutXProperty(),gameLogic.getGamePane().getLayoutX()-5)*/,new KeyValue(gameLogic.getGamePane().layoutYProperty(),gameLogic.getGamePane().getLayoutY()-5)),
                    new KeyFrame(Duration.millis(75)/*,new KeyValue(gameLogic.getGamePane().layoutXProperty(),gameLogic.getGamePane().getLayoutX()+4)*/,new KeyValue(gameLogic.getGamePane().layoutYProperty(),gameLogic.getGamePane().getLayoutY()+3)),
                    new KeyFrame(Duration.millis(88)/*,new KeyValue(gameLogic.getGamePane().layoutXProperty(),gameLogic.getGamePane().getLayoutX()-2)*/,new KeyValue(gameLogic.getGamePane().layoutYProperty(),gameLogic.getGamePane().getLayoutY()-2)),
                    new KeyFrame(Duration.millis(98)/*,new KeyValue(gameLogic.getGamePane().layoutXProperty(),gameLogic.getGamePane().getLayoutX()+1)*/ ,new KeyValue(gameLogic.getGamePane().layoutYProperty(),gameLogic.getGamePane().getLayoutY()+1))
            );
            timeShake.play();
            gameLogic.setData();
        });
        Timeline timelineRotate=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(pacman.getPacman_costume().rotateProperty(),0)),
                new KeyFrame(Duration.millis(333),new KeyValue(pacman.getPacman_costume().rotateProperty(),360))
        );
        timelineRotate.setCycleCount(3);
        timelineRotate.setOnFinished(event -> {
            gameLogic.gameEndMenu();
        });
        timelineRotate.play();
    }

    public double getStartLayoutX() {
        return startLayoutX;
    }

    public double getStartLayoutY() {
        return startLayoutY;
    }

    public void setPacman(Costume pacman) {
        this.pacman = pacman;
    }

    public Game_Logic getGameLogic() {
        return gameLogic;
    }

    public Timeline getTimelineRelocate() {
        return timelineRelocate;
    }

    public void setTimelineRelocate(Timeline timelineRelocate) {
        this.timelineRelocate = timelineRelocate;
    }

    public Timeline getTimelineMouthAnimation() {
        return timelineMouthAnimation;
    }

    public void setTimelineMouthAnimation(Timeline timelineMouthAnimation) {
        this.timelineMouthAnimation = timelineMouthAnimation;
    }

    public void restart() {
        getPacman().getPacman_costume().setOpacity(0);
        getPacman().getPacman_costume().setLayoutX(startLayoutX);
        getPacman().getPacman_costume().setLayoutY(startLayoutY);
        animationFadeIn();
    }


}

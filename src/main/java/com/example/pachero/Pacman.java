package com.example.pachero;

import javafx.animation.*;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.util.Duration;

public class Pacman {

    private final double startLayoutX=291;
    private final double startLayoutY=320;

    private Costume pacman;

    private Game_Logic gameLogic;
    public Pacman(Costume pacman){

        this.pacman=pacman;
    }

    private Timeline timelineRelocate;
    private Timeline timelineMouthAnimation;
    private double duration;
    public void relocate(double v){
        duration=Math.abs((double) (2000/242)*gameLogic.getPacmanStick().getLine().getEndY());
        timelineRelocate=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(pacman.getPacman_costume().layoutXProperty(),pacman.getPacman_costume().getLayoutX())),
                new KeyFrame(Duration.millis(duration),new KeyValue(pacman.getPacman_costume().layoutXProperty(),v))
        );
        timelineRelocate.setCycleCount(1);
        timelineRelocate.setDelay(Duration.millis(1050));
        timelineRelocate.setOnFinished(event->{
            gameLogic.pacmanPositionCheck(event);
            gameLogic.getAnimationList().remove(timelineRelocate);
        });
        timelineRelocate.play();
        gameLogic.getAnimationList().add(timelineRelocate);

        timelineMouthAnimation=new Timeline(
                new KeyFrame(Duration.ZERO,event -> pacman.changeToOpen()),
                new KeyFrame(Duration.millis(175),event -> pacman.changeToClose()),
                new KeyFrame(Duration.millis(350),event -> pacman.changeToOpen())
        );
        timelineMouthAnimation.setCycleCount((int) (duration/350));
        timelineMouthAnimation.setDelay(Duration.millis(1050));
        timelineMouthAnimation.setOnFinished(event -> {
            gameLogic.getAnimationList().remove(timelineMouthAnimation);
        });
        timelineMouthAnimation.play();
        gameLogic.getAnimationList().add(timelineMouthAnimation);
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
        rotateTransition.setOnFinished(event -> {
            gameLogic.getAnimationList().remove(rotateTransition);
        });
        rotateTransition.play();
        gameLogic.getAnimationList().add(rotateTransition);
    }


    public void rotateBack(double angle){
        RotateTransition rotateTransitionBack=new RotateTransition(Duration.millis(750),pacman.getPacman_costume());
        rotateTransitionBack.setByAngle(angle);
        rotateTransitionBack.setOnFinished(event -> {
            gameLogic.getAnimationList().remove(rotateTransitionBack);
        });
        rotateTransitionBack.play();
        gameLogic.getAnimationList().add(rotateTransitionBack);
    }

    public void goDown(){
    }

    public void goUp(){
    }

    public void costumeChange(Costume costume){

    }

    public void animationFadeIn(){
        FadeTransition fadeInTransition = new FadeTransition(Duration.millis(1000), pacman.getPacman_costume());
        fadeInTransition.setFromValue(0);
        fadeInTransition.setToValue(1);
        fadeInTransition.setDelay(Duration.millis(500));
        fadeInTransition.setOnFinished(event -> {
            gameLogic.getAnimationList().remove(fadeInTransition);
        });
        fadeInTransition.play();
        gameLogic.getAnimationList().add(fadeInTransition);
    }

    public Costume getPacman() {
        return pacman;
    }

    public void setGameLogic(Game_Logic gameLogic) {
        this.gameLogic = gameLogic;
    }

    public void goToStart(){
//        pacman.getPacman_costume().setRotate(180);
        Timeline timelineGoToStart=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(pacman.getPacman_costume().layoutXProperty(),pacman.getPacman_costume().getLayoutX())),
                new KeyFrame(Duration.millis(350),new KeyValue(pacman.getPacman_costume().layoutXProperty(),startLayoutX))
        );
        timelineGoToStart.setCycleCount(1);
        timelineGoToStart.setOnFinished(event->{
            gameLogic.resetKeyboard(event);
            gameLogic.playTextCheck();
            gameLogic.getAnimationList().remove(timelineGoToStart);
        });
        timelineGoToStart.play();
        gameLogic.getAnimationList().add(timelineGoToStart);


        Timeline timelineMouthAnimation=new Timeline(
                new KeyFrame(Duration.ZERO,event -> pacman.changeToOpen()),
                new KeyFrame(Duration.millis(175),event -> pacman.changeToClose()),
                new KeyFrame(Duration.millis(350),event -> pacman.changeToOpen())
        );
        timelineMouthAnimation.setCycleCount(1);
        timelineMouthAnimation.setOnFinished(event -> {
            pacman.getPacman_costume().setRotate(0);
            gameLogic.getAnimationList().remove(timelineMouthAnimation);
        });
        timelineMouthAnimation.play();
        gameLogic.getAnimationList().add(timelineMouthAnimation);
    }

    public void goIntoOblivion(){
        gameLogic.setStopKeyboard(1);
        Timeline timelineOblivion=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(pacman.getPacman_costume().layoutYProperty(),pacman.getPacman_costume().getLayoutY())),
                new KeyFrame(Duration.millis(1000),new KeyValue(pacman.getPacman_costume().layoutYProperty(),600))
        );
        timelineOblivion.play();
        gameLogic.getAnimationList().add(timelineOblivion);
        timelineOblivion.setOnFinished(event -> {
            gameLogic.setData();
            gameLogic.getAnimationList().remove(timelineOblivion);
            Timeline timelineShake=new Timeline(
                    new KeyFrame(Duration.ZERO,new KeyValue(gameLogic.getGamePane().layoutXProperty(),gameLogic.getGamePane().getLayoutX()),new KeyValue(gameLogic.getGamePane().layoutYProperty(),gameLogic.getGamePane().getLayoutY())),
                    new KeyFrame(Duration.millis(50)/*,new KeyValue(gameLogic.getGamePane().layoutXProperty(),gameLogic.getGamePane().getLayoutX()-5)*/,new KeyValue(gameLogic.getGamePane().layoutYProperty(),gameLogic.getGamePane().getLayoutY()-5)),
                    new KeyFrame(Duration.millis(75)/*,new KeyValue(gameLogic.getGamePane().layoutXProperty(),gameLogic.getGamePane().getLayoutX()+4)*/,new KeyValue(gameLogic.getGamePane().layoutYProperty(),gameLogic.getGamePane().getLayoutY()+3)),
                    new KeyFrame(Duration.millis(88)/*,new KeyValue(gameLogic.getGamePane().layoutXProperty(),gameLogic.getGamePane().getLayoutX()-2)*/,new KeyValue(gameLogic.getGamePane().layoutYProperty(),gameLogic.getGamePane().getLayoutY()-2)),
                    new KeyFrame(Duration.millis(98)/*,new KeyValue(gameLogic.getGamePane().layoutXProperty(),gameLogic.getGamePane().getLayoutX()+1)*/ ,new KeyValue(gameLogic.getGamePane().layoutYProperty(),gameLogic.getGamePane().getLayoutY()+1))
            );
            timelineShake.setOnFinished(event1->{
                gameLogic.getAnimationList().remove(timelineShake);
            });
            timelineShake.play();
            gameLogic.getAnimationList().add(timelineShake);
            gameLogic.gameEndMenu();

        });
        Timeline timelineRotate=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(pacman.getPacman_costume().rotateProperty(),0)),
                new KeyFrame(Duration.millis(333),new KeyValue(pacman.getPacman_costume().rotateProperty(),360))
        );
        timelineRotate.setCycleCount(3);
        timelineRotate.setOnFinished(event -> {

            gameLogic.getAnimationList().remove(timelineRotate);
        });
        timelineRotate.play();
        gameLogic.getAnimationList().add(timelineRotate);
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

    public double getDuration() {
        return duration;
    }
}

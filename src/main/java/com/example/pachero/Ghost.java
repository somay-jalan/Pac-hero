package com.example.pachero;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Ghost{
    private double x;
    private double y;

    private final ImageView ghost;
    private Game_Logic gameLogic;
    public Ghost(ImageView ghost,Game_Logic gameLogic){
        this.ghost=ghost;
        this.gameLogic=gameLogic;
    }

    public void relocate(ActionEvent event){
        Timeline timelineGhostRelocate=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(ghost.layoutXProperty(),ghost.getLayoutX())),
                new KeyFrame(Duration.millis(gameLogic.getPacman().getDuration()),new KeyValue(ghost.layoutXProperty(),gameLogic.getCur_platform().getRectangle().getLayoutX()))
        );

        timelineGhostRelocate.setOnFinished(event1 -> {
            animateFadeOut(event1);
            gameLogic.getAnimationList().remove(timelineGhostRelocate);
        });
        timelineGhostRelocate.play();
        gameLogic.getAnimationList().add(timelineGhostRelocate);
        BooleanBinding checkGhostPacmanCollision= Bindings.createBooleanBinding(
                ()->ghost.getBoundsInParent().intersects(gameLogic.getPacman().getPacman().getPacman_costume().getBoundsInParent()),
                ghost.boundsInParentProperty(),
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
//                    timelineGhostRelocate.pause();
                    gameLogic.getPacman().getTimelineRelocate().pause();
                    gameLogic.getPacman().getTimelineMouthAnimation().pause();
                    gameLogic.setStopKeyboard(1);
                    gameLogic.getPacman().getPacman().changeToOpen();
                    gameLogic.setData();
                    gameLogic.getPacman().goIntoOblivion();
                    checkGhostPacmanCollision.removeListener(this);


                }
            }
        };
        checkGhostPacmanCollision.addListener(collisionListener);
//        checkGhostPacmanCollision.addListener((obs,wasCollision,isNowCollision)->{
//            System.out.println(ghost.boundsInParentProperty());
//            System.out.println(gameLogic.getPacman().getPacman().getPacman_costume().boundsInParentProperty());
//            System.out.println(obs);
//            System.out.println(wasCollision);
//            System.out.println(isNowCollision);
//            if(isNowCollision && !wasCollision){
//                System.out.println("TRY");
//                timelineGhostRelocate.pause();
//                gameLogic.getPacman().getTimelineRelocate().pause();
//                gameLogic.getPacman().getTimelineMouthAnimation().pause();
////                gameLogic.setStopKeyboard(1);
//                gameLogic.getPacman().getPacman().changeToOpen();
//
//            }
//        });
//        checkGhostPacmanCollision.removeListener();
    }

    public void animateFadeIn(ActionEvent event){
        Timeline timelineFadeIn=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(ghost.opacityProperty(),0)),
                new KeyFrame(Duration.millis(20),new KeyValue(ghost.opacityProperty(),1))
        );
        timelineFadeIn.setOnFinished(event1 -> {
            relocate(event1);
            gameLogic.getAnimationList().remove(timelineFadeIn);
        });
        timelineFadeIn.play();
        gameLogic.getAnimationList().add(timelineFadeIn);
    }

    public void animateFadeOut(ActionEvent event){
        Timeline timelineFadeOut=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(ghost.opacityProperty(),1)),
                new KeyFrame(Duration.millis(20),new KeyValue(ghost.opacityProperty(),0))
        );
        timelineFadeOut.setOnFinished(eventNew ->{
            gameLogic.getAnimationList().remove(timelineFadeOut);
            Timeline timelineGoToZero=new Timeline(
                    new KeyFrame(Duration.ZERO,new KeyValue(ghost.layoutXProperty(),ghost.getLayoutX())),
                    new KeyFrame(Duration.millis(1),new KeyValue(ghost.layoutXProperty(),0))
            );
            timelineGoToZero.setOnFinished(event1->{
                gameLogic.getPerfect().removeGhost(event1);
                gameLogic.getAnimationList().remove(timelineGoToZero);
            });
            timelineGoToZero.play();
            gameLogic.getAnimationList().add(timelineGoToZero);
        });
        timelineFadeOut.play();
        gameLogic.getAnimationList().add(timelineFadeOut);

    }


}

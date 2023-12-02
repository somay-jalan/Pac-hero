package com.example.pachero;


import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.Random;

public class Cherry{


    private ImageView cherry;
    private Game_Logic gameLogic;


    public void setGameLogic(Game_Logic gameLogic) {
        this.gameLogic = gameLogic;
    }
    private int removed=0;
    public void checkIntersectionPacman(ActionEvent event){
        BooleanBinding checkGhostPacmanCollision= Bindings.createBooleanBinding(
                ()->cherry.getBoundsInParent().intersects(gameLogic.getPacman().getPacman().getPacman_costume().getBoundsInParent()),
                cherry.boundsInParentProperty(),
                gameLogic.getPacman().getPacman().getPacman_costume().boundsInParentProperty()
        );
        ChangeListener<Boolean> collisionListener=new ChangeListener<>() {
            @Override
            public void changed(ObservableValue obs, Boolean wasCollision, Boolean isNowCollision) {
                if(isNowCollision && !wasCollision){
                    removed=1;
                    removeCherry();
                    gameLogic.getCherries().setText(String.valueOf(Integer.parseInt(gameLogic.getCherries().getText())+1));
                    gameLogic.getCherries().setWrappingWidth(gameLogic.getCherries().getText().length()*50);
                    if(gameLogic.getCherries().getText().length()!=String.valueOf(Integer.parseInt(gameLogic.getCherries().getText())-1).length()){
                        gameLogic.getCherries().setLayoutX(gameLogic.getCherries().getLayoutX()-(gameLogic.getCherries().getWrappingWidth()-40));
                    }
                    checkGhostPacmanCollision.removeListener(this);
                }
            }
        };
        checkGhostPacmanCollision.addListener(collisionListener);
    }
    public void addCherry(){
        cherry=new ImageView(new Image("file:/home/somay/IdeaProjects/Pac-hero/src/main/resources/images/cherry.png"));
        cherry.setLayoutY(375);
//        System.out.println("NEW CHERRY");
        cherry.setFitHeight(50);
        cherry.setFitWidth(50);
        cherry.setOpacity(0);
//        System.out.println(gameLogic.getNext_platform().getRectangle().getLayoutX());
//        System.out.println(gameLogic.getPacmanStick().getLine().getLayoutX()+Math.abs(gameLogic.getPacmanStick().getLine().getEndY()));
        double maxX=Math.min(gameLogic.getNext_platform().getRectangle().getLayoutX(),gameLogic.getPacmanStick().getLine().getLayoutX()+Math.abs(gameLogic.getPacmanStick().getLine().getEndY()));
        double minX=gameLogic.getCur_platform().getRectangle().getBoundsInParent().getMaxX();
//        System.out.println(maxX);
//        System.out.println(minX);
        if(maxX-minX-cherry.getFitWidth()>0) {
            cherry.setLayoutX(new Random().nextDouble() * (maxX - minX) + minX);
//            cherry.setFitHeight(gameLogic.getPacman().getPacman().getPacman_costume().getFitHeight());
//            cherry.setFitHeight(gameLogic.getPacman().getPacman().getPacman_costume().getFitHeight());
            Timeline timelineCherryFadeIn = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(cherry.opacityProperty(), 0)),
                    new KeyFrame(Duration.millis(200), new KeyValue(cherry.opacityProperty(), 1))
            );
            if(cherry.getBoundsInParent().intersects(gameLogic.getNext_platform().getRectangle().getBoundsInParent())){
                cherry.setLayoutY(0);
                cherry.setLayoutX(0);
            }else{
                timelineCherryFadeIn.setDelay(Duration.millis(1050));
                timelineCherryFadeIn.setOnFinished(event -> {
                    checkIntersectionPacman(event);
                    removeCherry();
                });
                timelineCherryFadeIn.play();
            }

            gameLogic.getGamePane().getChildren().add(cherry);
        }
    }

    public void removeCherry(){
        Timeline timelineCherryFadeOut=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(cherry.opacityProperty(),cherry.getOpacity())),
                new KeyFrame(Duration.millis(200),new KeyValue(cherry.opacityProperty(),0))
        );

        if(removed==0){
            timelineCherryFadeOut.setDelay(Duration.millis(1500));
        }
        timelineCherryFadeOut.setOnFinished(event -> {
            cherry.setLayoutY(0);
            cherry.setLayoutX(0);
        });
        timelineCherryFadeOut.setOnFinished(event -> {
            cherry.setVisible(false);
        });
        timelineCherryFadeOut.play();

    }


}

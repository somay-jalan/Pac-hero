package com.example.pachero;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.scene.Scene;

public class Perfect {

    private double x;
    private double y;
    private final Rectangle rectangle;

    private Game_Logic gameLogic;

    private AnchorPane gamePane;
    public Perfect(Rectangle rectangle){
        this.rectangle=rectangle;
        this.rectangle.setWidth(15);
    }

    public void relocate(double v1) {

        Timeline timelineRelocate=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(rectangle.layoutXProperty(),rectangle.getLayoutY())),
                new KeyFrame(Duration.millis(10),new KeyValue(rectangle.layoutXProperty(),v1))
        );
        timelineRelocate.setDelay(Duration.millis(500));
        rectangle.toFront();
        timelineRelocate.setOnFinished(event -> {
            gameLogic.getAnimationList().remove(timelineRelocate);
        });
        timelineRelocate.play();
        gameLogic.getAnimationList().add(timelineRelocate);
    }

    public void animationFadeIn(){
        FadeTransition fadeInTransition = new FadeTransition(Duration.millis(1000), rectangle);
        fadeInTransition.setFromValue(0);
        fadeInTransition.setToValue(0.5);
        fadeInTransition.setDelay(Duration.millis(500));
        fadeInTransition.play();
        gameLogic.getAnimationList().add(fadeInTransition);
        fadeInTransition.setOnFinished(event -> {
            FlameAnimation();
            gameLogic.getAnimationList().remove(fadeInTransition);
//            System.out.println("PERFECT");
//            System.out.println(rectangle.getLayoutX());
//            System.out.println(rectangle.getLayoutY());
        });

    }
    private Timeline timelineFlame;
    private void FlameAnimation() {
        Timeline timelineOnceFLame=new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(rectangle.opacityProperty(), rectangle.getOpacity())),
                new KeyFrame(Duration.millis(1000), new KeyValue(rectangle.opacityProperty(), 0.5))
        );
        timelineOnceFLame.play();
        timelineFlame= new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(rectangle.opacityProperty(), 0.5)),
                new KeyFrame(Duration.millis(1000), new KeyValue(rectangle.opacityProperty(), 1))
        );
        timelineFlame.setCycleCount(Animation.INDEFINITE);
        timelineFlame.setAutoReverse(true);
        timelineFlame.setOnFinished(event -> {
//            gameLogic.getAnimationList().remove(timelineFlame);
        });
        timelineFlame.play();
//        gameLogic.getAnimationList().add(timelineFlame);
    }
    public void animationFadeOut(){
        timelineFlame.stop();
        Timeline timelineFadeOut=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(rectangle.opacityProperty(),rectangle.getOpacity())),
                new KeyFrame(Duration.millis(50),new KeyValue(rectangle.opacityProperty(),0))
        );
//        timelineFadeOut.setDelay(Duration.millis(50));
        timelineFadeOut.setOnFinished(event -> {
//            rectangle.setVisible(false);
            gameLogic.getAnimationList().remove(timelineFadeOut);
        });
        timelineFadeOut.play();
        gameLogic.getAnimationList().add(timelineFadeOut);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void perfectAnimation(){
        ImageView leftStripe=new ImageView(new Image("file:src/main/resources/images/perfect_stripe.jpg"));
        ImageView rightStripe=new ImageView(new Image("file:src/main/resources/images/perfect_stripe.jpg"));
        Text perfectText=new Text("perfect");
        Text ghostText=new Text("*But here's a ghost for you*");
        perfectText.getStyleClass().add("pac-man");
        ghostText.getStyleClass().add("pixeboy");
        perfectText.setFill(Color.YELLOW);
        ghostText.setFill(Color.RED);
        perfectText.setLayoutX(440);
        ghostText.setLayoutX(470);
        perfectText.setLayoutY(216);
        ghostText.setLayoutY(250);
        perfectText.setFont(new Font(50));
        ghostText.setFont(new Font(15));
        perfectText.setStrokeType(StrokeType.INSIDE);
        ghostText.setStrokeType(StrokeType.INSIDE);
        perfectText.setStrokeWidth(100);
        ghostText.setStrokeWidth(100);
        perfectText.setOpacity(0);
        ghostText.setOpacity(0);
        leftStripe.setLayoutX(0);
        leftStripe.setLayoutY(172);
        leftStripe.setFitWidth(434);
        leftStripe.setFitHeight(44);
        leftStripe.setOpacity(0);
        rightStripe.setLayoutX(690);
        rightStripe.setLayoutY(172);
        rightStripe.setFitWidth(434);
        rightStripe.setFitHeight(44);
        rightStripe.setOpacity(0);
        if(gameLogic.getNext_platform().getRectangle().getBoundsInParent().getMinX()-gameLogic.getCur_platform().getRectangle().getBoundsInParent().getMaxX()>gameLogic.getPacman().getPacman().getPacman_costume().getFitWidth()*4){
            addGhost();
        }
        else{
            ghostText.setVisible(false);
        }
        Timeline timelineLeftStripe=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(leftStripe.layoutXProperty(),-434,Interpolator.EASE_IN),new KeyValue(leftStripe.opacityProperty(),1)),
                new KeyFrame(Duration.millis(500),new KeyValue(leftStripe.layoutXProperty(),0,Interpolator.EASE_IN),new KeyValue(leftStripe.opacityProperty(),1)),
                new KeyFrame(Duration.millis(1500),new KeyValue(leftStripe.layoutXProperty(),0,Interpolator.EASE_IN),new KeyValue(leftStripe.opacityProperty(),1)),
                new KeyFrame(Duration.millis(1800),new KeyValue(leftStripe.layoutXProperty(),-434,Interpolator.EASE_IN),new KeyValue(leftStripe.opacityProperty(),1))
        );
        timelineLeftStripe.setOnFinished(event -> {
            gameLogic.getAnimationList().remove(timelineLeftStripe);
        });
        timelineLeftStripe.play();
        gameLogic.getAnimationList().add(timelineLeftStripe);
        Timeline timelineRightStripe=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(rightStripe.layoutXProperty(),1080,Interpolator.EASE_IN),new KeyValue(rightStripe.opacityProperty(),1)),
                new KeyFrame(Duration.millis(500),new KeyValue(rightStripe.layoutXProperty(),690,Interpolator.EASE_IN),new KeyValue(rightStripe.opacityProperty(),1)),
                new KeyFrame(Duration.millis(1500),new KeyValue(rightStripe.layoutXProperty(),690,Interpolator.EASE_IN),new KeyValue(rightStripe.opacityProperty(),1)),
                new KeyFrame(Duration.millis(1800),new KeyValue(rightStripe.layoutXProperty(),1080,Interpolator.EASE_IN),new KeyValue(rightStripe.opacityProperty(),1) )
        );
        timelineRightStripe.setOnFinished(event -> {
            gameLogic.getAnimationList().remove(timelineRightStripe);
        });
        timelineRightStripe.play();
        gameLogic.getAnimationList().add(timelineRightStripe);
        Timeline timelineText=new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(perfectText.opacityProperty(),0,Interpolator.DISCRETE),new KeyValue(ghostText.opacityProperty(),0,Interpolator.DISCRETE)),
                new KeyFrame(Duration.millis(300),new KeyValue(perfectText.opacityProperty(),1,Interpolator.DISCRETE),new KeyValue(ghostText.opacityProperty(),1,Interpolator.DISCRETE)),
                new KeyFrame(Duration.millis(1500),new KeyValue(perfectText.opacityProperty(),1,Interpolator.DISCRETE),new KeyValue(ghostText.opacityProperty(),1,Interpolator.DISCRETE)),
                new KeyFrame(Duration.millis(1800),new KeyValue(perfectText.opacityProperty(),0,Interpolator.DISCRETE),new KeyValue(ghostText.opacityProperty(),0,Interpolator.DISCRETE))
        );
        timelineText.setOnFinished(event -> {
            gameLogic.getAnimationList().remove(timelineText);
            rightStripe.setOpacity(0);
            leftStripe.setOpacity(0);
            perfectText.setOpacity(0);
        });
        timelineText.play();
        gameLogic.getAnimationList().add(timelineText);
        gamePane.getChildren().addAll(leftStripe,rightStripe,perfectText,ghostText);
        gameLogic.getScore().setText(String.valueOf(Integer.parseInt(gameLogic.getScore().getText())+1));
        gameLogic.getScore().setWrappingWidth(gameLogic.getScore().getText().length()*55);
        if(gameLogic.getScore().getText().length()!=(String.valueOf(Integer.parseInt(gameLogic.getScore().getText())-1)).length()) {
            gameLogic.getScore().setLayoutX(gameLogic.getScore().getLayoutX() - (gameLogic.getScore().getWrappingWidth() - 55));
        }

    }

    public void setGameLogic(Game_Logic gameLogic) {
        this.gameLogic = gameLogic;
    }

    public void setAnchorPane(AnchorPane gamePane) {
        this.gamePane=gamePane;

    }

    public void addGhost(){
        ImageView ghostImage=new ImageView(new Image("file:src/main/resources/images/ghost.png"));
        ghostImage.setId("Ghost");
        ghostImage.setLayoutX(this.getRectangle().getLayoutX()+5);
        ghostImage.setLayoutY(this.getRectangle().getLayoutY()+16);
        ghostImage.setFitWidth(gameLogic.getPacman().getPacman().getPacman_costume().getFitWidth());
        ghostImage.setFitHeight(gameLogic.getPacman().getPacman().getPacman_costume().getFitHeight());
        ghostImage.setOpacity(0);
        Ghost ghost=new Ghost(ghostImage,gameLogic);
        gamePane.getChildren().add(ghostImage);
        Timeline timelineIncreaseWidth=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(rectangle.layoutXProperty(),rectangle.getLayoutX()),new KeyValue(rectangle.widthProperty(),rectangle.getWidth())),
                new KeyFrame(Duration.millis(300),new KeyValue(rectangle.layoutXProperty(),rectangle.getLayoutX()-(50-rectangle.getWidth())/2),new KeyValue(rectangle.widthProperty(),50))
        );
//        timelineIncreaseWidth.setOnFinished(ghost::animateFadeIn);
        timelineIncreaseWidth.setOnFinished(event -> {
            gameLogic.getAnimationList().remove(timelineIncreaseWidth);
            ghost.animateFadeIn(event);
            Timeline timelineDecreaseWidth=new Timeline(
                    new KeyFrame(Duration.ZERO,new KeyValue(rectangle.layoutXProperty(),rectangle.getLayoutX()),new KeyValue(rectangle.widthProperty(),rectangle.getWidth())),
                    new KeyFrame(Duration.millis(300),new KeyValue(rectangle.layoutXProperty(),rectangle.getLayoutX()+(rectangle.getWidth()-15)/2),new KeyValue(rectangle.widthProperty(),15))
            );
            timelineDecreaseWidth.setOnFinished(event1 -> {
                gameLogic.getAnimationList().remove(timelineDecreaseWidth);
            });
            timelineDecreaseWidth.setDelay(Duration.millis(100));
            timelineDecreaseWidth.play();
            gameLogic.getAnimationList().add(timelineDecreaseWidth);
        });
        timelineIncreaseWidth.play();
        gameLogic.getAnimationList().add(timelineIncreaseWidth);




        this.getRectangle().toFront();
    }

    public void removeGhost(ActionEvent event){

        gamePane.getChildren().remove(gamePane.lookup("Ghost"));
    }
}

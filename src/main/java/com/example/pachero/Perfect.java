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
        ImageView leftStripe=new ImageView(new Image("file:/home/somay/IdeaProjects/Pac-hero/src/main/resources/images/perfect_stripe.jpg"));
        ImageView rightStripe=new ImageView(new Image("file:/home/somay/IdeaProjects/Pac-hero/src/main/resources/images/perfect_stripe.jpg"));
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
        addGhost();
        Timeline timelineLeftStripe=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(leftStripe.layoutXProperty(),-434,Interpolator.EASE_IN),new KeyValue(leftStripe.opacityProperty(),1)),
                new KeyFrame(Duration.millis(500),new KeyValue(leftStripe.layoutXProperty(),0,Interpolator.EASE_IN),new KeyValue(leftStripe.opacityProperty(),1)),
                new KeyFrame(Duration.millis(1500),new KeyValue(leftStripe.layoutXProperty(),0,Interpolator.EASE_IN),new KeyValue(leftStripe.opacityProperty(),1)),
                new KeyFrame(Duration.millis(1800),new KeyValue(leftStripe.layoutXProperty(),-434,Interpolator.EASE_IN),new KeyValue(leftStripe.opacityProperty(),1))
        );
        timelineLeftStripe.play();
        Timeline timelineRightStripe=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(rightStripe.layoutXProperty(),1080,Interpolator.EASE_IN),new KeyValue(rightStripe.opacityProperty(),1)),
                new KeyFrame(Duration.millis(500),new KeyValue(rightStripe.layoutXProperty(),690,Interpolator.EASE_IN),new KeyValue(rightStripe.opacityProperty(),1)),
                new KeyFrame(Duration.millis(1500),new KeyValue(rightStripe.layoutXProperty(),690,Interpolator.EASE_IN),new KeyValue(rightStripe.opacityProperty(),1)),
                new KeyFrame(Duration.millis(1800),new KeyValue(rightStripe.layoutXProperty(),1080,Interpolator.EASE_IN),new KeyValue(rightStripe.opacityProperty(),1) )
        );
        timelineRightStripe.play();
        Timeline timelineText=new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(perfectText.opacityProperty(),0,Interpolator.DISCRETE),new KeyValue(ghostText.opacityProperty(),0,Interpolator.DISCRETE)),
                new KeyFrame(Duration.millis(300),new KeyValue(perfectText.opacityProperty(),1,Interpolator.DISCRETE),new KeyValue(ghostText.opacityProperty(),1,Interpolator.DISCRETE)),
                new KeyFrame(Duration.millis(1500),new KeyValue(perfectText.opacityProperty(),1,Interpolator.DISCRETE),new KeyValue(ghostText.opacityProperty(),1,Interpolator.DISCRETE)),
                new KeyFrame(Duration.millis(1800),new KeyValue(perfectText.opacityProperty(),0,Interpolator.DISCRETE),new KeyValue(ghostText.opacityProperty(),0,Interpolator.DISCRETE))
        );
        timelineText.play();
        gamePane.getChildren().addAll(leftStripe,rightStripe,perfectText,ghostText);
        gameLogic.getScore().setText(String.valueOf(Integer.parseInt(gameLogic.getScore().getText())+1));
        rightStripe.setOpacity(0);
        leftStripe.setOpacity(0);
        perfectText.setOpacity(0);
    }

    public void setGameLogic(Game_Logic gameLogic) {
        this.gameLogic = gameLogic;
    }

    public void setAnchorPane(AnchorPane gamePane) {
        this.gamePane=gamePane;

    }

    public void addGhost(){
        ImageView ghostImage=new ImageView(new Image("file:/home/somay/IdeaProjects/Pac-hero/src/main/resources/images/ghost.png"));
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
            ghost.animateFadeIn(event);
            Timeline timelineDecreaseWidth=new Timeline(
                    new KeyFrame(Duration.ZERO,new KeyValue(rectangle.layoutXProperty(),rectangle.getLayoutX()),new KeyValue(rectangle.widthProperty(),rectangle.getWidth())),
                    new KeyFrame(Duration.millis(300),new KeyValue(rectangle.layoutXProperty(),rectangle.getLayoutX()+(rectangle.getWidth()-15)/2),new KeyValue(rectangle.widthProperty(),15))
            );
            timelineDecreaseWidth.setDelay(Duration.millis(100));
            timelineDecreaseWidth.play();
        });
        timelineIncreaseWidth.play();




        this.getRectangle().toFront();
    }

    public void removeGhost(ActionEvent event){

        gamePane.getChildren().remove(gamePane.lookup("Ghost"));
    }
}

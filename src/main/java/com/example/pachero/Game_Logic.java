package com.example.pachero;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

public class Game_Logic {
    private Pacman pacman;
    private Platform cur_platform;
    private Platform next_platform;
    private Perfect perfect;
    private Pacman_Stick pacmanStick;
    private Ghost ghost;
    private Cherry cherry;
    private Scene scene;
    private Text score;
    private Text cherries;
    private AnchorPane gamePane;

    private int highScore;
    private int highScoreAnimation=0;

    private AnchorPane gameOverMenu;


    public Game_Logic(Pacman pacman, Platform cur_platform, Platform next_platform, Perfect perfect, Pacman_Stick pacmanStick, AnchorPane gamePane, Text score,Text cherries,AnchorPane gameOverMenu) {
        this.gamePane=gamePane;
        this.pacman = pacman;
        this.cur_platform = cur_platform;
        this.next_platform = next_platform;
        this.perfect = perfect;
        this.pacmanStick = pacmanStick;
        this.score=score;
        this.cherries=cherries;
        this.gameOverMenu =gameOverMenu;
    }

    public void GameStop(){
    }

    public void GamePause(){
    }

    public void GamePlay(){
    }

    public void getData(){
        try {
            Path path = Paths.get("/home/somay/IdeaProjects/Pac-hero/src/main/resources/Data/HighScoreData.txt");
            Scanner scanner = new Scanner(path);
            highScore=scanner.nextInt();
            scanner.close();
        }catch (Exception e){
           highScore=0;
        }
        try {
            Path path = Paths.get("/home/somay/IdeaProjects/Pac-hero/src/main/resources/Data/CherryCountData.txt");
            Scanner scanner = new Scanner(path);
            int cherryCount=scanner.nextInt();
            cherries.setText(String.valueOf(cherryCount));
            cherries.setWrappingWidth(cherries.getText().length()*50);
            cherries.setLayoutX(945-(cherries.getBoundsInParent().getMaxX()-cherries.getBoundsInParent().getMinX()));
            scanner.close();
        }catch (Exception e){
            cherries.setText(String.valueOf(0));
        }
    }

    public void setData(){
        try {
            if(Integer.parseInt(score.getText())>highScore) {
                FileWriter myWriter = new FileWriter("/home/somay/IdeaProjects/Pac-hero/src/main/resources/Data/HighScoreData.txt");
                myWriter.write(score.getText());
                myWriter.close();
//            System.out.println("Successfully wrote to the file.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }

        try {
            FileWriter myWriter = new FileWriter("/home/somay/IdeaProjects/Pac-hero/src/main/resources/Data/CherryCountData.txt");
            myWriter.write(cherries.getText());
            myWriter.close();
//            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    public void GameStart() {
        getData();
        next_platform.setRectangle(next_platform.newRectangle() );
        gamePane.getChildren().add(next_platform.getRectangle());
        perfect.relocate(next_platform.getRectangle().getLayoutX()+(next_platform.getRectangle().getWidth()/2)-(perfect.getRectangle().getWidth()/2));
        pacmanStick.getLine().toFront();
        cur_platform.animateRectangleFadeIn();
        next_platform.animateRectangleFadeIn();
        perfect.animationFadeIn();
        pacman.animationFadeIn();
        setStopKeyboard(0);
    }

    public void pacmanPositionCheck(ActionEvent event){
        if(next_platform.getRectangle().getLayoutX()<=pacman.getPacman().getPacman_costume().getLayoutX() && pacman.getPacman().getPacman_costume().getLayoutX()<=next_platform.getRectangle().getBoundsInParent().getMaxX() && pacman.getPacman().getPacman_costume().getLayoutY()==pacman.getStartLayoutY()){
            pacman.goToStart();
            perfect.animationFadeOut();
            pacmanStick.goToStart();
            next_platform.relocate(cur_platform.getRectangle().getLayoutX()+cur_platform.getRectangle().getWidth()-next_platform.getRectangle().getWidth());
            cur_platform.animateRectangleFadeout();
            cur_platform.setRectangle(next_platform.getRectangle());
            next_platform.setRectangle(next_platform.newRectangle());
            gamePane.getChildren().add(next_platform.getRectangle());
            perfect.relocate(next_platform.getRectangle().getLayoutX()+(next_platform.getRectangle().getWidth()/2)-(perfect.getRectangle().getWidth()/2));
            perfect.animationFadeIn();
            next_platform.animateRectangleFadeIn();
            perfect.getRectangle().toFront();
            pacmanStick.getLine().toFront();
            score.setText(String.valueOf(Integer.parseInt(score.getText())+1));
            score.setWrappingWidth(score.getText().length()*55);
            if(score.getText().length()!=(String.valueOf(Integer.parseInt(score.getText())-1)).length()) {
                score.setLayoutX(score.getLayoutX() - (score.getWrappingWidth() - 55));
            }
            if(Integer.parseInt(score.getText())>highScore && highScoreAnimation==0){
                highScoreAnimation=1;
                highScoreAnimation();
                highScore=Integer.parseInt(score.getText());
            }
//            rotated=0;
//            key_pressed=0;

        }else{
            pacman.goIntoOblivion();
        }
    }

    private void highScoreAnimation() {
        ImageView leftStripe=new ImageView(new Image("file:/home/somay/IdeaProjects/Pac-hero/src/main/resources/images/perfect_stripe.jpg"));
        ImageView rightStripe=new ImageView(new Image("file:/home/somay/IdeaProjects/Pac-hero/src/main/resources/images/perfect_stripe.jpg"));
        Text highScoreText=new Text("highscore");
        highScoreText.getStyleClass().add("pac-man");
        highScoreText.setFill(Color.YELLOW);
        highScoreText.setLayoutX(425);
        highScoreText.setLayoutY(216);
        highScoreText.setFont(new Font(50));
        highScoreText.setStrokeType(StrokeType.INSIDE);
        highScoreText.setStrokeWidth(100);
        highScoreText.setOpacity(0);
        leftStripe.setLayoutX(0);
        leftStripe.setLayoutY(172);
        leftStripe.setFitWidth(434);
        leftStripe.setFitHeight(44);
        leftStripe.setOpacity(0);
        rightStripe.setLayoutX(900);
        rightStripe.setLayoutY(172);
        rightStripe.setFitWidth(434);
        rightStripe.setFitHeight(44);
        rightStripe.setOpacity(0);
        Timeline timelineLeftStripe=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(leftStripe.layoutXProperty(),-434, Interpolator.EASE_IN),new KeyValue(leftStripe.opacityProperty(),1)),
                new KeyFrame(Duration.millis(500),new KeyValue(leftStripe.layoutXProperty(),-15,Interpolator.EASE_IN),new KeyValue(leftStripe.opacityProperty(),1)),
                new KeyFrame(Duration.millis(1500),new KeyValue(leftStripe.layoutXProperty(),-15,Interpolator.EASE_IN),new KeyValue(leftStripe.opacityProperty(),1)),
                new KeyFrame(Duration.millis(1800),new KeyValue(leftStripe.layoutXProperty(),-434,Interpolator.EASE_IN),new KeyValue(leftStripe.opacityProperty(),1))
        );
        timelineLeftStripe.play();
        Timeline timelineRightStripe=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(rightStripe.layoutXProperty(),1080,Interpolator.EASE_IN),new KeyValue(rightStripe.opacityProperty(),1)),
                new KeyFrame(Duration.millis(500),new KeyValue(rightStripe.layoutXProperty(),755,Interpolator.EASE_IN),new KeyValue(rightStripe.opacityProperty(),1)),
                new KeyFrame(Duration.millis(1500),new KeyValue(rightStripe.layoutXProperty(),755,Interpolator.EASE_IN),new KeyValue(rightStripe.opacityProperty(),1)),
                new KeyFrame(Duration.millis(1800),new KeyValue(rightStripe.layoutXProperty(),1080,Interpolator.EASE_IN),new KeyValue(rightStripe.opacityProperty(),1) )
        );
        timelineRightStripe.play();
        Timeline timelineText=new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(highScoreText.opacityProperty(),0,Interpolator.DISCRETE)),
                new KeyFrame(Duration.millis(300),new KeyValue(highScoreText.opacityProperty(),1,Interpolator.DISCRETE)),
                new KeyFrame(Duration.millis(1500),new KeyValue(highScoreText.opacityProperty(),1,Interpolator.DISCRETE)),
                new KeyFrame(Duration.millis(1800),new KeyValue(highScoreText.opacityProperty(),0,Interpolator.DISCRETE))
        );
        timelineText.play();
        gamePane.getChildren().addAll(leftStripe,rightStripe,highScoreText);
        rightStripe.setOpacity(0);
        leftStripe.setOpacity(0);
        highScoreText.setOpacity(0);
    }

    public void gameEndMenu(){
        gameOverMenu.toFront();
        score.toFront();
        cherries.toFront();
//        System.out.println(gamePane.getChildren());
        gameOverMenu.setOpacity(0);
        gameOverMenu.setVisible(true);
        Timeline timelineGameOverMenuFade=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(gameOverMenu.opacityProperty(),0)),
                new KeyFrame(Duration.millis(300),new KeyValue(gameOverMenu.opacityProperty(),1))
        );
        timelineGameOverMenuFade.play();
    }

    public void restartGame(){
        pacman.restart();
        pacmanStick.goToStart();

        setStopKeyboard(0);
        resetKeyboard(new ActionEvent());
        getData();
        score.setText(String.valueOf(0));
    }

    public void resurrectGame(){
        pacman.restart();
        pacmanStick.goToStart();
        setStopKeyboard(0);
        resetKeyboard(new ActionEvent());
        getData();
    }

    private int pacmanRotated =0;
    private int key_pressed=0;
    private int pacmanDown=0;
    private int pacmanUp=1;
    private int stopKeyboard=0;
    public void resetKeyboard(ActionEvent event){
        pacmanRotated =0;
        key_pressed=0;
    }
//KEY BOARD CONTROLS
    public void keyboardControl(KeyEvent keyEvent) {
//        System.out.println(stopKeyboard);
        if(stopKeyboard==0) {
            if (pacmanRotated == 0) {
                if (keyEvent.getCode() == KeyCode.SPACE) {
                    pacman.rotate(-90);
                    pacmanRotated = 1;
                }
            }
            if (key_pressed == 0) {
                if (keyEvent.getEventType() == KeyEvent.KEY_RELEASED && keyEvent.getCode() == KeyCode.SPACE) {
                    double cherryRandom=new Random().nextDouble();
                    if(cherryRandom<0.8) {
                        cherry = new Cherry();
                        cherry.setGameLogic(this);
                        cherry.addCherry();
                    }
                    key_pressed = 1;
                    pacmanStick.StopIncreaseLength();
                    pacman.rotateBack(90);
                    pacman.relocate(pacmanStick.getLine().getLayoutX() + Math.abs(pacmanStick.getLine().getEndY()) + 37);

                }
                if (keyEvent.getCode() == KeyCode.SPACE && keyEvent.getEventType() != KeyEvent.KEY_RELEASED) {
                    pacmanStick.IncreaseLength();
                }
            }
            if (pacmanRotated == 1 && key_pressed == 1) {
                if (keyEvent.getCode() == KeyCode.DOWN && pacmanDown == 0 && pacmanUp == 1) {

                    Timeline timelineGoDown = new Timeline(
                            new KeyFrame(Duration.ZERO, new KeyValue(pacman.getPacman().getPacman_costume().layoutYProperty(), pacman.getPacman().getPacman_costume().getLayoutY())),
                            new KeyFrame(Duration.millis(100), new KeyValue(pacman.getPacman().getPacman_costume().layoutYProperty(), pacman.getPacman().getPacman_costume().getLayoutY() + 50))
                    );
                    timelineGoDown.play();
//                pacman.getPacman().getPacman_costume().setLayoutY(pacman.getPacman().getPacman_costume().getLayoutY()+50);
                    pacmanDown = 1;
                    pacmanUp = 0;
                }
                if (keyEvent.getCode() == KeyCode.UP && pacmanDown == 1 && pacmanUp == 0) {
                    Timeline timelineGoUp = new Timeline(
                            new KeyFrame(Duration.ZERO, new KeyValue(pacman.getPacman().getPacman_costume().layoutYProperty(), pacman.getPacman().getPacman_costume().getLayoutY())),
                            new KeyFrame(Duration.millis(100), new KeyValue(pacman.getPacman().getPacman_costume().layoutYProperty(), pacman.getPacman().getPacman_costume().getLayoutY() - 50))
                    );
                    timelineGoUp.play();
//                pacman.getPacman().getPacman_costume().setLayoutY(pacman.getPacman().getPacman_costume().getLayoutY()-50);
                    pacmanDown = 0;
                    pacmanUp = 1;
                }
            }
        }
    }



    public Pacman getPacman() {
        return pacman;
    }

    public void setPacman(Pacman pacman) {
        this.pacman = pacman;
    }

    public Platform getCur_platform() {
        return cur_platform;
    }

    public void setCur_platform(Platform cur_platform) {
        this.cur_platform = cur_platform;
    }

    public Platform getNext_platform() {
        return next_platform;
    }

    public void setNext_platform(Platform next_platform) {
        this.next_platform = next_platform;
    }

    public Perfect getPerfect() {
        return perfect;
    }

    public void setPerfect(Perfect perfect) {
        this.perfect = perfect;
    }

    public Pacman_Stick getPacmanStick() {
        return pacmanStick;
    }

    public void setPacmanStick(Pacman_Stick pacmanStick) {
        this.pacmanStick = pacmanStick;
    }

    public Ghost getGhost() {
        return ghost;
    }

    public void setGhost(Ghost ghost) {
        this.ghost = ghost;
    }

    public Cherry getCherry() {
        return cherry;
    }

    public void setCherry(Cherry cherry) {
        this.cherry = cherry;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Text getScore() {
        return score;
    }

    public void setScore(Text score) {
        this.score = score;
    }

    public AnchorPane getGamePane() {
        return gamePane;
    }

    public void setGamePane(AnchorPane gamePane) {
        this.gamePane = gamePane;
    }

    public int getPacmanRotated() {
        return pacmanRotated;
    }

    public void setPacmanRotated(int pacmanRotated) {
        this.pacmanRotated = pacmanRotated;
    }

    public int getKey_pressed() {
        return key_pressed;
    }

    public void setKey_pressed(int key_pressed) {
        this.key_pressed = key_pressed;
    }

    public void setStopKeyboard(int stopKeyboard) {
        this.stopKeyboard = stopKeyboard;
    }


    public Text getCherries() {
        return cherries;
    }
}

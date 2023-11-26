package com.example.pachero;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

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
    private AnchorPane gamePane;

    public Game_Logic(Pacman pacman, Platform cur_platform, Platform next_platform, Perfect perfect, Pacman_Stick pacmanStick, AnchorPane gamePane, Text score) {
        this.gamePane=gamePane;
        this.pacman = pacman;
        this.cur_platform = cur_platform;
        this.next_platform = next_platform;
        this.perfect = perfect;
        this.pacmanStick = pacmanStick;
        this.score=score;
    }

    public void GameStop(){
    }

    public void GamePause(){
    }

    public void GamePlay(){
    }

    public void GameStart() {
        next_platform.setRectangle(next_platform.newRectangle());
        gamePane.getChildren().add(next_platform.getRectangle());
        perfect.relocate(next_platform.getRectangle().getLayoutX()+(next_platform.getRectangle().getWidth()/2)-(perfect.getRectangle().getWidth()/2));
//        perfect.animationFadeIn();
        pacmanStick.getLine().toFront();
        cur_platform.animateRectangleFadeIn();
        next_platform.animateRectangleFadeIn();
        perfect.animationFadeIn();
        pacman.animationFadeIn();
    }

    public void pacmanPositionCheck(ActionEvent event){
        if(next_platform.getRectangle().getLayoutX()<pacman.getPacman().getPacman_costume().getLayoutX() && pacman.getPacman().getPacman_costume().getLayoutX()<=next_platform.getRectangle().getLayoutX()+next_platform.getRectangle().getWidth()){
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
//            rotated=0;
//            key_pressed=0;

        }else{
            pacman.goIntoOblivion();
        }
    }
    private int rotated=0;
    private int key_pressed=0;
    public void resetKeyboard(ActionEvent event){
        rotated=0;
        key_pressed=0;
    }
//KEY BOARD CONTROLS
    public void keyboardControl(KeyEvent keyEvent) {
        if(rotated==0) {
            if (keyEvent.getCode() == KeyCode.SPACE) {
                pacman.rotate(-90);
                rotated=1;
            }
        }
        if(key_pressed==0){
            if(keyEvent.getEventType()==KeyEvent.KEY_RELEASED && keyEvent.getCode() == KeyCode.SPACE ){
                key_pressed=1;
                pacmanStick.StopIncreaseLength();
                pacman.rotateBack(90);
                pacman.relocate(pacmanStick.getLine().getLayoutX()+Math.abs(pacmanStick.getLine().getEndY())+37);
            }
            if(keyEvent.getCode() == KeyCode.SPACE) {
                pacmanStick.IncreaseLength();
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

    public int getRotated() {
        return rotated;
    }

    public void setRotated(int rotated) {
        this.rotated = rotated;
    }

    public int getKey_pressed() {
        return key_pressed;
    }

    public void setKey_pressed(int key_pressed) {
        this.key_pressed = key_pressed;
    }
}

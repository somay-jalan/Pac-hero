package com.example.pachero;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Game_Logic {
    private Pacman pacman;
    private Platform cur_platform;
    private Platform next_platform;
    private Perfect perfect;
    private Pacman_Stick pacmanStick;
    private Ghost ghost;
    private Cherry cherry;
    private Scene scene;


    public Game_Logic(Pacman pacman, Platform cur_platform, Platform next_platform, Perfect perfect, Pacman_Stick pacmanStick) {
        this.pacman = pacman;
        this.cur_platform = cur_platform;
        this.next_platform = next_platform;
        this.perfect = perfect;
        this.pacmanStick = pacmanStick;
    }

    public void GameStop(){
    }

    public void GamePause(){
    }

    public void GamePlay(){
    }

    public void GameStart() {
        cur_platform.animateRectangleFadein();
        next_platform.animateRectangleFadein();
        perfect.animationFadeIn();
        pacman.animationFadeIn();
    }


    int rotated=0;
    int key_pressed=0;
    public void keyboardControl(KeyEvent keyEvent) {
//        System.out.println(keyEvent);
        if(rotated==0) {
            if (keyEvent.getCode() == KeyCode.SPACE) {
                pacman.rotate(-90);
                rotated=1;
            }
        }
        if(key_pressed==0){
            if(keyEvent.getEventType()==KeyEvent.KEY_RELEASED){
                key_pressed=1;
                pacmanStick.StopIncreaseLength();
                pacman.rotateBack(90);
                pacman.relocate(pacmanStick.getLine().getLayoutX()+Math.abs(pacmanStick.getLine().getEndY()));
            }
            if(keyEvent.getCode() == KeyCode.SPACE) {
                pacmanStick.IncreaseLength();
            }
        }
    }
}

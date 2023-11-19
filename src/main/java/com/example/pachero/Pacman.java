package com.example.pachero;

import javafx.scene.image.ImageView;

public class Pacman {

    private double x;
    private double y;

    private ImageView pacman;
    public Pacman(ImageView pacman){
        this.pacman=pacman;
    }

    public void relocate(double v,double v1){
        pacman.relocate(v,v1);
    }

    public void rotate(double angle){
    }

    public void rotateBack(double angle){
    }

    public void goDown(){
    }

    public void goUp(){
    }

    public void costumeChange(Costume costume){

    }
}

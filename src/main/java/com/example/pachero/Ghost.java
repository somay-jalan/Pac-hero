package com.example.pachero;


import javafx.scene.image.ImageView;

public class Ghost{
    private double x;
    private double y;

    private final ImageView ghost;
    public Ghost(ImageView ghost){
        this.ghost=ghost;
    }

    public void relocate(double v,double v1){
        ghost.relocate(v,v1);
    }

    public void animateFadeIn(){
    }

    public void animateFadeOut(){
    }


}

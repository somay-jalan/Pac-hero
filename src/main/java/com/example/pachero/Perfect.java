package com.example.pachero;

import javafx.scene.shape.Rectangle;

public class Perfect {

    private double x;
    private double y;
    private final Rectangle rectangle;
    public Perfect(Rectangle rectangle){
        this.rectangle=rectangle;
    }

    public void relocate(double v, double v1) {
        rectangle.relocate(v, v1);
    }

    public void animationFadeIn(){
    }
    public void animationFadeOut(){
    }

}

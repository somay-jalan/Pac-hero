package com.example.pachero;

import javafx.scene.shape.Rectangle;

public class Platform {


    private double x;
    private double y;
    private final Rectangle rectangle;
    public Platform(Rectangle rectangle){
        this.rectangle=rectangle;
    }

    public void animateRectangleFadein(){
    }

    public void relocate(double v, double v1) {
        rectangle.relocate(v, v1);
    }

    public void animateRectangleFadeout(){
    }
}

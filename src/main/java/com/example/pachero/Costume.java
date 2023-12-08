package com.example.pachero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;

public class Costume implements Serializable {

    private ImageView pacman_costume;
    private String close_url;

    private String open_url;

    public Costume(ImageView pacman_costume_open){
        this.pacman_costume=pacman_costume_open;
    }

    public ImageView getPacman_costume() {
        return pacman_costume;
    }

    public void changeToClose(){
        pacman_costume.setImage(new Image(getClose_url()));
    }

    public void changeToOpen(){
        pacman_costume.setImage(new Image(getOpen_url()));
    }

    public String getClose_url() {
        return close_url;
    }

    public String getOpen_url() {
        return open_url;
    }

    public void setPacman_costume(ImageView pacman_costume) {
        this.pacman_costume = pacman_costume;
    }

    public void setClose_url(String close_url) {
        this.close_url = close_url;
    }

    public void setOpen_url(String open_url) {
        this.open_url = open_url;
    }
}

package com.example.pachero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Costume {

    private ImageView pacman_costume;
    private String close_url;

    private String open_url;

    public Costume(ImageView pacman_costume_open){
        this.pacman_costume=pacman_costume_open;
        open_url=this.pacman_costume.getImage().getUrl();
        close_url=open_url.replace("open","close");
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
}

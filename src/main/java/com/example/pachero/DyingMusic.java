package com.example.pachero;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class DyingMusic implements Runnable {
    @Override
    public void run() {
        String dying_audio = "src/main/resources/music/PacDying.mp3";
        Media dying = new Media(new File(dying_audio).toURI().toString());
        MediaPlayer dyingPlayer = new MediaPlayer(dying);
        dyingPlayer.setVolume(0.50);
        dyingPlayer.play();
    }
}

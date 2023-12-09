package com.example.pachero;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class StickDropMusic implements Runnable {
    @Override
    public void run() {
        String drop_audio = "src/main/resources/music/StickDrop.mp3";
        Media drop = new Media(new File(drop_audio).toURI().toString());
        MediaPlayer dropAudioPlayer = new MediaPlayer(drop);
        dropAudioPlayer.setVolume(1);
        dropAudioPlayer.play();
    }
}
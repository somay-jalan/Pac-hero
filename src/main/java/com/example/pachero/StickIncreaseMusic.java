package com.example.pachero;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class StickIncreaseMusic implements Runnable {
    @Override
    public void run() {
        String stick_inc = "src/main/resources/music/stickIncrease.mp3";
        Media stick_inc_audio = new Media(new File(stick_inc).toURI().toString());
        MediaPlayer IncreaseAudioPlayer = new MediaPlayer(stick_inc_audio);
        IncreaseAudioPlayer.setVolume(0.50);
        IncreaseAudioPlayer.play();
    }
}
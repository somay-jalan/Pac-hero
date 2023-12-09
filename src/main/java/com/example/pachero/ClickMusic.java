package com.example.pachero;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class ClickMusic implements Runnable {
    @Override
    public void run() {
        String click = "src/main/resources/music/button.mp3";
        Media click_audio = new Media(new File(click).toURI().toString());
        MediaPlayer clickAudioPlayer = new MediaPlayer(click_audio);
        clickAudioPlayer.setVolume(0.50);
        clickAudioPlayer.play();
    }
}
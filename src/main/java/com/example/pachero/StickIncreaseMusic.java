package com.example.pachero;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class StickIncreaseMusic implements Runnable {
    private  MediaPlayer IncreaseAudioPlayer;
    @Override
    public void run() {
        String stick_inc = "src/main/resources/music/stickIncrease.mp3";
        Media stick_inc_audio = new Media(new File(stick_inc).toURI().toString());
        IncreaseAudioPlayer = new MediaPlayer(stick_inc_audio);
        IncreaseAudioPlayer.setVolume(0);
        Timeline timelineStickIncMusic=new Timeline(
                new KeyFrame(Duration.ZERO, actionEvent -> {
                    IncreaseAudioPlayer.play();
                }),
                new KeyFrame(Duration.seconds(60),actionEvent -> {
                    IncreaseAudioPlayer.play();
                })
        );
        timelineStickIncMusic.setCycleCount(Timeline.INDEFINITE);
        timelineStickIncMusic.play();
    }

    public MediaPlayer getIncreaseAudioPlayer() {
        return IncreaseAudioPlayer;
    }
}
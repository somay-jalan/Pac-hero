package com.example.pachero;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;


public class RewardMusic implements Runnable {
    @Override
    public void run() {
        String cherry_audio = "src/main/resources/music/Reward.mp3";
        Media cherry = new Media(new File(cherry_audio).toURI().toString());
        MediaPlayer cherryRewardPlayer = new MediaPlayer(cherry);
        cherryRewardPlayer.setVolume(0.50);
        cherryRewardPlayer.play();
    }
}


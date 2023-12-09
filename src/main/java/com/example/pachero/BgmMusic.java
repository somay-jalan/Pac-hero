package com.example.pachero;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class BgmMusic implements Runnable {
    @Override
    public void run() {
            String audio = "src/main/resources/music/Bgm.mp3";
            Media bgm = new Media(new File(audio).toURI().toString());
            MediaPlayer bgmMusicPlayer= new MediaPlayer(bgm);
            bgmMusicPlayer.setVolume(0.15);
            Timeline timelineBgmMusic=new Timeline(
                    new KeyFrame(Duration.ZERO,actionEvent -> {
                        bgmMusicPlayer.play();
                    }),
                    new KeyFrame(Duration.millis(6000),actionEvent -> {
                        bgmMusicPlayer.stop();
                    })
            );
            timelineBgmMusic.setCycleCount(Timeline.INDEFINITE);
            timelineBgmMusic.play();

    }


    //SINGELTON PATTERN IMPLEMENTED
    private static BgmMusic bgm=null;
    public static BgmMusic getInstance()
    {
        if (bgm == null) {
            bgm = new BgmMusic();
        }
        return bgm;
    }

    private BgmMusic() {
    }
}

package com.example.pachero;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class StickDropMusic extends MusicAbstractClass implements Runnable {
    @Override
    public void run() {
        String drop_audio = "src/main/resources/music/StickDrop.mp3";
        musicRun(drop_audio);
    }
}
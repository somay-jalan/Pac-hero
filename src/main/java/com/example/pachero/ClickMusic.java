package com.example.pachero;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class ClickMusic extends MusicAbstractClass implements Runnable{
    @Override
    public void run() {
        String click = "src/main/resources/music/button.mp3";
        musicRun(click);
    }
}
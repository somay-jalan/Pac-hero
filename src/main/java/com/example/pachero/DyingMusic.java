package com.example.pachero;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class DyingMusic extends MusicAbstractClass implements Runnable {
    @Override
    public void run() {
        String dying_audio = "src/main/resources/music/PacDying.mp3";
        musicRun(dying_audio);
    }
}

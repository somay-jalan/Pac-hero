package com.example.pachero;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
//TEMPLATE PATTERN IMPLEMENTED
public abstract class MusicAbstractClass {
    public final void musicRun(String audioFile){
        Media audioMedia = new Media(new File(audioFile).toURI().toString());
        MediaPlayer AudioPlayer = new MediaPlayer(audioMedia);
        AudioPlayer.setVolume(0.50);
        AudioPlayer.play();
    }
}

package com.example.pachero;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public abstract class MusicAbstractClass {
    public final void musicRun(String audioFile){
        Media click_audio = new Media(new File(audioFile).toURI().toString());
        MediaPlayer clickAudioPlayer = new MediaPlayer(click_audio);
        clickAudioPlayer.setVolume(0.50);
        clickAudioPlayer.play();
    }
}

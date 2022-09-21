package com.example.quiz;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;

public class Audio {
    static public ArrayList<Audio> sounds = new ArrayList<>();
    static public void mute() {
        muted = true;
        for(Audio sound : sounds){
            sound.stop();
        }

    }

    static public void unmute() {
        muted = false;
    }

    static public Boolean muted =false;
    private Media media;
    private MediaPlayer player;
    private Double volume = Double.valueOf(50);
    private Duration seconds = Duration.seconds(0);

    public Audio(String filename) {
        File mediaFile = new File("src/main/resources/com/example/quiz/Audio/"+ filename);
        media = new Media(mediaFile.toURI().toString());
        player = new MediaPlayer(media);
        Audio.sounds.add(this);
        player.setVolume(this.volume);
    }

    public Audio(String filename,Double volume) {
       this(filename);
       this.volume = volume;
       player.setVolume(this.volume);//Volume ranges from 0-1
    }

    public void play() {
        if (!Audio.muted) {
            this.player.play();
        }

    }

    public void pause() {
        if (!Audio.muted) {
            this.seconds = this.player.getCurrentTime();
            this.player.pause();
        }
    }

    public void stop() {
        this.player.stop();
    }

    public void loop() {
        if(!Audio.muted){
            this.stop();
            this.play();
            this.player.setOnEndOfMedia(() -> loop());
                }
        }
}

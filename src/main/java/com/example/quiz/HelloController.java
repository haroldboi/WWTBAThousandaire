package com.example.quiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class HelloController {
    private Audio sound;
    private Boolean paused = false;

    @FXML Button mute,pause;

    public HelloController(){
        sound = new Audio("MainTheme.wav",0.3d);
        sound.loop();

    }
    public void start(ActionEvent event) throws IOException {
       SceneController controller = new SceneController();
       sound.stop();
       Audio.sounds.clear();
       controller.switchToHome(event);

    }

    public void updateMute(){
        pause.setText( paused ? "play" : "pause");
        mute.setText(Audio.muted ? "unmute" : "mute");
    }

    public void MU(ActionEvent event){
        if(Audio.muted)
            Audio.unmute();
        else{
            Audio.mute();
            sound.play();
        }
        updateMute();
    }
    public void PP(ActionEvent event) {
        if(!Audio.muted){
        if(!paused)
            sound.pause();
        else
            sound.play();
        paused = !paused;
        updateMute();
    }}
}
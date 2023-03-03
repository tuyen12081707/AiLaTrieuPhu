package com.example.ailatrieuphu;

import android.media.AudioAttributes;
import android.media.MediaPlayer;

public class MediaManager {
    private static MediaManager instance;
    private MediaPlayer playBG;
    private MediaPlayer playGame;
    private boolean isPauseBG;
    private boolean isPauseGame;

    public static MediaManager getInstance() {
        if (instance == null) {
            instance = new MediaManager();
        }
        return instance;
    }

    public void playBG(int song) {
        if (playBG != null) {
            playBG.reset();
        }
        playBG = MediaPlayer.create(App.getInstance(), song);
        // cuộc gọi đến k dừng được
        playBG.setLooping(true);
        playBG.start();
    }

    public void playGame(int song, MediaPlayer.OnCompletionListener event) {
        if (playGame != null) {
            playGame.reset();
        }
        playGame = MediaPlayer.create(App.getInstance(), song);
        playGame.setOnCompletionListener(event);
        playGame.start();
    }

    public void playSong() {
        if (playBG != null & isPauseBG) {
            isPauseBG = false;
            playBG.start();
        }
        if (playGame != null & isPauseGame) {
            isPauseGame = false;
            playGame.start();
        }

    }

    public void pauseSong() {
        if (playBG != null && playBG.isPlaying()) {
            playBG.pause();
            isPauseBG = true;
        }
        if (playGame != null && playGame.isPlaying()) {
            playGame.pause();
            isPauseGame = true;
        }
    }

    public void stopBG() {
        if(playBG!=null){
            playBG.reset();
        }
    }
    public void stopGame() {
        if(playGame!=null){
            playGame.reset();
        }
    }
}

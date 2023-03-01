package com.example.ailatrieuphu.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

public class M003PlayVM extends BaseViewModel {
    private final MutableLiveData<Integer> time = new MutableLiveData<>(30);
    private boolean isPaused = true;
    private boolean isRunning = true;

    public MutableLiveData<Integer> getTime() {
        return time;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public void startCountDown() {
        Log.i("Hellow", time.getValue().toString());
        new Thread(() -> {
            while (isRunning) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (isPaused) continue;

                if (time.getValue() != null) {
                    if (time.getValue() > 0) {
                        time.postValue(time.getValue() - 1);
                    }
                }
            }
        }).start();
    }

    @Override
    protected void onCleared() {
        isRunning = false;
        super.onCleared();
    }
}

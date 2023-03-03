package com.example.ailatrieuphu.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.ailatrieuphu.db.entities.Question;

import java.util.Objects;

public class M003PlayVM extends BaseViewModel {
    private final MutableLiveData<Integer> time = new MutableLiveData<>(30);
    public MutableLiveData<Integer> index = new MutableLiveData<>(0);
    public MutableLiveData<Integer> money = new MutableLiveData<>(0);

    private int answer;
    private int trueCase;
    private boolean isPaused = true;
    private boolean isRunning = true;

    public int getTrueCase() {
        return trueCase;
    }

    public void setTrueCase(int trueCase) {
        this.trueCase = trueCase;
    }

    public MutableLiveData<Integer> getTime() {
        return time;
    }

    public MutableLiveData<Integer> getMoney() {
        return money;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public MutableLiveData<Integer> getIndex() {
        return index;
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


    public boolean checkAnswer(Question q) {
        setTrueCase(Integer.parseInt(q.trueCase));
        if (Objects.equals(Integer.parseInt(q.trueCase), answer)) {
            Log.i("truecase:", q.trueCase);
            Log.i("answer:", String.valueOf(answer));
            nextQuestion();
            return true;
        }
        return false;
    }

    public void nextQuestion() {
        if (index.getValue() != null) {
            index.postValue(index.getValue() + 1);
        }
    }

    public void updateScore() {
        money.postValue(money.getValue() + 10);
    }

    public void updateTime() {
        time.postValue(30);
    }
}

package com.example.ailatrieuphu.viewmodel;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.lifecycle.MutableLiveData;

import com.example.ailatrieuphu.db.entities.Question;

import java.util.Objects;
import java.util.Random;

public class M003PlayVM extends BaseViewModel {
    private final MutableLiveData<Integer> time = new MutableLiveData<>(30);
    public MutableLiveData<Integer> index = new MutableLiveData<>(0);
    public MutableLiveData<Integer> money = new MutableLiveData<>(0);
    private int incorrectAnswer1;
    private int incorrectAnswer2;
    private int answer;
    private int trueCase;
    private boolean isPaused = true;
    private boolean isRunning = true;

    public int getIncorrectAnswer1() {
        return incorrectAnswer1;
    }

    public int getIncorrectAnswer2() {
        return incorrectAnswer2;
    }

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
    public void firstQuestion(){
        if (index.getValue() != null) {
            index.postValue(0);
        }

    }

    public void updateScore() {
        money.postValue(money.getValue() + 10);
    }

    public void updateTime() {
        time.postValue(30);
    }

    public void delete5050(int correctAnswer, TextView tvAnsA, TextView tvAnsB, TextView tvAnsC, TextView tvAnsD) {

        Random random = new Random();
        int incorrectAnswer1 = random.nextInt(4);
        while (incorrectAnswer1 == correctAnswer || incorrectAnswer1 == 0) {
            incorrectAnswer1 = random.nextInt(4);
            this.incorrectAnswer1 = incorrectAnswer1;
        }
        this.incorrectAnswer1 = incorrectAnswer1;
        int incorrectAnswer2 = random.nextInt(4);

        while (incorrectAnswer2 == correctAnswer || incorrectAnswer2 == incorrectAnswer1 || incorrectAnswer2 == 0) {
            incorrectAnswer2 = random.nextInt(4);
        }
        this.incorrectAnswer2 = incorrectAnswer2;
        Log.i("incorrectAnswer2", String.valueOf(incorrectAnswer2));
        Log.i("incorrectAnswer1", String.valueOf(incorrectAnswer1));
        InVisibleHandle(correctAnswer, tvAnsA, tvAnsB, tvAnsC, tvAnsD, incorrectAnswer1, incorrectAnswer2);
    }

    private void InVisibleHandle(int correctAnswer, TextView tvAnsA, TextView tvAnsB, TextView tvAnsC, TextView tvAnsD, int incorrectAnswer1, int incorrectAnswer2) {
        TextView[] answerViews = {tvAnsA, tvAnsB, tvAnsC, tvAnsD};

        for (int i = 0; i < answerViews.length; i++) {
            if (i + 1 == correctAnswer) {
                continue; // skip correct answer
            }
            if (i + 1 == incorrectAnswer1 || i + 1 == incorrectAnswer2) {
                answerViews[i].setVisibility(View.INVISIBLE);
            } else {
                answerViews[i].setVisibility(View.VISIBLE);
            }
        }

    }
}

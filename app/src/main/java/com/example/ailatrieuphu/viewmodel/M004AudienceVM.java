package com.example.ailatrieuphu.viewmodel;

import android.util.Log;
import android.widget.LinearLayout;

import androidx.lifecycle.MutableLiveData;


import java.util.Random;

public class M004AudienceVM extends BaseViewModel {
    private final MutableLiveData<String> initRate = new MutableLiveData<>("");
    private final MutableLiveData<String> textViewA = new MutableLiveData<>("");
    private final MutableLiveData<String> textViewB = new MutableLiveData<>("");
    private final MutableLiveData<String> textViewC = new MutableLiveData<>("");
    private final MutableLiveData<String> textViewD = new MutableLiveData<>("");
    private final MutableLiveData<LinearLayout.LayoutParams> paramsDataA = new MutableLiveData<>();
    private final MutableLiveData<LinearLayout.LayoutParams> paramsDataB = new MutableLiveData<>();
    private final MutableLiveData<LinearLayout.LayoutParams> paramsDataC = new MutableLiveData<>();
    private final MutableLiveData<LinearLayout.LayoutParams> paramsDataD = new MutableLiveData<>();
    private int maxHeight;
    private int a, b, c, d;
    private Thread th;

    public Thread getTh() {
        return th;
    }

    public MutableLiveData<String> getTextViewB() {
        return textViewB;
    }

    public MutableLiveData<String> getTextViewC() {
        return textViewC;
    }

    public MutableLiveData<String> getTextViewD() {
        return textViewD;
    }

    public MutableLiveData<String> getInitRate() {
        return initRate;
    }

    public MutableLiveData<String> getTextViewA() {
        return textViewA;
    }

    public MutableLiveData<LinearLayout.LayoutParams> getParamsDataA() {
        return paramsDataA;
    }

    public MutableLiveData<LinearLayout.LayoutParams> getParamsDataB() {
        return paramsDataB;
    }

    public MutableLiveData<LinearLayout.LayoutParams> getParamsDataC() {
        return paramsDataC;
    }

    public MutableLiveData<LinearLayout.LayoutParams> getParamsDataD() {
        return paramsDataD;
    }

    public void initRate() {
        Random rd = new Random();
        a = rd.nextInt(101);
        b = 0;
        c = 0;
        d = 0;
        if (a < 100) {
            b = rd.nextInt(101 - a);
            if (a + b < 100) {
                c = rd.nextInt(101 - a - b);
                if (a + b + c < 100) {
                    d = 100 - a - b - c;
                }
            }
        }
    }

    public void drawColumns(LinearLayout.LayoutParams paramsA, LinearLayout.LayoutParams paramsB, LinearLayout.LayoutParams paramsC, LinearLayout.LayoutParams paramsD) {
        int hA = (maxHeight * a / 100);
        int hB = (maxHeight * b / 100);
        int hC = (maxHeight * c / 100);
        int hD = (maxHeight * d / 100);
            th = new Thread(() -> {
                for (int i = 0; i < maxHeight; i++) {
                    updateColumn(maxHeight, i, hA, paramsA, textViewA, paramsDataA);
                    updateColumn(maxHeight, i, hB, paramsB, textViewB, paramsDataB);
                    updateColumn(maxHeight, i, hC, paramsC, textViewC, paramsDataC);
                    updateColumn(maxHeight, i, hD, paramsD, textViewD, paramsDataD);
                    try {
                        Thread.sleep(10);
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                }
            });
            th.start();

    }
    private void updateColumn(int maxHeight, int i, int h, LinearLayout.LayoutParams paramsA, MutableLiveData<String> textView, MutableLiveData<LinearLayout.LayoutParams> paramsData) {
        if (i <= h) {
            paramsA.height = i;
            int percent = i * 100 / maxHeight;
            textView.postValue(String.format("%s%%", percent));
            paramsData.postValue(paramsA);
        }
    }

    public void setInfo(int maxHeight) {
        this.maxHeight = maxHeight;
    }


}

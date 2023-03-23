package com.example.ailatrieuphu.view.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.ailatrieuphu.OnActionCallBack;
import com.example.ailatrieuphu.R;

public class ConfirmDialog extends Dialog {
    public static final String KEY_BACK = "KEY_BACK";
    public static final String KEY_READY = "KEY_READY";
    private final OnActionCallBack callBack;

    public ConfirmDialog(@NonNull Context context, OnActionCallBack callBack) {
        super(context);
        setContentView(R.layout.view_ready);
        initView();
        this.callBack = callBack;
    }

    private void initView() {
        findViewById(R.id.btn_ready).setOnClickListener(v -> {
            doReady();
        });
        findViewById(R.id.btn_back).setOnClickListener(v -> {
            doBack();
        });
    }


    private void doBack() {
        callBack.callBack(null, KEY_BACK);
        dismiss();
    }

    private void doReady() {
        callBack.callBack(null, KEY_READY);
        dismiss();
    }
}

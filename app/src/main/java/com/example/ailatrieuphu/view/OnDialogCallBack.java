package com.example.ailatrieuphu.view;

public interface OnDialogCallBack {

    void callBack(Object data, String key);

    void showDialog(String tag, Object data, boolean isBacked);
}

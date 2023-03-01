package com.example.ailatrieuphu.view;

public interface OnMainCallBack {

    void backToPrevious();
    void showFragement(String tag, Object data, boolean isBacked);
    void updateUI(Runnable run);
}

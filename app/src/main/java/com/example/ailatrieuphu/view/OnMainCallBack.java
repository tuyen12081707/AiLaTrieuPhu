package com.example.ailatrieuphu.view;

public interface OnMainCallBack {
//    void callback(String key, Object data);

    void backToPrevious();
    void showFragement(String tag, Object data, boolean isBacked);
    void showVideo(String data);

}

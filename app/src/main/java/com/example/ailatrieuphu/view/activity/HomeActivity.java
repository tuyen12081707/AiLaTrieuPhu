package com.example.ailatrieuphu.view.activity;

import android.util.Log;

import com.example.ailatrieuphu.databinding.ActivityHomeBinding;
import com.example.ailatrieuphu.viewmodel.CommonVM;


public class HomeActivity extends BaseAct<ActivityHomeBinding, CommonVM> {

    private static final String TAG = ActivityHomeBinding.class.getName();
    @Override
    protected void initView() {
        Log.i(TAG, "Inits View...");
//        showFragement(M000SplashFragment.TAG, null, false);

    }

    @Override
    protected Class<CommonVM> getClassVM() {
        return CommonVM.class;
    }

    @Override
    protected ActivityHomeBinding initViewBinding() {
        return ActivityHomeBinding.inflate(getLayoutInflater());
    }

    @Override
    public void showVideo(String key) {
        Log.i(TAG, "M000SplashFragment View..." + key);

    }
}
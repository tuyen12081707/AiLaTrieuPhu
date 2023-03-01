package com.example.ailatrieuphu.view.fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.example.ailatrieuphu.databinding.M003PlayFrgBinding;
import com.example.ailatrieuphu.viewmodel.M003PlayVM;

public class M003PlayFrg extends BaseFragment<M003PlayFrgBinding, M003PlayVM> {
    public static final String TAG = M003PlayFrg.class.getName();


    @Override
    protected Class<M003PlayVM> getClassViewModel() {
        return M003PlayVM.class;
    }

    @Override
    protected void initView() {
        viewModel.getTime().observe(this, value ->{
            binding.tvCountDown.setText(value.toString());
            Log.i(TAG,value.toString());
        });
        viewModel.startCountDown();
        viewModel.setPaused(false);
        viewModel.setRunning(true);
        initQuestion();
        binding.iv5050.setOnClickListener(this);
        binding.ivAudience.setOnClickListener(this);
        binding.ivCall.setOnClickListener(this);
        binding.ivChangeQuestion.setOnClickListener(this);
        binding.ivStop.setOnClickListener(this);
    }

    private void initQuestion() {
    }
    @Override
    protected void clickView(View v) {
        super.clickView(v);

    }

    @Override
    protected M003PlayFrgBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return M003PlayFrgBinding.inflate(inflater, container, false);
    }
}

package com.example.ailatrieuphu.view.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ailatrieuphu.App;
import com.example.ailatrieuphu.MediaManager;
import com.example.ailatrieuphu.R;
import com.example.ailatrieuphu.databinding.M001MainFrgBinding;
import com.example.ailatrieuphu.viewmodel.CommonVM;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class M001MainFrg extends BaseFragment<M001MainFrgBinding, CommonVM>{
    public static final String TAG = M001MainFrg.class.getName();

    @Override
    protected Class<CommonVM> getClassViewModel() {
        return CommonVM.class;
    }

    @Override
    protected void initView() {
        MediaManager.getInstance().playBG(R.raw.song_intro);
        binding.ivPlay.setOnClickListener(this);
        binding.ivInfo.setOnClickListener(this);
        binding.ivCup.setOnClickListener(this);
        binding.ivSetting.setOnClickListener(this);
    }

    @Override
    protected void clickView(View v) {
        super.clickView(v);
        if(v.getId() == R.id.iv_play){
            MediaManager.getInstance().pauseSong();
            MediaManager.getInstance().stopBG();
            callback.showFragement(M002RuleFrg.TAG,null,true);
        }
    }

    @Override
    protected M001MainFrgBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return M001MainFrgBinding.inflate(inflater,container,false);
    }
}

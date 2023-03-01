package com.example.ailatrieuphu.view.fragment;

import android.app.Dialog;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ailatrieuphu.MediaManager;
import com.example.ailatrieuphu.R;
import com.example.ailatrieuphu.databinding.M002RuleFrgBinding;
import com.example.ailatrieuphu.view.Dialog.ConfirmDialog;
import com.example.ailatrieuphu.viewmodel.CommonVM;

public class M002RuleFrg extends BaseFragment<M002RuleFrgBinding, CommonVM> {
    public static final String TAG = M002RuleFrg.class.getName();

    @Override
    protected Class<CommonVM> getClassViewModel() {
        return CommonVM.class;
    }

    @Override
    protected void initView() {
        MediaManager.getInstance().playGame(R.raw.song_rule, mediaPlayer -> MediaManager.getInstance().playGame(R.raw.song_ready, mediaPlayer1 -> showDialog()));
        binding.lnMilestone.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_left));

    }

    private void showDialog() {
        ConfirmDialog dialog = new ConfirmDialog(context, (data, key) -> {
            if(key.equals(ConfirmDialog.KEY_READY)){
                doReady();
            }else if (key.equals(ConfirmDialog.KEY_BACK)){
                doBack();
            }
        });
        dialog.show();
    }

    private void doBack() {
    }

    private void doReady() {
    }


    @Override
    protected M002RuleFrgBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return M002RuleFrgBinding.inflate(inflater, container, false);
    }
}

package com.example.ailatrieuphu.view.Dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.ailatrieuphu.R;
import com.example.ailatrieuphu.databinding.ViewCallBinding;
import com.example.ailatrieuphu.viewmodel.CommonVM;

import java.lang.reflect.Constructor;

public class CallDialog extends BaseDialog<ViewCallBinding, CommonVM> {
    public static final String TAG = CallDialog.class.getName();
    public static final String CALL_EVENT = "CALL_EVENT";
    private final int[] listCall = new int[]{R.drawable.ic_player_help_call_01, R.drawable.ic_player_help_call_02, R.drawable.ic_player_help_call_03, R.drawable.ic_player_help_call_04};
    private final int answer;

    public CallDialog(int answer) {
        this.answer = answer;
    }


    @Override
    protected Class getClassViewModel() {
        return CommonVM.class;
    }

    @Override
    protected void initView() {
        binding.ivDoctor.setOnClickListener(this);
        binding.ivEngineer.setOnClickListener(this);
        binding.ivProfessor.setOnClickListener(this);
        binding.ivTeacher.setOnClickListener(this);
    }

    @Override
    protected void clickView(View v) {
        super.clickView(v);
        if (v.getId() == R.id.iv_doctor) {
            int[] data = new int[]{R.drawable.ic_player_help_call_01, answer};
            showDialog(CallDetailDialog.TAG, data, false);
        } else if (v.getId() == R.id.iv_engineer) {
            int[] data = new int[]{R.drawable.ic_player_help_call_02, answer};
            showDialog(CallDetailDialog.TAG, data, false);
        } else if (v.getId() == R.id.iv_professor) {
            int[] data = new int[]{R.drawable.ic_player_help_call_03, answer};
            showDialog(CallDetailDialog.TAG, data, false);
        } else if (v.getId() == R.id.iv_teacher) {
            int[] data = new int[]{R.drawable.ic_player_help_call_04, answer};
            showDialog(CallDetailDialog.TAG, data, false);
        }
    }

    public void showDialog(String tag, Object data, boolean isBacked) {
        try {
            Class<?> clazz = Class.forName(tag);
            Constructor<?> constructor = clazz.getConstructor();
            BaseDialog<?, ?> baseDialog = (BaseDialog<?, ?>) constructor.newInstance();
            baseDialog.setData(data);
            FragmentTransaction trans = getChildFragmentManager().beginTransaction();
            if (isBacked) {
                trans.addToBackStack(null);
            }
            trans.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                    .replace(R.id.fr_dialog, baseDialog, tag)
                    .commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.TransparentDialog);
    }


    @Override
    protected ViewCallBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return ViewCallBinding.inflate(inflater, container, false);
    }


}

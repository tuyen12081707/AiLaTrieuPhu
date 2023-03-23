package com.example.ailatrieuphu.view.Dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.ailatrieuphu.R;
import com.example.ailatrieuphu.databinding.ViewCallDetailBinding;
import com.example.ailatrieuphu.viewmodel.M003PlayVM;

public class CallDetailDialog extends BaseDialog<ViewCallDetailBinding, M003PlayVM> {
    public static final String TAG = CallDetailDialog.class.getName();
    public static final String[] answerList = new String[]{"A", "B", "C", "D"};

    @Override
    protected Class getClassViewModel() {
        return M003PlayVM.class;
    }

    @Override
    protected void initView() {
        if (data != null) {
            int[] dataCall = (int[]) data;
            int imageView = dataCall[0];
            int answerData = dataCall[1];
            binding.ivAnswer.setImageResource(imageView);
            String answer = null;
            for (int i = 0; i <= answerList.length; i++) {
                if (answerData == i+1) {
                    answer = answerList[i];
                    System.out.println(answerData);
                }
                System.out.println(answerData);
            }
            binding.tvAnswer.setText(String.format("Câu trả lời đúng là :" + answer));
        }
    }

    @Override
    protected void clickView(View v) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.TransparentDialog);
    }


    @Override
    protected ViewCallDetailBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return ViewCallDetailBinding.inflate(inflater, container, false);
    }


}

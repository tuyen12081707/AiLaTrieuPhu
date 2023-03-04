package com.example.ailatrieuphu.view.Dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.MutableLiveData;

import com.example.ailatrieuphu.App;
import com.example.ailatrieuphu.OnActionCallBack;
import com.example.ailatrieuphu.R;
import com.example.ailatrieuphu.databinding.ViewAudienceBinding;
import com.example.ailatrieuphu.viewmodel.M004AudienceVM;

public class AudienceDialog extends BaseDialog<ViewAudienceBinding, M004AudienceVM> {
    public static final String KEY_BACK = "KEY_BACK";
    public static String TAG = AudienceDialog.class.getName();
    private static AudienceDialog instance;
    public OnActionCallBack callBack;

    public AudienceDialog() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static AudienceDialog newInstance() {
        if (instance == null) {
            instance = new AudienceDialog();
        }
        return instance;
    }

    @Override
    protected Class<M004AudienceVM> getClassViewModel() {
        return M004AudienceVM.class;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.TransparentDialog);
    }

    public void setCallBack(OnActionCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    protected void initView() {

        int maxHeight = (int) App.getInstance().getResources().getDimension(R.dimen.d_240);
        LinearLayout.LayoutParams paramsA = (LinearLayout.LayoutParams) binding.vA.getLayoutParams();
        LinearLayout.LayoutParams paramsB = (LinearLayout.LayoutParams) binding.vB.getLayoutParams();
        LinearLayout.LayoutParams paramsC = (LinearLayout.LayoutParams) binding.vC.getLayoutParams();
        LinearLayout.LayoutParams paramsD = (LinearLayout.LayoutParams) binding.vD.getLayoutParams();
        viewModel.setInfo(maxHeight);
        viewModel.initRate();
        viewModel.drawColumns(paramsA, paramsB, paramsC, paramsD);
        viewModel.getParamsDataA().observe(this, params -> renderData(params, binding.tvA, binding.vA, viewModel.getTextViewA()));
        viewModel.getParamsDataB().observe(this, params -> renderData(params, binding.tvB, binding.vB, viewModel.getTextViewB()));
        viewModel.getParamsDataC().observe(this, params -> renderData(params, binding.tvC, binding.vC, viewModel.getTextViewC()));
        viewModel.getParamsDataD().observe(this, params -> renderData(params, binding.tvD, binding.vD, viewModel.getTextViewD()));
        binding.btClose.setVisibility(View.VISIBLE);
        binding.btClose.setOnClickListener(v -> {
            doBack();
        });
    }

    private void renderData(LinearLayout.LayoutParams params, TextView textViewColumm, View viewColumn, MutableLiveData<String> textViewData) {
        textViewColumm.setText(textViewData.getValue());
        viewColumn.setLayoutParams(params);
        viewColumn.postInvalidate();
    }

    @Override
    protected ViewAudienceBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return ViewAudienceBinding.inflate(inflater, container, false);
    }

    private void doBack() {
        callBack.callBack(null, KEY_BACK);
        dismiss();
    }


}

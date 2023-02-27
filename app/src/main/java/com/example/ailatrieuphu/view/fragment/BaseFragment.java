package com.example.ailatrieuphu.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.example.ailatrieuphu.view.OnMainCallBack;
import com.example.ailatrieuphu.viewmodel.BaseViewModel;


public abstract class BaseFragment<B extends ViewBinding, V extends BaseViewModel> extends Fragment implements View.OnClickListener{

    protected Object data;
    protected Context context;
    protected B binding;
    protected V viewModel;
    protected OnMainCallBack callback;

    public void setCallback(OnMainCallBack callback) {
        this.callback = callback;
    }

    public void setData(Object data) {
        this.data = data;
    }

    protected abstract Class<V> getClassViewModel();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = initViewBinding(inflater, container);
        viewModel = new ViewModelProvider(this).get(getClassViewModel());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    protected abstract void initView();

    protected abstract B initViewBinding(@NonNull LayoutInflater inflater,
                                         @Nullable ViewGroup container);

    @Override
    public void onClick(View v) {
        v.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
        clickView(v);
    }

    private void clickView(View v) {
    }

    protected final void notify(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    protected final void notify(int msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }



}

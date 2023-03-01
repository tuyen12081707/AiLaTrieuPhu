package com.example.ailatrieuphu.view.activity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;


import com.example.ailatrieuphu.MediaManager;
import com.example.ailatrieuphu.R;
import com.example.ailatrieuphu.view.OnMainCallBack;
import com.example.ailatrieuphu.view.fragment.BaseFragment;

import java.lang.reflect.Constructor;

public abstract class BaseAct<B extends ViewBinding, M extends ViewModel> extends AppCompatActivity implements View.OnClickListener, OnMainCallBack {
    protected B binding;
    protected M viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = initViewBinding();
        viewModel = new ViewModelProvider(this).get(getClassVM());
        setContentView(binding.getRoot());
        initView();
    }

    protected abstract void initView();

    protected abstract Class<M> getClassVM();

    protected abstract B initViewBinding();

    @Override
    public void onClick(View v) {
        v.startAnimation(AnimationUtils.loadAnimation(this, androidx.appcompat.R.anim.abc_fade_in));
        clickView(v);
    }

    private void clickView(View v) {
    }

    @Override
    public void showFragement(String tag, Object data, boolean isBacked) {
        try {
            Class<?> clazz = Class.forName(tag);
            Constructor<?> constructor = clazz.getConstructor();
            BaseFragment<?, ?> baseFragment = (BaseFragment<?, ?>) constructor.newInstance();
            baseFragment.setCallback(this);
            baseFragment.setData(data);
            FragmentTransaction trans = getSupportFragmentManager()
                    .beginTransaction();
            if (isBacked) {
                trans.addToBackStack(null);
            }
            trans.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                    .replace(R.id.ln_main, baseFragment, tag)
                    .commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        MediaManager.getInstance().playSong();
    }

    @Override
    protected void onStop() {
        super.onStop();
        MediaManager.getInstance().pauseSong();
    }


    protected final void notify(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected final void notify(int msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}

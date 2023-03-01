package com.example.ailatrieuphu.view.activity;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.ailatrieuphu.App;
import com.example.ailatrieuphu.MediaManager;
import com.example.ailatrieuphu.OnActionCallBack;
import com.example.ailatrieuphu.databinding.ActivityHomeBinding;
import com.example.ailatrieuphu.db.entities.Question;
import com.example.ailatrieuphu.view.fragment.M001MainFrg;
import com.example.ailatrieuphu.viewmodel.CommonVM;

import java.util.List;


public class HomeActivity extends BaseAct<ActivityHomeBinding, CommonVM> {

    private static final String TAG = ActivityHomeBinding.class.getName();

    @Override
    protected void initView() {
        Log.i(TAG, "Inits View...");
//        showFragement(M001MainFrg.TAG, null, false);
//    }
        initDB();

    }

    private void initDB() {
        new Thread(() -> {
            try {
                List<Question> listQuestion = App.getInstance().getDb().questionDAO().getAllQuestion();
                Log.i(TAG, "listQuestion :" + listQuestion.size());
                runOnUi((data, key) -> goToMainScreen(listQuestion));
            } catch (Exception e) {
                e.printStackTrace();
                runOnUi((data, key) -> showAlert());
            }
        }).start();
    }

    private void goToMainScreen(List<Question> listQuestion) {
        App.getInstance().getStorage().setListQuestion(listQuestion);
        new Handler().postDelayed(() -> {
            showFragement(M001MainFrg.TAG, null, false);
            binding.progressLoading.setVisibility(View.GONE);
            binding.tvLogo.setVisibility(View.GONE);
        }, 2000);
    }

    private void showAlert() {
        Toast.makeText(this, "Không nhận được db", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected Class<CommonVM> getClassVM() {
        return CommonVM.class;
    }

    @Override
    public void backToPrevious() {
        onBackPressed();
    }


    @Override
    protected ActivityHomeBinding initViewBinding() {
        return ActivityHomeBinding.inflate(getLayoutInflater());
    }


    public void runOnUi(OnActionCallBack callBack) {
        runOnUiThread(() -> callBack.callBack(null, null));
    }


}
package com.example.ailatrieuphu.view.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.example.ailatrieuphu.MediaManager;
import com.example.ailatrieuphu.R;
import com.example.ailatrieuphu.Storage;
import com.example.ailatrieuphu.databinding.M003PlayFrgBinding;
import com.example.ailatrieuphu.db.entities.Question;
import com.example.ailatrieuphu.view.Dialog.AudienceDialog;
import com.example.ailatrieuphu.viewmodel.M003PlayVM;

import java.util.List;

public class M003PlayFrg extends BaseFragment<M003PlayFrgBinding, M003PlayVM> {
    public static final String TAG = M003PlayFrg.class.getName();
    public static final int[] ID_SONG_QUESTION = {R.raw.ques1, R.raw.ques2, R.raw.ques3, R.raw.ques4, R.raw.ques5, R.raw.ques6, R.raw.ques7, R.raw.ques8, R.raw.ques9, R.raw.ques10, R.raw.ques11, R.raw.ques12, R.raw.ques13, R.raw.ques14, R.raw.ques15};
    public static final int[] ID_TRUE_ANSWER = {R.raw.song_true_a, R.raw.song_true_b, R.raw.song_true_c, R.raw.song_true_d};
    public static final int[] ID_WRONG_ANSWER = {R.raw.song_lose_a, R.raw.song_lose_b, R.raw.song_lose_c, R.raw.song_lose_d};
    private List<Question> questionList;
    private Question q;

    public Question getQ() {
        return q;
    }

    public void setQ(Question q) {
        this.q = q;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    @Override
    protected Class<M003PlayVM> getClassViewModel() {
        return M003PlayVM.class;
    }

    @Override
    protected void initView() {
        MediaManager.getInstance().playBG(R.raw.song_bg_1_5);
        viewModel.getTime().observe(this, this::checkTime);
        viewModel.startCountDown();
        viewModel.getMoney().observe(this, this::updateScore);
        viewModel.setPaused(true);
        viewModel.setRunning(true);
        initQuestion();
        binding.iv5050.setOnClickListener(this);
        binding.ivAudience.setOnClickListener(this);
        binding.ivCall.setOnClickListener(this);
        binding.ivChangeQuestion.setOnClickListener(this);
        binding.ivStop.setOnClickListener(this);
        binding.tvAnsA.setOnClickListener(this);
        binding.tvAnsB.setOnClickListener(this);
        binding.tvAnsC.setOnClickListener(this);
        binding.tvAnsD.setOnClickListener(this);
    }

    private void refreshAnswer() {
        binding.tvAnsA.setBackgroundResource(R.drawable.bg_normal);
        binding.tvAnsB.setBackgroundResource(R.drawable.bg_normal);
        binding.tvAnsC.setBackgroundResource(R.drawable.bg_normal);
        binding.tvAnsD.setBackgroundResource(R.drawable.bg_normal);
    }

    private void updateScore(Integer value) {
        binding.tvMoney.setText(value.toString());
        initQuestion();
    }

    private void checkTime(Integer value) {
        binding.tvCountDown.setText(value.toString());

        if (value == 0) {
            MediaManager.getInstance().stopBG();
            gameOver();
        }
    }

    private void gameOver() {
        Toast.makeText(context, "You lost!!!", Toast.LENGTH_SHORT).show();
        initQuestion();
        viewModel.firstQuestion();
    }

    private void initQuestion() {
        viewModel.updateTime();
        refreshAnswer();
        questionList = Storage.listQuestion;
        int index = viewModel.getIndex().getValue();
        Question q = questionList.get(index);
        setQ(q);
        recycleUIElements(binding);
        MediaManager.getInstance().playGame(ID_SONG_QUESTION[index], mediaPlayer -> {
            viewModel.setPaused(false);
        });

    }

    private void recycleUIElements(M003PlayFrgBinding binding) {
        int cau = viewModel.getIndex().getValue() + 1;
        binding.tvTitle.setText(String.format("Câu " + cau + ":"));
        binding.tvQuestion.setText(q.question);
        binding.tvAnsA.setText(String.format("A: %s", q.casea));
        binding.tvAnsB.setText(String.format("B: %s", q.caseb));
        binding.tvAnsC.setText(String.format("C: %s", q.casec));
        binding.tvAnsD.setText(String.format("D: %s", q.cased));
        // set visible
        binding.tvAnsA.setVisibility(View.VISIBLE);
        binding.tvAnsB.setVisibility(View.VISIBLE);
        binding.tvAnsC.setVisibility(View.VISIBLE);
        binding.tvAnsD.setVisibility(View.VISIBLE);

    }

    @Override
    protected void clickView(View v) {
        if (v instanceof TextView) {
            if (v.getId() == R.id.tv_ansA) {
                MediaManager.getInstance().playGame(R.raw.song_ans_a, mediaPlayer -> checkAnswer(v, R.id.tv_ansA));
            } else if (v.getId() == R.id.tv_ansB) {
                MediaManager.getInstance().playGame(R.raw.song_ans_b, mediaPlayer -> checkAnswer(v, R.id.tv_ansB));
            } else if (v.getId() == R.id.tv_ansC) {
                MediaManager.getInstance().playGame(R.raw.song_ans_c, mediaPlayer -> checkAnswer(v, R.id.tv_ansC));
            } else if (v.getId() == R.id.tv_ansD) {
                MediaManager.getInstance().playGame(R.raw.song_ans_d, mediaPlayer -> checkAnswer(v, R.id.tv_ansD));
            }
            binding.tvAnsA.setBackgroundResource(R.drawable.bg_normal);
            binding.tvAnsB.setBackgroundResource(R.drawable.bg_normal);
            binding.tvAnsC.setBackgroundResource(R.drawable.bg_normal);
            binding.tvAnsD.setBackgroundResource(R.drawable.bg_normal);
            v.setBackgroundResource(R.drawable.bg_milestone_selected);
            String answerConvert = ((TextView) v).getText().toString();
            int answer = replaceToAnswerInt(answerConvert, binding.tvAnsA.getText().toString(), binding.tvAnsB.getText().toString(), binding.tvAnsC.getText().toString(), binding.tvAnsD.getText().toString());
            viewModel.setAnswer(answer);
        } else if (v instanceof ImageView) {
            // 5050
            if (v.getId() == R.id.iv_5050) {
                binding.iv5050.setBackgroundResource(R.drawable.bg_5050_used);
                binding.iv5050.setEnabled(false);
                viewModel.setPaused(true);
                MediaManager.getInstance().playGame(R.raw.sound_5050, mediaPlayer -> {
                    delete5050();
                });
            } else if (v.getId() == R.id.iv_audience) {
                binding.ivAudience.setEnabled(false);
                viewModel.setPaused(true);
                MediaManager.getInstance().playGame(R.raw.song_audience, null);
                openAudience();
            } else if (v.getId() == R.id.iv_stop) {
                gameOver();
                callback.backToPrevious();
            }else if(v.getId() == R.id.iv_change_question){
                viewModel.nextQuestion();
            }else if(v.getId() == R.id.iv_call){
                MediaManager.getInstance().playGame(R.raw.song_help_call, null);
                showOption();
            }
        }

    }

    private void showOption() {

    }

    private void openAudience() {
        binding.ivAudience.setImageResource(R.drawable.bg_audience_used);
        callback.showFragement(AudienceDialog.TAG, null, true);
        FragmentManager fm = requireActivity().getSupportFragmentManager();
        AudienceDialog audienceDialog = AudienceDialog.newInstance();
        audienceDialog.setCallBack((data, key) -> {
            if (AudienceDialog.KEY_BACK.equals(key)) {
                viewModel.setPaused(true);
            }
        });
        audienceDialog.show(fm, AudienceDialog.TAG);


    }

    private void delete5050() {
        int correctAnswer = Integer.parseInt(q.trueCase);
        viewModel.delete5050(correctAnswer, binding.tvAnsA, binding.tvAnsB, binding.tvAnsC, binding.tvAnsD);
        viewModel.setPaused(false);
    }

    private void checkAnswer(View v, int tv_answer) {
        if (viewModel.checkAnswer(getQ())) {
            v.setBackgroundResource(R.drawable.bg_answer_correct);
            if (tv_answer == R.id.tv_ansA) {
                MediaManager.getInstance().playGame(ID_TRUE_ANSWER[0], mediaPlayer -> trueAnswer());
            } else if (tv_answer == R.id.tv_ansB) {
                MediaManager.getInstance().playGame(ID_TRUE_ANSWER[1], mediaPlayer -> trueAnswer());
            } else if (tv_answer == R.id.tv_ansC) {
                MediaManager.getInstance().playGame(ID_TRUE_ANSWER[2], mediaPlayer -> trueAnswer());
            } else if (tv_answer == R.id.tv_ansD) {
                MediaManager.getInstance().playGame(ID_TRUE_ANSWER[3], mediaPlayer -> trueAnswer());
            }
        } else {
            v.setBackgroundResource(R.drawable.bg_answer_wrong);
            if (viewModel.getTrueCase() == 1) {
                binding.tvAnsA.setBackgroundResource(R.drawable.bg_answer_correct);
                MediaManager.getInstance().playGame(ID_WRONG_ANSWER[0], mediaPlayer -> gameOver());
            } else if (viewModel.getTrueCase() == 2) {
                binding.tvAnsB.setBackgroundResource(R.drawable.bg_answer_correct);
                MediaManager.getInstance().playGame(ID_WRONG_ANSWER[1], mediaPlayer -> gameOver());
            } else if (viewModel.getTrueCase() == 3) {
                binding.tvAnsC.setBackgroundResource(R.drawable.bg_answer_correct);
                MediaManager.getInstance().playGame(ID_WRONG_ANSWER[2], mediaPlayer -> gameOver());
            } else if (viewModel.getTrueCase() == 4) {
                binding.tvAnsD.setBackgroundResource(R.drawable.bg_answer_correct);
                MediaManager.getInstance().playGame(ID_WRONG_ANSWER[3], mediaPlayer -> gameOver());
            }
        }
    }

    private void trueAnswer() {
        viewModel.updateScore();
        initQuestion();
    }

    private int replaceToAnswerInt(String answerConvert, String tvAnsA, String tvAnsB, String tvAnsC, String tvAnsD) {
        String[] answer = {tvAnsA, tvAnsB, tvAnsC, tvAnsD};
        for (int i = 0; i < answer.length; i++) {
            if (answerConvert.equals(answer[i])) {
                return i + 1;
            }
        }
        return -1;
    }

    @Override
    protected M003PlayFrgBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return M003PlayFrgBinding.inflate(inflater, container, false);
    }
}

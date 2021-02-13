package com.busazhida.quizapp.ui.result;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.busazhida.quizapp.App;
import com.busazhida.quizapp.R;
import com.busazhida.quizapp.data.models.ResultQuiz;
import com.busazhida.quizapp.databinding.ActivityResultBinding;

public class ResultActivity extends AppCompatActivity {

    public static final String RESULT_QUIZ_KEY = "RESULT_QUIZ_KEY";
    private ResultViewModel resultViewModel;
    private ActivityResultBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(App.getInstance().getPrefs().getTheme());
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result);
        init();
        setArg();
        subscribeIsFinished();
    }

    private void subscribeIsFinished() {
        resultViewModel.isFinished.observe(this, this::onFinish);
    }

    private void setArg() {
        binding.setViewModel(resultViewModel);
        if (getIntent() != null) {
            ResultQuiz resultQuiz = (ResultQuiz) getIntent().getSerializableExtra(RESULT_QUIZ_KEY);
            binding.setModel(resultQuiz);
            resultViewModel.initResult(resultQuiz);
        }
    }

    private void init() {
        resultViewModel = new ViewModelProvider(this).get(ResultViewModel.class);
    }

    private void onFinish(Boolean isFinish) {
        if (isFinish) finish();
    }

}
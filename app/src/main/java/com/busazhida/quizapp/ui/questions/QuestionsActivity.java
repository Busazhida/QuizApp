package com.busazhida.quizapp.ui.questions;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.busazhida.quizapp.data.call_back.OnResultAnswerClickListener;
import com.busazhida.quizapp.databinding.ActivityQuestionBinding;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.busazhida.quizapp.R;
import com.busazhida.quizapp.ui.adapter.QuestionsAdapter;
import com.busazhida.quizapp.ui.customs.CustomLinearLayoutManager;
import com.busazhida.quizapp.ui.result.ResultActivity;

public class QuestionsActivity extends AppCompatActivity implements OnResultAnswerClickListener {

    private QuestionsAdapter adapter;
    private ActivityQuestionBinding binding;
    private QuestionsViewModel mViewModel;
    private String count, category, difficulty;
    private int position;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_question);
        init();
        getArgs();
        subscribeQuestions();
        subscribePosition();
        subscribeResultForResultActivity();
        mViewModel.setAmountQuestions(String.valueOf(count), String.valueOf(category), difficulty);
    }

    private void subscribeResultForResultActivity() {
        mViewModel.result.observeForever(resultQuiz -> {
            Intent intent = new Intent(QuestionsActivity.this, ResultActivity.class);
            intent.putExtra(ResultActivity.RESULT_QUIZ_KEY, resultQuiz);
            startActivity(intent);
            finish();
        });
    }

    private void getArgs() {
        if (getIntent() != null){
            count = getIntent().getStringExtra("count");
            category = getIntent().getStringExtra("category");
            difficulty = getIntent().getStringExtra("difficulty");
            Log.e("gggggjjgjgjgj", "getArgs: " + count + " " + category + " " + difficulty);
        }
    }

    private void subscribePosition() {
        mViewModel.position.observe(this, pos -> {
            binding.progressBar.setProgress(pos);
            binding.questionRecyclerview.scrollToPosition(pos);
            if (!mViewModel.questionsLD.getValue().isEmpty())
                binding.categoryTitle.setText(mViewModel.questionsLD.getValue().get(pos).getCategory());
        });
    }

    private void subscribeQuestions() {
        mViewModel.questionsLD.observe(this, questionsModels -> adapter.setQuestions(questionsModels));
    }

    private void init() {
        adapter = new QuestionsAdapter();
        mViewModel = new ViewModelProvider(this).get(QuestionsViewModel.class);
        adapter.setAnswerClick(this);
        binding.questionRecyclerview.setAdapter(adapter);
        binding.setViewModel(mViewModel);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        };
        binding.questionRecyclerview.setLayoutManager(linearLayoutManager);
        binding.questionRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                CustomLinearLayoutManager linearLayoutManager = (CustomLinearLayoutManager) recyclerView.getLayoutManager();
                if (linearLayoutManager != null)
                    position = linearLayoutManager.findFirstVisibleItemPosition();
                binding.setPosition(position + 1);
                binding.progressTv.setText((position + 1) + "/" + mViewModel.questionsLD.getValue().size());
                binding.categoryTitle.setText((CharSequence) mViewModel.questionsLD.getValue().get(position));
            }
        });

    }

    @Override
    public void onClick(int result, String answer) {
        mViewModel.onAnswerClick(result, category, difficulty, answer);
    }
}

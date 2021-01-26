package com.busazhida.quizapp.ui.questions;

import android.os.CountDownTimer;
import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.busazhida.quizapp.App;
import com.busazhida.quizapp.data.call_back.IQuizApiCallBack;
import com.busazhida.quizapp.data.models.QuestionsModel;
import com.busazhida.quizapp.data.models.QuizResponse;
import com.busazhida.quizapp.data.models.ResultQuiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.busazhida.quizapp.ui.adapter.QuestionsAdapter.ViewHolder.CORRECT_ANSWER;
import static com.busazhida.quizapp.ui.adapter.QuestionsAdapter.ViewHolder.CORRECT_ANSWER_AND_FINAL_ANSWER;
import static com.busazhida.quizapp.ui.adapter.QuestionsAdapter.ViewHolder.WRONG_ANSWER_AND_FINAL_ANSWER;

public class QuestionsViewModel extends ViewModel implements IQuizApiCallBack.Questions {
    public ObservableField<Boolean> isLoading = new ObservableField<>();
    public MutableLiveData<Integer> position = new MutableLiveData<>(0);
    public MutableLiveData<List<QuestionsModel>> questionsLD = new MutableLiveData<>(new ArrayList<>());
    MutableLiveData<ResultQuiz> result = new MutableLiveData<>();
    private int correctAnswerAmount = 0;

    public void setAmountQuestions(String questionsAmount, String category, String difficulty) {
        App.getInstance().getQuizRepository().getQuestions(questionsAmount, category, difficulty, this);
    }

    public void btnBackClicked(){
        position.setValue(position.getValue() - 1);
    }

    public void btnSlipClicked() {
        position.setValue(position.getValue() + 1);
    }

    @Override
    public void onSuccess(QuizResponse quizResponse) {
        questionsLD.setValue(quizResponse.getQuestions());
    }

    @Override
    public void onFailure(Throwable throwable) {
       throwable.printStackTrace();
    }

    public void onAnswerClick(int result, String category, String difficulty, String answer) {
        Log.e("ololo", "onAnswerClick: " + position.getValue());
        if (result == CORRECT_ANSWER_AND_FINAL_ANSWER) {
            correctAnswerAmount++;
            onLastAnswerClick(category, difficulty);
        } else if (result == CORRECT_ANSWER)
            correctAnswerAmount++;
        else if (result == WRONG_ANSWER_AND_FINAL_ANSWER)
            onLastAnswerClick(category, difficulty);


        new CountDownTimer(500, 500) {
            @Override
            public void onTick(long l) { }

            @Override
            public void onFinish() {
                if (position.getValue() != null)
                    position.setValue(position.getValue() + 1);
            }
        }.start();
    }

    public void onLastAnswerClick(String category, String difficulty) {
        Log.e("ololo", "onLastAnswerClick: ");
        int questionAmount = Objects.requireNonNull(questionsLD.getValue()).size();
        this.result.setValue(new ResultQuiz(
                category,
                difficulty,
                correctAnswerAmount + "/" + questionAmount,
                (((double)correctAnswerAmount) / ((double)questionAmount)) * 100,
                correctAnswerAmount
        ));
    }

}

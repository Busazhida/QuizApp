package com.busazhida.quizapp.ui.questions;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.busazhida.quizapp.App;
import com.busazhida.quizapp.data.call_back.IQuizApiCallBack;
import com.busazhida.quizapp.data.models.QuestionsModel;
import com.busazhida.quizapp.data.models.QuizResponse;

import java.util.ArrayList;
import java.util.List;

public class QuestionsViewModel extends ViewModel implements IQuizApiCallBack.Questions {
    public ObservableField<Boolean> isLoading = new ObservableField<>();
    public MutableLiveData<Integer> position = new MutableLiveData<>(0);
    public MutableLiveData<List<QuestionsModel>> questionsLD = new MutableLiveData<>(new ArrayList<>());

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
}

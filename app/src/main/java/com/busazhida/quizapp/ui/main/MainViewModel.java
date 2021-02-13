package com.busazhida.quizapp.ui.main;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.busazhida.quizapp.App;
import com.busazhida.quizapp.data.call_back.IQuizApiCallBack;
import com.busazhida.quizapp.data.models.TriviaCategories;

public class MainViewModel extends ViewModel implements IQuizApiCallBack.Categories {

    public ObservableField<String> result = new ObservableField<>("10");
    MutableLiveData<TriviaCategories> triviaCategories = new MutableLiveData<>();
    MutableLiveData<Integer> progressBarSuccess = new MutableLiveData<>();

    public MainViewModel() {
        updateCategory();
    }

    private void updateCategory() {
        App.getInstance().getQuizRepository().getCategories(this);
    }

    int count = 0;

    public void plusClick(){
        count++;
        result.set(String.valueOf(count));
    }

    public void minusClick() {
        count--;
        result.set(String.valueOf(count));
    }

    @Override
    public void onSuccess(TriviaCategories categories) {
        triviaCategories.setValue(categories);
    }

    @Override
    public void onFailure(Throwable throwable) {
        throwable.printStackTrace();
    }
}

package com.busazhida.quizapp.ui.questions;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.busazhida.quizapp.data.models.QuestionsModel;

import java.util.ArrayList;
import java.util.List;

public class QuestionsViewModel extends ViewModel {
    public ObservableField<Boolean> isLoading = new ObservableField<>();
    public ObservableField<Integer> position = new ObservableField<>();
    public MutableLiveData<List<QuestionsModel>> questionsLD = new MutableLiveData<>(new ArrayList<>());

    @RequiresApi(api = Build.VERSION_CODES.R)
    public void setCategory(){
        for (int i = 0; i <= 10; i++) questionsLD.getValue().add(QuestionsModel.newInstance());
    }

    public void btnSlipClicked(){
        position.set(position.get()+1);
    }

}

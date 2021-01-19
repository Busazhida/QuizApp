package com.busazhida.quizapp.ui.main;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    public ObservableField<String> result = new ObservableField<>("0");

    int count = 0;

    public void plusClick(){
        count++;
        result.set(String.valueOf(count));
    }

    public void minusClick(){
        count--;
        result.set(String.valueOf(count));
    }

}

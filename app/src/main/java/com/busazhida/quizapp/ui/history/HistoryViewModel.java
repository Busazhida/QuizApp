package com.busazhida.quizapp.ui.history;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.busazhida.quizapp.App;
import com.busazhida.quizapp.data.models.ResultQuiz;

import java.util.List;

public class HistoryViewModel extends ViewModel {
    public LiveData<List<ResultQuiz>> listHistoryMutableLiveData = new MutableLiveData<>();

    public HistoryViewModel() {
        main();
    }

    private void main() {
        listHistoryMutableLiveData = App.getInstance().getQuizRepository().getAllHistoryResult();
    }

    public void popupMenuDelete(int position) {
        App.getInstance().getQuizRepository().deleteToId(listHistoryMutableLiveData.getValue().get(position).getId());
    }
}
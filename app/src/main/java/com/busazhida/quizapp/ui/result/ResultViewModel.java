package com.busazhida.quizapp.ui.result;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.busazhida.quizapp.App;
import com.busazhida.quizapp.data.models.ResultQuiz;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ResultViewModel extends ViewModel {
    MutableLiveData<Boolean> isFinished = new MutableLiveData<>(false);

    public void onFinishClicked(){
        isFinished.setValue(true);
    }

    public void initResult(ResultQuiz resultQuiz) {
        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

        resultQuiz.setStringDate(formattedDate);

        App.getInstance().getQuizRepository().insertHistoryResult(resultQuiz);
    }
}

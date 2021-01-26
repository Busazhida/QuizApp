package com.busazhida.quizapp.data.localy;

import androidx.lifecycle.LiveData;

import com.busazhida.quizapp.App;
import com.busazhida.quizapp.data.models.ResultQuiz;

import java.util.List;

public class HistoryStorage implements IHistoryClient{

    @Override
    public void insertHistoryResult(ResultQuiz resultModel) {
        App.getInstance().getAppDatabase().historyResultDao().insert(resultModel);
    }

    @Override
    public LiveData<List<ResultQuiz>> getAllHistoryResult() {
        return App.getInstance().getAppDatabase().historyResultDao().getAll();
    }

    @Override
    public void clearAll() {
        App.getInstance().getAppDatabase().historyResultDao().deleteAll();
    }

    @Override
    public void deleteToId(Long id) {
        App.getInstance().getAppDatabase().historyResultDao().deleteToId(id);
    }


}
package com.busazhida.quizapp.data.localy;

import androidx.lifecycle.LiveData;

import com.busazhida.quizapp.data.models.ResultQuiz;

import java.util.List;

public interface IHistoryClient {
    void insertHistoryResult(ResultQuiz resultModel);
    LiveData<List<ResultQuiz>> getAllHistoryResult();
    void clearAll();
    void deleteToId(Long id);
}
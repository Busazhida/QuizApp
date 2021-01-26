package com.busazhida.quizapp.data.localy.db;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;

import com.busazhida.quizapp.data.models.ResultQuiz;

import java.util.List;

@androidx.room.Dao
public interface HistoryDao {

    @Query("SELECT * FROM RESULTQUIZ")
    LiveData<List<ResultQuiz>> getAll();

    @Query("SELECT * FROM RESULTQUIZ WHERE id = :id")
    ResultQuiz getById(long id);

    @Insert
    void insert(ResultQuiz historyResultModel);

    @Query("DELETE FROM RESULTQUIZ")
    void deleteAll();

    @Query("DELETE FROM resultquiz WHERE id = :id")
    void deleteToId(long id);
}
package com.busazhida.quizapp.data.localy.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.busazhida.quizapp.data.models.ResultQuiz;

@Database(entities = {ResultQuiz.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract HistoryDao historyResultDao();
}
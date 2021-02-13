package com.busazhida.quizapp;

import android.app.Application;

import androidx.room.Room;

import com.busazhida.quizapp.data.QuizRepository;
import com.busazhida.quizapp.data.localy.HistoryStorage;
import com.busazhida.quizapp.data.localy.IHistoryClient;
import com.busazhida.quizapp.data.localy.db.AppDatabase;
import com.busazhida.quizapp.data.network.IQuizApiClient;
import com.busazhida.quizapp.data.network.QuizApiService;

public class App extends Application {
    private static App instance;
    private QuizRepository quizRepository;
    private AppDatabase appDatabase;
    private Prefs prefs;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        IQuizApiClient quizApiClient = new QuizApiService();
        IHistoryClient historyClient = new HistoryStorage();
        quizRepository = new QuizRepository(historyClient, quizApiClient);
        appDatabase = Room.databaseBuilder(this, AppDatabase.class, "app_database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        prefs = new Prefs(this);
    }

    public static App getInstance() {
        return instance;
    }

    public QuizRepository getQuizRepository() {
        return quizRepository;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    public Prefs getPrefs() {
        return prefs;
    }
}

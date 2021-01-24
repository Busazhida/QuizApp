package com.busazhida.quizapp;

import android.app.Application;

import com.busazhida.quizapp.data.QuizRepository;
import com.busazhida.quizapp.data.network.IQuizApiClient;
import com.busazhida.quizapp.data.network.QuizApiService;

public class App extends Application {
    private static App instance;
    private QuizRepository quizRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        IQuizApiClient quizApiClient = new QuizApiService();
        quizRepository = new QuizRepository(quizApiClient);
    }

    public static App getInstance() {
        return instance;
    }

    public QuizRepository getQuizRepository() {
        return quizRepository;
    }
}

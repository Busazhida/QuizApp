package com.busazhida.quizapp.data.network;

import com.busazhida.quizapp.data.call_back.IQuizApiCallBack;

public interface IQuizApiClient {
    void getQuestions(String amount, String category, String difficulty, IQuizApiCallBack.Questions callBack);

    void getCategories(IQuizApiCallBack.Categories callBack);
}
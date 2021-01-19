package com.busazhida.quizapp.data.call_back;

import com.busazhida.quizapp.core.IBaseCallBack;
import com.busazhida.quizapp.data.models.QuizResponse;
import com.busazhida.quizapp.data.models.TriviaCategories;

public interface IQuizApiCallBack {
    interface Questions extends IBaseCallBack<QuizResponse> {
        void onSuccess(QuizResponse quizResponse);

        void onFailure(Throwable throwable);
    }

    interface Categories extends IBaseCallBack<TriviaCategories> {
        void onSuccess(TriviaCategories categories);

        void onFailure(Throwable throwable);
    }
}
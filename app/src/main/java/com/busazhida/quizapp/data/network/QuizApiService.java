package com.busazhida.quizapp.data.network;

import androidx.annotation.NonNull;

import com.busazhida.quizapp.data.call_back.IQuizApiCallBack;
import com.busazhida.quizapp.data.models.QuizResponse;
import com.busazhida.quizapp.data.models.TriviaCategories;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuizApiService implements IQuizApiClient {
    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private final QuizApi service = retrofit.create(QuizApi.class);

    @Override
    public void getQuestions(String amount, String category, String difficulty, IQuizApiCallBack.Questions callBack) {
        service.getQuestion(amount, category, difficulty)
                .enqueue(new Callback<QuizResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<QuizResponse> call, @NonNull Response<QuizResponse> response) {
                        callBack.onSuccess(response.body());
                    }

                    @Override
                    public void onFailure(@NonNull Call<QuizResponse> call, @NonNull Throwable t) {
                        callBack.onFailure(t);
                    }
                });
    }

    @Override
    public void getCategories(IQuizApiCallBack.Categories callBack) {
        service.getCategories()
                .enqueue(new Callback<TriviaCategories>() {
                    @Override
                    public void onResponse(@NonNull Call<TriviaCategories> call, @NonNull Response<TriviaCategories> response) {
                        callBack.onSuccess(response.body());
                    }

                    @Override
                    public void onFailure(@NonNull Call<TriviaCategories> call, @NonNull Throwable t) {
                        callBack.onFailure(t);
                    }
                });
    }

}
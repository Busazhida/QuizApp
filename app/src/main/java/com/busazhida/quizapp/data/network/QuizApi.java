package com.busazhida.quizapp.data.network;

import com.busazhida.quizapp.data.models.QuizResponse;
import com.busazhida.quizapp.data.models.TriviaCategories;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuizApi {
    @GET("api.php")
    Call<QuizResponse> getQuestion(
            @Query("amount") String amount,
            @Query("category") String category,
            @Query("difficulty") String difficulty);

    @GET("api_category.php")
    Call<TriviaCategories> getCategories();

}
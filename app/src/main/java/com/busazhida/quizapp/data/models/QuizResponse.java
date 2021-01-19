package com.busazhida.quizapp.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuizResponse {

    @SerializedName("response_code")
    private Integer responseCode;
    @SerializedName("results")
    private List<QuestionsModel> questions = null;

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public List<QuestionsModel> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionsModel> questions) {
        this.questions = questions;
    }
}
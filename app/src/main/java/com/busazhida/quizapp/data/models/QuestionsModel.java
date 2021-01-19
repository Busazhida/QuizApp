package com.busazhida.quizapp.data.models;

import android.os.Build;
import android.text.Html;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuestionsModel {

    @SerializedName("category")
    private String category;
    @SerializedName("type")
    private String type;
    @SerializedName("difficulty")
    private String difficulty;
    @SerializedName("question")
    private String question;
    @SerializedName("correct_answer")
    private String correctAnswer;
    @SerializedName("incorrect_answers")
    private List<String> incorrectAnswers = null;
    private int userChoice;
    private boolean isChoice;
    MutableLiveData<Boolean> isSkipClicked = new MutableLiveData<>(false);

    public MutableLiveData<Boolean> getIsSkipClicked() {
        return isSkipClicked;
    }

    public void setSkipClicked(boolean skipClicked) {
        isSkipClicked.setValue(skipClicked);
    }

    public boolean isChoice() {
        return isChoice;
    }

    public void setChoice(boolean choice) {
        isChoice = choice;
    }

    public int getUserChoice() {
        return userChoice;
    }

    public void setUserChoice(int userChoice) {
        this.userChoice = userChoice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public List<String> getIncorrectAnswers() {
        for (int i = 0; i < incorrectAnswers.size()-1; i++) {
            incorrectAnswers.set(i, Html.fromHtml(Html.fromHtml(incorrectAnswers.get(i)).toString()).toString());
        }
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(List<String> incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    public static QuestionsModel newInstance(){
        QuestionsModel question1 = new QuestionsModel();
        question1.question = "Cashmere is the wool from which kind of animal?";
        question1.category = "Animals";
        question1.type = "multiple";
        question1.difficulty = "medium";
        question1.correctAnswer = "Goat";
        question1.incorrectAnswers = List.of("Sheep","Camel","Llama");
        return question1;
    }

}

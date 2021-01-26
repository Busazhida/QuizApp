
package com.busazhida.quizapp.data.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class ResultQuiz implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String category;
    private String difficulty;
    private String correctAns;
    private String resultPercentage;
    private String stringDate;
    private int AmountCorrectAns;

    public ResultQuiz(){}

    public ResultQuiz(String category, String difficulty, String correctAns, double resultPercentage, int AmountCorrectAns) {
        this.category = category;
        this.difficulty = difficulty;
        this.correctAns = correctAns;
        this.resultPercentage = (int) resultPercentage + "%";
        this.AmountCorrectAns = AmountCorrectAns;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setStringDate(String stringDate) {
        this.stringDate = stringDate;
    }

    public String getStringDate() {
        return stringDate;
    }

    public String getCategory() {
        return category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getCorrectAns() {
        return correctAns;
    }

    public String getResultPercentage() {
        return resultPercentage;
    }

    public int getAmountCorrectAns() {
        return AmountCorrectAns;
    }

    public void setAmountCorrectAns(int amountCorrectAns) {
        AmountCorrectAns = amountCorrectAns;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCorrectAns(String correctAns) {
        this.correctAns = correctAns;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setResultPercentage(String resultPercentage) {
        this.resultPercentage = resultPercentage;
    }
}
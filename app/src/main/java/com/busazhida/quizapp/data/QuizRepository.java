package com.busazhida.quizapp.data;

import androidx.lifecycle.LiveData;

import com.busazhida.quizapp.data.call_back.IQuizApiCallBack;
import com.busazhida.quizapp.data.call_back.QuestionsCallBack;
import com.busazhida.quizapp.data.localy.IHistoryClient;
import com.busazhida.quizapp.data.models.QuestionsModel;
import com.busazhida.quizapp.data.models.QuizResponse;
import com.busazhida.quizapp.data.models.ResultQuiz;
import com.busazhida.quizapp.data.network.IQuizApiClient;

import java.util.Collections;
import java.util.List;

public class QuizRepository implements IQuizApiClient, IHistoryClient {
    private final IHistoryClient historyStorage;
    private final IQuizApiClient quizApiClient;

    public QuizRepository(IHistoryClient historyStorage, IQuizApiClient quizApiClient) {
        this.historyStorage = historyStorage;
        this.quizApiClient = quizApiClient;
    }

    @Override
    public void getQuestions(String amount, String category, String difficulty, IQuizApiCallBack.Questions callBack) {
        quizApiClient.getQuestions(amount,
                category,
                difficulty,
                new QuestionsCallBack(){
                    @Override
                    public void onSuccess(QuizResponse result) {
                        addCorrectAnsInIncorrect(result.getQuestions());
                        callBack.onSuccess(result);
                    }
                });
    }

    @Override
    public void getCategories(IQuizApiCallBack.Categories callBack) {
        quizApiClient.getCategories(callBack);
    }

    private void addCorrectAnsInIncorrect(List<QuestionsModel> questions) {
        for (QuestionsModel question : questions) {
            question.getIncorrectAnswers().add(question.getCorrectAnswer());
            Collections.shuffle(question.getIncorrectAnswers());
        }
    }

    @Override
    public void insertHistoryResult(ResultQuiz resultModel) {
        historyStorage.insertHistoryResult(resultModel);
    }

    @Override
    public LiveData<List<ResultQuiz>> getAllHistoryResult() {
        return historyStorage.getAllHistoryResult();
    }

    @Override
    public void clearAll() {
        historyStorage.clearAll();
    }

    @Override
    public void deleteToId(Long id) {
        historyStorage.deleteToId(id);
    }

}
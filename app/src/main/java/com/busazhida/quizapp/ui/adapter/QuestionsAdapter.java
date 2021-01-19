package com.busazhida.quizapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.busazhida.quizapp.data.models.QuestionsModel;
import com.busazhida.quizapp.databinding.QuestionsItemBinding;

import java.util.ArrayList;
import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionsViewHolder> {

    private List<QuestionsModel> questionsList = new ArrayList<>();

    public void setQuestionsList(List<QuestionsModel> questionsList) {
        this.questionsList = questionsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuestionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuestionsViewHolder(QuestionsItemBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionsViewHolder holder, int position) {
        holder.onBind(questionsList.get(position));
    }

    @Override
    public int getItemCount() {
        return questionsList.size();
    }

    public static class QuestionsViewHolder extends RecyclerView.ViewHolder {

        private QuestionsItemBinding binding;

        public QuestionsViewHolder(@NonNull QuestionsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(QuestionsModel questionsModel) {
           binding.setQuestion(questionsModel);
        }
    }
}

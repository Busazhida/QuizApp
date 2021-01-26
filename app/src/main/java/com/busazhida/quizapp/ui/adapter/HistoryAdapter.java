package com.busazhida.quizapp.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.busazhida.quizapp.R;
import com.busazhida.quizapp.data.call_back.OnPopupMenuClickListener;
import com.busazhida.quizapp.data.models.ResultQuiz;
import com.busazhida.quizapp.databinding.ItemHistoryBinding;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private List<ResultQuiz> data = new ArrayList<>();
    private OnPopupMenuClickListener onPopupMenuClick;

    public void setOnPopupMenuClick(OnPopupMenuClickListener onPopupMenuClick) {
        this.onPopupMenuClick = onPopupMenuClick;
    }

    public void addData(List<ResultQuiz> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryViewHolder(ItemHistoryBinding.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.onBind(data.get(position));
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        private final ItemHistoryBinding itemHistory;

        public HistoryViewHolder(@NonNull ItemHistoryBinding binding) {
            super(binding.getRoot());
            itemHistory = binding;
            binding.popUpMenu.setOnClickListener(v -> onPopupMenuClick.onPopupMenuClick(v, getAdapterPosition()));
        }

        @SuppressLint("SetTextI18n")
        void onBind(ResultQuiz model){
            itemHistory.textViewCategory.setText(model.getCategory());
            itemHistory.textViewCorrectAns.setText(model.getCorrectAns() + "");
            itemHistory.textViewDifficulty.setText(model.getDifficulty());
            itemHistory.textViewData.setText(model.getStringDate());
        }
    }
}
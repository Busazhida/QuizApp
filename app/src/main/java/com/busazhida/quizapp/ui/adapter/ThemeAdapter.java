package com.busazhida.quizapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.busazhida.quizapp.R;
import com.busazhida.quizapp.data.call_back.OnThemeItemClickListener;
import com.busazhida.quizapp.data.models.ThemeModel;
import com.busazhida.quizapp.databinding.ItemThemeBinding;

import java.util.ArrayList;
import java.util.List;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ViewHolder> {
    private List<ThemeModel> data = new ArrayList<>();
    private OnThemeItemClickListener listener;

    public void onCLick(OnThemeItemClickListener listener){
        this.listener = listener;
    }

    public void setData(List<ThemeModel> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemThemeBinding.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theme, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final ItemThemeBinding binding;

        public ViewHolder(@NonNull ItemThemeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(view -> {
                binding.radioButton.setChecked(!binding.radioButton.isChecked());
                if (binding.radioButton.isChecked())
                    listener.onThemeClicked(getAdapterPosition());
            });
            binding.radioButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) listener.onThemeClicked(getAdapterPosition());
            });

        }

        public void onBind(ThemeModel themeModel){
            binding.imageViewTheme.setImageDrawable(ContextCompat.getDrawable(binding.getRoot().getContext(), themeModel.getIconDrawableId()));
            binding.radioButton.setChecked(themeModel.isChange());
        }
    }
}

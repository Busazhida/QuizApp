package com.busazhida.quizapp.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;

import com.busazhida.quizapp.R;
import com.busazhida.quizapp.data.call_back.ItemSelectedListener;
import com.busazhida.quizapp.data.call_back.SeekBarChangeListener;
import com.busazhida.quizapp.data.models.TriviaCategory;
import com.busazhida.quizapp.databinding.MainFragmentBinding;
import com.busazhida.quizapp.ui.questions.QuestionsActivity;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private MainFragmentBinding binding;
    private String difficulty;
    private Integer categoryId;
    private String categoryTitle;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = MainFragmentBinding.bind(inflater.inflate(R.layout.main_fragment, container, false));
        setListener();
        return binding.getRoot();
    }

    private void setListener() {
        binding.seekBar.setOnSeekBarChangeListener(new SeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mViewModel.progressBarSuccess.setValue(i);
            }
        });

        binding.spinnerSecond.setOnItemSelectedListener(new ItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                difficulty = getResources().getStringArray(R.array.spinner_category_example)[position];
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.setViewModel(mViewModel);
        binding.btnStart.setOnClickListener(v -> {
            Intent intent = new Intent(requireActivity(), QuestionsActivity.class);
            intent.putExtra("count", String.valueOf(mViewModel.progressBarSuccess.getValue()));
            intent.putExtra("difficulty", difficulty);
            intent.putExtra("category", String.valueOf(categoryId));
            startActivity(intent);
        });
        subscribeCategory();
        subscribeQuestionsAmount();
    }

    private void subscribeQuestionsAmount() {
        mViewModel.progressBarSuccess.observe(requireActivity(), amount -> binding.tvTen.setText(String.valueOf(amount)));
    }

    private void subscribeCategory() {
        mViewModel.triviaCategories.observeForever(triviaCategories -> {
            List<String> name_category = new ArrayList<>();
            for (TriviaCategory triviaCategory : triviaCategories.getTriviaCategories())
                name_category.add(triviaCategory.getName());

            ArrayAdapter<String> adapter = new ArrayAdapter<>(binding.getRoot().getContext(), R.layout.support_simple_spinner_dropdown_item, name_category);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.spinnerFirst.setAdapter(adapter);
            binding.spinnerFirst.setOnItemSelectedListener(new ItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    categoryId = triviaCategories.getTriviaCategories().get(position).getId();
                    categoryTitle = triviaCategories.getTriviaCategories().get(position).getName();
                }
            });
        });
    }

}
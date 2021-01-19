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

import com.busazhida.quizapp.R;
import com.busazhida.quizapp.databinding.MainFragmentBinding;
import com.busazhida.quizapp.ui.questions.QuestionsActivity;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private MainFragmentBinding binding;


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = MainFragmentBinding.bind(inflater.inflate(R.layout.main_fragment, container, false));
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.setViewModel(mViewModel);
        binding.btnStart.setOnClickListener(v -> startActivity(new Intent(requireActivity(), QuestionsActivity.class)));
    }

}
package com.busazhida.quizapp.ui.settings;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.busazhida.quizapp.App;
import com.busazhida.quizapp.data.call_back.OnThemeItemClickListener;
import com.busazhida.quizapp.databinding.SettingsFragmentBinding;
import com.busazhida.quizapp.ui.adapter.ThemeAdapter;

public class SettingsFragment extends Fragment implements OnThemeItemClickListener {

    private SettingsViewModel mViewModel;
    private SettingsFragmentBinding binding;
    private ThemeAdapter themeAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = SettingsFragmentBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        mViewModel.showTheme.observe(requireActivity(), integer -> {
            Intent intent = requireActivity().getIntent();
            requireActivity().finish();
            startActivity(intent);
        });
        mViewModel.themes.observe(requireActivity(), data -> themeAdapter.setData(data));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        binding.layout4.setOnClickListener( v-> {
            App.getInstance().getQuizRepository().clearAll();
            Toast.makeText(requireContext(), "history is deleted", Toast.LENGTH_SHORT).show();
        });
    }

    private void init() {
        themeAdapter = new ThemeAdapter();
        themeAdapter.onCLick(this);
        binding.recyclerview.setAdapter(themeAdapter);
    }


    @Override
    public void onThemeClicked(int position) {
        mViewModel.onThemeChanged(position);
    }
}
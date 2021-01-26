package com.busazhida.quizapp.ui.history;

import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import com.busazhida.quizapp.R;
import com.busazhida.quizapp.databinding.HistoryFragmentBinding;
import com.busazhida.quizapp.ui.adapter.HistoryAdapter;

public class HistoryFragment extends Fragment {

    private HistoryViewModel mViewModel;
    private HistoryFragmentBinding binding;
    private HistoryAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = HistoryFragmentBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        subscribeHistory();
    }

    private void subscribeHistory() {
        mViewModel.listHistoryMutableLiveData.observe(requireActivity(), historyModels -> {
            binding.message.setText("you have no history yet");
            if (historyModels.isEmpty()) binding.message.setVisibility(View.VISIBLE);
            else binding.message.setVisibility(View.GONE);
            adapter.addData(historyModels);
        });
    }

    private void init() {
        mViewModel = new ViewModelProvider(requireActivity()).get(HistoryViewModel.class);
        adapter = new HistoryAdapter();
        binding.recyclerview.setAdapter(adapter);
        adapter.setOnPopupMenuClick(this::showPopupMenu);
    }

    @SuppressLint("NonConstantResourceId")
    private void showPopupMenu(View v, int position) {
        PopupMenu popupMenu = new PopupMenu(requireContext(), v);
        popupMenu.inflate(R.menu.popup_menu);

        popupMenu
                .setOnMenuItemClickListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.delete:
                            mViewModel.popupMenuDelete(position);
                            return true;
                        case R.id.no:

                            return true;
                        default:
                            return false;
                    }
                });

        popupMenu.show();
    }

}
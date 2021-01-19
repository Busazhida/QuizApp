package com.busazhida.quizapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.busazhida.quizapp.databinding.UserItemBinding;

import java.util.ArrayList;

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.UserVH> {

    private ArrayList<Users> items;

    public UserRecyclerAdapter(ArrayList<Users> items) {
        this.items = items;
    }


    @NonNull
    @Override
    public UserVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        UserItemBinding binding = DataBindingUtil.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false));
        return new UserVH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserVH holder, int position) {
        holder.mBinding.text.setText(items.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class UserVH extends RecyclerView.ViewHolder {
        UserItemBinding mBinding;

        public UserVH(UserItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }
}

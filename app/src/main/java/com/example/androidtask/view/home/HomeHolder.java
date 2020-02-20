package com.example.androidtask.view.home;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidtask.data.model.ItemHome;
import com.example.androidtask.databinding.ItemHomeBinding;

public class HomeHolder extends RecyclerView.ViewHolder {

    private ItemHomeBinding binding;

    public HomeHolder(@NonNull ItemHomeBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(ItemHome item) {
        binding.setItem(item);
    }
}

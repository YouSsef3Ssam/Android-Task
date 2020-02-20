package com.example.androidtask.view.main;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.androidtask.R;
import com.example.androidtask.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;
    private boolean isOpened = false;

    private Animation show;
    private Animation hide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.bellManFab.setOnClickListener(this);
        binding.navigation.setItemIconTintList(null);
        NavController navController = Navigation.findNavController(this, R.id.navHostFragment);
        NavigationUI.setupWithNavController(binding.navigation, navController);
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            isOpened = false;
            binding.setIsOpened(false);
        });
        show = AnimationUtils.loadAnimation(getApplication(), R.anim.show);
        hide = AnimationUtils.loadAnimation(getApplication(), R.anim.hide);
    }

    @Override
    public void onClick(View v) {
        if (isOpened) {
            isOpened = false;
            binding.setIsOpened(false);
            binding.hotspotFab.setAnimation(hide);
            binding.eventsFab.setAnimation(hide);
            binding.attractionFab.setAnimation(hide);
            binding.mapFab.setAnimation(hide);
        } else {
            isOpened = true;
            binding.setIsOpened(true);
            binding.hotspotFab.setAnimation(show);
            binding.eventsFab.setAnimation(show);
            binding.attractionFab.setAnimation(show);
            binding.mapFab.setAnimation(show);
        }
    }
}
package com.example.androidtask.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.androidtask.data.network.ApiResponse;
import com.example.androidtask.repository.HomeRepository;

public class HomeViewModel extends ViewModel {
    private HomeRepository repository;

    public void setRepository(HomeRepository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ApiResponse> getHomeData() {
        return repository.getHomeData();
    }

    public MutableLiveData<Boolean> getLoading() {
        return repository.getIsLoading();
    }

    public MutableLiveData<String> getErrorMessage() {
        return repository.getErrorMessage();
    }
}

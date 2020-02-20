package com.example.androidtask.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.androidtask.data.model.ApiService;
import com.example.androidtask.data.network.ApiClient;
import com.example.androidtask.data.network.ApiResponse;
import com.example.androidtask.util.Constants;

import java.net.UnknownHostException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRepository {
    private static HomeRepository instance;
    private static ApiService apiService;
    private MutableLiveData<ApiResponse> shops = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private HashMap<String, String> headers = ApiClient.getHeaders();

    public static HomeRepository getInstance(Application application) {
        if (instance == null) {
            instance = new HomeRepository();
            apiService = ApiClient.getInstance(application);
        }
        return instance;
    }

    public MutableLiveData<ApiResponse> getHomeData() {
        isLoading.setValue(true);
        errorMessage.setValue("");
        apiService.getHomeData(headers).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                switch (response.code()) {
                    case 200:
                        shops.setValue(response.body());
                        break;
                }
                isLoading.setValue(false);
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                if (t instanceof UnknownHostException) {
                    errorMessage.setValue(Constants.NO_INTERNET);
                } else {
                    errorMessage.setValue(t.getMessage());
                }
                isLoading.setValue(false);
            }
        });
        return shops;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }
}

package com.example.androidtask.data.model;

import com.example.androidtask.data.network.ApiResponse;
import com.example.androidtask.util.Constants;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;

public interface ApiService {

    @GET(Constants.HOME)
    Call<ApiResponse> getHomeData(@HeaderMap Map<String, String> headers);

}

package com.example.jendi.projektgrupowy.services;

import com.example.jendi.projektgrupowy.models.LoginRequest;
import com.example.jendi.projektgrupowy.models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LoginService {

    @Headers("Content-Type: application/json")
    @POST("user/log-in")
    Call<LoginResponse> logIn(@Body LoginRequest loginRequest);
}

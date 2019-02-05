package com.example.jendi.projektgrupowy.services;

import com.example.jendi.projektgrupowy.models.LoginRequest;
import com.example.jendi.projektgrupowy.models.LoginResponse;
import com.example.jendi.projektgrupowy.models.RegistrationRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserService {

    @Headers("Content-Type: application/json")
    @POST("user/log-in")
    Call<LoginResponse> logIn(@Body LoginRequest loginRequest);

    @Headers("Content-Type: application/json")
    @POST("user/register")
    Call<LoginResponse> register(@Body RegistrationRequest registrationRequest);
}

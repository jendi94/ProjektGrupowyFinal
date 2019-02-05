package com.example.jendi.projektgrupowy.clients;

import com.example.jendi.projektgrupowy.models.LoginRequest;
import com.example.jendi.projektgrupowy.models.LoginResponse;
import com.example.jendi.projektgrupowy.models.RegistrationRequest;
import com.example.jendi.projektgrupowy.services.UserService;

import java.io.IOException;

import retrofit2.Call;

public class UserClient extends BaseClient{
    private UserService service;

    public UserClient() {
        super();
        service = retrofit.create(UserService.class);
    }

    public LoginResponse logIn(LoginRequest request) throws IOException {
        Call<LoginResponse> call = service.logIn(request);
        return call.execute().body();
    }

    public LoginResponse register(RegistrationRequest request) throws IOException {
        Call<LoginResponse> call = service.register(request);
        return call.execute().body();
    }
}

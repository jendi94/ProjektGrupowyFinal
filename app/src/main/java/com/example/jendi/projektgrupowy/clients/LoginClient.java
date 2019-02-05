package com.example.jendi.projektgrupowy.clients;

import com.example.jendi.projektgrupowy.models.LoginRequest;
import com.example.jendi.projektgrupowy.models.LoginResponse;
import com.example.jendi.projektgrupowy.services.LoginService;

import java.io.IOException;

import retrofit2.Call;

public class LoginClient extends BaseClient{
    private LoginService service;

    public LoginClient() {
        super();
        service = retrofit.create(LoginService.class);
    }

    public LoginResponse logIn(LoginRequest request) throws IOException {
        Call<LoginResponse> call = service.logIn(request);
        return call.execute().body();
    }
}

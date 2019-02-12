package com.example.jendi.projektgrupowy.clients;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseClient {
    private static final String BASE_URL = "http://52.157.228.97:7050/";
    protected Retrofit retrofit;

    public BaseClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setDateFormat("yyyy-MM-dd").create()))
                .build();
    }
}

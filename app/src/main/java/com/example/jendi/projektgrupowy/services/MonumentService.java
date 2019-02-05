package com.example.jendi.projektgrupowy.services;

import com.example.jendi.projektgrupowy.models.Monument;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MonumentService {

    @GET("monuments/all")
    Call<List<Monument>> getAllMonuments();
}

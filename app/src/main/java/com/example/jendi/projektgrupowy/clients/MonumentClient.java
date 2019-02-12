package com.example.jendi.projektgrupowy.clients;

import com.example.jendi.projektgrupowy.models.Monument;
import com.example.jendi.projektgrupowy.models.MonumentRequest;
import com.example.jendi.projektgrupowy.services.MonumentService;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class MonumentClient extends BaseClient {
    private MonumentService service;

    public MonumentClient() {
        super();
        service = retrofit.create(MonumentService.class);
    }

    public List<Monument> getAllApprovedMonuments() throws IOException {
        Call<List<Monument>> call = service.getAllApprovedMonuments();
        List<Monument> monuments = call.execute().body();
        return monuments;
    }

    public List<Monument> getAllUnapprovedMonuments() throws IOException {
        Call<List<Monument>> call = service.getAllUnapprovedMonuments();
        List<Monument> monuments = call.execute().body();
        return monuments;
    }

    public Monument addMonument(String token, MonumentRequest request) throws IOException {
        Call<Monument> call = service.addMonument(token, request);
        Response<Monument> response = call.execute();
        Monument monument = response.body();
        return monument;
     }

     public Monument approveMonument(String token, int id) throws IOException {
        Call<Monument> call = service.approveMonument(token, id);
        Response<Monument> response = call.execute();
        Monument monument = response.body();
        return monument;
     }
}

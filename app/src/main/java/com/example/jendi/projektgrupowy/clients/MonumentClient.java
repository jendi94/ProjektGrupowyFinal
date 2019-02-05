package com.example.jendi.projektgrupowy.clients;

import com.example.jendi.projektgrupowy.models.Monument;
import com.example.jendi.projektgrupowy.services.MonumentService;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

public class MonumentClient extends BaseClient {
    private MonumentService service;

    public MonumentClient() {
        super();
        service = retrofit.create(MonumentService.class);
    }

    public List<Monument> getAllMonuments() throws IOException {
        Call<List<Monument>> call = service.getAllMonuments();
        List<Monument> monuments = call.execute().body();
        return monuments;
     }
}

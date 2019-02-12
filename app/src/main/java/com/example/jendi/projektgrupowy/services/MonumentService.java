package com.example.jendi.projektgrupowy.services;

import com.example.jendi.projektgrupowy.models.Monument;
import com.example.jendi.projektgrupowy.models.MonumentRequest;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MonumentService {

    @GET("monuments?approved=true")
    Call<List<Monument>> getAllApprovedMonuments();

    @POST("monuments/add")
    Call<Monument> addMonument(@Header ("Authorization") String token, @Body MonumentRequest monumentRequest);

    @GET("monuments?approved=false")
    Call<List<Monument>> getAllUnapprovedMonuments();

    @PUT("monuments/approve/{monumentId}/monument")
    Call<Monument> approveMonument(@Header("Authorization") String token, @Path("monumentId") int monumentId);
}

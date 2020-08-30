package com.example.trial.Retrofit;

import com.example.trial.Model.Data;
import com.example.trial.Model.Root;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {
    @GET("apod")
    Call<Data> getData(@Query("api_key") String api_key,
                       @Query("date") String s);

    @GET("search?q=apollo%2011")
    Call<Root> getRoot();
}

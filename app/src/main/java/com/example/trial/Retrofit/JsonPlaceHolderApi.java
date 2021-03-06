package com.example.trial.Retrofit;

import com.example.trial.Model.Data;
import com.example.trial.Model.Root;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
// https://api.nasa.gov/planetary/apod?api_key=MXEWuJaXkmmC66pJt24HH5L2lSbrkIcLZPThhWKM&date=2020-08-28
public interface JsonPlaceHolderApi {
    @GET("apod")
    Call<Data> getData(@Query("api_key") String api_key, @Query("date") String s);

    @GET("search")
    Call<Root> getRoot(@Query("q") String search);

    @GET("{asset}/{NasaID}")
    Call<com.example.trial.Model.ClickSearch.Root> getRoot(@Path("asset") String asset,@Path("NasaID") String NasaId);
}

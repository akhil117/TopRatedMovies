package com.example.akhilbatchu.topratedmovies.Services;

import com.example.akhilbatchu.topratedmovies.Model.Movies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Endpoints {

    @GET("movie/popular")
    Call<Movies> getPopularMovies(@Query("api_key") String apikey);
}

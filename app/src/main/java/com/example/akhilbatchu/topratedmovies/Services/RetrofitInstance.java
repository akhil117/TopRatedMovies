package com.example.akhilbatchu.topratedmovies.Services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit = null;
    private static  String Base_url  = "https://api.themoviedb.org/3/";

    public static  Retrofit getCombine()

    {
        if(retrofit==null)
        {
            retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(Base_url).build();
        }
        return  retrofit;
    }
}

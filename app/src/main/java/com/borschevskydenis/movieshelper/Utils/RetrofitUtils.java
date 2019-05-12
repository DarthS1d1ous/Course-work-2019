package com.borschevskydenis.movieshelper.Utils;

import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;

public class RetrofitUtils {
    public static final String BASE_URL = "https://api.themoviedb.org";

    public static Retrofit createRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}


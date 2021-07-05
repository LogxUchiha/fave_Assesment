package com.logavance.faveassesment.request;

import com.logavance.faveassesment.utils.MovieApi;
import com.logavance.faveassesment.utils.credentials;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class servicey {

    private static Retrofit.Builder retrofitBuilder=
            new Retrofit.Builder()
            .baseUrl(credentials.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static MovieApi movieApi=retrofit.create(MovieApi.class);

    public static MovieApi getMovieApi(){
        return movieApi;
    }


}

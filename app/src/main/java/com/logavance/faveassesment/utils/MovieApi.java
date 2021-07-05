package com.logavance.faveassesment.utils;

import com.logavance.faveassesment.models.MovieModel;
import com.logavance.faveassesment.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    //Search for movies
    @GET("/3/discover/movie?api_key=328c283cd27bd1877d9080ccb1604c91&primary_release_date.lte=2016-12-31&sort_by=release_date.desc&page=1")
    Call<MovieSearchResponse> searchMovie(
            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") String page

    );


    @GET("3/movie/{movie_id}?")
    Call<MovieModel>getMovie(
            @Path("movie_id") int movie_id,
            @Query("api_key") String api_key

    );
}

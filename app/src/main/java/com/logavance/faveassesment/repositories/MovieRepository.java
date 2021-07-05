package com.logavance.faveassesment.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.logavance.faveassesment.models.MovieModel;
import com.logavance.faveassesment.request.MovieApiClient;
import com.logavance.faveassesment.utils.MovieApi;

import java.util.List;

public class MovieRepository {
    //this is class is acting as repositories

    private static MovieRepository instance;

    private MovieApiClient movieApiClient;

    private String mQuery;
    private int mPageNumber;


    public static MovieRepository getInstance(){
        if(instance == null){
            instance = new MovieRepository();
        }
        return instance;

    }

    private MovieRepository(){
    movieApiClient=movieApiClient.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies(){
        return movieApiClient.getMovies();
    }

    public void searchMovieApi(String query, int pageNumber){

        mQuery=query;
        mPageNumber=pageNumber;

        movieApiClient.searchMovieApi(query,pageNumber);

    }

    public void searchNextPage(){
        searchMovieApi(mQuery,mPageNumber+1);
    }
}



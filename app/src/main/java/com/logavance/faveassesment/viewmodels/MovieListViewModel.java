package com.logavance.faveassesment.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.logavance.faveassesment.models.MovieModel;
import com.logavance.faveassesment.repositories.MovieRepository;

import java.util.List;

public class MovieListViewModel extends ViewModel {


    private MovieRepository movieRepository;

    //constructor

    public MovieListViewModel() {

        movieRepository = MovieRepository.getInstance();

    }

    public LiveData<List<MovieModel>> getMovies(){
        return movieRepository.getMovies();

    }

    public void searchMovieApi(String query,int pageNumber){
        movieRepository.searchMovieApi(query, pageNumber);
    }


    public void searchNextpage(){
        movieRepository.searchNextPage();
    }
}

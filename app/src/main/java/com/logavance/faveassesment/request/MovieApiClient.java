package com.logavance.faveassesment.request;

import android.net.Credentials;
import android.net.wifi.hotspot2.pps.Credential;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.logavance.faveassesment.AppExecutors;
import com.logavance.faveassesment.R;
import com.logavance.faveassesment.models.MovieModel;
import com.logavance.faveassesment.response.MovieSearchResponse;
import com.logavance.faveassesment.utils.credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class MovieApiClient {
    private MutableLiveData<List<MovieModel>> mMovies = new MutableLiveData<>();

    private static MovieApiClient instance;

    private RetrieveMovieRunnable retrieveMovieRunnable;

    public static MovieApiClient getInstance() {

        if (instance == null) {
            instance = new MovieApiClient();
        }
        return instance;
    }


    private MovieApiClient() {

        mMovies = new MutableLiveData<>();
    }

    public LiveData<List<MovieModel>> getMovies() {
        return mMovies;

    }


    public void searchMovieApi(String query, int pageNumber) {

        if (retrieveMovieRunnable != null) {
            retrieveMovieRunnable = null;
        }

        retrieveMovieRunnable = new RetrieveMovieRunnable(query, pageNumber);
        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveMovieRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //cancelling the retrofit call
                myHandler.cancel(true);
            }
        }, 3000, TimeUnit.MILLISECONDS);
    }


    private class RetrieveMovieRunnable implements Runnable {

        private String query;
        private int pageNumber;
        boolean cancelRequest;


        public RetrieveMovieRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            cancelRequest = false;

        }

        @Override
        public void run() {

            try {
                Response response = getMovies(query, pageNumber).execute();

                if (cancelRequest) {
                    return;
                }

                if (response.code() == 200) {
                    List<MovieModel> list = new ArrayList<>(((MovieSearchResponse) response.body()).getMovies());
                    if (pageNumber == 1) {
                        mMovies.postValue(list);
                    } else {
                        List<MovieModel> currentMovies = mMovies.getValue();
                        currentMovies.addAll(list);
                        mMovies.postValue(currentMovies);

                    }
                } else {
                    String error = response.errorBody().string();
                    Log.v("Tag", "Error" + error);
                    mMovies.postValue(null);

                }

            } catch (IOException e) {
                e.printStackTrace();
                mMovies.postValue(null);
            }


        }


        private Call<MovieSearchResponse> getMovies(String query, int pageNumber) {
            return servicey.getMovieApi().searchMovie(
                    credentials.API_KEY,
                    query,
                    String.valueOf(pageNumber)
            );
        }

        private void cancelRequest() {
            Log.v("Tag", "Cancelling Search Req");
            cancelRequest = true;
        }


    }
}





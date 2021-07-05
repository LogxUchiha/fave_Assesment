package com.logavance.faveassesment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Credentials;
import android.net.wifi.hotspot2.pps.Credential;
import android.os.Bundle;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.logavance.faveassesment.adapters.MovieRecyclerView;
import com.logavance.faveassesment.adapters.OnMovieListener;
import com.logavance.faveassesment.models.MovieModel;
import com.logavance.faveassesment.request.servicey;
import com.logavance.faveassesment.response.MovieSearchResponse;
import com.logavance.faveassesment.utils.MovieApi;
import com.logavance.faveassesment.utils.credentials;
import com.logavance.faveassesment.viewmodels.MovieListViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListActivity extends AppCompatActivity implements OnMovieListener {

    SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private MovieRecyclerView movieRecyclerViewAdapter;


    //ViewModel
    private MovieListViewModel movieListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);

        //searchView
       // SetupSearchView();

        recyclerView = findViewById(R.id.recyclerView);

        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);





        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                ConfigureReyclerView();
                ObserveAnyChange();

            }
        });


    }


    //observing data change
    private void ObserveAnyChange(){
        movieListViewModel.getMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                //Observing data change
                if(movieModels!=null){
                    for(MovieModel movieModel:movieModels){
                        Log.v("Tag","OnChanged "+movieModel.getTitle());

                        movieRecyclerViewAdapter.setmMovies(movieModels);
                    }
                }
                
            }
        });

    }




    private void ConfigureReyclerView(){
        movieRecyclerViewAdapter=new MovieRecyclerView(this);

        recyclerView.setAdapter(movieRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {

                if(!recyclerView.canScrollVertically(1)){
                    movieListViewModel.searchNextpage();

                }
            }
        });

    }

    @Override
    public void onMovieClick(int position) {

        Intent intent =new Intent(this,MovieDetails.class);
        intent.putExtra("movie", movieRecyclerViewAdapter.getSelectedMovie(position));
        startActivity(intent);
    }

    @Override
    public void onCategoryClick(String category) {

    }


}


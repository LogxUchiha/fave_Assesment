package com.logavance.faveassesment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.logavance.faveassesment.models.MovieModel;

public class MovieDetails extends AppCompatActivity {
    private ImageView imageViewDetails;
    private TextView titleDetails,descDetails, genreDetails, langDetails;
    private RatingBar ratingBarDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        
        imageViewDetails=findViewById(R.id.imageView_details);
        titleDetails=findViewById(R.id.textView_title_details);
        descDetails=findViewById(R.id.textView_desc_details);
        ratingBarDetails=findViewById(R.id.ratingBar_details);
        genreDetails=findViewById(R.id.genre_detail);
        langDetails=findViewById(R.id.lang_detail);


        GetDataFromIntent();
        
    }

    private void GetDataFromIntent() {
        if(getIntent().hasExtra("movie")){
            MovieModel movieModel= getIntent().getParcelableExtra("movie");

            titleDetails.setText(movieModel.getTitle());
            descDetails.setText(movieModel.getMovie_overview());
            genreDetails.setText("" +movieModel.getGenres());
            langDetails.setText(""+movieModel.getOriginal_language());
            ratingBarDetails.setRating((movieModel.getVote_average())/2);

            Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w500"
                    +movieModel.getPoster_path())
                    .into(imageViewDetails);



        }
    }
}
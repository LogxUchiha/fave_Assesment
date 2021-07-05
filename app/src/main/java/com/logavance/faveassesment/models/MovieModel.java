package com.logavance.faveassesment.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieModel implements Parcelable {
    //model Class for movies

    private String title;
    private String poster_path;
    private String release_date;
    private int movie_id;
    private int budget;

    private final String genres;
    private float vote_average;
    @SerializedName("overview")
    @Expose
    private String movie_overview;
    private String original_language;


//constructor

    public MovieModel(String title, String poster_path, String release_date, int movie_id,
                      int budget, String genres, float vote_average, String movie_overview,
                      String original_language) {
        this.title = title;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.movie_id = movie_id;
        this.budget = budget;
        this.genres = genres;
        this.vote_average = vote_average;
        this.movie_overview = movie_overview;
        this.original_language = original_language;
    }


//getter

    protected MovieModel(Parcel in) {
        title = in.readString();
        poster_path = in.readString();
        release_date = in.readString();
        movie_id = in.readInt();
        budget = in.readInt();
        genres = in.readString();
        vote_average = in.readFloat();
        movie_overview = in.readString();
        original_language=in.readString();
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    public String getTitle() {
        return title;
    }


    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public int getBudget() {
        return budget;
    }

    public String getGenres() {
        return genres;
    }

    public float getVote_average() {
        return vote_average;
    }

    public String getMovie_overview() {
        return movie_overview;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "MovieModel{" +
                "title='" + title + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", release_date='" + release_date + '\'' +
                ", movie_id=" + movie_id +
                ", budget=" + budget +
                ", genres='" + genres + '\'' +
                ", vote_average=" + vote_average +
                ", movie_overview='" + movie_overview + '\'' +
                ", original_language='" + original_language + '\'' +
                '}';
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(poster_path);
        dest.writeString(release_date);
        dest.writeInt(movie_id);
        dest.writeInt(budget);
        dest.writeString(genres);
        dest.writeFloat(vote_average);
        dest.writeString(movie_overview);
        dest.writeString(original_language);

    }
}

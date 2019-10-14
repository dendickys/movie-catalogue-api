package com.dendickys.moviecatalogueapi.interfaces;

import com.dendickys.moviecatalogueapi.model.GetMovies;
import com.dendickys.moviecatalogueapi.model.GetTvShow;
import com.dendickys.moviecatalogueapi.model.Movies;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitServices {
    String BASE_URL = "https://api.themoviedb.org/3/discover/";
    String API_KEY = "320c6e292bcd195d89d1eea9d49225d4";

    @GET("movie?api_key=" + API_KEY + "&language=en-US")
    Call<GetMovies> getAllMovies();

    @GET("tv?api_key=" + API_KEY + "&language=en-US")
    Call<GetTvShow> getAllTvShow();
}

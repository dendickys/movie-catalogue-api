package com.dendickys.moviecatalogueapi.interfaces;

import com.dendickys.moviecatalogueapi.BuildConfig;
import com.dendickys.moviecatalogueapi.model.ListMovies;
import com.dendickys.moviecatalogueapi.model.ListTvShow;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApi {
    String BASE_URL = "https://api.themoviedb.org/3/";
    String BASE_URL_POSTER = "https://image.tmdb.org/t/p/";
    String API_KEY = BuildConfig.TMDB_API_KEY;

    @GET("discover/movie?api_key=" + API_KEY + "&language=en-US")
    Call<ListMovies> getAllMovies();

    @GET("discover/tv?api_key=" + API_KEY + "&language=en-US")
    Call<ListTvShow> getAllTvShow();
}

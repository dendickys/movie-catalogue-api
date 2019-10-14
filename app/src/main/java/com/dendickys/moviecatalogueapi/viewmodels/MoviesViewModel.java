package com.dendickys.moviecatalogueapi.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dendickys.moviecatalogueapi.interfaces.RetrofitServices;
import com.dendickys.moviecatalogueapi.model.GetMovies;
import com.dendickys.moviecatalogueapi.model.GetTvShow;
import com.dendickys.moviecatalogueapi.model.Movies;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Movies>> moviesList;

    public LiveData<ArrayList<Movies>> getAllMovies() {
        if (moviesList == null) {
            moviesList = new MutableLiveData<ArrayList<Movies>>();
            loadMovies();
        }
        return moviesList;
    }

    private void loadMovies() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitServices.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitServices api = retrofit.create(RetrofitServices.class);
        Call<GetMovies> call = api.getAllMovies();

        call.enqueue(new Callback<GetMovies>() {
            @Override
            public void onResponse(Call<GetMovies> call, Response<GetMovies> response) {
                assert response.body() != null;
                moviesList.setValue(response.body().getListMovies());
            }

            @Override
            public void onFailure(Call<GetMovies> call, Throwable t) {
                Log.d("onFailure: ", Objects.requireNonNull(t.getMessage()));
            }
        });
    }
}

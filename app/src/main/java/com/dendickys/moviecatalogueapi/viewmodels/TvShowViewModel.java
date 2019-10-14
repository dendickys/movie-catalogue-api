package com.dendickys.moviecatalogueapi.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dendickys.moviecatalogueapi.interfaces.RetrofitServices;
import com.dendickys.moviecatalogueapi.model.GetTvShow;
import com.dendickys.moviecatalogueapi.model.Movies;
import com.dendickys.moviecatalogueapi.model.TvShow;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TvShowViewModel extends ViewModel {
    private MutableLiveData<ArrayList<TvShow>> tvShowList;

    public LiveData<ArrayList<TvShow>> getAllTvShow() {
        if (tvShowList == null) {
            tvShowList = new MutableLiveData<ArrayList<TvShow>>();
            loadTvShow();
        }
        return tvShowList;
    }

    private void loadTvShow() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitServices.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitServices api = retrofit.create(RetrofitServices.class);
        Call<GetTvShow> call = api.getAllTvShow();

        call.enqueue(new Callback<GetTvShow>() {
            @Override
            public void onResponse(Call<GetTvShow> call, Response<GetTvShow> response) {
                assert response.body() != null;
                tvShowList.setValue(response.body().getListTvShow());
            }

            @Override
            public void onFailure(Call<GetTvShow> call, Throwable t) {
                Log.d("onFailure: ", Objects.requireNonNull(t.getMessage()));
            }
        });
    }
}

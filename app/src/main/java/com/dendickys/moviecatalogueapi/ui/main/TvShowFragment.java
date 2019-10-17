package com.dendickys.moviecatalogueapi.ui.main;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dendickys.moviecatalogueapi.R;
import com.dendickys.moviecatalogueapi.adapter.TvShowAdapter;
import com.dendickys.moviecatalogueapi.model.TvShow;
import com.dendickys.moviecatalogueapi.viewmodels.TvShowViewModel;

import java.util.ArrayList;

public class TvShowFragment extends Fragment {

    private RecyclerView recyclerView;
    private TvShowAdapter tvShowAdapter;
    private ProgressBar progressBar;

    public TvShowFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.progressBar_tv_show);
        showLoading(true);

        recyclerView = view.findViewById(R.id.rv_tv_show);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        TvShowViewModel tvShowViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(TvShowViewModel.class);
        showLoading(true);

        tvShowViewModel.getAllTvShow().observe(this, new Observer<ArrayList<TvShow>>() {
            @Override
            public void onChanged(ArrayList<TvShow> tvShows) {
                if (tvShows != null) {
                    tvShowAdapter = new TvShowAdapter(tvShows);
                    tvShowAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(tvShowAdapter);
                    showLoading(false);
                }
            }
        });
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}

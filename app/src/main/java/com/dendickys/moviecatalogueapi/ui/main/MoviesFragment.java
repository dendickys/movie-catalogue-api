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
import com.dendickys.moviecatalogueapi.adapter.MoviesAdapter;
import com.dendickys.moviecatalogueapi.model.Movies;
import com.dendickys.moviecatalogueapi.viewmodel.MoviesViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {

    private MoviesAdapter moviesAdapter;
    private ProgressBar progressBar;
    private MoviesViewModel moviesViewModel;

    public MoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.progressBar_movies);

        RecyclerView recyclerView = view.findViewById(R.id.rv_movies);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        moviesAdapter = new MoviesAdapter();
        moviesAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(moviesAdapter);

        moviesViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MoviesViewModel.class);
        moviesViewModel.setMovies();
        showLoading(true);

        moviesViewModel.getMovies().observe(this, new Observer<ArrayList<Movies>>() {
            @Override
            public void onChanged(ArrayList<Movies> movies) {
                if (movies != null) {
                    moviesAdapter.setListMovies(movies);
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

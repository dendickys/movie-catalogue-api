package com.dendickys.moviecatalogueapi;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.dendickys.moviecatalogueapi.model.Movies;
import com.dendickys.moviecatalogueapi.viewmodels.MoviesViewModel;

import java.util.ArrayList;

public class DetailMoviesActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private MoviesViewModel moviesViewModel;
    public static final String EXTRA_MOVIE = "extra_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movies);

        progressBar = findViewById(R.id.progressBar_detail_movie);

        /*moviesViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MoviesViewModel.class);
        moviesViewModel.setMovies();
        showLoading(true);

        moviesViewModel.getMovies().observe(this, new Observer<ArrayList<Movies>>() {
            @Override
            public void onChanged(ArrayList<Movies> movies) {
                if (movies != null) {
                    movies = getIntent().getParcelableExtra(EXTRA_MOVIE);

                    if (getSupportActionBar() != null) {
                        assert movies != null;
                        //getSupportActionBar().setTitle(movies.getTitle());
                    }

                    ImageView imgPoster = findViewById(R.id.img_poster_movie);
                    TextView tvOverview = findViewById(R.id.tv_overview_movie);

                    assert movies != null;
                    //imgPoster.setImageResource(Integer.valueOf(movies.getPoster_path()));
                    //tvOverview.setText(movies.getOverview());

                    showLoading(false);
                }
            }
        });*/
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}

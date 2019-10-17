package com.dendickys.moviecatalogueapi;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.dendickys.moviecatalogueapi.model.Movies;
import com.dendickys.moviecatalogueapi.viewmodels.MoviesViewModel;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;

import static com.dendickys.moviecatalogueapi.interfaces.RetrofitApi.BASE_URL_POSTER;

public class DetailMoviesActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView mPoster;
    private TextView mReleaseDate, mVoteAverage, mOverview;
    private ProgressBar progressBar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    public static final String MOVIE_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movies);

        bindData();
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, android.R.color.white));
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.white));

        MoviesViewModel detailMovieViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MoviesViewModel.class);
        showLoading(true);

        detailMovieViewModel.getAllMovies().observe(this, new Observer<ArrayList<Movies>>() {
            @Override
            public void onChanged(ArrayList<Movies> movies) {
                if (movies != null) {
                    setDetailMovie();
                    showLoading(false);
                }
            }
        });
    }

    private void bindData() {
        toolbar = findViewById(R.id.toolbar_movie);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_movie);
        mPoster = findViewById(R.id.img_poster_movie);
        mReleaseDate = findViewById(R.id.tv_release_date_movie);
        mVoteAverage = findViewById(R.id.tv_vote_average_movie);
        mOverview = findViewById(R.id.tv_overview_movie);
        progressBar = findViewById(R.id.progressBar_detail_movie);
    }

    private void setDetailMovie() {
        Movies movie = getIntent().getParcelableExtra(MOVIE_ID);
        assert movie != null;
        collapsingToolbarLayout.setTitle(movie.getTitle());
        Glide.with(getApplicationContext())
                .load(BASE_URL_POSTER + "w500/" + movie.getPoster_path())
                .into(mPoster);
        mReleaseDate.setText(movie.getRelease_date());
        mVoteAverage.setText(movie.getVote_average());
        mOverview.setText(movie.getOverview());
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

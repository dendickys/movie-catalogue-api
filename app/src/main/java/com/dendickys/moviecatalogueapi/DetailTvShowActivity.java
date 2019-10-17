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
import com.dendickys.moviecatalogueapi.model.TvShow;
import com.dendickys.moviecatalogueapi.viewmodels.TvShowViewModel;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;

public class DetailTvShowActivity extends AppCompatActivity {

    private ImageView mPoster;
    private TextView mReleaseDate, mVoteAverage, mOverview;
    private ProgressBar progressBar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    public static final String TV_SHOW_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv_show);

        Toolbar toolbar = findViewById(R.id.toolbar_tvshow);
        setSupportActionBar(toolbar);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_tvshow);
        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, android.R.color.white));
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.white));

        mPoster = findViewById(R.id.img_poster_tvshow);
        mReleaseDate = findViewById(R.id.tv_release_date_tvshow);
        mVoteAverage = findViewById(R.id.tv_vote_average_tvshow);
        mOverview = findViewById(R.id.tv_overview_tvshow);
        progressBar = findViewById(R.id.progressBar_detail_tvshow);

        TvShowViewModel tvShowViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(TvShowViewModel.class);
        showLoading(true);

        tvShowViewModel.getAllTvShow().observe(this, new Observer<ArrayList<TvShow>>() {
            @Override
            public void onChanged(ArrayList<TvShow> tvShows) {
                if (tvShows != null) {
                    setDetailTvShow();
                    showLoading(false);
                }
            }
        });
    }

    private void setDetailTvShow() {
        TvShow tvShow = getIntent().getParcelableExtra(TV_SHOW_ID);
        assert tvShow != null;
        collapsingToolbarLayout.setTitle(tvShow.getTitle());
        Glide.with(getApplicationContext())
                .load("https://image.tmdb.org/t/p/w500/" + tvShow.getPoster_path())
                .into(mPoster);
        mReleaseDate.setText(tvShow.getRelease_date());
        mVoteAverage.setText(tvShow.getVote_average());
        mOverview.setText(tvShow.getOverview());
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}

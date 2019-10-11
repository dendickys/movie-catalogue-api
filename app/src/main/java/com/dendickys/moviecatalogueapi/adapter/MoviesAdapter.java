package com.dendickys.moviecatalogueapi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dendickys.moviecatalogueapi.R;
import com.dendickys.moviecatalogueapi.model.Movies;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {

    private ArrayList<Movies> listMovies = new ArrayList<>();

    public void setListMovies(ArrayList<Movies> items) {
        listMovies.clear();
        listMovies.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies, parent, false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        holder.bind(listMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder {
        private ImageView posterMovie;
        private TextView titleMovie, releaseDataMovie, overviewMovie;

        public MoviesViewHolder(@NonNull View itemView) {
            super(itemView);

            posterMovie = itemView.findViewById(R.id.img_poster_movie);
            titleMovie = itemView.findViewById(R.id.tv_title_movie);
            releaseDataMovie = itemView.findViewById(R.id.tv_release_date_movie);
            overviewMovie = itemView.findViewById(R.id.tv_overview_movie);
        }

        void bind(Movies movieItems) {
            Glide.with(itemView.getContext())
                    .load("https://image.tmdb.org/t/p/w185/" + movieItems.getPoster_path())
                    .apply(new RequestOptions()).override(100, 150)
                    .into(posterMovie);
            titleMovie.setText(movieItems.getTitle());
            releaseDataMovie.setText(movieItems.getRelease_date());
            overviewMovie.setText(movieItems.getOverview());
        }
    }
}

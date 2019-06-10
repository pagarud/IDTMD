package io.pagarusha.movieapp.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import io.pagarusha.movieapp.R;
import io.pagarusha.movieapp.model.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private final String BASE_IMAGE_URL = "http://image.tmdb.org/t/p/w500/";

    private List<Movie> movies;
    private Context context;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.grid_cell, viewGroup, false);

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {

        // Get a single item in the list from its position
        final Movie movie = movies.get(i);

        // Set view properties
        Glide.with(context)
                .load(BASE_IMAGE_URL + movie.getPosterPath())
                .placeholder(R.mipmap.placeholder)
                .into(movieViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void swapList(List<Movie> newList) {
        movies = newList;
        if (newList != null) {
            this.notifyDataSetChanged();
        }
    }
}

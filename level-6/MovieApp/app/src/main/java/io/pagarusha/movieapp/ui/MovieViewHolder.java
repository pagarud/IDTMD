package io.pagarusha.movieapp.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import io.pagarusha.movieapp.R;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        this.imageView = itemView.findViewById(R.id.image_view_poster);
    }
}

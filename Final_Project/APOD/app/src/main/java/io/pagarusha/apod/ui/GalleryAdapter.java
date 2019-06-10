package io.pagarusha.apod.ui;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import com.bumptech.glide.Glide;

import io.pagarusha.apod.R;
import io.pagarusha.apod.model.Apod;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryViewHolder> {

    private List<Apod> apods;
    private Context context;

    public GalleryAdapter(List<Apod> apods, Context context) {
        this.apods = apods;
        this.context = context;
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.listitem_gallery, viewGroup, false);

        return new GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder galleryViewHolder, int i) {
        final Apod apod = apods.get(i);

        galleryViewHolder.date.setText(apod.getDate());
        galleryViewHolder.title.setText(apod.getTitle());

        // Set view properties
        Glide.with(context)
                .load(apod.getUrl())
                .placeholder(R.drawable.placeholder_square)
                .into(galleryViewHolder.apodImage);
    }

    @Override
    public int getItemCount() {
        return apods.size();
    }
}

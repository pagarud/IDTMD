package io.pagarusha.apod.ui;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

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
        final GalleryViewHolder vh = galleryViewHolder;

        vh.date.setText(apod.getDate());
        vh.title.setText(apod.getTitle());

        Glide.with(context)
                .load(apod.getUrl())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        vh.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        vh.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(vh.apodImage);

        // Set view properties
//        Glide.with(context)
//                .load(apod.getUrl())
//                .placeholder(R.drawable.placeholder_square)
//                .into(galleryViewHolder.apodImage);
    }

    @Override
    public int getItemCount() {
        return apods.size();
    }
}

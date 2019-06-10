package io.pagarusha.apod.ui;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import io.pagarusha.apod.R;

public class GalleryViewHolder extends RecyclerView.ViewHolder {

    public ImageView apodImage;
    public TextView date;
    public TextView title;
    public ProgressBar progressBar;

    public GalleryViewHolder(@NonNull View itemView) {
        super(itemView);
        this.apodImage = itemView.findViewById(R.id.imageview_galleryitem);
        this.date = itemView.findViewById(R.id.labeldate_galleryitem);
        this.title = itemView.findViewById(R.id.labeltitle_galleryitem);
        this.progressBar = itemView.findViewById(R.id.progress_listitem_loadimage);
    }
}

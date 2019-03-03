package io.pagarusha.geoguess.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.pagarusha.geoguess.GeoObjectViewHolder;
import io.pagarusha.geoguess.R;
import io.pagarusha.geoguess.model.GeoObject;

public class GeoObjectAdapter extends RecyclerView.Adapter<GeoObjectViewHolder> {

    private Context context;
    public List<GeoObject> listGeoObject;

    public GeoObjectAdapter(Context context, List<GeoObject> listGeoObject) {
        this.context = context;
        this.listGeoObject = listGeoObject;
    }

    @NonNull
    @Override
    public GeoObjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.grid_cell, parent, false);

        return new GeoObjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GeoObjectViewHolder holder, int position) {

        // Gets a single item in the list from its position
        final GeoObject geoObject = listGeoObject.get(position);


        // The holder argument is used to reference the views inside the viewHolder
        // Populate the views with the data from the list
        holder.geoImage.setImageResource(geoObject.getmGeoImageId());
    }

    @Override
    public int getItemCount() {

        return listGeoObject.size();
    }
}

package io.pagarusha.traileraddict.ui;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.pagarusha.traileraddict.ui.model.OnRecyclerViewItemClickListener;
import io.pagarusha.traileraddict.ui.model.RecyclerViewRow;

public class RecyclerViewAdapter<T>
        extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<T> mDataset;
    private OnRecyclerViewItemClickListener<T> onRecyclerViewItemClickListener;
    private int layoutId;

    public RecyclerViewAdapter(ArrayList<T> measurements,
                               OnRecyclerViewItemClickListener<T> onRecyclerViewItemClickListener,
                               int layoutId) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
        this.mDataset = measurements;
        this.layoutId = layoutId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerViewRow<T> row = (RecyclerViewRow<T>) LayoutInflater
                .from(parent.getContext()).inflate(layoutId, parent, false);
        ViewHolder vh = new ViewHolder(row);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mRow.showData(mDataset.get(position));
        ((View) holder.mRow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onRecyclerViewItemClickListener != null) {
                    onRecyclerViewItemClickListener.onItemClick(mDataset.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void setItems(List<T> items) throws IllegalArgumentException {
        if (items == null) {
            throw new IllegalArgumentException("Cannot set `null` item to the Recycler adapter");
        } else {
            mDataset.clear();
            mDataset.addAll(items);
            notifyDataSetChanged();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public RecyclerViewRow mRow;

        public ViewHolder(RecyclerViewRow itemView) {
            super((View) itemView);
            mRow = itemView;
        }
    }

}

package io.pagarusha.bucketlist;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.pagarusha.bucketlist.model.BucketListItem;

public class BucketListAdapter extends RecyclerView.Adapter<BucketListItemViewHolder> {

    public List<BucketListItem> items;
    private Context context;

    public BucketListAdapter(Context context, List<BucketListItem> items) {
        this.context = context;
        this.items = new ArrayList<>();
        this.items = items;
    }

    public void add(BucketListItem item) {
        this.items.add(item);
    }

    @NonNull
    @Override
    public BucketListItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.bucket_list_item_view, viewGroup, false);

        return new BucketListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BucketListItemViewHolder bucketListItemViewHolder, int i) {

        // Gets a single item in the list from its position
        final BucketListItem item = items.get(i);

        // The holder argument is used to reference the views inside the viewholder
        // Populate the views with the data from the list
        bucketListItemViewHolder.title.setText(item.getTitle());
        bucketListItemViewHolder.description.setText(item.getDescription());
        bucketListItemViewHolder.checked.setChecked(item.isChecked());

        // Decorate title and description with strikethrough if item is checked
        if (item.isChecked()) {
            bucketListItemViewHolder.title.setPaintFlags(bucketListItemViewHolder.title.getPaintFlags() |
                    Paint.STRIKE_THRU_TEXT_FLAG);
            bucketListItemViewHolder.description.setPaintFlags(bucketListItemViewHolder.description.getPaintFlags() |
                    Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            bucketListItemViewHolder.title.setPaintFlags(bucketListItemViewHolder.title.getPaintFlags() &
                    ~Paint.STRIKE_THRU_TEXT_FLAG);
            bucketListItemViewHolder.description.setPaintFlags(bucketListItemViewHolder.description.getPaintFlags() &
                    ~Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

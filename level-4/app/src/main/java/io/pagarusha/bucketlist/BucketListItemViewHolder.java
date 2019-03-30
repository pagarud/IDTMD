package io.pagarusha.bucketlist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class BucketListItemViewHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public TextView description;
    public CheckBox checked;

    public BucketListItemViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.bucket_list_item_title);
        description = itemView.findViewById(R.id.bucket_list_item_description);
        checked = itemView.findViewById(R.id.checkbox_list_item);
    }
}

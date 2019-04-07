package io.pagarusha.gamebacklog.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import io.pagarusha.gamebacklog.R;

public class GameViewHolder extends RecyclerView.ViewHolder {

    public TextView tvTitle;
    public TextView tvPlatform;
    public TextView tvStatus;
    public TextView tvDate;

    public GameViewHolder(@NonNull View itemView) {
        super(itemView);
        this.tvTitle = itemView.findViewById(R.id.tv_title);
        this.tvPlatform = itemView.findViewById(R.id.tv_platform);
        this.tvStatus = itemView.findViewById(R.id.tv_status);
        this.tvDate = itemView.findViewById(R.id.tv_date);
    }

}

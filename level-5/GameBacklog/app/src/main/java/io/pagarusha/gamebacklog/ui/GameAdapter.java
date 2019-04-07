package io.pagarusha.gamebacklog.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import io.pagarusha.gamebacklog.R;
import io.pagarusha.gamebacklog.model.Game;

public class GameAdapter extends RecyclerView.Adapter<GameViewHolder> {

    private List<Game> mGames;

    public GameAdapter(List<Game> games) {
        this.mGames = games;
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.card_view, viewGroup, false);

        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder gameViewHolder, int i) {

        // Get a single item in the list from its position
        final Game game = mGames.get(i);

        // The holder argument is used to reference the views inside the viewholder
        // Populate the views with the data from the list
        gameViewHolder.tvTitle.setText(game.getTitle());
        gameViewHolder.tvPlatform.setText(game.getPlatform());
        gameViewHolder.tvStatus.setText(game.getStatus());

        // Format date
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = dateFormat.format(game.getDate());
        gameViewHolder.tvDate.setText(dateString);

    }

    @Override
    public int getItemCount() {
        return mGames.size();
    }

    public void swapList (List<Game> newList) {
        mGames = newList;
        if (newList != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }
}

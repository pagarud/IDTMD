package io.pagarusha.gamebacklog.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import io.pagarusha.gamebacklog.model.Game;
import io.pagarusha.gamebacklog.model.GameRepository;

public class MainViewModel extends AndroidViewModel {

    private GameRepository mGameRepository;
    private LiveData<List<Game>> mGames;

    public MainViewModel(@NonNull Application application) {
        super(application);
        mGameRepository = new GameRepository(application.getApplicationContext());
        mGames = mGameRepository.getAllGames();
    }

    // Get all
    public LiveData<List<Game>> getAllGames() {

        return mGames;
    }

    // Insert one
    public void insert(Game game) {
        mGameRepository.insert(game);
    }

    public void insert(List<Game> games) {
        mGameRepository.insert(games);
    }

    // Update one
    public void update(Game game) {
        mGameRepository.update(game);
    }


    // Delete one
    public void delete(Game game) {
        mGameRepository.delete(game);
    }


    // Delete all
    public void delete(List<Game> games) {
        mGameRepository.deleteAll(games);
    }

}

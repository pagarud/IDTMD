package io.pagarusha.gamebacklog.model;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.pagarusha.gamebacklog.database.GameDao;
import io.pagarusha.gamebacklog.database.GameRoomDatabase;

public class GameRepository {

    private GameRoomDatabase mGameDatabase;
    private GameDao mDao;
    private LiveData<List<Game>> mGames;
    private Executor mExecutor = Executors.newSingleThreadExecutor();

    public GameRepository(Context context) {
        mGameDatabase = GameRoomDatabase.getDatabase(context);
        mDao = mGameDatabase.gameDao();
        mGames = mDao.getAllGames();
    }

    // Get all
    public LiveData<List<Game>> getAllGames() {
        return mGames;
    }

    // Insert
    public void insert(final Game game) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mDao.insert(game);
            }
        });
    }

    public void insert(final List<Game> games) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mDao.insert(games);
            }
        });
    }


    // Update
    public void update(final Game game) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mDao.update(game);
            }
        });
    }


    // Delete one
    public void delete(final Game game) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mDao.delete(game);
            }
        });
    }


    // Delete all
    public void deleteAll(final List<Game> games) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mDao.delete(games);
            }
        });
    }
}

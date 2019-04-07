package io.pagarusha.gamebacklog.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.pagarusha.gamebacklog.model.Game;

@Dao
public interface GameDao {

    @Insert
    void insert(Game game);

    @Insert
    void insert(List<Game> games);

    @Update
    void update(Game game);

    @Delete
    void delete(Game game);

    @Delete
    void delete(List<Game> games);

    @Query("SELECT * from game_table")
    LiveData<List<Game>> getAllGames();

}

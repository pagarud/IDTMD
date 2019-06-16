package io.pagarusha.traileraddict.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.pagarusha.traileraddict.db.entity.Movie;
import io.pagarusha.traileraddict.db.entity.MovieGist;
import io.pagarusha.traileraddict.db.entity.Person;
import io.pagarusha.traileraddict.db.entity.PersonGist;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertMovieList(List<MovieGist> list);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertPersonGistList(List<PersonGist> list);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertMovie(Movie movie);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertPerson(Person person);

    @Query("SELECT * FROM moviegist_table")
    LiveData<List<MovieGist>> loadPopularMovies();

    @Query("SELECT * FROM persongist_table")
    LiveData<List<PersonGist>> loadPopularPeople();

    @Query("SELECT * FROM movie_table WHERE id = :movieId")
    LiveData<Movie> loadMovie(int movieId);

    @Query("SELECT * FROM person_table WHERE id = :personId")
    LiveData<Person> loadPerson(int personId);

}

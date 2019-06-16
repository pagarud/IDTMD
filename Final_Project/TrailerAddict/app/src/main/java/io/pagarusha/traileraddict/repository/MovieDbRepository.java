package io.pagarusha.traileraddict.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.pagarusha.traileraddict.db.MovieRoomDatabase;
import io.pagarusha.traileraddict.db.dao.MovieDao;
import io.pagarusha.traileraddict.db.entity.Movie;
import io.pagarusha.traileraddict.db.entity.MovieGist;
import io.pagarusha.traileraddict.db.entity.Person;
import io.pagarusha.traileraddict.db.entity.PersonGist;

public class MovieDbRepository implements MovieDao {

    private MovieDao mMovieDao;
    private LiveData<List<MovieGist>> mPopularMovies;
    private LiveData<List<PersonGist>> mPopularPeople;
    private LiveData<Movie> mMovie;
    private LiveData<Person> mPerson;
    private Application mApplcation;
    private Executor mExecutor = Executors.newSingleThreadExecutor();

    public MovieDbRepository(Application application) {
        MovieRoomDatabase db = MovieRoomDatabase.getDatabase(application);
        mMovieDao = db.movieDao();
        mPopularMovies = mMovieDao.loadPopularMovies();
        mPopularPeople = mMovieDao.loadPopularPeople();
        mApplcation = application;
    }

    public LiveData<List<MovieGist>> getPopularMovies() {
        return mPopularMovies;
    }

    public LiveData<List<PersonGist>> getPopularPeople() {
        return mPopularPeople;
    }

    public LiveData<Movie> getMovie(int id) {
        return mMovie;
    }

    @Override
    public void insertMovieList(final List<MovieGist> list) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mMovieDao.insertMovieList(list);
            }
        });
    }

    @Override
    public void insertPersonGistList(final List<PersonGist> list) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mMovieDao.insertPersonGistList(list);
            }
        });
    }

    @Override
    public void insertMovie(final Movie movie) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mMovieDao.insertMovie(movie);
            }
        });
    }

    @Override
    public void insertPerson(final Person person) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mMovieDao.insertPerson(person);
            }
        });

    }

    @Override
    public LiveData<List<MovieGist>> loadPopularMovies() {
        return mMovieDao.loadPopularMovies();
    }

    @Override
    public LiveData<List<PersonGist>> loadPopularPeople() {
        return mMovieDao.loadPopularPeople();
    }

    @Override
    public LiveData<Movie> loadMovie(int movieId) {

        return null;
    }

    @Override
    public LiveData<Person> loadPerson(final int personId) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mPerson = mMovieDao.loadPerson(personId);
            }
        });

        return mPerson;
    }

    private static class insertPersonGistListAsyncTask extends AsyncTask<List<PersonGist>, Void, Void> {

        private MovieDao mAsyncTaskDao;

        insertPersonGistListAsyncTask(MovieDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final List<PersonGist>... params) {
            mAsyncTaskDao.insertPersonGistList(params[0]);
            return null;
        }
    }

    // Helper method to create async task while inserting into room db
    private static class insertListAsyncTask extends AsyncTask<List<MovieGist>, Void, Void> {

        private MovieDao mAsyncTaskDao;

        insertListAsyncTask(MovieDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final List<MovieGist>... params) {
            mAsyncTaskDao.insertMovieList(params[0]);
            return null;
        }
    }

}

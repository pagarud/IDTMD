package io.pagarusha.traileraddict.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.pagarusha.traileraddict.db.entity.Movie;
import io.pagarusha.traileraddict.db.entity.MovieGist;
import io.pagarusha.traileraddict.db.entity.Person;
import io.pagarusha.traileraddict.db.entity.PersonGist;
import io.pagarusha.traileraddict.repository.model.TrendingMovieResult;
import io.pagarusha.traileraddict.repository.MovieApiRepository;
import io.pagarusha.traileraddict.repository.MovieDbRepository;
import io.pagarusha.traileraddict.repository.model.TrendingPeopleResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends AndroidViewModel {

    private MovieDbRepository mDbRepo;
    private MovieApiRepository mMovieApiRepo;
    private LiveData<List<MovieGist>> mPopularMovies;
    private LiveData<List<PersonGist>> mPopularPeople;
    private LiveData<Movie> mMovie;
    private LiveData<Person> mPerson;
    private MutableLiveData<String> error = new MutableLiveData<>();
    private Executor mExecutor = Executors.newSingleThreadExecutor();

    public MovieViewModel(@NonNull Application application) {
        super(application);
        mDbRepo = new MovieDbRepository(application);
        mMovieApiRepo = new MovieApiRepository();
        mPopularMovies = mDbRepo.getPopularMovies();
        mPopularPeople = mDbRepo.getPopularPeople();
        loadPopularMovies();
        loadPopularPeople();
    }

    public void loadPopularMovies() {
        mMovieApiRepo
                .getPopularMovies()
                .enqueue(new Callback<TrendingMovieResult>() {
                    @Override
                    public void onResponse(Call<TrendingMovieResult> call, Response<TrendingMovieResult> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            TrendingMovieResult result = response.body();
                            insertMovieList(result.getResults()); // update DB
                            Log.e("onReponse: ", "onResponse: " + result.getPage());
                        } else {
                            error.setValue("API error: " + response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<TrendingMovieResult> call, Throwable t) {
                        error.setValue("API error " + t.getMessage());

                    }
                });
    }

    public void loadPopularPeople() {
        mMovieApiRepo
                .getPopularPeople()
                .enqueue(new Callback<TrendingPeopleResult>() {
                    @Override
                    public void onResponse(Call<TrendingPeopleResult> call, Response<TrendingPeopleResult> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            TrendingPeopleResult result = response.body();
                            insertPersonList(result.getResults()); // update DB
                        } else {
                            error.setValue("API error: " + response.message());
                        }

                    }

                    @Override
                    public void onFailure(Call<TrendingPeopleResult> call, Throwable t) {
                        error.setValue("API error " + t.getMessage());
                    }
                });
    }

    private void getMovie(int id) {
        mMovieApiRepo
                .getMovie(id)
                .enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Movie result = response.body();
                            insertMovie(result); // update DB
                        } else {
                            error.setValue("API error: " + response.message());
                        }

                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {
                        error.setValue("API error " + t.getMessage());
                    }
                });
    }

    private void getPerson(int id) {
        mMovieApiRepo
                .getPerson(id)
                .enqueue(new Callback<Person>() {
                    @Override
                    public void onResponse(Call<Person> call, Response<Person> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Person result = response.body();
                            insertPerson(result); // update DB
                        } else {
                            error.setValue("API error: " + response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<Person> call, Throwable t) {
                        error.setValue("API error: " + t.getMessage());
                    }
                });
    }

    private void insertPersonList(List<PersonGist> results) {
        mDbRepo.insertPersonGistList(results);
    }

    public void insertMovieList(List<MovieGist> movies) {
        mDbRepo.insertMovieList(movies);

    }

    public void insertMovie(Movie movie) {
        mDbRepo.insertMovie(movie);
    }

    public void insertPerson(Person person) {
        mDbRepo.insertPerson(person);
    }

    public LiveData<Movie> getMovieById(final int id) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                getMovie(id);
            }
        });

        return mDbRepo.loadMovie(id);
    }

    public LiveData<Person> getPersonById(int id) {
        getPerson(id);
        return mDbRepo.loadPerson(id);
    }

    public LiveData<List<MovieGist>> getPopularMovies() {
        return mPopularMovies;
    }

    public LiveData<List<PersonGist>> getPopularPeople() {
        return mPopularPeople;
    }


}

package io.pagarusha.movieapp.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import io.pagarusha.movieapp.model.DiscoverResult;
import io.pagarusha.movieapp.model.Movie;
import io.pagarusha.movieapp.model.MovieRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends AndroidViewModel {

    private MovieRepository movieRepository = new MovieRepository();

    private MutableLiveData<List<Movie>> movies = new MutableLiveData<>();
    private MutableLiveData<Movie> movie = new MutableLiveData<>();
    private MutableLiveData<String> error = new MutableLiveData<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<Movie>> getMovies() {
        return movies;
    }

    public MutableLiveData<Movie> getMovie() { return movie; }

    public MutableLiveData<String> getError() {
        return error;
    }

    public void getMovieListByYear(int year) {
        movieRepository
                .getMovieListByYear(year)
                .enqueue(new Callback<DiscoverResult>() {
                    @Override
                    public void onResponse(Call<DiscoverResult> call, Response<DiscoverResult> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            DiscoverResult results = response.body();
                            movies.setValue(results.getResults());

                        } else {
                            error.setValue("API error: " + response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<DiscoverResult> call, Throwable t) {
                        error.setValue("API error " + t.getMessage());
                    }
                });
    }

    public void getMovieById(int id) {
        movieRepository
                .getMovieById(id)
                .enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Movie result = response.body();
                            movie.setValue(result);
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
}

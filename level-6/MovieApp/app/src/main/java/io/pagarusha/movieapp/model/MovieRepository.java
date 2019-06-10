package io.pagarusha.movieapp.model;

import io.pagarusha.movieapp.service.MovieApiService;
import retrofit2.Call;

/**
 * Repository for Movie Service API.
 */
public class MovieRepository {

    private MovieApiService movieApiService = MovieApi.create();

    public Call<DiscoverResult> getMovieListByYear(int year) {

        return movieApiService.getMovieListByYear(year);
    }

    public Call<Movie> getMovieById(int id) {

        return movieApiService.getMovieById(id);
    }
}

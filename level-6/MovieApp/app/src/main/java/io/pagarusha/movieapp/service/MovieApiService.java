package io.pagarusha.movieapp.service;

import io.pagarusha.movieapp.BuildConfig;
import io.pagarusha.movieapp.model.DiscoverResult;
import io.pagarusha.movieapp.model.Movie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Interface for Movie API endpoints
 */
public interface MovieApiService {

    // Get all movies for a given year
    @GET("discover/movie?api_key=" + BuildConfig.api_key + "&language=en-US")
    Call<DiscoverResult> getMovieListByYear(@Query("primary_release_year") int year);

    // Get movie by id
    @GET("movie/{id}?api_key=" + BuildConfig.api_key)
    Call<Movie> getMovieById(@Path("id") int id);
}

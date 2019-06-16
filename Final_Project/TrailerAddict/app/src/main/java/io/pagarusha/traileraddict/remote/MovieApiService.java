package io.pagarusha.traileraddict.remote;

import io.pagarusha.traileraddict.BuildConfig;
import io.pagarusha.traileraddict.db.entity.Movie;
import io.pagarusha.traileraddict.db.entity.Person;
import io.pagarusha.traileraddict.repository.model.TrendingMovieResult;
import io.pagarusha.traileraddict.repository.model.TrendingPeopleResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Interface for Movie API endpoints
 */
public interface MovieApiService {

    // Get all movies for a given year
    @GET("trending/movie/week?api_key=" + BuildConfig.api_key)
    Call<TrendingMovieResult> getPopularMovies();

    @GET("trending/person/week?api_key=" + BuildConfig.api_key)
    Call<TrendingPeopleResult> getPopularPeople();

    // Get movie by id
    @GET("movie/{id}?api_key=" + BuildConfig.api_key)
    Call<Movie> getMovieById(@Path("id") int id);

    // Get actor or crew
    @GET("person/{id}?api_key=" + BuildConfig.api_key)
    Call<Person> getPersonById(@Path("id") int id);

}


package io.pagarusha.traileraddict.repository;

import io.pagarusha.traileraddict.db.entity.Movie;
import io.pagarusha.traileraddict.db.entity.Person;
import io.pagarusha.traileraddict.remote.MovieApi;
import io.pagarusha.traileraddict.remote.MovieApiService;
import io.pagarusha.traileraddict.repository.model.TrendingMovieResult;
import io.pagarusha.traileraddict.repository.model.TrendingPeopleResult;
import retrofit2.Call;

public class MovieApiRepository {

    private MovieApiService movieApiService = MovieApi.create();

    public Call<TrendingMovieResult> getPopularMovies() {

        return movieApiService.getPopularMovies();
    }

    public Call<TrendingPeopleResult> getPopularPeople() {

        return movieApiService.getPopularPeople();
    }

    public Call<Movie> getMovie(final int id) {
        return movieApiService.getMovieById(id);
    }

    public Call<Person> getPerson(int id) {

        return movieApiService.getPersonById(id);
    }
}

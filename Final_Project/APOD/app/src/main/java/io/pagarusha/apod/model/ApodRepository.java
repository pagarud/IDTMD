package io.pagarusha.apod.model;

import android.graphics.Movie;

import io.pagarusha.apod.service.ApodApiService;
import retrofit2.Call;

public class ApodRepository {
    private ApodApiService apodApiService = ApodApi.create();

    public Call<Apod> getApodByDate(String date) {

        return apodApiService.getApodByDate(date);
    }

//    public Call<Movie> getMovieById(int id) {
//
//        return movieApiService.getMovieById(id);
//    }
}

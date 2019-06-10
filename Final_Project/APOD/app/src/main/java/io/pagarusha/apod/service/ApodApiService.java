package io.pagarusha.apod.service;

import io.pagarusha.apod.BuildConfig;
import io.pagarusha.apod.model.Apod;
import io.pagarusha.apod.model.ApodApi;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApodApiService {

    // Get an APOD by date
    @GET(ApodApi.BASE_URL + "apod?api_key=" + BuildConfig.api_key)
    Call<Apod> getApodByDate(@Query("date") String date);

    // Get movie by id
//    @GET("movie/{id}?api_key=" + BuildConfig.api_key)
//    Call<Apod> getMovieById(@Path("id") int id);


    // TODO: get list of apods in date range
}

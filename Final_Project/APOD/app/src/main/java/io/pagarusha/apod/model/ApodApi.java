package io.pagarusha.apod.model;

import io.pagarusha.apod.BuildConfig;
import io.pagarusha.apod.service.ApodApiService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApodApi {

    public static final String BASE_URL = "https://api.nasa.gov/planetary/";

    public static ApodApiService create() {

        // Create an OkHttpClient to be able to make a log of the network traffic
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(
                        new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                )
                .build();

        // Create the retrofit instance
        Retrofit apodApi = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Return the retrofit MovieApi
        return apodApi.create(ApodApiService.class);
    }
}

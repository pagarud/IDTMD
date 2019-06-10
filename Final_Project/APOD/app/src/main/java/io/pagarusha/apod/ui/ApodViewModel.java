package io.pagarusha.apod.ui;

import android.app.Application;
import android.graphics.Movie;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.pagarusha.apod.model.Apod;
import io.pagarusha.apod.model.ApodRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApodViewModel extends AndroidViewModel {

    private ApodRepository apodRepository = new ApodRepository();

    private MutableLiveData<List<Apod>> galleryApods = new MutableLiveData<>();
    private MutableLiveData<List<Apod>> favoriteApods = new MutableLiveData<>();
    private MutableLiveData<Apod> apod = new MutableLiveData<>();
    private MutableLiveData<String> error = new MutableLiveData<>();

    public ApodViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<Apod>> getGalleryApods() {
        return galleryApods;
    }

    public MutableLiveData<List<Apod>> getFavoriteApods() {
        return favoriteApods;
    }

    public MutableLiveData<Apod> getApod() {
        return apod;
    }

    public MutableLiveData<String> getError() {
        return error;
    }

    // TODO: set galleryApods

    // TODO: set favorite apods

    // TODO: set single apod
    public void getApodByDate(String date) {
        apodRepository
                .getApodByDate(date)
                .enqueue(new Callback<Apod>() {
                    @Override
                    public void onResponse(Call<Apod> call, Response<Apod> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Apod result = response.body();
                            apod.setValue(result);
                            Log.d("RESPONSE BODY", "onResponse: " + result);
                        } else {
                            error.setValue("API error: " + response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<Apod> call, Throwable t) {
                        error.setValue("API error " + t.getMessage());
                    }
                });
    }
}

package io.pagarusha.traileraddict.db.entity.helpers;

import androidx.room.TypeConverters;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.pagarusha.traileraddict.db.converter.ResultConverter;
import io.pagarusha.traileraddict.db.entity.helpers.Result;

public class Videos {

    @SerializedName("results")
    @Expose
    @TypeConverters(ResultConverter.class)
    private List<Result> results = null;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

}
package io.pagarusha.traileraddict.db.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import io.pagarusha.traileraddict.db.entity.helpers.Result;

public class ResultConverter {
    private static Gson gson = new Gson();
    @TypeConverter
    public static List<Result> stringToList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Result>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String ListToString(List<Result> someObjects) {
        return gson.toJson(someObjects);
    }
}

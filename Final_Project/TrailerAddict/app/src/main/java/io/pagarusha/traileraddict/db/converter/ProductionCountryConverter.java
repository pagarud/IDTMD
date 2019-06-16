package io.pagarusha.traileraddict.db.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import io.pagarusha.traileraddict.db.entity.helpers.ProductionCountry;

public class ProductionCountryConverter {
    private static Gson gson = new Gson();
    @TypeConverter
    public static List<ProductionCountry> stringToList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<ProductionCountry>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String ListToString(List<ProductionCountry> someObjects) {
        return gson.toJson(someObjects);
    }
}

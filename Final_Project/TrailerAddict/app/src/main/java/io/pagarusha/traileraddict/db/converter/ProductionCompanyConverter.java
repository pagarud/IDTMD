package io.pagarusha.traileraddict.db.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import io.pagarusha.traileraddict.db.entity.helpers.ProductionCompany;

public class ProductionCompanyConverter {
    private static Gson gson = new Gson();
    @TypeConverter
    public static List<ProductionCompany> stringToList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<ProductionCompany>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String ListToString(List<ProductionCompany> someObjects) {
        return gson.toJson(someObjects);
    }
}

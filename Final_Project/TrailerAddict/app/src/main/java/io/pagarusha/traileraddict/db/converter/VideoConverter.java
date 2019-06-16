package io.pagarusha.traileraddict.db.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import io.pagarusha.traileraddict.db.entity.helpers.Videos;

public class VideoConverter {
    private static Gson gson = new Gson();
    @TypeConverter
    public static List<Videos> stringToList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Videos>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String ListToString(List<Videos> someObjects) {
        return gson.toJson(someObjects);
    }
}

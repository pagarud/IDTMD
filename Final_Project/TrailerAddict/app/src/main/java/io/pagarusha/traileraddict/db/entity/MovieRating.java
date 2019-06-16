package io.pagarusha.traileraddict.db.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "rating_table")
public class MovieRating {

    public Integer getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(Integer movie_id) {
        this.movie_id = movie_id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @PrimaryKey
    private Integer movie_id;

    @ColumnInfo(name = "rating")
    private Integer rating;
}

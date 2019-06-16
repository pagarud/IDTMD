package io.pagarusha.traileraddict.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.pagarusha.traileraddict.db.converter.MovieGistConverter;

@Entity(tableName = "persongist_table")
public class PersonGist {

    @SerializedName("id")
    @Expose
    @NonNull
    @PrimaryKey
    private Integer id;
    @SerializedName("adult")
    @Expose
    private Boolean adult;
    @SerializedName("gender")
    @Expose
    private Integer gender;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("known_for")
    @Expose
    @TypeConverters(MovieGistConverter.class)
    private List<MovieGist> knownFor = null;
    @SerializedName("known_for_department")
    @Expose
    private String knownForDepartment;
    @SerializedName("profile_path")
    @Expose
    private String profilePath;
    @SerializedName("popularity")
    @Expose
    private Double popularity;

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<MovieGist> getKnownFor() {
        return knownFor;
    }

    public void setKnownFor(List<MovieGist> knownFor) {
        this.knownFor = knownFor;
    }

    public String getKnownForDepartment() {
        return knownForDepartment;
    }

    public void setKnownForDepartment(String knownForDepartment) {
        this.knownForDepartment = knownForDepartment;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }
}

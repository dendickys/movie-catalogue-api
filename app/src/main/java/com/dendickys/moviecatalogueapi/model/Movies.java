package com.dendickys.moviecatalogueapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Movies implements Parcelable {
    @SerializedName("id")
    private int id;
    @SerializedName("poster_path")
    private String poster_path;
    @SerializedName("title")
    private String title;
    @SerializedName("release_date")
    private String release_date;
    @SerializedName("overview")
    private String overview;

    public Movies(int id, String poster_path, String title, String release_date, String overview) {
        this.id = id;
        this.poster_path = poster_path;
        this.title = title;
        this.release_date = release_date;
        this.overview = overview;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.poster_path);
        dest.writeString(this.title);
        dest.writeString(this.release_date);
        dest.writeString(this.overview);
    }

    public Movies() {
    }

    protected Movies(Parcel in) {
        this.id = in.readInt();
        this.poster_path = in.readString();
        this.title = in.readString();
        this.release_date = in.readString();
        this.overview = in.readString();
    }

    public static final Creator<Movies> CREATOR = new Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel source) {
            return new Movies(source);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };
}

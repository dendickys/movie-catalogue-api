package com.dendickys.moviecatalogueapi.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movies implements Parcelable {
    private int poster;
    private String name;
    private String year;
    private String genre;
    private String description;

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.poster);
        dest.writeString(this.name);
        dest.writeString(this.year);
        dest.writeString(this.genre);
        dest.writeString(this.description);
    }

    public Movies() {
    }

    protected Movies(Parcel in) {
        this.poster = in.readInt();
        this.name = in.readString();
        this.year = in.readString();
        this.genre = in.readString();
        this.description = in.readString();
    }

    public static final Parcelable.Creator<Movies> CREATOR = new Parcelable.Creator<Movies>() {
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

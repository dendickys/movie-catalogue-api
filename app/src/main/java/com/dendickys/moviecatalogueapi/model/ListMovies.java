package com.dendickys.moviecatalogueapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ListMovies {
    @SerializedName("results")
    private ArrayList<Movies> listMovies;

    public ArrayList<Movies> getListMovies() {
        return listMovies;
    }

    public void setListMovies(ArrayList<Movies> listMovies) {
        this.listMovies = listMovies;
    }
}

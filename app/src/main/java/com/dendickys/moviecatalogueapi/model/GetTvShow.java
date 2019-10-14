package com.dendickys.moviecatalogueapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GetTvShow {
    @SerializedName("results")
    private ArrayList<TvShow> listTvShow;

    public ArrayList<TvShow> getListTvShow() {
        return listTvShow;
    }

    public void setListTvShow(ArrayList<TvShow> listTvShow) {
        this.listTvShow = listTvShow;
    }
}

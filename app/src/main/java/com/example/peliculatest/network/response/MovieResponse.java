package com.example.peliculatest.network.response;

import com.example.peliculatest.network.dto.MovieDto;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {

    @SerializedName("results")
    private List<MovieDto> result;

    public MovieResponse(List<MovieDto> result) {
        this.result = result;
    }

    public List<MovieDto> getResult() {
        return result;
    }

    public void setResult(List<MovieDto> result) {
        this.result = result;
    }
}

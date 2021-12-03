package com.example.peliculatest.network.apiService;


import com.example.peliculatest.network.response.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApi {
    String TOP_RATED = "movie/top_rated";

    @GET(TOP_RATED)
    Call<MovieResponse> getMovies();
}

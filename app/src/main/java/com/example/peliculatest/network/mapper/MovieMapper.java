package com.example.peliculatest.network.mapper;


import com.example.peliculatest.model.Movie;
import com.example.peliculatest.network.dto.MovieDto;
import com.example.peliculatest.utilities.DateUtil;

import java.util.Calendar;
import java.util.Date;

public class MovieMapper {

    public static MovieDto objectToDto(Movie movie) {
        return new MovieDto();
    }

    public static Movie dtoToObject(MovieDto dto) {
        Date releaseDate = DateUtil.parseSimpleDate(dto.getReleaseDate());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(releaseDate == null ? new Date(0) : releaseDate);

        return new Movie(dto.getId(), dto.getTitle(), dto.getOverview(), calendar.getTimeInMillis(), dto.getPosterPath(), dto.getVoteAverage());
    }

}

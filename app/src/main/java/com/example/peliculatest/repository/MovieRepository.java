package com.example.peliculatest.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.peliculatest.database.Database;
import com.example.peliculatest.database.dao.MovieDao;
import com.example.peliculatest.model.Movie;
import com.example.peliculatest.network.dto.MovieDto;
import com.example.peliculatest.network.mapper.MovieMapper;
import com.example.peliculatest.network.response.MovieResponse;
import com.example.peliculatest.network.service.MovieService;
import com.example.peliculatest.utilities.NetworkUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public class MovieRepository {

    Database db;
    private MovieDao movieDao;
    private MovieService service;
    private MutableLiveData<List<Movie>> moviesLive;
    private MutableLiveData<List<Movie>> favoriteLive;
    private MutableLiveData<Movie> movieLive;


    public MovieRepository(Context context) {
        db = Database.getDatabase(context);
        movieDao = db.movieDao();
        service = new MovieService();
        moviesLive = new MutableLiveData<>();
        favoriteLive = new MutableLiveData<>();
        movieLive = new MutableLiveData<>();
    }

    public LiveData<List<Movie>> getMovies(Context context) {
        if (NetworkUtil.isConnected(context)) {
            callService();
            moviesLive.setValue(new ArrayList<>());
        } else {
            getMovies();
        }
        return moviesLive;
    }

    private void callService() {
        service.getMovies(new MovieService.OnGet() {
            @Override
            public void onResponse(MovieResponse response) {
                List<Movie> movies = new ArrayList<>();

                if (response != null && response.getResult() != null) {
                    for (MovieDto movieDto : response.getResult()) {
                        movies.add(MovieMapper.dtoToObject(movieDto));
                    }
                    insert(new ArrayList<>(movies));
                    moviesLive.setValue(movies);
                }
            }

            @Override
            public void onFailure(Object error) {
                moviesLive.setValue(new ArrayList<>());

            }
        });
    }

    public void insert(Movie movie) {
        Database.databaseWriteExecutor.execute(() -> {
            movieDao.insert(movie);
        });
    }

    public void insert(ArrayList<Movie> movies) {
        Database.databaseWriteExecutor.execute(() -> {
            movieDao.insertAll(movies);
        });
    }

    public void deleteAll(Movie... movies) {
        Database.databaseWriteExecutor.execute(() -> {
            movieDao.deleteAll(movies);
        });
    }

    public void deleteAll() {
        Database.databaseWriteExecutor.execute(() -> {
            movieDao.deleteAll();
        });
    }

    public void getMovies() {
        Database.databaseWriteExecutor.execute(() -> {
            moviesLive.postValue(movieDao.getAllMovies());
        });
    }

    public LiveData<List<Movie>> getFavoriteMovies() {
        Database.databaseWriteExecutor.execute(() -> {
            favoriteLive.postValue(movieDao.getFavoriteMovies());
        });
        return favoriteLive;
    }

    public LiveData<Movie> getMovie(Integer movieId)  {
        Database.databaseWriteExecutor.execute(() -> {
            movieLive.postValue(movieDao.getMovie(movieId));
        });
        return movieLive;
    }
}

package com.example.peliculatest.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.peliculatest.model.Movie;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Movie movie);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ArrayList<Movie> movie);

    @Query("DELETE FROM movies")
    void deleteAll();

    @Delete
    void deleteAll(Movie... movies);

    @Query("SELECT * from movies ORDER BY id DESC")
    List<Movie> getAllMovies();

    @Query("SELECT * FROM movies WHERE favorite = 1 ORDER BY id DESC")
    List<Movie> getFavoriteMovies();

    @Query("SELECT * from movies WHERE id = :movieId")
    Movie getMovie(Integer movieId);
}
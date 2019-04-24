package com.borschevskydenis.movieshelper.DB;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.borschevskydenis.movieshelper.ResultsFromServer.MovieById;
import com.borschevskydenis.movieshelper.ResultsFromServer.MovieSearch;

import java.util.List;

//import androidx.lifecycle.LiveData;
//import androidx.room.Delete;
//import androidx.room.Insert;
//import androidx.room.Query;

@Dao
public interface FavoritesMoviesDao {
    @Query("SELECT * FROM FavoritesMovies")
    LiveData<List<MovieById>> getAllMovies();

    @Query("SELECT * FROM FavoritesMovies WHERE id == :id")
    MovieById getMovieById(int id);

    @Query("DELETE FROM FavoritesMovies")
    void deleteAllFavoritesMovies();

    @Insert
    void insertMovie(MovieById movie);

    @Delete
    void deleteFavoriteMovie(MovieById movie);

}

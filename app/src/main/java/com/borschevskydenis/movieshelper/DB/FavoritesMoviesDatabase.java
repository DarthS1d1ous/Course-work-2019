package com.borschevskydenis.movieshelper.DB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.borschevskydenis.movieshelper.ResultsFromServer.MovieById;
import com.borschevskydenis.movieshelper.ResultsFromServer.MovieSearch;

//import androidx.room.Database;
//import androidx.room.Room;
//import androidx.room.RoomDatabase;

@Database(entities = {MovieById.class}, version = 1,exportSchema = false)
public abstract class FavoritesMoviesDatabase extends RoomDatabase {
    public static final String DB_NAME="FavoritesMovies.db";
    private static FavoritesMoviesDatabase database;
    private static final Object LOCK = new Object();

    public static FavoritesMoviesDatabase getInstance(Context context){
        synchronized (LOCK) {
            if (database == null) {
                database = Room.databaseBuilder(context,
                        FavoritesMoviesDatabase.class, DB_NAME).build();
            }
        }
        return database;

    }

    public abstract FavoritesMoviesDao favoritesMoviesDao();
}


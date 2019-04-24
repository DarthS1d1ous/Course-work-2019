package com.borschevskydenis.movieshelper.DB;

import android.app.Application;

//import androidx.lifecycle.AndroidViewModel;
//import androidx.lifecycle.LiveData;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.borschevskydenis.movieshelper.ResultsFromServer.MovieById;
import com.borschevskydenis.movieshelper.ResultsFromServer.MovieSearch;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainViewModel extends AndroidViewModel {

    private static FavoritesMoviesDatabase database;
    private LiveData<List<MovieById>> movies;

    public LiveData<List<MovieById>> getAllMovies() {
        return movies;
    }

    public MainViewModel(@NonNull Application application) {
        super(application);
        database= FavoritesMoviesDatabase.getInstance(getApplication());
        movies = database.favoritesMoviesDao().getAllMovies();
    }

    public MovieById getMovieById(int id){
        try {
            return new GetMovieTask().execute(id).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteAllFavoritesMovies(){
        new DeleteAllMoviesTask().execute();
    }

    public void insertFavoriteMovie(MovieById movie){
        new InsertMovieTask().execute(movie);
    }

    public void deleteFavoriteMovie(MovieById movie){
        new DeleteMovieTask().execute(movie);
    }

    private static class DeleteMovieTask extends AsyncTask<MovieById,Void, Void>{
        @Override
        protected Void doInBackground(MovieById... resultsBeans) {
            if(resultsBeans != null && resultsBeans.length>0){
                database.favoritesMoviesDao().deleteFavoriteMovie(resultsBeans[0]);
            }
            return null;
        }
    }

    private static class InsertMovieTask extends AsyncTask<MovieById,Void, Void>{
        @Override
        protected Void doInBackground(MovieById... resultsBeans) {
            if(resultsBeans != null && resultsBeans.length>0){
                database.favoritesMoviesDao().insertMovie(resultsBeans[0]);
            }
            return null;
        }
    }

    private static class DeleteAllMoviesTask extends AsyncTask<Void,Void, Void>{
        @Override
        protected Void doInBackground(Void... integers) {
            if(integers != null && integers.length>0){
                database.favoritesMoviesDao().deleteAllFavoritesMovies();
            }
            return null;
        }
    }

    private static class GetMovieTask extends AsyncTask<Integer,Void, MovieById>{
        @Override
        protected MovieById doInBackground(Integer... integers) {
            if(integers != null && integers.length>0){
                return database.favoritesMoviesDao().getMovieById(integers[0]);
            }
            return null;
        }
    }

}

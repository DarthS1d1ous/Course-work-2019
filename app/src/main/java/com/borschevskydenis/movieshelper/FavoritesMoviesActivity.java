package com.borschevskydenis.movieshelper;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.borschevskydenis.movieshelper.Adapters.FavoritesMoviesAdapter;
import com.borschevskydenis.movieshelper.Adapters.MovieAdapter;
import com.borschevskydenis.movieshelper.DB.MainViewModel;
import com.borschevskydenis.movieshelper.ResultsFromServer.MovieById;

import java.util.List;

public class FavoritesMoviesActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private LiveData<List<MovieById>> favoritesMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites_movies);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        RecyclerView recyclerViewFavoritePosters = findViewById(R.id.recyclerViewFavoritePosters);
        final FavoritesMoviesAdapter movieAdapter = new FavoritesMoviesAdapter();
        recyclerViewFavoritePosters.setLayoutManager(new GridLayoutManager(this,3));

        favoritesMovies = viewModel.getAllMovies();
        favoritesMovies.observe(this, new Observer<List<MovieById>>() {
            @Override
            public void onChanged(@Nullable final List<MovieById> movieByIds) {
                movieAdapter.setMovies(movieByIds);
                movieAdapter.setOnPosterClickListener(new FavoritesMoviesAdapter.OnPosterClickListener() {
                    @Override
                    public void onPosterClick(int position) {
                        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                        int id = movieByIds.get(position).getId();
                        intent.putExtra(DetailActivity.RESULT_ID, id);
                        startActivity(intent);
                    }
                });
            }
        });
        recyclerViewFavoritePosters.setAdapter(movieAdapter);


        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}

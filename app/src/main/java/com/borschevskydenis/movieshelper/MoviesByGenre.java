package com.borschevskydenis.movieshelper;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.borschevskydenis.movieshelper.Adapters.CatalogMoviesAdapter;
import com.borschevskydenis.movieshelper.Adapters.FavoritesMoviesAdapter;
import com.borschevskydenis.movieshelper.Adapters.MovieAdapter;
import com.borschevskydenis.movieshelper.DB.MainViewModel;
import com.borschevskydenis.movieshelper.ResultsFromServer.MovieById;
import com.borschevskydenis.movieshelper.ResultsFromServer.MovieSearch;
import com.borschevskydenis.movieshelper.Utils.CommonUtils;
import com.borschevskydenis.movieshelper.Utils.RetrofitUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MoviesByGenre extends AppCompatActivity {

    public static final String RESULT_GENRE = "result_genre";
    public int PAGE = 1;
    private int genreId;
    private String genre;
    private ArrayList<MovieSearch.ResultsBean> listOfMovies;
    private MovieAdapter movieAdapter;
    private RecyclerView recyclerViewPosters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_by_genre);

        recyclerViewPosters = findViewById(R.id.recyclerViewPosters);
        movieAdapter = new MovieAdapter();
        recyclerViewPosters.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(DetailActivity.RESULT_ID)) {
            genreId = intent.getIntExtra(DetailActivity.RESULT_ID , -1);
            genre = intent.getStringExtra(RESULT_GENRE);
        } else {
            finish();
        }

        /**Кнопка назад */
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(String.format("Фильмы с жанром %s",genre));
        }

        getMovieByGenre();

        /** Добавление фильмов при прокрутке*/
        recyclerViewPosters.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(final RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!MovieAdapter.isLoading) {
                    if (!recyclerView.canScrollVertically(1)) {
                        MovieAdapter.isLoading = true;
                        recyclerView.stopNestedScroll();
                        PAGE++;
                        Retrofit retrofit = RetrofitUtils.createRetrofit();

                        ApiInterface myInterface = retrofit.create(ApiInterface.class);

                        Call<MovieSearch> call = myInterface.getDiscoverMovies(CommonUtils.CATEGORY_MOVIE , CommonUtils.API_KEY,CommonUtils.LANGUAGE_RU,CommonUtils.SORT_POPULARITY_DESC,CommonUtils.INCLUDE_ADULT_FALSE, PAGE, String.valueOf(genreId));

                        call.enqueue(new Callback<MovieSearch>() {
                            @Override
                            public void onResponse(final Call<MovieSearch> call, Response<MovieSearch> response) {
                                MovieSearch search = response.body();
                                if (search.getResults().size() != 0) {
                                    listOfMovies.addAll(search.getResults());
                                    movieAdapter.setMovies(listOfMovies);
                                    recyclerView.scrollToPosition(listOfMovies.size() - search.getResults().size() - 1);
                                    recyclerViewPosters.setAdapter(movieAdapter);
                                    movieAdapter.setOnPosterClickListener(new MovieAdapter.OnPosterClickListener() {
                                        @Override
                                        public void onPosterClick(int position) {
                                            Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                                            int id = listOfMovies.get(position).getId();
                                            intent.putExtra(DetailActivity.RESULT_ID, id);
                                            startActivity(intent);
                                        }
                                    });
                                }

                            }

                            @Override
                            public void onFailure(Call<MovieSearch> call, Throwable t) {
                                t.printStackTrace();
                            }
                        });

                    }
                }
            }
        });
    }

    public void getMovieByGenre() {

        PAGE = 1;

        Retrofit retrofit = RetrofitUtils.createRetrofit();

        ApiInterface myInterface = retrofit.create(ApiInterface.class);

        Call<MovieSearch> call = myInterface.getDiscoverMovies(CommonUtils.CATEGORY_MOVIE, CommonUtils.API_KEY, CommonUtils.LANGUAGE_RU,CommonUtils.SORT_POPULARITY_DESC,CommonUtils.INCLUDE_ADULT_FALSE, PAGE, String.valueOf(genreId));

        call.enqueue(new Callback<MovieSearch>() {
            @Override
            public void onResponse(final Call<MovieSearch> call, Response<MovieSearch> response) {
                MovieSearch search = response.body();
                listOfMovies =(ArrayList<MovieSearch.ResultsBean>) search.getResults();
                if (listOfMovies != null) {
                    movieAdapter.setMovies(listOfMovies);
                    recyclerViewPosters.setAdapter(movieAdapter);
                    movieAdapter.setOnPosterClickListener(new MovieAdapter.OnPosterClickListener() {
                        @Override
                        public void onPosterClick(int position) {
                            Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                            int id = listOfMovies.get(position).getId();
                            intent.putExtra(DetailActivity.RESULT_ID, id);
                            startActivity(intent);
                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<MovieSearch> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}

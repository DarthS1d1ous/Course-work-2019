package com.borschevskydenis.movieshelper;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.borschevskydenis.movieshelper.Adapters.MovieAdapter;
import com.borschevskydenis.movieshelper.ResultsFromServer.MovieSearch;
import com.borschevskydenis.movieshelper.Utils.CommonUtils;
import com.borschevskydenis.movieshelper.Utils.RetrofitUtils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FullMoviesActivity extends AppCompatActivity {

    public static final String RESULT_CATALOG = "result_catalog";
    private String category;
    private int PAGE = 1;
    private ArrayList<MovieSearch.ResultsBean> listOfMovies;
    private MovieAdapter movieAdapter;
    private RecyclerView recyclerViewPosters;
    private CollapsingToolbarLayout toolbarTitle;
    private NestedScrollView nestedScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_movies);

        /**Кнопка назад */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();// возврат на предыдущий activity
            }
        });

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(RESULT_CATALOG)) {
            category = intent.getStringExtra(RESULT_CATALOG);
        }
        nestedScrollView = findViewById(R.id.nestedScrollView);
        toolbarTitle = findViewById(R.id.collapsingToolbar);
        recyclerViewPosters = findViewById(R.id.recyclerViewPosters);
        movieAdapter = new MovieAdapter();
        recyclerViewPosters.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));

        /** Заголовок activity*/
        setActivityTitle(category);

        /** Получаем список фильмов по категории */
        getMoviesByCategory();

        /** Добавление фильмов при прокрутке*/
        recyclerViewPosters.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(final RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!MovieAdapter.isLoading) {
                    if (!recyclerView.canScrollVertically(1) && !nestedScrollView.canScrollVertically(1)) {
                        MovieAdapter.isLoading = true;
                        recyclerView.stopNestedScroll();
                        //                    Toast.makeText(FullMoviesActivity.this, "AAAAAAAAAAAAAA", Toast.LENGTH_SHORT).show();
                        PAGE++;
                        Retrofit retrofit = RetrofitUtils.createRetrofit();

                        ApiInterface myInterface = retrofit.create(ApiInterface.class);

                        Call<MovieSearch> call = myInterface.getMoviesByCategory(category, CommonUtils.API_KEY, CommonUtils.LANGUAGE_RU, PAGE, CommonUtils.REGION_RU);

                        call.enqueue(new Callback<MovieSearch>() {
                            @Override
                            public void onResponse(Call<MovieSearch> call, Response<MovieSearch> response) {
                                MovieSearch search = response.body();
                                if (search.getResults().size() != 0) {
                                    listOfMovies.addAll(search.getResults());
                                    movieAdapter.setMovies(listOfMovies);
                                    recyclerView.scrollToPosition(listOfMovies.size() - search.getResults().size() - 2);
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

    /**
     * Получаем список фильмов по категории
     */
    public void getMoviesByCategory() {
        PAGE = 1;

        Retrofit retrofit = RetrofitUtils.createRetrofit();

        ApiInterface myInterface = retrofit.create(ApiInterface.class);

        Call<MovieSearch> call = myInterface.getMoviesByCategory(category, CommonUtils.API_KEY, CommonUtils.LANGUAGE_RU, PAGE, CommonUtils.REGION_RU);

        call.enqueue(new Callback<MovieSearch>() {
            @Override
            public void onResponse(Call<MovieSearch> call, Response<MovieSearch> response) {
                MovieSearch search = response.body();
                listOfMovies = (ArrayList<MovieSearch.ResultsBean>) search.getResults();
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
                } else {
//                    tvNotFound.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<MovieSearch> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void setActivityTitle(String category) {
        switch (category) {
            case CommonUtils.CATEGORY_NOW_PLAYING:
                toolbarTitle.setTitle("Новые фильмы");
                break;
            case CommonUtils.CATEGORY_POPULAR:
                toolbarTitle.setTitle("Популярные фильмы");
                break;
            case CommonUtils.CATEGORY_TOP_RATED:
                toolbarTitle.setTitle("Фильмы с лучшим рейтингом");
                break;
        }
    }
}

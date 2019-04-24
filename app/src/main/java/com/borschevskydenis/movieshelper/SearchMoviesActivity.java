package com.borschevskydenis.movieshelper;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.borschevskydenis.movieshelper.Adapters.MovieAdapter;
import com.borschevskydenis.movieshelper.MainTabsLayout.SearchFragment;
import com.borschevskydenis.movieshelper.ResultsFromServer.MovieSearch;
import com.borschevskydenis.movieshelper.Utils.CommonUtils;
import com.borschevskydenis.movieshelper.Utils.RetrofitUtils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchMoviesActivity extends AppCompatActivity {

    public static final String RESULTS_BEAN = "results_bean";
    public int PAGE = 1;
    public static String QUERY = SearchFragment.QUERY;
    public ArrayList<MovieSearch.ResultsBean> listOfMovies;
    private MovieAdapter movieAdapter;
    private RecyclerView recyclerViewPosters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movies);
        recyclerViewPosters = findViewById(R.id.recyclerViewPosters);
        movieAdapter = new MovieAdapter();
        recyclerViewPosters.setLayoutManager(new GridLayoutManager(this, 3));
//        movieAdapter.setHasStableIds(false);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        Intent intent = getIntent();
        if( intent!=null && intent.hasExtra(RESULTS_BEAN) ) {
//            QUERY = intent.getStringExtra(RESULTS_BEAN);
//
//            Retrofit retrofit = RetrofitUtils.createRetrofit();
//
//            ApiInterface myInterface = retrofit.create(ApiInterface.class);
//
//            Call<MovieSearch> call = myInterface.searchMovies(CommonUtils.CATEGORY_MOVIE, CommonUtils.API_KEY, CommonUtils.LANGUAGE_RU, QUERY, PAGE, CommonUtils.INCLUDE_ADULT_FALSE);
//
//            call.enqueue(new Callback<MovieSearch>() {
//                @Override
//                public void onResponse(Call<MovieSearch> call, Response<MovieSearch> response) {
//                    MovieSearch search = response.body();
//                    listOfMovies = (ArrayList<MovieSearch.ResultsBean>) search.getResults();
//                    movieAdapter.setMovies(listOfMovies);
//                    recyclerViewPosters.setAdapter(movieAdapter);
//                    movieAdapter.setOnPosterClickListener(new MovieAdapter.OnPosterClickListener() {
//                        @Override
//                        public void onPosterClick(int position) {
//                            Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
//                            int id = listOfMovies.get(position).getId();
//                            intent.putExtra(DetailActivity.RESULT_ID, id);
//                            startActivity(intent);
//                        }
//                    });
//
//                }
//
////                    public void onResponse(Call<MovieSearch> call, Response<MovieSearch> response) {
////                        Intent intent = new Intent(getApplicationContext(), SearchMoviesActivity.class);
////                        MovieSearch search = response.body();
////                        List<MovieSearch.ResultsBean> listOfMovies = search.getResults();
////                        MovieSearch.ResultsBean rofl = listOfMovies.get(0);
////                        intent.putExtra(SearchMoviesActivity.RESULTS_BEAN, rofl);
////                        startActivity(intent);
////                    }
//
//                @Override
//                public void onFailure(Call<MovieSearch> call, Throwable t) {
//                    t.printStackTrace();
//                }
//            });
            listOfMovies = intent.getParcelableArrayListExtra(RESULTS_BEAN);
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
            finish();
        }

        //* Добавление фильмов при прокрутке*/
        recyclerViewPosters.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(final RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    PAGE++;
                    Retrofit retrofit = RetrofitUtils.createRetrofit();

                    ApiInterface myInterface = retrofit.create(ApiInterface.class);

                    Call<MovieSearch> call = myInterface.searchMovies(CommonUtils.CATEGORY_MOVIE, CommonUtils.API_KEY, CommonUtils.LANGUAGE_RU, QUERY, PAGE, CommonUtils.INCLUDE_ADULT_FALSE);

                    call.enqueue(new Callback<MovieSearch>() {
                        @Override
                        public void onResponse(Call<MovieSearch> call, Response<MovieSearch> response) {
                            MovieSearch search = response.body();
                            if(search.getResults().size()!=0) {
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
        });
    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}



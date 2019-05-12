package com.borschevskydenis.movieshelper.MainTabsLayout;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.borschevskydenis.movieshelper.Adapters.CatalogMoviesAdapter;
import com.borschevskydenis.movieshelper.Adapters.GenresAdapter;
import com.borschevskydenis.movieshelper.Adapters.MovieAdapter;
import com.borschevskydenis.movieshelper.Adapters.RecommendationsMoviesAdapter;
import com.borschevskydenis.movieshelper.ApiInterface;
import com.borschevskydenis.movieshelper.DetailActivity;
import com.borschevskydenis.movieshelper.FullMoviesActivity;
import com.borschevskydenis.movieshelper.MoviesByGenre;
import com.borschevskydenis.movieshelper.R;
import com.borschevskydenis.movieshelper.ResultsFromServer.Genres;
import com.borschevskydenis.movieshelper.ResultsFromServer.MovieSearch;
import com.borschevskydenis.movieshelper.Utils.CommonUtils;
import com.borschevskydenis.movieshelper.Utils.RetrofitUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class CatalogFragment extends Fragment {

    private ArrayList<MovieSearch.ResultsBean> listOfNewMovies;
    private ArrayList<MovieSearch.ResultsBean> listOfPopularMovies;
    private ArrayList<MovieSearch.ResultsBean> listOfTopRatedMovies;
    private ArrayList<Genres.GenresBean> listOfGenres;
    private final int PAGE = 1;
    private CatalogMoviesAdapter movieAdapter;
    private GenresAdapter genresAdapter;
    private RecyclerView recyclerViewNewMovies;
    private RecyclerView recyclerViewPopularMovies;
    private RecyclerView recyclerViewTopRatedMovies;
    private RecyclerView recyclerViewGenres;

    public CatalogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalog, container, false);

        recyclerViewNewMovies = view.findViewById(R.id.rvNewMovies);
        recyclerViewPopularMovies = view.findViewById(R.id.rvPopularMovies);
        recyclerViewTopRatedMovies = view.findViewById(R.id.rvTopRated);
        recyclerViewGenres = view.findViewById(R.id.rvGenres);

        recyclerViewNewMovies.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        recyclerViewPopularMovies.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        recyclerViewTopRatedMovies.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        recyclerViewGenres.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        getNewMovies();
        getPopularMovies();
        getTopRatedMovies();
        getGenres();


        return view;
    }

    public void getGenres(){
        Retrofit retrofit = RetrofitUtils.createRetrofit();

        ApiInterface myInterface = retrofit.create(ApiInterface.class);

        Call<Genres> call = myInterface.getGenres(CommonUtils.API_KEY, CommonUtils.LANGUAGE_RU);

        call.enqueue(new Callback<Genres>() {
            @Override
            public void onResponse(final Call<Genres> call, Response<Genres> response) {
                Genres search = response.body();
                listOfGenres =(ArrayList<Genres.GenresBean>) search.getGenres();
                if (listOfGenres != null) {
                    genresAdapter = new GenresAdapter();
                    genresAdapter.setGenres(listOfGenres);
                    recyclerViewGenres.setAdapter(genresAdapter);
                    genresAdapter.setOnButtonClickListener(new GenresAdapter.OnButtonClickListener() {
                        @Override
                        public void onButtonClick(int position) {
                            Intent intent = new Intent(getContext(), MoviesByGenre.class);
                            int genreId = listOfGenres.get(position).getId();
                            String genre = listOfGenres.get(position).getName();
                            intent.putExtra(DetailActivity.RESULT_ID, genreId);
                            intent.putExtra(MoviesByGenre.RESULT_GENRE, genre);
                            startActivity(intent);
                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<Genres> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getNewMovies() {

        Retrofit retrofit = RetrofitUtils.createRetrofit();

        ApiInterface myInterface = retrofit.create(ApiInterface.class);

        Call<MovieSearch> call = myInterface.getMoviesByCategory(CommonUtils.CATEGORY_NOW_PLAYING, CommonUtils.API_KEY, CommonUtils.LANGUAGE_RU, PAGE, CommonUtils.REGION_RU);

        call.enqueue(new Callback<MovieSearch>() {
            @Override
            public void onResponse(final Call<MovieSearch> call, Response<MovieSearch> response) {
                MovieSearch search = response.body();
                listOfNewMovies =(ArrayList<MovieSearch.ResultsBean>) search.getResults();
                if (listOfNewMovies != null) {
                    movieAdapter = new CatalogMoviesAdapter();
                    movieAdapter.setMovies(listOfNewMovies);
                    recyclerViewNewMovies.setAdapter(movieAdapter);
                    movieAdapter.setOnPosterClickListener(new CatalogMoviesAdapter.OnPosterClickListener() {
                        @Override
                        public void onPosterClick(int position) {
                            Intent intent = new Intent(getContext(), DetailActivity.class);
                            int id = listOfNewMovies.get(position).getId();
                            intent.putExtra(DetailActivity.RESULT_ID, id);
                            startActivity(intent);
                        }
                    });
                    movieAdapter.setOnButtonFullClickListener(new CatalogMoviesAdapter.OnButtonFullClickListener() {
                        @Override
                        public void onButtonFullClick() {
                            Intent intent = new Intent(getContext(), FullMoviesActivity.class);
                            intent.putExtra(FullMoviesActivity.RESULT_CATALOG,CommonUtils.CATEGORY_NOW_PLAYING);
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

    public void getPopularMovies() {

        Retrofit retrofit = RetrofitUtils.createRetrofit();

        ApiInterface myInterface = retrofit.create(ApiInterface.class);

        Call<MovieSearch> call = myInterface.getMoviesByCategory(CommonUtils.CATEGORY_POPULAR, CommonUtils.API_KEY, CommonUtils.LANGUAGE_RU, PAGE, CommonUtils.REGION_RU);

        call.enqueue(new Callback<MovieSearch>() {
            @Override
            public void onResponse(Call<MovieSearch> call, Response<MovieSearch> response) {
                MovieSearch search = response.body();
                listOfPopularMovies =(ArrayList<MovieSearch.ResultsBean>) search.getResults();
                if (listOfPopularMovies != null) {
                    movieAdapter = new CatalogMoviesAdapter();
                    movieAdapter.setMovies(listOfPopularMovies);
                    recyclerViewPopularMovies.setAdapter(movieAdapter);
                    movieAdapter.setOnPosterClickListener(new CatalogMoviesAdapter.OnPosterClickListener()  {
                        @Override
                        public void onPosterClick(int position) {
                            Intent intent = new Intent(getContext(), DetailActivity.class);
                            int id = listOfPopularMovies.get(position).getId();
                            intent.putExtra(DetailActivity.RESULT_ID, id);
                            startActivity(intent);
                        }
                    });
                    movieAdapter.setOnButtonFullClickListener(new CatalogMoviesAdapter.OnButtonFullClickListener() {
                        @Override
                        public void onButtonFullClick() {
                            Intent intent = new Intent(getContext(), FullMoviesActivity.class);
                            intent.putExtra(FullMoviesActivity.RESULT_CATALOG,CommonUtils.CATEGORY_POPULAR);
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

    public void getTopRatedMovies() {

        Retrofit retrofit = RetrofitUtils.createRetrofit();

        ApiInterface myInterface = retrofit.create(ApiInterface.class);

        Call<MovieSearch> call = myInterface.getMoviesByCategory(CommonUtils.CATEGORY_TOP_RATED, CommonUtils.API_KEY, CommonUtils.LANGUAGE_RU, PAGE, CommonUtils.REGION_RU);

        call.enqueue(new Callback<MovieSearch>() {
            @Override
            public void onResponse(Call<MovieSearch> call, Response<MovieSearch> response) {
                MovieSearch search = response.body();
                listOfTopRatedMovies =(ArrayList<MovieSearch.ResultsBean>) search.getResults();
                if (listOfTopRatedMovies != null) {
                    movieAdapter = new CatalogMoviesAdapter();
                    movieAdapter.setMovies(listOfTopRatedMovies);
                    recyclerViewTopRatedMovies.setAdapter(movieAdapter);
                    movieAdapter.setOnPosterClickListener(new CatalogMoviesAdapter.OnPosterClickListener()  {
                        @Override
                        public void onPosterClick(int position) {
                            Intent intent = new Intent(getContext(), DetailActivity.class);
                            int id = listOfTopRatedMovies.get(position).getId();
                            intent.putExtra(DetailActivity.RESULT_ID, id);
                            startActivity(intent);
                        }
                    });
                    movieAdapter.setOnButtonFullClickListener(new CatalogMoviesAdapter.OnButtonFullClickListener() {
                        @Override
                        public void onButtonFullClick() {
                            Intent intent = new Intent(getContext(), FullMoviesActivity.class);
                            intent.putExtra(FullMoviesActivity.RESULT_CATALOG,CommonUtils.CATEGORY_TOP_RATED);
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

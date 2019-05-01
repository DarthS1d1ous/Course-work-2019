package com.borschevskydenis.movieshelper.MainTabsLayout;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.borschevskydenis.movieshelper.Adapters.MovieAdapter;
import com.borschevskydenis.movieshelper.ApiInterface;
import com.borschevskydenis.movieshelper.DetailActivity;
import com.borschevskydenis.movieshelper.R;
import com.borschevskydenis.movieshelper.ResultsFromServer.MovieSearch;
import com.borschevskydenis.movieshelper.Utils.CommonUtils;
import com.borschevskydenis.movieshelper.Utils.RetrofitUtils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    public int PAGE = 1;
    public ArrayList<MovieSearch.ResultsBean> listOfMovies;
    private MovieAdapter movieAdapter;
    private RecyclerView recyclerViewPosters;

    private Button btn_clear;
    private EditText etSearch;
    private TextView tvNotFound;

    public static String QUERY;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_search, container, false);

        btn_clear = view.findViewById(R.id.btn_clear);
        etSearch = view.findViewById(R.id.etSearch);
        tvNotFound = view.findViewById(R.id.tvNotFound);

        tvNotFound.setVisibility(View.INVISIBLE);

        recyclerViewPosters = view.findViewById(R.id.recyclerViewPosters);
        movieAdapter = new MovieAdapter();

        etSearch.setImeOptions(EditorInfo.IME_ACTION_SEARCH);

        etSearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!etSearch.getText().toString().equals("")) {
                    btn_clear.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });

        /** Добавление кнопки поиска на клавиатуре */
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (!etSearch.getText().toString().equals("")) {
                        performSearch();
                    }
                    etSearch.setImeOptions(EditorInfo.IME_ACTION_DONE);
                    InputMethodManager inputManager =
                            (InputMethodManager) view.getContext().
                                    getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(view.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    etSearch.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
                    view.clearFocus();
                    btn_clear.setVisibility(View.INVISIBLE);
                    return true;
                }
                return false;
            }
        });

        /** Видимость кнопки очистки */
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!etSearch.getText().toString().equals("")) {
                    btn_clear.setVisibility(View.VISIBLE);
                } else {
                    btn_clear.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        /** Очистка etSearch */
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSearch.getText().clear();
            }
        });

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

                        Call<MovieSearch> call = myInterface.searchMovies(CommonUtils.CATEGORY_MOVIE, CommonUtils.API_KEY, CommonUtils.LANGUAGE_RU, QUERY, PAGE, CommonUtils.INCLUDE_ADULT_FALSE);

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
                                            Intent intent = new Intent(getContext(), DetailActivity.class);
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

        return view;
    }

    /**
     * Поиск по title
     */
    public void performSearch() {
        PAGE = 1;

        tvNotFound.setVisibility(View.INVISIBLE);

        QUERY = etSearch.getText().toString();

        recyclerViewPosters.setLayoutManager(new GridLayoutManager(getContext(), 3));

        Retrofit retrofit = RetrofitUtils.createRetrofit();

        ApiInterface myInterface = retrofit.create(ApiInterface.class);

        Call<MovieSearch> call = myInterface.searchMovies(CommonUtils.CATEGORY_MOVIE, CommonUtils.API_KEY, CommonUtils.LANGUAGE_RU, QUERY, PAGE, CommonUtils.INCLUDE_ADULT_FALSE);

        call.enqueue(new Callback<MovieSearch>() {
            @Override
            public void onResponse(Call<MovieSearch> call, Response<MovieSearch> response) {
                MovieSearch search = response.body();
                listOfMovies = (ArrayList<MovieSearch.ResultsBean>) search.getResults();
                if (listOfMovies.size() != 0) {
                        movieAdapter.setMovies(listOfMovies);
                        recyclerViewPosters.setAdapter(movieAdapter);
                        movieAdapter.setOnPosterClickListener(new MovieAdapter.OnPosterClickListener() {
                            @Override
                            public void onPosterClick(int position) {
                                Intent intent = new Intent(getContext(), DetailActivity.class);
                                int id = listOfMovies.get(position).getId();
                                intent.putExtra(DetailActivity.RESULT_ID, id);
                                startActivity(intent);
                            }
                        });
                } else {
                    movieAdapter.setMovies(listOfMovies);
                    recyclerViewPosters.setAdapter(movieAdapter);
                    tvNotFound.setVisibility(View.VISIBLE);

                }

            }

            @Override
            public void onFailure(Call<MovieSearch> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}

package com.borschevskydenis.movieshelper.MainTabsLayout;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.borschevskydenis.movieshelper.ApiInterface;
import com.borschevskydenis.movieshelper.R;
import com.borschevskydenis.movieshelper.ResultsFromServer.MovieSearch;
import com.borschevskydenis.movieshelper.SearchMoviesActivity;
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

    public static int PAGE = 1;

    private Button btn_clear;
    private EditText etSearch;
    public static String QUERY;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        btn_clear = view.findViewById(R.id.btn_clear);
        etSearch = view.findViewById(R.id.etSearch);

        etSearch.setImeOptions(EditorInfo.IME_ACTION_SEARCH);

        /** Добавление кнопки поиска на клавиатуре */
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch();
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
                if(!etSearch.getText().toString().equals("")) {
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

        return view;
    }

    /** Поиск по title */
    public void performSearch(){
        QUERY = etSearch.getText().toString();

        Retrofit retrofit = RetrofitUtils.createRetrofit();

        ApiInterface myInterface = retrofit.create(ApiInterface.class);

        Call<MovieSearch> call = myInterface.searchMovies(CommonUtils.CATEGORY_MOVIE, CommonUtils.API_KEY, CommonUtils.LANGUAGE_RU, QUERY, PAGE, CommonUtils.INCLUDE_ADULT_FALSE);

        call.enqueue(new Callback<MovieSearch>() {
            @Override
            public void onResponse(Call<MovieSearch> call, Response<MovieSearch> response) {
                Intent intent = new Intent(getContext(), SearchMoviesActivity.class);
                MovieSearch search = response.body();
                ArrayList<MovieSearch.ResultsBean> listOfMovies = (ArrayList<MovieSearch.ResultsBean>) search.getResults();
                intent.putParcelableArrayListExtra(SearchMoviesActivity.RESULTS_BEAN, listOfMovies);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<MovieSearch> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}

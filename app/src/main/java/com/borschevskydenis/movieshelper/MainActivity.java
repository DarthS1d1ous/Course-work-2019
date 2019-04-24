package com.borschevskydenis.movieshelper;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.borschevskydenis.movieshelper.MainTabsLayout.CatalogFragment;
import com.borschevskydenis.movieshelper.MainTabsLayout.PagerAdapter;
import com.borschevskydenis.movieshelper.MainTabsLayout.SearchFragment;
import com.borschevskydenis.movieshelper.ResultsFromServer.MovieSearch;
import com.borschevskydenis.movieshelper.Utils.CommonUtils;
import com.borschevskydenis.movieshelper.Utils.RetrofitUtils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    public static int PAGE = 1;

    private Button btnSearch;
    private EditText etSearch;
    public static String QUERY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        btnSearch = (Button) findViewById(R.id.btnSearch);
//        etSearch = (EditText) findViewById(R.id.etSearch);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
//
//
//        btnSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                startSearch();
//                QUERY = etSearch.getText().toString();
//
//                QUERY = "Супер";
//
////                Intent intent = new Intent(getApplicationContext(),SearchMoviesActivity.class);
////                intent.putExtra(SearchMoviesActivity.RESULTS_BEAN,QUERY);
////                startActivity(intent);
//
//                Retrofit retrofit = RetrofitUtils.createRetrofit();
//
//                ApiInterface myInterface = retrofit.create(ApiInterface.class);
//
//                Call<MovieSearch> call = myInterface.searchMovies(CommonUtils.CATEGORY_MOVIE, CommonUtils.API_KEY, CommonUtils.LANGUAGE_RU, QUERY, PAGE, CommonUtils.INCLUDE_ADULT_FALSE);
//
//                call.enqueue(new Callback<MovieSearch>() {
//                    @Override
//                    public void onResponse(Call<MovieSearch> call, Response<MovieSearch> response) {
//                        Intent intent = new Intent(getApplicationContext(), SearchMoviesActivity.class);
//                        MovieSearch search = response.body();
//                        ArrayList<MovieSearch.ResultsBean> listOfMovies = (ArrayList<MovieSearch.ResultsBean>) search.getResults();
//                        intent.putParcelableArrayListExtra(SearchMoviesActivity.RESULTS_BEAN, listOfMovies);
//                        startActivity(intent);
//                    }
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
//                    @Override
//                    public void onFailure(Call<MovieSearch> call, Throwable t) {
//                        t.printStackTrace();
//                    }
//                });
//
//            }
//        })

        // Получаем ViewPager и устанавливаем в него адаптер
        ViewPager viewPager = findViewById(R.id.viewpager);
        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new CatalogFragment(),"Каталог");
        adapter.AddFragment(new SearchFragment(),"Поиск");
        adapter.AddFragment(new SearchFragment(), "Фильтры");
        viewPager.setAdapter(adapter);
        // Передаём ViewPager в TabLayout
        tabLayout.setupWithViewPager(viewPager);
    }
}

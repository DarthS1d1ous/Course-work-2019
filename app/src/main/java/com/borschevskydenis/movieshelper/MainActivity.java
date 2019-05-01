package com.borschevskydenis.movieshelper;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.borschevskydenis.movieshelper.MainTabsLayout.CatalogFragment;
import com.borschevskydenis.movieshelper.MainTabsLayout.FavoritesMoviesFragment;
import com.borschevskydenis.movieshelper.MainTabsLayout.PagerAdapter;
import com.borschevskydenis.movieshelper.MainTabsLayout.SearchFragment;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        // Получаем ViewPager и устанавливаем в него адаптер
        ViewPager viewPager = findViewById(R.id.viewpager);
        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new CatalogFragment(), "Каталог");
        adapter.AddFragment(new SearchFragment(), "Поиск");
        adapter.AddFragment(new FavoritesMoviesFragment(), "Избранные");
        viewPager.setAdapter(adapter);
        // Передаём ViewPager в TabLayout
        tabLayout.setupWithViewPager(viewPager);
    }

}

package com.borschevskydenis.movieshelper.MainTabsLayout;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.borschevskydenis.movieshelper.Adapters.FavoritesMoviesAdapter;
import com.borschevskydenis.movieshelper.DB.MainViewModel;
import com.borschevskydenis.movieshelper.DetailActivity;
import com.borschevskydenis.movieshelper.R;
import com.borschevskydenis.movieshelper.ResultsFromServer.MovieById;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesMoviesFragment extends Fragment {

    private MainViewModel viewModel;
    private LiveData<List<MovieById>> favoritesMovies;

    public FavoritesMoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorites_movies, container, false);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        RecyclerView recyclerViewFavoritePosters = view.findViewById(R.id.recyclerViewFavoritePosters);
        final FavoritesMoviesAdapter movieAdapter = new FavoritesMoviesAdapter();
        recyclerViewFavoritePosters.setLayoutManager(new GridLayoutManager(getContext(),3));

        favoritesMovies = viewModel.getAllMovies();
        favoritesMovies.observe(this, new Observer<List<MovieById>>() {
            @Override
            public void onChanged(@Nullable final List<MovieById> movieByIds) {
                movieAdapter.setMovies(movieByIds);
                movieAdapter.setOnPosterClickListener(new FavoritesMoviesAdapter.OnPosterClickListener() {
                    @Override
                    public void onPosterClick(int position) {
                        Intent intent = new Intent(getContext(), DetailActivity.class);
                        int id = movieByIds.get(position).getId();
                        intent.putExtra(DetailActivity.RESULT_ID, id);
                        startActivity(intent);
                    }
                });
            }
        });
        recyclerViewFavoritePosters.setAdapter(movieAdapter);

        return view;
    }

}

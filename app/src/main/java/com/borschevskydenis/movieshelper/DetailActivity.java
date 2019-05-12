package com.borschevskydenis.movieshelper;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.borschevskydenis.movieshelper.Adapters.CastAdapter;
import com.borschevskydenis.movieshelper.Adapters.CrewAdapter;
import com.borschevskydenis.movieshelper.Adapters.RecommendationsMoviesAdapter;
import com.borschevskydenis.movieshelper.DB.MainViewModel;
import com.borschevskydenis.movieshelper.ResultsFromServer.CreditsById;
import com.borschevskydenis.movieshelper.ResultsFromServer.MovieById;
import com.borschevskydenis.movieshelper.ResultsFromServer.MovieSearch;
import com.borschevskydenis.movieshelper.Utils.CommonUtils;
import com.borschevskydenis.movieshelper.Utils.RetrofitUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

//import androidx.lifecycle.ViewModelProviders;

public class DetailActivity extends AppCompatActivity {

    public static int PAGE = 1;
    public static final String RESULT_ID = "result_id";
    private int id;
    private ImageView imageViewBigPoster;
    private CollapsingToolbarLayout toolbarTitle;
    private TextView tvVoteAvarage;
    private TextView tvReleaseDateAndCountry;
    private TextView tvGenres;
    private TextView tvRuntime;
    private TextView tvOverview;
    private TextView tvRecommendationsMovies;
    private TextView tvCast;
    private TextView tvCrew;
    private Button btnEstimate;
    private CoordinatorLayout mCoordinatorLayout;
    private FloatingActionButton mShowFabButton;

    private MainViewModel viewModel;
    private MovieById movie = new MovieById();
    private MovieById favoriteMovie;
    private ArrayList<MovieSearch.ResultsBean> listOfRecommendationMovies;
    private ArrayList<CreditsById.CrewBean> listOfCrew;
    private ArrayList<CreditsById.CastBean> listOfCast;

    private SharedPreferences sharedPreferences;

    private RecyclerView recyclerViewRecommendations;
    private RecommendationsMoviesAdapter recommendationsMoviesAdapter;
    private RecyclerView recyclerViewCrew;
    private CrewAdapter crewAdapter;
    private RecyclerView recyclerViewCast;
    private CastAdapter castAdapter;


//    private ArrayList<Videos.ResultsBean> listOfVideos;

//    private YouTubePlayerView mYouTubePlayerView;
//    private YouTubePlayer.OnInitializedListener mOnInitializedListener;
//    private YouTubePlayerSupportFragment youTubePlayerSupportFragment;


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        /**Устновка оценки*/
        loadRating();

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        tvRecommendationsMovies = findViewById(R.id.recommendationsMovies);
        tvCast = findViewById(R.id.tvCast);
        tvCrew = findViewById(R.id.tvCrew);
        imageViewBigPoster = findViewById(R.id.toolbarImage);
        toolbarTitle = findViewById(R.id.collapsingToolbar);
        tvVoteAvarage = findViewById(R.id.voteAverage);
        tvReleaseDateAndCountry = findViewById(R.id.releaseDateAndCountry);
        tvGenres = findViewById(R.id.genres);
        tvRuntime = findViewById(R.id.runtime);
        tvOverview = findViewById(R.id.overview);
        btnEstimate = findViewById(R.id.estimate);
        mCoordinatorLayout = findViewById(R.id.coordinatorLayout);
        mShowFabButton = findViewById(R.id.floatingActionButton);

//        youTubePlayerSupportFragment = (YouTubePlayerSupportFragment) getSupportFragmentManager().findFragmentById(R.id.youtubePlay);
//        if (youTubePlayerSupportFragment != null) {
//            youTubePlayerSupportFragment.initialize(CommonUtils.API_KEY_YOUTUBE,this);
//        }


//        mYouTubePlayerView = findViewById(R.id.youtubePlay);

//        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
//            @Override
//            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//                youTubePlayer.loadVideo("W4hTJybfU7s");
//            }
//
//            @Override
//            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//
//            }
//        };

//        mYouTubePlayerView.initialize(CommonUtils.API_KEY_YOUTUBE, mOnInitializedListener);



        /**Кнопка назад */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                onBackPressed();// возврат на предыдущий activity
            }
        });

        recyclerViewRecommendations = findViewById(R.id.recyclerViewRecommendations);
        recyclerViewCrew = findViewById(R.id.recyclerViewCrew);
        recyclerViewCast = findViewById(R.id.recyclerViewCast);
//        final RecyclerView recyclerViewAdditionalMaterials = findViewById(R.id.recyclerViewAdditionalMaterials);
        recommendationsMoviesAdapter = new RecommendationsMoviesAdapter();
        crewAdapter = new CrewAdapter();
        castAdapter = new CastAdapter();
//        final AdditionalMaterialsAdapter additionalMaterialsAdapter = new AdditionalMaterialsAdapter();
        recyclerViewRecommendations.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewCrew.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerViewCast.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
//        recyclerViewAdditionalMaterials.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        /** Получение id фильма их прошлой activity*/
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(RESULT_ID)) {
            id = intent.getIntExtra(RESULT_ID, -1);
        } else {
            finish();
        }

        /** Получение подробной информации о фильмах*/
        getDetailInformation();

        /** Получение рекомендованных фильмов*/
        getRecommendationMovies();

        /** Получение съёмочной группы*/
        getCrew();

        /** Получение актёров*/
        getCast();


        /** Получение дополнительной информации(видео)*/
//        Retrofit retrofit3 = RetrofitUtils.createRetrofit();
//
//        ApiInterface myInterface3 = retrofit3.create(ApiInterface.class);
//
//        Call<Videos> callVideos = myInterface3.getVideos(id, CATEGORY_VIDEOS,CommonUtils.API_KEY,CommonUtils.LANGUAGE_RU);
//
//        callVideos.enqueue(new Callback<Videos>() {
//            @Override
//            public void onResponse(Call<Videos> call, Response<Videos> response) {
//                Videos videos = response.body();
//                listOfVideos = (ArrayList<Videos.ResultsBean>) videos.getResults();
//                if(listOfVideos != null){
//                    additionalMaterialsAdapter.setVideos(listOfVideos);
//                    recyclerViewAdditionalMaterials.setAdapter(additionalMaterialsAdapter);
//                    additionalMaterialsAdapter.setOnPosterClickListener(new AdditionalMaterialsAdapter.OnPosterClickListener() {
//                        @Override
//                        public void onPosterClick(int position) {
//                            additionalMaterialsAdapter.Initialize();
//                        }
//                    });
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Videos> call, Throwable t) {
//
//            }
//        });

        /** Оценка фильма*/
        btnEstimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RatingActivity.class);
                intent.putExtra(DetailActivity.RESULT_ID, id);
                startActivity(intent);
            }
        });

        /** База данных*/
        favoriteMovie = viewModel.getMovieById(id);
        if(favoriteMovie == null){
            mShowFabButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.star_offf));
        } else {
            mShowFabButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.star_on));
        }

        mShowFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favoriteMovie = viewModel.getMovieById(id);
                if(favoriteMovie == null){
                    mShowFabButton.setImageDrawable(ContextCompat.getDrawable
                            (getApplicationContext(),R.drawable.star_on));
                    viewModel.insertFavoriteMovie(movie);
                    Snackbar.make(mCoordinatorLayout,"Фильм добавлен в избранное",
                            Snackbar.LENGTH_LONG)
                            .setAction("ЗАКРЫТЬ", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                }
                            }).show();
                } else {
                    viewModel.deleteFavoriteMovie(movie);
                    mShowFabButton.setImageDrawable(ContextCompat.getDrawable
                            (getApplicationContext(),R.drawable.star_offf));
                    Snackbar.make(mCoordinatorLayout,"Фильм удалён из избранного",
                            Snackbar.LENGTH_LONG)
                            .setAction("ЗАКРЫТЬ", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                }
                            }).show();
                }
            }

        });

    }

    /** Получение рекомендованных фильмов*/
    public void getRecommendationMovies(){

        Retrofit retrofit = RetrofitUtils.createRetrofit();

        ApiInterface myInterface = retrofit.create(ApiInterface.class);

        Call<MovieSearch> callRecommendationsMovies = myInterface.getRecommendationsMovie(id, CommonUtils.CATEGORY_RECOMMENDATIONS, CommonUtils.API_KEY, CommonUtils.LANGUAGE_RU, PAGE);

        callRecommendationsMovies.enqueue(new Callback<MovieSearch>() {
            @Override
            public void onResponse(Call<MovieSearch> call, Response<MovieSearch> response) {
                MovieSearch search = response.body();
                listOfRecommendationMovies = (ArrayList<MovieSearch.ResultsBean>) search.getResults();
                if (listOfRecommendationMovies != null && listOfRecommendationMovies.size() != 0) {
                    recommendationsMoviesAdapter.setMovies(listOfRecommendationMovies);
                    recyclerViewRecommendations.setAdapter(recommendationsMoviesAdapter);
                    recommendationsMoviesAdapter.setOnPosterClickListener(new RecommendationsMoviesAdapter.OnPosterClickListener() {
                        @Override
                        public void onPosterClick(int position) {
                            Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                            int id = listOfRecommendationMovies.get(position).getId();
                            intent.putExtra(DetailActivity.RESULT_ID, id);
                            startActivity(intent);
                        }
                    });
                } else tvRecommendationsMovies.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<MovieSearch> call, Throwable t) {

            }
        });
    }

    /** Получение подробной информации о фильмах*/
    public void getDetailInformation(){

        Retrofit retrofit = RetrofitUtils.createRetrofit();

        ApiInterface myInterface = retrofit.create(ApiInterface.class);

        Call<MovieById> callMovieById = myInterface.getMovie(id, CommonUtils.API_KEY, CommonUtils.LANGUAGE_RU);

        callMovieById.enqueue(new Callback<MovieById>() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<MovieById> call, Response<MovieById> response) {
                movie = response.body();
                if(movie.getBackdrop_path()!=null)
                    Picasso.get().load(CommonUtils.BASE_POSTER_URL + CommonUtils.ORIGINAL_SIZE + movie.getBackdrop_path()).into(imageViewBigPoster);
                else {
                    Picasso.get().load(R.drawable.no_image).into(imageViewBigPoster);
                    toolbarTitle.setExpandedTitleColor(getColor(R.color.TabLayout));
                }
                if (movie.getTitle() != null) {
                    toolbarTitle.setTitle(movie.getTitle());
                }
                if (movie.getVote_average() != 0) {
                    tvVoteAvarage.setText(String.format(Locale.getDefault(), "%.1f", movie.getVote_average()));
                } else tvVoteAvarage.setText("Нет оценок");
                if (!movie.getRelease_date().equals("") && !movie.getProduction_countries().isEmpty()) {
                    tvReleaseDateAndCountry.setText(String.format("%.4s, %s", movie.getRelease_date(), movie.getProduction_countries().get(0).getName()));
                } else if (!movie.getProduction_countries().isEmpty()){
                    tvReleaseDateAndCountry.setText(String.format("%s", movie.getProduction_countries().get(0).getName()));
                } else if (!movie.getRelease_date().equals("")) {
                    tvReleaseDateAndCountry.setText(String.format("%.4s", movie.getRelease_date()));
                }
                StringBuilder genres = new StringBuilder();
                if (movie.getGenres()!=null) {
                    for (int i=0; i<movie.getGenres().size();i++) {
                        MovieById.GenresBean genresBean = movie.getGenres().get(i);
                        genres.append(genresBean.getName());
                        if(i != movie.getGenres().size()-1)
                            genres.append(", ");
                    }
                    tvGenres.setText(genres);
                } else tvGenres.setVisibility(View.GONE);
                tvRuntime.setText(String.format(Locale.getDefault(), "%d мин", movie.getRuntime()));
                if (movie.getOverview() != null)
                    tvOverview.setText(movie.getOverview());
            }

            @Override
            public void onFailure(Call<MovieById> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    /** Получение съёмочной группы*/
    public void getCrew(){
        Retrofit retrofit = RetrofitUtils.createRetrofit();

        ApiInterface myInterface = retrofit.create(ApiInterface.class);

        Call<CreditsById> callRecommendationsMovies = myInterface.getInfoAboutMovieByCategory(id, CommonUtils.CATEGORY_CREDITS, CommonUtils.API_KEY);

        callRecommendationsMovies.enqueue(new Callback<CreditsById>() {
            @Override
            public void onResponse(Call<CreditsById> call, Response<CreditsById> response) {
                CreditsById search = response.body();
                listOfCrew = (ArrayList<CreditsById.CrewBean>) search.getCrew();
                if (listOfCrew != null && listOfCrew.size() != 0) {
                    crewAdapter.setMovies(listOfCrew);
                    recyclerViewCrew.setAdapter(crewAdapter);
//                    crewAdapter.setOnPosterClickListener(new CrewAdapter.OnPosterClickListener() {
//                        @Override
//                        public void onPosterClick(int position) {
//                            Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
//                            int id = listOfCrew.get(position).getId();
//                            intent.putExtra(DetailActivity.RESULT_ID, id);
//                            startActivity(intent);
//                        }
//                    });
                } else tvCrew.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<CreditsById> call, Throwable t) {

            }
        });
    }

    /** Получение актёров*/
    public void getCast(){
        Retrofit retrofit = RetrofitUtils.createRetrofit();

        ApiInterface myInterface = retrofit.create(ApiInterface.class);

        Call<CreditsById> callRecommendationsMovies = myInterface.getInfoAboutMovieByCategory(id, CommonUtils.CATEGORY_CREDITS, CommonUtils.API_KEY);

        callRecommendationsMovies.enqueue(new Callback<CreditsById>() {
            @Override
            public void onResponse(Call<CreditsById> call, Response<CreditsById> response) {
                CreditsById search = response.body();
                listOfCast = (ArrayList<CreditsById.CastBean>) search.getCast();
                if (listOfCast != null && listOfCast.size() != 0) {
                    castAdapter.setMovies(listOfCast);
                    recyclerViewCast.setAdapter(castAdapter);
//                    crewAdapter.setOnPosterClickListener(new CrewAdapter.OnPosterClickListener() {
//                        @Override
//                        public void onPosterClick(int position) {
//                            Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
//                            int id = listOfCrew.get(position).getId();
//                            intent.putExtra(DetailActivity.RESULT_ID, id);
//                            startActivity(intent);
//                        }
//                    });
                } else tvCast.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<CreditsById> call, Throwable t) {

            }
        });
    }

//    /**Простое меню */
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_detail, menu);
//        return true;
//    }
//    /** Переход в БД*/
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        switch (id){
//            case R.id.favoritesMovies:
//                Intent intent = new Intent(getApplicationContext(), FavoritesMoviesActivity.class);
//                startActivity(intent);
//
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    void loadRating(){
        sharedPreferences = getSharedPreferences(RatingActivity.RATING,MODE_PRIVATE);
        int rating = sharedPreferences.getInt(String.valueOf(id), 0);
        if(rating!=0){
            btnEstimate.setText(String.format(Locale.getDefault(),
                    "Моя оценка %d", rating));
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        loadRating();
    }

//    @Override
//    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//        if(!b){
//            youTubePlayer.loadVideo("D0q0QeQbw9U");
//        }
//    }
//
//    @Override
//    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
////        if (youTubeInitializationResult.isUserRecoverableError()) {
////            youTubeInitializationResult.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
////        } else {
////            String errorMessage = String.format(getString(R.string.error_player), youTubeInitializationResult.toString());
////            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
////        }
//    }
}

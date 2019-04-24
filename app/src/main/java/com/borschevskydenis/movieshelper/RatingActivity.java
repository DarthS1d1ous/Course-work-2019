package com.borschevskydenis.movieshelper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.borschevskydenis.movieshelper.ResultsFromServer.MovieById;

import java.util.Locale;

public class RatingActivity extends AppCompatActivity {

    public static final String RATING="RATING";

    ImageButton btnExit;
    RatingBar ratingBar;
    Button btnEstimate, btnsetTozero;
    SharedPreferences sharedPreferences;

    private int movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        btnExit = findViewById(R.id.btnExit);
        ratingBar = findViewById(R.id.ratingBar);
        btnEstimate = findViewById(R.id.btnEstimate);
        btnsetTozero = findViewById(R.id.btnsetToZero);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnsetTozero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratingBar.setRating(0);
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

            }
        });

        btnEstimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveRating();
                finish();
            }
        });

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(DetailActivity.RESULT_ID)) {
            movieId = intent.getIntExtra(DetailActivity.RESULT_ID, 0);
        }
        loadRating();
    }

    void saveRating() {
        sharedPreferences = getSharedPreferences(RatingActivity.RATING,MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.putInt( String.valueOf(movieId), (int) ratingBar.getRating());
        editor.apply();
    }

    void loadRating(){
        sharedPreferences = getSharedPreferences(RatingActivity.RATING,MODE_PRIVATE);
        int rating = sharedPreferences.getInt(String.valueOf(movieId), 0);
        if(rating!=0){
            ratingBar.setRating(rating);
        }
    }
}

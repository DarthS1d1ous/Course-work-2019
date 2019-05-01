package com.borschevskydenis.movieshelper;

import com.borschevskydenis.movieshelper.ResultsFromServer.CreditsById;
import com.borschevskydenis.movieshelper.ResultsFromServer.Genres;
import com.borschevskydenis.movieshelper.ResultsFromServer.MovieById;
import com.borschevskydenis.movieshelper.ResultsFromServer.MovieSearch;
import com.borschevskydenis.movieshelper.ResultsFromServer.Rofl;
import com.borschevskydenis.movieshelper.ResultsFromServer.Videos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

//    @GET("/3/movie/{category}")
//    Call<MovieResults> getMovies(
//            @Path("category") String category,
//            @Query("api_key") String apiKey,
//            @Query("language") String language,
//            @Query("page") int page
//    );
    @GET("/3/search/{category}")
    Call<MovieSearch> searchMovies(
            @Path("category") String category,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("query") String query,
            @Query("page") int page,
            @Query("include_adult") boolean include_adult
    );

    @GET("/3/movie/{movie_id}")
    Call<MovieById> getMovie(
            @Path("movie_id") int movieId,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("/3/movie/{movie_id}/{category}")
    Call<MovieSearch> getRecommendationsMovie(
            @Path("movie_id") int movieId,
            @Path("category") String category,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("/3/movie/{movie_id}/{category}")
    Call<CreditsById> getInfoAboutMovieByCategory(
            @Path("movie_id") int movieId,
            @Path("category") String category,
            @Query("api_key") String apiKey
    );

    @GET("/3/movie/{category}")
    Call<MovieSearch> getMoviesByCategory(
            @Path("category") String category,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page,
            @Query("region") String region
    );

    @GET("/3/discover/{category}")
    Call<MovieSearch> getDiscoverMovies(
            @Path("category") String category,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("sort") String sort,
            @Query("include_adult") boolean include_adult,
            @Query("page") int page,
            @Query("with_genres") String genre
    );

    @GET("/3/genre/movie/list")
    Call<Genres> getGenres(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );


}

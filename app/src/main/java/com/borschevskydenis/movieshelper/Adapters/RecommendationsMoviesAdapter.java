package com.borschevskydenis.movieshelper.Adapters;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.borschevskydenis.movieshelper.R;
import com.borschevskydenis.movieshelper.ResultsFromServer.MovieSearch;
import com.borschevskydenis.movieshelper.Utils.CommonUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecommendationsMoviesAdapter extends RecyclerView.Adapter<RecommendationsMoviesAdapter.MovieViewHolder> {

    private ArrayList<MovieSearch.ResultsBean> movies;
    private OnPosterClickListener onPosterClickListener;

    public void setOnPosterClickListener(OnPosterClickListener onPosterClickListener) {
        this.onPosterClickListener = onPosterClickListener;
    }

    public RecommendationsMoviesAdapter(){
        movies = new ArrayList<>();
    }

    public interface OnPosterClickListener{
        void onPosterClick(int position);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recommendation_movie_item, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        MovieSearch.ResultsBean movie = movies.get(i);
        Picasso.get().load(CommonUtils.BASE_POSTER_URL + CommonUtils.ORIGINAL_SIZE + movie.getPoster_path()).into(movieViewHolder.imageViewSmallPoster);
        movieViewHolder.textViewTitle.setText(movie.getTitle());
//        movieViewHolder.textViewOriginalTitle.setText(movie.getOriginal_title());

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageViewSmallPoster;
        private TextView textViewTitle;
//        private TextView textViewOriginalTitle;
//        private TextView textViewGenre;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewSmallPoster = itemView.findViewById(R.id.ivSmallPoster);
            textViewTitle = itemView.findViewById(R.id.tvTitle);
//            textViewOriginalTitle = itemView.findViewById(R.id.tvOriginalTitle);
//            textViewGenre = itemView.findViewById(R.id.tvGenre);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onPosterClickListener != null){
                        onPosterClickListener.onPosterClick(getAdapterPosition());
                    }
                }
            });
        }
    }

    public void setMovies(ArrayList<MovieSearch.ResultsBean> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public ArrayList<MovieSearch.ResultsBean> getMovies() {
        return movies;
    }
}




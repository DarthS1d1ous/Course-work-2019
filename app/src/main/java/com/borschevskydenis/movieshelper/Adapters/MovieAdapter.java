package com.borschevskydenis.movieshelper.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.borschevskydenis.movieshelper.R;
import com.borschevskydenis.movieshelper.ResultsFromServer.MovieSearch;
import com.borschevskydenis.movieshelper.Utils.CommonUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private ArrayList<MovieSearch.ResultsBean> movies;
    private OnPosterClickListener onPosterClickListener;
    public static Boolean isLoading;

    public void setOnPosterClickListener(OnPosterClickListener onPosterClickListener) {
        this.onPosterClickListener = onPosterClickListener;
    }

    public MovieAdapter(){
        movies = new ArrayList<>();
    }

    public interface OnPosterClickListener{
        void onPosterClick(int position);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_item, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        MovieSearch.ResultsBean movie = movies.get(i);
        if(movie.getPoster_path()!=null)
            Picasso.get().load(CommonUtils.BASE_POSTER_URL + CommonUtils.W342_SIZE + movie.getPoster_path()).into(movieViewHolder.imageViewSmallPoster);
        else Picasso.get().load(R.drawable.poster_is_missing).into(movieViewHolder.imageViewSmallPoster);
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
        isLoading=false;
    }

    public ArrayList<MovieSearch.ResultsBean> getMovies() {
        return movies;
    }
}




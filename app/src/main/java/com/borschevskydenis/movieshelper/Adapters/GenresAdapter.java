package com.borschevskydenis.movieshelper.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.borschevskydenis.movieshelper.R;
import com.borschevskydenis.movieshelper.ResultsFromServer.Genres;
import com.borschevskydenis.movieshelper.ResultsFromServer.MovieSearch;
import com.borschevskydenis.movieshelper.Utils.CommonUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.MovieViewHolder> {

    private ArrayList<Genres.GenresBean> genres;
    private OnButtonClickListener onButtonClickListener;

    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener){
        this.onButtonClickListener = onButtonClickListener;
    }

    public interface OnButtonClickListener {
        void onButtonClick(int position);
    }

    public GenresAdapter(){
        genres = new ArrayList<>();
    }

    @NonNull
    @Override
    public GenresAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.genres_item, viewGroup, false);
        return new GenresAdapter.MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenresAdapter.MovieViewHolder movieViewHolder, int i) {
        Genres.GenresBean genre = genres.get(i);
        movieViewHolder.buttonGenre.setText(genre.getName());
//        movieViewHolder.textViewOriginalTitle.setText(movie.getOriginal_title());

    }

    @Override
    public int getItemCount() {
        return genres.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder{

        private Button buttonGenre;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            buttonGenre = itemView.findViewById(R.id.btnGenre);
            buttonGenre.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onButtonClickListener != null){
                        onButtonClickListener.onButtonClick(getAdapterPosition());
                    }
                }
            });
        }
    }

    public void setGenres(ArrayList<Genres.GenresBean> movies) {
        this.genres = movies;
        notifyDataSetChanged();
    }

    public ArrayList<Genres.GenresBean> getGenres() {
        return genres;
    }
}


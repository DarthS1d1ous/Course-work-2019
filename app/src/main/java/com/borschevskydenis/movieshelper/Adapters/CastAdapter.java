package com.borschevskydenis.movieshelper.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.borschevskydenis.movieshelper.R;
import com.borschevskydenis.movieshelper.ResultsFromServer.CreditsById;
import com.borschevskydenis.movieshelper.Utils.CommonUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.MovieViewHolder> {

    private ArrayList<CreditsById.CastBean> movies;
    private CastAdapter.OnPosterClickListener onPosterClickListener;
    public static Boolean isLoading;

    public void setOnPosterClickListener(CastAdapter.OnPosterClickListener onPosterClickListener) {
        this.onPosterClickListener = onPosterClickListener;
    }

    public CastAdapter(){
        movies = new ArrayList<>();
    }

    public interface OnPosterClickListener{
        void onPosterClick(int position);
    }

    @NonNull
    @Override
    public CastAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.crew_item, viewGroup, false);
        return new CastAdapter.MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CastAdapter.MovieViewHolder movieViewHolder, int i) {
        CreditsById.CastBean castBean = movies.get(i);
        if(castBean.getProfile_path()!=null)
            Picasso.get().load(CommonUtils.BASE_POSTER_URL + CommonUtils.W342_SIZE + castBean.getProfile_path()).into(movieViewHolder.imageViewProfile);
        else Picasso.get().load(R.drawable.empty_avatar).into(movieViewHolder.imageViewProfile);
        movieViewHolder.textViewName.setText(castBean.getName());
        movieViewHolder.textViewJob.setText(castBean.getCharacter());
//        movieViewHolder.textViewOriginalTitle.setText(movie.getOriginal_title());

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageViewProfile;
        private TextView textViewName;
        private TextView textViewJob;


        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewProfile = itemView.findViewById(R.id.ivProfile);
            textViewName = itemView.findViewById(R.id.tvName);
            textViewJob = itemView.findViewById(R.id.tvJob);
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

    public void setMovies(ArrayList<CreditsById.CastBean> movies) {
        this.movies = movies;
        notifyDataSetChanged();
//        isLoading=false;
    }

    public ArrayList<CreditsById.CastBean> getMovies() {
        return movies;
    }
}





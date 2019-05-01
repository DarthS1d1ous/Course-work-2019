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

public class CrewAdapter extends RecyclerView.Adapter<CrewAdapter.MovieViewHolder> {

    private ArrayList<CreditsById.CrewBean> movies;
    private CrewAdapter.OnPosterClickListener onPosterClickListener;
    public static Boolean isLoading;

    public void setOnPosterClickListener(CrewAdapter.OnPosterClickListener onPosterClickListener) {
        this.onPosterClickListener = onPosterClickListener;
    }

    public CrewAdapter(){
        movies = new ArrayList<>();
    }

    public interface OnPosterClickListener{
        void onPosterClick(int position);
    }

    @NonNull
    @Override
    public CrewAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.crew_item, viewGroup, false);
        return new CrewAdapter.MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CrewAdapter.MovieViewHolder movieViewHolder, int i) {
        CreditsById.CrewBean crewBean = movies.get(i);
        if(crewBean.getProfile_path()!=null)
            Picasso.get().load(CommonUtils.BASE_POSTER_URL + CommonUtils.W342_SIZE + crewBean.getProfile_path()).into(movieViewHolder.imageViewProfile);
        else Picasso.get().load(R.drawable.empty_avatar).into(movieViewHolder.imageViewProfile);
        movieViewHolder.textViewName.setText(crewBean.getName());
        movieViewHolder.textViewJob.setText(crewBean.getJob());
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

    public void setMovies(ArrayList<CreditsById.CrewBean> movies) {
        this.movies = movies;
        notifyDataSetChanged();
//        isLoading=false;
    }

    public ArrayList<CreditsById.CrewBean> getMovies() {
        return movies;
    }
}





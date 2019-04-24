//package com.borschevskydenis.movieshelper.Adapters;
//
//import android.content.Context;
//import android.os.Build;
//import android.support.annotation.NonNull;
//import android.support.annotation.RequiresApi;
//import android.support.v4.app.Fragment;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.borschevskydenis.movieshelper.R;
//import com.borschevskydenis.movieshelper.ResultsFromServer.MovieSearch;
//import com.borschevskydenis.movieshelper.ResultsFromServer.Videos;
//import com.borschevskydenis.movieshelper.Utils.CommonUtils;
//import com.google.android.youtube.player.YouTubeInitializationResult;
//import com.google.android.youtube.player.YouTubePlayer;
//import com.google.android.youtube.player.YouTubePlayerSupportFragment;
//import com.squareup.picasso.Picasso;
//
//import java.util.ArrayList;
//
//public class AdditionalMaterialsAdapter extends RecyclerView.Adapter<AdditionalMaterialsAdapter.MovieViewHolder> implements YouTubePlayer.OnInitializedListener {
//    private ArrayList<Videos.ResultsBean> videos;
//    private AdditionalMaterialsAdapter.OnPosterClickListener onPosterClickListener;
//    private YouTubePlayerSupportFragment youTubePlayerSupportFragment;
//    private int h;
//
//    public void setOnPosterClickListener(AdditionalMaterialsAdapter.OnPosterClickListener onPosterClickListener) {
//        this.onPosterClickListener = onPosterClickListener;
//    }
//
//    public AdditionalMaterialsAdapter(){
//        videos = new ArrayList<>();
//    }
//
//
//
//    public interface OnPosterClickListener{
//        void onPosterClick(int position);
//    }
//
//    @NonNull
//    @Override
//    public AdditionalMaterialsAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.additional_materials_item, viewGroup, false);
//        h=i;
//        return new AdditionalMaterialsAdapter.MovieViewHolder(view);
//    }
//
////    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
//    @Override
//    public void onBindViewHolder(@NonNull AdditionalMaterialsAdapter.MovieViewHolder movieViewHolder, int i) {
//        Videos.ResultsBean video = videos.get(i);
//        movieViewHolder.textViewTitle.setText(video.getType());
//    }
//
//    @Override
//    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//        if(!b){
//            youTubePlayer.loadVideo(videos.get(h).getKey());
//        }
//    }
//
//    @Override
//    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return videos.size();
//    }
//
//    class MovieViewHolder extends RecyclerView.ViewHolder{
//
//        private TextView textViewTitle;
////        private TextView textViewOriginalTitle;
////        private TextView textViewGenre;
//
//        public MovieViewHolder(@NonNull View itemView) {
//            super(itemView);
//            youTubePlayerSupportFragment =(YouTubePlayerSupportFragment) ((AppCompatActivity)itemView.getContext()).getSupportFragmentManager().findFragmentById(R.id.youtubePlay);
//            textViewTitle = itemView.findViewById(R.id.tvTitle);
//            textViewTitle.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (onPosterClickListener != null){
//                        onPosterClickListener.onPosterClick(getAdapterPosition());
//                    }
//                }
//            });
//        }
//    }
//
//    public void setVideos(ArrayList<Videos.ResultsBean> videos) {
//        this.videos = videos;
//        notifyDataSetChanged();
//    }
//
//    public void Initialize(){
//        if (youTubePlayerSupportFragment != null) {
//            youTubePlayerSupportFragment.initialize(CommonUtils.API_KEY_YOUTUBE,this);
//        }
//    }
//
//    public ArrayList<Videos.ResultsBean> getMovies() {
//        return videos;
//    }
//}
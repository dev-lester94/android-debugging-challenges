package com.codepath.debuggingchallenges.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.debuggingchallenges.R;
import com.codepath.debuggingchallenges.models.Movie;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private List<Movie> movies;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // only needed because we need to set the background color
        View view;

        // Lookup view for data population
        TextView tvName;
        TextView tvRating;
        ImageView ivPoster;

        public ViewHolder(View itemView) {
            super(itemView);

            view = itemView;
            tvName = itemView.findViewById(R.id.tvTitle);
            tvRating = itemView.findViewById(R.id.tvRating);
            ivPoster = itemView.findViewById(R.id.ivPoster);
        }

        public void bind(Movie movie) {
            // Populate the data into the template view using the data object
            tvName.setText(movie.getTitle());

            Resources resources = tvName.getResources();
            double movieRating = movie.getRating();

            if (movieRating > 6) {
                view.setBackgroundColor(Color.GREEN);
            }

            String ratingText = String.format(resources.getString(R.string.rating), movieRating);
            tvRating.setText(ratingText);

            Glide.with(ivPoster.getContext()).load(movie.getPosterUrl()).into(
                    ivPoster);
        }
    }

    public MoviesAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public int getItemCount() {
        Log.i("getItemCount", ""+movies.size());
        return movies.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View movieView = inflater.inflate(R.layout.item_movie, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(movieView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MoviesAdapter.ViewHolder viewHolder, int position) {

        Movie movie = movies.get(position);

        viewHolder.bind(movie);



    }
}

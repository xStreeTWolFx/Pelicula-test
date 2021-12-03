package com.example.peliculatest.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.peliculatest.R;
import com.example.peliculatest.model.Movie;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieRecycleAdapter extends RecyclerView.Adapter<MovieRecycleAdapter.ViewHolder> {
    List<Movie> movieList = new ArrayList<>();
    Context context;

    public MovieRecycleAdapter(Context context) {
        this.context = context;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList == null ? new ArrayList<>() : movieList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.favoriteButton.setImageDrawable(movie.isFavorite() ? getDrawable(R.drawable.ic_star) : getDrawable(R.drawable.ic_star_disable));
        holder.favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movie.setFavorite(!movie.isFavorite());
                notifyDataSetChanged();
            }
        });
        String path = movie.getPosterPath();
        String prefix = "https://image.tmdb.org/t/p/w500";
        Picasso.get().load(prefix + path).resize(400, 400).centerInside()
                .error(R.drawable.ic_cinema).into(holder.movieImage, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.INVISIBLE);
                holder.favoriteButton.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError(Exception e) {
                holder.progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    private Drawable getDrawable(int resId) {
        return ContextCompat.getDrawable(context, resId);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView movieImage;
        ProgressBar progressBar;
        ImageButton favoriteButton;

        ViewHolder(View view) {
            super(view);
            movieImage = view.findViewById(R.id.image_movie);
            progressBar = view.findViewById(R.id.progress_image);
            favoriteButton = view.findViewById(R.id.image_button_favorite);
        }
    }
}

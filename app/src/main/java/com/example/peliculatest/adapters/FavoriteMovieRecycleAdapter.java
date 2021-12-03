package com.example.peliculatest.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class FavoriteMovieRecycleAdapter extends RecyclerView.Adapter<FavoriteMovieRecycleAdapter.ViewHolder> {
    List<Movie> movieList = new ArrayList<>();
    Context context;

    public FavoriteMovieRecycleAdapter(Context context) {
        this.context = context;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList == null ? new ArrayList<>() : movieList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoriteMovieRecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new FavoriteMovieRecycleAdapter.ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteMovieRecycleAdapter.ViewHolder holder, int position) {
        Movie movie = movieList.get(position);

        String path = movie.getPosterPath();
        //TODO: pasa a una clase de constantes
        String prefix = "https://image.tmdb.org/t/p/w500";
        Picasso.get().load(prefix + path).resize(400, 400).centerInside()
                .error(R.drawable.ic_cinema).into(holder.movieImage, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.INVISIBLE);
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

        ViewHolder(View view) {
            super(view);
            movieImage = view.findViewById(R.id.image_movie);
            progressBar = view.findViewById(R.id.progress_image);
        }
    }
}

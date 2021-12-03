package com.example.peliculatest.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.peliculatest.R;
import com.example.peliculatest.model.Movie;
import com.example.peliculatest.utilities.TextUtil;
import com.example.peliculatest.viewModel.MovieViewModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    public static final String MOVIE_ID_KEY = "movie_key";
    private ImageView imageView;
    private TextView title;
    private TextView overview;
    private TextView rating;
    private ProgressBar progressBar;
    private MovieViewModel viewModel;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        imageView = findViewById(R.id.image_movie_detail);
        title = findViewById(R.id.text_title);
        overview = findViewById(R.id.text_overview);
        rating = findViewById(R.id.text_post_average);
        progressBar = findViewById(R.id.progress_image_detail);
        Integer movieId = getIntent().getExtras().getInt(MOVIE_ID_KEY);
        viewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        viewModel.getMovie(movieId).observe(this, movie -> {
            String path = movie.getPosterPath();
            String prefix = "https://image.tmdb.org/t/p/w500";
            title.setText(movie.getTitle());
            overview.setText(movie.getOverview());
            rating.setText(TextUtil.formatDouble(movie.getVoteAverage()));
            Picasso.get().load(prefix + path).resize(400, 400).centerInside()
                    .error(R.drawable.ic_cinema).into(imageView, new Callback() {
                @Override
                public void onSuccess() {
                    progressBar.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onError(Exception e) {
                    progressBar.setVisibility(View.INVISIBLE);
                }
            });

        });
    }
}
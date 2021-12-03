package com.example.peliculatest.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.peliculatest.R;
import com.example.peliculatest.adapters.FavoriteMovieRecycleAdapter;
import com.example.peliculatest.viewModel.MovieViewModel;

public class FavoriteMovieListFragment extends Fragment {

    private RecyclerView recyclerView;
    private FavoriteMovieRecycleAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private MovieViewModel viewModel;

    public FavoriteMovieListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorite_movie_list, container, false);
        adapter = new FavoriteMovieRecycleAdapter(getContext());
        layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView = view.findViewById(R.id.recycle_view_favorite_movie);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        viewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        viewModel.getFavoriteMovies().observe(getViewLifecycleOwner(), movies -> adapter.setMovieList(movies));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.getFavoriteMovies();
    }
}
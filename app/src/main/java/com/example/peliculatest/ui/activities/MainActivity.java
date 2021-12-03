package com.example.peliculatest.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.peliculatest.R;
import com.example.peliculatest.adapters.MoviePagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 pager;
    private TabLayout tabLayout;
    private MoviePagerAdapter moviePagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        moviePagerAdapter = new MoviePagerAdapter(this);
        pager = findViewById(R.id.view_pager);
        pager.setAdapter(moviePagerAdapter);
        tabLayout = findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, pager, (tab, position) -> tab.setText(position == 0 ? "Peli" : "Fav")).attach();

    }
}
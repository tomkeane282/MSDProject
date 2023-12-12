package com.example.msdproject;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.msdproject.databinding.ActivityMovieDetailBinding;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        TextView tvTitle = findViewById(R.id.tvTitle);
        TextView tvYear = findViewById(R.id.tvYear);
        TextView tvLength = findViewById(R.id.tvLength);

        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra("title");
            String year = intent.getStringExtra("year");
            String length = intent.getStringExtra("length");

            tvTitle.setText(title);
            tvYear.setText(year);
            tvLength.setText(length);
        }
    }
}


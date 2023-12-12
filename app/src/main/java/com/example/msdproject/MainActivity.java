package com.example.msdproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.msdproject.database.AppDatabase;
import com.example.msdproject.database.Movie;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AppDatabase db;
    private ConstraintLayout constraintLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        constraintLayout = findViewById(R.id.constraintLayout);
        db = AppDatabase.getDatabase(this);

        loadMovies();

        Button Movie1Button = findViewById(R.id.Movie1Button);
        Movie1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Movie1.class);
                startActivity(intent);
            }
        });

        Button profileButton = findViewById(R.id.profileButton);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Profile.class);
                startActivity(intent);
            }
        });




    }

    private void loadMovies() {
        new Thread(() -> {
            List<Movie> movies = db.movieDao().getAll();
            Log.d("MainActivity", "Number of movies retrieved: " + movies.size());
            for (Movie movie : movies) {
                Log.d("MainActivity", "Movie: " + movie.title);
            }
            runOnUiThread(() -> createButtonsForMovies(movies));
        }).start();
    }


    private void createButtonsForMovies(List<Movie> movies) {
        ConstraintSet constraintSet = new ConstraintSet();
        int previousButtonId = ConstraintSet.PARENT_ID; // Start with the parent ID

        for (Movie movie : movies) {
            Button button = new Button(this);
            button.setId(View.generateViewId()); // Generate a unique ID for each button
            button.setText(movie.title);
            button.setOnClickListener(v -> openMovieDetailActivity(movie));

            constraintLayout.addView(button);

            // Clone the current constraints to modify them
            constraintSet.clone(constraintLayout);

            // Connect the top of the button to the bottom of the previous button
            constraintSet.connect(button.getId(), ConstraintSet.TOP, previousButtonId, previousButtonId == ConstraintSet.PARENT_ID ? ConstraintSet.TOP : ConstraintSet.BOTTOM, 8);


            constraintSet.applyTo(constraintLayout);

            previousButtonId = button.getId(); // Update previousButtonId for the next iteration
        }
    }

    private void openMovieDetailActivity(Movie movie) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("title", movie.title);
        intent.putExtra("year", movie.year);
        intent.putExtra("length", movie.length);
        startActivity(intent);
    }
}
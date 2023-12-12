package com.example.msdproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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
        int previousViewId = ConstraintSet.PARENT_ID; // Start with the parent ID

        // Create and configure the TextView for the "List of Movies" title
        TextView titleTextView = new TextView(this);
        titleTextView.setId(View.generateViewId()); // Generate a unique ID
        titleTextView.setText("Browse Movies");
        titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18); // Example text size
        titleTextView.setTextColor(Color.WHITE); // Set text color
        titleTextView.setGravity(Gravity.CENTER);
        titleTextView.setPadding(0, 60, 0, 42);


        // Set the layout parameters for the TextView
        ConstraintLayout.LayoutParams titleParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        titleTextView.setLayoutParams(titleParams);

        // Add the TextView to the ConstraintLayout
        constraintLayout.addView(titleTextView);

        // Set up constraints for the TextView
        constraintSet.clone(constraintLayout);
        constraintSet.connect(titleTextView.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0);
        constraintSet.connect(titleTextView.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
        constraintSet.connect(titleTextView.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
        constraintSet.applyTo(constraintLayout);

        previousViewId = titleTextView.getId(); // Set the ID of the TextView as the previous view for the first button

        for (Movie movie : movies) {
            Button button = new Button(this);
            button.setId(View.generateViewId()); // Generate a unique ID for each button
            button.setText(movie.title);
            button.setOnClickListener(v -> openMovieDetailActivity(movie));

            // Set the button width to match the parent's width
            ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
            );
            button.setLayoutParams(params);

            // Set text color to white and background color to dark grey
            button.setTextColor(Color.WHITE);
            button.setBackgroundColor(Color.DKGRAY);

            constraintLayout.addView(button);

            // Clone the current constraints to modify them
            constraintSet.clone(constraintLayout);

            // Connect the top of the button to the bottom of the previous view
            constraintSet.connect(button.getId(), ConstraintSet.TOP, previousViewId, ConstraintSet.BOTTOM, 0);

            // Connect the button's left and right to the parent's left and right
            constraintSet.connect(button.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
            constraintSet.connect(button.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);

            // Apply the constraints
            constraintSet.applyTo(constraintLayout);

            previousViewId = button.getId(); // Update previousViewId for the next iteration
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
package com.example.msdproject.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Movie {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "year")
    public String year;

    @ColumnInfo(name = "length")
    public String length;

    @ColumnInfo(name = "imageUrl")
    public String imageUrl;

    @ColumnInfo(name = "userRating")
    public String userRating;

    @ColumnInfo(name = "watchlisted")
    public String watchlisted;

    // Constructor
    public Movie(String title, String year, String length, String imageUrl, String userRating, String watchlisted) {
        this.title = title;
        this.year = year;
        this.length = length;
        this.imageUrl = imageUrl;
        this.userRating = userRating;
        this.watchlisted = watchlisted;
    }

}

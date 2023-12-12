package com.example.msdproject.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDao {
    @Query("SELECT * FROM movie")
    List<Movie> getAll();

    @Insert
    void insertAll(Movie... movies);

    @Delete
    void delete(Movie movie);

    @Query("DELETE FROM movie")
    void deleteAll();



}

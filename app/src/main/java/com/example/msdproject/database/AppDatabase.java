package com.example.msdproject.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executors;

@Database(entities = {Movie.class}, version = 4)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();
    private static volatile AppDatabase INSTANCE;


    private static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);


            Executors.newSingleThreadExecutor().execute(() -> {

                MovieDao dao = INSTANCE.movieDao();
                dao.deleteAll();


                Movie movie1 = new Movie("Inception","2010", "2h 28m","https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_.jpg","", "");
                dao.insertAll(movie1);
                Movie movie2 = new Movie("The Matrix","1999","2h 16m","https://m.media-amazon.com/images/M/MV5BNzQzOTk3OTAtNDQ0Zi00ZTVkLWI0MTEtMDllZjNkYzNjNTc4L2ltYWdlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_FMjpg_UX1000_.jpg","","");
                dao.insertAll(movie2);
                Movie movie3 = new Movie("Toy Story 3","2010","1h 43m","https://m.media-amazon.com/images/M/MV5BMTI3NDUyMzk5MV5BMl5BanBnXkFtZTcwMjMyMjI0Mw@@._V1_.jpg","","");
                dao.insertAll(movie3);
                Movie movie4 = new Movie("Jaws","1975","2h 4m","https://m.media-amazon.com/images/M/MV5BMmVmODY1MzEtYTMwZC00MzNhLWFkNDMtZjAwM2EwODUxZTA5XkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_.jpg","","");
                dao.insertAll(movie4);
            });
        }
    };

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "movie_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}




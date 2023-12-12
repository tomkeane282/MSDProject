package com.example.msdproject.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executors;

@Database(entities = {Movie.class}, version = 5)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();
    private static volatile AppDatabase INSTANCE;


    private static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);


            Executors.newSingleThreadExecutor().execute(() -> {

                MovieDao dao = INSTANCE.movieDao();
                dao.deleteAll();


                Movie movie1 = new Movie("Inception","2010", "2h 28m","https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_.jpg","9.1", "a");
                dao.insertAll(movie1);
                Movie movie2 = new Movie("The Matrix","1999","2h 16m","https://m.media-amazon.com/images/M/MV5BNzQzOTk3OTAtNDQ0Zi00ZTVkLWI0MTEtMDllZjNkYzNjNTc4L2ltYWdlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_FMjpg_UX1000_.jpg","8.4","a");
                dao.insertAll(movie2);
                Movie movie3 = new Movie("Toy Story 3","2010","1h 43m","https://m.media-amazon.com/images/M/MV5BMTI3NDUyMzk5MV5BMl5BanBnXkFtZTcwMjMyMjI0Mw@@._V1_.jpg","7.9","a");
                dao.insertAll(movie3);
                Movie movie4 = new Movie("Jaws","1975","2h 4m","https://m.media-amazon.com/images/M/MV5BMmVmODY1MzEtYTMwZC00MzNhLWFkNDMtZjAwM2EwODUxZTA5XkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_.jpg","7.5","a");
                dao.insertAll(movie4);
                Movie movie5 = new Movie("The Incredibles","2004","1h 55m","https://m.media-amazon.com/images/M/MV5BMTY5OTU0OTc2NV5BMl5BanBnXkFtZTcwMzU4MDcyMQ@@._V1_.jpg","6.4","a");
                dao.insertAll(movie5);
                Movie movie6 = new Movie("Forrest Gump","1994","2h 22m","https://m.media-amazon.com/images/M/MV5BNWIwODRlZTUtY2U3ZS00Yzg1LWJhNzYtMmZiYmEyNmU1NjMzXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_FMjpg_UX1000_.jpg","8.7","a");
                dao.insertAll(movie6);
                Movie movie7 = new Movie("Memento","2000","1h 53m","https://m.media-amazon.com/images/M/MV5BZTcyNjk1MjgtOWI3Mi00YzQwLWI5MTktMzY4ZmI2NDAyNzYzXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_.jpg","7.4","a");
                dao.insertAll(movie7);
                Movie movie8 = new Movie("WALL-E","2008","1h 38m","https://m.media-amazon.com/images/M/MV5BMjExMTg5OTU0NF5BMl5BanBnXkFtZTcwMjMxMzMzMw@@._V1_.jpg","8.1","a");
                dao.insertAll(movie8);
                Movie movie9 = new Movie("Into the Wild","2007","2h 4m","https://m.media-amazon.com/images/M/MV5BNjQ0ODlhMWUtNmUwMS00YjExLWI4MjQtNjVmMmE2Y2E0MGRmXkEyXkFqcGdeQXVyNDk3NzU2MTQ@._V1_.jpg","7.1","a");
                dao.insertAll(movie9);
                Movie movie10 = new Movie("Avengers: Infinity War","2018","2h 29m","https://m.media-amazon.com/images/M/MV5BMjMxNjY2MDU1OV5BMl5BanBnXkFtZTgwNzY1MTUwNTM@._V1_.jpg","7.0","a");
                dao.insertAll(movie10);
                Movie movie11 = new Movie("Oppenheimer","2023","3h 0m","https://m.media-amazon.com/images/M/MV5BMDBmYTZjNjUtN2M1MS00MTQ2LTk2ODgtNzc2M2QyZGE5NTVjXkEyXkFqcGdeQXVyNzAwMjU2MTY@._V1_.jpg","8.2","a");
                dao.insertAll(movie11);
                Movie movie12 = new Movie("Toy Story","1995","1h 21m","https://m.media-amazon.com/images/M/MV5BMDU2ZWJlMjktMTRhMy00ZTA5LWEzNDgtYmNmZTEwZTViZWJkXkEyXkFqcGdeQXVyNDQ2OTk4MzI@._V1_.jpg","8.3","a");
                dao.insertAll(movie12);
                Movie movie13 = new Movie("Joker","2019","2h 2m","https://m.media-amazon.com/images/M/MV5BNGVjNWI4ZGUtNzE0MS00YTJmLWE0ZDctN2ZiYTk2YmI3NTYyXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_.jpg","7.7","a");
                dao.insertAll(movie13);
            });
        }
    };

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "movie_database")
                            //.fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}

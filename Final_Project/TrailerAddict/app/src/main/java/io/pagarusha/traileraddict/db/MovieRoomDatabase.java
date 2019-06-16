package io.pagarusha.traileraddict.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import io.pagarusha.traileraddict.db.dao.MovieDao;
import io.pagarusha.traileraddict.db.entity.Movie;
import io.pagarusha.traileraddict.db.entity.MovieGist;
import io.pagarusha.traileraddict.db.entity.Person;
import io.pagarusha.traileraddict.db.entity.PersonGist;


@Database(entities = {Movie.class, Person.class, PersonGist.class, MovieGist.class}, version = 1, exportSchema = false)
public abstract class MovieRoomDatabase extends RoomDatabase {


    public abstract MovieDao movieDao();

    private static volatile MovieRoomDatabase INSTANCE;

    public static MovieRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MovieRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MovieRoomDatabase.class, "movie_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final String TAG = PopulateDbAsync.class.getName();
        private final MovieDao mDao;

        PopulateDbAsync(MovieRoomDatabase db) {
            mDao = db.movieDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {

            return null;
        }
    }
}

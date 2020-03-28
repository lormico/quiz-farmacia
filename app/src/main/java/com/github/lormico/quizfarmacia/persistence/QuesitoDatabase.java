package com.github.lormico.quizfarmacia.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Quesito.class}, version = 1, exportSchema = false)
public abstract class QuesitoDatabase extends RoomDatabase {
    public abstract QuesitoDAO quesitoDAO();

    private static volatile QuesitoDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 1;
    // TODO a che cavolo mi serve????
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static QuesitoDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (QuesitoDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            QuesitoDatabase.class, "Questions.db")
                            .createFromAsset("databases/quesiti.sqlite")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

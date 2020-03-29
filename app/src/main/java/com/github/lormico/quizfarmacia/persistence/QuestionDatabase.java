package com.github.lormico.quizfarmacia.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Question.class}, version = 1, exportSchema = false)
public abstract class QuestionDatabase extends RoomDatabase {
    public abstract QuestionDAO questionDAO();

    private static volatile QuestionDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 1;
    // TODO a che cavolo mi serve????
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static QuestionDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (QuestionDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            QuestionDatabase.class, "Questions.db")
                            .createFromAsset("databases/quesiti.sqlite")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

package com.example.viewpager;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

    private static WordRoomDatabase sInstance;

    public abstract WordDao wordDao();

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    private static volatile WordRoomDatabase Instance;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static WordRoomDatabase getDatabase(final Context context,
                                        final AppExecutors executors){
        if (Instance == null){
            synchronized (WordRoomDatabase.class){
                if(Instance == null){
                    Instance = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return Instance;
    }
    public void setDatabaseCreated() { mIsDatabaseCreated.postValue(true);}

    public LiveData<Boolean> getDatabaseCreated(){ return mIsDatabaseCreated; }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                WordDao dao = Instance.wordDao();
                dao.deleteAll();

                Word word = new Word(1,"幸弘","ああ","希望");
                dao.insert(word);
                word = new Word(2,"博之","いい","激励");
                dao.insert(word);
            });
        }
    };
}
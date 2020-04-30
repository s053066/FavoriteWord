package com.example.viewpager;

import android.app.Application;

public class BasicApp extends Application {
    private AppExecutors mAppExecutors;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppExecutors = new AppExecutors();
    }
    public WordRoomDatabase getDatabase() { return WordRoomDatabase.getDatabase(this, mAppExecutors); }

    public WordRepository getRepository() { return WordRepository.getInstance(getDatabase());}
}

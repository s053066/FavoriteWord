package com.example.viewpager;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.room.RoomDatabase;

import com.example.viewpager.Word;
import com.example.viewpager.WordDao;

import java.util.List;

public class WordRepository {

    private MediatorLiveData<List<Word>> mObservableWords;

    private static WordRepository sInstance;
    private final WordRoomDatabase mDatabase;

    private WordRepository(final WordRoomDatabase database){
        mDatabase = database;
        mObservableWords = new MediatorLiveData<>();

        mObservableWords.addSource(mDatabase.wordDao().loadAllWords(),
                word -> {
                    if(mDatabase.getDatabaseCreated().getValue() != null){
                        mObservableWords.postValue(word);
                    }
                });
    }

    LiveData<List<Word>> getWords(){
        return mObservableWords;
    }

    public LiveData<Word> loadWord(final int wordId){
        return mDatabase.wordDao().loadWord(wordId);
    }

    public static WordRepository getInstance(final WordRoomDatabase database){
        if(sInstance == null){
            synchronized (WordRepository.class){
                if(sInstance == null){
                    sInstance = new WordRepository(database);
                }
            }
        }
        return sInstance;
    }

    public LiveData<List<Word>> searchWords(String query){
        // return mDatabase.wordDao().searchAllWords(query);
        return mDatabase.wordDao().loadAllWords();
    }


}

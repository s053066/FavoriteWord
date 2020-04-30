package com.example.viewpager;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private final int mWordId;

    private WordRepository mRepository;

    private LiveData<Word> mObservableWord;

    public WordViewModel (@NonNull Application application, WordRepository repository, final int wordId) {
        super(application);
        mWordId = wordId;

        mObservableWord = repository.loadWord(mWordId);
    }

    LiveData<Word> getWord() { return mObservableWord; }


}


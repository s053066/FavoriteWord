package com.example.viewpager;

import android.app.Application;
import android.text.TextUtils;
import android.view.TextureView;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;

import java.util.List;

public class WordListViewModel extends AndroidViewModel {

    private final SavedStateHandle mSavedStateHandler;
    private final WordRepository mRepository;
    private final LiveData<List<Word>> mWords;


    public WordListViewModel(@NonNull Application application, @NonNull SavedStateHandle savedStateHandle) {
        super(application);
        mSavedStateHandler = savedStateHandle;

        mRepository =((BasicApp)application).getRepository();

        mWords = Transformations.switchMap(
                savedStateHandle.getLiveData("QUERY", null),
                (Function<CharSequence, LiveData<List<Word>>>) query -> {
                    if(TextUtils.isEmpty(query)){
                        return mRepository.getWords();
                    }
                    return mRepository.searchWords("*" + query + "*");
        });
    }

    public LiveData<List<Word>> getWords(){ return mWords;}
}

package com.example.viewpager;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WordFragment extends Fragment {

    private WordViewModel mViewModel;

    private static final String KEY_WORD_ID = "word_id";

    public static WordFragment newInstance() {
        return new WordFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.word_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        // TODO: Use the ViewModel
    }
    public static WordFragment forWord(int wordId){
        WordFragment fragment = new WordFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_WORD_ID, wordId);
        fragment.setArguments(args);
        return fragment;
    }
}

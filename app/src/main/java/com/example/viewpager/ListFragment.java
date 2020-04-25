package com.example.viewpager;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {
    private View mView;
    private RecyclerView mRecyclerView = null;
    private RvAdapter mRvAdapter = null;

    private static final String ARG_COUNT = "param1";
    private Integer counter;
    private int[] COLOR_MAP = {
            R.color.red_100, R.color.red_300, R.color.red_500, R.color.red_700, R.color.blue_100,
            R.color.blue_300, R.color.blue_500, R.color.blue_700, R.color.green_100, R.color.green_300,
            R.color.green_500, R.color.green_700
    };
    public ListFragment() {
        // Required empty public constructor
    }
    public static ListFragment newInstance(Integer counter) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COUNT, counter);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            counter = getArguments().getInt(ARG_COUNT);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_list, container, false);
    }
    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //view.setBackgroundColor(ContextCompat.getColor(getContext(), COLOR_MAP[counter]));
        // フラグメントの中の設定
        // recyclerviewの中もここで設定
        WordRepository wordRepository = new WordRepository(getActivity().getApplication());
        // テストデータを利用ここから
        ArrayList<Word> mData = new ArrayList<Word>();

        // RvAdapter myAdapter = new RvAdapter(wordRepository.getAllWords());

        ArrayList data = (ArrayList)wordRepository.getAllWords();

        RvAdapter myAdapter = new RvAdapter(GetData(counter, wordRepository.getAllWords()));

        RecyclerView recyclerView = view.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplication()));
        recyclerView.setAdapter(myAdapter);

        // TextView textViewCounter = view.findViewById(R.id.tv_counter);
        // textViewCounter.setText("Fragment No " + (counter+1));
    }
    private ArrayList<Word> GetData(int position, ArrayList<Word> mData){
        // データを取得
        ArrayList<Word> data = new ArrayList<Word>();
        for(Word w : mData){
            if(position == 0 && w.getWordTag() == "勇気"){
                data.add(w);
            }
            else if(position == 1 && w.getWordTag() == "希望"){
                data.add(w);
            }
            else if(position == 2 && w.getWordTag() == "怒り"){
                data.add(w);
            }
            else if(position == 3 && w.getWordTag() == "激励") {
                data.add(w);
            }
            // お気に入り
            //else if(position == 4){
            //    if(お気に入りフラグ== true){
            //        data.add(w);
            //    }
            //}
        }
        return data;
    }

}

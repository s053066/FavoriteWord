package com.example.viewpager;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.viewpager.databinding.FragmentListBinding;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {
    private WordAdapter mWordAdapter = null;
    private FragmentListBinding mBinding;

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
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_list, container, false);

        mWordAdapter = new WordAdapter(mWordClickCallback);
        mBinding.wordsList.setAdapter(mWordAdapter);

        return mBinding.getRoot();
    }
    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final WordListViewModel viewModel = new ViewModelProvider(this).get(WordListViewModel.class);

        // 検索処理
        //mBinding.productsSearchBtn.setOnClickListener(v -> {
        //    Editable query = mBinding.productsSearchBox.getText();
        //    viewModel.setQuery(query);
        //});

        subscribeUi(viewModel.getWords());
    }

    private void subscribeUi(LiveData<List<Word>> liveData){
        liveData.observe(getViewLifecycleOwner(), myWords ->{
           if(myWords != null) {
               // ロード中
               // mBinding.setIsLoading(false);
               mWordAdapter.setWordList(myWords);
           }
           else{
               // ロード中
               // mBinding.setIsLoading(true);
           }

           mBinding.executePendingBindings();
        });
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
    private final WordClickCallback mWordClickCallback = word -> {
        if(getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)){
            ((MainActivity)requireActivity()).show(word);
        }
    };
}

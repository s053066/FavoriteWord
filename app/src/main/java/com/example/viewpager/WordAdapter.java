package com.example.viewpager;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {
    private Context context;
    private List<? extends  Word> mWordList;

    @Nullable
    private final WordClickCallback mWordClickCallback;

    public WordAdapter(@Nullable WordClickCallback clickCallback){
        mWordClickCallback = clickCallback;
    }

    public void setWordList(final List<? extends  Word> wordList){
        if(mWordList == null){
            mWordList = wordList;
            notifyItemRangeChanged(0, wordList.size());
        }
        else{
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mWordList.size();
                }

                @Override
                public int getNewListSize() {
                    return wordList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mWordList.get(oldItemPosition).getWordId() == wordList.get(newItemPosition).getWordId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Word newWord = wordList.get(newItemPosition);
                    Word oldWord = wordList.get(oldItemPosition);
                    return newWord.getWordId() == oldWord.getWordId()
                            && TextUtils.equals(newWord.getWorderName(), oldWord.getWorderName())
                            && TextUtils.equals(newWord.getWorderContents(), oldWord.getWorderContents())
                            && TextUtils.equals(newWord.getWordTag(), oldWord.getWordTag());
                }
            });
            mWordList = wordList;
            result.dispatchUpdatesTo(this);
        }
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_row_item, parent, false);
        WordViewHolder wordViewHolder = new WordViewHolder(itemView);


        // アイテムを押された時の処理

        return wordViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        holder.worder_contents.setText(mWordList.get(position).getWorderContents());
        holder.worder_name.setText(holder.getAdapterPosition());
    }

    @Override
    public int getItemCount() {
        return mWordList == null ? 0 : mWordList.size();
    }

    public static class WordViewHolder extends RecyclerView.ViewHolder{

        TextView worder_name,worder_contents;
        LinearLayout view_container;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);

            view_container = itemView.findViewById(R.id.container);
            worder_name = itemView.findViewById(R.id.worderName);
            worder_contents  = itemView.findViewById(R.id.worderContents);
        }
    }

}

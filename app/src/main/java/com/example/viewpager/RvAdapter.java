package com.example.viewpager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.WordViewHolder> {
    private Context context;
    private List<Word> mData;

    public RvAdapter(ArrayList<Word> mData){
        this.mData = mData;
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
        holder.worder_contents.setText(mData.get(position).getWorderContents());
        holder.worder_name.setText(holder.getAdapterPosition());
    }

    @Override
    public int getItemCount() {
        return mData.size();
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

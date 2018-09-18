package com.example.pangling.project4.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pangling.project4.R;
import com.example.pangling.project4.adapter.viewholder.SearchViewholder;
import com.example.pangling.project4.model.Items;

import java.util.ArrayList;

public class AdapterSearch extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<Items> data;

    public AdapterSearch(Context context, ArrayList<Items> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_movie, parent, false);
        return new SearchViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof SearchViewholder){
            ((SearchViewholder)holder).setData(data.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

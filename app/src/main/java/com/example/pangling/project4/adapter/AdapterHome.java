package com.example.pangling.project4.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pangling.project4.R;
import com.example.pangling.project4.adapter.viewholder.HomeViewholder;
import com.example.pangling.project4.model.Items;

import java.util.ArrayList;

public class AdapterHome extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<Items> data;
    Context context;

    public AdapterHome(ArrayList<Items> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_movie, parent, false);
        return new HomeViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof HomeViewholder){
            ((HomeViewholder)holder).setData(data.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

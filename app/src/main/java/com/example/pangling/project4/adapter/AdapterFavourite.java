package com.example.pangling.project4.adapter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pangling.project4.CustomOnItemClickListener;
import com.example.pangling.project4.DetailFavouriteActivity;
import com.example.pangling.project4.R;
import com.example.pangling.project4.model.Movies;
import com.squareup.picasso.Picasso;

import static com.example.pangling.project4.db.DatabaseContract.CONTENT_URI;

public class AdapterFavourite extends RecyclerView.Adapter<AdapterFavourite.MovieViewholder> {

    private Cursor listNotes;
    private Activity activity;

    public AdapterFavourite(Activity activity) {
        this.activity = activity;
    }

    public Cursor getListNotes() {
        return listNotes;
    }

    public void setListNotes(Cursor listNotes) {
        this.listNotes = listNotes;
    }



    @NonNull
    @Override
    public MovieViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_movie, parent, false);
        return new MovieViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewholder holder, int position) {
        final Movies movies = getItem(position);
        holder.judul.setText(movies.getTitle());
        holder.deskripsi.setText(movies.getDescription());
        holder.tgl.setText(movies.getDate());
        if(movies.getDescription().length() > 15){
            holder.deskripsi.setText(movies.getDescription().substring(0,17)+"...");
        }else {
            holder.deskripsi.setText(movies.getDescription());
        }
        Picasso.with(activity).load("http://image.tmdb.org/t/p/w500"+movies.getImage()).into(holder.imageView);
        holder.lihat.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(activity, DetailFavouriteActivity.class);
                Uri uri = Uri.parse(CONTENT_URI+"/"+movies.getId());
                intent.setData(uri);
                activity.startActivityForResult(intent, 0);
                //activity.startActivity(intent);
            }
        }));
    }

    @Override
    public int getItemCount() {
        if (listNotes == null) return 0;
        return listNotes.getCount();
    }

    private Movies getItem(int position){
        if (!listNotes.moveToPosition(position)) {
            throw new IllegalStateException("Position invalid");
        }
        return new Movies(listNotes);
    }

    public class MovieViewholder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView judul, deskripsi, tgl;
        Button lihat;

        public MovieViewholder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgNowPlaying);
            judul = itemView.findViewById(R.id.tvJudulNowPlaying);
            deskripsi = itemView.findViewById(R.id.tvDesNowPlaying);
            tgl = itemView.findViewById(R.id.tvTglNowPlaying);
            lihat = itemView.findViewById(R.id.btndetailNowPlaying);
        }
    }
}

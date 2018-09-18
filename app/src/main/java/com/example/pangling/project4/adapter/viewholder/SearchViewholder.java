package com.example.pangling.project4.adapter.viewholder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pangling.project4.DetailActivity;
import com.example.pangling.project4.R;
import com.example.pangling.project4.model.Items;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SearchViewholder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView judul, deskripsi, tgl;
    Button lihat;
    Items items;
    public SearchViewholder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imgNowPlaying);
        judul = itemView.findViewById(R.id.tvJudulNowPlaying);
        deskripsi = itemView.findViewById(R.id.tvDesNowPlaying);
        tgl = itemView.findViewById(R.id.tvTglNowPlaying);
        lihat = itemView.findViewById(R.id.btndetailNowPlaying);

        onClick();
    }

    private void onClick(){
        lihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(itemView.getContext(), DetailActivity.class);
                i.putExtra("datas", items);
                itemView.getContext().startActivity(i);
            }
        });
    }

    public void setData(Items data){
        try {
            if(data.getOverview().length() > 15){
                deskripsi.setText(data.getOverview().substring(0,17)+"...");
            }else {
                deskripsi.setText(data.getOverview());
            }
            Picasso.with(itemView.getContext()).load("http://image.tmdb.org/t/p/w500"+data.getPosterPath()).into(imageView);
            judul.setText(data.getTitle());
            Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(data.getRilisDate());
            tgl.setText(new SimpleDateFormat("EEE , MMMM d, y").format(date1));
            items = data;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

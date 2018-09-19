package com.example.moviesapp;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviesapp.entity.Movies;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailActivity extends AppCompatActivity {

    ImageView img;
    TextView judul, tgl, overview;
    Movies movies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        img = findViewById(R.id.imgDetailFav);
        judul = findViewById(R.id.tvJudulDetailFav);
        tgl = findViewById(R.id.tvTglDetailFav);
        overview = findViewById(R.id.tvOverviewDetailFav);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail");

        Uri uri = getIntent().getData();
        if(uri != null){
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null){
                if(cursor.moveToFirst()) movies = new Movies(cursor);
                cursor.close();
            }
        }

        if(movies != null){
            try {
                judul.setText(movies.getTitle());
                Date date1= null;
                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(movies.getDate());
                tgl.setText(new SimpleDateFormat("EEE , MMMM d, y").format(date1));
                overview.setText(movies.getDescription());
                Picasso.with(this).load("http://image.tmdb.org/t/p/w500"+movies.getImage()).into(img);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_delete:
                getContentResolver().delete(getIntent().getData(), null, null);
                Intent i = new Intent(DetailActivity.this, MainActivity.class);
                setResult(1, i);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

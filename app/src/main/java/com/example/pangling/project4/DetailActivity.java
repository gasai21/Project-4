package com.example.pangling.project4;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pangling.project4.db.MovieHelper;
import com.example.pangling.project4.model.Items;
import com.example.pangling.project4.model.Movies;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.pangling.project4.db.DatabaseContract.CONTENT_URI;
import static com.example.pangling.project4.db.DatabaseContract.MovieColumns.DATE;
import static com.example.pangling.project4.db.DatabaseContract.MovieColumns.DESCRIPTION;
import static com.example.pangling.project4.db.DatabaseContract.MovieColumns.IMAGE;
import static com.example.pangling.project4.db.DatabaseContract.MovieColumns.TITLE;

public class DetailActivity extends AppCompatActivity {

    ImageView img, star;
    TextView judul, tgl, overview, popularity;
    private Movies movies;
    //private int position;
    Boolean suggestionFavourite = true;
    private MovieHelper moviesHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail");

        img = findViewById(R.id.imgDetail);
        judul = findViewById(R.id.tvJudulDetail);
        tgl = findViewById(R.id.tvTglDetail);
        overview = findViewById(R.id.tvOverviewDetail);
        popularity = findViewById(R.id.tvPopularityDetail);
        star = findViewById(R.id.starFavourite);
        getData();
        addFavourite();

        moviesHelper = new MovieHelper(this);
        moviesHelper.open();
    }

    private void addFavourite(){
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (suggestionFavourite) {
                    Intent i = getIntent();
                    Items d = (Items) i.getSerializableExtra("datas");

                    ContentValues values = new ContentValues();
                    values.put(TITLE, d.getTitle());
                    values.put(DESCRIPTION, d.getOverview());
                    values.put(DATE, d.getRilisDate());
                    values.put(IMAGE, d.getPosterPath());
                    getContentResolver().insert(CONTENT_URI, values);
                    Toast.makeText(getApplicationContext(), "Berhasil!", Toast.LENGTH_SHORT).show();
                    star.setImageResource(R.drawable.ic_star_on);
                    suggestionFavourite = false;
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void getData(){
        Intent i = getIntent();
        Items d = (Items) i.getSerializableExtra("datas");
        Log.d("ggg", d.getTitle());

        try {
            judul.setText(d.getTitle());
            Date date1= null;
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(d.getRilisDate());
            tgl.setText(new SimpleDateFormat("EEE , MMMM d, y").format(date1));
            overview.setText(d.getOverview());
            popularity.setText(d.getPopularity());
            Picasso.with(this).load("http://image.tmdb.org/t/p/w500"+d.getPosterPath()).into(img);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}

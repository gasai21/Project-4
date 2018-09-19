package com.example.moviesapp;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.moviesapp.adapter.AdapterMain;

import static com.example.moviesapp.db.DatabaseContract.CONTENT_URI;

public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor>{

    RecyclerView rvFavourite;
    private Cursor list;
    private AdapterMain adapter;
    private final int LOAD_NOTES_ID = 110;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvFavourite = findViewById(R.id.rvMain);
        rvFavourite.setLayoutManager(new LinearLayoutManager(this));
        rvFavourite.setHasFixedSize(true);

        adapter = new AdapterMain(this);
        adapter.setListNotes(list);
        rvFavourite.setAdapter(adapter);

        //getSupportLoaderManager().initLoader(LOAD_NOTES_ID, null, this);
        getLoaderManager().initLoader(LOAD_NOTES_ID, null, this);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this, CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        list = cursor;
        adapter.setListNotes(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}

package com.example.pangling.project4.fragment;


import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pangling.project4.R;
import com.example.pangling.project4.adapter.AdapterFavourite;

import static com.example.pangling.project4.db.DatabaseContract.CONTENT_URI;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavouriteFragment extends Fragment {


    public FavouriteFragment() {
        // Required empty public constructor
    }

    RecyclerView rvFavourite;
    private Cursor list;
    private AdapterFavourite adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_favourite, container, false);
        rvFavourite = v.findViewById(R.id.rvFavourite);
        rvFavourite.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFavourite.setHasFixedSize(true);

        adapter = new AdapterFavourite(getActivity());
        adapter.setListNotes(list);
        rvFavourite.setAdapter(adapter);

        new LoadMovieAsync().execute();
        return v;
    }

    @Override
    public void onResume() {
        //adapter.notifyDataSetChanged();
        new LoadMovieAsync().execute();
        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0 && resultCode == 1){
            Toast.makeText(getContext(), "Dapet", Toast.LENGTH_SHORT).show();
        }
    }

    private class LoadMovieAsync extends AsyncTask<Void, Void, Cursor> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            return getContext().getContentResolver().query(CONTENT_URI,null,null,null,null);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            list = cursor;
            adapter.setListNotes(list);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

package com.example.pangling.project4.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pangling.project4.MyInterface;
import com.example.pangling.project4.R;
import com.example.pangling.project4.adapter.AdapterSearch;
import com.example.pangling.project4.helper.Constans;
import com.example.pangling.project4.model.Items;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {


    public SearchFragment() {
        // Required empty public constructor
    }

    AdapterSearch adapter;
    ArrayList<Items> data = new ArrayList<>();
    RecyclerView recyclerView;
    RequestQueue requestQueue;
    StringRequest stringRequest;
    String link = "https://api.themoviedb.org/3/movie/now_playing?api_key="+ Constans.API_KEY+"&language=en-US";
    SearchView searchView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = v.findViewById(R.id.rvSearch);
        requestQueue = Volley.newRequestQueue(getContext());
        searchView = v.findViewById(R.id.svSearch);
        searchView.setBackgroundColor(getResources().getColor(R.color.putih));
        setAdapter();
        doSearch();
        new LoadMovieAsyc().execute();
        return v;
    }

    private void doSearch(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("ccc", query);
                data.clear();
                String link = "https://api.themoviedb.org/3/search/movie?api_key=293dc4ce4df06b15d37a7f0c1d13f716&language=en-US&query="+query;
                getData(link, new MyInterface() {
                    @Override
                    public void Success(String response) {
                        JSONObject obj = null;
                        try {
                            obj = new JSONObject(response);
                            JSONArray result = obj.getJSONArray("results");
                            if(result.length() < 1){
                                data.clear();
                            }else {
                                for (int i = 0; i < result.length(); i++) {
                                    JSONObject datas = result.getJSONObject(i);
                                    String id = datas.getString("id");
                                    String path = datas.getString("poster_path");
                                    String popular = datas.getString("popularity");
                                    String title = datas.getString("title");
                                    Boolean adult = datas.getBoolean("adult");
                                    String overview = datas.getString("overview");
                                    String rilis = datas.getString("release_date");
                                    String vote = datas.getString("vote_count");
                                    String average = datas.getString("vote_average");
                                    String adults;

                                    if (adult) {
                                        adults = "1";
                                    } else {
                                        adults = "0";
                                    }

                                    data.add(new Items(id, path, popular, title, adults, overview, rilis, vote, average));
                                    Log.d("HHH", title + "Hello");
                                }
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void setAdapter(){
        adapter = new AdapterSearch(getContext(), data);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    public void getData(String url, final MyInterface callback){
        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.Success(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

    private class LoadMovieAsyc extends AsyncTask<Void, Void, ArrayList<Items>>{

        @Override
        protected ArrayList<Items> doInBackground(Void... voids) {
            getData(link, new MyInterface() {
                @Override
                public void Success(String response) {
                    JSONObject obj = null;
                    try {
                        obj = new JSONObject(response);
                        JSONArray result = obj.getJSONArray("results");

                        for(int i=0;i < result.length(); i++){
                            JSONObject datas = result.getJSONObject(i);
                            String id = datas.getString("id");
                            String path = datas.getString("poster_path");
                            String popular = datas.getString("popularity");
                            String title = datas.getString("title");
                            Boolean adult = datas.getBoolean("adult");
                            String overview = datas.getString("overview");
                            String rilis = datas.getString("release_date");
                            String vote = datas.getString("vote_count");
                            String average = datas.getString("vote_average");
                            String adults;

                            if(adult){
                                adults = "1";
                            }else{
                                adults = "0";
                            }

                            data.add(new Items(id,path,popular,title,adults,overview,rilis,vote,average));
                            Log.d("HHH", title+"Hello");
                        }
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            return data;
        }
    }

}

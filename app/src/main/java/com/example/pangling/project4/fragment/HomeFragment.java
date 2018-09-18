package com.example.pangling.project4.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.example.pangling.project4.adapter.AdapterHome;
import com.example.pangling.project4.helper.Constans;
import com.example.pangling.project4.model.Items;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    AdapterHome adapter;
    ArrayList<Items> data = new ArrayList<>();
    RequestQueue requestQueue;
    StringRequest stringRequest;
    String link = "https://api.themoviedb.org/3/movie/now_playing?api_key="+ Constans.API_KEY+"&language=en-US";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = v.findViewById(R.id.rvHome);
        requestQueue = Volley.newRequestQueue(getContext());

        setAdapter();
        //getData(this);
        new LoadMoviesAsync().execute();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void setAdapter(){
        adapter = new AdapterHome(data, getContext());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void getData(final MyInterface myInterface){
        stringRequest = new StringRequest(Request.Method.GET, link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("jjj", response);
                myInterface.Success(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Errlol", error.getMessage());
            }
        });
        requestQueue.add(stringRequest);
    }



    private class LoadMoviesAsync extends AsyncTask<Void, Void, ArrayList<Items>>{

        @Override
        protected ArrayList<Items> doInBackground(Void... voids) {
            getData(new MyInterface() {
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

        @Override
        protected void onPostExecute(ArrayList<Items> items) {
            super.onPostExecute(items);
            adapter.notifyDataSetChanged();
        }
    }

}

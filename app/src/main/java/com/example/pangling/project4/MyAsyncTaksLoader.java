package com.example.pangling.project4;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.example.pangling.project4.helper.Constans;
import com.example.pangling.project4.model.Items;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MyAsyncTaksLoader extends AsyncTaskLoader<ArrayList<Items>> {
    private ArrayList<Items> mData;
    private boolean mHasResult = false;
    private String mKumpulanMovie;

    public MyAsyncTaksLoader(Context context) {
        super(context);
        onContentChanged();
    }

    @Override
    protected void onStartLoading() {
        if(takeContentChanged()){
            forceLoad();
        }else {
            deliverResult(mData);
        }
    }

    @Override
    public void deliverResult(ArrayList<Items> data) {
        mData = data;
        mHasResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if(mHasResult){
            onReleaseResources(mData);
            mData = null;
            mHasResult = false;
        }
    }

    @Override
    public ArrayList<Items> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient();
        final ArrayList<Items> datas = new ArrayList<>();
        String link = "https://api.themoviedb.org/3/movie/now_playing?api_key="+ Constans.API_KEY+"&language=en-US";
        client.get(link, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                setUseSynchronousMode(true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String results = new String(responseBody);
                    JSONObject responseObject = new JSONObject(results);
                    JSONArray list =  responseObject.getJSONArray("results");
                    for(int i = 0; i < list.length(); i++){
                        JSONObject movies = list.getJSONObject(i);
                        String id = movies.getString("id");
                        String path = movies.getString("poster_path");
                        String popular = movies.getString("popularity");
                        String title = movies.getString("title");
                        Boolean adult = movies.getBoolean("adult");
                        String overview = movies.getString("overview");
                        String rilis = movies.getString("release_date");
                        String vote = movies.getString("vote_count");
                        String average = movies.getString("vote_average");
                        String adults;

                        if(adult){
                            adults = "1";
                        }else{
                            adults = "0";
                        }
                        datas.add(new Items(id,path,popular,title,adults,overview,rilis,vote,average));
                        Log.d("lll", rilis+"uuuu");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        return datas;
    }

    protected void onReleaseResources(ArrayList<Items> data) {
        //nothing to do.
    }
}

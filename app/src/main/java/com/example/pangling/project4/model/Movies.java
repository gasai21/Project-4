package com.example.pangling.project4.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.pangling.project4.db.DatabaseContract;

import static android.provider.MediaStore.Audio.Playlists.Members._ID;
import static com.example.pangling.project4.db.DatabaseContract.getColumnInt;
import static com.example.pangling.project4.db.DatabaseContract.getColumnString;

public class Movies implements Parcelable {

    private int id;
    private String title;
    private String description;
    private String date;
    private String image;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.title);
        parcel.writeString(this.description);
        parcel.writeString(this.date);
        parcel.writeString(this.image);
    }

    public Movies(){

    }

    public Movies(Cursor cursor){
        this.id = getColumnInt(cursor, _ID);
        this.title = getColumnString(cursor, DatabaseContract.MovieColumns.TITLE);
        this.description = getColumnString(cursor, DatabaseContract.MovieColumns.DESCRIPTION);
        this.date = getColumnString(cursor, DatabaseContract.MovieColumns.DATE);
        this.image = getColumnString(cursor, DatabaseContract.MovieColumns.IMAGE);
    }

    protected Movies(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        date = in.readString();
        image = in.readString();
    }

    public static final Creator<Movies> CREATOR = new Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel in) {
            return new Movies(in);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };
}

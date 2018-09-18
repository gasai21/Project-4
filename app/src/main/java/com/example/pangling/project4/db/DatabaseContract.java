package com.example.pangling.project4.db;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {
    public static String TABLE_NOTE = "movie";

    public static final class MovieColumns implements BaseColumns {
        //movie title
        public static String TITLE = "title";
        //Movie description
        public static String DESCRIPTION = "description";
        //Movie date
        public static String DATE = "date";
        //Movie Image
        public static String IMAGE = "image";
    }

    public static final String AUTHORITY = "com.example.pangling.project4";
    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(TABLE_NOTE)
            .build();
    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString( cursor.getColumnIndex(columnName) );
    }
    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt( cursor.getColumnIndex(columnName) );
    }
    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong( cursor.getColumnIndex(columnName) );
    }
}

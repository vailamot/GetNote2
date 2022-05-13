package com.doanhtq.getnote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.app.LoaderManager;
import android.content.ContentProviderClient;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    public static final String COLUMN_TITLE_NOTE = "note_title";
    public static final String COLUMN_CONTENT_NOTE = "note_description";

    public static final String AUTHORITY = "com.doanhtq.note.noteprovider";
    public static final String CONTENT_PATH =  "words";
    public static final String URL = "content://" + AUTHORITY + "/" + CONTENT_PATH;
    public static final Uri CONTENT_URI = Uri.parse(URL);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        ContentProviderClient contentProviderClient = getContentResolver().acquireContentProviderClient(CONTENT_URI);
//        Cursor cursor = null;
//        try {
//            cursor = contentProviderClient.query(CONTENT_URI,null,null,null,null);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//            Log.d("DoanhTq", "No query");
//        }

        Cursor cursor = getContentResolver().query(CONTENT_URI, null,
                null,null,null);
        if(cursor != null){
            cursor.moveToFirst();
            do{
                int noteID = cursor.getInt(0);
                String noteTitle = cursor.getString(1);
                String noteDescription = cursor.getString(2);
                Log.d("DoanhTq", noteID + " " + noteTitle + noteDescription);
            }while (cursor.moveToNext());
            cursor.close();
        } else {
            Log.d("DoanhTq", "No cursor");
        }
    }


}
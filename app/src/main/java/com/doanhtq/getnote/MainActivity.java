package com.doanhtq.getnote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    public static final String COLUMN_TITLE_NOTE = "note_title";
    public static final String COLUMN_CONTENT_NOTE = "note_description";

    public static final String AUTHORITY = "com.doanhtq.note.NoteProvider";
    public static final String CONTENT_PATH =  "notes";
    public static final String URL = "content://" + AUTHORITY + "/" + CONTENT_PATH;
    public static final Uri CONTENT_URI = Uri.parse(URL);

    CursorLoader mCursorLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Cursor cursor = getApplicationContext().getContentResolver().query(CONTENT_URI, null,
                null,null,null);
        if(cursor != null){
            do{
                cursor.moveToFirst();
                int noteID = cursor.getInt(0);
                String noteTitle = cursor.getString(1);
                String noteDescription = cursor.getString(2);
                Log.d("DoanhTq", "onCreate: " + noteID + noteTitle + noteDescription);
            }while (cursor.moveToNext());
            cursor.close();
        }


    }


}
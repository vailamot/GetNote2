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
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String COLUMN_TITLE_NOTE = "note_title";
    public static final String COLUMN_CONTENT_NOTE = "note_description";

    public static final String AUTHORITY = "com.doanhtq.note.noteprovider";
    public static final String CONTENT_PATH =  "words";
    public static final String URL = "content://" + AUTHORITY + "/" + CONTENT_PATH;
    public static final Uri CONTENT_URI = Uri.parse(URL);

    private EditText mNoteID;
    private TextView mNoteResult;

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
        mNoteID = findViewById(R.id.et_id);
        mNoteResult = findViewById(R.id.tv_result);

        Cursor cursor = getContentResolver().query(CONTENT_URI, null,
                null,null,null);
        if((cursor!=null) && (cursor.moveToFirst())){
            while (!cursor.isAfterLast()){
                int noteID = cursor.getInt(0);
                String noteTitle = cursor.getString(1);
                String noteDescription = cursor.getString(2);
                Log.d("DoanhTq", " " + noteID + noteTitle + noteDescription);
                cursor.moveToNext();
            }
            cursor.close();
        }else {
            Log.d("DoanhTq", "No cursor");
        }
    }


    public void findNoteById(View view) {
        String url = URL + "/" + mNoteID.getText().toString();
        Cursor cursor = getContentResolver().query(Uri.parse(url), null,
                null,null,null);
        if((cursor!=null) && (cursor.moveToFirst())){
            while (!cursor.isAfterLast()){
                int noteID = cursor.getInt(0);
                String noteTitle = cursor.getString(1);
                String noteDescription = cursor.getString(2);
                String noteResult = noteID + " " + noteTitle + " " + noteDescription;
                Log.d("DoanhTq", " find " + noteResult);
                mNoteResult.setText(noteResult);
                cursor.moveToNext();
            }
            cursor.close();
        }else {
            Log.d("DoanhTq", "No cursor");
        }
    }
}
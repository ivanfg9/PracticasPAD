package com.example.gymsy;

import android.database.Cursor;
import android.os.AsyncTask;
import android.widget.SimpleCursorAdapter;


public class EjerciciosLoadTask extends AsyncTask<Void,Void, Cursor> {

    private BaseDatos bd;
    private SimpleCursorAdapter sca;

    public EjerciciosLoadTask(BaseDatos db, SimpleCursorAdapter cursorAdapter){
        bd = db;
        sca = cursorAdapter;
    }
    @Override
    protected Cursor doInBackground(Void... voids) {
        return bd.getEjercicios();
    }

    @Override
    protected void onPostExecute(Cursor cursor) {
        if (cursor != null && cursor.getCount() > 0) {
            sca.swapCursor(cursor);
        }
    }
}

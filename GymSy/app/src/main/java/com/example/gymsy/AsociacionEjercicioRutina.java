package com.example.gymsy;

import android.content.ContentValues;
import android.database.Cursor;

public class AsociacionEjercicioRutina {
    private int idRutina;
    private int idUsuario;

    public AsociacionEjercicioRutina(int idR, int idU){
        idRutina = idR;
        idUsuario = idU;
    }

    public int getIdRutina() {
        return idRutina;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public AsociacionEjercicioRutina(Cursor cursor) {
        idRutina = cursor.getInt(cursor.getColumnIndex(TablasBD.AsociacionRutinaEntry.ID_RUTINA));
        idUsuario = cursor.getInt(cursor.getColumnIndex(TablasBD.AsociacionRutinaEntry.ID_USUARIO));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();

        values.put(TablasBD.AsociacionRutinaEntry.ID_RUTINA, idRutina);
        values.put(TablasBD.AsociacionRutinaEntry.ID_USUARIO, idUsuario);

        return values;
    }

}

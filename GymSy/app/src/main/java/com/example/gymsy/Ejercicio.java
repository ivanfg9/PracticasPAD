package com.example.gymsy;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.UUID;

public class Ejercicio {
    private String nombreEjercicio;
    private String musculo;
    private String imagenEjercicio;

    public Ejercicio(String nombre, String zonaMuscular, String imagen){
        nombreEjercicio = nombre;
        musculo = zonaMuscular;
        imagenEjercicio = imagen;
    }

    public String getNombreEjercicio() {
        return nombreEjercicio;
    }

    public String getMusculo() {
        return musculo;
    }

    public Ejercicio(Cursor cursor) {
        nombreEjercicio = cursor.getString(cursor.getColumnIndex(TablasBD.EjercicioEntry.NOMBRE));
        musculo = cursor.getString(cursor.getColumnIndex(TablasBD.EjercicioEntry.MUSCULO));
        imagenEjercicio = cursor.getString(cursor.getColumnIndex(TablasBD.EjercicioEntry.IMAGEN_URI));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();

        values.put(TablasBD.EjercicioEntry.NOMBRE, nombreEjercicio);
        values.put(TablasBD.EjercicioEntry.MUSCULO, musculo);
        values.put(TablasBD.EjercicioEntry.IMAGEN_URI, imagenEjercicio);

        return values;
    }
}

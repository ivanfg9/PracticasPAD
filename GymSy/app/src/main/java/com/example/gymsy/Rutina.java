package com.example.gymsy;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.Date;

public class Rutina {
    private String diaEntreno;
    private int idEjercicio;
    private int repeticiones;
    private int pesoLevantado;

    public Rutina(String dia, int id, int repes, int peso){
        diaEntreno = dia;
        idEjercicio = id;
        repeticiones = repes;
        pesoLevantado = peso;
    }

    public int getIdEjercicio() {
        return idEjercicio;
    }

    public String getDiaEntreno() {
        return diaEntreno;
    }

    public int getPesoLevantado() {
        return pesoLevantado;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public Rutina(Cursor cursor) {
        /*
        diaEntreno = cursor.getString(cursor.getColumnIndex(TablasBD.RutinaEntry.DIA_ENTRENO));
        idEjercicio = cursor.getInt(cursor.getColumnIndex(TablasBD.RutinaEntry.ID_EJERCICIO));
        repeticiones = cursor.getInt(cursor.getColumnIndex(TablasBD.RutinaEntry.REPETICIONES));
        pesoLevantado = cursor.getInt(cursor.getColumnIndex(TablasBD.RutinaEntry.PESO_LEVANTADO));

         */
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
/*
        values.put(TablasBD.RutinaEntry.DIA_ENTRENO, diaEntreno);
        values.put(TablasBD.RutinaEntry.ID_EJERCICIO, idEjercicio);
        values.put(TablasBD.RutinaEntry.REPETICIONES, repeticiones);
        values.put(TablasBD.RutinaEntry.PESO_LEVANTADO, pesoLevantado);*/

        return values;
    }
}

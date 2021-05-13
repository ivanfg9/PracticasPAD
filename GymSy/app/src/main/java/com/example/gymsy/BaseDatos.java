package com.example.gymsy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BaseDatos extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "BaseDatos.db";


    public BaseDatos(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TablasBD.EjercicioEntry.NOMBRE_TABLA + " ("
        + TablasBD.EjercicioEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
        + TablasBD.EjercicioEntry.NOMBRE  + " TEXT NOT NULL,"
        + TablasBD.EjercicioEntry.MUSCULO + " TEXT NOT NULL,"
        + TablasBD.EjercicioEntry.IMAGEN_URI + " TEXT)"
        );

        db.execSQL("CREATE TABLE " + TablasBD.RutinaEntry.NOMBRE_TABLA + " ("
                + TablasBD.RutinaEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TablasBD.RutinaEntry.DIA_ENTRENO + " TEXT,"
                + TablasBD.RutinaEntry.ID_EJERCICIO + " INTEGER,"
                + TablasBD.RutinaEntry.REPETICIONES + " INTEGER,"
                + TablasBD.RutinaEntry.PESO_LEVANTADO + " INTEGER)"
        );

        db.execSQL("CREATE TABLE " + TablasBD.UsuarioEntry.NOMBRE_TABLA + " ("
                + TablasBD.RutinaEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TablasBD.UsuarioEntry.NOMBRE_USUARIO + " TEXT,"
                + TablasBD.UsuarioEntry.CONTRASENA  + " TEXT,"
                + TablasBD.UsuarioEntry.ALTURA + " INTEGER,"
                + TablasBD.UsuarioEntry.PESO + " INTEGER,"
                + TablasBD.UsuarioEntry.ETAPA + " TEXT,"
                + TablasBD.UsuarioEntry.FREC_ACTUAL + " TEXT,"
                + TablasBD.UsuarioEntry.FREC_OBJ + " TEXT,"
                + TablasBD.UsuarioEntry.LESION_SI_NO + " TEXT,"
                + TablasBD.UsuarioEntry.ZONA_LESION + " TEXT)");

        db.execSQL("CREATE TABLE " + TablasBD.AsociacionRutinaEntry.NOMBRE_TABLA + " ("
                + TablasBD.EjercicioEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TablasBD.AsociacionRutinaEntry.ID_RUTINA  + " INTEGER,"
                + TablasBD.AsociacionRutinaEntry.ID_USUARIO + " INTEGER)"
        );

        //Valores Ejercicios
        Ejercicio ej = new Ejercicio("Hammer Curl", "Biceps", "hammerCurl.jpg");

        db.insert(TablasBD.EjercicioEntry.NOMBRE_TABLA,null,ej.toContentValues());

        //Valores Usuarios
        Usuario us = new Usuario("ivan","1234",178,75,"Musculacion","ninguna/1 vez/semana",
                "5/mas veces/semana","No","");

        db.insert(TablasBD.UsuarioEntry.NOMBRE_TABLA,null,us.toContentValues());

        //Valores Rutina
        Rutina ru = new Rutina("09-08-2021",Integer.valueOf(TablasBD.UsuarioEntry._ID.toString()),
                10, 15);

        db.insert(TablasBD.RutinaEntry.NOMBRE_TABLA,null,ru.toContentValues());

        //Valores AsociacionEjerciciosRutina
        AsociacionEjercicioRutina aer = new AsociacionEjercicioRutina(Integer.valueOf(TablasBD.RutinaEntry._ID),
                Integer.valueOf(TablasBD.UsuarioEntry._ID.toString()));

        db.insert(TablasBD.AsociacionRutinaEntry.NOMBRE_TABLA,null,aer.toContentValues());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor getEjercicios(){
        return getReadableDatabase().query(
                        TablasBD.EjercicioEntry.NOMBRE_TABLA,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
    }

    public Cursor getRutinas(){
        return getReadableDatabase().query(
                TablasBD.RutinaEntry.NOMBRE_TABLA,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public Cursor getUsuarios(){
        return getReadableDatabase().query(
                TablasBD.UsuarioEntry.NOMBRE_TABLA,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public Cursor getAsociacionRutinaEjercicio(){
        return getReadableDatabase().query(
                TablasBD.AsociacionRutinaEntry.NOMBRE_TABLA,
                null,
                null,
                null,
                null,
                null,
                null);
    }
}
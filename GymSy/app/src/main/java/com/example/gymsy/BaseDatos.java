package com.example.gymsy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class BaseDatos extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "BaseDatos.db";
    private SQLiteDatabase bbdd;

    public BaseDatos(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        bbdd =  this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //this.bbdd =  this.getWritableDatabase();
        this.bbdd.execSQL("CREATE TABLE " + TablasBD.EjercicioEntry.NOMBRE_TABLA + " ("
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
        Log.e("test",ej.toContentValues().toString());
        Log.e("test","hola");
        db.insert(TablasBD.EjercicioEntry.NOMBRE_TABLA,null,ej.toContentValues());

        //Valores Usuarios
        Usuario us = new Usuario("ivan","1234",178,75,"Musculacion","ninguna/1 vez/semana",
                "5/mas veces/semana","No","");

        db.insert(TablasBD.UsuarioEntry.NOMBRE_TABLA,null,us.toContentValues());

        Rutina ru = new Rutina("09-08-2021",Integer.valueOf("1"/*TablasBD.UsuarioEntry._ID*/),
                10, 15);

        db.insert(TablasBD.RutinaEntry.NOMBRE_TABLA,null,ru.toContentValues());

        AsociacionEjercicioRutina aer = new AsociacionEjercicioRutina(Integer.valueOf("1"/*TablasBD.RutinaEntry._ID*/),
                Integer.valueOf("1"/*TablasBD.UsuarioEntry._ID.toString()*/));

        db.insert(TablasBD.AsociacionRutinaEntry.NOMBRE_TABLA,null,aer.toContentValues());
    }

    public void insert(SQLiteDatabase db, String nombreTabla, Object nulo, ContentValues valores){
        db.insert(nombreTabla,null,valores);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS ejercicios");
        db.execSQL("DROP TABLE IF EXISTS rutina");
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        db.execSQL("DROP TABLE IF EXISTS asociacionRutinaEjercicio");

        onCreate(db);
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

    public Cursor getEjercicioPorId(String idEjercicio){
        String buscado[] = {idEjercicio};

        Cursor c = getReadableDatabase().query(
                TablasBD.EjercicioEntry.NOMBRE_TABLA,
                null,
                TablasBD.EjercicioEntry._ID + " LIKE ?",
                buscado,
                null,
                null,
                null);
        return c;
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

    public Cursor getRutinaPorId(String idRutina){
        String buscado[] = {idRutina};

        Cursor c = getReadableDatabase().query(
                TablasBD.RutinaEntry.NOMBRE_TABLA,
                null,
                TablasBD.RutinaEntry._ID + " LIKE ?",
                buscado,
                null,
                null,
                null);
        return c;
    }

    public Cursor getRutinaPorIdEjercicio(String idEjercicio){
        String buscado[] = {idEjercicio};

        Cursor c = getReadableDatabase().query(
                TablasBD.RutinaEntry.NOMBRE_TABLA,
                null,
                TablasBD.RutinaEntry.ID_EJERCICIO + " LIKE ?",
                buscado,
                null,
                null,
                null);
        return c;
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

    public Cursor getUsuarioPorUsername(String username){
        String buscado[] = {username};

        Cursor c = getReadableDatabase().query(
                TablasBD.UsuarioEntry.NOMBRE_TABLA,
                null,
                TablasBD.UsuarioEntry.NOMBRE_USUARIO + " LIKE ?",
                buscado,
                null,
                null,
                null);
        return c;
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

    public Cursor getAsociacionRutinaEjercicioPorId(String idAscociacion){
        String buscado[] = {idAscociacion};

        Cursor c = getReadableDatabase().query(
                TablasBD.AsociacionRutinaEntry.NOMBRE_TABLA,
                null,
                TablasBD.AsociacionRutinaEntry._ID + " LIKE ?",
                buscado,
                null,
                null,
                null);
        return c;
    }
}
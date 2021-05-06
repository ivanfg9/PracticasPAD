package com.example.gymsy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BaseDatos extends SQLiteOpenHelper {

    private static final int BUFFER_SIZE = 2048;
    private Context context;
    private static int version = 1;
    private static String name = "gymsy";
    private static SQLiteDatabase.CursorFactory factory = null;

    public BaseDatos(@Nullable Context context) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Crear tabla de usuarios
        db.execSQL("CREATE TABLE usuarios (idUsuario INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT, password TEXT, altura INTEGER, peso INTEGER, etapa " +
                "TEXT, frecuenciaEjercicioActual TEXT, frecuenciaEjercicioObjetivo " +
                "TEXT, lesionSiNo TINYINT, zonaLesion TEXT, nivel INTEGER, idAsociacionRutina INTEGER," +
                "FOREIGN KEY(idAsociacionRutina) REFERENCES asociacionRutinaEjercicio(" +
                "idAsociacionRutina) ON DELETE CASCADE)");

        //Crear tabla de ejercicios
        db.execSQL("CREATE TABLE ejercicio (idEjercicio INTEGER PRIMARY KEY AUTOINCREMENT," +
                "musculo TEXT, nombreEjercicio TEXT) ");

        //Crear tabla de rutinas
        db.execSQL("CREATE TABLE rutina (idRutina INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombreEjercicio TEXT, diaEntreno INTEGER, series INTEGER," +
                " repeticiones INTEGER, pesoLevantado INTEGER," + " idEjercicio INTEGER)");

        //Crear tabla de asociacionRutinaEjercicio
        db.execSQL("CREATE TABLE asociacionRutinaEjercicio (idAsociacionRutina INTEGER" +
                " PRIMARY KEY AUTOINCREMENT, idRutina INTEGER, idUsuario INTEGER, " +
                "FOREIGN KEY(idRutina) REFERENCES rutina(idRutina), " +
                "FOREIGN KEY(idUsuario) REFERENCES usuario(idUsuario))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Borrar base de datos anticuadas
        db.execSQL("DROP TABLE IF EXISTS usuario");
        db.execSQL("DROP TABLE IF EXISTS ejercicio");
        db.execSQL("DROP TABLE IF EXISTS rutina");
        db.execSQL("DROP TABLE IF EXISTS asociacionRutinaEjercicio");

        //Actualizarlas
        onCreate(db);
    }

    public SQLiteDatabase onCreateDataBase(){
        return getWritableDatabase();
    }
}

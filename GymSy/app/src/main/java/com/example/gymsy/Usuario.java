package com.example.gymsy;

import android.content.ContentValues;
import android.database.Cursor;

public class Usuario {
    private String nombreUsuario;
    private String contrasena;
    private int altura;
    private int peso;
    private String etapa;
    private String frecActual;
    private String frecObj;
    private String lesion;
    private String zonaLesion;

    public Usuario(String nombre, String contrasenia, int alt, int pes,
                   String et, String fa, String fo, String les, String zl){
        nombreUsuario = nombre;
        contrasena = contrasenia;
        altura = alt;
        peso = pes;
        etapa = et;
        frecActual = fa;
        frecObj = fo;
        lesion = les;
        zonaLesion = zl;
    }

    public int getAltura() {
        return altura;
    }

    public int getPeso() {
        return peso;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getEtapa() {
        return etapa;
    }

    public String getFrecActual() {
        return frecActual;
    }

    public String getFrecObj() {
        return frecObj;
    }

    public String getLesion() {
        return lesion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getZonaLesion() {
        return zonaLesion;
    }

    public Usuario(Cursor cursor) {
        nombreUsuario = cursor.getString(cursor.getColumnIndex(TablasBD.UsuarioEntry.NOMBRE_USUARIO));
        contrasena = cursor.getString(cursor.getColumnIndex(TablasBD.UsuarioEntry.CONTRASENA));
        altura = cursor.getInt(cursor.getColumnIndex(TablasBD.UsuarioEntry.ALTURA));
        peso = cursor.getInt(cursor.getColumnIndex(TablasBD.UsuarioEntry.PESO));
        etapa = cursor.getString(cursor.getColumnIndex(TablasBD.UsuarioEntry.ETAPA));
        frecActual = cursor.getString(cursor.getColumnIndex(TablasBD.UsuarioEntry.FREC_ACTUAL));
        frecObj = cursor.getString(cursor.getColumnIndex(TablasBD.UsuarioEntry.FREC_OBJ));
        lesion = cursor.getString(cursor.getColumnIndex(TablasBD.UsuarioEntry.LESION_SI_NO));
        zonaLesion = cursor.getString(cursor.getColumnIndex(TablasBD.UsuarioEntry.ZONA_LESION));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();

        values.put(TablasBD.UsuarioEntry.NOMBRE_USUARIO, "ivan");
        values.put(TablasBD.UsuarioEntry.CONTRASENA, "1234");
        values.put(TablasBD.UsuarioEntry.ALTURA, "178");
        values.put(TablasBD.UsuarioEntry.PESO, "75");
        values.put(TablasBD.UsuarioEntry.ETAPA, "Musculacion");
        values.put(TablasBD.UsuarioEntry.FREC_ACTUAL, "ninguna/1 vez/semana");
        values.put(TablasBD.UsuarioEntry.FREC_OBJ, "5/mas veces/semana");
        values.put(TablasBD.UsuarioEntry.LESION_SI_NO, "No");
        values.put(TablasBD.UsuarioEntry.ZONA_LESION, "");

        return values;
    }
}

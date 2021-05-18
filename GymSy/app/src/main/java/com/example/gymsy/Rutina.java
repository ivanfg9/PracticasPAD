package com.example.gymsy;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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


/*




    private int id;
    //el numero de dias de entrenamiento
    private int dias;
    private List<Ejercicio> listaEjercicios;
    //el nivel va a determinar el numero de ejercicios por sesion
    private int nivel;
    private int repeticiones;
    private int pesoLevantado;
    private TipoEntrenamiento tipo;

    public Rutina(TipoEntrenamiento tipo, int dias, int nivel, int id, int repes, int peso, List<Ejercicio> listaEjercicios){
        this.id=id;
        this.dias = dias;
        this.repeticiones = repes;
        this.pesoLevantado = peso;
        this.listaEjercicios=listaEjercicios;
        this.tipo=tipo;
    }


    public Rutina(Cursor cursor) {

        diaEntreno = cursor.getString(cursor.getColumnIndex(TablasBD.RutinaEntry.DIA_ENTRENO));
        idEjercicio = cursor.getInt(cursor.getColumnIndex(TablasBD.RutinaEntry.ID_EJERCICIO));
        repeticiones = cursor.getInt(cursor.getColumnIndex(TablasBD.RutinaEntry.REPETICIONES));
        pesoLevantado = cursor.getInt(cursor.getColumnIndex(TablasBD.RutinaEntry.PESO_LEVANTADO));


    }

public ContentValues toContentValues() {
        ContentValues values = new ContentValues();

        values.put(TablasBD.RutinaEntry.DIA_ENTRENO, diaEntreno);
        values.put(TablasBD.RutinaEntry.ID_EJERCICIO, idEjercicio);
        values.put(TablasBD.RutinaEntry.REPETICIONES, repeticiones);
        values.put(TablasBD.RutinaEntry.PESO_LEVANTADO, pesoLevantado);

        return values;
        }

public List<Pair<DiaSemana,List<Ejercicio>>> rutina(){
        List<Pair<DiaSemana,List<Ejercicio>>> vc= new ArrayList<>();
        List<Ejercicio> aux;
        if(tipo==TipoEntrenamiento.FULLBODY){

        vc=elegirEntrenamientoFullBody(dias,nivel);

        }
        return vc;

        }

public List<Pair<DiaSemana, List<Ejercicio>>> elegirEntrenamientoFullBody(int dias, int nivel) {
        List<Pair<DiaSemana,List<Ejercicio>>> vc= new ArrayList<Pair<DiaSemana,List<Ejercicio>>>();

        DiaSemana [] diasSemana= devolverDiasEntrenamiento(dias);





        return null;

        }

public List<Ejercicio> devolverRutina(int nivel, int numDias){

        return null;
        }




public DiaSemana[] devolverDiasEntrenamiento(int dias){
        int [] array = arrayAleatorio(dias);

        array=ordenaArray(array);
        DiaSemana [] diasEntrenamiento= new DiaSemana [dias];


        for(int i=0;i<array.length;i++){
        switch(array[i]){

        case 0:
        diasEntrenamiento[i]=DiaSemana.Lunes;
        break;
        case 1:
        diasEntrenamiento[i]=DiaSemana.Martes;
        break;
        case 2:
        diasEntrenamiento[i]=DiaSemana.Miercoles;
        break;
        case 3:
        diasEntrenamiento[i]=DiaSemana.Jueves;
        break;
        case 4:
        diasEntrenamiento[i]=DiaSemana.Viernes;
        break;
        case 5:
        diasEntrenamiento[i]=DiaSemana.Sabado;
        break;
        case 6:
        diasEntrenamiento[i]=DiaSemana.Domingo;
        break;


        }
        }



        return diasEntrenamiento;
        }



public int [] arrayAleatorio(int numero){


        int a=-1;
        boolean p=false;
        int [] array= new int [numero];
        int contador=0;

        for(int i=0; i<array.length;i++) {

        while(p==false) {
        a=(int) (Math.random()*numero + 1);

        if(numeroDistinto(array,a)==false) {
        p=true;
        array[i]=a;
        }


        }

        p=false;

        }
        array=ordenaArray(array);

        return array;

        }



public boolean numeroDistinto(int [] array, int numero) {
        boolean a=false;
        for(int i=0;i<array.length; i++) {
        if(array[i]==numero) {
        a=true;
        }
        }
        return a;
        }


public  int [] ordenaArray(int [] desorden) {
        int [] orden= new int [desorden.length];
        int aux;
        int contador=0;
        for(int i=0;i<desorden.length;i++) {
        aux=desorden[i];
        for(int j=0;j<desorden.length;j++) {

        if(aux>desorden[j]) {
        contador++;
        }
        }
        orden[contador]=aux;
        contador=0;

        }

        return orden;

        }





        */
package com.example.gymsy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gymsy.connection.PostData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;


public class EjercicioActual extends AppCompatActivity {

    private ImageView imagen;
    private TextView repeticiones;
    private TextView _musculo;
    private TextView descripcion;

    private Button start;
    private Button terminar;
    private Button before;
    private Button next;
    private Chronometer cronometro;

    private int id_ejercicio;
    private int totalRepeticiones = 0;
    private int numTotalEjercicios;
    private long tiempoCrono;
    private boolean estadoPlay;
    private boolean estadoComenzar;
    private static final int REQUEST_SHOW_EJERCICIOS = 2;
    private String data = "";
    private String url = "http://35.180.41.33/ejercicios/id/";

    public EjercicioActual(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ejercicio_actual);

        // Obtienes los datos de la clase que fue invocado
        Bundle datosIntent = getIntent().getExtras();

        id_ejercicio = datosIntent.getInt("extra_ejercicio_id") + 1;

        imagen  = findViewById(R.id.imageEjercicio);
        before = findViewById(R.id._before);
        next = findViewById(R.id._after);
        start = findViewById(R.id._start);
        terminar = findViewById(R.id._finish);
        cronometro = findViewById(R.id._cronometro);
        repeticiones = findViewById(R.id._repeticiones);
        _musculo = findViewById(R.id._musculo);
        descripcion = findViewById(R.id._descripcionMusculo);

        constructView();

        /* Guarda el estado en el que se encuentren los elementos si cambia la orientacion del
        *  movil
        */
        if(savedInstanceState != null){
            estadoComenzar = savedInstanceState.getBoolean("pulsado_comenzar");
            estadoPlay = savedInstanceState.getBoolean("pulsado_play");

            if(estadoComenzar){
                tiempoCrono = savedInstanceState.getLong("crono");
                cronometro.setBase(SystemClock.elapsedRealtime() - tiempoCrono);
                cronometro.start();
            }
            else if(estadoPlay){
                tiempoCrono = savedInstanceState.getLong("crono");
                cronometro.setBase(SystemClock.elapsedRealtime() - tiempoCrono);
                cronometro.start();
            }
        }

        /* Se pone en marcha el cronometro y se registra que el usuario ha hecho ejercicio ese dia */
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hoySeHaEntrenado();
                estadoComenzar = true;
                cronometro.setBase(SystemClock.elapsedRealtime());
                cronometro.start();
            }
        });

        /* Se para el cronometro y te redirecciona a la vista de Inicio */
        terminar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                cronometro.stop();
                Inicio inicio = new Inicio();
                Intent intent = new Intent(getApplicationContext(),inicio.getClass());
                startActivity(intent);
            }
        });

        /* Te pone el ejercicio anterior, siempre y cuando haya ejercicio anterior */
        before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id_ejercicio - 1 > 0){
                    id_ejercicio = id_ejercicio - 1;
                    constructView();
                }
            }
        });

        /* Te pone el ejercicio siguiente, siempre y cuando haya siguiente ejercicio */
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numTotalEjercicios=EjerciciosFragment.numEjerciciosTotales;
                if(id_ejercicio < numTotalEjercicios) {
                    id_ejercicio = id_ejercicio + 1;
                    constructView();
                }
            }
        });
    }

    /* Guarda el estado en ese momento de los elementos */
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        long tiempo = SystemClock.elapsedRealtime() - cronometro.getBase();
        boolean estComenzar = estadoComenzar;
        boolean estPlay = estadoPlay;

        outState.putLong("crono",tiempo);
        outState.putBoolean("pulsado_comenzar",estComenzar);
        outState.putBoolean("pulsado_play",estPlay);
    }

    /* Actualiza la vista con los parametros que hay en la base de datos del ejercicio con
    *  id = id_ejercicio
    */
    public void constructView(){

        int id = getResources().getIdentifier("ej"+id_ejercicio, "drawable", getPackageName());
        Log.e("test", "Resource id: "+id);
        if(id == 0 ){
            id = getResources().getIdentifier("gimsy", "drawable", getPackageName());
        }
        imagen.setImageDrawable( getResources().getDrawable(id));

        data = "id=" + id_ejercicio;
        PostData post = new PostData(data, url); // Ejemplo de lo que devuelve: { "msg":"Success", "data":[{"id":"1", "nombre":"Biceps con Mancuerna", "descripcion":"Aqui una explicacion de mierda sobre como hacer este ejercicio.", "repeticiones":"12", "secs":"40" }] }
        String ejercicioJSON = post.postData();
        Log.e("test",  ejercicioJSON);

        try {
            JSONObject json = new JSONObject( ejercicioJSON);
            JSONArray arr = json.getJSONArray("data");
            numTotalEjercicios=arr.length();

            JSONObject defDataJSON = arr.getJSONObject(0);
            repeticiones.setText(defDataJSON.getString("repeticiones") + " repeticiones");
            descripcion.setText(defDataJSON.getString("descripcion"));

        }catch(JSONException jsonEx){
            Log.e("test", "Failed to convert to JSON");
            jsonEx.printStackTrace();
        }

    }

    /* Registra en la base de datos, API y local, la fecha en la que ha realizado el ejercicio */
    public void hoySeHaEntrenado(){

        SharedPreferences sharedPreferences = this.getSharedPreferences("usuarios", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","No existe un usuario con este username");
        if(!username.equalsIgnoreCase("No existe un usuario con este username")){
            String fechasJSON = sharedPreferences.getString("fechasJSON","Ninguna");
            String token = sharedPreferences.getString("token","Ninguna");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            if(!fechasJSON.contains(formatter.format(date))){
                String data = "token="+token;
                String url = "http://35.180.41.33/identity/user/fechas/nueva/";
                PostData foo = new PostData(data, url);
                foo.postData();
                try{
                    Log.e("test",fechasJSON);
                    JSONObject newDate = new JSONObject();
                    newDate.put("date", formatter.format(date));
                    JSONArray jsonArrayDates = new JSONArray(fechasJSON);
                    jsonArrayDates.put(newDate);

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("fechasJSON", jsonArrayDates.toString());
                }catch(Exception ex){

                }
            }
        }
    }
}
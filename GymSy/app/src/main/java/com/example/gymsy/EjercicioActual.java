package com.example.gymsy;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.bumptech.glide.load.engine.Resource;
import com.example.gymsy.connection.PostData;
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EjercicioActual extends AppCompatActivity {
    private ImageView imagen;
    private TextView repeticiones;
    private TextView _musculo;
    private TextView descripcion;

    private Button start;
    private Button terminar;
    //private Button siguiente;
    private Button before;
    private Button pause;
    private Button next;
    private Chronometer cronometro;

    private int id_ejercicio;
    private int totalRepeticiones = 0;
    private int numTotalEjercicios;
    private String musculo = "";
    private long tiempoCrono;
    private int id_rutina;
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

        imagen  = findViewById(R.id.imageEjercicio);
        before = findViewById(R.id._before);
        next = findViewById(R.id._after);
        start = findViewById(R.id._start);
        terminar = findViewById(R.id._finish);
        cronometro = findViewById(R.id._cronometro);
        repeticiones = findViewById(R.id._repeticiones);
        _musculo = findViewById(R.id._musculo);
        descripcion = findViewById(R.id._descripcionMusculo);

        Bundle datosIntent = getIntent().getExtras();


        id_ejercicio = datosIntent.getInt("extra_ejercicio_id") + 1;


        //Drawable drawable = ResourcesCompat.getDrawable(res,R.drawable.gimsy,null);
        int id = getResources().getIdentifier("ej"+id_ejercicio, "drawable", getPackageName());
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

        //descripcion.setText(musculo);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estadoComenzar = true;
                cronometro.setBase(SystemClock.elapsedRealtime());
                cronometro.start();
            }
        });

        terminar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                cronometro.stop();
                Inicio inicio = new Inicio();
                Intent intent = new Intent(getApplicationContext(),inicio.getClass());
            }
        });

        before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id_ejercicio - 1 > 0){
                    Log.e("test", "a");
                    EjercicioActual ejercicioActual = new EjercicioActual();
                    Intent intent = new Intent(getApplicationContext(), ejercicioActual.getClass());
                    intent.putExtra(Inicio.EXTRA_EJERCICIO_ID, id_ejercicio - 2 );
                    startActivityForResult(intent, REQUEST_SHOW_EJERCICIOS);
                }
            }
        });

        /*pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estadoPlay=false;
            }
        });*/

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(id_ejercicio < EjerciciosFragment.numEjerciciosTotales) {
                    EjercicioActual ejercicioActual = new EjercicioActual();
                    Intent intent = new Intent(getApplicationContext(), ejercicioActual.getClass());
                    intent.putExtra(Inicio.EXTRA_EJERCICIO_ID, id_ejercicio);
                    startActivityForResult(intent, REQUEST_SHOW_EJERCICIOS);
                }
            }
        });

        /*siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Acceso a base de datos
            }
        });*/
    }

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

}

/*


 */

package com.example.gymsy;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
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

import com.bumptech.glide.Glide;

public class EjercicioActual extends AppCompatActivity {
    private ImageView imagen;
    private TextView repeticiones;
    private TextView _musculo;
    private TextView descripcion;

    private Button start;
    private Button terminar;
    //private Button siguiente;
    private ImageButton play;
    private ImageButton pause;
    private ImageButton stop;
    private Chronometer cronometro;

    private int id_ejercicio;
    private int totalRepeticiones = 0;
    private String musculo = "";
    private long tiempoCrono;
    private int id_rutina;
    private boolean estadoPlay;
    private boolean estadoComenzar;

    public EjercicioActual(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ejercicio_actual);

        imagen  = findViewById(R.id.imageEjercicio);
        play = findViewById(R.id._play);
        pause = findViewById(R.id._pause);
        stop = findViewById(R.id._stop);
        //siguiente = findViewById(R.id._next);
        start = findViewById(R.id._start);
        terminar = findViewById(R.id._finish);
        cronometro = findViewById(R.id._cronometro);
        repeticiones = findViewById(R.id._repeticiones);
        _musculo = findViewById(R.id._musculo);
        descripcion = findViewById(R.id._descripcionMusculo);

        Bundle datosIntent = getIntent().getExtras();

        id_ejercicio = datosIntent.getInt("extra_ejercicio_id") + 1;
        Log.e("test", Integer.toString(id_ejercicio));


        /*Cursor c = bd.getRutinaPorIdEjercicio(datosIntent.toString());
        repeticiones.setText(c.getColumnIndex(TablasBD.RutinaEntry.REPETICIONES));

        c = bd.getEjercicioPorId(datosIntent.toString());
        descripcion.setText(c.getColumnIndex(TablasBD.EjercicioEntry.MUSCULO));
        Glide.with(this)
                .load(Uri.parse("file:/app/src/main/res/drawable/" +
                        c.getString(c.getColumnIndex(TablasBD.EjercicioEntry.IMAGEN_URI))))
                .centerCrop()
                .into(imagen); */

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

        descripcion.setText(musculo);

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

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estadoPlay=true;
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estadoPlay=false;
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estadoComenzar=false;
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

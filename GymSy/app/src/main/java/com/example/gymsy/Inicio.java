package com.example.gymsy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class Inicio extends AppCompatActivity {

    public static final String EXTRA_EJERCICIO_ID = "extra_ejercicio_id";
    public static final String EXTRA_USUARIO_ID = "extra_usuario_id";

    private MiPerfil miperfil;

    private Button acercaDe;
    private Button rendimiento;
    private Button logout;

    public Inicio(){
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);

        rendimiento = findViewById(R.id._rendimiento);
        acercaDe = findViewById(R.id._acercaDe);
        logout = findViewById(R.id._logout);

        /* Añade a la vista la lista de ejercicios contenido en EjerciciosFragment */
        EjerciciosFragment fragment = (EjerciciosFragment)
                getSupportFragmentManager().findFragmentById( R.id.ejercicio_container);


        if(fragment==null){
            fragment = new EjerciciosFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.ejercicio_container, fragment)
                    .commit();
        }

        rendimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miperfil = new MiPerfil();
                Intent intent = new Intent(getApplicationContext(), miperfil.getClass());
                startActivity(intent);
            }
        });

        acercaDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AcercaDe acercade = new AcercaDe();
                Intent intent = new Intent(getApplicationContext(),acercade.getClass());
                startActivity(intent);
            }
        });

        /* Elimina de la base de datos local los datos del usuario que se encontraba logueado
        *  en ese momento
        */
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("usuarios", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("username");
                editor.remove("altura");
                editor.remove("peso");
                editor.remove("etapa");
                editor.remove("token");

                editor.remove("fechasJSON");
                editor.commit();
                finish();
            }
        });
    }

    /* Bloquea el boton de atras del movil, para que no vuelva a la pantalla de MainActivity */
    @Override
    public void onBackPressed() {

    }

}

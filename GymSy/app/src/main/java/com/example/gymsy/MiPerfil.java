package com.example.gymsy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import sun.bob.mcalendarview.MCalendarView;

public class MiPerfil extends AppCompatActivity {
    private ImageView imagenPerfil;
    private TextView nombreUsuario;
    private TextView altura;
    private TextView peso;
    private TextView etapa;
    private MCalendarView calendario;

    public MiPerfil(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mi_perfil);

        imagenPerfil = findViewById(R.id.imageView);
        nombreUsuario = findViewById(R.id._nombreUser);
        altura = findViewById(R.id._altString);
        peso = findViewById(R.id._pes);
        etapa = findViewById(R.id._frecActual);
        calendario = findViewById(R.id.calendar);





        SharedPreferences sharedPreferences = this.getSharedPreferences("usuarios", Context.MODE_PRIVATE);

        String username = sharedPreferences.getString("username","No existe un usuario con este username");
        if(username.equalsIgnoreCase("No existe un usuario con este username")){
            String height = sharedPreferences.getString("altura","0");
            String weight = sharedPreferences.getString("peso","0");
            String stage = sharedPreferences.getString("etapa","Ninguna");

            nombreUsuario.setText("Nombre usuario: " + username);
            altura.setText("Altura: " + height);
            peso.setText("Peso:" + weight);
            etapa.setText("Etapa: " + stage);
        }
        else{
            nombreUsuario.setText("Nombre usuario: ");
            altura.setText("Altura: ");
            peso.setText("Peso:");
            etapa.setText("Etapa: ");
        }

        calendario.markDate(2020,05,19);
    }
}

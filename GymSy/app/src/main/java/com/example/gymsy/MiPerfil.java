package com.example.gymsy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

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
        peso = findViewById(R.id._pesoString);
        etapa = findViewById(R.id._frecActual);
        calendario = findViewById(R.id.calendar);

        SharedPreferences sharedPreferences = this.getSharedPreferences("usuarios", Context.MODE_PRIVATE);

        String username = sharedPreferences.getString("username","No existe un usuario con este username");
        Log.e("test", username);
        if(!username.equalsIgnoreCase("No existe un usuario con este username")){
            String height = sharedPreferences.getString("altura","0");
            String weight = sharedPreferences.getString("peso","0");
            String stage = sharedPreferences.getString("etapa","Ninguna");
            String fechasJSON = sharedPreferences.getString("fechasJSON","Ninguna");

            nombreUsuario.setText(username);
            altura.setText("Altura: " + height);
            peso.setText("Peso:" + weight + "kg");
            etapa.setText("Etapa: " + stage);
            try{

            }catch(Exception ex){
                JSONObject json2 = new JSONObject(fechasJSON);
            }



            calendario.markDate(2021,05,18);
        }
        else{
            nombreUsuario.setText("Nombre usuario: ");
            altura.setText("Altura: ");
            peso.setText("Peso:");
            etapa.setText("Etapa: ");
        }




    }
}

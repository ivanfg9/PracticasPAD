package com.example.gymsy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
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

        /* Carga los datos que haya en el SharedPreferences para mostrar la información del
        *  usuario logueado. En el caso de que no hubiera un usuario mostraria todos los campos
        *  vacios y el calendario sin marcas
        */
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
                Log.e("test",fechasJSON);
                JSONArray jsonArrayDates = new JSONArray(fechasJSON);

                Log.e("test", String.valueOf(jsonArrayDates.length() ) );
                int anio,mes,dia;
                for(int i = 0; i < jsonArrayDates.length(); i++){
                    String[] splitDate = new JSONObject(jsonArrayDates.get(i).toString()).getString("date").split("-");
                    anio = Integer.valueOf(splitDate[0]);
                    mes = Integer.valueOf(splitDate[1]);
                    dia = Integer.valueOf(splitDate[2]);
                    calendario.markDate(anio,mes,dia);
                }

            }catch(Exception ex){

            }
        }
        else{
            nombreUsuario.setText("Nombre usuario: ");
            altura.setText("Altura: ");
            peso.setText("Peso:");
            etapa.setText("Etapa: ");
        }
    }

    public MCalendarView getCalendario() {
        return calendario;
    }


    @Override
    public void onBackPressed() {
        finish();
        SharedPreferences preferences = getSharedPreferences("usuarios", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        try {


                String fechasJSON = preferences.getString("fechasJSON", "Ninguna");
                JSONArray jsonArrayDates = new JSONArray(fechasJSON);
                Log.e("test", String.valueOf(jsonArrayDates.length()));
                int anio, mes, dia;
                for (int i = 0; i < jsonArrayDates.length(); i++) {
                    String[] splitDate = new JSONObject(jsonArrayDates.get(i).toString()).getString("date").split("-");
                    anio = Integer.valueOf(splitDate[0]);
                    mes = Integer.valueOf(splitDate[1]);
                    dia = Integer.valueOf(splitDate[2]);
                    calendario.unMarkDate(anio, mes, dia);
                }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}

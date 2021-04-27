package com.example.gymsy;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Cuestionario extends Activity {
    private BaseDatos bd;
    private int nivel;

    private Spinner edad;
    private Spinner altura;
    private Spinner peso;
    private Spinner etapa;
    private Spinner frecuenciaEjercicioActual;
    private Spinner frecuenciaEjercicioObjetivo;
    private Spinner lesionSiNo;
    private EditText zonaLesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cuestionario);

        edad = findViewById(R.id.edad);
        altura = findViewById(R.id.altura);
        peso = findViewById(R.id.peso);
        etapa = findViewById(R.id.etapa);
        String[] e = {"Volumen","Musculación"};
        etapa.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, e));
        frecuenciaEjercicioActual = findViewById(R.id.frecEjerAct);
        String[] fa = {"Nunca", "1 vez a la semana","3 veces a la semana", "5 o mas veces a la semana"};
        frecuenciaEjercicioActual.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, fa));
        frecuenciaEjercicioObjetivo = findViewById(R.id.frecEjerObj);
        String[] fo = {"1 vez a la semana","3 veces a la semana", "5 o mas veces a la semana"};
        frecuenciaEjercicioObjetivo.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, fa));
        lesionSiNo = findViewById(R.id.lesionSiNo);
        String[] lsn = {"Si","No"};
        lesionSiNo.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, fa));
        zonaLesion = findViewById(R.id.zonaLesion);

        if(edad.getCount() == 0 || altura.getCount() == 0 || peso.getCount() == 0
        || etapa.getSelectedItem().equals(null) || frecuenciaEjercicioActual.getSelectedItem().equals(null)
        || frecuenciaEjercicioObjetivo.getSelectedItem().equals(null) || lesionSiNo.getSelectedItem().equals(null)) {
            Toast toast = Toast.makeText(getApplicationContext(), "Quedan campos por rellenar",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
        else if(lesionSiNo.getSelectedItem().equals("Si")){
            Toast toast = Toast.makeText(getApplicationContext(), "Consulte con su médico antes" +
                            "de hacer las siguiente rutinas", Toast.LENGTH_SHORT);
            toast.show();
        }
        else{
            //Crear nueva id
            //Guardar datos
        }
    }
}

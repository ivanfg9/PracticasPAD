package com.example.gymsy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.security.NoSuchAlgorithmException;

public class NuevoUsuario extends Activity {
    String [] etapas = {"Volumen", "Definicion"};
    String [] frecuencias = {"Ninguna/1 vez/semana", "3/4 veces/semana", "5/mas veces/semanas"};
    String [] lesion = {"Si", "No"};

    private TextView username;
    private EditText nombreUsuario;
    private TextView password;
    private EditText contrasena;
    private TextView height;
    private EditText altura;
    private TextView weight;
    private EditText peso;
    private TextView stage;
    private Spinner etapa;
    private TextView currentFrequency;
    private Spinner frecuenciaActual;
    private TextView targetFrequency;
    private Spinner frecuenciaObjetivo;
    private TextView injury;
    private Spinner lesionSiNo;
    private TextView injuredArea;
    private EditText zonaLesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevoUsuario);

        username = findViewById(R.id._username);
        nombreUsuario = findViewById(R.id._nombreUsuario);

        password = findViewById(R.id._password);
        contrasena = findViewById(R.id._contrasena);

        height = findViewById(R.id._height);
        altura = findViewById(R.id._altura);

        weight = findViewById(R.id._weight);
        peso = findViewById(R.id._peso);

        stage = findViewById(R.id._stage);
        etapa.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                etapas));

        currentFrequency = findViewById(R.id._currentFrec);
        frecuenciaActual.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                frecuencias));

        targetFrequency = findViewById(R.id._targetFrec);
        frecuenciaObjetivo.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                frecuencias));

        injury = findViewById(R.id._injury);
        lesionSiNo.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                lesion));

        injuredArea = findViewById(R.id._injuredArea);
        zonaLesion = findViewById(R.id._zonaLesion);
    }

    public void registrarUsuario(View view) throws NoSuchAlgorithmException {
        String _nombreUsuario = nombreUsuario.getText().toString();
        String _altura = altura.getText().toString();
        String _peso = peso.getText().toString();
        String _etapa = etapa.getSelectedItem().toString();
        String _frecActual = frecuenciaActual.getSelectedItem().toString();
        String _frecObjetivo = frecuenciaObjetivo.getSelectedItem().toString();
        String _lesionSiNo = lesionSiNo.getSelectedItem().toString();
        String _zonaLesion = "";
        if (_lesionSiNo.equalsIgnoreCase("Si")){
            _zonaLesion = zonaLesion.getText().toString();
        }
        int nivel;

        if(_nombreUsuario.isEmpty() || _altura.isEmpty() || _peso.isEmpty() ||
        _etapa.isEmpty() || _frecActual.isEmpty() || _frecObjetivo.isEmpty() || _lesionSiNo.isEmpty()){
            Toast toast = Toast.makeText(getApplicationContext(), "Rellene los campos que faltan",
                    Toast.LENGTH_LONG);
            toast.show();
        }
        else{
            BaseDatos db = new BaseDatos(this);

            if(db.existeUsuario(_nombreUsuario)){
                Toast toast = Toast.makeText(getApplicationContext(),"Usuario ya existente", Toast.LENGTH_SHORT);
                toast.show();
            }
            else{
                if(_frecActual.contains("1")){
                    nivel = 1;
                }
                else if(_frecActual.contains("3")){
                    nivel = 2;
                }
                else{
                    nivel = 3;
                }
            }

            byte[] salt = ContrasenaEncriptada.getSalt();
            String contrasenaHasheada = ContrasenaEncriptada.hash(contrasena.getText().toString(), salt);

            //Añadir usuario a base de datos

            finish();
        }
    }
}

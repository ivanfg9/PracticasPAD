package com.example.gymsy;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.gymsy.connection.PostData;

import java.io.UnsupportedEncodingException;
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


    private String _nombreUsuario;
    private String _altura;
    private String _peso;
    private String _etapa;
    private String _frecActual;
    private String _frecObjetivo;
    private String _lesionSiNo;
    private String _zonaLesion;
    private String _contrasenia;

    public NuevoUsuario(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo_usuario);

        username = findViewById(R.id._username);
        nombreUsuario = findViewById(R.id._nombreUsuario);

        password = findViewById(R.id._password);
        contrasena = findViewById(R.id._contrasenia);

        height = findViewById(R.id._heigh);
        altura = findViewById(R.id._altura);

        weight = findViewById(R.id._weight);
        peso = findViewById(R.id._pesoString);

        stage = findViewById(R.id._stageText);
        etapa = findViewById(R.id._etapa);
        etapa.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                etapas));

        currentFrequency = findViewById(R.id._currentFrec);
        frecuenciaActual = findViewById(R.id._frecuenciaAct);
        frecuenciaActual.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                frecuencias));

        targetFrequency = findViewById(R.id._targetFrec);
        frecuenciaObjetivo = findViewById(R.id._frecObj);
        frecuenciaObjetivo.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                frecuencias));

        injury = findViewById(R.id._injury);
        lesionSiNo = findViewById(R.id._lesionSiNo);
        lesionSiNo.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                lesion));

        injuredArea = findViewById(R.id._injuredArea);
        zonaLesion = findViewById(R.id._zonaLesion);
    }

    public void registrarUsuario(View view) throws NoSuchAlgorithmException, UnsupportedEncodingException {
         _contrasenia = contrasena.getText().toString();
         _nombreUsuario = nombreUsuario.getText().toString();
         _altura = altura.getText().toString();
         _peso = peso.getText().toString();
         _etapa = etapa.getSelectedItem().toString();
         _frecActual = frecuenciaActual.getSelectedItem().toString();
         _frecObjetivo = frecuenciaObjetivo.getSelectedItem().toString();
         _lesionSiNo = lesionSiNo.getSelectedItem().toString();
         _zonaLesion = "";

        if (_lesionSiNo.equalsIgnoreCase("Si")){
            _zonaLesion = zonaLesion.getText().toString();
            _lesionSiNo = "1";
        }
        else{
            _lesionSiNo = "0";
        }
        int nivel;

        if(_nombreUsuario.isEmpty() || _altura.isEmpty() || _peso.isEmpty() ||
        _etapa.isEmpty() || _frecActual.isEmpty() || _frecObjetivo.isEmpty() || _lesionSiNo.isEmpty()){
            Toast toast = Toast.makeText(getApplicationContext(), "Rellene los campos que faltan",
                    Toast.LENGTH_LONG);
            toast.show();
        }
        else{

            String jsonRet = postData();

            if(jsonRet.contains("Username Exists")){
                Toast toast = Toast.makeText(getApplicationContext(),"Usuario ya existente", Toast.LENGTH_SHORT);
                toast.show();
            }
            else if(jsonRet.contains("Registered")){
                Toast toast = Toast.makeText(getApplicationContext(),"Registrado!", Toast.LENGTH_SHORT);
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
            Intent mainActivity = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(mainActivity);
        }
    }


    public String postData() {

        try {

            String data = "username="+_nombreUsuario+"&password="+_contrasenia+"&altura="+_altura+"&peso="+_peso+"&etapa="+_etapa+"&lesion="+_lesionSiNo+"&zonaLesion="+_zonaLesion;
            String url = "http://35.180.41.33/auth/register/";
            PostData foo = new PostData(data, url);
            String jsonResult = foo.postData();

            Log.e("test", jsonResult );
            return jsonResult;
        }
        catch(Exception e){
            e.printStackTrace();
            String fail = "{\"msg\":\"Username Exists\"}";
            return fail;
        }
    }

}

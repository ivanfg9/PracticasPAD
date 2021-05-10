package com.example.gymsy;

import android.app.Activity;
import android.content.SyncStatusObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

public class NuevoUsuario extends Activity {
    String [] etapas = {"Volumen", "Definicion"};
    String [] frecuencias = {"Ninguna/1 vez/semana", "3/4 veces/semana", "5/mas veces/semanas"};
    String [] lesion = {"Si", "No"};

    private BaseDatos bd;

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
        setContentView(R.layout.nuevo_usuario);

        username = findViewById(R.id._username);
        nombreUsuario = findViewById(R.id._nombreUsuario);

        password = findViewById(R.id._password);
        contrasena = findViewById(R.id._contrasenia);

        height = findViewById(R.id._heigh);
        altura = findViewById(R.id._altura);

        weight = findViewById(R.id._weight);
        peso = findViewById(R.id._peso);

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
            //bd = new BaseDatos(this);
            postData();
            if(false    /*Acceso base de datos*/){
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



            //Añadir usuario a base de datos

            finish();
        }
    }


    public void postData() {

        try {
            String urlParameters  = "username=bindrei1&password=12345&altura=185&peso=80&etapa=empanadilla&lesion=dierna&zonaLesion=espalda";
            byte[] postData       = urlParameters.getBytes( "UTF_8" );
            int    postDataLength = postData.length;
            String request = "http://127.0.0.1/auth/register/";
            URL    url            = new URL( request );
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setDoOutput( true );
            conn.setInstanceFollowRedirects( false );
            conn.setRequestMethod( "POST" );
            conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty( "charset", "utf-8");
            conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
            conn.setUseCaches( false );
            try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
                wr.write( postData );
            }
            catch (Exception e){
                Log.d("ALV",e.getMessage());
            }
        }
        catch(Exception e){
            Log.d("ALV",e.getMessage());
        }
    }

}

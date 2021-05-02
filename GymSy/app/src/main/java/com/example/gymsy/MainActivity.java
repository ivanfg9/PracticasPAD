package com.example.gymsy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText usuario;
    private EditText contrasena;
    //Base de datos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = findViewById(R.id._usuario);
        contrasena = findViewById(R.id._contrasena);
    }

    public void iniciar(View view){
        String _usuario = usuario.getText().toString();
        String _contrasena = contrasena.getText().toString();

        if(_usuario.isEmpty() && _contrasena.isEmpty()){
            Toast toast = Toast.makeText(getApplicationContext(),"El usuario y contraseña" +
                    "son inválidos", Toast.LENGTH_SHORT);
            toast.show();
        }
        else if(_usuario.isEmpty()){
            Toast toast = Toast.makeText(getApplicationContext(),"El usuario" +
                    "es inválido", Toast.LENGTH_SHORT);
            toast.show();
        }
        else if(_contrasena.isEmpty()){
            Toast toast = Toast.makeText(getApplicationContext(),"La contraseña" +
                    "es inválida", Toast.LENGTH_SHORT);
            toast.show();
        }
        else{
            //Acceso a base de datos
        }
    }

    public void olvidoContrasena(View view){
        Intent intent = new Intent(this,ContrasenaOlvidada.class);
        startActivity(intent);
    }

    public void registrar(View view){
        Intent intent = new Intent(this,NuevoUsuario.class);
        startActivity(intent);
    }
}
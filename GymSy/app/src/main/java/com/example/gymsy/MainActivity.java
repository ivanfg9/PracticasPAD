package com.example.gymsy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int registrado = 0;
    //Base de datos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent;
        /*Mediante la base de datos mirar si esta registrado
            En el caso que lo esté pone registrado a > 0 y te manda al intent de tus ejercicios
            En otro caso te manda al intent de las preguntas
         */
        if(registrado > 0){
            intent = new Intent(this, Inicio.class);
            startActivity(intent);
        }
        else{
            intent = new Intent(this, Cuestionario.class);
            startActivity(intent);
        }
    }
}
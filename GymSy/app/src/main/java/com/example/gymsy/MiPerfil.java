package com.example.gymsy;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MiPerfil extends AppCompatActivity {
    private ImageView imagenPerfil;
    private TextView nombreUsuario;
    private TextView altura;
    private TextView peso;
    private TextView frecAct;

    public MiPerfil(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mi_perfil);

        imagenPerfil = findViewById(R.id.imageView);
        nombreUsuario = findViewById(R.id._nombreUser);
        altura = findViewById(R.id._alt);
        peso = findViewById(R.id._pes);
        frecAct = findViewById(R.id._frecActual);

        DiaSemanaFragment fragment = (DiaSemanaFragment)
                getSupportFragmentManager().findFragmentById( R.id.ejercicio_container);


        if(fragment==null){
            fragment = new DiaSemanaFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.ejercicio_container, fragment)
                    .commit();
        }

    }

}

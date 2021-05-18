package com.example.gymsy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class Inicio extends AppCompatActivity {

    public static final String EXTRA_EJERCICIO_ID = "extra_ejercicio_id";
    public static final String EXTRA_USUARIO_ID = "extra_usuario_id";


    private Button acercaDe;
    private Button rendimiento;
    private Button logout;

    public Inicio(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);

        rendimiento = findViewById(R.id._rendimiento);
        acercaDe = findViewById(R.id._acercaDe);
        logout = findViewById(R.id._logout);

        EjerciciosFragment fragment = (EjerciciosFragment)
                getSupportFragmentManager().findFragmentById( R.id.ejercicio_container);


        if(fragment==null){
            fragment = new EjerciciosFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.ejercicio_container, fragment)
                    .commit();
        }

        rendimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Rendimiento.class);
                startActivity(intent);
            }
        });

        acercaDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AcercaDe.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}

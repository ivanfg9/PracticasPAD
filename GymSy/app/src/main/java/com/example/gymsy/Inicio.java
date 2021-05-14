package com.example.gymsy;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class Inicio extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);

        EjerciciosFragment fragment = (EjerciciosFragment)
                getSupportFragmentManager().findFragmentById( R.id.ejercicio_container);

        if(fragment==null){
            fragment = new EjerciciosFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.ejercicio_container, fragment)
                    .commit();
        }
    }
}

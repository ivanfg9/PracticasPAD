package com.example.gymsy;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Inicio extends ListActivity {
    private int id_usuario;
    private BaseDatos basedatos;
    private ArrayList<String> nombrerutinas= new ArrayList<String>();
    private ArrayList<Integer> ids_rutinas;
    private ListView listarutinas;
    private ArrayAdapter<String> adapter;

    private TextView tituloApp;
    private TextView rutinas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);

        tituloApp = findViewById(R.id.tituloApp);
        rutinas = findViewById(R.id.rutinas);

        ConjuntosID userId = new ConjuntosID(this);
        _userId = userId.getId();
        basedatos = new BaseDatos(this);
    }
}

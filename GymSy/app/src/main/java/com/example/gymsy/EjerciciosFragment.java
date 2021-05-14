package com.example.gymsy;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class EjerciciosFragment extends Fragment {

    private BaseDatos db;

    private ListView ejerciciosListView;
    private SimpleCursorAdapter ejerciciosCursorAdapter;



    public EjerciciosFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_ejercicios, container, false);

        ejerciciosListView = (ListView) root.findViewById(R.id.ejercicios_list);
        ejerciciosCursorAdapter = new SimpleCursorAdapter(
                getActivity(),
                android.R.layout.two_line_list_item,
                db.getEjercicios(),
                new String[]{TablasBD.EjercicioEntry.IMAGEN_URI},
                new int[]{R.id.iv_avatar},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        );

        ejerciciosListView.setAdapter(ejerciciosCursorAdapter);

        db = new BaseDatos(getActivity());

        // Carga de datos
        loadEjercicios(db);

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    private void loadEjercicios(BaseDatos bd){
        EjerciciosLoadTask ej = new EjerciciosLoadTask(bd,ejerciciosCursorAdapter);
    }
}
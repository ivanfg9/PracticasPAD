package com.example.gymsy;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class EjerciciosFragment extends Fragment {

    private static final int REQUEST_SHOW_EJERCICIOS = 2;

    private BaseDatos db;

    private ListView ejerciciosListView;
    private SimpleCursorAdapter ejerciciosCursorAdapter;

    public EjerciciosFragment() {
        db = new  BaseDatos(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        db = new  BaseDatos(getActivity());
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

        ejerciciosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor currentItem = (Cursor) ejerciciosCursorAdapter.getItem(position);
                String currentEjercicioId = currentItem.getString(
                        currentItem.getColumnIndex(TablasBD.EjercicioEntry._ID));

                showEjercicioScreen(currentEjercicioId);
            }
        });

        db = new BaseDatos(getActivity());

        loadEjercicios(db);

        return root;
    }

    private void showEjercicioScreen(String ejercicioId) {
        Intent intent = new Intent(getActivity(), EjercicioActual.class);
        intent.putExtra(Inicio.EXTRA_EJERCICIO_ID, ejercicioId);
        startActivityForResult(intent, REQUEST_SHOW_EJERCICIOS);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Activity.RESULT_OK == resultCode) {
            switch (requestCode) {
                case REQUEST_SHOW_EJERCICIOS:
                    loadEjercicios(db);
                    break;
            }
        }
    }

    private void loadEjercicios(BaseDatos bd){
        EjerciciosLoadTask ej = new EjerciciosLoadTask(bd,ejerciciosCursorAdapter);
        ej.execute();
    }
}
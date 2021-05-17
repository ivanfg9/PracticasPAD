package com.example.gymsy;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class EjerciciosFragment extends Fragment {

    private static final int REQUEST_SHOW_EJERCICIOS = 2;

    private BaseDatos bd;

    private ListView ejerciciosListView;
    private SimpleCursorAdapter ejerciciosCursorAdapter;

    public EjerciciosFragment(BaseDatos bbdd) {
        bd = bbdd;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_ejercicios, container, false);

        ejerciciosListView = (ListView) root.findViewById(R.id.ejercicios_list);
        ejerciciosCursorAdapter = new SimpleCursorAdapter(
                getActivity(),
                android.R.layout.two_line_list_item,
                bd.getEjercicios(),
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

        loadEjercicios(bd);

        return root;
    }

    private void showEjercicioScreen(String ejercicioId) {
        EjercicioActual ejercicioActual = new EjercicioActual(bd);
        Intent intent = new Intent(getActivity(), ejercicioActual.getClass());
        intent.putExtra(Inicio.EXTRA_EJERCICIO_ID, ejercicioId);
        startActivityForResult(intent, REQUEST_SHOW_EJERCICIOS);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Activity.RESULT_OK == resultCode) {
            switch (requestCode) {
                case REQUEST_SHOW_EJERCICIOS:
                    loadEjercicios(bd);
                    break;
            }
        }
    }

    private void loadEjercicios(BaseDatos bd){
        EjerciciosLoadTask ej = new EjerciciosLoadTask(bd,ejerciciosCursorAdapter);
        ej.execute();
    }
}
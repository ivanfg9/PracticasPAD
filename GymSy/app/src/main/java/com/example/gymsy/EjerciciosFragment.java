package com.example.gymsy;

import android.app.Activity;
import android.content.Intent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.gymsy.connection.PostData;

import java.util.ArrayList;
import java.util.List;

public class EjerciciosFragment extends Fragment {

    private static final int REQUEST_SHOW_EJERCICIOS = 2;

    private ListView ejerciciosListView;
    private SimpleCursorAdapter ejerciciosCursorAdapter;
    private String data = "";
    private String url = "http://192.168.0.196:5000/ejercicios/all/";

    public EjerciciosFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.content_ejercicios, container, false);

        ejerciciosListView = (ListView) root.findViewById(R.id.ejercicios_list);

        PostData post = new PostData(data, url); // Ejemplo del JSON que devuelve esto: { "msg":"Success", "data":[{"id":"1", "nombre":"Biceps con Mancuerna", "descripcion":"Aqui una explicacion de mierda sobre como hacer este ejercicio.", "repeticiones":"12", "secs":"40" },{"id":"2", "nombre":"Push ups", "descripcion":"Lo de toda la life", "repeticiones":"30", "secs":"120" }] }
        String allEjerciciosJSON = post.postData();
        Log.e("test", allEjerciciosJSON);
        String[] list = new String[1];
        list[0] = "Something failed";
        try {

            JSONObject json = new JSONObject(allEjerciciosJSON);
            JSONArray arr = json.getJSONArray("data");
            list = new String[arr.length()];
           for(int i = 0; i < arr.length(); i++){
               list[i] = arr.getJSONObject(i).getString("nombre");
           }

        }catch(JSONException jsonEx){
            Log.e("test", "Failed to convert to JSON");
            jsonEx.printStackTrace();
        }


        ArrayAdapter adapter = new ArrayAdapter<String>(getContext(), R.layout.content_ejercicios, R.id.test25,list);
        /*
        ejerciciosCursorAdapter = new SimpleCursorAdapter(
                getActivity(),
                android.R.layout.two_line_list_item,
                bd.getEjercicios(),
                new String[]{TablasBD.EjercicioEntry.IMAGEN_URI},
                new int[]{R.id.iv_avatar},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        );
*/
        ejerciciosListView.setAdapter(adapter);

        ejerciciosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showEjercicioScreen(position);
            }
        });

        return root;
    }

    private void showEjercicioScreen(int ejercicioId) {
        EjercicioActual ejercicioActual = new EjercicioActual();
        Intent intent = new Intent(getActivity(), ejercicioActual.getClass());
        intent.putExtra(Inicio.EXTRA_EJERCICIO_ID, ejercicioId);
        startActivityForResult(intent, REQUEST_SHOW_EJERCICIOS);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Activity.RESULT_OK == resultCode) {
            switch (requestCode) {
                case REQUEST_SHOW_EJERCICIOS:
                    break;
            }
        }
    }

    private void loadEjercicios(){
        EjerciciosLoadTask ej = new EjerciciosLoadTask(ejerciciosCursorAdapter);
        ej.execute();
    }
}
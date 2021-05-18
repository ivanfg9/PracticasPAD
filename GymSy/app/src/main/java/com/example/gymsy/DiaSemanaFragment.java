package com.example.gymsy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.fragment.app.Fragment;

import com.example.gymsy.connection.PostData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DiaSemanaFragment extends Fragment {

    private static final int REQUEST_SHOW_USERS = 2;

    private ListView diaSemanaListView;
    private SimpleCursorAdapter diaSemanaCursorAdapter;
    private String data = "";
    private String url = "http://35.180.41.33/ejercicios/all/";

    public DiaSemanaFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.content_mi_perfil, container, false);

        diaSemanaListView = (ListView) root.findViewById(R.id.perfil_list);

        PostData post = new PostData(data, url); // Ejemplo del JSON que devuelve esto: { "msg":"Success", "data":[{"id":"1", "nombre":"Biceps con Mancuerna", "descripcion":"Aqui una explicacion de mierda sobre como hacer este ejercicio.", "repeticiones":"12", "secs":"40" },{"id":"2", "nombre":"Push ups", "descripcion":"Lo de toda la life", "repeticiones":"30", "secs":"120" }] }
        String allUsuarioJSON = post.postData();
        Log.e("test", allUsuarioJSON);
        String[] list = new String[1];
        list[0] = "Something failed";
        try {

            JSONObject json = new JSONObject(allUsuarioJSON);
            JSONArray arr = json.getJSONArray("data");
            list = new String[arr.length()];
            for(int i = 0; i < arr.length(); i++){
                list[i] = arr.getJSONObject(i).getString("nombre");
            }

        }catch(JSONException jsonEx){
            Log.e("test", "Failed to convert to JSON");
            jsonEx.printStackTrace();
        }


        ArrayAdapter adapter = new ArrayAdapter<String>(getContext(), R.layout.content_mi_perfil, R.id.test26,list);
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
        diaSemanaListView.setAdapter(adapter);

       diaSemanaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showEjercicioScreen(position);
            }
        });

        return root;
    }

    private void showEjercicioScreen(int username) {
        MiPerfil miPerfil = new MiPerfil();
        Intent intent = new Intent(getActivity(), miPerfil.getClass());
        intent.putExtra(Inicio.EXTRA_USUARIO_ID, username);
        startActivityForResult(intent, REQUEST_SHOW_USERS);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Activity.RESULT_OK == resultCode) {
            switch (requestCode) {
                case REQUEST_SHOW_USERS:
                    break;
            }
        }
    }

    private void loadEjercicios(){
        EjerciciosLoadTask ej = new EjerciciosLoadTask(diaSemanaCursorAdapter);
        ej.execute();
    }
}

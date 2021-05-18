package com.example.gymsy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gymsy.connection.PostData;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private ImageView foto;
    private TextView usuarioView;
    private EditText usuario;
    private TextView contrasenaView;
    private EditText contrasena;
    private Button inicioSesion;
    private Button registrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = this.getSharedPreferences("usuarios",Context.MODE_PRIVATE);
        String token = preferences.getString("token","No existe token");
        Log.e("test", token);
        if(token.equalsIgnoreCase("No existe token")){
            foto = findViewById(R.id._foto);
            usuarioView = findViewById(R.id._heigh);
            usuario = findViewById(R.id._altura);
            contrasenaView = findViewById(R.id._weight);
            contrasena = findViewById(R.id._peso);
            inicioSesion = findViewById(R.id._inicioSesion);
            registrarse = findViewById(R.id._registrarse);
        }
        else{
            Inicio inicio = new Inicio();
            Intent intent = new Intent(this,inicio.getClass());
            intent.putExtra(Inicio.EXTRA_USUARIO_ID,preferences.getString("username","user"));
            startActivity(intent);
        }
    }

    public void iniciar(View view){
        String _usuario = usuario.getText().toString();
        String _contrasena = contrasena.getText().toString();

        if(_usuario.isEmpty() && _contrasena.isEmpty()){
            Toast toast = Toast.makeText(getApplicationContext(),"El usuario y contraseña" +
                    "son inválidos", Toast.LENGTH_SHORT);
            toast.show();
        }
        else if(_usuario.isEmpty()){
            Toast toast = Toast.makeText(getApplicationContext(),"El usuario" +
                    "es inválido", Toast.LENGTH_SHORT);
            toast.show();
        }
        else if(_contrasena.isEmpty()){
            Toast toast = Toast.makeText(getApplicationContext(),"La contraseña" +
                    "es inválida", Toast.LENGTH_SHORT);
            toast.show();
        }
        else{

            String jsonAnswer;
            try{

                String data = "username="+_usuario+"&password="+_contrasena;
                String url = "http://35.180.41.33/auth/login/";
                PostData loginConnection = new PostData(data,url);
                Thread thread = new Thread(loginConnection);
                thread.start();
                thread.join();
                jsonAnswer = loginConnection.getValue();
                Log.e("test",jsonAnswer);
                if(jsonAnswer.contains("Wrong")){
                    Toast toast = Toast.makeText(getApplicationContext(),"El usuario no existe o la contraseña es incorrecta", Toast.LENGTH_SHORT);
                    toast.show();

                }
                else if (jsonAnswer.contains("Logged")){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Inicio de Sesión correcto", Toast.LENGTH_SHORT);
                    toast.show();

                    String jsonAnswer2;
                    JSONObject json = new JSONObject(jsonAnswer);
                    String token = json.getString("token");
                    String data2 = "token="+token;
                    String url2 = "http://35.180.41.33/identity/user/";

                    PostData loginConnection2 = new PostData(data2,url2);
                    Thread thread2 = new Thread(loginConnection2);
                    thread2.start();
                    thread2.join();
                    jsonAnswer2 = loginConnection2.getValue();
                    JSONObject json2 = new JSONObject(jsonAnswer2);
                    Log.e("test",jsonAnswer2);

                    SharedPreferences preferences = this.getSharedPreferences("usuarios",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("username",json2.getString("username"));
                    editor.putString("altura",json2.getString("altura"));
                    editor.putString("peso",json2.getString("peso"));
                    editor.putString("etapa",json2.getString("etapa"));
                    editor.putString("token",token);
                    editor.commit();

                    Inicio inicio = new Inicio();
                    Intent intent = new Intent(this,inicio.getClass());
                    intent.putExtra(Inicio.EXTRA_USUARIO_ID,_usuario);
                    startActivity(intent);
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void registrar(View view){
        NuevoUsuario nuevoUsuario = new NuevoUsuario();
        Intent intent = new Intent(this,nuevoUsuario.getClass());
        startActivity(intent);
    }
}
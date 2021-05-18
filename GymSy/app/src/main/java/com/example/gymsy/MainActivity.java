package com.example.gymsy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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


        foto = findViewById(R.id._foto);
        usuarioView = findViewById(R.id._heigh);
        usuario = findViewById(R.id._altura);
        contrasenaView = findViewById(R.id._weight);
        contrasena = findViewById(R.id._peso);
        inicioSesion = findViewById(R.id._inicioSesion);
        registrarse = findViewById(R.id._registrarse);
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
                String url = "http://192.168.0.196:5000/auth/login/";
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
package com.example.gymsy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView foto;
    private TextView usuarioView;
    private EditText usuario;
    private TextView contrasenaView;
    private EditText contrasena;
    private Button inicioSesion;
    private Button registrarse;

    private BaseDatos bd;

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

        bd = new BaseDatos(getApplicationContext());

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
            //Acceso a base de datos
            Cursor c = bd.getUsuarioPorUsername(_usuario);
            if(c == null){
                Toast toast = Toast.makeText(getApplicationContext(),"El usuario" +
                        "no existe", Toast.LENGTH_SHORT);
                toast.show();
            }
            else if(c.getString(c.getColumnIndex(TablasBD.UsuarioEntry.CONTRASENA)).equals(contrasena)){
                Intent intent = new Intent(this,Inicio.class);
                intent.putExtra(Inicio.EXTRA_USUARIO_ID,_usuario);
                startActivity(intent);
            }
            else{
                Toast toast = Toast.makeText(getApplicationContext(),"La contraseña" +
                        "no coincide", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    public void registrar(View view){
        Intent intent = new Intent(this,NuevoUsuario.class);
        startActivity(intent);
    }
}
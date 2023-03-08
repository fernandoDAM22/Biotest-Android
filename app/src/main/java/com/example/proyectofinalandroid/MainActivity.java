package com.example.proyectofinalandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.proyectofinalandroid.controller.acceso.Codigos;
import com.example.proyectofinalandroid.controller.acceso.Login;
import com.example.proyectofinalandroid.controller.baseDeDatos.Constantes;
import com.example.proyectofinalandroid.controller.baseDeDatos.HttpRequest;
import com.example.proyectofinalandroid.controller.tools.ComprobarDatos;
import com.example.proyectofinalandroid.controller.tools.CrearToast;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity implements Codigos {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Este metodo permite al usuario
     * @param view
     */
    public void login(View view){
        Executor executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = ((ExecutorService) executor).submit(() -> {
            TextView txtNombre = findViewById(R.id.username);
            TextView txtPassword = findViewById(R.id.password);
            if(txtNombre.getText().toString().equals("") || txtPassword.getText().toString().equals("")){
                return ERROR_CAMPOS_VACIOS;
            }
            return Login.login(txtNombre.getText().toString(),txtPassword.getText().toString());
        });
        try {
            int resultado = future.get();
            String mensaje = "";
            if(resultado == ERROR_CAMPOS_VACIOS){
                mensaje = "Error, rellene todos los campos";
            }else if(resultado == ERROR_NO_EXISTE_USUARIO){
                mensaje = "Error, no existe el usuario";
            }else if(resultado == Codigos.ERROR_PASSWORD_INCORRECTA){
                mensaje = "Error, contraseña incorrecta";
            }
            //mostramos el mensaje de error en caso de que haya ocurrido
            if(resultado < 0){
                CrearToast.crearToast(mensaje,getApplicationContext()).show();
            }
            //si no ha habido errores lanzamos la ventana de seleccionar modo de juego
            if(resultado == CORRECTO){
                CrearToast.crearToast("Login Correcto",getApplicationContext()).show();
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
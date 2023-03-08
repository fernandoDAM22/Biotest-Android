package com.example.proyectofinalandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.view.View;
import android.widget.TextView;

import com.example.proyectofinalandroid.activities.PantallaRegistro;
import com.example.proyectofinalandroid.activities.PantallaSeleccionarModoJuego;
import com.example.proyectofinalandroid.controller.acceso.Codigos;
import com.example.proyectofinalandroid.controller.acceso.Login;
import com.example.proyectofinalandroid.controller.tools.CrearToast;
import com.example.proyectofinalandroid.controller.usuario.ConfiguracionUsuario;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity implements Codigos {
    TextView txtNombre;
    TextView txtPassword;
    TextView txtRegistro;
    Vibrator vibrator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtNombre = findViewById(R.id.username);
        txtPassword = findViewById(R.id.password);
        txtRegistro = findViewById(R.id.opcion_registrarse);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

    }

    /**
     * Este metodo permite al usuario
     * @param view
     */
    public void login(View view){
        Executor executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = ((ExecutorService) executor).submit(() -> {
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
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    // Crea un objeto VibrationEffect que especifica el patrón de vibración
                    VibrationEffect vibrationEffect = VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE);
                    // Vibra con el patrón especificado
                    vibrator.vibrate(vibrationEffect);
                } else {
                    // Si la API de Android es anterior a la versión O, utiliza el método anterior
                    vibrator.vibrate(100);
                }
                return;
            }
            //si no ha habido errores lanzamos la ventana de seleccionar modo de juego
            if(resultado == CORRECTO){
                //CrearToast.crearToast("Login Correcto",getApplicationContext()).show();
                ConfiguracionUsuario.setNombreUsuario(txtNombre.getText().toString());
                Intent intent = new Intent(getApplicationContext(), PantallaSeleccionarModoJuego.class);
                startActivity(intent);
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void registrarse(View view){
        Intent intent = new Intent(getApplicationContext(), PantallaRegistro.class);
        startActivity(intent);
    }
}
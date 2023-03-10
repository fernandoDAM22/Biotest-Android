package com.example.proyectofinalandroid.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinalandroid.R;
import com.example.proyectofinalandroid.controller.acceso.Codigos;
import com.example.proyectofinalandroid.controller.acceso.Registro;
import com.example.proyectofinalandroid.controller.tools.CrearToast;
import com.example.proyectofinalandroid.controller.tools.Dialogos;
import com.example.proyectofinalandroid.controller.tools.Vibracion;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PantallaRegistro extends AppCompatActivity implements Codigos {
    TextView txtNombre;
    TextView txtPassword;
    TextView txtPassword2;
    TextView txtEmail;
    TextView txtTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_registro);
        txtNombre = findViewById(R.id.txtNombre);
        txtPassword = findViewById(R.id.txtPassword);
        txtPassword2 = findViewById(R.id.txtPassword2);
        txtEmail = findViewById(R.id.txtEmail);
        txtTelefono = findViewById(R.id.txtTelefono);
    }

    public void registro(View view) {
        try {
            int estado = registrarse();
            String mensaje = "";
            switch (estado) {
                //en funcion del codigo devuelto mostratemos un mensaje u otro
                case ERROR_CAMPOS_VACIOS:
                    mensaje = "Error, rellene todos los campos";
                    break;
                case ERROR_NOMBRE:
                    mensaje = "Error, el nombre no es correcto";
                    break;
                case ERROR_EMAIL:
                    mensaje = "Error, el email no es correcto";
                    break;
                case ERROR_FORMATO_PASSWORD:
                    mensaje = "Error, el formato de la contraseña no es correcto";
                    break;
                case ERROR_PASSWORDS:
                    mensaje = "Error, las contraseñas no coinciden";
                    break;
                case ERROR_TELEFONO:
                    mensaje = "Error, el telefono no es correcto";
                    break;
                case ERROR_EXISTE_USUARIO:
                    mensaje = "Error, ya existe un usuario con ese nombre";
                    break;
                case CANCELADO:
                    mensaje = "Operacion cancelada";
                    break;
                case CORRECTO:
                    mensaje = "Usuario registrado correctamente";
                    break;
            }
            CrearToast.crearToast(mensaje, getApplicationContext()).show();
            //mostramos el mensaje correspondiente
            if (estado > 0) { //si el codigo es negativo es un error
                finish();
            }else{
                Vibracion.vibrar(getApplicationContext(),100);
            }

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int registrarse() throws ExecutionException, InterruptedException {
        Executor executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = ((ExecutorService) executor).submit(() -> {
            //registramos el usuario pasando los datos de los inputs
            int codigo = Registro.comprobarRegistro(txtNombre.getText().toString(),
                    txtEmail.getText().toString(), txtPassword.getText().toString(),
                    txtPassword2.getText().toString(), txtTelefono.getText().toString()
                    , getApplicationContext());
            return codigo;
        });
       return future.get();

    }


}
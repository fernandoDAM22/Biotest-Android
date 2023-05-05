package com.example.proyectofinalandroid.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.proyectofinalandroid.R;
import com.example.proyectofinalandroid.controller.acceso.Codigos;
import com.example.proyectofinalandroid.controller.acceso.Registro;
import com.example.proyectofinalandroid.controller.tools.CrearToast;
import com.example.proyectofinalandroid.controller.tools.Mensajes;
import com.example.proyectofinalandroid.controller.tools.Vibracion;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Esta clase se encarga de gestionar la logica de la pantalla de registro
 * @author Fernando
 */
public class PantallaRegistro extends AppCompatActivity implements Codigos {
    /**
     * Es el campo para el nombre del usuario
     */
    private TextView txtNombre;
    /**
     * Es el campo para la contraseña del usuario
     */
    private TextView txtPassword;
    /**
     * Es el campo para la repeticion de la contraseña del usuario
     */
    private TextView txtPassword2;
    /**
     * Es el campo para el email del usuario
     */
    private TextView txtEmail;
    /**
     * Es el campo para el telefono del usuario
     */
    private TextView txtTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_registro);
        //inicializamos las variables con los campos de la interfaz
        txtNombre = findViewById(R.id.txtNombre);
        txtPassword = findViewById(R.id.txtPassword);
        txtPassword2 = findViewById(R.id.txtPassword2);
        txtEmail = findViewById(R.id.txtEmail);
        txtTelefono = findViewById(R.id.txtTelefonoActual);
    }

    /**
     * Este metodo pemite al usuario registrarse, se ejecuta cuando se pulsa el boton
     * de registrar
     * @param view es el boton que se pulsa
     * @author Fernando
     */
    public void registro(View view) {
        //Creamos el dialogo
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmacion");
        builder.setMessage(Mensajes.MENSAJE_CONFIRMACION_REGISTRO);
        //la acion de registrarse solo se llevara a cabo si se pulsa el boton de aceptar
        builder.setPositiveButton("Aceptar", (dialog, which) -> {
            try {
                //intentamos registrarnos y obtenemos el codigo
                int estado = registrarse();
                String mensaje = "";
                //en funcion del codigo guardamos el mensaje correspondiente
                switch (estado) {
                    //en funcion del codigo devuelto mostratemos un mensaje u otro
                    case ERROR_CAMPOS_VACIOS:
                        mensaje = Mensajes.ERROR_CAMPOS_VACIOS;
                        break;
                    case ERROR_NOMBRE:
                        mensaje = Mensajes.ERROR_FORMATO_USERNAME;
                        break;
                    case ERROR_EMAIL:
                        mensaje = Mensajes.ERROR_REQUISTOS_EMAIL;
                        break;
                    case ERROR_FORMATO_PASSWORD:
                        mensaje = Mensajes.ERROR_FORMATO_PASSWORD;
                        break;
                    case ERROR_PASSWORDS:
                        mensaje = Mensajes.ERROR_PASSWORDS;
                        break;
                    case ERROR_TELEFONO:
                        mensaje = Mensajes.ERROR_FORMATO_TELEFONO;
                        break;
                    case ERROR_EXISTE_USUARIO:
                        mensaje = Mensajes.ERROR_EXISTE_USERNAME;
                        break;
                    case CANCELADO:
                        mensaje = Mensajes.OPERACION_CANCELADA;
                        break;
                    case CORRECTO:
                        mensaje = Mensajes.MENSAJE_USUARIO_REGISTRADO_CORRECTAMENTE;
                        break;
                }
                //mostramos el toast con el mensaje correspondiente
                CrearToast.toastLargo(mensaje, getApplicationContext()).show();
                //si el codigo es negativo es un error, si no es correcto
                if (estado > 0) {
                    //en caso de ser correcto simplemente cerramos la actividad para volver a la pantalla de login
                    finish();
                }else{
                    //si se llega aqui a ocurrido un error, por lo que hacemos vibrar el telefono
                    Vibracion.vibrar(getApplicationContext(),100);
                }

            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        builder.setNegativeButton("Cancelar", null);
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    /**
     * Este metodo registra al usuario en la base de datos, realiza la accion desde un hilo aparte
     * para evitar el bloqueo de la aplicacion
     * @return el codigo de estado correspondiente
     * @throws ExecutionException cuando falla la tarea
     * @throws InterruptedException cuando se interrumpe la tarea
     * @author Fernando
     */
    public int registrarse() throws ExecutionException, InterruptedException {
        Executor executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = ((ExecutorService) executor).submit(() -> {
            //registramos el usuario pasando los datos de los inputs y retornamos el resultado
            return Registro.comprobarRegistro(txtNombre.getText().toString(),
                    txtEmail.getText().toString(), txtPassword.getText().toString(),
                    txtPassword2.getText().toString(), txtTelefono.getText().toString()
                    , getApplicationContext());
        });
       return future.get();
    }

    /**
     * Este metodo permite cerrar la pantalla de registro y volver a la ventana de login, se
     * ejecuta cuando se pulsa el boton de cancelar
     * @param view es el boton que se pulsa
     * @author Fernando
     *
     */
    public void cancelar(View view){
        finish();
    }

}
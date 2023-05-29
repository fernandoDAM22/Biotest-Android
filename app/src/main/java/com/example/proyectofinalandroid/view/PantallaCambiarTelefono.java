package com.example.proyectofinalandroid.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.proyectofinalandroid.MainActivity;
import com.example.proyectofinalandroid.controller.acceso.Codigos;
import com.example.proyectofinalandroid.controller.acceso.Login;
import com.example.proyectofinalandroid.controller.baseDeDatos.Constantes;
import com.example.proyectofinalandroid.controller.baseDeDatos.GestionUsuarios;
import com.example.proyectofinalandroid.controller.tools.Cifrado;
import com.example.proyectofinalandroid.controller.tools.ComprobarDatos;
import com.example.proyectofinalandroid.controller.tools.CrearToast;
import com.example.proyectofinalandroid.controller.tools.Mensajes;
import com.example.proyectofinalandroid.controller.tools.Vibracion;
import com.example.proyectofinalandroid.controller.usuario.ConfiguracionUsuario;
import com.example.proyectofinalandroid.R;

import java.util.Locale;

/**
 * Esta clase pinta la pantalla que permite al usuario cambiar su telefono
 */
public class PantallaCambiarTelefono extends AppCompatActivity {
    public static final String ERROR_CAMBIO_TELEFONO = "Error al cambiar el telefono";
    /**
     * Input para la contrasena
     */
    private EditText txtPassword;
    /**
     * Input para el telefono
     */
    private EditText txtTelefono;
    /**
     * TextView para el telefono actual del usuario
     */
    private TextView txtTelefonoActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_cambiar_telefono);
        //inicializamos las variables
        txtPassword = findViewById(R.id.inputPasswordTelefono);
        txtTelefono = findViewById(R.id.inputCambiarTelefono);
        txtTelefonoActual = findViewById(R.id.txtTelefonoActual);
        //mostramos el telefono actual del usuario
        txtTelefonoActual.setText("Telefono actual: " + Login.obtenerDatos(ConfiguracionUsuario.getNombreUsuario(),Codigos.OBTENER_TELEFONO));
        //le aplicamos un filtro blanco a los inputs
        Drawable drawable1 = txtPassword.getBackground();
        Drawable drawable2 = txtTelefono.getBackground();
        drawable1.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_IN);
        drawable2.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_IN);
    }

    /**
     * Metodo onCreateOptionsMenu sobreescrito para poder establacer un menu en la actividad
     * @param menu es el menu que se añade
     * @return true
     * @author Fernando
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    /**
     * MetodoOptionsItemSelected sobreescrito para poderle dar funcionalidad al menu
     * @param item es el item del menu que se pulsa
     * @return true si se pulsa sobre una opcion valida del menu, false si ocurre algun error
     * @author Fernando
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        switch (id){
            case R.id.item_modo_juego:
                intent = new Intent(this,PantallaSeleccionarModoJuego.class);
                startActivity(intent);
                return true;
            case R.id.item_cerrar_sesion:
                finishAffinity();
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.item_cambiar_password:
                intent = new Intent(this,PantallaCambiarPassword.class);
                startActivity(intent);
                return true;
            case R.id.item_cambiar_usuario:
                intent = new Intent(this,PantallaCambiarNombreUsuario.class);
                startActivity(intent);
                return true;
            case R.id.item_cambiar_telefono:
                intent = new Intent(this,PantallaCambiarTelefono.class);
                startActivity(intent);
                return true;
            case R.id.item_cambiar_email:
                intent = new Intent(this,PantallaCambiarEmail.class);
                startActivity(intent);
                return true;
            case R.id.item_licencia:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(Constantes.URL_LICENCIA));
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Este metodo se ejecuta cuando el usuario pulsa el boton de aceptar
     * @param view es el boton que se pulsa
     * @author Fernando
     */
    public void btnAceptarOnClick(View view){
        //obtenemos la contrasena y el telefono
        String password = txtPassword.getText().toString();
        String telefono = txtTelefono.getText().toString();
        //se comprueba que los datos no esten vacios
        if(password.equals("") ||telefono.equals("")){
            CrearToast.toastLargo(Mensajes.ERROR_CAMPOS_VACIOS,getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(),100);
            return;
        }
        //comprobamos la contrasena sea la del usuario
        if(!Cifrado.SHA256(password).equals(Login.obtenerDatos(ConfiguracionUsuario.getNombreUsuario(), Codigos.OBTENER_PASSWORD))){
            CrearToast.toastLargo(Mensajes.ERROR_PASSWORD_INCORRECTA,getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(),100);
            return;
        }
        //se comprueba que el telefono cumpla con los requisitos
        if(!ComprobarDatos.comprobarTelefono(telefono)){
            CrearToast.toastLargo(Mensajes.ERROR_FORMATO_TELEFONO,getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(),100);
            return;
        }
        //se cambia el telefono y se muestra el mensaje correspondiente
        if(GestionUsuarios.cambiarTelefono(ConfiguracionUsuario.getNombreUsuario(),telefono)){
            CrearToast.toastLargo(Mensajes.CAMBIO_TELEFONO_CORRECTO,getApplicationContext()).show();
            limpiarCampos();
            txtTelefonoActual.setText("Telefono actual: " + Login.obtenerDatos(ConfiguracionUsuario.getNombreUsuario(),Codigos.OBTENER_TELEFONO));
        }else{
            CrearToast.toastLargo(ERROR_CAMBIO_TELEFONO,getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(),100);
        }
    }

    /**
     * Este metodo permite limpiar los inputs
     * @author Fernando
     */
    private void limpiarCampos(){
        txtPassword.setText("");
        txtTelefono.setText("");
    }

    /**
     * Este metodo se ejecuta cuando se pulsa el boton de cancelar
     * @param view es el boton que se pulsa
     * @author Fernando
     */
    public void btnCancelarOnClik(View view){
        limpiarCampos();
    }
}
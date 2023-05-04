package com.example.proyectofinalandroid.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.proyectofinalandroid.MainActivity;
import com.example.proyectofinalandroid.R;
import com.example.proyectofinalandroid.controller.acceso.Codigos;
import com.example.proyectofinalandroid.controller.acceso.Login;
import com.example.proyectofinalandroid.controller.baseDeDatos.Constantes;
import com.example.proyectofinalandroid.controller.baseDeDatos.GestionUsuarios;
import com.example.proyectofinalandroid.controller.tools.Cifrado;
import com.example.proyectofinalandroid.controller.tools.ComprobarDatos;
import com.example.proyectofinalandroid.controller.tools.CrearToast;
import com.example.proyectofinalandroid.controller.tools.Vibracion;
import com.example.proyectofinalandroid.controller.usuario.ConfiguracionUsuario;

import java.util.Locale;

/**
 * Esta clase pinta la pantalla permite al usuario cambiar su email
 * @author Fernando
 */
public class PantallaCambiarEmail extends AppCompatActivity {
    /**
     * Input para la contrasena del usuario
     */
    EditText txtPassword;
    /**
     * Input para el email del usuario
     */
    EditText txtEmail;
    /**
     * TextView para mostrar el email actual del usuario
     */
    TextView txtEmailActual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_cambiar_email);
        //inicializamos las variables
        txtPassword = findViewById(R.id.inputPasswordTelefono);
        txtEmail = findViewById(R.id.inputCambiarTelefono);
        txtEmailActual = findViewById(R.id.txtEmailActual);
        //Colocamos el email actual del usuario
        txtEmailActual.setText("Email actual: " + Login.obtenerDatos(ConfiguracionUsuario.getNombreUsuario(),Codigos.OBTENER_EMAIL));
        //le aplicamos un filtro blanco a los inputs
        Drawable drawable1 = txtPassword.getBackground();
        Drawable drawable2 = txtEmail.getBackground();
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
        //obtenemos la contrasena y el email
        String password = txtPassword.getText().toString();
        String email = txtEmail.getText().toString();
        //comprobamos que os campos no esten vacios
        if(password.equals("") || email.equals("")){
            CrearToast.toastLargo("Rellene todos los campos",getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(),100);
            return;
        }
        //comprobamos la contrasena sea la del usuario
        if(!Cifrado.SHA256(password).equals(Login.obtenerDatos(ConfiguracionUsuario.getNombreUsuario(), Codigos.OBTENER_PASSWORD))){
            CrearToast.toastLargo("La contraseña actual no es correcta",getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(),100);
            return;
        }
        //comprobamos que el email cumple con los requisitos
        if(!ComprobarDatos.comprobarCorreo(email)){
            CrearToast.toastLargo("Error, el email no cumple con los requistos",getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(),100);
            return;
        }
        //cambiamos el email al usuario y mostramos el mensaje correspondiente
        if(GestionUsuarios.cambiarEmail(ConfiguracionUsuario.getNombreUsuario(),email)){
            CrearToast.toastLargo("Email cambiado correctamete",getApplicationContext()).show();
            limpiarCampos();
            txtEmailActual.setText("Email actual: " + Login.obtenerDatos(ConfiguracionUsuario.getNombreUsuario(),Codigos.OBTENER_EMAIL));
        }else{
            CrearToast.toastLargo("Error al cambiar el email",getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(),100);
        }
    }
    /**
     * Este metodo permite limpiar los inputs
     * @author Fernando
     */
    private void limpiarCampos(){
        txtPassword.setText("");
        txtEmail.setText("");
    }

    /**
     * Este metodo se ejecuta cuando se pulsa el boton de cancelar
     * @param view es el boton que se pulsa
     * @author Fernando
     */
    public void btnCancelarOnClick(View view){
        limpiarCampos();
    }
}
package com.example.proyectofinalandroid.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.proyectofinalandroid.MainActivity;
import com.example.proyectofinalandroid.R;
import com.example.proyectofinalandroid.controller.acceso.Codigos;
import com.example.proyectofinalandroid.controller.acceso.Login;
import com.example.proyectofinalandroid.controller.baseDeDatos.GestionUsuarios;
import com.example.proyectofinalandroid.controller.tools.Cifrado;
import com.example.proyectofinalandroid.controller.tools.ComprobarDatos;
import com.example.proyectofinalandroid.controller.tools.CrearToast;
import com.example.proyectofinalandroid.controller.tools.Vibracion;
import com.example.proyectofinalandroid.controller.usuario.ConfiguracionUsuario;

/**
 * Esta clase pinta la pantalla que permite al usuario cambiar su nombre de usuario
 * @author Fernando
 */
public class PantallaCambiarNombreUsuario extends AppCompatActivity {
    /**
     * Input para la contrasena del usuario
     */
    EditText txtPassword;
    /**
     * Input para el nombre de usuario
     */
    EditText txtUsername;
    /**
     * TextView para mostrar el usuario
     */
    TextView txtUsuarioActual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_cambiar_nombre_usuario);
        //iniciamos las variables
        txtPassword = findViewById(R.id.inputPasswordTelefono);
        txtUsername = findViewById(R.id.inputCambiarUsername);
        txtUsuarioActual = findViewById(R.id.txtUsuarioActual);
        //mostramos el usuario actual
        txtUsuarioActual.setText("Usuario actual: " + ConfiguracionUsuario.getNombreUsuario());
        //le aplicamos un filtro blanco a los inputs
        Drawable drawable1 = txtPassword.getBackground();
        Drawable drawable2 = txtUsername.getBackground();
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
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Este metodo se ejecuta cuando el usuario pulsa el boton de aceptar
     * @param view es el boton que se pulsa
     * @author Fernando
     */
    public void btnAceptarOnClick(View view){
        //obtenemos la contrasena y el nombre de usuario
        String password = txtPassword.getText().toString();
        String username = txtUsername.getText().toString();
        //se comprueba que los datos no esten vacios
        if(password.equals("") || username.equals("")){
            CrearToast.toastLargo("Rellene todos los campos",getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(),100);
            return;
        }
        //comprobamos la contrasena  sea la del usuario
        if(!Cifrado.SHA256(password).equals(Login.obtenerDatos(ConfiguracionUsuario.getNombreUsuario(), Codigos.OBTENER_PASSWORD))){
            CrearToast.toastLargo("La contraseña actual no es correcta",getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(),100);
            return;
        }
        //se comprueba que el nombre de usuario cumpla con los requisitos
        if(!ComprobarDatos.comprobarNombre(username)){
            CrearToast.toastLargo("Error, el nombre de usuario no cumple con los requisitos",getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(),100);
            return;
        }
        //se comprueba que no exista ya alguien con ese usuario
        if(ComprobarDatos.existeUsuario(username) == 0){
            CrearToast.toastLargo("Error, ya existe un usuario con ese nombre",getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(),100);
            return;
        }
        //Se modifica el nombre de usuario y se muestra el mensaje de error correspondiente
        if(GestionUsuarios.modificarNombre(ConfiguracionUsuario.getNombreUsuario(),username)){
            CrearToast.toastLargo("Nombre de usuario cambiado correctamete",getApplicationContext()).show();
            ConfiguracionUsuario.setNombreUsuario(username);
            txtUsuarioActual.setText("Usuario actual: " + ConfiguracionUsuario.getNombreUsuario());
            limpiarCampos();
        }else{
            CrearToast.toastLargo("Error al cambiar el nombre de usuario",getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(),100);
        }
    }

    /**
     * Este metodo permite limpiar los inputs
     * @author Fernando
     */
    private void limpiarCampos(){
        txtPassword.setText("");
        txtUsername.setText("");
    }

    /**
     * Este metodo se ejecuta cuando el usuario pulsa el boton de cancelar
     * @param view es el boton que se pulsa
     * @author Fernando
     */
    public void btnCancelarOnClick(View view){
        limpiarCampos();
    }
}
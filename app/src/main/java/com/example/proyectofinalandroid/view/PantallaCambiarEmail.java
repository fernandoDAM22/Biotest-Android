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

import java.util.Locale;

public class PantallaCambiarEmail extends AppCompatActivity {
    EditText txtPassword;
    EditText txtEmail;
    TextView txtEmailActual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_cambiar_email);
        txtPassword = findViewById(R.id.inputPasswordTelefono);
        txtEmail = findViewById(R.id.inputCambiarTelefono);
        txtEmailActual = findViewById(R.id.txtEmailActual);
        txtEmailActual.setText("Email actual: " + Login.obtenerDatos(ConfiguracionUsuario.getNombreUsuario(),Codigos.OBTENER_EMAIL));
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

    public void btnAceptarOnClick(View view){
        String password = txtPassword.getText().toString();
        String email = txtEmail.getText().toString();

        if(password.equals("") || email.equals("")){
            CrearToast.toastLargo("Rellene todos los campos",getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(),100);
            return;
        }
        //comprobamos la contrasena vieja sea la del usuario
        if(!Cifrado.SHA256(password).equals(Login.obtenerDatos(ConfiguracionUsuario.getNombreUsuario(), Codigos.OBTENER_PASSWORD))){
            CrearToast.toastLargo("La contraseña actual no es correcta",getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(),100);
            return;
        }
        if(!ComprobarDatos.comprobarCorreo(email)){
            CrearToast.toastLargo("Error, el email no cumple con los requistos",getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(),100);
            return;
        }
        if(GestionUsuarios.cambiarEmail(ConfiguracionUsuario.getNombreUsuario(),email)){
            CrearToast.toastLargo("Email cambiado correctamete",getApplicationContext()).show();
            limpiarCampos();
            txtEmailActual.setText("Email actual: " + Login.obtenerDatos(ConfiguracionUsuario.getNombreUsuario(),Codigos.OBTENER_EMAIL));
        }else{
            CrearToast.toastLargo("Error al cambiar el nombre de usuario",getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(),100);
        }
    }
    private void limpiarCampos(){
        txtPassword.setText("");
        txtEmail.setText("");
    }
    public void btnCancelarOnClick(View view){
        limpiarCampos();
    }
}
package com.example.proyectofinalandroid.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
            txtEmailActual.setText("Email actual: " + Login.obtenerDatos(ConfiguracionUsuario.getNombreUsuario(),Codigos.OBTENER_EMAIL));
        }else{
            CrearToast.toastLargo("Error al cambiar el nombre de usuario",getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(),100);
        }
    }
}
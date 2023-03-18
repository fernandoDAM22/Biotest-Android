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

public class PantallaCambiarNombreUsuario extends AppCompatActivity {
    EditText txtPassword;
    EditText txtUsername;
    TextView txtUsuarioActual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_cambiar_nombre_usuario);
        txtPassword = findViewById(R.id.inputPasswordTelefono);
        txtUsername = findViewById(R.id.inputCambiarUsername);
        txtUsuarioActual = findViewById(R.id.txtUsuarioActual);
        txtUsuarioActual.setText("Usuario actual: " + ConfiguracionUsuario.getNombreUsuario());
        Drawable drawable1 = txtPassword.getBackground();
        Drawable drawable2 = txtUsername.getBackground();
        drawable1.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_IN);
        drawable2.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_IN);
    }
    public void btnAceptarOnClick(View view){
        String password = txtPassword.getText().toString();
        String username = txtUsername.getText().toString();
        //se comprueba que los datos no esten vacios
        if(password.equals("") || username.equals("")){
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
        if(!ComprobarDatos.comprobarNombre(username)){
            CrearToast.toastLargo("Error, el nombre de usuario no cumple con los requisitos",getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(),100);
            return;
        }
        if(ComprobarDatos.existeUsuario(username) == 0){
            CrearToast.toastLargo("Error, ya existe un usuario con ese nombre",getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(),100);
            return;
        }
        if(GestionUsuarios.modificarNombre(ConfiguracionUsuario.getNombreUsuario(),username)){
            CrearToast.toastLargo("Nombre de usuario cambiado correctamete",getApplicationContext()).show();
            ConfiguracionUsuario.setNombreUsuario(username);
            txtUsuarioActual.setText("Usuario actual: " + ConfiguracionUsuario.getNombreUsuario());
        }else{
            CrearToast.toastLargo("Error al cambiar el nombre de usuario",getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(),100);
        }
    }
}
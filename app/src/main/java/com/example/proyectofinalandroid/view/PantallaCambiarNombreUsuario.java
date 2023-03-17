package com.example.proyectofinalandroid.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.EditText;

import com.example.proyectofinalandroid.R;

public class PantallaCambiarNombreUsuario extends AppCompatActivity {
    EditText txtPassword;
    EditText txtUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_cambiar_nombre_usuario);
        txtPassword = findViewById(R.id.inputPasswordTelefono);
        txtUsername = findViewById(R.id.inputCambiarUsername);
        Drawable drawable1 = txtPassword.getBackground();
        Drawable drawable2 = txtUsername.getBackground();
        drawable1.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_IN);
        drawable2.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_IN);
    }
}
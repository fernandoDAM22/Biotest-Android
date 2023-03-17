package com.example.proyectofinalandroid.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.EditText;

import com.example.proyectofinalandroid.R;

/**
 * Este clase pinta la pantalla que permite al usuario cambiar su contrasena
 * @author Fernando
 */
public class PantallaCambiarPassword extends AppCompatActivity {
    /**
     * Input para la contrasena antigua
     */
    EditText txtOldPassword;
    /**
     * Input para la contrasena nueva
     */
    EditText txtNewPassword;
    /**
     * Input para la repeticion de la nueva contrasena
     */
    EditText txtNewPassword2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_cambiar_password);
        //obtenemos los inputs
        txtOldPassword = findViewById(R.id.txtOldPassword);
        txtNewPassword = findViewById(R.id.txtNewPassword);
        txtNewPassword2 = findViewById(R.id.txtNewPassword2);
        //creamos los objetos drawable
        Drawable drawable1 = txtOldPassword.getBackground();
        Drawable drawable2 = txtNewPassword.getBackground();
        Drawable drawable3 = txtNewPassword2.getBackground();
        //les aplicamos un filtro blanco para cambiar el color de la linea
        drawable1.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_IN);
        drawable2.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_IN);
        drawable3.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_IN);

    }
}
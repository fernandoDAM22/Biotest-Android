package com.example.proyectofinalandroid.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
    public void btnAceptarOnClick(View view){
        //obtenemos los datos
        String oldPassword = txtOldPassword.getText().toString();
        String newPassword = txtNewPassword.getText().toString();
        String newPassword2 = txtNewPassword2.getText().toString();
        //comprobamos que no esten vacios
        if(oldPassword.equals("") || newPassword.equals("") || newPassword2.equals("")){
            CrearToast.toastLargo("Rellene todos los campos",getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(),100);
            return;
        }
        //comprobamos la contrasena vieja sea la del usuario
        if(!Cifrado.SHA256(oldPassword).equals(Login.obtenerDatos(ConfiguracionUsuario.getNombreUsuario(), Codigos.OBTENER_PASSWORD))){
            CrearToast.toastLargo("La contraseña actual no es correcta",getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(),100);
            return;
        }
        //comprobamos que las dos contrasenas sean iguales
        if(!ComprobarDatos.comprobarPassword(newPassword,newPassword2)){
            CrearToast.toastLargo("Las contraseñas no coinciden",getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(),100);
            return;
        }
        //comprobamos que la contraseña cumpla con los requisitos
        if(!ComprobarDatos.comprobarFormatoPassword(newPassword)){
            CrearToast.toastLargo("La contraseña no cumple con los requisitos",getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(),100);
            return;
        }
        if(GestionUsuarios.cambiarPassword(ConfiguracionUsuario.getNombreUsuario(),Cifrado.SHA256(newPassword))){
            CrearToast.toastLargo("Contraseña cambiada correctamente",getApplicationContext()).show();
        }else{
            CrearToast.toastLargo("Error al cambiar la contraseña",getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(),100);
        }


    }
}
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

/**
 * Este clase pinta la pantalla que permite al usuario cambiar su contrasena
 * @author Fernando
 */
public class PantallaCambiarPassword extends AppCompatActivity {
    public static final String ERROR_FORMATO_PASSWORD = "La contraseña no cumple con los requisitos";
    /**
     * Input para la contrasena antigua
     */
    private EditText txtOldPassword;
    /**
     * Input para la contrasena nueva
     */
    private EditText txtNewPassword;
    /**
     * Input para la repeticion de la nueva contrasena
     */
    private EditText txtNewPassword2;
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
        //obtenemos los datos
        String oldPassword = txtOldPassword.getText().toString();
        String newPassword = txtNewPassword.getText().toString();
        String newPassword2 = txtNewPassword2.getText().toString();
        //comprobamos que los campos no esten vacios
        if(oldPassword.equals("") || newPassword.equals("") || newPassword2.equals("")){
            CrearToast.toastLargo(Mensajes.ERROR_CAMPOS_VACIOS,getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(),100);
            return;
        }
        //comprobamos la contrasena vieja sea la del usuario
        if(!Cifrado.SHA256(oldPassword).equals(Login.obtenerDatos(ConfiguracionUsuario.getNombreUsuario(), Codigos.OBTENER_PASSWORD))){
            CrearToast.toastLargo(Mensajes.ERROR_PASSWORD_ACTUAL_INCORRECTA,getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(),100);
            return;
        }
        //comprobamos que las dos contrasenas sean iguales
        if(!ComprobarDatos.comprobarPassword(newPassword,newPassword2)){
            CrearToast.toastLargo(Mensajes.ERROR_PASSWORDS,getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(),100);
            return;
        }
        //comprobamos que la contraseña cumpla con los requisitos
        if(!ComprobarDatos.comprobarFormatoPassword(newPassword)){
            CrearToast.toastLargo(ERROR_FORMATO_PASSWORD,getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(),100);
            return;
        }
        //cambiamos la contrasena y mostramos el mensaje correspondiente
        if(GestionUsuarios.cambiarPassword(ConfiguracionUsuario.getNombreUsuario(),Cifrado.SHA256(newPassword))){
            CrearToast.toastLargo(Mensajes.CAMBIO_PASSWORD_CORRECTO,getApplicationContext()).show();
            limpiarCampos();
        }else{
            CrearToast.toastLargo(Mensajes.ERROR_CAMBIO_CONTRASENA,getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(),100);
        }
    }

    /**
     * Este metodo permite limpiar los inputs
     * @author Fernando
     */
    private void limpiarCampos(){
        txtOldPassword.setText("");
        txtNewPassword.setText("");
        txtNewPassword2.setText("");
    }

    /**
     * Este metodo se ejecuta cuando el usuario pulsa el boton de aceptar
     * @param view es el boton que se pulsa
     * @author Fernando
     */
    public void btnCancelarOnClick(View view){
        limpiarCampos();
    }
}
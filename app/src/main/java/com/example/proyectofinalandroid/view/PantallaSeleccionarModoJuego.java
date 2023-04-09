package com.example.proyectofinalandroid.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.proyectofinalandroid.MainActivity;
import com.example.proyectofinalandroid.R;
import com.example.proyectofinalandroid.controller.baseDeDatos.Constantes;
import com.example.proyectofinalandroid.controller.baseDeDatos.GestionCuestionarios;
import com.example.proyectofinalandroid.controller.tools.CrearToast;
import com.example.proyectofinalandroid.controller.tools.Mensajes;
import com.example.proyectofinalandroid.controller.tools.TipoPartida;
import com.example.proyectofinalandroid.controller.tools.Vibracion;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Esta clase se encarga de gestionar la pantalla que permite al
 * usuario seleccionar un modo de juego
 *
 * @author Fernando
 */
public class PantallaSeleccionarModoJuego extends AppCompatActivity {
    /**
     * Color que se le asigna a los toggleButtons cuando estan seleccionados
     */
    int colorON;
    /**
     * Color que se le asigna a los toggleButtons cuando no estan seleccionados
     */
    int colorOFF;
    /**
     * Boton que permite seleccionar el modo clasico
     */
    ToggleButton btnModoClasico;
    /**
     * Boton que permite seleccionar el modo sin fallos
     */
    ToggleButton btnModoSinFallos;
    /**
     * Boton que permite seleccionar el modo libre
     */
    ToggleButton btnModoLibre;
    /**
     * Boton que permite seleccionar el modo cuestionarios
     */
    ToggleButton btnModoCuestionarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        colorON = ContextCompat.getColor(this, R.color.color_boton_encendido);
        colorOFF = ContextCompat.getColor(this, R.color.color_boton_apagado);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_seleccionar_modo_juego);
        // Obtener los botones por su ID
        btnModoClasico = findViewById(R.id.btnModoClasico);
        btnModoSinFallos = findViewById(R.id.btnModoSinFallos);
        btnModoLibre = findViewById(R.id.btnModoLibre);
        btnModoCuestionarios = findViewById(R.id.btnModoCuestionarios);
        btnModoClasico.setBackgroundColor(colorOFF);
        btnModoSinFallos.setBackgroundColor(colorOFF);
        btnModoLibre.setBackgroundColor(colorOFF);
        btnModoCuestionarios.setBackgroundColor(colorOFF);

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
                intent = new Intent(this,MainActivity.class);
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
     * Este metodo se ejecuta cuando se pulsa alguno de los 4 ToggleButtons
     *
     * @param view es el boton que se pulsa
     * @author Fernando
     */
    public void btnClick(View view) {
        //se comprueba si el boton pulsado esta seleccionado o no
        boolean on = ((ToggleButton) view).isChecked();
        //se pone el color de fondo correspondiente al boton
        cambiarColorFondo(view, on);
        //se desmarcan los demas botones
        desmarcarOtrosBotones(view);
        //se coloca la descripcion en la etiqueta correspondiente
        colocarDescripcion(view);
    }

    /**
     * Este metodo cambia el color de fondo de los botones en funcion de
     * si estan seleccionados o no
     *
     * @param view   es el boton que se pulsa
     * @param estado indica si estan seleccionados o no
     * @author Fernando
     */
    private void cambiarColorFondo(View view, boolean estado) {
        if (estado) {
            view.setBackgroundColor(colorON);
        } else {
            view.setBackgroundColor(colorOFF);
        }
    }

    /**
     * Este metodo desmcarca los botones a excepcion del que se ha pulsado
     *
     * @param view es el boton que se pulsa
     * @author Fernando
     */
    private void desmarcarOtrosBotones(View view) {
        //se obtiene el contenedor de los botones
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        //se recorren los elementos del contenedor
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View child = viewGroup.getChildAt(i);
            //en caso de que el elementos sea un ToogleButton y sea diferente al boton pulsado
            if (child instanceof ToggleButton && child != view) {
                //se desmcarca y se le cambia el color
                cambiarColorFondo(child, false);
                ((ToggleButton) child).setChecked(false);
            }
        }
    }

    /**
     * Este metodo coloca la descripcion de los diferentes modos de
     * juego en la etiqueta correspondiente
     *
     * @param view es el boton que se pulsa
     * @author Fernando
     */
    public void colocarDescripcion(View view) {
        TextView textView = findViewById(R.id.txtDescripcion);
        ToggleButton boton = (ToggleButton) view;
        if (boton.equals(btnModoClasico)) {
            textView.setText(Mensajes.DESCRIPCION_MODO_CLASICO);
        } else if (boton.equals(btnModoLibre)) {
            textView.setText(Mensajes.DESCRIPCION_MODO_LIBRE);
        } else if (boton.equals(btnModoSinFallos)) {
            textView.setText(Mensajes.DESCRIPCION_MODO_SIN_FALLOS);
        } else {
            textView.setText(Mensajes.DESCRIPCION_CUESTIONARIOS);
        }
    }

    /**
     * Este metodo se ejecuta cuando se pulsa el boton de cancelar
     * @param view es el boton que se pulsa
     * @author Fernando
     */
    public void onClickCancelar(View view) {
        //se obtiene el contenedor de los botones
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        //se recorren los elementos del contenedor
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View child = viewGroup.getChildAt(i);
            //en caso de que el elementos sea un ToogleButton se desmarcan
            if (child instanceof ToggleButton) {
                //se desmcarca y se le cambia el color
                cambiarColorFondo(child, false);
                ((ToggleButton) child).setChecked(false);
            }
        }
        ((TextView) findViewById(R.id.txtDescripcion)).setText("");
    }

    /**
     * Este metodo permite retornar el boton seleccionado
     * @return el boton seleccionado, null si no hay ninguno seleccionado
     * @param viewGroup es el layout donde se encuentran los botones
     * @author Fernando
     */
    private ToggleButton obtenerBotonSeleccionado(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View child = viewGroup.getChildAt(i);
            if (child instanceof ToggleButton && ((ToggleButton) child).isChecked()) {
                return (ToggleButton) child;
            }
        }
        return null;
    }

    /**
     * Este metodo se ejecuta cuando se pulsa el boton de jugar
     * @param view es el boton que se pulsa
     * @author Fernando
     */
    public void onClickJugar(View view) {
        //se obtiene el boton
        ToggleButton boton = obtenerBotonSeleccionado(findViewById(R.id.grupoBotones));
        //en caso de que sea nulo
        if(boton == null){
            CrearToast.toastLargo("No has seleccionado ningun boton",getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(),100);
            return;
        }
        //se crea el tipo partida y el intent
        TipoPartida tipoPartida;
        Intent intent = new Intent(this,PantallaJugar.class);
        //se lanza el intent con el tipo de partida correspondiente en funcion al boton seleccionado
        if (boton.equals(btnModoClasico)) {
            tipoPartida = TipoPartida.MODO_CLASICO;
        } else if (boton.equals(btnModoLibre)) {
            tipoPartida = TipoPartida.MODO_LIBRE;
        } else if (boton.equals(btnModoSinFallos)) {
            tipoPartida = TipoPartida.MODO_SIN_FALLOS;
        } else {
            //este metodo ya manda los extras con el tipo de partida y el cuestionario elegido, por eso el return
            mostrarDialogoCuestionarios();
            return;
        }
        //se manda el extra con el tipo de partida
        intent.putExtra("tipo",tipoPartida);
        startActivity(intent);
    }

    /**
     * Este metodo permite mostrar un AlertDialog con los cuestionarios disponibles
     * @author Fernando
     */
    private void mostrarDialogoCuestionarios() {
        Intent intent = new Intent(this, PantallaJugar.class);
        ArrayList<String> cuestionarios = null;
        try {
            cuestionarios = GestionCuestionarios.obtenerCuestionariosCompletos();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        if (cuestionarios == null || cuestionarios.isEmpty()) {
            CrearToast.toastLargo("No hay cuestionarios disponibles",getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(),100);
            return;
        }

        // Crear un ArrayAdapter para mostrar la lista de cuestionarios
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, cuestionarios);

        // Crear el diálogo de selección de cuestionarios
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccione un cuestionario");
        ArrayList<String> finalCuestionarios = cuestionarios;
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Obtener el cuestionario seleccionado y lanzar el intent
                String cuestionario = finalCuestionarios.get(i);
                intent.putExtra("tipo", TipoPartida.CUESTIONARIOS);
                intent.putExtra("cuestionario", cuestionario);
                startActivity(intent);
            }
        });

        builder.show();
    }



}
package com.example.proyectofinalandroid.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import com.example.proyectofinalandroid.R;

/**
 * Esta clase se encarga de gestionar la pantalla que permite al
 * usuario seleccionar un modo de juego
 * @author Fernando
 */
public class PantallaSeleccionarModoJuego extends AppCompatActivity {
    int colorON;
    int colorOFF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        colorON = ContextCompat.getColor(this, R.color.color_boton_encendido);
        colorOFF = ContextCompat.getColor(this, R.color.color_boton_apagado);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_seleccionar_modo_juego);
        // Obtener los botones por su ID
        ToggleButton boton1 = findViewById(R.id.btnModoClasico);
        ToggleButton boton2 = findViewById(R.id.btnModoSinFallos);
        ToggleButton boton3 = findViewById(R.id.btnModoLibre);
        ToggleButton boton4 = findViewById(R.id.btnModoCuestionarios);
        boton1.setBackgroundColor(colorOFF);
        boton2.setBackgroundColor(colorOFF);
        boton3.setBackgroundColor(colorOFF);
        boton4.setBackgroundColor(colorOFF);

    }
    public void onToggleClicked(View view) {
        boolean on = ((ToggleButton) view).isChecked();
        // Cambiar el color de fondo en función del estado del botón
        if (on) {
            view.setBackgroundColor(colorON);
        } else {
            view.setBackgroundColor(colorOFF);
        }
        // Desmarcar los otros ToggleButtons
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View child = viewGroup.getChildAt(i);
            if (child instanceof ToggleButton && child != view) {
                ((ToggleButton) child).setChecked(false);

                // Cambiar el color de fondo de los otros botones
                child.setBackgroundColor(getResources().getColor(R.color.color_boton_apagado));
            }
        }
    }

}
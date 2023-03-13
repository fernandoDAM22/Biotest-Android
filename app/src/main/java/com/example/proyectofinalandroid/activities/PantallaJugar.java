package com.example.proyectofinalandroid.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.proyectofinalandroid.R;
import com.example.proyectofinalandroid.controller.tools.CrearToast;
import com.example.proyectofinalandroid.controller.tools.TipoPartida;

public class PantallaJugar extends AppCompatActivity {
    TipoPartida tipoPartida;
    String cuestionario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_jugar);
        // Obtener el extra tipo (obligatorio)
        tipoPartida = (TipoPartida) getIntent().getSerializableExtra("tipo");
        // Verificar si el extra cuestionario existe
        if (getIntent().hasExtra("cuestionario")) {
            // Obtener el extra cuestionario
            cuestionario = getIntent().getStringExtra("cuestionario");
            // Hacer algo con el extra cuestionario...
        }
    }
}
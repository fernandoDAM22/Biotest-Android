package com.example.proyectofinalandroid.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.proyectofinalandroid.R;
import com.example.proyectofinalandroid.controller.baseDeDatos.Constantes;
import com.example.proyectofinalandroid.controller.baseDeDatos.GestionPreguntas;
import com.example.proyectofinalandroid.controller.controlPartida.ConsultasPartida;
import com.example.proyectofinalandroid.controller.tools.CrearToast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class PantallaResultado extends AppCompatActivity {
    private int backPressCount = 0;
    private Timer backPressTimer;
    int idPartida;
    ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_resultado);
        idPartida = getIntent().getIntExtra("id_partida",-1);
        lista = findViewById(R.id.listPreguntas);
        cargarLista();
    }
    @Override
    public void onBackPressed() {
        if (backPressCount == 0) {
            backPressCount++;
           CrearToast.toastCorto("Pulse de nuevo para salir",getApplicationContext()).show();

            backPressTimer = new Timer();
            backPressTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    backPressCount = 0;
                }
            }, 2000); // Aquí puedes ajustar la duración del temporizador en milisegundos (2 segundos en este ejemplo)
        } else {
            backPressTimer.cancel();

            // Aquí lanzas la actividad que desees en lugar de volver a la actividad anterior
            Intent intent = new Intent(this, PantallaSeleccionarModoJuego.class);
            startActivity(intent);
            backPressCount = 0;
        }
    }
    private void cargarLista() {
        if(idPartida == -1){
            CrearToast.toastCorto("No se ha podido obtener las preguntas de la partida",getApplicationContext()).show();
        }
        ArrayList<String[]> preguntas = ConsultasPartida.obtenerPreguntasPartida(idPartida);

        // Crear un ArrayList de Strings con el contenido de las preguntas y su estado (acertada o no)
        ArrayList<Pair<String, Boolean>> contenidoPreguntas = new ArrayList<>();
        for (String[] pregunta : preguntas) {
            int idPregunta = GestionPreguntas.obtenerIdPregunta(pregunta[0]);
            System.err.println(idPregunta);
            boolean preguntaAcertada = GestionPreguntas.preguntaAcertada(idPartida,idPregunta); // aquí debes llamar al método que verifica si la pregunta fue acertada
            contenidoPreguntas.add(new Pair<>(pregunta[0], preguntaAcertada)); // aquí debes sustituir 0 por el índice que corresponda a la pregunta
        }

        // Crear un ArrayAdapter personalizado y asignarlo al ListView
        ArrayAdapter<Pair<String, Boolean>> adapter = new ArrayAdapter<Pair<String, Boolean>>(this, android.R.layout.simple_list_item_1, contenidoPreguntas) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                String pregunta = contenidoPreguntas.get(position).first;
                ((TextView) view.findViewById(android.R.id.text1)).setText(pregunta);

                boolean preguntaAcertada = contenidoPreguntas.get(position).second;
                if (preguntaAcertada) {
                    view.setBackgroundColor(Color.GREEN);
                } else {
                    view.setBackgroundColor(Color.RED);
                }

                return view;
            }

        };
        lista.setAdapter(adapter);
    }


}
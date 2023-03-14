package com.example.proyectofinalandroid.controller.controlPartida;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.proyectofinalandroid.R;
import com.example.proyectofinalandroid.controller.baseDeDatos.GestionCuestionarios;
import com.example.proyectofinalandroid.controller.baseDeDatos.GestionPreguntas;
import com.example.proyectofinalandroid.controller.tools.PilaSinRepetidos;
import com.example.proyectofinalandroid.model.Partida;
import com.example.proyectofinalandroid.model.Pregunta;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Esta clase permite jugar una partida en modo cuestionario,
 * se resuelven las preguntas de un cuestionario previamente creado
 * @author Fernando
 */
public class PartidaCuestionario extends GestionPartida{
    PilaSinRepetidos pila;
    /**
     * Contienen los ids de todas las preguntas de la base de datos
     */
    private ArrayList<Integer> idPreguntas;
    /**
     * Objeto de la clase pregunta donde guardamos los datos de la pregunta
     */
    Pregunta pregunta;
    /**
     * Es el contador de las respuestas correctas de la partida
     */
    private int contadorPreguntasCorrectas;
    /**
     * Es el contador de las preguntas incorrectas en la partida
     */
    private int contadorRespuestasIncorrectas;
    Context context;

    public PartidaCuestionario(Partida partida, Button btnOpcion1, Button btnOpcion2, Button btnOpcion3, Button btnOpcion4, TextView enunciado,String cuestionario,Context context) {
        super(partida, btnOpcion1, btnOpcion2, btnOpcion3, btnOpcion4, enunciado);
        pila = new PilaSinRepetidos();
        idPreguntas = GestionCuestionarios.obtenerIdPreguntas(cuestionario);
        this.context = context;
        contadorPreguntasCorrectas = 0;
        contadorRespuestasIncorrectas = 0;
        ConsultasPartida.insertarPartida(partida);
    }

    /**
     * Este metodo nos permite saber cuando se ha terminado una partida
     * @return true si se termina, false si no
     * @author Fernando
     */
    @Override
    public boolean fin() {
        //negamos pila.isEmpty(), si la pila esta llena retorna false, y si esta vacia retorna true
        return !pila.isEmpty();
    }

    /**
     * Este metodo nos permite responder una pregunta
     * @param boton es el boton que se pulsa
     * @return true si se acierta la pregunta, false si no
     * @author Fernando
     */
    @Override
    public boolean responder(Button boton) {
        int colorCorrecto = ContextCompat.getColor(context, R.color.color_correcto);
        int colorIncorrecto = ContextCompat.getColor(context, R.color.color_incorrecto);
        if (boton.getText().toString().equals(pregunta.getRespuestaCorrecta())) {
            boton.setBackgroundColor(colorCorrecto);
            contadorPreguntasCorrectas++;
            return true;
        } else {
            contadorRespuestasIncorrectas++;
            boton.setBackgroundColor(colorIncorrecto);
            if (btnOpcion1.getText().equals(pregunta.getRespuestaCorrecta())) {
                btnOpcion1.setBackgroundColor(colorCorrecto);
            }
            if (btnOpcion2.getText().equals(pregunta.getRespuestaCorrecta())) {
                btnOpcion2.setBackgroundColor(colorCorrecto);
            }
            if (btnOpcion3.getText().equals(pregunta.getRespuestaCorrecta())) {
                btnOpcion3.setBackgroundColor(colorCorrecto);
            }
            if (btnOpcion4.getText().equals(pregunta.getRespuestaCorrecta())) {
                btnOpcion4.setBackgroundColor(colorCorrecto);
            }
            return false;
        }
    }
    /**
     * Este metodo permite obtener los datos de la pregunta seleccionada y
     * colocarlos en la pantalla
     *
     * @return el id de la pregunta seleccionada
     */
    public int obtenerDatos() {
        //sacamos un id de la lista
        int id = pila.pop();
        /*
        borramos el id de la lista de ids de todas las preguntas, para evitar
        que se responde la misma pregunta mas de una vez en la misma partida
         */
        idPreguntas.removeIf(x -> x == id);
        //obtenemos los datos de la pregunta
        pregunta = GestionPreguntas.obtenerDatos(id);
        if (pregunta != null) {
            //obtenemos las respuestas de la pregunta
            String[] respuestas = pregunta.obtenerRespuestas();
            //colocamos el enunciado de la pregunta
            enunciado.setText(pregunta.getEnunciado());
            //desordenamos las respuesta
            Collections.shuffle(Arrays.asList(respuestas));
            //las colocamos en los botones
            Button[] botones = {btnOpcion1,btnOpcion2,btnOpcion3,btnOpcion4};
            for (int i = 0; i < botones.length; i++) {
                colocarRespuestas(botones[i],respuestas[i]);
            }
        }
        return id;

    }

    /**
     * Este metodo permite seleccionar una pregunta
     *
     * @author Fernando
     */
    public void seleccionarPregunta() {
        while (pila.size() < 10) {
            int indice = (int) (Math.random() * idPreguntas.size());
            pila.push(idPreguntas.get(indice));
        }
    }

    /**
     * Este metodo nos permite realizar un ciclo en la partida, (seleccionar una
     * nueva pregunta y restablecer los colores de los botones
     * @return el id de la pregunta seleccionada
     */
    public int ciclo() {
        int numero = obtenerDatos();
        restablecerColores(context);
        return numero;
    }


    public int getContadorPreguntasCorrectas() {
        return contadorPreguntasCorrectas;
    }

    public int getContadorRespuestasIncorrectas() {
        return contadorRespuestasIncorrectas;
    }
}
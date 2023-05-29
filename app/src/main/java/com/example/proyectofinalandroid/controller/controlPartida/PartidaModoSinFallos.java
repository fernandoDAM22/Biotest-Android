package com.example.proyectofinalandroid.controller.controlPartida;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.proyectofinalandroid.controller.baseDeDatos.GestionPreguntas;
import com.example.proyectofinalandroid.controller.tools.PilaSinRepetidos;
import com.example.proyectofinalandroid.controller.tools.Vibracion;
import com.example.proyectofinalandroid.model.Partida;
import com.example.proyectofinalandroid.model.Pregunta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import com.example.proyectofinalandroid.R;

/**
 * Esta clase nos permite jugar una partida en modo sin fallos
 * @author Fernando
 */
public class PartidaModoSinFallos extends GestionPartida {
    PilaSinRepetidos pila;
    /**
     * Contienen los ids de todas las preguntas de la base de datos
     */
    private ArrayList<Integer> idPreguntas;
    /**
     * Objeto pregunta
     */
    Pregunta pregunta;
    /**
     * Es el contador de las respuestas incorrectas en la partida
     */
    private int contadorPreguntasCorrectas;
    /**
     * Es el contador de las preguntas incorrectas en la partida
     */
    private int contadorRespuestasIncorrectas;
    /**
     * Numero de preguntas respondidas
     */
    private int numeroPreguntas;
    /**
     * Nos indica si se acerto la pregunta o no
     */
    boolean fallo;
    /**
     * Contexto de la aplicacion
     */
    Context context;

    /**
     * Constructor con parametros
     * @param partida objeto de la clase partida
     * @param btnOpcion1 boton para la primera respuesta de la partida
     * @param btnOpcion2 boton para la segunda respuesta de la partida
     * @param btnOpcion3 boton para la tercera respuesta de la partida
     * @param btnOpcion4 boton para la cuarta respuesta de la partida
     * @param enunciado TextView para el enunciado de la pregunta
     * @param context es el contexto de la aplicacion
     * @author Fernando
     */
    public PartidaModoSinFallos(Partida partida, Button btnOpcion1, Button btnOpcion2, Button btnOpcion3, Button btnOpcion4, TextView enunciado, Context context) {
        super(partida, btnOpcion1, btnOpcion2, btnOpcion3, btnOpcion4, enunciado);
        this.context = context;
        pila = new PilaSinRepetidos();
        idPreguntas = GestionPreguntas.obtenerIds();
        contadorPreguntasCorrectas = 0;
        contadorRespuestasIncorrectas = 0;
        numeroPreguntas = 0;
        fallo = false;
        ConsultasPartida.insertarPartida(partida);
    }

    /**
     * Este metodo permite seleccionar una pregunta, cada vez que se ejecuta
     * obtiene una pregunta diferente, esto permite que no se respondan
     * preguntas repetidas en la misma partida
     *
     * @author Fernando
     */
    public void seleccionarPregunta() {
        int tam = pila.size();
        //repetira el bucle hasta que se encuentre un numero el cual no este ya en la pila
        while (tam == pila.size()) {
            int indice = (int) (Math.random() * idPreguntas.size());
            pila.push(idPreguntas.get(indice));
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
            //colocamos el enunciado de la pregunta
            enunciado.setText(pregunta.getEnunciado());
            //obtenemos las respuestas de la pregunta
            String[] respuestas = pregunta.obtenerRespuestas();
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
     * Este metodo permite realizar un ciclo, (seleccionar una pregunta, obtener los datos y restablecer los colores)
     *
     * @return el id de la pregunta que se ha seleccionado
     * @author Fernando
     */
    public int ciclo() {
        seleccionarPregunta();
        int numero = obtenerDatos();
        restablecerColores(context);
        numeroPreguntas++;
        return numero;
    }

    /**
     * Este metodo nos permite saber cuando se ha terminado la partida
     * @return true si se termina la partida, false si no
     * @author Fernando
     */
    @Override
    public boolean fin() {
        //si se falla una pregunta o se responden mas de 100 se termina la partida
        return fallo || numeroPreguntas >= 100;
    }

    /**
     * Este metodo permite responder una pregunta
     *
     * @param boton es el boton que se pulsa
     * @return true si se acierta la pregunta, false si no
     */
    public boolean responder(Button boton) {
        //se obtienen los colores que se le asignan a los botones
        int colorCorrecto = ContextCompat.getColor(context, R.color.color_correcto);
        int colorIncorrecto = ContextCompat.getColor(context, R.color.color_incorrecto);
        //en caso de que se responda correctamente la pregunta
        if (boton.getText().toString().equals(pregunta.getRespuestaCorrecta())) {
            boton.setBackgroundColor(colorCorrecto);
            contadorPreguntasCorrectas++;
            return true;
        }
        //en caso de que se responda incorrectamente
        else {
            contadorRespuestasIncorrectas++;
            boton.setBackgroundColor(colorIncorrecto);
            //se pone de color verde el boton que contiene la respuesta correcta
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
            fallo = true;
            Vibracion.vibrar(context,100);
            return false;
        }
    }
    public int getContadorPreguntasCorrectas() {
        return contadorPreguntasCorrectas;
    }

    public int getContadorRespuestasIncorrectas() {
        return contadorRespuestasIncorrectas;
    }
}

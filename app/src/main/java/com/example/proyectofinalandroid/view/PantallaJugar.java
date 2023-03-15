package com.example.proyectofinalandroid.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.proyectofinalandroid.R;
import com.example.proyectofinalandroid.controller.baseDeDatos.GestionUsuarios;
import com.example.proyectofinalandroid.controller.controlPartida.ConsultasPartida;
import com.example.proyectofinalandroid.controller.controlPartida.GestionPartida;
import com.example.proyectofinalandroid.controller.controlPartida.PartidaCuestionario;
import com.example.proyectofinalandroid.controller.controlPartida.PartidaModoClasico;
import com.example.proyectofinalandroid.controller.controlPartida.PartidaModoLibre;
import com.example.proyectofinalandroid.controller.controlPartida.PartidaModoSinFallos;
import com.example.proyectofinalandroid.controller.tools.CrearToast;
import com.example.proyectofinalandroid.controller.tools.TipoPartida;
import com.example.proyectofinalandroid.controller.tools.Vibracion;
import com.example.proyectofinalandroid.controller.usuario.ConfiguracionUsuario;
import com.example.proyectofinalandroid.model.Partida;

/**
 * Esta clase permite al usuario jugar una partida en los diferentes modos de juego
 *
 * @author Fernando
 */
public class PantallaJugar extends AppCompatActivity {
    /**
     * Tipo de partida que se esta jugando
     */
    TipoPartida tipoPartida;
    /**
     * Cuestionario que se resuelve en caso de que el modo elegido sea modo cuestionario
     */
    String cuestionario;
    /**
     * Botones con las diferentes respuestas de la pregunta
     */
    private Button btnOpcion1, btnOpcion2, btnOpcion3, btnOpcion4;
    /**
     * Partida modo libre por si el usuario decice jugar este tipo de partida
     */
    private PartidaModoLibre partidaModoLibre;
    /**
     * Partida modo sin fallos por si el usuario decice jugar este tipo de partida
     */
    private PartidaModoSinFallos partidaModoSinFallos;
    /**
     * Partida modo clasico por si el usuario decice jugar este tipo de partida
     */
    private PartidaModoClasico partidaModoClasico;
    /**
     * Partida modo cuestionario por si el usuario decice jugar este tipo de partida
     */
    private PartidaCuestionario partidaCuestionario;
    /**
     * Objeto de tipo modelo para la partida
     */
    private Partida partida;
    /**
     * Bandera para saber si la pregunta a sido respondida o no
     */
    private boolean bandera;
    /**
     * Id de la pregunta que se esta respondiendo
     */
    private int idPregunta;
    /**
     * Id de la partida que se esta jugando
     */
    private int idPartida;
    /**
     * Id del usuario que esta jugando la partida
     */
    private int idUsuario;
    /**
     * TextView para colocar la informacion en pantalla
     */
    private TextView labelPregunta, labelRespuestasCorrectas, labelRespuestasIncorrectas;
    /**
     * Boton que permite finalizar una partida en caso de que el modo elegido sea modo libre
     */
    private Button botonFinalizar;


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
        initComponents();
        jugar();
    }

    /**
     * Metodo obBackPressed sobreescrito para evitar que el usuario pueda atras
     * usando el boton de retroceso del movil en mitad de una partida
     * @author Fernando
     */
    @Override
    public void onBackPressed() {
        CrearToast.toastCorto("No se puede volver atras hasta que finalices la partida",getApplicationContext()).show();
        Vibracion.vibrar(getApplicationContext(),100);
    }

    /**
     * Este metodo se encarga de inicializar todas las variables con los
     * elementos correspondientes del layout
     *
     * @author Fernando
     */
    private void initComponents() {
        btnOpcion1 = findViewById(R.id.btnOpcion1);
        btnOpcion2 = findViewById(R.id.btnOpcion2);
        btnOpcion3 = findViewById(R.id.btnOpcion3);
        btnOpcion4 = findViewById(R.id.btnOpcion4);
        labelPregunta = findViewById(R.id.txtEnunciado);
        labelRespuestasCorrectas = findViewById(R.id.txtRespuestasCorrectas);
        labelRespuestasIncorrectas = findViewById(R.id.txtRespuestasIncorrectas);
        botonFinalizar = findViewById(R.id.btnFinalizar);
        bandera = true;
    }

    /**
     * Este metodo permite iniciar una partida
     *
     * @author Fernando
     */
    private void jugar() {
        if (tipoPartida != TipoPartida.MODO_LIBRE) {
            LinearLayout layout = findViewById(R.id.layoutBotones);
            layout.removeView(botonFinalizar);
        }
        //se inicia la partida en funcion del tipo de partida elegido
        switch (tipoPartida) {
            case MODO_LIBRE:
                jugarModoLibre();
                break;
            case MODO_SIN_FALLOS:
                jugarModoSinFallos();
                break;
            case MODO_CLASICO:
                jugarModoClasico();
                break;
            case CUESTIONARIOS:
                jugarCuestionarios();
                break;
        }
    }

    /**
     * Este metodo permite guardar el id de la partida y el id del usuario,
     * se guardan en variables globales porque sera necesario acceder a ellos
     * desde otros metodos
     *
     * @author Fernando
     */
    private void iniciarPartida() {
        idPartida = GestionPartida.obtenerId();
        idUsuario = GestionUsuarios.obtenerIdUsuario(ConfiguracionUsuario.getNombreUsuario());
    }


    /**
     * Este metodo permite jugar una partida en modo libre
     *
     * @author Fernando
     */
    private void jugarModoLibre() {
        iniciarPartida();
        //nos aseguramos de que no hayan errores al obtener los datos
        if (idPartida == -1 || idUsuario == -1) {
            //en caso de no poder crear la partida avisamos al usuario y hacemos vibrar el telefono
            CrearToast.toastLargo("No se ha podido inicar la partida", getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(), 100);
            return;
        }
        partida = new Partida(idPartida, tipoPartida.toString(), idUsuario);
        partidaModoLibre = new PartidaModoLibre(partida, btnOpcion1, btnOpcion2, btnOpcion3, btnOpcion4, labelPregunta, getApplicationContext());
        idPregunta = partidaModoLibre.ciclo();
    }

    /**
     * Este metodo permite jugar una partida en modo sin fallos
     *
     * @author Fernando
     */
    private void jugarModoSinFallos() {
        iniciarPartida();
        //nos aseguramos de que no hayan errores al obtener los datos
        if (idPartida == -1 || idUsuario == -1) {
            //en caso de no poder crear la partida avisamos al usuario y hacemos vibrar el telefono
            CrearToast.toastLargo("No se ha podido inicar la partida", getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(), 100);
            return;
        }
        partida = new Partida(idPartida, tipoPartida.toString(), idUsuario);
        partidaModoSinFallos = new PartidaModoSinFallos(partida, btnOpcion1, btnOpcion2, btnOpcion3, btnOpcion4, labelPregunta, getApplicationContext());
        idPregunta = partidaModoSinFallos.ciclo();
    }

    /**
     * Este metodo permite jugar una partida en modo clasico
     *
     * @author Fernando
     */
    private void jugarModoClasico() {
        iniciarPartida();
        //nos aseguramos de que no hayan errores al obtener los datos
        if (idPartida == -1 || idUsuario == -1) {
            //en caso de no poder crear la partida avisamos al usuario y hacemos vibrar el telefono
            CrearToast.toastLargo("No se ha podido inicar la partida", getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(), 100);
            return;
        }
        partida = new Partida(idPartida, tipoPartida.toString(), idUsuario);
        partidaModoClasico = new PartidaModoClasico(partida, btnOpcion1, btnOpcion2, btnOpcion3, btnOpcion4, labelPregunta, getApplicationContext());
        partidaModoClasico.seleccionarPregunta();
        idPregunta = partidaModoClasico.ciclo();

    }

    /**
     * Este metodo permite jugar una partida resolviendo un cuestionario
     *
     * @author Fernando
     */
    private void jugarCuestionarios() {
        //nos aseguramos de que no hayan errores al obtener los datos
        iniciarPartida();
        if (idPartida == -1 || idUsuario == -1) {
            //en caso de no poder crear la partida avisamos al usuario y hacemos vibrar el telefono
            CrearToast.toastLargo("No se ha podido inicar la partida", getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(), 100);
            return;
        }
        partida = new Partida(idPartida, tipoPartida.toString(), idUsuario);
        partidaCuestionario = new PartidaCuestionario(partida, btnOpcion1, btnOpcion2, btnOpcion3, btnOpcion4, labelPregunta, cuestionario, getApplicationContext());
        partidaCuestionario.seleccionarPregunta();
        idPregunta = partidaCuestionario.ciclo();
    }

    /**
     * Este metodo permite responder una pregunta
     *
     * @param view es el boton que se pulsa
     */

    public void responder(View view) {

        //en funcion del tipo de partida llamamos al metodo responder de la partida correspondiente
        switch (tipoPartida) {
            case MODO_LIBRE:
                responderModoLibre(view);
                break;
            case MODO_SIN_FALLOS:
                responderModoSinFallos(view);
                break;
            case MODO_CLASICO:
                responderModoClasico(view);
                break;
            case CUESTIONARIOS:
                responderCuestionario(view);
                break;
        }
    }

    /**
     * Este metodo permite responder una pregunta en una partida de modo clasico
     *
     * @param view es el boton que se pulsa
     * @author Fernando
     */
    private void responderModoClasico(View view) {
        //nos aseguramos de que la pregunta no esta respondida ya
        if (bandera) {
            //obtenemos el boton
            Button button = (Button) view;
            boolean acertada = partidaModoClasico.responder(button);
            //se marca que la pregunta ya ha sido respondida
            bandera = false;
            //se actualizan los contadores de respuestas correctas e incorrectas
            labelRespuestasCorrectas.setText("Respuestas correctas: " + partidaModoClasico.getContadorPreguntasCorrectas());
            labelRespuestasIncorrectas.setText("Respuestas incorrectas: " + partidaModoClasico.getContadorRespuestasIncorrectas());
            ConsultasPartida.insertarPregunta(partida.getId(), idPregunta, acertada);
        } else {
            //indicamos al usuario que esa pregunta ya esta respondida
            CrearToast.toastLargo("Ya has respondido a la pregunta", getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(), 100);
        }
    }

    /**
     * Este metodo permite responder una pregunta en una partida de modo sin fallos
     *
     * @param view es el boton que se pulsa
     * @author Fernando
     */
    private void responderModoSinFallos(View view) {
        //nos aseguramos de que la pregunta no esta respondida ya
        if (bandera) {
            //obtenemos el boton
            Button button = (Button) view;
            boolean acertada = partidaModoSinFallos.responder(button);
            //se marca que la pregunta ya ha sido respondida
            bandera = false;
            //se actualizan los contadores de respuestas correctas e incorrectas
            labelRespuestasCorrectas.setText("Respuestas correctas: " + partidaModoSinFallos.getContadorPreguntasCorrectas());
            labelRespuestasIncorrectas.setText("Respuestas incorrectas: " + partidaModoSinFallos.getContadorRespuestasIncorrectas());
            ConsultasPartida.insertarPregunta(partida.getId(), idPregunta, acertada);
        } else {
            //indicamos al usuario que esa pregunta ya esta respondida
            CrearToast.toastCorto("Ya has respondido a la pregunta", getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(), 100);
        }
    }

    /**
     * Este metodo permite responder una pregunta cuando se esta jugando una partida en modo libre
     *
     * @param view es el boton que se pulsa
     */
    private void responderModoLibre(View view) {
        //solo se permite pulsar un boton, hasta que se coloque otra pregunta
        if (bandera) {
            //obtenemos el boton
            Button button = (Button) view;
            boolean acertada = partidaModoLibre.responder(button);
            //se marca que la pregunta ya ha sido respondida
            bandera = false;
            //se actualizan los contadores de respuestas correctas e incorrectas
            labelRespuestasCorrectas.setText("Respuestas correctas: " + partidaModoLibre.getContadorPreguntasCorrectas());
            labelRespuestasIncorrectas.setText("Respuestas incorrectas: " + partidaModoLibre.getContadorRespuestasIncorrectas());
            ConsultasPartida.insertarPregunta(partida.getId(), idPregunta, acertada);
        } else {
            //indicamos al usuario que esa pregunta ya esta respondida
            CrearToast.toastCorto("Ya has respondido a la pregunta", getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(), 100);
        }
    }

    private void responderCuestionario(View view) {
        //nos aseguramos de que la pregunta no ha sido respondida ya
        if (bandera) {
            //obtenemos el boton
            Button button = (Button) view;
            boolean acertada = partidaCuestionario.responder(button);
            //se marca que la pregunta ya ha sido respondida
            bandera = false;
            //se actualizan los contadores de respuestas correctas e incorrectas
            labelRespuestasCorrectas.setText("Respuestas correctas: " + partidaCuestionario.getContadorPreguntasCorrectas());
            labelRespuestasIncorrectas.setText("Respuestas incorrectas: " + partidaCuestionario.getContadorRespuestasIncorrectas());
            ConsultasPartida.insertarPregunta(partida.getId(), idPregunta, acertada);
        } else {
            //indicamos al usuario que esa pregunta ya esta respondida
            CrearToast.toastCorto("Ya has respondido a la pregunta", getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(), 100);
        }
    }

    public void siguiente(View view) {
        //nos aseguramos de que la pregunta no ha sido ya respondida
        if (bandera) {
            CrearToast.toastCorto("Debe responder a la pregunta antes de pasar a la siguiente", getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(), 100);
            return;
        }
        //en funcion del tipo de partida se llama al metodo ciclo de la partida correspondiente
        switch (tipoPartida) {
            case MODO_LIBRE:
                if (!partidaModoLibre.fin()) {
                    bandera = true;
                    idPregunta = partidaModoLibre.ciclo();
                } else {
                    lanzarVentanaResultado();
                }
                break;
            case MODO_SIN_FALLOS:
                if (!partidaModoSinFallos.fin()) {
                    bandera = true;
                    idPregunta = partidaModoSinFallos.ciclo();
                } else {
                    lanzarVentanaResultado();
                }
                break;
            case MODO_CLASICO:
                if (partidaModoClasico.fin()) {
                    bandera = true;
                    idPregunta = partidaModoClasico.ciclo();
                } else {
                    lanzarVentanaResultado();
                }
                break;
            case CUESTIONARIOS:
                if (partidaCuestionario.fin()) {
                    bandera = true;
                    idPregunta = partidaCuestionario.ciclo();
                } else {
                    lanzarVentanaResultado();
                }
                break;
        }
    }

    /**
     * Este metodo permite lanzar la ventana de resultado
     *
     * @author Fernando
     */
    private void lanzarVentanaResultado() {
        colocarPuntuacion();
        Intent intent = new Intent(this, PantallaResultado.class);
        //mandamos el id de la partida hacia la pantalla donde se muestra el resultado
        intent.putExtra("id_partida",idPartida);
        startActivity(intent);
    }

    /**
     * Este metodo permite colocar la puntuacion de la partida una vez terminada
     * @author Fernando
     */
    private void colocarPuntuacion() {
        int puntuacion = 0;
        //obtenemos la puntuacion en funcion de la partida que se ha jugado
        switch (tipoPartida) {
            case MODO_LIBRE:
                puntuacion = partidaModoLibre.getContadorPreguntasCorrectas();
                break;
            case MODO_SIN_FALLOS:
                puntuacion = partidaModoSinFallos.getContadorPreguntasCorrectas();
                break;
            case MODO_CLASICO:
                puntuacion = partidaModoClasico.getContadorPreguntasCorrectas();
                break;
            case CUESTIONARIOS:
                puntuacion = partidaCuestionario.getContadorPreguntasCorrectas();
                break;
        }
        ;
        //realizamos una consulta de modificacion para colocar la puntuacion
        ConsultasPartida.establecerPuntuacion(partida.getId(), puntuacion);
    }

    /**
     * Este metodo permite finalizar una partida en caso de que el modo
     * de juego sea modo libre, cuenta con doble verificacion, para terminar
     * una partida es necesario pulsar el boton de aceptar del dialogo que
     * se nos mostrara una vez pulsado el boton de finalizar
     * @param view es el boton que se pulsa
     * @author Fernando
     */
    public void finalizar(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Está seguro de que desea finalizar?");
        builder.setPositiveButton("Aceptar", (dialog, which) -> lanzarVentanaResultado());
        builder.setNegativeButton("Cancelar", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }


}


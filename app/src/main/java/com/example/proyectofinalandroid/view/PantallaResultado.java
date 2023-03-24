package com.example.proyectofinalandroid.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.provider.Telephony;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinalandroid.MainActivity;
import com.example.proyectofinalandroid.R;
import com.example.proyectofinalandroid.controller.acceso.Codigos;
import com.example.proyectofinalandroid.controller.acceso.Login;
import com.example.proyectofinalandroid.controller.baseDeDatos.Constantes;
import com.example.proyectofinalandroid.controller.baseDeDatos.GestionCategorias;
import com.example.proyectofinalandroid.controller.baseDeDatos.GestionPreguntas;
import com.example.proyectofinalandroid.controller.baseDeDatos.GestionUsuarios;
import com.example.proyectofinalandroid.controller.controlPartida.ConsultasPartida;
import com.example.proyectofinalandroid.controller.tools.CrearToast;
import com.example.proyectofinalandroid.controller.tools.Vibracion;
import com.example.proyectofinalandroid.controller.usuario.ConfiguracionUsuario;
import com.example.proyectofinalandroid.model.Partida;
import com.example.proyectofinalandroid.model.Pregunta;
import com.example.proyectofinalandroid.model.Usuario;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Esta clase permite gestionar la pantalla que permite al usuario jugar una partida
 * @author Fernando
 */
public class PantallaResultado extends AppCompatActivity {
    /**
     * Contador para el boton de atras, su funcion es que el usuario tenga que dar
     * dos veces al boton para retroceder y evitar pulsaciones accidentales
     */
    private int backPressCount = 0;
    /**
     * Contador para el boton de salir, su funcion es que el usuario tenga que dar
     * dos veces al boton para salir y evitar pulsaciones accidentales
     */
    private int exitCount = 0;
    /**
     * Timer para los botones de salir y retroceder
     */
    private Timer backPressTimer;
    /**
     * Id de la partida que se acaba de jugar
     */
    int idPartida;
    /**
     * Lista donde se colocan las preguntas
     */
    ListView lista;
    /**
     * TextView para colocar la puntuacion de la la partida
     */
    TextView txtPuntuacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_resultado);
        //obtenemos el id de la partida
        idPartida = getIntent().getIntExtra("id_partida", -1);
        //obtenemos la lista donde se colocan la preguntas
        lista = findViewById(R.id.listPreguntas);
        //obtenemos el TextView para colocar la puntuacion
        txtPuntuacion = findViewById(R.id.txtPuntuacion);
        //cargamos las preguntas en la lista
        cargarLista();
        //colocamos la puntuacion
        txtPuntuacion.setText("Puntuacion: " + ConsultasPartida.obtenerPuntuacion(idPartida));
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
        }
        return super.onOptionsItemSelected(item);
    }



    /**
     * Metodo onBackPressed sobreescrito para poder personalizar la accion
     * al momento de pulsar el boton de retroceso, para salir sera necesario
     * que el usuario pulse dos veces el boton en un intervalo menor a 2 segundos,
     * de esta forma se evitan pulsaciones accidentales, tambien en vez de retroceder
     * lo cual nos devolveria a la pantalla jugar con la ultima pregunta respondida simplemente
     * volvemos a la pantalla que permite seleccionar un modo de juego
     * @author Fernando
     */
    @Override
    public void onBackPressed() {
        if (backPressCount == 0) {
            backPressCount++;
            CrearToast.toastCorto("Pulse de nuevo para salir", getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(), 100);

            backPressTimer = new Timer();
            backPressTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    backPressCount = 0;
                }
            }, 2000);
        } else {
            backPressTimer.cancel();
            //Lanzamos la actividad de la pantalla que permite seleccionar el modo de juego
            Intent intent = new Intent(this, PantallaSeleccionarModoJuego.class);
            startActivity(intent);
            backPressCount = 0;
        }
    }

    /**
     * Este metodo permite cargar la lista con las preguntas respondidas durante
     * la partida
     * @author Fernando
     */
    private void cargarLista() {
        if (idPartida == -1) {
            CrearToast.toastCorto("No se ha podido obtener las preguntas de la partida", getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(),100);
        }
        //obtenemos los colores que le vamos a poner a la lista
        int colorPreguntasAcertadas = ContextCompat.getColor(getApplicationContext(), R.color.color_preguntas_acertadas);
        int colorPreguntasFalladas = ContextCompat.getColor(getApplicationContext(), R.color.color_preguntas_falladas);
        //obtenemos las preguntas de la partida
        ArrayList<String[]> preguntas = ConsultasPartida.obtenerPreguntasPartida(idPartida);
        // Creamos un ArrayList de Strings con el contenido de las preguntas y su estado (acertada o no)
        ArrayList<Pair<String, Boolean>> contenidoPreguntas = new ArrayList<>();
        for (String[] pregunta : preguntas) {
            int idPregunta = GestionPreguntas.obtenerIdPregunta(pregunta[0]);
            boolean preguntaAcertada = GestionPreguntas.preguntaAcertada(idPartida, idPregunta); // aquí debes llamar al método que verifica si la pregunta fue acertada
            contenidoPreguntas.add(new Pair<>(pregunta[0], preguntaAcertada)); // aquí debes sustituir 0 por el índice que corresponda a la pregunta
        }

        // Crear un ArrayAdapter personalizado y asignarlo al ListView
        ArrayAdapter<Pair<String, Boolean>> adapter = new ArrayAdapter<Pair<String, Boolean>>(this, android.R.layout.simple_list_item_1, contenidoPreguntas) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                String pregunta = contenidoPreguntas.get(position).first;
                //le damos los estilos necesarios a los TextView
                ((TextView) view.findViewById(android.R.id.text1)).setTypeface(null, Typeface.BOLD);
                ((TextView) view.findViewById(android.R.id.text1)).setText(pregunta);
                ((TextView) view.findViewById(android.R.id.text1)).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                boolean preguntaAcertada = contenidoPreguntas.get(position).second;
                //le damos un color de fondo en funcion de si se acerto la pregunta o no
                if (preguntaAcertada) {
                    view.setBackgroundColor(colorPreguntasAcertadas);
                } else {
                    view.setBackgroundColor(colorPreguntasFalladas);
                }

                return view;
            }

        };
        //le anadimos a la lista el listener para cuando se pulse en un elemento
        lista.setOnItemClickListener((parent, view, position, id) -> {
            String enunciado = contenidoPreguntas.get(position).first;
            // Llamar al método que deseas ejecutar pasándole el enunciado de la pregunta
            mostrarInformacionPregunta(enunciado);
        });
        //le añadimos el adapter a la lista
        lista.setAdapter(adapter);
    }

    /**
     * Este metodo permite mostrar la informacion de una pregunta cuando se pulsa sobre ella
     * en la lista de preguntas
     * @param enunciado es el enunciado de la lista en la que se ha pulsado
     * @author Fernando
     */
    private void mostrarInformacionPregunta(String enunciado) {
        int id = GestionPreguntas.obtenerIdPregunta(enunciado);
        Pregunta pregunta = GestionPreguntas.obtenerDatos(id);
        if (pregunta == null) {
            CrearToast.toastCorto("Error al obtener los datos de la pregunta", getApplicationContext());
            Vibracion.vibrar(getApplicationContext(), 100);
            return;
        }
        construirDialogo(pregunta).show();
    }

    /**
     * Este metodo permite mostrar el AlertDialog con la informacion de una pregunta
     * @param pregunta obtejo pregunta con los datos que vamos a mostrar
     * @return el AlertDialog creado
     * @author Fernando
     */
    private AlertDialog construirDialogo(Pregunta pregunta) {
        //obtenemos los datos
        String enunciado = pregunta.getEnunciado();
        String respuestaCorrecta = pregunta.getRespuestaCorrecta();
        String respuestaIncorrecta1 = pregunta.getRespuestaIncorrecta1();
        String respuestaIncorrecta2 = pregunta.getRespuestaIncorrecta2();
        String respuestaIncorrecta3 = pregunta.getRespuestaIncorrecta3();
        String categoria = GestionCategorias.obtenerCategoriaPregunta(enunciado);
        //creamos un stringBuilder con las respuestas incorrectas para darle mejor formato
        StringBuilder respuestasIncorrectas = new StringBuilder();
        respuestasIncorrectas.append("Respuestas incorrectas: \n");
        respuestasIncorrectas.append("- ").append(respuestaIncorrecta1).append("\n");
        respuestasIncorrectas.append("- ").append(respuestaIncorrecta2).append("\n");
        respuestasIncorrectas.append("- ").append(respuestaIncorrecta3).append("\n");
        //creamos el alertDialog con los datos de la pregunta
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Información de la pregunta");
        builder.setMessage("Enunciado: " + enunciado + "\n" +
                "Respuesta correcta: " + respuestaCorrecta + "\n" +
                respuestasIncorrectas +
                "Categoría: " + categoria);
        builder.setPositiveButton("Cerrar", null);
        //lo retornamos
        return builder.create();
    }

    /**
     * Este metodo permite volver al menu principal, es decir a la pantalla que nos permite seleccionar un modo
     * de juego, su comportamiento es el mismo que el boton de retroceso del movil, por lo que simplemente llama
     * al mismo metodo
     * @param view es el boton que se pulsa
     * @author Fernando
     */
    public void volverMenuPrincipal(View view) {
        /*
        Se llama al metodo onBackPressed, por lo que internamente se esta pulsando el
        boton de retroceso
         */
       onBackPressed();
    }
    /**
     * Este metodo permite salir de la aplicacion
     * @param view es el boton que se pulsa
     * @author Fernando
     */
    public void salir(View view){
        if (exitCount == 0) {
            exitCount++;
            CrearToast.toastCorto("Pulse de nuevo para salir", getApplicationContext()).show();
            Vibracion.vibrar(getApplicationContext(), 100);

            backPressTimer = new Timer();
            backPressTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    exitCount = 0;
                }
            }, 2000);
        } else {
            backPressTimer.cancel();
            // cerramos la aplicacion
            finishAffinity();
            System.exit(0);
            exitCount = 0;
        }
    }
    public void btnSendEmailOnClick(View view){
        Partida partida = ConsultasPartida.obtenerPartida(idPartida);
        sendEmail(partida);


    }
    /**
     * Este metodo crea un resumen de una partida en formato String
     * @param partida es el objeto partida que contiene los datos de la partida de la cual
     *                queremos obtener el resumen
     * @return Un StringBuilder con los datos de la partida
     * @author Fernando
     */
    public StringBuilder crearResumen(Partida partida){
        int idPregunta;
        boolean acertada;
        String texto;
        StringBuilder cadena = new StringBuilder();
        cadena.append("-----RESUMEN PARTIDA-----\n");
        cadena.append("USUARIO ===> " ).append(ConfiguracionUsuario.getNombreUsuario()).append("\n");
        cadena.append("FECHA ===> ").append(partida.getFecha()).append("\n");
        cadena.append("TIPO ===> ").append(partida.getTipo()).append("\n");
        cadena.append("PUNTUACION ===> ").append(partida.getPuntuacion()).append("\n");
        cadena.append("PREGUNTAS RESPONDIDAS: ").append("\n");
        for(Pregunta p: partida.getPreguntas()){
            idPregunta = GestionPreguntas.obtenerIdPregunta(p.getEnunciado());
            acertada = GestionPreguntas.preguntaAcertada(idPartida,idPregunta);
            if(acertada){
                texto = "SI";
            }else{
                texto = "NO";
            }
            cadena.append("\t").append("-").append(p.getEnunciado()).append(" acertada ==> ").append(texto).append("\n");
        }
        return cadena;
    }
    private void sendEmail(Partida partida) {
        String emailDestino = Login.obtenerDatos(ConfiguracionUsuario.getNombreUsuario(),Codigos.OBTENER_EMAIL);
        String[] TO = {emailDestino};
        String[] CC = {Constantes.EMAIL};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "RESUMEN PARTIDA");
        emailIntent.putExtra(Intent.EXTRA_TEXT, crearResumen(partida).toString());

        try {
            startActivity(Intent.createChooser(emailIntent, "Enviar correo electrónico..."));
        } catch (android.content.ActivityNotFoundException ex) {
            CrearToast.toastLargo("No hay clientes de correo electronico instalados",getApplicationContext()).show();
        }
    }
}



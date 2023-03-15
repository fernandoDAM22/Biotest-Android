package com.example.proyectofinalandroid.controller.controlPartida;
import com.example.proyectofinalandroid.controller.baseDeDatos.Constantes;
import com.example.proyectofinalandroid.controller.baseDeDatos.HttpRequest;
import com.example.proyectofinalandroid.controller.tools.CrearToast;
import com.example.proyectofinalandroid.model.Partida;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;


/**
 * Esta clase contiene los metodos que implican consultas a la
 * base de datos relacionadas con las partidas
 *
 * @author Fernando
 */
public class ConsultasPartida {
    /**
     * Este metodo permite obtener todos los id de las partidas
     *
     * @return un ArrayList con los ids de las partidas, null si ocurre algun error
     * @author Fernando
     */
    public static ArrayList<Integer> obtenerId() {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<ArrayList<Integer>> future = executor.submit(() -> {
            //obtenemos la respuesta
            String respuesta = HttpRequest.GET_REQUEST(Constantes.URL_OBTENER_IDS_PARTIDAS,new HashMap<>());
            //la parseamos
            JsonElement element = JsonParser.parseString(respuesta);
            if (element.isJsonArray()) {
                ArrayList<Integer> list = new ArrayList<>();
                JsonArray jsonArray = element.getAsJsonArray();
                for (JsonElement jsonElement : jsonArray) {
                    list.add(jsonElement.getAsInt());
                }
                //retornamos la lista con los ids
                return list;
            } else {
                return null;
            }
        });
        try {
            //terminamos el executor
            executor.shutdown();
            //retornamos el valor devuelto por el future, es decir la lista de ids
            return future.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Este metodo permite insertar una partida en la base de datos
     *
     * @param partida es el objeto de la clase partida que contiene los datos
     * @author Fernando
     */
    public static boolean insertarPartida(Partida partida) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Boolean> future = executor.submit(() -> {
            //creamos el mapa con los datos que tenemos que pasar el la peticion
            HashMap<String,String> data = new HashMap<>();
            data.put("id", String.valueOf(partida.getId()));
            data.put("puntuacion", String.valueOf(partida.getPuntuacion()));
            data.put("id_usuario", String.valueOf(partida.getIdUsuario()));
            data.put("tipo_partida",partida.getTipo());
            //obtenemos la respuesta
            String respuesta = HttpRequest.POST_REQUEST(Constantes.URL_INSERTAR_PARTIDA,data);
            //la parseamos
            JsonElement element = JsonParser.parseString(respuesta);
            //retornamos la respuesta en formato booleano
            return element.getAsBoolean();
        });

        try {
            //terminamos el executor
            executor.shutdown();
            //devolvemos la respuesta del future, es decir un booleano
            return future.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }

    }
    /**
     * Este metodo permite insertar una pregunta en la tabla preguntas_partida
     *
     * @param idPartida  es el id de la partida en la que se ha respondido esa pregunta
     * @param idPregunta es el id de la pregunta que se ha respondido
     * @param acertada   indica si la pregunta a sido acertada o no
     * @author Fernando
     */
    public static boolean insertarPregunta(int idPartida, int idPregunta, boolean acertada) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Boolean> future = executor.submit(() -> {
            //creamos el mapa con los datos que tenemos que enviar en la peticion
            HashMap<String,String> data = new HashMap<>();
            data.put("id_partida", String.valueOf(idPartida));
            data.put("id_pregunta",String.valueOf(idPregunta));
            data.put("acertada",String.valueOf(acertada));
            //obtenemos la respuesta
            String respuesta = HttpRequest.POST_REQUEST(Constantes.URL_INSERTAR_PREGUNTA_PARTIDA,data);
            //la parseamos
            JsonElement element = JsonParser.parseString(respuesta);
            //devolvemos la respuesta en formato booleano
            return element.getAsBoolean();
        });
        try {
            //terminamos el executor
            executor.shutdown();
            //retornamos el resultado devuelto por el future, es decir un booleano
            return future.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Este metodo permite establecer una puntuacion a una partida
     *
     * @param idPartida  es el id de la partida a la que le vamos a asignar la puntuacion
     * @param puntuacion es la puntuacion que le vamos a asignar a la partida
     * @author Fernando
     */
    public static boolean establecerPuntuacion(int idPartida, int puntuacion) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Boolean> future = executor.submit(() -> {
            //creamos el mapa con los parametros necesarios
            HashMap<String,String> data = new HashMap<>();
            data.put("id_partida", String.valueOf(idPartida));
            data.put("puntuacion",String.valueOf(puntuacion));
            //obtenemos la respuesta
            String respuesta = HttpRequest.POST_REQUEST(Constantes.URL_ESTABLECER_PUNTUACION,data);
            //la parseamos
            JsonElement element = JsonParser.parseString(respuesta);
            //la devolvemos en formato boolean
            return element.getAsBoolean();
        });
        try {
            //terminamos el executor
            executor.shutdown();
            //retornamos el resultado devuelto por el future, es decir, un booleano
            return future.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Este metodo nos permite obtener las preguntas que se han
     * respondido en una partida a traves del id de la partida
     *
     * @param idPartida es el id de la partida de la cual queremos obtener sus preguntas
     * @return un ArrayList de String con los datos de las preguntas
     * @author Fernando
     */
    public static ArrayList<String[]> obtenerPreguntasPartida(int idPartida) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<ArrayList<String[]>> future = executor.submit(() -> {
            //creamos un mapa con los valores necesarios
            HashMap<String,String> data = new HashMap<>();
            data.put("partida",String.valueOf(idPartida));
            //obtenemos la respuessta
            String respuesta = HttpRequest.GET_REQUEST(Constantes.URL_OBTENER_PREGUNTAS_PARTIDA, data);
            System.err.println(respuesta);
            //la parseamos
            Gson gson = new Gson();
            List<String[]> listaPreguntas = gson.fromJson(respuesta, new TypeToken<List<String[]>>(){}.getType());
            //añadimos los datos de las preguntas a un ArrayList
            ArrayList<String[]> preguntas = new ArrayList<>();
            for (String[] pregunta : listaPreguntas) {
                preguntas.add(new String[]{pregunta[0],pregunta[1],pregunta[2],pregunta[3],pregunta[4]});
            }
            //retornamos el ArrayList con los datos de las preguntas
            return preguntas;
        });
        try {
            //terminamos el executor
            executor.shutdown();
            //devolvemos el resultado devuelto por el executor
            return future.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Este metodo nos permite obtener la puntuacion de una partida
     * @param idPartida es el id de la partida de la cual queremos obtener su puntuacion
     * @return la puntuacion de la partida, -1 si ocurre algun error
     * @author Fernando
     */
    public static int obtenerPuntuacion(int idPartida) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Integer> future = executor.submit(() -> {
            //creamos el mapa con los parametros necesarios
            HashMap<String,String> data = new HashMap<>();
            data.put("partida",String.valueOf(idPartida));
            //obtenemos la respuesta
            String respuesta = HttpRequest.GET_REQUEST(Constantes.URL_OBTENER_PUNTUACION,data);
            //la parseamos
            JsonElement element = JsonParser.parseString(respuesta);
            String result = element.toString().replaceAll("\"","");
            //la devolvemos como entero
            return Integer.parseInt(result);
        });
        try {
            //terminamos el executor
            executor.shutdown();
            //retornamos la respuesta devuelta por el future, es decir un numero entero
            return future.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
    }



}

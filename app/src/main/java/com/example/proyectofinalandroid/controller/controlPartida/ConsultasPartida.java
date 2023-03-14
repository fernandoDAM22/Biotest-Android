package com.example.proyectofinalandroid.controller.controlPartida;
import com.example.proyectofinalandroid.controller.baseDeDatos.Constantes;
import com.example.proyectofinalandroid.controller.baseDeDatos.HttpRequest;
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
        Executor executor = Executors.newSingleThreadExecutor();
        Future<ArrayList<Integer>> future = (Future<ArrayList<Integer>>) ((ExecutorService) executor).submit(() -> {
            String respuesta = HttpRequest.GET_REQUEST(Constantes.URL_OBTENER_IDS_PARTIDAS,new HashMap<>());
            JsonElement element = JsonParser.parseString(respuesta);
            if (element.isJsonArray()) {
                ArrayList<Integer> list = new ArrayList<>();
                JsonArray jsonArray = element.getAsJsonArray();
                for (JsonElement jsonElement : jsonArray) {
                    list.add(jsonElement.getAsInt());
                }
                return list;
            } else {
                return null;
            }
        });
        try {
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
        Executor executor = Executors.newSingleThreadExecutor();
        Future<Boolean> future = (Future<Boolean>) ((ExecutorService) executor).submit(() -> {
            HashMap<String,String> data = new HashMap<>();
            data.put("id", String.valueOf(partida.getId()));
            data.put("fecha",partida.getFecha());
            data.put("puntuacion", String.valueOf(partida.getPuntuacion()));
            data.put("id_usuario", String.valueOf(partida.getIdUsuario()));
            data.put("tipo_partida",partida.getTipo());
            String respuesta = HttpRequest.POST_REQUEST(Constantes.URL_INSERTAR_PARTIDA,data);
            JsonElement element = JsonParser.parseString(respuesta);
            return element.getAsBoolean();
        });
        try {
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
        Executor executor = Executors.newSingleThreadExecutor();
        Future<Boolean> future = (Future<Boolean>) ((ExecutorService) executor).submit(() -> {
            HashMap<String,String> data = new HashMap<>();
            data.put("id_partida", String.valueOf(idPartida));
            data.put("id_pregunta",String.valueOf(idPregunta));
            data.put("acertada",String.valueOf(acertada));
            String respuesta = HttpRequest.POST_REQUEST(Constantes.URL_INSERTAR_PREGUNTA_PARTIDA,data);
            JsonElement element = JsonParser.parseString(respuesta);
            return element.getAsBoolean();
        });
        try {
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
        Executor executor = Executors.newSingleThreadExecutor();
        Future<Boolean> future = (Future<Boolean>) ((ExecutorService) executor).submit(() -> {
            HashMap<String,String> data = new HashMap<>();
            data.put("id_partida", String.valueOf(idPartida));
            data.put("puntuacion",String.valueOf(puntuacion));
            String respuesta = HttpRequest.POST_REQUEST(Constantes.URL_ESTABLECER_PUNTUACION,data);
            JsonElement element = JsonParser.parseString(respuesta);
            return element.getAsBoolean();
        });
        try {
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
        Executor executor = Executors.newSingleThreadExecutor();
        Future<ArrayList<String[]>> future = (Future<ArrayList<String[]>>) ((ExecutorService) executor).submit(() -> {
            HashMap<String,String> data = new HashMap<>();
            data.put("partida",String.valueOf(idPartida));
            String respuesta = HttpRequest.GET_REQUEST(Constantes.URL_OBTENER_PREGUNTAS_PARTIDA, data);

            Gson gson = new Gson();
            List<String[]> listaPreguntas = gson.fromJson(respuesta, new TypeToken<List<String[]>>(){}.getType());

            ArrayList<String[]> preguntas = new ArrayList<>();
            for (String[] pregunta : listaPreguntas) {
                preguntas.add(new String[]{pregunta[0],pregunta[1],pregunta[2],pregunta[3],pregunta[4]});
            }
            return preguntas;
        });
        try {
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
        Executor executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = (Future<Integer>) ((ExecutorService) executor).submit(() -> {
            HashMap<String,String> data = new HashMap<>();
            data.put("partida",String.valueOf(idPartida));
            String respuesta = HttpRequest.GET_REQUEST(Constantes.URL_OBTENER_PUNTUACION,data);
            JsonElement element = JsonParser.parseString(respuesta);
            String result = element.toString().replaceAll("\"","");
            return Integer.parseInt(result);
        });
        try {
            return future.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
    }



}

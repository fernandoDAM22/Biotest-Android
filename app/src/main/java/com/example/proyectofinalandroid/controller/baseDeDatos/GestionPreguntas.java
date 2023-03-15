package com.example.proyectofinalandroid.controller.baseDeDatos;

import com.example.proyectofinalandroid.model.Pregunta;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Esta clase permite gestionar las preguntas en la base de datos
 *
 * @author Fernando
 */
public class GestionPreguntas {
    /**
     * Este metodo permite obtener todos los ids de las preguntas de la base de datos
     *
     * @return un ArrayList con los ids de todas las preguntas de la base de datos
     * @author Fernando
     */
    public static ArrayList<Integer> obtenerIds() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<ArrayList<Integer>> future = executor.submit(() -> {
            //obtenemos la respuesta
            String respuesta = HttpRequest.GET_REQUEST(Constantes.URL_OBTENER_IDS, new HashMap<>());
            //la parseamos
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
            //terminamos el executor y devolvemos el resultado del future
            executor.shutdown();
            return future.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Este metodo permite obtener los datos de una pregunta a partir de su id
     *
     * @param id es el id de la pregunta
     * @return un objeto pregunta
     * @author Fernando
     */
    public static Pregunta obtenerDatos(int id) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Pregunta> future = executor.submit(() -> {
            String url = Constantes.URL_OBTENER_DATOS;
            //creamos el mapa con los parametros necesarios
            HashMap<String, String> data = new HashMap<>();
            data.put("id", String.valueOf(id));
            //obtenemos la respuesta
            String respuesta = HttpRequest.GET_REQUEST(url, data);
            //la parseamos
            Gson gson = new Gson();
            String[] pregunta = gson.fromJson(respuesta, String[].class);
            if (pregunta != null) {
                return new Pregunta(pregunta[0], pregunta[1], pregunta[2], pregunta[3], pregunta[4]);
            }
            return null;
        });
        try {
            //terminamos el executor y retornamos el resultado del future
            executor.shutdown();
            return future.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean preguntaAcertada(int idPartida, int idPregunta) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Boolean> future = executor.submit(() -> {
            HashMap<String, String> params = new HashMap<>();
            params.put("idPartida", String.valueOf(idPartida));
            params.put("idPregunta", String.valueOf(idPregunta));
            System.out.println("idPartida --------------> " + idPartida);
            System.out.println("idPregunta --------------> " + idPregunta);

            String respuesta = HttpRequest.GET_REQUEST(Constantes.URL_PREGUNTA_ACERTADA, params);
            System.out.println("Respuesta --------------> " + respuesta);
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(respuesta);
            String result = element.toString().replaceAll("\"", "").replace("[","").replace("]","");
            return Integer.parseInt(result) != 0;
        });
        try {
            //terminamos el executor y retornamos el resultado del future
            executor.shutdown();
            return future.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int obtenerIdPregunta(String enunciado) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(() -> {
            try {
                HashMap<String, String> data = new HashMap<>();
                data.put("enunciado", enunciado);
                String response = HttpRequest.GET_REQUEST(Constantes.URL_OBTENER_ID, data);
                JsonParser parser = new JsonParser();
                JsonElement element = parser.parse(response);
                String result = element.toString().replaceAll("\"", "");
                return Integer.parseInt(result);
            } catch (Exception e) {
                return -1;
            }
        });
        try {
            //terminamos el executor y retornamos el resultado del future
            executor.shutdown();
            return future.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
    }

}

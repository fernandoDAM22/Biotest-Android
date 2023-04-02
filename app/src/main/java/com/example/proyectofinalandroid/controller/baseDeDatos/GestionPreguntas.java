package com.example.proyectofinalandroid.controller.baseDeDatos;

import com.example.proyectofinalandroid.model.Pregunta;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
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
            String respuesta = HttpRequest.getRequest(Constantes.URL_OBTENER_IDS, new HashMap<>());
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
            String respuesta = HttpRequest.getRequest(url, data);
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

    /**
     * Este metodo permite saber si una pregunta fue acertada o no en una partida
     * @param idPartida es el id de la partida en la que se respondio la pregunta
     * @param enunciado es el enunciado de la pregunta
     * @return true si fue acertada, false si no
     * @author Fernando
     */
    public static boolean preguntaAcertada(int idPartida, String enunciado) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Boolean> future = executor.submit(() -> {
            //creamos el mapa con los parametros necesarios
            HashMap<String, String> params = new HashMap<>();
            params.put("idPartida", String.valueOf(idPartida));
            params.put("enunciado",enunciado);
            //obtenemos la respuesta
            String respuesta = HttpRequest.getRequest(Constantes.URL_PREGUNTA_ACERTADA, params);
            JsonElement element = JsonParser.parseString(respuesta);
            //la parseamos
            String result = element.toString().replaceAll("\"","").replace("[","").replace("]","");
            //retornamos true si fue acertada, false si no
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

    /**
     * Este metodo permite obtener el id de una pregunta a partir de su enunciado
     * @param enunciado es el enunciado de la pregunta de la cual queremos obtener su id
     * @return el id de la pregunta, -1 si ocurre algun error
     */
    public static int obtenerIdPregunta(String enunciado) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(() -> {
            try {
                //creamos el mapa con los parametros necesarios
                HashMap<String, String> data = new HashMap<>();
                data.put("enunciado", enunciado);
                //obtenemos la respuesta
                String respuesta = HttpRequest.getRequest(Constantes.URL_OBTENER_ID, data);
                //la parseamos
                JsonElement element = JsonParser.parseString(respuesta);
                String result = element.toString().replaceAll("\"","");
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

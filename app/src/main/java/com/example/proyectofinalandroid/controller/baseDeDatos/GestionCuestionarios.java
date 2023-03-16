package com.example.proyectofinalandroid.controller.baseDeDatos;

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
 * Esta clase contiene los metodos relacionados con los cuestionarios en la base de datos
 * @author Fernando
 */
public class GestionCuestionarios {
    /**
     * Este metodo nos permite obtener los cuestionarios que cuenten con 10 o
     * mas preguntas
     * @return un ArrayList con los nombres de los cuestionarios
     * @author Fernando
     */
    public static ArrayList<String> obtenerCuestionariosCompletos() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<ArrayList<String>> future = executor.submit(() -> {
            //obtenemos la respuesta
            String response = HttpRequest.getRequest(Constantes.URL_OBTENER_CUESTIONARIOS_COMPLETOS, new HashMap<>());
            //la parseamos
            JsonArray jsonArray = new Gson().fromJson(response, JsonArray.class);
            ArrayList<String> data = new ArrayList<>();
            for (JsonElement element : jsonArray) {
                data.add(element.getAsString());
            }
            //retormaos el resultado
            return data;
        });
        //terminamos el executor y delvolvemos el resultado del future
        ArrayList<String> result = future.get();
        executor.shutdown();
        return result;
    }

    /**
     * Este metodo permite obtener los ids  de las preguntas de un cuestionario
     * @param cuestionario es el nombre del cuestionario del que queremos obtener sus preguntas
     * @return un ArrayList con los ids de las preguntas
     * @author Fernando
     */
    public static ArrayList<Integer> obtenerIdPreguntas(String cuestionario) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<ArrayList<Integer>> future = executor.submit(() -> {
            //creamos el mapa con los datos necesarios
            HashMap<String,String> data = new HashMap<>();
            data.put("nombre",cuestionario);
            //obtenemos la respuesta
            String respuesta = HttpRequest.getRequest(Constantes.URL_OBTENER_IDS_PREGUNTAS_CUESTIONARIO,data);
            //parseamos la respuesta
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
            //terminanos el executor y devolvemos el resultado del future
            executor.shutdown();
            return future.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}

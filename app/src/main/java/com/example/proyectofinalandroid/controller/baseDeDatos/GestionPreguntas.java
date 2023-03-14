package com.example.proyectofinalandroid.controller.baseDeDatos;

import com.example.proyectofinalandroid.model.Pregunta;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GestionPreguntas {
    /**
     * Este metodo permite obtener todos los ids de las preguntas de la base de datos
     * @return un ArrayList con los ids de todas las preguntas de la base de datos
     * @author Fernando
     */
    public static ArrayList<Integer> obtenerIds(){
        Executor executor = Executors.newSingleThreadExecutor();
        Future<ArrayList<Integer>> future = (Future<ArrayList<Integer>>) ((ExecutorService) executor).submit(() -> {
            String respuesta = HttpRequest.GET_REQUEST(Constantes.URL_OBTENER_IDS,new HashMap<>());
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
     * Este metodo permite obtener los datos de una pregunta a partir de su id
     * @param id es el id de la pregunta
     * @return un objeto pregunta
     * @author Fernando
     */
    public static Pregunta obtenerDatos(int id){ Executor executor = Executors.newSingleThreadExecutor();
        Future<Pregunta> future = (Future<Pregunta>) ((ExecutorService) executor).submit(() -> {
            String url = Constantes.URL_OBTENER_DATOS;
            HashMap<String,String> data = new HashMap<>();
            data.put("id", String.valueOf(id));
            String respuesta = HttpRequest.GET_REQUEST(url, data);

            Gson gson = new Gson();
            String[] pregunta = gson.fromJson(respuesta, String[].class);
            if(pregunta != null){
                return new Pregunta(pregunta[0],pregunta[1],pregunta[2],pregunta[3],pregunta[4]);
            }
            return null;
        });
        try {
            return future.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }


    }
}

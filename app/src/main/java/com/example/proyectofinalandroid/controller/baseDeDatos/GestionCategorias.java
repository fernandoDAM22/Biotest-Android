package com.example.proyectofinalandroid.controller.baseDeDatos;

import com.google.gson.Gson;
import com.google.gson.JsonArray;


import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Esta clase permite gestionar las categorias de la base de datos
 *
 * @author Fernando
 */
public class GestionCategorias {
    /**
     * Este metodo permite obtener la categoria de una pregunta
     *
     * @param enunciado es el enunciado de la pregunta de la que queremos obtener su categoria
     * @return el nombre de la categoria si existe, null si no existe
     * @author Fernando
     */
    public static String obtenerCategoriaPregunta(String enunciado) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<String> future = executor.submit(() -> {
            HashMap<String,String> param = new HashMap<>();
            param.put("pregunta",enunciado);
            String respuesta = HttpRequest.getRequest(Constantes.URL_OBTENER_CATEGORIA_PREGUNTA, param);
            Gson gson = new Gson();
            JsonArray jsonArray = gson.fromJson(respuesta, JsonArray.class);
            return jsonArray.get(0).getAsString();
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
}

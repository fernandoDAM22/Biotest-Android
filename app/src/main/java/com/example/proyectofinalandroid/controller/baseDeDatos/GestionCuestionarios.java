package com.example.proyectofinalandroid.controller.baseDeDatos;

import com.example.proyectofinalandroid.controller.acceso.Registro;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
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
            String response = HttpRequest.GET_REQUEST(Constantes.URL_OBTENER_CUESTIONARIOS_COMPLETOS, new HashMap<>());
            Gson gson = new Gson();
            JsonArray jsonArray = new Gson().fromJson(response, JsonArray.class);
            ArrayList<String> data = new ArrayList<>();

            for (JsonElement element : jsonArray) {
                data.add(element.getAsString());
            }

            return data;
        });
        ArrayList<String> result = future.get();
        executor.shutdown();
        return result;
    }

}

package com.example.proyectofinalandroid.controller.baseDeDatos;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GestionUsuarios {
    public static int obtenerIdUsuario(String nombreUsuario) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(() -> {
            HashMap<String,String> data = new HashMap<>();
            data.put("nombre",nombreUsuario);
            String respuesta = HttpRequest.GET_REQUEST(Constantes.URL_OBTENER_ID_USUARIO,data);
            System.err.println(respuesta);
            JsonElement element = JsonParser.parseString(respuesta);
            String result = element.toString().replaceAll("\"","");
            return Integer.parseInt(result);
        });
        try {
            executor.shutdown();// Cierra el Executor
            return future.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
    }
}

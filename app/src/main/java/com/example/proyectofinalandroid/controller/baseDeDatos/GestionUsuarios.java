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

/**
 * Esta clase permite gestionar los usuarios de la base de datos
 * @author Fernando
 */
public class GestionUsuarios {
    /**
     * Este metodo permite obtener el id de un usuario a partir de su nombre
     * @param nombreUsuario es el nombre del usuario del que queremos obtener su id
     * @return el id del usuario, -1 si ocurre algun error
     */
    public static int obtenerIdUsuario(String nombreUsuario) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(() -> {
            //creamos el mapa con los valores necesarios
            HashMap<String,String> data = new HashMap<>();
            data.put("nombre",nombreUsuario);
            //obtenemos la respuesta
            String respuesta = HttpRequest.GET_REQUEST(Constantes.URL_OBTENER_ID_USUARIO,data);
            //la parseamos
            JsonElement element = JsonParser.parseString(respuesta);
            String result = element.toString().replaceAll("\"","");
            //retornamos la respuesta como entero
            return Integer.parseInt(result);
        });
        try {
            //terminamos el executor y delvolvemos el resultado del future, es decir un numero entero
            executor.shutdown();
            return future.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
    }
}

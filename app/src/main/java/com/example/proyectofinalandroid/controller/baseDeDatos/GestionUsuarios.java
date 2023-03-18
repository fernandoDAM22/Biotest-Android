package com.example.proyectofinalandroid.controller.baseDeDatos;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;
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
            String respuesta = HttpRequest.getRequest(Constantes.URL_OBTENER_ID_USUARIO,data);
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

    public static boolean cambiarPassword(String usuario, String password) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Boolean> future = executor.submit(() -> {
            //creamos el mapa con los valores necesarios
            HashMap<String,String> data = new HashMap<>();
            data.put("nombre",usuario);
            data.put("password",password);
            //obtenemos la respuesta
            String respuesta = HttpRequest.postRequest(Constantes.URL_CAMBIAR_PASSWORD,data);
            System.err.println("Respuesta ---------------> " + respuesta);
            //la parseamos
            JsonElement element = JsonParser.parseString(respuesta);
            //retornamos la respuesta como entero
            return element.getAsBoolean();

        });
        try {
            //terminamos el executor y delvolvemos el resultado del future, es decir un numero entero
            executor.shutdown();
            return future.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean modificarNombre(String nombreAntiguo, String nombreNuevo) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Boolean> future = executor.submit(() -> {
            //creamos el mapa con los valores necesarios
            HashMap<String,String> data = new HashMap<>();
            data.put("nombreAntiguo",nombreAntiguo);
            data.put("nombreNuevo",nombreNuevo);
            //obtenemos la respuesta
            String respuesta = HttpRequest.postRequest(Constantes.URL_CAMBIAR_USUARIO,data);
            System.err.println("Respuesta ---------------> " + respuesta);
            //la parseamos
            JsonElement element = JsonParser.parseString(respuesta);
            //retornamos la respuesta como entero
            return element.getAsBoolean();

        });
        try {
            //terminamos el executor y delvolvemos el resultado del future, es decir un numero entero
            executor.shutdown();
            return future.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean cambiarEmail(String nombre, String email) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Boolean> future = executor.submit(() -> {
            //creamos el mapa con los valores necesarios
            HashMap<String,String> data = new HashMap<>();
            data.put("nombre",nombre);
            data.put("email",email);
            //obtenemos la respuesta
            String respuesta = HttpRequest.postRequest(Constantes.URL_CAMBIAR_EMAIL,data);
            System.err.println("Respuesta ---------------> " + respuesta);
            //la parseamos
            JsonElement element = JsonParser.parseString(respuesta);
            //retornamos la respuesta como entero
            return element.getAsBoolean();

        });
        try {
            //terminamos el executor y delvolvemos el resultado del future, es decir un numero entero
            executor.shutdown();
            return future.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean cambiarTelefono(String nombre, String telefono) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Boolean> future = executor.submit(() -> {
            //creamos el mapa con los valores necesarios
            HashMap<String,String> data = new HashMap<>();
            data.put("nombre",nombre);
            data.put("telefono",telefono);
            //obtenemos la respuesta
            String respuesta = HttpRequest.postRequest(Constantes.URL_CAMBIAR_TELEFONO,data);
            System.err.println("Respuesta ---------------> " + respuesta);
            //la parseamos
            JsonElement element = JsonParser.parseString(respuesta);
            //retornamos la respuesta como entero
            return element.getAsBoolean();

        });
        try {
            //terminamos el executor y delvolvemos el resultado del future, es decir un numero entero
            executor.shutdown();
            return future.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
}

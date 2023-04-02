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
            return -1;
        }
    }

    /**
     * Este metodo permite cambiar la contrasena a un usuario
     * @param usuario es el nombre del usuario al que le vamos a cambiar la contrasena
     * @param password es la nueva contrasena que se le va a poner al usuario
     * @return true si se cambia con exito, false si ocurre algun error
     * @author Fernando
     */
    public static boolean cambiarPassword(String usuario, String password) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Boolean> future = executor.submit(() -> {
            //creamos el mapa con los valores necesarios
            HashMap<String,String> data = new HashMap<>();
            data.put("nombre",usuario);
            data.put("password",password);
            //obtenemos la respuesta
            String respuesta = HttpRequest.postRequest(Constantes.URL_CAMBIAR_PASSWORD,data);
            //la parseamos
            JsonElement element = JsonParser.parseString(respuesta);
            //retornamos la respuesta como entero
            return element.getAsBoolean();

        });
        try {
            //terminamos el executor y delvolvemos el resultado del future (true o false)
            executor.shutdown();
            return future.get();
        } catch (ExecutionException | InterruptedException e) {
            return false;
        }
    }

    /**
     * Este metodo permite cambiar el nombre de usuario a una persona
     * @param nombreAntiguo es el nombre antiguo del usuario
     * @param nombreNuevo es el nuevo nombre del usuario
     * @return true si se cambia con exito el nombre, false si no
     * @author Fernando
     */
    public static boolean modificarNombre(String nombreAntiguo, String nombreNuevo) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Boolean> future = executor.submit(() -> {
            //creamos el mapa con los valores necesarios
            HashMap<String,String> data = new HashMap<>();
            data.put("nombreAntiguo",nombreAntiguo);
            data.put("nombreNuevo",nombreNuevo);
            //obtenemos la respuesta
            String respuesta = HttpRequest.postRequest(Constantes.URL_CAMBIAR_USUARIO,data);
            //la parseamos
            JsonElement element = JsonParser.parseString(respuesta);
            //retornamos la respuesta como boolean
            return element.getAsBoolean();

        });
        try {
            //terminamos el executor y delvolvemos el resultado del future (true o false)
            executor.shutdown();
            return future.get();
        } catch (ExecutionException | InterruptedException e) {
            return false;
        }
    }

    /**
     * Este metodo permite cambiar el email de un usuario
     * @param nombre es el nombre del usuario al que le vamos a cambiar el email
     * @param email es el nuevo email que le vamos a poner
     * @return true si se cambia el email correctamente, false si no
     */
    public static boolean cambiarEmail(String nombre, String email) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Boolean> future = executor.submit(() -> {
            //creamos el mapa con los valores necesarios
            HashMap<String,String> data = new HashMap<>();
            data.put("nombre",nombre);
            data.put("email",email);
            //obtenemos la respuesta
            String respuesta = HttpRequest.postRequest(Constantes.URL_CAMBIAR_EMAIL,data);
            //la parseamos
            JsonElement element = JsonParser.parseString(respuesta);
            //retornamos la respuesta como boolean
            return element.getAsBoolean();

        });
        try {
            //terminamos el executor y delvolvemos el resultado del future, (true o flase)
            executor.shutdown();
            return future.get();
        } catch (ExecutionException | InterruptedException e) {
            return false;
        }
    }

    /**
     * Este metodo permite cambiar el telefono a un usuario
     * @param nombre es el nombre del usuario al que le vamos a cambiar el telefono
     * @param telefono es el nuevo telefono que le vamos a asignar
     * @return true si se cambia correctamente el telefono, false si no
     * @author Fernando
     */
    public static boolean cambiarTelefono(String nombre, String telefono) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Boolean> future = executor.submit(() -> {
            //creamos el mapa con los valores necesarios
            HashMap<String,String> data = new HashMap<>();
            data.put("nombre",nombre);
            data.put("telefono",telefono);
            //obtenemos la respuesta
            String respuesta = HttpRequest.postRequest(Constantes.URL_CAMBIAR_TELEFONO,data);
            //la parseamos
            JsonElement element = JsonParser.parseString(respuesta);
            //retornamos la respuesta como boolean
            return element.getAsBoolean();

        });
        try {
            //terminamos el executor y delvolvemos el resultado del future, (true o false)
            executor.shutdown();
            return future.get();
        } catch (ExecutionException | InterruptedException e) {
            return false;
        }
    }
}

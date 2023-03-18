package com.example.proyectofinalandroid.controller.tools;

import com.example.proyectofinalandroid.controller.acceso.Codigos;
import com.example.proyectofinalandroid.controller.acceso.Registro;
import com.example.proyectofinalandroid.controller.baseDeDatos.Constantes;
import com.example.proyectofinalandroid.controller.baseDeDatos.HttpRequest;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Esta clase permite comprobar que los datos introducidos por
 * el usuario son correctos
 *
 * @author Fernando
 */
public class ComprobarDatos implements Patrones {
    /**
     * Este metodo permite comprobar que el nombre del usuario cumple con
     * los requisitos adecuados
     *
     * @param cadena es la cadena que queremos comprobar
     * @return true si la cadena cumple con las condiciones, false si no
     * @author Fernando
     */
    public static boolean comprobarNombre(String cadena) {
        return cadena.matches(PATRON_NOMBRE);
    }

    /**
     * Este metodo nos permite comprobar que el formato del correo electronico
     * es valido
     *
     * @param email es el email que queremos comprobar
     * @return true si el correo es valido, false si no lo es
     * @author Fernando
     */
    public static boolean comprobarCorreo(String email) {
        return email.matches(PATRON_EMAIL);
    }

    /**
     * Este metodo comprueba que un el formato del numero de telefono
     * es valido
     *
     * @param telefono es el telefono que queremos comprobar
     * @return true si es valido, false si no
     * @author Fernando
     */
    public static boolean comprobarTelefono(String telefono) {
        return telefono.matches(PATRON_TELEFONO);
    }

    /**
     * Este metodo comprueba que las dos contraseñas son iguales
     *
     * @param password1 es la primera contraseña
     * @param password2 es la segunda contraseña
     * @return true si las contraseñas coinciden, false si no
     * @author Fernando
     */
    public static boolean comprobarPassword(String password1, String password2) {
        return password1.equals(password2);
    }

    /**
     * Este metodo permite comprobar que la contraseña contiene al menos
     * 8 caracteres de los cuales uno es un numero
     *
     * @param password es la contraseña que queremos comprobar
     * @return true si es valida, false si no
     * @author Fernando
     */
    public static boolean comprobarFormatoPassword(String password) {
        return password.matches(PATRON_FORMATO_PASSWORD);
    }

    /**
     * Este metodo permite comprobar si existe un usuario con un deteminado
     * nombre en la base de datos
     *
     * @param nombre es el nombre del usuario que queremos comprobar
     * @return Codigos.ERROR si existe alguien con ese nombre, 0 si no
     * @author Fernando
     */
    public static int existeUsuario(String nombre){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(() -> {
            //creamos el mapa con los parametros
            HashMap<String, String> params = new HashMap<>();
            //añadimos el nombre al mapa
            params.put("nombre", nombre);
            //obtenemos la respuesta
            String respuesta = HttpRequest.getRequest(Constantes.URL_EXISTE_USUARIO, params);
            //parseamos la respuesta
            JsonElement element = JsonParser.parseString(respuesta);
            //devolvemos un resutado en funcion de lo ocurrido
            if (element.getAsBoolean()) {
                return 0;
            } else {
                return Codigos.ERROR;
            }
        });
        try {
            executor.shutdown();
            return future.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return Codigos.ERROR;

    }



}

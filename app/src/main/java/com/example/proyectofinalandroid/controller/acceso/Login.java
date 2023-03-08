package com.example.proyectofinalandroid.controller.acceso;

import com.example.proyectofinalandroid.controller.baseDeDatos.Constantes;
import com.example.proyectofinalandroid.controller.baseDeDatos.HttpRequest;
import com.example.proyectofinalandroid.controller.tools.Cifrado;
import com.example.proyectofinalandroid.controller.tools.ComprobarDatos;
import com.example.proyectofinalandroid.model.Usuario;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

/**
 * Esta clase permite que el usuario se registre en el sistema
 * @author Fernando
 */
public class Login implements Codigos {
    /**
     * Este metodo permite comprobar si los datos introducidos
     * en el formulario de login son correctos
     * @param nombre es el nombre de la persona
     * @param password es la contraseña de la persona
     * @return el codigo de error correspondiente
     * @author Fernando
     */
    public static int login(String nombre, String password) throws ExecutionException, InterruptedException {
        if (ComprobarDatos.existeUsuario(nombre) == ERROR) {
            return ERROR_NO_EXISTE_USUARIO;
        }
        if (Cifrado.SHA256(password).equals(obtenerDatos(nombre,Codigos.OBTENER_PASSWORD))) {
            return CORRECTO;
        }
        return ERROR_PASSWORD_INCORRECTA;
    }

    /**
     * Este metodo nos permite obtener algun dato de una persona
     * a partir de su nombre
     * @param nombre es el nombre de la persona que estamos buscando
     * @param tipoDato es el dato que queremos obtener del usuario
     * @return el dato indicado si existe la persona, una cadena vacia si no existe
     * @author Fernando
     */
    public static String obtenerDatos(String nombre,String tipoDato) {
        String url = Constantes.URL_LOGIN;
        HashMap<String,String> params = new HashMap<>();
        params.put("nombre",nombre);
        String jsonResultado = HttpRequest.GET_REQUEST(url, params);

        Gson gson = new Gson();
        JsonElement jsonElement = gson.fromJson(jsonResultado, JsonElement.class);
        JsonArray jsonArray = jsonElement.getAsJsonArray();

        int id = jsonArray.get(0).getAsInt();
        String name = jsonArray.get(1).getAsString();
        String contrasena = jsonArray.get(2).getAsString();
        String email = jsonArray.get(3).getAsString();
        String telefono = jsonArray.get(4).getAsString();
        String tipo = jsonArray.get(5).getAsString();

        Usuario usuario = new Usuario(id, name, contrasena, email, telefono, tipo);
        if (tipoDato.equals(Codigos.OBTENER_PASSWORD)) {
            return usuario.getPassword();
        } else if (tipoDato.equals(Codigos.OBTENER_TIPO)) {
            return usuario.getTipo();
        }

        return null;
    }
}

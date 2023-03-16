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
    public static int login(String nombre, String password) {
        //se comprueba que el usuario exista
        if (ComprobarDatos.existeUsuario(nombre) == ERROR) {
            return ERROR_NO_EXISTE_USUARIO;
        }
        //se comprueba que la contraseña sea correcta
        if (Cifrado.SHA256(password).equals(obtenerDatos(nombre,Codigos.OBTENER_PASSWORD))) {
            return CORRECTO;
        }
        //en caso de que la contraseña no sea correcta
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
        //crear el mapa para añadir los parametros
        HashMap<String,String> params = new HashMap<>();
        //añadimos el nombre el mapa
        params.put("nombre",nombre);
        //hacemos la peticion y guardamos la respuesta
        String jsonResultado = HttpRequest.getRequest(url, params);
        //parseamos la respuesta
        Gson gson = new Gson();
        JsonElement jsonElement = gson.fromJson(jsonResultado, JsonElement.class);
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        // obtenemos los datos
        int id = jsonArray.get(0).getAsInt();
        String name = jsonArray.get(1).getAsString();
        String contrasena = jsonArray.get(2).getAsString();
        String email = jsonArray.get(3).getAsString();
        String telefono = jsonArray.get(4).getAsString();
        String tipo = jsonArray.get(5).getAsString();
        //creamos el obtejo usuario
        Usuario usuario = new Usuario(id, name, contrasena, email, telefono, tipo);
        //obtenemos el dato que se nos pide
        if (tipoDato.equals(Codigos.OBTENER_PASSWORD)) {
            return usuario.getPassword();
        } else if (tipoDato.equals(Codigos.OBTENER_TIPO)) {
            return usuario.getTipo();
        }

        return null;
    }
}

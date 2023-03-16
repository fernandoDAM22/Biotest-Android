package com.example.proyectofinalandroid.controller.acceso;

import android.content.Context;

import com.example.proyectofinalandroid.controller.baseDeDatos.Constantes;
import com.example.proyectofinalandroid.controller.baseDeDatos.HttpRequest;
import com.example.proyectofinalandroid.controller.tools.Cifrado;
import com.example.proyectofinalandroid.controller.tools.ComprobarDatos;
import com.example.proyectofinalandroid.model.Usuario;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.HashMap;

public class Registro implements Codigos {
    /**
     * Este metodo permite comprobar que los datos de registro estan correctos
     *
     * @param nombre    es el nombre de la persona
     * @param email     es el email de la persona
     * @param password1 es la contraseña de la persona
     * @param password2 es la repeticion de la contraseña de la persona
     * @param telefono  es el telefono de la persona
     * @param context   es el contexto de la aplicacion donde se lanzara el AlertDialog
     *                  para confirmar la operacion
     * @return el codigo correspondiente en funcion de lo ocurrido
     * @author Fernando
     */
    public static int comprobarRegistro(String nombre, String email, String password1, String password2, String telefono, Context context) {
        //Se comprueba que no haya campos vacios
        if (nombre.equals("") || email.equals("") || password1.equals("") || password2.equals("") || telefono.equals("")) {
            return ERROR_CAMPOS_VACIOS;
        }
        //Se comprueba que el nombre solo tenga espacios y letras
        if (!ComprobarDatos.comprobarNombre(nombre)) {
            return ERROR_NOMBRE;
        }
        //se comprueba que el correo tenga un formato valido
        if (!ComprobarDatos.comprobarCorreo(email)) {
            return ERROR_EMAIL;
        }
        //se comprueba que las dos contraseñas coinciden
        if (!ComprobarDatos.comprobarPassword(password1, password2)) {
            return ERROR_PASSWORDS;
        }
        //se comprueba que el formato de la contraseña es correcto
        if (!ComprobarDatos.comprobarFormatoPassword(password1)) {
            return ERROR_FORMATO_PASSWORD;
        }
        //se comprueba que el telefono
        if (!ComprobarDatos.comprobarTelefono(telefono)) {
            return ERROR_TELEFONO;
        }
        if (ComprobarDatos.existeUsuario(nombre) == 0) {
            return ERROR_EXISTE_USUARIO;
        }
        boolean resultado = registrarUsuario(new Usuario(nombre, Cifrado.SHA256(password1), email, telefono, USUARIO_NORMAL));
        if (resultado) {
            return CORRECTO;
        } else {
            return ERROR;
        }


    }


    /**
     * Este metodo inserta el usuario en la base de datos
     *
     * @param usuario es el usuario con los datos que vamos a insertar
     * @author Fernando
     */
    public static boolean registrarUsuario(Usuario usuario) {
        //creamos el mapa con los datos del usuario
        HashMap<String, String> params = new HashMap<>();
        //agregamos los datos del usuario al mapa
        params.put("nombre", usuario.getNombre());
        params.put("email", usuario.getEmail());
        params.put("telefono", usuario.getTelefono());
        params.put("password", usuario.getPassword());
        params.put("tipo", usuario.getTipo());
        //realizamos la peticion y obtenemos la respuesta
        String respuesta = HttpRequest.postRequest(Constantes.URL_REGISTRO, params);
        //parseamos la respuesta y la devolvemos como booleano
        JsonElement element = JsonParser.parseString(respuesta);
        return element.getAsBoolean();

    }

}

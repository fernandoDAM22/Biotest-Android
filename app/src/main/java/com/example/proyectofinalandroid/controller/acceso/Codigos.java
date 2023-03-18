package com.example.proyectofinalandroid.controller.acceso;

/**
 * Este interfaz contiene los codigos necesarios para poder informar al
 * usuario de lo que esta pasando
 *
 * @author Fernando
 */
public interface Codigos {

    /**
     * Este codigo se manda cuando ocurre algun error
     */
    int ERROR = -1000;
    /**
     * Este codigo se manda cuando se inserta el usuario
     */
    int CORRECTO = 1;
    /**
     * Este codigo se manda cuando algunos de los campos
     * del formulacio estan vacios
     */
    int ERROR_CAMPOS_VACIOS = -1;
    /**
     * Este codigo se manda cuando el nombre no cumple con el formato,
     * formato: (solo letras y espacios)
     */
    int ERROR_NOMBRE = -2;
    /**
     * Este codigo se manda cuando el email del usuario
     * no cumple con las caracteristicas
     */
    int ERROR_EMAIL = -3;
    /**
     * Este codigo se manda cuando las dos contraseñas no coinciden
     */
    int ERROR_PASSWORDS = -4;
    /**
     * Este codigo se manda cuando la contraseña no cumple
     * con el formato, formato: (8 caracteres de los cuales
     * uno debe ser un numero)
     */
    int ERROR_FORMATO_PASSWORD = -5;
    /**
     * Este codigo se manda cuando el telefono no cumple con el
     * formato, formato: (9 numeros)
     */
    int ERROR_TELEFONO = -6;
    /**
     * Este error se manda cuando ya existe un usuario con ese nombre
     * en la base de datos
     */
    int ERROR_EXISTE_USUARIO = -7;
    /**
     * Este error se manda cuando el usuario pulsa el boton
     * de cancelar
     */
    int CANCELADO = -8;
    /**
     * Este codigo se manda cuando no existe ningun usuario con ese nombre
     */
    int ERROR_NO_EXISTE_USUARIO = -9;
    /**
     * Este error se manda cuando el la contraseña no coincide con la del
     * usuario
     */
    int ERROR_PASSWORD_INCORRECTA = -10;

    /**
     * Esta cadena indica que el usuario va a ser tipo user
     */

    String USUARIO_NORMAL = "user";

    /**
     * Con esta constante le indicamos al metodo de obtener datos,
     * que queremos obtener la contraseña del usuario
     */
    String OBTENER_PASSWORD = "contrasena";
    /**
     * Con esta constante le indicamos al metodo de obtener datos,
     * que queremos obtener el tipo del usuario
     */
    String OBTENER_TIPO = "tipo";

    String OBTENER_EMAIL = "email";
    String OBTENER_TELEFONO = "telefono";
}

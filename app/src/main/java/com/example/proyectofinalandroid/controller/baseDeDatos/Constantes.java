package com.example.proyectofinalandroid.controller.baseDeDatos;

/**
 * Esta interfaz contiene contantes con las direcciones a los
 * scripts de php en el servidor
 * @author Fernando
 */
public interface Constantes {
    String SERVER = "http://192.168.1.14/webserviceProyecto/";
    String URL_LOGIN = SERVER + "acceso/login.php";
    String URL_EXISTE_USUARIO =  SERVER + "acceso/existeUsuario.php";

}

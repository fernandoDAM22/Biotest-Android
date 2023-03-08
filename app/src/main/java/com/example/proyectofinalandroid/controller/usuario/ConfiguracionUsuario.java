package com.example.proyectofinalandroid.controller.usuario;
/**
 * Esta clase guarda algunos datos que nos seran necesarios para
 * permitir el acceso al usuario a determinadas acciones
 *
 * @author Fernando
 */
public class ConfiguracionUsuario {
    /**
     * Contiene el nombre del usuario logueado
     */
    private static String nombreUsuario;

    /**
     * Este metodo permite guardar el nombre del usuario que esta registrado
     * actualmente en el sistema
     * @param nombreUsuario es el nombre del usuario conectado actualmente
     * @author Fernando
     */
    public static void setNombreUsuario(String nombreUsuario) {
        ConfiguracionUsuario.nombreUsuario = nombreUsuario;
    }

    /**
     * Este metodo permite obtener el nombre del usuario conectado actualmente
     * @return el nombre del usuario conectado actualmente
     * @author Fernando
     */
    public static String getNombreUsuario(){
        return nombreUsuario;
    }
}

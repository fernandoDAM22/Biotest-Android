package com.example.proyectofinalandroid.controller.baseDeDatos;

/**
 * Esta interfaz contiene contantes con las direcciones a los
 * scripts de php en el servidor
 * @author Fernando
 */
public interface Constantes {
    String SERVER = "http://192.168.1.143/webserviceProyecto/";
    String URL_LOGIN = SERVER + "acceso/login.php";
    String URL_EXISTE_USUARIO =  SERVER + "acceso/existeUsuario.php";
    String URL_REGISTRO = SERVER + "acceso/registro.php";
    String URL_OBTENER_CUESTIONARIOS_COMPLETOS = SERVER + "admin/cuestionarios/obtenerCuestionariosCompletos.php";
    String URL_OBTENER_IDS_PARTIDAS = SERVER + "admin/partidas/obtenerIds.php";
    String URL_INSERTAR_PARTIDA = SERVER + "admin/partidas/insertarPartida.php";
    String URL_INSERTAR_PREGUNTA_PARTIDA = SERVER + "admin/partidas/insertarPregunta.php";
    String URL_ESTABLECER_PUNTUACION = SERVER + "admin/partidas/establecerPuntuacion.php";
    String URL_OBTENER_PREGUNTAS_PARTIDA = SERVER + "admin/obtenerPreguntasPartidas";
    String URL_OBTENER_PUNTUACION = SERVER + "admin/categorias/obtenerPuntuacion";

    String URL_OBTENER_IDS = SERVER + "admin/preguntas/obtenerIds.php" ;
    String URL_OBTENER_DATOS = SERVER + "admin/preguntas/obtenerDatos.php";

    String URL_OBTENER_IDS_PREGUNTAS_CUESTIONARIO = SERVER + "admin/cuestionarios/obtenerIdPreguntas.php";
    String URL_OBTENER_ID_USUARIO = SERVER + "admin/usuarios/obtenerIdUsuario.php";
}

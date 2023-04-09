package com.example.proyectofinalandroid.controller.baseDeDatos;

/**
 * Esta interfaz contiene contantes con las direcciones a los
 * scripts de php en el servidor
 * @author Fernando
 */
public interface Constantes {
    String SERVER = "http://192.168.1.79/webserviceProyecto/";
    String URL_LOGIN = SERVER + "acceso/login.php";
    String URL_EXISTE_USUARIO =  SERVER + "acceso/existeUsuario.php";
    String URL_REGISTRO = SERVER + "acceso/registro.php";
    String URL_OBTENER_CUESTIONARIOS_COMPLETOS = SERVER + "admin/cuestionarios/obtenerCuestionariosCompletos.php";
    String URL_OBTENER_IDS_PARTIDAS = SERVER + "admin/partidas/obtenerIds.php";
    String URL_INSERTAR_PARTIDA = SERVER + "admin/partidas/insertarPartida.php";
    String URL_INSERTAR_PREGUNTA_PARTIDA = SERVER + "admin/partidas/insertarPregunta.php";
    String URL_ESTABLECER_PUNTUACION = SERVER + "admin/partidas/establecerPuntuacion.php";
    String URL_OBTENER_PREGUNTAS_PARTIDA = SERVER + "admin/partidas/obtenerPreguntasPartida.php";
    String URL_OBTENER_PUNTUACION = SERVER + "admin/partidas/obtenerPuntuacion.php";

    String URL_OBTENER_IDS = SERVER + "admin/preguntas/obtenerIds.php" ;
    String URL_OBTENER_DATOS = SERVER + "admin/preguntas/obtenerDatos.php";

    String URL_OBTENER_IDS_PREGUNTAS_CUESTIONARIO = SERVER + "admin/cuestionarios/obtenerIdPreguntas.php";
    String URL_OBTENER_ID_USUARIO = SERVER + "admin/usuarios/obtenerIdUsuario.php";
    String URL_PREGUNTA_ACERTADA = SERVER + "admin/preguntas/preguntaAcertada.php";
    String URL_OBTENER_ID = SERVER + "admin/preguntas/obtenerId.php";
    String URL_OBTENER_CATEGORIA_PREGUNTA = SERVER + "admin/categorias/obtenerCategoriaPregunta.php";
    String URL_CAMBIAR_PASSWORD = SERVER + "admin/usuarios/modificarPassword.php";
    String URL_CAMBIAR_USUARIO = SERVER + "admin/usuarios/modificarUsuario.php";
    String URL_CAMBIAR_EMAIL = SERVER + "admin/usuarios/modificarEmail.php";
    String URL_CAMBIAR_TELEFONO = SERVER + "admin/usuarios/modificarTelefono.php";
    String URL_OBTENER_PARTIDA = SERVER + "admin/partidas/obtenerPartida.php";

    String EMAIL = "BioTestProyecto@gmail.com";
    String URL_LICENCIA = "https://creativecommons.org/licenses/by-nc-sa/4.0/deed.es";
}

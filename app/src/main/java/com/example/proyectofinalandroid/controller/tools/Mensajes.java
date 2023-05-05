package com.example.proyectofinalandroid.controller.tools;

/**
 * En esta interfaz tenemos los mensajes que mostramos al usuario
 *
 * @author Fernando
 */
public interface Mensajes {
    
    
    
    String DESCRIPCION_MODO_LIBRE = "En este modo de juego podras responder todas las preguntas que quieras";
    String DESCRIPCION_MODO_SIN_FALLOS = "En este modo de juego podras responder preguntas hasta que falles una";
    String DESCRIPCION_MODO_CLASICO = "En este modo de juego jugaras una partida de 10 preguntas";
    String DESCRIPCION_CUESTIONARIOS = "En este modo de juego jugaras una partida resolviendo un cuestionario";

    String ERROR_CAMPOS_VACIOS = "Error, rellene todos los campos";
    String ERROR_NO_EXISTE_USUARIO = "Error, no existe el usuario";
    String ERROR_PASSWORD_INCORRECTA = "Error, contraseña incorrecta";
    String ERROR_PASSWORD_ACTUAL_INCORRECTA = "La contraseña actual no es correcta";
    String ERROR_REQUISTOS_EMAIL = "Error, el email no cumple con los requistos";
    String CAMBIO_EMAIL_CORRECTO = "Email cambiado correctamete";
    String ERROR_CAMBIAR_EMAIL = "Error al cambiar el email";
    String ERROR_REQUSITOS_EMAIL = "Error, el nombre de usuario no cumple con los requisitos";
    String CAMBIO_USERNAME_CORRECTO = "Nombre de usuario cambiado correctamete";
    String ERROR_CAMBIO_USERNAME = "Error al cambiar el nombre de usuario";
    String ERROR_PASSWORDS = "Las contraseñas no coinciden";
    String CAMBIO_PASSWORD_CORRECTO = "Contraseña cambiada correctamente";
    String ERROR_CAMBIO_CONTRASEÑA = "Error al cambiar la contraseña";
    String ERROR_FORMATO_TELEFONO = "Error, el telefono no cumple con los requistos";
    String CAMBIO_TELEFONO_CORRECTO = "Telefono cambiado correctamete";
    String MENSAJE_VOLVER_EN_PARTIDA = "No se puede volver atras hasta que finalices la partida";
    String ERROR_INICIAR_PARTIDA = "No se ha podido inicar la partida";
    String ERROR_RESPONDER_PRIMERO = "Debe responder a la pregunta antes de pasar a la siguiente";
    String MENSAJE_CONFIRMACION_FINALIZAR = "¿Está seguro de que desea finalizar?";
    String MENSAJE_CONFIRMACION_REGISTRO = "Estas seguro de que quieres registrarse";
    String ERROR_FORMATO_USERNAME = "Error, el nombre no es correcto";
    String ERROR_FORMATO_PASSWORD = "Error, el formato de la contraseña no es correcto";
    String ERROR_EXISTE_USERNAME = "Error, ya existe un usuario con ese nombre";
    String OPERACION_CANCELADA = "Operacion cancelada";
    String MENSAJE_USUARIO_REGISTRADO_CORRECTAMENTE = "Usuario registrado correctamente";
    String MENSAJE_PULSAR_SALIR = "Pulse de nuevo para salir";
    String ERROR_OBTENER_PREGUNTAS = "No se ha podido obtener las preguntas de la partida";
    String ERROR_OBTENER_DATOS_PREGUNTA = "Error al obtener los datos de la pregunta";
    String TITULO_RESUMEN = "-----RESUMEN PARTIDA-----\n";
    String TEXTO_USUARIO_RESUMEN = "USUARIO ===> ";
    String TEXTO_TIPO_RESUMEN = "TIPO ===> ";
    String TEXTO_PUNTUACION_RESUMEN = "PUNTUACION ===> ";
    String TEXTO_PREGUNTAS_RESUMEN = "PREGUNTAS RESPONDIDAS: ";
    String TEXTO_TITULO_RESUMEN = "RESUMEN PARTIDA";
    String TEXTO_ENVIAR_CORREO = "Enviar correo electrónico...";
    String ERROR_CLIENTES_EMAIL = "No hay clientes de correo electronico instalados";
    String ERROR_SELECCION_BOTON = "No has seleccionado ningun boton";
}

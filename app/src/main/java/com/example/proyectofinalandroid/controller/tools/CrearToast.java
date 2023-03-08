package com.example.proyectofinalandroid.controller.tools;

import android.content.Context;
import android.widget.Toast;

/**
 * Esta clase contiene un metodo que permite crear un toast
 * y retornarlo
 * @author Fernando
 */
public class CrearToast {
    /**
     * Este metodo permite crear un toast con el mensaje que se indica
     * @param mensaje es el mensaje que tendra el toast
     * @param context es el contexto en el que se muestra el toast
     * @return el toast creado
     */
    public static Toast crearToast(String mensaje, Context context){
        return Toast.makeText(context,mensaje,Toast.LENGTH_LONG);
    }
}

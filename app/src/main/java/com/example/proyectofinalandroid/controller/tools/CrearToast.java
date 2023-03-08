package com.example.proyectofinalandroid.controller.tools;

import android.content.Context;
import android.widget.Toast;

/**
 * Esta clase contiene un metodo que permite crear un toast
 * y retornarlo
 * @author Fernando
 */
public class CrearToast {
    public static Toast crearToast(String mensaje, Context context){
        return Toast.makeText(context,mensaje,Toast.LENGTH_LONG);
    }
}

package com.example.proyectofinalandroid.controller.tools;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

public class Dialogos {
    public static int mostrarDialogo(String titulo, String mensaje, Context context) {
        final int[] resultado = {-1}; // Inicializamos la variable con un valor por defecto

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titulo)
                .setMessage(mensaje)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        resultado[0] = DialogInterface.BUTTON_POSITIVE; // Guardamos el resultado en la variable
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        resultado[0] = DialogInterface.BUTTON_NEGATIVE; // Guardamos el resultado en la variable
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();

        return resultado[0]; // Devolvemos el resultado guardado en la variable
    }

}

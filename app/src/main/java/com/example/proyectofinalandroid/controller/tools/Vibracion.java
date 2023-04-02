package com.example.proyectofinalandroid.controller.tools;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

/**
 * Esta clase permite hacer que el telefono vibre
 * @author Fernando
 */
public class Vibracion {
    public static void vibrar(Context context, long duracion) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null && vibrator.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // Crea un objeto VibrationEffect que especifica el patrón de vibración
                VibrationEffect vibrationEffect = VibrationEffect.createOneShot(duracion, VibrationEffect.DEFAULT_AMPLITUDE);
                // Vibra con el patrón especificado
                vibrator.vibrate(vibrationEffect);
            } else {
                // Si la API de Android es anterior a la versión 8 (Android Oreo), utiliza el método anterior
                vibrator.vibrate(duracion);
            }
        }
    }
}

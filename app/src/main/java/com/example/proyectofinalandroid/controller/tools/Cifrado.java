package com.example.proyectofinalandroid.controller.tools;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Este metodo permite cifrar las contraseñas mediante el
 * algoritmo de cifrado SHA-256
 * @author Fernando
 */
public class Cifrado {
    /**
     * Este metodo retorna el hash de una contraseña mediante el algoritmo
     * de cifrado SHA-256
     * @param password es la contraseña que queremos cifrar
     * @return retorna el hash de la contraseña
     * @author Fernando
     */
    public static String SHA256(String password) {
        MessageDigest md = null;
        byte[] hash;
        try {
            md = MessageDigest.getInstance("SHA-256");
            hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}

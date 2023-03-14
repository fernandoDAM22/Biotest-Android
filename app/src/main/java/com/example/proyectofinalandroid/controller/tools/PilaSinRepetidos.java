package com.example.proyectofinalandroid.controller.tools;

import java.util.Stack;

/**
 * Esta clase permite crear una pila de numeros enteros sin repetidos
 * @author Fernando
 */
public class PilaSinRepetidos extends Stack<Integer> {
    @Override
    public Integer push(Integer item) {
        // Verifica si el elemento ya existe en la pila
        if (!this.contains(item)) {
            // Si no existe, lo añade a la pila
            return super.push(item);
        }
        // Si ya existe, no hace nada y devuelve null
        return null;
    }
}

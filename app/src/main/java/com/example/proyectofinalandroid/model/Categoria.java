package com.example.proyectofinalandroid.model;

import java.io.Serializable;

/**
 * Clase modelo de la categoria.
 * La categoria de la preguna o cuestionario
 * @author Fernando
 */
public class Categoria implements Serializable {
    /**
     * Id de la categoria
     */
    private int id;
    /**
     * Nombre de la categoria
     */
    private String nombre;
    /**
     * Descripcion de la pregunta, contiene una breve descripcion del tipo
     * de contenido que se trata en la pregunta o cuestionario
     */
    private String descripcion;

    /**
     * Constructor vacio
     */
    public Categoria() {
    }

    /**
     * Constructor parametrizado
     * @param id es el id de la categoria
     * @param nombre es el nombre de categoria
     * @param descripcion es la descripcion de la categoria, contiene una breve descripcion de
     *                    los contenidos que se van a tratar en las preguntas y cuestionarios que
     *                    pertenecen a ella
     */
    public Categoria(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Categoria(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}

package com.example.proyectofinalandroid.model;

import java.util.ArrayList;

/**
 * Clase modelo del cuestionario
 * @author Fernando
 */
public class Cuestionario {
    /**
     * Id del cuestionario
     */
    private int id;
    /**
     * Nombre del cuestionario
     */
    private String nombre;
    /**
     * Id de la categoria, esta id corresponde con el id de la categoria correspondiente
     */
    private int idCategoria;
    /**
     * Descripcion del cuestionario
     */
    private String descripcion;
    /**
     * Lista de preguntas del cuestionario
     */
    private ArrayList<Pregunta> preguntas;

    /**
     * Constructor vacio
     */
    public Cuestionario() {
    }

    /**
     * Este constructor crea un cuestionario sin preguntas, no obstante inicializa el ArrayList de las preguntas
     * a uno vacio
     * @param id es él id del cuestionario
     * @param nombre es el nombre del cuestionario
     * @param idCategoria es él id de la categoria, esté id corresponde con el id de la categoria correspondiente
     */
    public Cuestionario(int id, String nombre, int idCategoria) {
        this.id = id;
        this.nombre = nombre;
        this.idCategoria = idCategoria;
        preguntas = new ArrayList<>();
    }

    public Cuestionario(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    /**
     *
     * @param id es el id del cuestionario
     * @param nombre es el nombre del cuestionario
     * @param idCategoria es el id de la categoria, esté id corresponde con el id de la categoria correspondiente
     * @param preguntas es la lista de preguntas del cuestionario
     */
    public Cuestionario(int id, String nombre, int idCategoria, ArrayList<Pregunta> preguntas) {
        this.id = id;
        this.nombre = nombre;
        this.idCategoria = idCategoria;
        this.preguntas = preguntas;
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

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public ArrayList<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(ArrayList<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Cuestionario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", idCategoria=" + idCategoria +
                ", preguntas=" + preguntas +
                '}';
    }
}

package com.example.proyectofinalandroid.model;

import java.io.Serializable;

/**
 * Clase modelo de las preguntas
 * @author Fernando
 */
public class Pregunta implements Serializable {
    /**
     * Id de la preguna
     */
    private int id;
    /**
     * enunciado de la pregunta
     */
    private String enunciado;
    /**
     * Respuesta correcta de la pregunta
     */
    private String respuestaCorrecta;
    /**
     * Respuesta incorrecta 1 de la pregunta
     */
    private String respuestaIncorrecta1;
    /**
     * Respuesta incorrecta 2 de la pregunta
     */
    private String respuestaIncorrecta2;
    /**
     * Respuesta incorrecta 3 de la pregunta
     */
    private String respuestaIncorrecta3;
    /**
     * Id de la categoria de la pregunta, este id coincide con el id de su correspondiente
     * categoria en la base de datos
     */
    private int idCategoria;

    /**
     * Constructor vacio
     */
    public Pregunta() {
    }

    /**
     * Constructor parametrizado
     * @param id es el id de la pregunta
     * @param enunciado es el enunciado de la pregunta
     * @param respuestaCorrecta es la respuesta correcta a la pregunta
     * @param respuestaIncorrecta1 es la primera respuesta incorrecta de la pregunta
     * @param respuestaIncorrecta2 es la segunda respuesta incorrecta de la pregunta
     * @param respuestaIncorrecta3 es la tercera respuesta incorrecta de la pregunta
     * @param idCategoria es el id de la categoria, esté id corresponde con el id de la categoria correspondiente
     */
    public Pregunta(int id, String enunciado, String respuestaCorrecta, String respuestaIncorrecta1, String respuestaIncorrecta2, String respuestaIncorrecta3, int idCategoria) {
        this.id = id;
        this.enunciado = enunciado;
        this.respuestaCorrecta = respuestaCorrecta;
        this.respuestaIncorrecta1 = respuestaIncorrecta1;
        this.respuestaIncorrecta2 = respuestaIncorrecta2;
        this.respuestaIncorrecta3 = respuestaIncorrecta3;
        this.idCategoria = idCategoria;
    }

    /**
     * Este constructor permite crear una pregunta sin especificar el id
     * @param enunciado es el enunciado de la pregunta
     * @param respuestaCorrecta es la respuesta correcta a la pregunta
     * @param respuestaIncorrecta1 es la primera respuesta incorrecta de la pregunta
     * @param respuestaIncorrecta2 es la segunda respuesta incorrecta de la pregunta
     * @param respuestaIncorrecta3 es la tercera respuesta incorrecta de la pregunta
     * @param idCategoria es el id de la categoria, esté id corresponde con el id de la categoria correspondiente
     */
    public Pregunta(String enunciado, String respuestaCorrecta, String respuestaIncorrecta1, String respuestaIncorrecta2, String respuestaIncorrecta3, int idCategoria) {
        this.enunciado = enunciado;
        this.respuestaCorrecta = respuestaCorrecta;
        this.respuestaIncorrecta1 = respuestaIncorrecta1;
        this.respuestaIncorrecta2 = respuestaIncorrecta2;
        this.respuestaIncorrecta3 = respuestaIncorrecta3;
        this.idCategoria = idCategoria;
    }

    /**
     * Este constructor permite crear una pregunta sin especificar el id y la categoria
     * @param enunciado es el enunciado de la pregunta
     * @param respuestaCorrecta es la respuesta correcta a la pregunta
     * @param respuestaIncorrecta1 es la primera respuesta incorrecta de la pregunta
     * @param respuestaIncorrecta2 es la segunda respuesta incorrecta de la pregunta
     * @param respuestaIncorrecta3 es la tercera respuesta incorrecta de la pregunta
     */
    public Pregunta(String enunciado, String respuestaCorrecta, String respuestaIncorrecta1, String respuestaIncorrecta2, String respuestaIncorrecta3) {
        this.enunciado = enunciado;
        this.respuestaCorrecta = respuestaCorrecta;
        this.respuestaIncorrecta1 = respuestaIncorrecta1;
        this.respuestaIncorrecta2 = respuestaIncorrecta2;
        this.respuestaIncorrecta3 = respuestaIncorrecta3;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public String getRespuestaIncorrecta1() {
        return respuestaIncorrecta1;
    }

    public void setRespuestaIncorrecta1(String respuestaIncorrecta1) {
        this.respuestaIncorrecta1 = respuestaIncorrecta1;
    }

    public String getRespuestaIncorrecta2() {
        return respuestaIncorrecta2;
    }

    public void setRespuestaIncorrecta2(String respuestaIncorrecta2) {
        this.respuestaIncorrecta2 = respuestaIncorrecta2;
    }

    public String getRespuestaIncorrecta3() {
        return respuestaIncorrecta3;
    }

    public void setRespuestaIncorrecta3(String respuestaIncorrecta3) {
        this.respuestaIncorrecta3 = respuestaIncorrecta3;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public String toString() {
        return "Pregunta{" +
                "id=" + id +
                ", enunciado='" + enunciado + '\'' +
                ", respuestaCorrecta='" + respuestaCorrecta + '\'' +
                ", respuestaIncorrecta1='" + respuestaIncorrecta1 + '\'' +
                ", respuestaIncorrecta2='" + respuestaIncorrecta2 + '\'' +
                ", respuestaIncorrecta3='" + respuestaIncorrecta3 + '\'' +
                ", idCategoria=" + idCategoria +
                '}';
    }

    /**
     * Este metodo devuelve un Array de String con las respuestas de una pregunta
     * @return un Array de String con los datos de la pregunta
     */
    public String[] obtenerRespuestas() {
        return new String[]{respuestaCorrecta,respuestaIncorrecta1,respuestaIncorrecta2,respuestaIncorrecta3};
    }
}

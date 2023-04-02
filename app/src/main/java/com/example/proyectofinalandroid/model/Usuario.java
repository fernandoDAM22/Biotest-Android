package com.example.proyectofinalandroid.model;

/**
 * Clase modelo para el usuario
 */
public class Usuario {
    /**
     * Id de la persona
     */
    private int id;
    /**
     * Nombre de la persona
     */
    private String nombre;
    /**
     * Contraseña de la persona
     */
    private String password;
    /**
     * Email de la persona
     */
    private String email;
    /**
     * Numero de telefono de la persona
     */
    private String telefono;
    /**
     * Tipo de usuario
     */
    private String tipo;

    public Usuario() {
    }

    /**
     * Este constructor permite crear una persona sin el id
     * @param nombre es el nombre de la persona
     * @param password es la contraseña cifrada de la persona
     * @param email es el email de la persona
     * @param telefono es el telefono de la persona
     */
    public Usuario(String nombre, String password, String email, String telefono, String tipo) {
        this.nombre = nombre;
        this.password = password;
        this.email = email;
        this.telefono = telefono;
        this.tipo = tipo;
    }

    /**
     * Constructor parametrizado
     * @param id es el id de la persona
     * @param nombre es el nombre de la persona
     * @param password es la contraseña cifrada de la persona
     * @param email es el email de la persona
     * @param telefono es el telefono de la persona
     * @param tipo es el tipo de usuario
     */
    public Usuario(int id, String nombre, String password, String email, String telefono, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.password = password;
        this.email = email;
        this.telefono = telefono;
        this.tipo = tipo;
    }
    /**
     * Constructor parametrizado
     * @param nombre es el nombre de la persona
     * @param email es el email de la persona
     * @param telefono es el telefono de la persona
     * @param tipo es el tipo de usuario
     */
    public Usuario(String nombre, String email, String telefono, String tipo) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.tipo = tipo;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}

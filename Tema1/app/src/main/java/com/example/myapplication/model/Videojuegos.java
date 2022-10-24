package com.example.myapplication.model;

public class Videojuegos {
    private int imagenID;
    private String titulo;
    private String descripcion;

    public Videojuegos(int imagenID, String titulo, String descripcion){
        this.descripcion = descripcion;
        this.imagenID = imagenID;
        this.titulo = titulo;
    }

    public int getImagenID() {
        return imagenID;
    }

    public void setImagenID(int imagenID) {
        this.imagenID = imagenID;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
package com.example.myapplication.modelos;

public class IAImagen {

    private String nombre_Cliente;
    private String nombre_Imagen;
    private String descripcion;
    private String url;

    public IAImagen(){

    }

    public IAImagen(String nombre_Cliente,String nombre_Imagen,String descripcion,String url){
        this.nombre_Cliente = nombre_Cliente;
        this.nombre_Imagen = nombre_Imagen;
        this.descripcion = descripcion;
        this.url = url;
    }

    public String getNombre_Imagen() {
        return nombre_Imagen;
    }

    public void setNombre_Imagen(String nombre_Imagen) {
        this.nombre_Imagen = nombre_Imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre_Cliente() {
        return nombre_Cliente;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setNombre_Cliente(String nombre_Cliente) {
        this.nombre_Cliente = nombre_Cliente;
    }
}

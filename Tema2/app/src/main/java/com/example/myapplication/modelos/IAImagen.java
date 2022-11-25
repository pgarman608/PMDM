package com.example.myapplication.modelos;

public class IAImagen {

    private String nombre_Cliente;
    private String url;

    public IAImagen(){

    }

    public IAImagen(String nombre_Cliente,String url){
        this.nombre_Cliente = nombre_Cliente;
        this.url = url;
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

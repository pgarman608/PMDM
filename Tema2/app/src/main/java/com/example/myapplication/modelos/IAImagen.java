package com.example.myapplication.modelos;

public class IAImagen {

    private static int CODIGOS = 0;

    private String nombre_Cliente;
    private int codigo_Imagen;
    private String nombre_Imagen;
    private String descripcion;
    private String url;

    public IAImagen(){

    }
    public static void setCodigos(int codigos){
        CODIGOS = codigos;
    }

    public int getCodigo_Imagen() {
        return codigo_Imagen;
    }

    public void setCodigo_Imagen(int codigo_Imagen) {
        this.codigo_Imagen = codigo_Imagen;
    }

    public IAImagen(String nombre_Cliente, String nombre_Imagen, String descripcion, String url){
        this.nombre_Cliente = nombre_Cliente;
        this.nombre_Imagen = nombre_Imagen;
        this.descripcion = descripcion;
        this.url = url;
        this.CODIGOS++;
        this.codigo_Imagen=CODIGOS;
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

    @Override
    public String toString() {
        return "IAImagen{" +
                "nombre_Cliente='" + nombre_Cliente + '\'' +
                ", codigo_Imagen=" + codigo_Imagen +
                ", nombre_Imagen='" + nombre_Imagen + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
